package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {

    private FileUtils() {
        super();
    }

    private static final Logger logger = LogManager.getLogger(FileUtils.class);

    public static void deleteFiles(File dirPath){
        if(dirPath == null || !dirPath.exists()){
            LogsUtils.warn("directory does not exist");
            return;
        }
        File[] files = dirPath.listFiles();
        if(files == null) {
            LogsUtils.warn("directory is empty");
            return;
        }
        for(File file : files){
            //equal true in case of directory and false in case of file
            if(file.isDirectory()){
                deleteFiles(file);
            }else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtils.error("unable to delete file " + file.getName());
                }
            }
        }
    }

    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if(files == null || files.length ==0){
            LogsUtils.warn("no files found in directory "+ folderPath);
            return null;
        }
        File latestFile = files[0];
        for(File file : files){
            if(file.lastModified() > latestFile.lastModified()){
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void cleanDirectory(File file){
        try{
            org.apache.commons.io.FileUtils.cleanDirectory(file);
        } catch (Exception e) {
            LogsUtils.error("Error cleaning directory: " + e.getMessage());
        }
    }


}
