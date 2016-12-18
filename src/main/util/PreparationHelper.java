package main.util;

import main.alg.PrometheeIIMethod;

import java.util.*;

/**
 * Created by jtomasik on 17/12/2016.
 */
public class PreparationHelper {
    static DefaultConstants constants = new DefaultConstants();
    static ArrayList<List<Double>> performanceTable;
    static List<String> criteriaList;
    static List<String> alternativesList;
    static String dataSetName;
    static PrometheeIIMethod prometheeIIMethod;

    public static ArrayList<List<Double>> getPerformanceTable() {
        if(performanceTable != null) {
            return performanceTable;
        }
        throw new NullPointerException();
    }

    public static List<String> getCriteriaList() {
        if(criteriaList != null) {
            return criteriaList;
        }
        throw new NullPointerException();
    }

    public static List<String> getAlternativesList() {
        if(alternativesList != null) {
            return alternativesList;
        }
        throw new NullPointerException();
    }


    public static void showMeTheMagic() {
        loadDefaultData();
        performExamplePrometheeIIRun();
    }

    public static void loadDefaultData() {
        DataLoader dataLoader = new DataLoader(constants.getResourcesDir());
        PreparationHelper.alternativesList = dataLoader.getDataListFromCSV(constants.getAlternativesFileName());
        PreparationHelper.criteriaList = dataLoader.getDataListFromCSV(constants.getCriteriaFileName());
        PreparationHelper.performanceTable = dataLoader.getPerformanceTableFromCSV(constants.getPerformanceTableFileName());
    }

    public static void loadDataSet(Enum dataSet) {
//        PreparationHelper.dataSetName = dataSet.DATA_SET_NAME;
//        DataLoader dataLoader = new DataLoader(constants.getResourcesDir());
//        PreparationHelper.alternativesList = dataLoader.getDataListFromCSV(dataSet.ALTERNATIVES_FILE_NAME);
//        PreparationHelper.criteriaList = dataLoader.getDataListFromCSV(dataSet.CRITERIA_FILE_NAME);
//        PreparationHelper.performanceTable = dataLoader.getPerformanceTableFromCSV(dataSet.PERFORMANCE_TABLE_FILE_NAME);
//
    }

    public static void printLoadedData() {
        System.out.println("Current loaded data is shown below: ");
        System.out.println("Data set: " + dataSetName);
        System.out.println("\nAlternatives (rows):");
        for(String alternative : alternativesList) {
            System.out.println(alternative);
        }
        System.out.println("\nCriteria (columns):");
        for(String criterion : criteriaList) {
            System.out.println(criterion);
        }
        System.out.println("\nPerformance table:");
        for(List<Double> performanceTableLine : performanceTable) {
            System.out.println(performanceTableLine);
        }
    }

    public static void performExamplePrometheeIIRun() {
        constants.setCriteriaWeightsMap(criteriaList);
        constants.setIndifferenceThresholdValuesMap(criteriaList);
        constants.setPreferenceThresholdValuesMap(criteriaList);

        PrometheeIIMethod prometheeIIMethodInstance = new PrometheeIIMethod(criteriaList, alternativesList, performanceTable);

        prometheeIIMethodInstance.setCriteriaWeightsMap(constants.getCriteriaWeightsMap());
        prometheeIIMethodInstance.setIndifferenceThresholdValuesMap(constants.getIndifferenceThresholdValuesMap());
        prometheeIIMethodInstance.setPreferenceThresholdValuesMap(constants.getPreferenceThresholdValuesMap());

        LinkedHashMap<String, Double> prometheeIIRankingMap = prometheeIIMethodInstance.getAlternativesRanking();

        for (Map.Entry<String, Double> alternativeEntry : prometheeIIRankingMap.entrySet()) {
            System.out.println("Alternative: " + alternativeEntry.getKey() + " netflow: " + alternativeEntry.getValue());
        }
    }

    public static void preparePrometheeIIRun(HashMap<String, Double> criteriaWeighthsMap, HashMap<String, Double> preferenceThresholdValuesMap, HashMap<String, Double> indifferenceThresholdValuesMap) {
        prometheeIIMethod = new PrometheeIIMethod(criteriaList, alternativesList, performanceTable);
        prometheeIIMethod.setCriteriaWeightsMap(criteriaWeighthsMap);
        prometheeIIMethod.setPreferenceThresholdValuesMap(preferenceThresholdValuesMap);
        prometheeIIMethod.setIndifferenceThresholdValuesMap(indifferenceThresholdValuesMap);
    }

    public static void runPrometheeIIMethod() {
        LinkedHashMap<String, Double> prometheeIIRankingMap  = prometheeIIMethod.getAlternativesRanking();
        for (Map.Entry<String, Double> alternativeEntry : prometheeIIRankingMap.entrySet()) {
            System.out.println("Alternative: " + alternativeEntry.getKey() + " netflow: " + alternativeEntry.getValue());
        }
    }
}
