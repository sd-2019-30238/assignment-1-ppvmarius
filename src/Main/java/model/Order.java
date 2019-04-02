package model;

public class Order {
    private int id;
    private String status;

    public Order(int id, String status){        //String products - pur si simplu le trec acolo.. produse, pret si cantitate!
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
