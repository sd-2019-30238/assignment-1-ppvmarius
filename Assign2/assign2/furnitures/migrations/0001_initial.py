# -*- coding: utf-8 -*-
# Generated by Django 1.11.20 on 2019-04-30 14:55
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Furniture',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('slug', models.SlugField()),
                ('description', models.TextField()),
                ('price', models.PositiveIntegerField()),
                ('quantity', models.PositiveIntegerField()),
                ('type', models.PositiveIntegerField()),
                ('image', models.ImageField(blank=True, upload_to=b'')),
            ],
        ),
    ]
