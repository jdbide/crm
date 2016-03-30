package miage.pds.api.jbide.uc.notifypresence.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miage.pds.api.common.model.ClockinObject;
import miage.pds.api.jbide.uc.notifypresence.model.Tag;
import miage.pds.api.jbide.uc.registercall.dao.CraDAO;
import miage.pds.api.jbide.uc.registercall.model.Cra;

/**
 * Created by jbide on 30/03/2016.
 */

public class TagDAO  extends BasicDAO<Cra, ObjectId> {
	
	private static final Logger logger = LoggerFactory.getLogger(CraDAO.class);

	public TagDAO(Datastore datastore) {
		super(datastore);					
	}

	// Tag insertion
	public boolean addTag(Tag tag) {
		getDatastore().save(tag);
		return true;
	}
	
	//
	public boolean updateLocation(ClockinObject object) {
		  getDatastore().createQuery(Tag.class).disableValidation().field("id").equal(tag.getId()).get();
		  return true;
	}
	
	public boolean deleteTag(String idTag) {
		getDatastore().delete(getDatastore().createQuery(Tag.class).field("id").equal(idTag));
		return true;
	}


}
