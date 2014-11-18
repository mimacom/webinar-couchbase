package com.mimacom.polls.repository.impl;

import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.couchbase.client.protocol.views.ViewResponse;
import com.mimacom.polls.repository.CommonRepository;
import com.mimacom.polls.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@Repository
public class CommonRepositoryImpl implements CommonRepository {


    private final CouchbaseTemplate couchbaseTemplate;

    @Autowired
    public CommonRepositoryImpl(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    @Override
    public String getNextId() {

        Query query = new Query();
        query.setStale(Stale.FALSE);
        ViewResponse response = this.couchbaseTemplate.queryView(Constants.DESIGN_DOC_COMMON.toString(), Constants.VIEW_SEQUENCE_ID.toString(), query);
        if (response.iterator().hasNext()){
            return response.iterator().next().getValue();
        }
        return "1";
    }
}
