package model;

public class Discount20 implements Discount {
    @Override
    public Product getCartItem(Product product) {
        return new Product(product.getId(), product.getProductName(),(int)(product.getPrice() * 0.8), product.getProdDescription(), product.getCategory(), 1, product.getSaleType());
    }
}
