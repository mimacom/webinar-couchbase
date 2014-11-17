package com.mimacom.polls.repository;

import com.couchbase.client.protocol.views.Query;
import com.mimacom.polls.domain.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@Repository
public interface ParticipantRepository extends CrudRepository<Participant, String>, ParticipantCustomRepository {

    List<Participant> findByPollFk(Query query);

}
