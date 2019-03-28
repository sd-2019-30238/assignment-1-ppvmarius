package model;

public class Product {
    private int id;
    private String productName;
    private int price;
    private String prodDescription;
    private int categId;
    private int quantity;

    public Product(){this(0,"",0,"",0,0);}
    public Product(int id, String productName, int price, String prodDescription, int categId, int quantity){
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.prodDescription = prodDescription;
        this.categId = categId;
        this.quantity = quantity;
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

    public int getCategId() {
        return categId;
    }

    public void setCategId(int categId) {
        this.categId = categId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
