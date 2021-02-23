package dao;

import abstr.AbstractCrudOperation;
import entity.User;

/**
 * @author yuriismac on 2/23/21.
 * @project travel_agency
 */
public interface UserDao extends AbstractCrudOperation<User> {
    User getUserByEmail(String email);
}
