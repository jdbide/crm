package miage.pds.api.registercall.dao;

import java.net.UnknownHostException;
import java.util.List;

import miage.pds.api.registercall.RestController;
import miage.pds.registercall.model.Cra;
import miage.pds.registercall.model.ListCra;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DAO {

	private static final Logger logger = LoggerFactory.getLogger(DAO.class);

	DB db;
	Long count;
	Morphia morphia;
	Datastore datastore;

	public DAO() {
	}

	public boolean ConnectDB() {
		/**** Connect to MongoDB ****/
		MongoClient mongo;

		try {
			mongo = new MongoClient(Config.URL, Config.PORT);
		
		morphia = new Morphia();

		/**** Get database ****/
		// if database doesn't exists, MongoDB will create it for you
		datastore = morphia.createDatastore(mongo, "crm");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// cra insertion
	public boolean createCra(Cra cra) {
		boolean state = false;
		// check if connected to DB
		if (ConnectDB()) {
			datastore.save(cra);
			state = true;
		}
		return state;
	}

	public List<Cra> getListCraForUser(int iduser) {
		List<Cra> liste = null;
		if (ConnectDB()) {
		liste =  datastore.createQuery(Cra.class).field("iduser").equal(iduser).asList();
		}
		/*logger.info("1");
		System.out.println("1");
		datastore.createQuery(Cra.class).filter("iduser", iduser);
		logger.info("2");
		List<Cra> liste = datastore.createQuery(Cra.class).filter("iduser", iduser).asList();
		
		logger.info("size" + String.valueOf(liste.size()));*/
		//ListCra list = new ListCra(datastore.createQuery(Cra.class).filter("iduser", Integer.parseInt(iduser)).asList());
		//logger.info(arg0);
		//return list;
		//ListCra list = new ListCra(liste);
		return liste;
	}
}
