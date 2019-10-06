package com.swivel.searchapp.modules.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserJsonQueryTest {

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
    public void test_execution_of_userInformation_search() {

        String searchUserKey = "_id";
        String searchUserValue = "1";
        String orgId = "2";

        userObject.put("_id", 1);
        userObject.put("organization_id", 2);
        userArray.add(userObject);

        ticketObject.put("submitter_id", 1);
        ticketObject.put("assignee_id", 3);
        ticketObject.put("subject", "subject 1");
        ticketArray.add(ticketObject);

        organizationObject.put("_id", 2);
        organizationObject.put("name", "organization 2");
        organizationArray.add(organizationObject);

        UserJsonQuery jsonQuerySpy = Mockito.spy(new UserJsonQuery());

        doReturn(userArray).when(jsonQuerySpy).readJsonFile("USER");
        doReturn(ticketArray).when(jsonQuerySpy).readJsonFile("TICKET");
        doReturn(organizationArray).when(jsonQuerySpy).readJsonFile("ORGANIZATION");

        jsonQuerySpy.displaySearchResults(searchUserKey, searchUserValue);
        verify(jsonQuerySpy, times(1)).displaySearchResults(searchUserKey, searchUserValue);


    }

    @Test
    public void test_display_of_ticket_n_oraganization_details_for_userInformation_search() {

        String searchUserKey = "_id";
        String searchUserValue = "1";
        String orgId = "2";

        userObject.put("_id", 1);
        userObject.put("organization_id", 2);
        userArray.add(userObject);

        ticketObject.put("submitter_id", 1);
        ticketObject.put("assignee_id", 3);
        ticketObject.put("subject", "subject 1");
        ticketArray.add(ticketObject);

        organizationObject.put("_id", 2);
        organizationObject.put("name", "organization 2");
        organizationArray.add(organizationObject);

        UserJsonQuery jsonQuerySpy = Mockito.spy(new UserJsonQuery());

        doReturn(userArray).when(jsonQuerySpy).readJsonFile("USER");
        doReturn(ticketArray).when(jsonQuerySpy).readJsonFile("TICKET");
        doReturn(organizationArray).when(jsonQuerySpy).readJsonFile("ORGANIZATION");

        jsonQuerySpy.displaySearchResults(searchUserKey, searchUserValue);
        verify(jsonQuerySpy, times(1)). displayTicketSubjects(searchUserValue);
        verify(jsonQuerySpy, times(1)).displayOrganizationNames(orgId);

    }
}