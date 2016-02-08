package me.pedrohenriquerls;

import com.beust.jcommander.Parameter;

public class Config {

    @Parameter(names = "--debug")
    public static boolean debug = false;

    @Parameter(names = {"--service-port"})
    public static Integer servicePort = 4567;

    @Parameter(names = {"--database"})
    public static String database = "sample";

    @Parameter(names = {"--db-host"})
    public static String dbHost = "192.168.99.100";

    @Parameter(names = {"--db-username"})
    public static String dbUsername = "sample_owner";

    @Parameter(names = {"--db-password"})
    public static String dbPassword = "sampletest";

    @Parameter(names = {"--db-port"})
    public static Integer dbPort = 5432;

    public static final String token = "37d759acc748941dd27c7eea06f7cbdb";
}
