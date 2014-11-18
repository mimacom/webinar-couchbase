package com.mimacom.polls.repository;

import com.couchbase.client.protocol.views.Query;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public interface ParticipantCustomRepository {

    Integer findTotalVotes(Query query);
}
