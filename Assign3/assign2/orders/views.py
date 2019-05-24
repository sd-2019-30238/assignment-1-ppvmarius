# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render, redirect
from furnitures import models as furnitureModels
from .models import Order
from .utile import Observable
from assign2.utile import ModifyFurnitureQuantityQuery, AddOrderRequest, RequestAllOrders, RequestSpecificOrder, RequestModifySpecificOrder, Mediator

# Create your views here.
mediator = Mediator()

def add_order(request):
    if request.method == 'POST':
        if 'product' in request.POST:
            modifFurnQuery = ModifyFurnitureQuantityQuery(request.POST.get('product'))
            mediator.mediate(modifFurnQuery)
            addReq = AddOrderRequest(request.POST.get('product'), request.user, "unconfirmed", request.POST.get('bonus'))
            mediator.mediate(addReq)
        return render(request, 'orders/addedOrder.html')

def list_orders(request):
    reqAllOrd = RequestAllOrders(request, 'id')
    return mediator.mediate(reqAllOrd)

def order_details(request,id):
    reqSpecOrd = RequestSpecificOrder(request, id)
    return mediator.mediate(reqSpecOrd)

def modify_order(request):
    if request.method == 'POST':
        reqModifOrder = RequestModifySpecificOrder(request.POST.get('order_id'),request.POST.get('current_status'))
        mediator.mediate(reqModifOrder)
    return redirect('orders:list')
