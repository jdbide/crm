package miage.pds.api.crv.dao;

import java.net.UnknownHostException;

import miage.pds.api.crv.model.Product;
import miage.pds.api.crv.model.Report;
import miage.pds.api.crv.model.Reporting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.DBPortPool.NoMoreConnection;

public class CrvMorphiaDao {
	DB db;
	Long count;
	Morphia morphia;
	Datastore datastore;
	
	public CrvMorphiaDao(){}

	public boolean ConnectDB(){		
		/**** Connect to MongoDB ****/
		MongoClient mongo;
		
		try {

			mongo = new MongoClient(Config.URL, Config.PORT);
			morphia = new Morphia();
			
			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			datastore = morphia.createDatastore(mongo, "CRM");
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	//insert a crv into database
	public boolean createCrv(Report report){
			
		//check if connected to DB
		if(ConnectDB()){
			//save report to DB
			datastore.save(report);
			return true;
		}
		return false;
			
	}
}
