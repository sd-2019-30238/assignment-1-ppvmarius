package model;

public class Product {
    private int id;
    private String productName;
    private int price;
    private String prodDescription;
    private String category;
    private int quantity;
    private String saleType;

    public Product(){this(0,"",0,"","",0, "None");}
    public Product(int id, String productName, int price, String prodDescription, String category, int quantity, String saleType){
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.prodDescription = prodDescription;
        this.category = category;
        this.quantity = quantity;
        this.saleType = saleType;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
}
