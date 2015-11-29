package fr.datour.protoclientrest.model.orm.dao;

import java.util.ArrayList;

import fr.datour.protoclientrest.model.entities.User;

public class UserDAO {
    public void createUser(User user) {
        user.save();
    }

    public void deleteUser(User user) {
        user.delete();
    }

    public void updateUsers(ArrayList<User> users) {
        Integer usersSize = users.size();

        for (int i = 0; i < usersSize; ++i) {
            users.get(i).save();
        }
    }
}
