package miage.pds.prospect.dao;

import miage.pds.prospect.model.Prospect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
public interface ProspectDAO {

    public List<Prospect> getAllProspect();

    public Prospect getProspectByID(int id);

}
