package gol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private String filePath;
    private List<String> lines = new ArrayList<>();

    public ReadFile(String fp) {
        this.filePath = fp;
    }
    
    public void readFile() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line;

        System.out.println("begin reading file");

        while ((line = br.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            lines.add(line);

        }

        System.out.println("file completed reading");
        br.close();
    }

    // Getters
    public String getFilePath() { return filePath; }

    public List<String> getLines() { return lines; }


    
}
