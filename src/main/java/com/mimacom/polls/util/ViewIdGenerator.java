package com.mimacom.polls.util;

import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.couchbase.client.protocol.views.ViewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public class ViewIdGenerator {

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    public String getNextId(){

        Query query = new Query();
        query.setStale(Stale.FALSE);
        ViewResponse response = couchbaseTemplate.queryView("poll", "sequence-id", query);
        if (response.iterator().hasNext()){
            return Integer.valueOf(response.iterator().next().getValue() + 1).toString();
        }
        return "1";
    }
}
