package fr.datour.protoclientrest.model.orm;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import fr.datour.protoclientrest.model.bus.events.CreateUserEvent;
import fr.datour.protoclientrest.model.bus.events.DeleteUserEvent;
import fr.datour.protoclientrest.model.entities.User;
import fr.datour.protoclientrest.model.orm.dao.UserDAO;

public class DAL {
    @Subscribe
    public static void handleCreateUserEvent(CreateUserEvent createUserEvent) {
        UserDAO userDAO = new UserDAO();

        userDAO.createUser(createUserEvent.getUser());
    }

    @Subscribe
    public static void handleDeleteUserEvent(DeleteUserEvent deleteUserEvent) {
        UserDAO userDAO = new UserDAO();

        userDAO.deleteUser(deleteUserEvent.getUser());
    }

    @Subscribe
    public static void handleUpdateUsersResponse(ArrayList<User> users) {
        UserDAO userDAO = new UserDAO();

        userDAO.updateUsers(users);
    }
}
