# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from furnitures import models as furnitureModels
from . import models
# Create your views here.

def add_order(request):
    if request.method == 'POST':
        if 'product' in request.POST:
            order = models.Order()
            order.furniture = furnitureModels.Furniture.objects.get(slug=request.POST.get('product'))
            order.client = request.user
            order.save()
        return render(request, 'orders/addedOrder.html')
