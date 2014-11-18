package com.mimacom.polls.config;

import com.couchbase.client.CouchbaseClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.WriteResultChecking;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories("com.mimacom.polls.repository")
public class TestApplicationConfig extends AbstractCouchbaseConfiguration {

    @Bean
    public String couchbaseAdminUser() {
        return  "Administrator";
    }

    @Bean
    public String couchbaseAdminPassword() {
        return "s3cr3t0";
    }

    @Override
    protected List<String> bootstrapHosts() {
        return Arrays.asList("127.0.0.1");
    }

    @Override
    protected String getBucketName() {
        return "test-polls";
    }

    @Override
    protected String getBucketPassword() {
        return "test-polls";
    }

    @Bean
    public BucketCreator bucketCreator() throws Exception {
        return new BucketCreator(this.bootstrapHosts().get(0), this.couchbaseAdminUser(), this.couchbaseAdminPassword(), this.getBucketName());
    }

    @Bean
    @Override
    @DependsOn("bucketCreator")
    public CouchbaseClient couchbaseClient() throws Exception {
        return super.couchbaseClient();
    }

    @Override
    public CouchbaseTemplate couchbaseTemplate() throws Exception {
        CouchbaseTemplate template = super.couchbaseTemplate();
        template.setWriteResultChecking(WriteResultChecking.LOG);
        return template;
    }
}
