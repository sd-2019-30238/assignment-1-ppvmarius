# -*- coding: utf-8 -*-
# Generated by Django 1.11.20 on 2019-04-30 18:50
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('furnitures', '0002_auto_20190430_1815'),
    ]

    operations = [
        migrations.AlterField(
            model_name='furniture',
            name='image',
            field=models.ImageField(blank=True, default='logo.jpg', upload_to=b''),
        ),
    ]