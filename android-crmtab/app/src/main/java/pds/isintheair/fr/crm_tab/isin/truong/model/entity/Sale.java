package pds.isintheair.fr.crm_tab.isin.truong.model.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Truong on 12/14/2015.
 */
public class Sale extends BaseModel{

    @Column
    @PrimaryKey
    private int id;
    @Column
    private String customer;
    @Column
    private double salesPrice;

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }
}
