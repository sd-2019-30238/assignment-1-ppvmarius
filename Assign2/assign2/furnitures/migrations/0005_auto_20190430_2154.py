# -*- coding: utf-8 -*-
# Generated by Django 1.11.20 on 2019-04-30 18:54
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('furnitures', '0004_auto_20190430_2151'),
    ]

    operations = [
        migrations.RenameField(
            model_name='furniture',
            old_name='image',
            new_name='thumb',
        ),
    ]
