package dao;

import connection.ConnectionFactory;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Order> {
    private static final String insertStatement = "INSERT INTO `order`(id, clientId, status, description) VALUES (?, ?, ?, ?)";
    private static final String updateStatement = "UPDATE `order` SET clientId = ?, status = ?, description =? WHERE id = ?";

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

    public static int updateOrder(Order order) {
        int rezultat = 0;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStatement);
            statement.setInt(1, order.getClientId());
            statement.setString(2, order.getStatus());
            statement.setString(3, order.getDescription());
            statement.setInt(4, order.getId());
            rezultat = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rezultat;
    }

    private static final String findByClientId = "SELECT * FROM `order` WHERE clientId = ?";

    public ArrayList<Order> findByClientId(int clientId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findByClientId);
            statement.setInt(1, clientId);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
