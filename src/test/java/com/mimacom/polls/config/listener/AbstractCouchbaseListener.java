/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 *  Project: Service Pricing Calculator
 *
 *  Copyright © $year Baxter Healthcare Corporation. All rights reserved.
 */

package com.mimacom.polls.config.listener;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.DesignDocument;
import com.couchbase.client.protocol.views.ViewDesign;
import com.mimacom.polls.util.Constants;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author _lopezj24
 */
public class AbstractCouchbaseListener extends DependencyInjectionTestExecutionListener {

    void createAndWaitForDesignDocs(CouchbaseClient client){
        DesignDocument designDoc = new DesignDocument(Constants.DESIGN_DOC_COMMON.toString());

        String mapFunction = "function (doc, meta) {  emit(meta.id, null); }";
        String reduceFunction = "function (key, values, rereduce) {\n" +
                "    var max = 0\n" +
                "    for(var i = 0; i < values.length; i++)\n" +
                "        max = Math.max(values[i], max);\n" +
                "    return max+1;\n" +
                "}";

        designDoc.setView(new ViewDesign(Constants.VIEW_SEQUENCE_ID.toString(), mapFunction, reduceFunction));

        client.createDesignDoc(designDoc);
    }

}
