package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Sales;

import java.util.List;

/**
 * This is a class dao which declare all method communicate with the sales collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public interface SalesDAO {

    public List<Sales> getAllSales();

    public long getCountAllSales();

    public List<Sales> getSalesByIDClient(int idClient);
}
