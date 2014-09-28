package com.mimacom.polls.service.impl;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.mimacom.polls.domain.Poll;
import com.mimacom.polls.repository.PollRepository;
import com.mimacom.polls.service.PollService;
import com.mimacom.polls.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }


    @Override
    public String save(Poll poll) {
        if (poll.getId() == null){
            poll.setId(IdGenerator.getNextId());
        }
        this.pollRepository.save(poll);
        return poll.getId();
    }

    @Override
    public Poll getPollByUser(String userName) {
        Query query = new Query();
        query.setKey(ComplexKey.of(userName));
        return this.pollRepository.findByUserName(query);
    }
}
