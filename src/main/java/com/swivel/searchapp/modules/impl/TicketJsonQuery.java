package com.swivel.searchapp.modules.impl;

import com.swivel.searchapp.modules.JsonQuery;
import com.swivel.searchapp.util.Constant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class TicketJsonQuery extends JsonQuery {

    @Override
    public void displaySearchResults(String searchKey, String searchValue) {

        System.out.format("Searching tickets for %s with a value of %s \n", searchKey, searchValue);

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_TICKET);

        if( validateSearchTerm(searchKey, (JSONObject) dataArray.get(0)) ){

            Iterator<Object> iterator = dataArray.iterator();
            boolean isDataExists = false;
            while (iterator.hasNext()) {
                JSONObject ticket = (JSONObject) iterator.next();

                if(  String.valueOf(ticket.get(searchKey)).equals(searchValue) ){

                    Iterator keys = ticket.keySet().iterator();

                    while(keys.hasNext()) {
                        String currentDynamicKey = String.valueOf(keys.next());
                        System.out.format("%-30s %-30s \n", currentDynamicKey, String.valueOf(ticket.get(currentDynamicKey)));
                    }

                    displayOrganizationNames(String.valueOf(ticket.get("organization_id")));
                    displayUserNames(String.valueOf(ticket.get("submitter_id")), String.valueOf(ticket.get("assignee_id")));
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



    protected void displayUserNames(String submitId, String assignId) {

        JSONArray dataArray = readJsonFile(Constant.JSON_NAME_FOR_USER);
        Iterator<Object> iterator = dataArray.iterator();
        while (iterator.hasNext()) {
            JSONObject user = (JSONObject) iterator.next();

            if(  String.valueOf(user.get("_id")).equals(submitId) )
                System.out.format("%-30s %-30s \n", "submitter_name", String.valueOf(user.get("name")));

            if(  String.valueOf(user.get("_id")).equals(assignId) )
                System.out.format("%-30s %-30s \n", "assignee_name", String.valueOf(user.get("name")));
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