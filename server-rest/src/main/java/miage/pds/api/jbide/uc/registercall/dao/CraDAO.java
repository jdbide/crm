package miage.pds.api.jbide.uc.registercall.dao;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miage.pds.api.common.LoggingRestController;
import miage.pds.api.common.model.User;
import miage.pds.api.jbide.uc.registercall.model.Cra;

/**
 * Created by jbide on 20/12/2015.
 */

public class CraDAO extends BasicDAO<Cra, ObjectId> {

	private static final Logger logger = LoggerFactory.getLogger(CraDAO.class);

	public CraDAO(Datastore datastore) {
		super(datastore);
		
		//Mock Cra
	    Cra newCra = new Cra();
	    newCra.setCalltype("Recu");
	    newCra.setClientname("CH HENRI MONDOR");
	    newCra.setComments("Les rappeler lorsque nouveaux produits disponibles");
	    newCra.setDate("23 janv 2016");
	    newCra.setDuration((long) 1234);
	    newCra.setContactname("Franck NEROT");
	    newCra.setIdcontact((long) 1);
	    newCra.setSubject("Demande d'informations sur scanners");
	    newCra.setIduser(LoggingRestController.idusertest);
	    newCra.setIdcra(getUniqueIdCra());
	    createCra(newCra);
	    
	    newCra = new Cra();
	    newCra.setCalltype("Emis");
	    newCra.setClientname("CS Daniel Renoult et Montreuil");
	    newCra.setComments("Nouveaux types de compresses");
	    newCra.setDate("23 janv 2016");
	    newCra.setDuration((long) 1234);
	    newCra.setContactname("Luc BERNARD");
	    newCra.setIdcontact((long) 2);
	    newCra.setSubject("Demande d'informations sur compresses");
	    newCra.setIduser(LoggingRestController.idusertest);
	    newCra.setIdcra(getUniqueIdCra());
	    createCra(newCra);
	    
	    logger.info("ADDED 2 Cra");
			
	}

	// cra insertion
	public boolean createCra(Cra cra) {
		boolean state = false;
		// check if connected to DB
		cra.setIdcra(getUniqueIdCra());
		getDatastore().save(cra);
		return true;
	}
	
	public boolean deleteCra(String idcra) {
		getDatastore().delete(getDatastore().createQuery(Cra.class).field("idcra").equal(idcra));
		return true;
	}

	public List<Cra> getListCraForUser(String iduser) {
		return  getDatastore().createQuery(Cra.class).disableValidation().field("iduser").equal(iduser).asList();
	}

	public User logUser(User user) {
		User u = new User();
		u =   getDatastore().createQuery(User.class)
				.field("email").equal(user.getEmail())
				.field("password").equal(user.getPassword()).get();
			//logger.info("result" + u.getEmail());
			
		if(u!=null)
		return u;
		else return null;
	}

	public boolean addUser(User u) {
		boolean state = false;
		// check if connected to DB
			getDatastore().save(u);
		logger.info("ADDED USER : " + u.getLname());
		return true;		
	}
	
	public String getUniqueUid(){
	
		User u = new User();
		boolean unique = false;
		String uid = "" ;
		do{
			uid = UUID.randomUUID().toString();
			u = getDatastore().createQuery(User.class).field("id").equal(uid).get();			
			if(u==null) unique = true;
		}while(unique=false);
		return uid;	
	}
	
	
	public String getUniqueIdCra(){		
		Cra u = new Cra();
		boolean unique = false;
		String id = "" ;
		do{
			id = UUID.randomUUID().toString().substring(0, 10);
			u = getDatastore().createQuery(Cra.class).field("idcra").equal(id).get();			
			if(u==null) unique = true;
		}while(unique=false);
		return id;	
	}
	
	
	
	public void dropTables(){

		getDatastore().getCollection(User.class).drop();
		getDatastore().getCollection(Cra.class).drop();
		
		logger.info("DROPPED TABLE User");
		logger.info("DROPPED TABLE Cra");
	}


}
