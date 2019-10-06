package com.swivel.searchapp;

import com.swivel.searchapp.modules.JsonQuery;
import com.swivel.searchapp.modules.JsonQueryFactory;
import com.swivel.searchapp.util.Constant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class ApplicationExecutor {

    public boolean readPropertyFile(){

        try (InputStream input = new FileInputStream("application.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            Constant.JSON_FILE_LOCATION_FOR_USER = prop.getProperty("user.json.location");
            Constant.JSON_FILE_LOCATION_FOR_TICKET = prop.getProperty("ticket.json.location");
            Constant.JSON_FILE_LOCATION_FOR_ORGANIZATION = prop.getProperty("organization.json.location");
            return true;

        } catch (IOException ex) {
            System.out.println("Error while reading the property file " + ex);
        }
        return false;
    }


    public void runApplication(){
        String userInput;
        Scanner sn = new Scanner(System.in);
        JsonQueryFactory jsonQueryFactoryInstance = new JsonQueryFactory();

        //loop the utility in loop until the user makes the choice to exit
        while(true){

            System.out.println("\n\nType 'quit' to exit at any time. Press 'Enter' to continue \n\n");
            System.out.println("            Select search options");
            System.out.println("             * Press 1 to search");
            System.out.println("             * Press 2 to view a list of searchable fields");
            System.out.println("             * Press 'quit' to exit \n");

            userInput = sn.next();

            switch(userInput){

                case "1":

                    jsonQueryFactoryInstance.displaySearchTypes();

                    int searchId;
                    try{
                        searchId = Integer.valueOf(sn.next());
                        if(jsonQueryFactoryInstance.isValidSearchType(searchId)) {
                            System.out.println("Enter search term");
                            String searchTerm = sn.next();
                            System.out.println("Enter search value");
                            String searchValue = sn.next();

                            JsonQuery jsonQuery = jsonQueryFactoryInstance.getJsonDetails((searchId));
                            jsonQuery.displaySearchResults(searchTerm, searchValue);
                        }
                        else
                            System.out.println("Invalid search type ");
                    }catch (NumberFormatException e){
                        System.out.print("Invalid input. Please enter a number.");
                    }
                    break;

                case "2":

                    System.out.println("done with job number 2");
                    jsonQueryFactoryInstance.displaySearchFieldList();
                    break;

                case "quit":

                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Read the options carefully...");
            }
        }

    }

}
