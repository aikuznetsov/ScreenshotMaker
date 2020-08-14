import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FolderManager {

    private String screenshotFolderPath,
            helpCenterFolderPath,
            igSelectedFolderPath;


    public static String getDesktopPath() {
        String USER_NAME = System.getProperty("user.name");
        return "/Users/" + USER_NAME + "/Desktop/";
    }

    public FolderManager() {
    }

    public FolderManager createNewScreenshotFolder(String folderName) throws IOException {
        File screenshotFolder = new File(FolderManager.getDesktopPath() + folderName);
        if (screenshotFolder.exists())
            FileUtils.deleteDirectory(screenshotFolder);
        screenshotFolder.mkdir();
        this.screenshotFolderPath = screenshotFolder.getAbsolutePath();
        return this;
    }

    public String getScreenshotFolderPath() {
        return screenshotFolderPath;
    }

    public FolderManager createHelpCenterFolder(String folderName) throws IOException {
        File helpCenterFolder = new File(getScreenshotFolderPath() + "/" + folderName);
        if (helpCenterFolder.exists())
            FileUtils.deleteDirectory(helpCenterFolder);
        helpCenterFolder.mkdir();
        this.helpCenterFolderPath = helpCenterFolder.getAbsolutePath();
        return this;
    }

    public FolderManager createIgSelectedFolder(String folderName) throws IOException {
        File igSelectedFolder = new File(getScreenshotFolderPath() + "/" + folderName);
        if (igSelectedFolder.exists())
            FileUtils.deleteDirectory(igSelectedFolder);
        igSelectedFolder.mkdir();
        this.igSelectedFolderPath = igSelectedFolder.getAbsolutePath();
        return this;
    }

    public String getScreenshotFolderByUrl(String url){
        if (url.contains("facebook.com/help"))
            return helpCenterFolderPath;
        else
            return igSelectedFolderPath;
    }


}
