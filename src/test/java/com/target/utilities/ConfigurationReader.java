package com.target.utilities;

/*
In this class, we will be creating the re-usable logic to read from
configuration.properties file
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //1 Create the Properties object (create object)
    private static Properties properties = new Properties();

    static{

        try {
            //2 Open file using FileInputStream (open file)
            FileInputStream file = new FileInputStream("configuration.properties");
            //3 Load the "properties" object with "file" (load properties)
            properties.load(file);
            file.close();

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND WITH GIVEN PATH!!!");
            e.printStackTrace();
        }
    }

    //create a utility method to use the object to read
    //4 Use "properties" object to read from the file (read properties)

    public static String getProperty(String keyword) {
        return properties.getProperty(keyword);
    }
}
