# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User
from furnitures import models as furnitureModels
# Create your models here.
import smtplib, ssl

class Order(models.Model):
    client = models.ForeignKey(User, default=None)
    furniture = models.ForeignKey(furnitureModels.Furniture, default=None)
    status = models.CharField(max_length=50)
    date = models.DateTimeField(auto_now_add=True)
    def __str__(self):
        return str(self.id)

    @staticmethod
    def sendEmail(email, name, client_name):
        port = 587  # For starttls
        smtp_server = "smtp.gmail.com"
        sender_email = "ppvmarius1234@gmail.com"
        password = "Assign22"
        receiver_email = email
        context = ssl.create_default_context()
        message = "Dear client: " + client_name + ", mail: " + email + ", your order: " + str(name) + " has been processed."
        print message
        with smtplib.SMTP(smtp_server, port, context=context) as server:
            server.ehlo()  # Can be omitted
            server.starttls(context=context)
            server.ehlo()  # Can be omitted
            server.login(sender_email, password)
            server.sendmail(sender_email, receiver_email, message)

    def update(self, order_id):
        if self.id == order_id:
            try:
                if self.client.email is not None:
                    self.sendEmail(self.client.email, self.id, self.client.username)
            except:
                pass
