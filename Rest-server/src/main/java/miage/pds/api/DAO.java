package miage.pds.api;

import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DAO {
	DB db;
	Long count;
	public DAO(){}

	public boolean ConnectDB(){		
		/**** Connect to MongoDB ****/
		MongoClient mongo;
		try {

			mongo = new MongoClient(Config.URL, Config.PORT);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			db = mongo.getDB("CRM");
			


		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	
}