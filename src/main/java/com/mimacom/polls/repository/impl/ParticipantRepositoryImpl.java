package com.mimacom.polls.repository.impl;

import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewResponse;
import com.couchbase.client.protocol.views.ViewRow;
import com.mimacom.polls.repository.ParticipantCustomRepository;
import com.mimacom.polls.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@Repository
public class ParticipantRepositoryImpl implements ParticipantCustomRepository{

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @Override
    public Integer findTotalVotes(Query query) {
        ViewResponse response = this.couchbaseTemplate.queryView("participant", "total_votes", query);
        if (response.iterator().hasNext()){
            return Integer.valueOf(response.iterator().next().getValue());
        }
        return 0;
    }
}
