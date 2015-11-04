/*
*Copyright (c) 2005-2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/
package org.dharshanaw.rest.client;


import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class GenericRestClientWink {
    RestClient client;
    ClientResponse response;

    public GenericRestClientWink() {
        client = new RestClient();
    }

    public static void main(String[] args) throws JSONException {
        GenericRestClientWink genericRestClient = new GenericRestClientWink();
        Map<String, String> queryParamMap = new HashMap<String, String>();
        Map<String, String> headerMap = new HashMap<String, String>();
        genericRestClient.setKeysForRestClient();
        JSONObject obj = new JSONObject(genericRestClient.
                geneticRestRequestPost("https://localhost:9443/publisher/apis/authenticate/",
                                       MediaType.APPLICATION_FORM_URLENCODED,
                                       MediaType.APPLICATION_JSON, "username=admin&password=admin",
                                       queryParamMap, headerMap, null).getEntity(String.class));
        obj.getJSONObject("data").getString("sessionId");
    }

    private void setKeysForRestClient() {
        System.setProperty("javax.net.ssl.trustStore", "/Users/dharshana/greg/wso2greg-5.1.0-SNAPSHOT/repository/resources/security/wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");

    }

    public ClientResponse geneticRestRequestPost(String resourceUrl, String contentType,
                                                 String acceptMediaType, Object postBody,
                                                 Map<String, String> queryParamMap,
                                                 Map<String, String> headerMap,
                                                 String cookie) {
        Resource resource = client.resource(resourceUrl);
        if (!(queryParamMap.size() <= 0)) {
            for (Map.Entry<String, String> queryParamEntry : queryParamMap.entrySet()) {
                resource.queryParam(queryParamEntry.getKey(), queryParamEntry.getValue());
            }
        }
        if (!(headerMap.size() <= 0)) {
            for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                resource.header(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        if (cookie != null) {
            resource.cookie(cookie);
        }
        response = resource.contentType(contentType).
                accept(acceptMediaType).post(postBody);
        return response;
    }

    public ClientResponse geneticRestRequestDelete(String resourceUrl, String contentType,
                                                   String acceptMediaType,
                                                   Map<String, String> queryParamMap,
                                                   Map<String, String> headerMap,
                                                   String cookie) {
        Resource resource = client.resource(resourceUrl);
        if (!(queryParamMap.size() <= 0)) {
            for (Map.Entry<String, String> queryParamEntry : queryParamMap.entrySet()) {
                resource.queryParam(queryParamEntry.getKey(), queryParamEntry.getValue());
            }
        }
        if (!(headerMap.size() <= 0)) {
            for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                resource.header(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        if (cookie != null) {
            resource.cookie(cookie);
        }
        response = resource.contentType(contentType).
                accept(acceptMediaType).delete();
        return response;
    }


}