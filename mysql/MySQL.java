package dev.rtt.dazcomc.skypvp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL
{
    private String host = "bj4waxotyt4bt82sqcuc-mysql.services.clever-cloud.com";
    private String port = "3306";
    private String database = "bj4waxotyt4bt82sqcuc";
    private String username = "uwucpf75dw2a5wtz";
    private String password = "CIeoRW8U2uHd8nuUZjE0";

    private Connection connection;

    public boolean isConnected(){
        return (connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?useSSL=false",
                    username, password);

        }
    }
        public void disconnect(){
            if(isConnected()){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    public Connection getConnection(){
        return connection;
    }
}
