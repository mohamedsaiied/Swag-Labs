package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.bidi.log.Log;

import java.io.File;
import java.io.FileReader;

public class JsonUtils {

    public static String JSON_FILE_PATH = "src/test/resources/";

    static String jsonReader;
     String jsonFileName;

    public JsonUtils(String jsonFileName){
        this.jsonFileName = jsonFileName;

        try{
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public String getJsonData(String jsonPath){
        String testData = "";
        try{
            testData = JsonPath.read(jsonReader,jsonPath);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage() , "No results for json path : " + jsonPath + " in the json file : ");
        }
        return testData;
    }




}
