package model;

public class Discount50 implements Discount{
    @Override
    public Product getCartItem(Product product) {
        return new Product(product.getId(), product.getProductName(),product.getPrice() / 2, product.getProdDescription(), product.getCategory(), 1, product.getSaleType());
    }
}
