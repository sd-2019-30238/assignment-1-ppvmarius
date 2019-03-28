package dao;

import connection.ConnectionFactory;
import model.Admin;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class AdminDAO {
    private static final String findByUserAndPassQuery = "SELECT * FROM `admin` WHERE username = ? and password = ?";

    public Admin findByUsernameAndPassword(String username, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findByUserAndPassQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            return createAdmins(resultSet).get(0);
        } catch (SQLException e) {

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public ArrayList<Admin> createAdmins(ResultSet resultSet){
        ArrayList<Admin> admins = new ArrayList<Admin>();
        try {
            while (resultSet.next()) {
                Admin instance = new Admin();

                for (Field field : Admin.class.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), Admin.class);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                admins.add(instance);
            }
        }catch (SQLException | IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        }
        if(admins.size() == 0)
            admins.add(null);
        return admins;

    }
}
