# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.

def update(self,message):
    print('{} got message "{}"'.format(self.username,message))
