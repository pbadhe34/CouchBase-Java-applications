import java.util.concurrent.TimeUnit;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;


public class CouchBase_QueryClient {

	public static void main(String[] args) {
		// Create a cluster reference
		CouchbaseCluster cluster = CouchbaseCluster.create("127.0.0.1");
	 
		CouchbaseCluster apps = cluster.authenticate("Administrator", "MyPass123");
		//Create a new Bucket from console
		// Connect to the bucket and open it
		//Bucket bucket = apps.openBucket("apps");
		
		//OPen with TimeOut
		Bucket bucket = apps.openBucket("apps",10,TimeUnit.SECONDS);

		N1qlQueryResult result = bucket.query(N1qlQuery.simple("SELECT DISTINCT(account) FROM `apps` WHERE account = 'saving' LIMIT 10"));

		
		System.out.println("Couchbase Query Reading");
		for (N1qlQueryRow row : result) {
		    System.out.println(row.value());
		}		 
		
		 

		// Close all buckets and disconnect
		cluster.disconnect();


	}

}
