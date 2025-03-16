import Utilities.JsonUtils;
import Utilities.PropertiesUtils;

public class main {
    public static void main(String[] args) {
        JsonUtils testData;
        testData = new JsonUtils("loginData");
        System.out.println(testData.getJsonData("Login-credentials.username"));
        System.out.println(testData.getJsonData("Login-credentials.password"));
    }
}
