package model;

public class Order {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    public Order(int id, int idClient, int idProduct, int quantity){
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public Order(){
        this(0,0,0,0);
    }
}
