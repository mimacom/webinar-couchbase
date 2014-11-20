package com.mimacom.polls.config.listener;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.DesignDocument;
import com.couchbase.client.protocol.views.InvalidViewException;
import com.couchbase.client.protocol.views.ViewDesign;
import com.mimacom.polls.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;

import java.util.Arrays;

/**
 * @author Michael Nitschinger
 */
public class PollRepositoryListener extends AbstractCouchbaseListener {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(PollRepositoryListener.class);

    public void beforeTestClass(final TestContext testContext) throws Exception {
        CouchbaseClient client = (CouchbaseClient) testContext.getApplicationContext().getBean("couchbaseClient");
        createAndWaitForDesignDocs(client);
    }

    void createAndWaitForDesignDocs(CouchbaseClient client) {
        super.createAndWaitForDesignDocs(client);

        DesignDocument designDocument = null;
        try {
            designDocument = client.getDesignDoc(Constants.DESIGN_DOC_POLL.toString());
        } catch (InvalidViewException e) {
            this.logger.debug("The design doc is not created, creating design doc....");
        }
        if (designDocument != null) {
            client.deleteDesignDoc(Constants.DESIGN_DOC_POLL.toString());
        }
        DesignDocument designDoc = new DesignDocument(Constants.DESIGN_DOC_POLL.toString());
        String byUserNameMap = "function (doc, meta) { if (doc._class == 'com.mimacom.polls.domain.Poll') { emit(doc.userName, null); }}";
        String allMap = "function (doc, meta) {  emit(meta.id, null); }";
        ViewDesign byUserName = new ViewDesign(Constants.VIEW_BY_USER_NAME.toString(), byUserNameMap, "");
        ViewDesign all = new ViewDesign(Constants.VIEW_ALL.toString(), allMap, "");
        designDoc.setViews(Arrays.asList(byUserName, all));
        client.createDesignDoc(designDoc);
    }

}
