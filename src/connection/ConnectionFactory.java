package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/assign1?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "assign1";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private Connection createConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        }catch (SQLException e){
            LOGGER.log(Level.WARNING, "Cannot connect to database!");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Returneaza conexiunea catre baza de date. Se foloseste design-ul de tip SingleTone.
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * Inchide conexiunea
     */
    public static void close(Connection connection){
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.log(Level.WARNING, "Cannot close the connection!");
                e.printStackTrace();
            }
        }
    }

    /**
     * Inchide obiectul de tip Statement
     */
    public static void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            }catch (SQLException e){
                LOGGER.log(Level.WARNING, "Cannot close the statement");
            }
        }
    }

    /**
     * Inchide obiectul de tip ResultSet
     */
    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                LOGGER.log(Level.WARNING, "Cannot close the resultSet");
            }
        }
    }
}
