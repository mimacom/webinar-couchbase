package com.mimacom.polls.config;

import com.couchbase.client.ClusterManager;
import com.couchbase.client.clustermanager.BucketType;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

public class BucketCreator implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(BucketCreator.class);

    private final String hostUri;
    private final String adminUser;
    private final String adminPass;
    private final String bucket;

    public BucketCreator(String host, String user, String pass, String bucket) {
        hostUri = host;
        adminUser = user;
        adminPass = pass;
        this.bucket = bucket;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(adminUser, adminPass));
        HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
        ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(client);

        RestTemplate template = new RestTemplate(rf);

        String fullUri = "http://" + hostUri + ":8091/pools/default/buckets/" + this.bucket;

        ResponseEntity<String> entity;
        try {
            entity = template.getForEntity(fullUri, String.class);
        } catch (HttpClientErrorException ex) {
            logger.info("Got execpetion while looking for bucket: " + ex.getMessage());
            if (ex.getMessage().equals("404 Object Not Found")) {
                logger.info("Creating bucket with admin credentials.");
                createBucket();
                return;
            } else {
                throw new RuntimeException("Could not see if bucket is already created.", ex);
            }
        }

        logger.info("Checking for bucket returned status code " + entity.getStatusCode());
    }

    private void createBucket() throws Exception {
        ClusterManager bucketManager =
                new ClusterManager(Arrays.asList(new URI(hostUri)), adminUser, adminPass);
        bucketManager.createNamedBucket(BucketType.COUCHBASE, this.bucket, 128, 0, "test-polls", true);

        logger.info("Finished creating bucket, sleeping for warmup.");
        Thread.sleep(5000);
        bucketManager.shutdown();
    }

}