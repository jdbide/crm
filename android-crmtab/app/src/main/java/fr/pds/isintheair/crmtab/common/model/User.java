package fr.pds.isintheair.crmtab.common.model;

/**
 * Created by bide2 on 19/01/2016.
 */
public class User {

    private String fname;

    private String lname;

    private String mdp;

    private int tel;

    private String email;


    public int getTel() {
        return tel;
    }

    public String getMdp() {
        return mdp;
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

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
