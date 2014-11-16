package com.mimacom.polls.util;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.QueryResult;

import static com.couchbase.client.java.query.Select.select;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public class N1Q3LIdGenerator {

    public static String getNextId(){
        // Through a system property
        System.setProperty("com.couchbase.queryEnabled", "true");

        Cluster cluster = CouchbaseCluster.create();
        Bucket bucket = cluster.openBucket("polls");
        String name = bucket.name();
        // using the DSL
        QueryResult query = bucket.query(select("max(to_num(META().id))").from(name));

        Integer id = null;
        if (query.allRows().size() > 0){
            id = query.allRows().get(0).value().getInt("$1");
        }
        if (id == null) {
            id = 1;
        } else {
            id++;
        }

        return id.toString();
    }
}
