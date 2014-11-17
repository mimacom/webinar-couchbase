package com.mimacom.polls.service.impl;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.mimacom.polls.domain.Participant;
import com.mimacom.polls.repository.CommonRepository;
import com.mimacom.polls.repository.ParticipantRepository;
import com.mimacom.polls.repository.PollRepository;
import com.mimacom.polls.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final CommonRepository commonRepository;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository, CommonRepository commonRepository) {
        this.participantRepository = participantRepository;
        this.commonRepository = commonRepository;
    }

    @Override
    public void saveParticipant(String pollId, Participant participant) {

        if (participant.getId() == null){
            participant.setId(this.commonRepository.getNextId());
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
