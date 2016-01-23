package miage.pds.api.registercall.dao;

import java.net.UnknownHostException;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import miage.pds.api.common.model.User;
import miage.pds.registercallmodel.Cra;

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
		return liste;
	}

	public User logUser(User user) {
		User u = new User();
		Query<User> q;

		if (ConnectDB()) {
			u =   datastore.createQuery(User.class)
					.field("email").equal(user.getEmail())
					.field("password").equal(user.getPassword()).get();
			logger.info("result" + u.getEmail());
			}
		if(u.getId()!=null)
		return u;
		else return null;
	}

	public boolean addUser(User u) {
		boolean state = false;
		// check if connected to DB
		if (ConnectDB()) {
			datastore.save(u);
			state = true;
		}
		return state;		
	}
}
