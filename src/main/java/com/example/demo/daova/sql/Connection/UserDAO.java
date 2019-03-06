package com.example.demo.daova.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;

import com.example.demo.DBManager;
import com.example.demo.models.User;



public class UserDAO {
    private static UserDAO ourInstance = new UserDAO();

    public static UserDAO getInstance() {
        return ourInstance;
    }


    //add user via db manager
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, first_name, last_name, email) values (?,?,?,?,?)";

        Connection con = (Connection) DBManager.getInstance().getConnection();
        PreparedStatement ps = ((java.sql.Connection) con).prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLasstName());
        ps.setString(5, user.getEmail());
        ps.executeUpdate();

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            user.setId(generatedKeys.getLong(1));
        }
    }



    //delete user via db manager
    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";

        Connection con = (Connection) DBManager.getInstance().getConnection();
        PreparedStatement ps = ((java.sql.Connection) con).prepareStatement(sql);
        ps.setString(1, username);
        ps.executeUpdate();
    }

}
