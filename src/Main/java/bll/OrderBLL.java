package bll;

import dao.OrderDAO;
import model.Order;
import java.util.ArrayList;

/**
 * Nivelul bussines - logic. Aceasta clasa foloseste clasele de nivel inferior (DAO - cele pentru legatura cu baza de date).
 */
public class OrderBLL {
    //    private static int id = 1;
    private ProductBLL productBLL = new ProductBLL();
    private Order order = new Order();

    public Order findById(int id) {
        OrderDAO orderDAO = new OrderDAO();
        order = orderDAO.findById(id);

        if (order != null)
            return order;
        else
            return null;
    }


    public int insertOrder(int id, int clientId, String status, String description) {
        while (findById(id) != null) {
            id++;
        }
        order = new Order(id, clientId, status, description);

        if (OrderDAO.insertOrder(order) == 0) {
            return id;
        } else {
            return id;
        }
    }

    public void updateOrder(Order order){
        if(OrderDAO.updateOrder(order) == 0){
            System.out.println("The client does not exist!");
        }
    }

    public ArrayList<Order> getClientOrders(int clientId){
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> orders = orderDAO.findByClientId(clientId);
        return orders;
    }


    public ArrayList<Order> getOrders(){
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> orders = orderDAO.getObjects();
        return orders;
    }
}
