package com.swivel.searchapp.modules;

import com.swivel.searchapp.util.Constant;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


public abstract class JsonQuery {


    public boolean validateSearchTerm(String searchKey, JSONObject sampleObject){
        Iterator keys = sampleObject.keySet().iterator();

        while(keys.hasNext()) {
            if( String.valueOf(keys.next()).equals(searchKey) )
                return true;
        }
        return false;
    }



    public abstract void displaySearchResults(String searchKey, String searchValue);



    public JSONArray readJsonFile(String jsonType) {

        String fileLocation;

        if(jsonType.equals(Constant.JSON_NAME_FOR_USER))
            fileLocation = Constant.JSON_FILE_LOCATION_FOR_USER;

        else if(jsonType.equals(Constant.JSON_NAME_FOR_TICKET))
            fileLocation = Constant.JSON_FILE_LOCATION_FOR_TICKET;

        else if(jsonType.equals(Constant.JSON_NAME_FOR_ORGANIZATION))
            fileLocation = Constant.JSON_FILE_LOCATION_FOR_ORGANIZATION;

        else
            fileLocation = null;

        JSONArray jsonArray = new JSONArray();

        try{
                Reader reader = createFileReader(fileLocation);
                JSONParser parser = createJSONParser();
                jsonArray = (JSONArray) parser.parse(reader);

            } catch (ParseException e) {
                System.out.format("Error while reading the %s file : %s \n", fileLocation, e);
                System.out.println("Please verify the input source file and restart the application....");
            }catch (IOException e) {
                System.out.format("Error while reading the %s file : %s \n", fileLocation, e);
                System.out.println("Please verify the input source file and restart the application....");
            }
        return jsonArray;
    }



    public void getSearchFieldList(){

        System.out.println("\n-----------------------------------------------------");
        System.out.println("Search Users with");

        JSONArray userArray = readJsonFile(Constant.JSON_NAME_FOR_USER);
        JSONObject sampleUser = (JSONObject) userArray.get(0);
        Iterator userKeys = sampleUser.keySet().iterator();
        while(userKeys.hasNext())
            System.out.println(userKeys.next());


        System.out.println("\n-----------------------------------------------------");
        System.out.println("Search Tickets with");

        JSONArray ticketArray = readJsonFile(Constant.JSON_NAME_FOR_TICKET);
        JSONObject sampleTicket = (JSONObject) ticketArray.get(0);
        Iterator ticketKeys = sampleTicket.keySet().iterator();
        while(ticketKeys.hasNext())
            System.out.println(ticketKeys.next());

        System.out.println("\n-----------------------------------------------------");
        System.out.println("Search Organizations with");

        JSONArray oraganizationArray = readJsonFile(Constant.JSON_NAME_FOR_ORGANIZATION);
        JSONObject sampleOraganization = (JSONObject) oraganizationArray.get(0);
        Iterator oraganizationKeys = sampleOraganization.keySet().iterator();
        while(oraganizationKeys.hasNext())
            System.out.println(oraganizationKeys.next());

        System.out.println();

    }


    protected JSONParser createJSONParser() {
        return new JSONParser();
    }


    protected FileReader createFileReader(String fileLocation) throws FileNotFoundException {
        return new FileReader(fileLocation);
    }
}