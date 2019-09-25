import java.util.concurrent.TimeUnit;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class CouchBase_Client {

	public static void main(String[] args) {
		// Create a cluster reference
		CouchbaseCluster cluster = CouchbaseCluster.create("127.0.0.1");

	 
		CouchbaseCluster apps = cluster.authenticate("Administrator", "MyPass123");
		//Create a new Bucket from console
		// Connect to the bucket and open it
		//Bucket bucket = apps.openBucket("apps");
		
		//OPen with TimeOut
		Bucket bucket = apps.openBucket("apps",10,TimeUnit.SECONDS);

		// Create a JSON document and store it with the ID "helloworld"
		JsonObject content = JsonObject.create().put("Welcome", "Ashok");
		//Insert the document
		JsonDocument inserted = bucket.upsert(JsonDocument.create("greeting", content));

		// Read the document and print the ID as "greeting" field
		JsonDocument found = bucket.get("greeting");
		System.out.println("Couchbase data read is  " + found.content().getString("Welcome"));
		
		//add one more document
		JsonObject jsonParent = JsonObject.create().put("userName", "Vijay");
		jsonParent.put("employed",true);
		
		//create child json object
		JsonObject childJson = JsonObject.create();
		childJson.put("account", "saving");
		childJson.put("balance",1000);
		//add child json to parent
		jsonParent.put("userAccount", childJson);
		
		JsonDocument document = JsonDocument.create("appUser", jsonParent);
		
		JsonDocument result = bucket.upsert(document);
		System.out.println("Couchbase document addd "+result);

		// Read the document and print the ID as "greeting" field
		JsonDocument readDocument = bucket.get("appUser");
		System.out.println("Couchbase User read "+readDocument);
		System.out.println("Couchbase data User read is  " + readDocument.content().getString("userName"));


		// Close all buckets and disconnect
		cluster.disconnect();


	}

}
