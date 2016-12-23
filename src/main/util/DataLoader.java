package main.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Load alternatives, criteria and performance table from a CSV file
 */
public class DataLoader {
    String resourcesDirName;

    public DataLoader(String resourcesDirName) {
        this.resourcesDirName = resourcesDirName;
    }
    public ArrayList<List<String>> getDataMatrixFromCSV(String fileName) {
        ArrayList<List<String>> dataMatrix = new ArrayList<List<String>>();
        String filePath = FileSystems.getDefault().getPath(resourcesDirName, fileName).toAbsolutePath().toString();
        File file = new File(filePath);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String dataLine = inputStream.nextLine();
                String[] dataLineArray = dataLine.split("(,)|(;)", -1);
                List<String> dataArrayList = Arrays.asList(dataLineArray);
                dataMatrix.add(dataArrayList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataMatrix;
    }

    public List<String> getDataListFromCSV(String fileName) {
        List<String> dataArray = new ArrayList<String>();
        String filePath = FileSystems.getDefault().getPath(resourcesDirName, fileName).toAbsolutePath().toString();
        File file = new File(filePath);
        try {
            Scanner inputStream = new Scanner(file);
            if(inputStream.hasNext()) {
                String dataLine = inputStream.nextLine();
                String[] dataLineArray = dataLine.split("(,)|(;)", -1);
                dataArray = Arrays.asList(dataLineArray);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    public ArrayList<List<Double>> getPerformanceTableFromCSV(String fileName) {
        ArrayList<List<Double>> dataMatrix = new ArrayList<List<Double>>();
        String filePath = FileSystems.getDefault().getPath(resourcesDirName, fileName).toAbsolutePath().toString();
        File file = new File(filePath);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String dataLine = inputStream.nextLine();
                String[] dataLineArray = dataLine.split("(,)|(;)", -1);
                List<Double> dataValuesList = new ArrayList<>();
                for(String value : dataLineArray) {
                    dataValuesList.add(Double.parseDouble(value));
                }
                dataMatrix.add(dataValuesList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dataMatrix;
    }


}
