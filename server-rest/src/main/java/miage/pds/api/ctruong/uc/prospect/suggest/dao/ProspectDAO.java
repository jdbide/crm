package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;

import java.util.List;

/**
 * This is an interface dao which declare all method communicate with the client collection in the database crm.
 *
 * Created by Truong on 12/20/2015.
 * @version 1.1.19
 * @serial 111912202015
 */
public interface ProspectDAO {

    public List<Prospect> getAllProspect();

    public Prospect getProspectByID(int id);

}
