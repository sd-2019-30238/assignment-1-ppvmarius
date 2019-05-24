from .models import Order

class Observable:
    def __init__(self):
        self.obs_list = []

    def attach(self, observer):
        if not observer in self.obs_list:
            self.obs_list.append(observer)

    def detach(self, observer):
        try:
            self.obs_list.remove(observer)
        except:
            pass

    def Notify(self, order_id):
        for observer in self.obs_list:
            observer.update(order_id)
