package pds.isintheair.fr.crmtab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bide2 on 19/01/2016.
 */
public class User {
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("mdp")
    @Expose
    private String mdp;
    @SerializedName("tel")
    @Expose
    private String tel;


    public String getTel() {
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

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return fname + " " + lname + " " + tel;
    }
}
