package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtils {
    public static String LOGS_PATH = "test-outputs/logs";

    public static Logger logger (){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    public static void trace(String... msg){
        logger().trace(String.join(" ",msg));
    }

    public static void debug(String... msg){
        logger().debug(String.join(" ",msg));
    }

    public static void info(String... msg){
        logger().info(String.join(" ",msg));
    }

    public static void error(String... msg){
        logger().error(String.join(" ",msg));
    }

    public static void warn(String... msg){
        logger().warn(String.join(" ",msg));
    }

    public static void fatal(String... msg){
        logger().fatal(String.join(" ",msg));
    }


}
