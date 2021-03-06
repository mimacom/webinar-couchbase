package com.mimacom.polls.config;

import com.couchbase.client.CouchbaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.WriteResultChecking;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories("com.mimacom.polls.repository")
@PropertySource(value = {"classpath:com/mimacom/polls/config/couchbase.properties"})
public class TestApplicationConfig extends AbstractCouchbaseConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public String couchbaseAdminUser() {
        return  env.getProperty("couchbase.username");
    }

    @Bean
    public String couchbaseAdminPassword() {
        return env.getProperty("couchbase.password");
    }

    @Override
    protected List<String> bootstrapHosts() {
        return Arrays.asList(env.getProperty("couchbase.host"));
    }

    @Override
    protected String getBucketName() {
        return env.getProperty("couchbase.test.bucket");
    }

    @Override
    protected String getBucketPassword() {
        return env.getProperty("couchbase.test.bucket.pass");
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
