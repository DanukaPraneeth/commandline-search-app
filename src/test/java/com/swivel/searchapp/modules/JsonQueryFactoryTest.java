package com.swivel.searchapp.modules;

import com.swivel.searchapp.modules.impl.OrganizationJsonQuery;
import com.swivel.searchapp.modules.impl.TicketJsonQuery;
import com.swivel.searchapp.modules.impl.UserJsonQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonQueryFactoryTest {

    private  JsonQueryFactory jsonQueryFactory;

    @Before
    public void setUp() throws Exception {
        jsonQueryFactory = new JsonQueryFactory();
    }

    @Test
    public void test_userInputValidation_for_searchType_with_ValidInputs() {
        boolean expected = true;
        boolean actual = jsonQueryFactory.isValidSearchType(1);
        assertEquals(expected, actual);
    }

    @Test
    public void test_userInputValidation_for_searchType_InValidInputs() {
        boolean expected = false;
        boolean actual = jsonQueryFactory.isValidSearchType(5);
        assertEquals(expected, actual);
    }

    @Test
    public void test__factoryName_with_ValidInputs() {
        String  expected_1 = "USER";
        String  expected_2 = "TICKET";
        String  expected_3 = "ORGANIZATION";
        String  actual_1 = jsonQueryFactory.getFactoryName(1);
        String  actual_2 = jsonQueryFactory.getFactoryName(2);
        String  actual_3 = jsonQueryFactory.getFactoryName(3);

        assertEquals("User input validation to search users", expected_1, actual_1);
        assertEquals("User input validation to search tickets", expected_2, actual_2);
        assertEquals("User input validation to search organizations", expected_3, actual_3);
    }

    @Test
    public void test__factoryName_with_InValidInputs() {
        String  expected = null;
        String  actual = jsonQueryFactory.getFactoryName(5);

        assertEquals( expected, actual);
    }

    @Test
    public void test_instantiation_of_factory_instance(){
        JsonQuery fromFactory_1 = new JsonQueryFactory().getJsonDetails((1));
        JsonQuery fromFactory_2 = new JsonQueryFactory().getJsonDetails((2));
        JsonQuery fromFactory_3 = new JsonQueryFactory().getJsonDetails((3));

        Assert.assertTrue("testing factory instantiation for user json", fromFactory_1 instanceof UserJsonQuery);
        Assert.assertTrue("testing factory instantiation for ticket json", fromFactory_2 instanceof TicketJsonQuery);
        Assert.assertTrue("testing factory instantiation for organization json", fromFactory_3 instanceof OrganizationJsonQuery);
    }
}