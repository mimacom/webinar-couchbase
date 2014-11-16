package com.mimacom.polls.service;

import com.mimacom.polls.domain.Poll;

/**
 * @author sago, mimacom
 * @since 0.1
 */
public interface PollService {

    String save(Poll poll);

    java.util.List<Poll> getPollsByUser(String userName);

    Poll getById(String pollId);

    void closePoll(String pollId, String selectedOption);
}
