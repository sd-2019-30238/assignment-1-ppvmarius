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

    public ArrayList<Order> getOrders(){
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<Order> orders = orderDAO.getObjects();
        return orders;
    }
}
