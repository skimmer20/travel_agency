package dao.impl;

import connect.ConnectionManager;
import dao.UserDao;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
public class UserDaoImpl implements UserDao {

    private static final String CREATE = "insert into user(`first_name`,`last_name`, `user_role`, `phone_number`, `email`, `password`) values(?, ?, ?, ?, ?, ?)";
    private static final String READ_BY_ID = "select * from user where id = ?";
    private static final String READ_USER_BY_EMAIL = "select * from user where email = ?";
    private static final String READ_ALL = "select * from user";
    private static final String UPDATE_BY_ID = "update user set first_name = ?, last_name = ?, user_role = ?, phone_number = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_BY_ID = "delete from user where id = ?";

    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                }
                ConnectionManager.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User read(Long id) {
        User user = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("user_role");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new User(userId, firstName, lastName, role, phoneNumber, email, password);
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(UPDATE_BY_ID)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_ALL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long userId = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String role = resultSet.getString("user_role");
                    String phoneNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    userList.add(new User(userId, firstName, lastName, role, phoneNumber, email, password));
                }
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(READ_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String role = resultSet.getString("user_role");
                String phoneNumber = resultSet.getString("phone_number");
                String userEmail = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new User(userId, firstName, lastName, role, phoneNumber, userEmail, password);
            }
            ConnectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
