package miage.pds.prospect.dao;

import miage.pds.prospect.model.Sales;

import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
public interface SalesDAO {

    public List<Sales> getAllSales();

    public long getCountAllSales();

    public List<Sales> getSalesSuperiorThanAverage(double average);
}
