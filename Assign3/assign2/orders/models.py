# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User
from furnitures import models as furnitureModels
# Create your models here.
import smtplib, ssl
import six
from abc import ABCMeta

class Order(models.Model):
    client = models.ForeignKey(User, default=None)
    furniture = models.ForeignKey(furnitureModels.Furniture, default=None)
    status = models.CharField(max_length=50)
    date = models.DateTimeField(auto_now_add=True)
    bonus = models.CharField(max_length=50)
    def __str__(self):
        return str(self.id)

    def getBonusProduct(self):
        return "Basic"

class Decorator(Order):
    def __init__(self, order, bonusType):
        self.order = order
        self.order.bonus = bonusType
    def getBonusProduct(self):
        return self.order.getBonusProduct()

class TigaieDecorator(Decorator):
    def __init__(self, order):
        Decorator.__init__(self, order, "Tigaie Tefal 26cm")
    def getBonusProduct(self):
        return self.getBonusProduct() + ", plus tigaie"

class TacamuriDecorator(Decorator):
    def __init__(self, order):
        Decorator.__init__(self, order, "Set 24 tacamuri")
    def getBonusProduct(self):
        return self.getBonusProduct() + ", plus tacamuri"
