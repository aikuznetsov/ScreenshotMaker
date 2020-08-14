import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UrlSourceProvider {

    private String source;
    private String fileName;
    private String outputFileName;

    public UrlSourceProvider() {
    }

    public UrlSourceProvider setDesktopAsSource() {
        this.source = FolderManager.getDesktopPath();
        return this;
    }

    public UrlSourceProvider setSource(String source) {
        this.source = source;
        return this;
    }

    public UrlSourceProvider setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public UrlSourceProvider setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
        return this;
    }

    public List<String> getUrlList() {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(source + fileName));
            String line = br.readLine();
            while (line != null) {
                if (line.contains("http")) {
                    if (!line.contains("https"))
                        line = line.replace("http", "https");
                    result.add(line);
                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = result.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("URL count: " + result.size());

        if (outputFileName != null) {
            File output = new File(source + outputFileName);
            try {
                output.createNewFile();
                java.io.FileWriter fileWriter = new java.io.FileWriter(output, true);
                for (String url : result) {
                    fileWriter.write(url + "\n");
                }
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {
            }
        }
        return result;
    }
}
