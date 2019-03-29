package model;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> shopItems;

    public ShoppingCart(){
        shopItems = new ArrayList<>();
    }

    public ShoppingCart(Product product){
        shopItems.add(product);
    }

    public void addProduct(Product product){
        shopItems.add(product);
    }

    public void removeProduct(Product product){
        Product tempProd = new Product();
        for(Product prod: shopItems){
            if(prod.getId() == product.getId()){
                tempProd = prod;
            }
        }
        if(tempProd.getId() != 0){
            shopItems.remove(tempProd);
        }
    }
}
