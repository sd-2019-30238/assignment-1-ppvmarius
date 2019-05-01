# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render, redirect
from furnitures import models as furnitureModels
from .models import Order
# Create your views here.

def add_order(request):
    if request.method == 'POST':
        if 'product' in request.POST:
            order = Order()
            temp_furniture = furnitureModels.Furniture.objects.get(slug=request.POST.get('product'))
            temp_furniture.quantity = temp_furniture.quantity - 1
            temp_furniture.save()
            print temp_furniture.quantity
            order.furniture = temp_furniture
            order.client = request.user
            order.status = "Unconfirmed"
            order.save()
        return render(request, 'orders/addedOrder.html')

def list_orders(request):
    orders = Order.objects.all().order_by('id')
    if not request.user.is_staff:
        orders = [item for item in orders if request.user == item.client]
    return render(request, 'orders/list_orders.html', {'orders':orders})

def order_details(request,id):
    order = Order.objects.get(id=id)
    return render(request, 'orders/order_details.html', {'order':order})

def modify_order(request):
    if request.method == 'POST':
        order = Order.objects.get(id=request.POST.get('order_id'))
        order.status = request.POST.get('current_status')
        order.save()
    return redirect('orders:list')
