# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User
from furnitures import models as furnitureModels
# Create your models here.


class Order(models.Model):
    client = models.ForeignKey(User, default=None)
    furniture = models.ForeignKey(furnitureModels.Furniture, default=None)
    status = models.CharField(max_length=50)
    # date - to be added
    def __str__(self):
        return str(self.id)
