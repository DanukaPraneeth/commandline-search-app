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
public class OrganizationJsonQueryTest {

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
    public void test_execution_of_organizationInformation_search() {

        String searchUserKey = "_id";
        String searchUserValue = "3";

        userObject.put("_id", 1);
        userObject.put("name", "John");
        userObject.put("organization_id", 3);
        userArray.add(userObject);

        ticketObject.put("organization_id", 3);
        ticketObject.put("name", "Ticket 3");
        ticketArray.add(ticketObject);

        organizationObject.put("_id", 3);
        organizationObject.put("name", "organization 3");
        organizationArray.add(organizationObject);

        OrganizationJsonQuery jsonQuerySpy = Mockito.spy(new OrganizationJsonQuery());

        doReturn(userArray).when(jsonQuerySpy).readJsonFile("USER");
        doReturn(ticketArray).when(jsonQuerySpy).readJsonFile("TICKET");
        doReturn(organizationArray).when(jsonQuerySpy).readJsonFile("ORGANIZATION");

        jsonQuerySpy.displaySearchResults(searchUserKey, searchUserValue);
        verify(jsonQuerySpy, times(1)).displaySearchResults(searchUserKey, searchUserValue);

    }

    @Test
    public void test_display_of_ticket_n_user_details_for_organizationInformation_search() {

        String searchUserKey = "_id";
        String searchUserValue = "3";

        userObject.put("_id", 1);
        userObject.put("name", "John");
        userObject.put("organization_id", 3);
        userArray.add(userObject);

        ticketObject.put("organization_id", 3);
        ticketObject.put("name", "Ticket 3");
        ticketArray.add(ticketObject);

        organizationObject.put("_id", 3);
        organizationObject.put("name", "organization 3");
        organizationArray.add(organizationObject);

        OrganizationJsonQuery jsonQuerySpy = Mockito.spy(new OrganizationJsonQuery());

        doReturn(userArray).when(jsonQuerySpy).readJsonFile("USER");
        doReturn(ticketArray).when(jsonQuerySpy).readJsonFile("TICKET");
        doReturn(organizationArray).when(jsonQuerySpy).readJsonFile("ORGANIZATION");

        jsonQuerySpy.displaySearchResults(searchUserKey, searchUserValue);
        verify(jsonQuerySpy, times(1)). displayUserNames(searchUserValue);
        verify(jsonQuerySpy, times(1)).displayTicketSubjects(searchUserValue);

    }
}