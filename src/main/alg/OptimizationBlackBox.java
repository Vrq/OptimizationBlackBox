package main.alg;

import main.util.DataLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varq on 2016-12-14.
 */
public class OptimizationBlackBox {
    ArrayList<List<String>> performanceTable;
    List<String> criteriaList;
    List<String> alternativesList;

    public OptimizationBlackBox(String resourcesDir, String performanceTableFileName, String criteriaFileName, String alternativesFileName) {
        DataLoader dataLoader = new DataLoader(resourcesDir);
        performanceTable = dataLoader.getDataMatrixFromCSV(performanceTableFileName);
        criteriaList = dataLoader.getDataListFromCSV(criteriaFileName);
        alternativesList = dataLoader.getDataListFromCSV(alternativesFileName);
    }
    public void printPerformanceTable() {
        for(List<String> valuesList : performanceTable) {
            System.out.println(valuesList);
        }
    }
    public void printCriteriaList() {
        for(String criterion : criteriaList) {
            System.out.println(criterion);
        }
    }
    public void printAlternativesList() {
        for(String alternative : alternativesList) {
            System.out.println(alternative);
        }
    }

    public void performPrometheeIIMethod(double preferenceTreshold, double vetoTreshhold) {

    }
}
