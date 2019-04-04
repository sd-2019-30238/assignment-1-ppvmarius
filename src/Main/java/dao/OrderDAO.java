package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Order> {
    private static final String insertStatement = "INSERT INTO `order`(id, clientId, status, description) VALUES (?, ?, ?, ?)";

    public static int insertOrder(Order order) {
        int rezultat = 0;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertStatement);
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getClientId());
            statement.setString(3, order.getStatus());
            statement.setString(4, order.getDescription());
            rezultat = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rezultat;
    }


}
