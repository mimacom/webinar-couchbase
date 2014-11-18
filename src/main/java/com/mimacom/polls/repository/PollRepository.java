package com.mimacom.polls.repository;

import com.couchbase.client.protocol.views.Query;
import com.mimacom.polls.domain.Poll;
import org.springframework.data.couchbase.core.view.View;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@Repository
public interface PollRepository extends CrudRepository<Poll, String> {

    List<Poll> findByUserName(Query query);

}
