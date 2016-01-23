package fr.pds.isintheair.crmtab.common.model;

/**
 * Created by jbide on 19/01/2016.
 */

public class User {

    private String id;

    private String fname;

    private String lname;

    private String tel;

    private String email;

    private String password;


    public String getTel() {
        return tel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
