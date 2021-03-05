package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.UserService;

import java.util.List;

/**
 * @author yuriismac on 3/5/21.
 * @project travel_agency
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private static UserService userServiceImpl;

    //singleton
    public static UserService userService(){
        if (userServiceImpl == null){
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }

    private UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    @Override
    public User create(User user) {
        return this.userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        return this.userDao.read(id);
    }

    @Override
    public User update(User user) {
        return this.userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        this.userDao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return this.userDao.getAll();
    }
}
