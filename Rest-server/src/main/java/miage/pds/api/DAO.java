package miage.pds.api;

import java.net.UnknownHostException;

import miage.pds.model.Product;
import miage.pds.model.Reporting;
import miage.pds.model.Report;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.DBPortPool.NoMoreConnection;

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
			DBCollection table = db.getCollection("crv");
			count = table.count();


		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	//insert a crv into database
	public boolean createCrv(Reporting crv){


		Report list = crv.getReport();

		Product[] product = list.getProduct();

		System.out.println(list.getCommercial());
		if(ConnectDB()){
			DBCollection tableCrv = db.getCollection("crv");
			DBCollection tableProductContainer = db.getCollection("product_container");
			BasicDBObject docCrv = new BasicDBObject();
			BasicDBObject docProductContainer = new BasicDBObject();

			final long idCrv = count+1;



			docCrv.put("_id", list.getId());	
			docCrv.put("date", list.getDate());
			docCrv.put("satisfaction", list.getSatisfaction());
			docCrv.put("comment", list.getComment());
			docCrv.put("idCommercial", list.getCommercial());
			docCrv.put("idContact", list.getContact());
			docCrv.put("idClient", list.getClient());
			docCrv.put("idVisit", list.getVisit());
			tableCrv.insert(docCrv);

			for(int i=0; i< product.length; i++){
				docProductContainer.put("idReport", idCrv);
				docProductContainer.put("idProduct", product[i].getId());
			}
			tableProductContainer.insert(docProductContainer);

			return true;

		}

		return false;

	}
	
	

	public String getRandomInfo(int id){
		String subject = "";
		String userName = "";
		String userId = "";
		String contactName = "";
		String contactTel = "";
		String contactId = "";
		String clientId = "";
		String visitId = "";
		String clientName = "";
		String clientAddress = "";

		if(ConnectDB()){
			DBCollection tableVisit = db.getCollection("visit");
			DBCollection tableUser = db.getCollection("user");
			DBCollection tableContact = db.getCollection("contact");
			DBCollection tableClient = db.getCollection("client");

			BasicDBObject query = new BasicDBObject();
			query.put("_id", id);

			BasicDBObject queryUser = new BasicDBObject();
			queryUser.put("idUser", id);

			BasicDBObject queryContact = new BasicDBObject();
			queryUser.put("idClient", id);

			DBCursor cursor = tableVisit.find(query);
			DBCursor cursor2 = tableUser.find(queryUser);
			DBCursor cursor3 = tableContact.find(queryContact);

			while (cursor.hasNext()) {
				subject =  cursor.next().get("subject").toString();
				//visitId =  cursor.next().get("_id").toString();	    
			}
			while (cursor2.hasNext()) {
				userName =  cursor2.next().get("nom").toString();
				userId = cursor2.next().get("_id").toString();

			}
			while (cursor3.hasNext()) {
				//contactName =  cursor.next().get("nom").toString();
				contactTel = cursor3.next().get("tel").toString();
				//contactId = cursor3.next().get("_id").toString();
				clientId = cursor3.next().get("idClient").toString();
			}

			BasicDBObject queryClient = new BasicDBObject();
			queryUser.put("_id", clientId);
			DBCursor cursor4 = tableClient.find(queryClient);
			while (cursor4.hasNext()) {
				clientName =  cursor4.next().get("nom").toString();
				//clientAddress = cursor4.next().get("address").toString();


			}

			JSONObject response = new JSONObject();
			JSONObject mock = new JSONObject();

			try {
				response.put("user", userName);
				response.put("contact", contactName);
				response.put("tel", contactTel);
				response.put("subject", subject);
				response.put("client",clientName );
				response.put("address",clientAddress );
				response.put("userId",userId );
				response.put("contactId",contactId );
				response.put("clientId",clientId );
				response.put("visitId",visitId );

				mock.put("mock", response);
				return mock.toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "false";
	}
}
