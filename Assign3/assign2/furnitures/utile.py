from .models import Furniture

class FurnitureQueryService:
    def getFurniture(self, slug):
        return Furniture.objects.get(slug=slug)

    def getFurnitures(self, ordered_by="name"):
        return Furniture.objects.all().order_by(ordered_by)

class FurnitureCommandService:
    def saveFurniture(self, furnitureInstance):
            furnitureInstance.save()
