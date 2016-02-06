package miage.pds.api.ctruong.uc.prospect.suggest.dao;

import miage.pds.api.ctruong.uc.prospect.suggest.model.Prospect;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import java.util.List;

/**
 * Created by Truong on 2/6/2016.
 */
public interface ProspectDAO extends DAO<Prospect, ObjectId>{

    /**
     * get a Prospect using it id
     * @param id
     * @return
     */
    public Prospect getProspectById(long id);

    /**
     * get a list all prospect
     * @return
     */
    public List<Prospect> getListProspect();

}
