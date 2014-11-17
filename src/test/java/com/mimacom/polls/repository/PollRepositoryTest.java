/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 *  Project: Service Pricing Calculator
 *
 *  Copyright © $year Baxter Healthcare Corporation. All rights reserved.
 */

package com.mimacom.polls.repository;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.mimacom.polls.config.TestApplicationConfig;
import com.mimacom.polls.config.listener.PollRepositoryListener;
import com.mimacom.polls.domain.Poll;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@TestExecutionListeners(PollRepositoryListener.class)
public class PollRepositoryTest {

    private PollRepository pollRepository;

    @Autowired
    private CouchbaseOperations couchbaseTemplate;

    @Before
    public void setup() throws Exception {
        this.pollRepository = new CouchbaseRepositoryFactory(couchbaseTemplate).getRepository(PollRepository.class);
        this.pollRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        this.pollRepository.deleteAll();
    }

    @Test
    public void testGetPollsByUserName() throws Exception {
        Poll poll1 = savePoll("12", "Poll 1", "test@mimacom.com", "sgomez");
        Poll poll2 = savePoll("43", "Poll 2", "test@mimacom.com", "sgomez");

        Query query = new Query();
        query.setKey(ComplexKey.of("sgomez"));
        query.setStale(Stale.FALSE);
        List<Poll> polls = this.pollRepository.findByUserName(query);
        assertNotNull(polls);
        assertEquals(2, polls.size());

        this.assertPoll(poll1, polls.get(0));
        this.assertPoll(poll2, polls.get(1));

    }

    @Test
    public void testSavePoll() {

        Poll savedPoll = this.savePoll("3", "Test poll", "test@mimacom.com", "sgomez");
        assertNotNull(savedPoll);
    }

    @Test
    public void testFindById() throws Exception {
        Poll poll1 = savePoll("32", "Poll 1", "test@mimacom.com", "sgomez");

        Poll savedPoll = this.pollRepository.findOne("32");
        this.assertPoll(poll1, savedPoll);


    }

    private Poll savePoll(String id, String title, String email, String userName) {
        Poll poll = new Poll();
        poll.setTitle(title);
        poll.setEmailAddress(email);
        poll.setUserName(userName);
        poll.setId(id);
        return this.pollRepository.save(poll);
    }

    private void assertPoll(Poll original, Poll saved){
        assertEquals(original.getId(), saved.getId());
        assertEquals(original.getEmailAddress(), saved.getEmailAddress());
        assertEquals(original.getTitle(), saved.getTitle());
        assertEquals(original.getUserName(), saved.getUserName());
    }
}
