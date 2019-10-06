package com.swivel.searchapp.modules.impl;

import com.swivel.searchapp.modules.JsonQuery;
import com.swivel.searchapp.util.Constant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Iterator;

public class UserJsonQuery extends JsonQuery {


    @Override
    public void displaySearchResults(String searchKey, String searchValue) {

        System.out.format("Searching users for %s with a value of %s \n", searchKey, searchValue);

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_USER);

        if( validateSearchTerm(searchKey, (JSONObject) dataArray.get(0)) ){

            Iterator<Object> iterator = dataArray.iterator();
            boolean isDataExists = false;
            while (iterator.hasNext()) {
                JSONObject user = (JSONObject) iterator.next();

                if(  String.valueOf(user.get(searchKey)).equals(searchValue) ){

                    Iterator keys = user.keySet().iterator();

                    while(keys.hasNext()) {
                        String currentDynamicKey = String.valueOf(keys.next());
                        System.out.format("%-30s %-30s \n", currentDynamicKey, String.valueOf(user.get(currentDynamicKey)));
                    }

                    displayOrganizationNames(String.valueOf(user.get("organization_id")));
                    displayTicketSubjects(String.valueOf(user.get("_id")));
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



    protected void displayTicketSubjects(String userId){

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_TICKET);
        Iterator<Object> iterator = dataArray.iterator();
        int counter = 0;

        while (iterator.hasNext()) {
            JSONObject ticket = (JSONObject) iterator.next();

            if(  String.valueOf(ticket.get("submitter_id")).equals(userId) ||  String.valueOf(ticket.get("assignee_id")).equals(userId) ) {
                System.out.format("%-30s %-30s \n", "ticket_" + counter, String.valueOf(ticket.get("subject")));
                counter++;
            }
        }
    }



    protected void displayOrganizationNames(String organizationId){

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_ORGANIZATION);
        Iterator<Object> iterator = dataArray.iterator();
        while (iterator.hasNext()) {
            JSONObject organization = (JSONObject) iterator.next();

            if(  String.valueOf(organization.get("_id")).equals(organizationId) ) {
                System.out.format("%-30s %-30s \n", "organization_name", String.valueOf(organization.get("name")));
                break;
            }
        }
    }
}
