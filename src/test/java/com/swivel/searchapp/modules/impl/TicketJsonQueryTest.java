package com.swivel.searchapp.modules.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class TicketJsonQueryTest {

    private JSONObject userObject;
    private JSONObject ticketObject;
    private JSONObject organizationObject;

    private JSONArray userArray;
    private JSONArray ticketArray;
    private JSONArray organizationArray;


    @Before
    public void setUp() throws Exception {
        userObject = new JSONObject();
        ticketObject = new JSONObject();
        organizationObject = new JSONObject();

        userArray=new JSONArray();
        ticketArray=new JSONArray();
        organizationArray=new JSONArray();

    }


    @Test
    public void test_execution_of_ticketInformation_search() {

        String searchUserKey = "organization_id";
        String searchUserValue = "2";
        String orgId = "2";

        userObject.put("_id", 1);
        userObject.put("name", "John");
        userArray.add(userObject);

        ticketObject.put("organization_id", 2);
        ticketObject.put("submitter_id", 1);
        ticketObject.put("assignee_id", 2);
        ticketArray.add(ticketObject);

        organizationObject.put("_id", 2);
        organizationObject.put("name", "organization 2");
        organizationArray.add(organizationObject);

        TicketJsonQuery jsonQuerySpy = Mockito.spy(new TicketJsonQuery());

        doReturn(userArray).when(jsonQuerySpy).readJsonFile("USER");
        doReturn(ticketArray).when(jsonQuerySpy).readJsonFile("TICKET");
        doReturn(organizationArray).when(jsonQuerySpy).readJsonFile("ORGANIZATION");

        jsonQuerySpy.displaySearchResults(searchUserKey, searchUserValue);
        verify(jsonQuerySpy, times(1)).displaySearchResults(searchUserKey, searchUserValue);


    }

    @Test
    public void test_display_of_user_n_oraganization_details_for_ticketInformation_search() {

        String searchUserKey = "organization_id";
        String searchUserValue = "2";
        String orgId = "2";

        userObject.put("_id", 1);
        userObject.put("name", "John");
        userArray.add(userObject);

        ticketObject.put("organization_id", 2);
        ticketObject.put("submitter_id", 1);
        ticketObject.put("assignee_id", 2);
        ticketArray.add(ticketObject);

        organizationObject.put("_id", 2);
        organizationObject.put("name", "organization 2");
        organizationArray.add(organizationObject);


        TicketJsonQuery jsonQuerySpy = Mockito.spy(new TicketJsonQuery());

        doReturn(userArray).when(jsonQuerySpy).readJsonFile("USER");
        doReturn(ticketArray).when(jsonQuerySpy).readJsonFile("TICKET");
        doReturn(organizationArray).when(jsonQuerySpy).readJsonFile("ORGANIZATION");

        jsonQuerySpy.displaySearchResults(searchUserKey, searchUserValue);
        verify(jsonQuerySpy, times(1)). displayUserNames("1","2");
        verify(jsonQuerySpy, times(1)).displayOrganizationNames(orgId);

    }
}