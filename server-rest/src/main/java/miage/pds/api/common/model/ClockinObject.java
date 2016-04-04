package miage.pds.api.common.model;

import java.util.Date;

public class ClockinObject {

    private User user;
    private String tagId;
    private Date date;
    
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
    
    public Date getDate() {
        return date;
    }


}
