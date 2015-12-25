package miage.pds.api.registercall.dao;

import java.util.List;

import miage.pds.api.RestController;
import miage.pds.registercallmodel.Cra;
import miage.pds.registercallmodel.ListCra;

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

		mongo = new MongoClient(Config.URL, Config.PORT);
		morphia = new Morphia();

		/**** Get database ****/
		// if database doesn't exists, MongoDB will create it for you
		datastore = morphia.createDatastore(mongo, "CRM");

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

	// cra insertion
	public List<Cra> getAllCra() {

		Query q = datastore.find(Cra.class);
		// single entity
		// MyEntity e = q.get();
		// e = q.sort("foo").get();

		// for
		// for (Cra i : q)
		// logger.info(i.toString());
//Hotel hotel = ds.get(Hotel.class, hotelId);
		return q.asList();
	}

	public List<Cra> getListCraForUser(String iduser) {
		List<Cra> list = datastore.createQuery(Cra.class).filter("iduser", Integer.parseInt(iduser)).asList();
		return list;
	}
}
