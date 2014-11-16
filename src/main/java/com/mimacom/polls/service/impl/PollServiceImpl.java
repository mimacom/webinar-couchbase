package com.mimacom.polls.service.impl;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.mimacom.polls.domain.Poll;
import com.mimacom.polls.repository.PollRepository;
import com.mimacom.polls.service.PollService;
import com.mimacom.polls.util.N1Q3LIdGenerator;
import com.mimacom.polls.util.ViewIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author sago, mimacom
 * @since 0.1
 */
@Service
public class PollServiceImpl extends ViewIdGenerator implements PollService {

    private final PollRepository pollRepository;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }


    @Override
    public String save(Poll poll) {
        if (poll.getId() == null){
            poll.setId(this.getNextId());
            poll.setCreatedOn(new Date());
        }
        this.pollRepository.save(poll);
        return poll.getId();
    }

    @Override
    public List<Poll> getPollsByUser(String userName) {
        Query query = new Query();
        query.setKey(ComplexKey.of(userName));
        query.setStale(Stale.FALSE);
        return this.pollRepository.findByUserName(query);
    }

    @Override
    public Poll getById(String pollId) {
        return this.pollRepository.findOne(pollId);
    }

    @Override
    public void closePoll(String pollId, String selectedOption) {
        Assert.notNull(pollId);
        Poll poll = this.getById(pollId);
        poll.setClosed(true);
        poll.setSelectedOption(selectedOption);
        this.save(poll);
    }
}
