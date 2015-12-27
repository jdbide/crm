package miage.pds.prospect.dao;

import miage.pds.prospect.model.Prospect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
public interface ProspectDAO {

    /**
     * Function to call a prospect with it id
     * @param id
     * @return
     */
    public Prospect getProspectByIDAndName(int id, String name);

    /**
     * Function to call a list of prospect
     * @return
     */
    public List<Prospect> getAllProspect();



}
