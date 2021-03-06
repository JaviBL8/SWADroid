package es.ugr.swad.swadroid.modules.messages;

/**
 * Class for manage a list of users
 *
 * @author Rubén Martín Hidalgo
 */

import java.util.ArrayList;
import java.util.List;
import es.ugr.swad.swadroid.model.UserFilter;

/**
 * UserFilter list
 */
public class UsersList {
    private List<UserFilter> users = new ArrayList();

    public void saveUser(UserFilter user) {
        users.add(user);
    }

    public List<UserFilter> getUsers() { return users; }
}
