package dao;

import connection.ConnectionFactory;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Clasa care face legatura cu tabela Client din baza de date
 */
public class ClientDAO extends AbstractDAO<Client> {
    private static final String insertStatement = "INSERT INTO `client`(id, numeClient, address, phone, age, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String updateStatement = "UPDATE `client` SET numeClient = ?, address = ?, phone = ?, age = ?, username = ?, password = ? WHERE id = ?";

    /**
     * Introduce un client nou in baza de date
     * @param client
     * @return
     */
    public static int insertClient(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rezultat = 0;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertStatement);
            statement.setInt(1, client.getId());
            statement.setString(2, client.getPersonName());
            statement.setString(3, client.getAddress());
            statement.setString(4, client.getPhone());
            statement.setInt(5, client.getAge());
            statement.setString(6, client.getUsername());
            statement.setString(7, client.getPassword());
            rezultat = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rezultat;
    }

    /**
     * Updateaza datele unui client existent in baza de date
     * @param client
     * @return
     */
    public static int updateClient(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rezultat = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStatement);
            statement.setString(1, client.getPersonName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());
            statement.setInt(4, client.getAge());
            statement.setInt(5, client.getId());
            statement.setString(6, client.getUsername());
            statement.setString(7, client.getPassword());
            rezultat = statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rezultat;
    }
}
