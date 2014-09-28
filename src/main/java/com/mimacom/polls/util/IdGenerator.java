package com.mimacom.polls.util;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.QueryResult;
import org.springframework.util.StringUtils;

import static com.couchbase.client.java.query.Select.select;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public class IdGenerator {

    public static String getNextId(){

        // Through a system property
        System.setProperty("com.couchbase.queryEnabled", "true");

        Cluster cluster = CouchbaseCluster.create();
        Bucket bucket = cluster.openBucket("polls");
        String name = bucket.name();

        // using the DSL
        QueryResult query = bucket.query(select("max(META().id)").from(name));

        String id = query.allRows().get(0).value().getString("$1");
        if (id == null) {
            id = "1";
        }
        Long nextId = Long.valueOf(id) + 1;
        return nextId.toString();
    }
}
