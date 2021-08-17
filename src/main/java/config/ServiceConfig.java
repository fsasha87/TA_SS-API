package config;

import utils.PropertiesReader;

public class ServiceConfig {
    public static final String HOST = "http://localhost:8080";
    //            ServiceConfig.getHost();

    private static String getHost() {
        return new PropertiesReader("config.properties").get("host");
    }
}
