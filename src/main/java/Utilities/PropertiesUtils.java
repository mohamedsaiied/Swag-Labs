package Utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {

    private PropertiesUtils(){
        super();
    }

    public static final String Properties_File_Path = "src/main/resources/";

    public static Properties loadProperties(){
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFilesList;
            propertiesFilesList = FileUtils.listFiles(new File(Properties_File_Path) , new String[]{"properties"}, true);
            propertiesFilesList.forEach(propertyFile -> {
                try{
                    properties.load(new FileInputStream(propertyFile));
                } catch (IOException e) {
                    LogsUtils.error(e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            LogsUtils.info("Loading Properties File Data");
            return properties;
        } catch (Exception e) {
            LogsUtils.error("failed to load properties file data because : " + e.getMessage());
            return null;
        }
    }

    public static String getPropertyValue(String key){
        try{
            return System.getProperty(key);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "";
        }
    }

}
