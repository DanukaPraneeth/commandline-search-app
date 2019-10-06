package com.swivel.searchapp.modules.impl;

import com.swivel.searchapp.modules.JsonQuery;
import com.swivel.searchapp.util.Constant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class OrganizationJsonQuery extends JsonQuery {

    @Override
    public void displaySearchResults(String searchKey, String searchValue) {

        System.out.format("Searching organizations for %s with a value of %s \n", searchKey, searchValue);

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_ORGANIZATION);

        if( validateSearchTerm(searchKey, (JSONObject) dataArray.get(0)) ){

            Iterator<Object> iterator = dataArray.iterator();
            boolean isDataExists = false;
            while (iterator.hasNext()) {
                JSONObject organization = (JSONObject) iterator.next();

                if(  String.valueOf(organization.get(searchKey)).equals(searchValue) ){

                    Iterator keys = organization.keySet().iterator();

                    while(keys.hasNext()) {
                        String currentDynamicKey = String.valueOf(keys.next());
                        System.out.format("%-30s %-30s \n", currentDynamicKey, String.valueOf(organization.get(currentDynamicKey)));
                    }

                    displayUserNames(String.valueOf(organization.get("_id")));
                    displayTicketSubjects(String.valueOf(organization.get("_id")));
                    System.out.println("\n");
                    isDataExists = true;
                }
            }

            if(! isDataExists)
                System.out.println("No results found");
        }
        else
            System.out.println("Invalid value for search term");

    }



    protected void displayUserNames(String organizationId) {

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_USER);
        Iterator<Object> iterator = dataArray.iterator();
        int counter = 0;

        while (iterator.hasNext()) {
            JSONObject user = (JSONObject) iterator.next();

            if(  String.valueOf(user.get("organization_id")).equals(organizationId) ) {
                System.out.format("%-30s %-30s \n", "user_name_" + counter, String.valueOf(user.get("name")));
                counter ++;
            }
        }
    }



    protected void displayTicketSubjects(String organizationId){

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_TICKET);
        Iterator<Object> iterator = dataArray.iterator();
        int counter = 0;

        while (iterator.hasNext()) {
            JSONObject ticket = (JSONObject) iterator.next();

            if(  String.valueOf(ticket.get("organization_id")).equals(organizationId) ) {
                System.out.format("%-30s %-30s \n", "ticket_" + counter, String.valueOf(ticket.get("subject")));
                counter++;
            }
        }
    }


}
