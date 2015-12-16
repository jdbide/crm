package fr.datour.protoclientrest.model.bus.events;

import fr.datour.protoclientrest.model.entities.User;

public class CreateUserEvent {
    private User user;

    public CreateUserEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
