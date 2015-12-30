package pds.isintheair.fr.crm_tab.crv.model;

/**
 * Created by Muthu on 30/12/2015.
 */
public class Product {


    private String id;
    private String name;
    private String idReport;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+"]";
    }
}

