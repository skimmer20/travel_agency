package service;

import abstr.AbstractCrudOperation;
import entity.User;

/**
 * @author yuriismac on 3/4/21.
 * @project travel_agency
 */
public interface UserService extends AbstractCrudOperation<User> {

    User getUserByEmail(String email);

}
