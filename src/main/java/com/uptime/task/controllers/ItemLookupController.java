/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package com.uptime.task.controllers;

import com.uptime.task.utils.SignedRequestsHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

import static com.uptime.task.utils.XmlHelper.convertXmlToJson;

/*
 * This class shows how to make a simple authenticated ItemLookup call to the
 * Amazon Product Advertising API.
 * 
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
@Controller
public class ItemLookupController {
    /*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAJKFVAAALNOUDBDXQ";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "FUz8axyEerZvGg2MYQNjEip6WcrvvMv2paSOuuZa";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "ecs.amazonaws.com";

    /**
     * Product lookup controller
     * @param searchIndex
     * @param keywords
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/amazonProductSearch", method= RequestMethod.POST, produces="application/json")
    public String getSearchResultViaAjax(
            @RequestParam(value = "searchIndex") String searchIndex,
            @RequestParam(value = "keywords") String keywords,
            @RequestParam(value = "currentPage") Long currentPage) {

        String jsonResponse = "{}";

        // Set up the signed requests helper
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResponse;
        }

        String requestUrl = null;

        // Fill the request parameters
        System.out.println("Map form example:");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        params.put("AssociateTag", "ajakupson-20");
        params.put("Operation", "ItemSearch");
        params.put("SearchIndex", searchIndex);
        params.put("Keywords", keywords);
        params.put("RelatedItemPage", currentPage.toString());
        params.put("ResponseGroup", "Large");

        requestUrl = helper.sign(params);
        System.out.println("Signed Request is \"" + requestUrl + "\"");

        jsonResponse = getJsonDataFromResponse(requestUrl);

//        for(Long i = currentPage; i < (preloaded ? 3 : 2); i++) {
//            params.put("VariationPage", currentPage.toString());
//        }

        return jsonResponse;

    }

    private String getJsonDataFromResponse(String requestUrl) {
        String response = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);

            response = convertXmlToJson(doc);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

//    private String getListOfProductsForSpecificPage() {
//
//    }
}
