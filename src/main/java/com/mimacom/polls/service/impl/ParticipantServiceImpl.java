package com.mimacom.polls.service.impl;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.mimacom.polls.domain.Participant;
import com.mimacom.polls.repository.ParticipantRepository;
import com.mimacom.polls.service.ParticipantService;
import com.mimacom.polls.util.N1Q3LIdGenerator;
import com.mimacom.polls.util.ViewIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ParticipantServiceImpl extends ViewIdGenerator implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public void saveParticipant(String pollId, Participant participant) {

        if (participant.getId() == null){
            participant.setId(this.getNextId());
        }
        participant.setPollFk(pollId);
        this.participantRepository.save(participant);

    }

    @Override
    public List<Participant> findParticipants(String pollId) {
        Query query = new Query();
        query.setKey(ComplexKey.of(pollId));
        query.setStale(Stale.FALSE);
        return this.participantRepository.findByPollFk(query);
    }

    @Override
    public Integer getTotalVotes(String pollId, String option) {
        Query query = new Query();
        query.setGroupLevel(2);
        query.setKey(ComplexKey.of(pollId, option));
        query.setStale(Stale.FALSE);
        return this.participantRepository.findTotalVotes(query);
    }

    @Override
    public Participant getById(String participantId) {
        return this.participantRepository.findOne(participantId);
    }
}
