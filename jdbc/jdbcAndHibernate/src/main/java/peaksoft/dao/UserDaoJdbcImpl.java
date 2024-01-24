package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    public Connection connection = Util.connection;


    public UserDaoJdbcImpl() {
        
    }

    public void createUsersTable() {
        String create = "create table if not exists users(id serial primary key,first_name varchar ,last_name varchar,age int)";
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String check = "SELECT table_name FROM information_schema.tables WHERE table_name = 'users'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(check);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String drop = "drop table users";
                try {
                    Statement statement = connection.createStatement();
                    int i = statement.executeUpdate(drop);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "insert into users(first_name,last_name,age) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String remove = "delete from users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);
            preparedStatement.setLong(1,id);
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String get = "select * from users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(get);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setAge((byte) resultSet.getInt("age"));
                user.setName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "truncate table users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(clean);
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}