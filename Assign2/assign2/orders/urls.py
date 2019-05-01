from django.conf.urls import url
from . import views

app_name = 'orders'

urlpatterns = [
    url(r'^add_order', views.add_order, name="add_order"),
    url(r'^(?P<id>[0-9]+)/$', views.order_details, name="detail"),
    url(r'^$', views.list_orders, name="list"),
]
