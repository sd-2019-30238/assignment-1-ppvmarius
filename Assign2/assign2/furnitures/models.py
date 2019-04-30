# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.

class Furniture(models.Model):
    name = models.CharField(max_length=100)
    slug = models.SlugField()
    description = models.TextField()
    oldPrice = models.PositiveIntegerField()
    newPrice = models.PositiveIntegerField()
    saleType = models.CharField(max_length=10)
    quantity = models.PositiveIntegerField()
    image = models.ImageField(blank=True)

    def __str__(self):
        return self.name

    def getSnippet(self):
        return self.description[:50] + "..."
