# ScreenshotMaker

General goal: automatically taking screenshots from web pages.

**Ran script**

Launch Main.main method


**Settings**

```

        folderManager
                .createNewScreenshotFolder("UrlScreenshots")
                .createHelpCenterFolder("Help_Center_URLs")
                .createIgSelectedFolder("IG_Selected_URLs");

        List<String> urls = new UrlSourceProvider()
                .setDesktopAsSource()
                .setFileName("UrlSource.txt")
                .setOutputFileName("Filtered Urls.txt")
                .getUrlList();

```