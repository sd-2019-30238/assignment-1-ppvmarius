package model;

public class Discount1P1 implements Discount{
    @Override
    public Product getCartItem(Product product) {
        return new Product(product.getId(), product.getProductName(),product.getPrice(), product.getProdDescription(), product.getCategory(), 2, product.getSaleType());
    }
}
