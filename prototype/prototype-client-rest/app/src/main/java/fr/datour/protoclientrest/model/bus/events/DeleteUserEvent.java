package fr.datour.protoclientrest.model.bus.events;

import fr.datour.protoclientrest.model.entities.User;

public class DeleteUserEvent {
    private User user;

    public DeleteUserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
