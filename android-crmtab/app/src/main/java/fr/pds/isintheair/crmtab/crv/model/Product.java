package fr.pds.isintheair.crmtab.crv.model;

/**
 * Created by Muthu on 30/12/2015.
 */
public class Product {


    private String id;
    private String name;

    public String getId ()
    {
        return id;
    }
    public String getName ()
    {
        return name;
    }

    public void setId (String id)
    {
        this.id = id;
    }
    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+"]";
    }
}

