package dao.impl;

import dao.UserDao;
import entity.User;

import java.util.List;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
public class UserDaoImpl implements UserDao {

    private static String CREATE = "insert into user(`first_name`,`last_name`, `user_role`, `phone_number`, `email`, `password`) values(?, ?, ?, ?, ?, ?)";
    private static String READ_BY_ID = "select * from user where id = ?";
    private static String READ_USER_BY_EMAIL = "select * from user where email = ?";
    private static String READ_ALL = "select * from user";
    private static String UPDATE_BY_ID = "update user set first_name = ?, last_name = ?, user_role = ?, phone_number = ?, email = ?, password = ? where id = ?";
    private static String DELETE_BY_ID = "delete from user where id = ?";

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User read(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
