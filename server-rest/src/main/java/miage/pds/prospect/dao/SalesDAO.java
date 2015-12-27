package miage.pds.prospect.dao;

import miage.pds.prospect.model.Sales;

import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
public interface SalesDAO {

    /**
     *
     * @param idClient
     * @return
     */
    public List<Sales> getSalesByIdProspect(int idClient);

    /**
     * 
     * @param value
     * @return
     */
    public Sales getSalesBySalesValue(double value);


    public List<Sales> getAllSales();
}
