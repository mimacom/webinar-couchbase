package com.mimacom.polls.repository;

import com.mimacom.polls.config.TestApplicationConfig;
import com.mimacom.polls.config.listener.AbstractCouchbaseListener;
import com.mimacom.polls.repository.impl.CommonRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.support.CouchbaseRepositoryFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@TestExecutionListeners(AbstractCouchbaseListener.class)
public class CommonRepositoryTest {

    private CommonRepository commonRepository;

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @Before
    public void setup() throws Exception {
        commonRepository = new CommonRepositoryImpl(this.couchbaseTemplate);
    }

    @Test
    public void testGetNextId() throws Exception {
        String nextId = this.commonRepository.getNextId();
        assertNotNull(nextId);
        assertEquals("1", nextId);

    }
}
