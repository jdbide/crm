package miage.pds.prospect.dao;

import miage.pds.prospect.model.Prospect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Truong on 12/20/2015.
 */
@Repository
public class ProspectDAO {

    static final Logger logger = LoggerFactory.getLogger(ProspectDAO.class);

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Select * from Client table
     */
    public void findAllProspect(){
        List<Prospect> prospects = mongoTemplate.findAll(Prospect.class);
        logger.info("Prospect total: {} ", prospects.size());
    }

    /**
     * Create a {@link Prospect} collection if the collection does not already exists
     */
    public void createCollection(){
        if (!mongoTemplate.collectionExists(Prospect.class)) {
            mongoTemplate.createCollection(Prospect.class);
        }
    }

    /**
     * Drops the {@link Prospect} collection if the collection does already exists
     */
    public void dropCollection() {
        if (mongoTemplate.collectionExists(Prospect.class)) {
            mongoTemplate.dropCollection(Prospect.class);
        }
    }

    public void insertProspectWithRandomPlace() {
        //get random age between 1 and 100
        int place = (int) Math.random();

        Prospect p = new Prospect("John", place);

        mongoTemplate.insert(p);
    }

}
