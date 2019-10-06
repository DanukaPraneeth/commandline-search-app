package com.swivel.searchapp.modules;

import com.swivel.searchapp.modules.impl.OrganizationJsonQuery;
import com.swivel.searchapp.modules.impl.TicketJsonQuery;
import com.swivel.searchapp.modules.impl.UserJsonQuery;
import com.swivel.searchapp.util.Constant;

public class JsonQueryFactory {

    public JsonQuery getJsonDetails(int searchType){

        String factoryName = getFactoryName(searchType);

        if(factoryName == null)
            return null;

        else if(factoryName.equals(Constant.JSON_NAME_FOR_USER))
            return new UserJsonQuery();

        else if(factoryName.equals(Constant.JSON_NAME_FOR_TICKET))
            return new TicketJsonQuery();

        else if(factoryName.equals(Constant.JSON_NAME_FOR_ORGANIZATION))
            return new OrganizationJsonQuery();

        return null;
    }


    public void displaySearchTypes(){
        System.out.println("Select 1) Users or 2) Tickets or 3) Organizations");
    }


    public boolean isValidSearchType(int searchType){

        if(searchType == 1 || searchType == 2 || searchType == 3)
            return true;

        return false;
    }


    public String  getFactoryName(int searchType){

        switch (searchType){
            case 1 : return Constant.JSON_NAME_FOR_USER;
            case 2 : return Constant.JSON_NAME_FOR_TICKET;
            case 3 : return Constant.JSON_NAME_FOR_ORGANIZATION;

            default: return null;
        }
    }


    public void displaySearchFieldList(){
        JsonQuery jsonQuery =  new OrganizationJsonQuery();
        jsonQuery.getSearchFieldList();
    }
}
