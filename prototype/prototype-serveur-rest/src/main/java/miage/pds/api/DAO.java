package miage.pds.api;

import java.awt.List;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DAO {
	DB db;
	public DAO(){}
	
public boolean ConnectDB(){		
		
		/**** Connect to MongoDB ****/
		MongoClient mongo;
		try {
			
			mongo = new MongoClient(Config.URL, Config.PORT);
			
		/**** Get database ****/
		// if database doesn't exists, MongoDB will create it for you
			db = mongo.getDB("CRM");
			/*DBCollection table = db.getCollection("users");
			BasicDBObject doc1 = new BasicDBObject("id", 1)
	        .append("nom", "BALABASCARIN")
	        .append("prenom", "Muthu");
			
			BasicDBObject doc2 = new BasicDBObject("id", 2)
	        .append("nom", "BIDE")
	        .append("prenom", "Jean Daniel");
			
			BasicDBObject doc3 = new BasicDBObject("id", 3)
	        .append("nom", "DATOUR")
	        .append("prenom", "Julien");
			
			BasicDBObject doc4 = new BasicDBObject("id", 4)
	        .append("nom", "LACOUQUE")
	        .append("prenom", "Titouan");
	        
			table.insert(doc1);
			table.insert(doc2);
			table.insert(doc3);
			table.insert(doc4);*/
			
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}

public boolean createUser(int id, String nom, String prenom){
	
	try {
		ConnectDB();
		
		/**** Get collection / table from 'CRM' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("users");
		
		BasicDBObject doc1 = new BasicDBObject();
		doc1.put("id", id);
		doc1.put("nom", nom);
		doc1.put("prenom", prenom);
		table.insert(doc1);
		
		
		} catch (MongoException ex) {
			ex.printStackTrace();
			return false;
		    }
	
	return true;
}

public JSONArray getUsers(){
	String jsonUsersObject = "";
	JSONArray jsonarray = new JSONArray();

	try {
		ConnectDB();
		
		/**** Get collection / table from 'CRM' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("users");
		
		

		DBCursor cursor = table.find();

		while (cursor.hasNext()) {
			
			jsonarray.put(cursor.next());
			
			
			
			return jsonarray;
			
		}
		
		/**** Done ****/
		System.out.println("Done");
		
		
		} catch (MongoException ex) {
			ex.printStackTrace();
			
		    }

	return null;
	
}

public String getUserById(int id){
	String jsonUserObject = null;
	
	try {
		ConnectDB();
		
		/**** Get collection / table from 'CRM' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("users");
		
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		DBCursor cursor = table.find(query);
		// Print out "highest" priority items
		while (cursor.hasNext()) {
			
			return cursor.next().toString();
		}
		
		/**** Done ****/
		System.out.println("Done");
		
		
		} catch (MongoException ex) {
			ex.printStackTrace();
			
		    }
	
	return "";
}

public boolean deleteUserById(int id){
	
	try {
		ConnectDB();
		
		/**** Get collection / table from 'CRM' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("users");
		
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("id", id);
		DBCursor cursor = table.find(deleteQuery);
		while (cursor.hasNext()) {
		    DBObject item = cursor.next();
		    table.remove(item);
		}
		
		
		} catch (MongoException ex) {
			ex.printStackTrace();
			return false;
		    }
	
	return true;
}

public boolean updateUser(int id, String nom, String prenom){
	
	try {
		ConnectDB();
		
		/**** Get collection / table from 'CRM' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("users");
		
		/**** Find and update ****/
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);

		BasicDBObject newDocument = new BasicDBObject();
		if(!nom.equals("")){
			newDocument.put("nom", nom);
		}
		if(!prenom.equals("")){
			newDocument.put("prenom", prenom);
		}
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		
		table.update(searchQuery, updateObj);
		
		/**** Done ****/
		System.out.println("Done");
		
		
		} catch (MongoException ex) {
			ex.printStackTrace();
			return false;
		    }
	
	return true;
}




}
