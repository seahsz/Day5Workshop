package gol;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException{

        String filePath = "";

        // Obtain filePath from console
        if (args.length != 1) {
            System.out.println("Only include 1 argument: file path");
            System.exit(-1);
        } else {
            filePath = args[0];
        }

        // Read file
        ReadFile rf = new ReadFile(filePath);
        rf.readFile();
        List<String> lines = rf.getLines();

        Grid grid = new Grid();

        int initStartIdx = 0;
        // Go through line by line
        for (int idx = 0; idx < lines.size(); idx++) {

            String line = lines.get(idx);
            String[] temp = line.toLowerCase().trim().split(" ");
            String cmd = temp[0];

            switch (cmd) {
                case "grid":
                    int height = Integer.parseInt(temp[1]);
                    int length = Integer.parseInt(temp[2]);
                    grid.setHeight(height);
                    grid.setLength(length);
                    grid.emptyBoard();                
                    break;

                case "start":
                    int x = Integer.parseInt(temp[1]);
                    int y = Integer.parseInt(temp[2]);
                    grid.setStartX(x);
                    grid.setStartY(y);
                    break;

                case "data":
                    initStartIdx = idx + 1;     // to know where to start for the initial configuration
                    idx = lines.size();     // to exit the for loop so that i can go set the config
                    System.out.println("Initializing Game of Life");
                    break;
                default:
                    break;
            }
        }

        // Start the initial configuration
        //      have to recreate the List<string[]> for initial configuration because they were not separated during readfile
        //      since they do not have " " between them --> need to resplit them with ""
        List<String> temp = lines.subList(initStartIdx, lines.size());    
        List<String[]> config = new ArrayList<>();

        for (String line: temp) {
            String[] newStringArray = line.split("");
            config.add(newStringArray);
        }

        // Initialize the board with the initial configuration
        grid.initBoard(config);

        grid.printGrid(0);

        int iterations = 20;   // to be set
        for (int idx = 0; idx < iterations; idx++) {
            grid.interateBoard();
            grid.printGrid(idx + 1);
        }
    }
}
