package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;


/**
 * Face legatura cu tabela Product din baza de date.
 */
public class ProductDAO extends AbstractDAO<Product> {
    private static final String insertStatement = "INSERT INTO `product`(id, productName, price, prodDescription, category, quantity, saleType) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String updateStatement = "UPDATE `product` SET productName=?, price=?, prodDescription=?, category=?, quantity=?, saleType=? WHERE id = ?";

    /**
     * Insereaza un produs in baza de date
     * @param product
     * @return
     */
    public static int insertProduct(Product product) {
        int rezultat = 0;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertStatement);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getPrice());
            statement.setString(4, product.getProdDescription());
            statement.setString(5, product.getCategory());
            statement.setInt(6, product.getQuantity());
            statement.setString(7, product.getSaleType());
            rezultat = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProducttDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rezultat;
    }

    /**
     * Modifica datele unui produs existent in baza de date
     * @param product
     * @return
     */
    public static int updateProduct(Product product) {
        int rezultat = 0;

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStatement);
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getProdDescription());
            statement.setString(4, product.getCategory());
            statement.setInt(5, product.getQuantity());
            statement.setString(6, product.getSaleType());
            statement.setInt(7, product.getId());
            rezultat = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rezultat;
    }

    public ArrayList<Product> findBySaleType(String saleType) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "Select * from `product` where saleType = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, saleType);
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
