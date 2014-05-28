package com.github.rich.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import org.joda.time.DateTime;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * Hello world!
 *
 */
/**
 * $ mongo MongoDB shell version: 2.2.3 connecting to: test
 * 
 * > show dbs testdb 0.203125GB
 * 
 * > use testdb switched to db testdb
 * 
 * > show collections system.indexes user 
 * 
 * > db.user.find() { "_id" :
 * ObjectId("51398e6e30044a944cc23e2e"), "age" : 30, "createdDate" :
 * ISODate("2013-03-08T07:08:30.168Z"), "name" : "mkyong-updated" }
 * 
 * @author ionix
 *
 */
public class Crud {
	private static final String USER_NAME = "ionix";

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);

		// display all databases
		displayDatabases(mongo);

		// select database "dbname"
		DB db = mongo.getDB("dbname");
		displayCollectionNames(db);

		// get table "User"
		DBCollection userTbl = db.getCollection("user");

		// create user "ionix"
		System.out.println("Creating user...");
		createUser(userTbl);
		System.out.println("Creating user...OK");

		// update user
		System.out.println("Updating user...");
		WriteResult r = updateUser(userTbl);
		System.out.println("Updating user...OK"+" ("+r.getN()+")");

		// update all "updateCount"
		BasicDBObject updateAllCount = 
				new BasicDBObject().append("$inc", 
				new BasicDBObject().append("updateCount", 1));
		r = userTbl.update(new BasicDBObject().append("name", "ionix-updated"), updateAllCount, true, true);
		System.out.println("Updating all count...OK"+" ("+r.getN()+")");
		
		// show users
		DBCursor cursor = userTbl.find();
		System.out.println("Show Users:");
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		System.out.println("Show Users. Done.");
	}

	private static WriteResult updateUser(DBCollection userTbl) {
		BasicDBObject query = new BasicDBObject();
		query.put("name", "ionix");

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("name", "ionix-updated");
		
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		return userTbl.update(query, updateObj, true, true);
	}

	private static void createUser(DBCollection table) {
		BasicDBObject user = new BasicDBObject();
		user.put("name", USER_NAME);
		user.put("age", 29);
		user.put("createdDate", new DateTime().toDate());
		user.put("updateCount", 0);
		table.insert(user);
	}

	private static void displayCollectionNames(DB db) {
		for (String coll : db.getCollectionNames()) {
			System.out.println(coll);
		}
	}

	private static void displayDatabases(MongoClient mongo) {
		List<String> dbs = mongo.getDatabaseNames();
		for (String db : dbs) {
			System.out.println(db);
		}
	}
}
