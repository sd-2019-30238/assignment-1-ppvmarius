# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render, redirect
from furnitures import models as furnitureModels
from .models import Order
from .utile import Observable
# Create your views here.
from furnitures.utile import FurnitureQueryService, FurnitureCommandService
from .utile import OrderQueryService, OrderCommandService

observable = Observable()
furnitureCommand = FurnitureCommandService()
furnitureQuery = FurnitureQueryService()
orderCommand = OrderCommandService()
orderQuery = OrderQueryService()

def add_order(request):
    if request.method == 'POST':
        if 'product' in request.POST:
            order = Order()
            temp_furniture = furnitureQuery.getFurniture(request.POST.get('product'))
            temp_furniture.quantity = temp_furniture.quantity - 1
            furnitureCommand.saveFurniture(temp_furniture)
            order.furniture = temp_furniture
            order.client = request.user
            order.status = "Unconfirmed"
            orderCommand.saveOrder(order)
            observable.attach(order)
        return render(request, 'orders/addedOrder.html')

def list_orders(request):
    orders = orderQuery.getOrders('id')
    for order in orders:
        observable.attach(order)
    if not request.user.is_staff:
        orders = [item for item in orders if request.user == item.client]
    return render(request, 'orders/list_orders.html', {'orders':orders})

def order_details(request,id):
    order = orderQuery.getOrder(id=id)
    return render(request, 'orders/order_details.html', {'order':order})

def modify_order(request):
    if request.method == 'POST':
        order = orderQuery.getOrder(request.POST.get('order_id'))
        order.status = request.POST.get('current_status')
        orderCommand.saveOrder(order)
        observable.Notify(order.id)
    return redirect('orders:list')
