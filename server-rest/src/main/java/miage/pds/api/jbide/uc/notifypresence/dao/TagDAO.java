package miage.pds.api.jbide.uc.notifypresence.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miage.pds.api.jbide.uc.notifypresence.model.Tag;
import miage.pds.api.jbide.uc.registercall.dao.CraDAO;
import miage.pds.api.jbide.uc.registercall.model.Cra;

public class TagDAO  extends BasicDAO<Cra, ObjectId> {
	
	private static final Logger logger = LoggerFactory.getLogger(CraDAO.class);

	public TagDAO(Datastore datastore) {
		super(datastore);					
	}

	// Tag insertion
	public boolean addTag(Tag tag) {
		boolean state = false;
		// check if connected to DB
		//cra.setIdcra(getUniqueIdCra());
		//getDatastore().save(cra);
		return true;
	}
	
	public boolean deleteTag(String idTag) {
		//getDatastore().delete(getDatastore().createQuery(Cra.class).field("idcra").equal(idcra));
		return true;
	}


}
