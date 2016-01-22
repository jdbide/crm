package fr.pds.isintheair.crmtab.crv.model;

/**
 * Created by Muthu on 08/01/2016.
 */
public class Contact {

    public String name;
    public String tel;

    public Contact(String name, String tel){
        this.name = name;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }
}
