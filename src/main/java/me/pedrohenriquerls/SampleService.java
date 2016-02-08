package me.pedrohenriquerls;
 
import com.beust.jcommander.JCommander;
import me.pedrohenriquerls.handlers.*;
import me.pedrohenriquerls.sql2omodel.Sql2oModel;

import static spark.Spark.*;

public class SampleService {

    private static final String API_CONTEXT = "/api/v1";

    public static void main( String[] args) {
        Config options = new Config();
        new JCommander(options, args);

        System.out.println("Starting...");
        System.out.println("Options.debug = " + options.debug);
        System.out.println("Options.dbPort = " + options.dbPort);
        System.out.println("Options.dbHost = " + options.dbHost);
        System.out.println("Options.database = " + options.database);
        System.out.println("Options.dbUsername = " + options.dbUsername);
        System.out.println("Options.servicePort = " + options.servicePort);

        Sql2oModel.setInstance(options);

        staticFileLocation("/public");

        get(API_CONTEXT+"/companies/:uuid/details", new GetCompanyDetailHandler());
        get(API_CONTEXT+"/companies/list", new GetCompaniesHandler());
        post(API_CONTEXT+"/companies/create", new CreateCompanyHandler());
        put(API_CONTEXT+"/companies/update", new UpdateCompanyHandler());

        post(API_CONTEXT+"/companies/employee/create", new CreateEmployeeHandler());

        get(API_CONTEXT+"/alive", (req, res) -> "OK");
    }
}
