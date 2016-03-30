package fr.pds.isintheair.crmtab.model;

import fr.pds.isintheair.crmtab.model.entity.User;

/**
 * Created by jbide on 29/03/2016.
 */
public class ClockinObject {

    private User user;
    private String tagId;

    public ClockinObject(User user, String tagId) {
        this.user = user;
        this.tagId = tagId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

}
