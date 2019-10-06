package com.swivel.searchapp.modules;

import com.swivel.searchapp.modules.impl.UserJsonQuery;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JsonQueryTest {


    @Mock
    private JSONParser parser;
    @Mock
    private FileReader fileReader;

    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private UserJsonQuery jsonQuery;



    @Before
    public void setUp(){
        jsonArray=new JSONArray();
        jsonObject=new JSONObject();
        jsonQuery = new UserJsonQuery();
    }

    @Test
    public void test_searchTerm_validation() {

        jsonObject.put("firstName", "John");
        jsonObject.put("lastName", "Doe");
        jsonArray.add(jsonObject);

        assertTrue(jsonQuery.validateSearchTerm("firstName", jsonObject));
        assertFalse(jsonQuery.validateSearchTerm("first", jsonObject));
    }

    @Test
    public void test_jsonArray_extraction_from_jsonFile() throws Exception {
        jsonObject.put("firstName", "John");
        jsonObject.put("lastName", "Doe");
        jsonArray.add(jsonObject);
        String fileLocation = null;         // testing the file read without actual file

        when(parser.parse(any(FileReader.class))).thenReturn(jsonArray);

        JsonQuery jsonQuerySpy = Mockito.spy(new UserJsonQuery());
        doReturn(parser).when(jsonQuerySpy).createJSONParser();
        doReturn(fileReader).when(jsonQuerySpy).createFileReader(fileLocation);

        assertEquals("Testing the json file read for the type USER", jsonArray, jsonQuerySpy.readJsonFile("USER"));
        assertEquals("Testing the json file read for the type TICKET", jsonArray, jsonQuerySpy.readJsonFile("TICKET"));
        assertEquals("Testing the json file read for the type ORGANIZATION", jsonArray, jsonQuerySpy.readJsonFile("ORGANIZATION"));
    }


}