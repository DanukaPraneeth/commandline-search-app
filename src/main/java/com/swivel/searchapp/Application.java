package com.swivel.searchapp;

public class Application {

    public static void main(String[] args) {

        ApplicationExecutor app = new ApplicationExecutor();
        if(app.readPropertyFile())
            app.runApplication();
    }

}
