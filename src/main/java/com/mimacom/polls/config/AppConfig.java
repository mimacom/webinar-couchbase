package com.mimacom.polls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories("com.mimacom.polls.repository")
public class AppConfig extends AbstractCouchbaseConfiguration {

    @Override
    protected List<String> bootstrapHosts() {
        return Collections.singletonList("127.0.0.1");
    }

    @Override
    protected String getBucketName() {
        return "polls";
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }
}
