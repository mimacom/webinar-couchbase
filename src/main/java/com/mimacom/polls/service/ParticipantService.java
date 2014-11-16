package com.mimacom.polls.service;

import com.mimacom.polls.domain.Participant;

import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public interface ParticipantService {

    void saveParticipant(String pollId, Participant participant);

    List<Participant> findParticipants(String pollId);

    Integer getTotalVotes(String pollId, String option);

    Participant getById(String participantId);
}
