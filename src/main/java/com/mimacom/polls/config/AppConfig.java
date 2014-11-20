package com.mimacom.polls.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories("com.mimacom.polls.repository")
public class AppConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.host}")
    String couchbaseHost;

    @Value("${couchbase.bucket}")
    String couchbaseBucket;

    @Value("${couchbase.bucket.pass}")
    String couchbaseBucketPass;

    @Override
    protected List<String> bootstrapHosts() {
        return Collections.singletonList(couchbaseHost);
    }

    @Override
    protected String getBucketName() {
        return couchbaseBucket;
    }

    @Override
    protected String getBucketPassword() {
        return couchbaseBucketPass;
    }
}
