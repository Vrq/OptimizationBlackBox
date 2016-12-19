package main.util;

import java.util.HashMap;

/**
 * Created by jtomasik on 17/12/2016.
 */
public class Constants {
    private static final HashMap<Enum<FileName>, String> carsDataSet = new HashMap<>();
    private static final HashMap<Enum<FileName>, String> germanCreditDataSet = new HashMap<>();
    private static final HashMap<Enum<FileName>, String> australianCreditDataSet = new HashMap<>();
    public enum FileName {
        ALTERNATIVES, CRITERIA, PERFORMANCE_TABLE, DATASET
    }
    static {
        Constants.carsDataSet.put(FileName.DATASET, "Cars Data Set");
        Constants.carsDataSet.put(FileName.ALTERNATIVES, "carsAlternatives.csv");
        Constants.carsDataSet.put(FileName.CRITERIA, "carsCriteria.csv");
        Constants.carsDataSet.put(FileName.PERFORMANCE_TABLE, "carsPerformanceTable.csv");

        Constants.germanCreditDataSet.put(FileName.DATASET, "German Credit Data Set");
        Constants.germanCreditDataSet.put(FileName.ALTERNATIVES, "germanCreditAlternatives.csv");
        Constants.germanCreditDataSet.put(FileName.CRITERIA, "germanCreditCriteria.csv");
        Constants.germanCreditDataSet.put(FileName.PERFORMANCE_TABLE, "germanCreditPerformanceTable.csv");

        Constants.australianCreditDataSet.put(FileName.DATASET, "Australian Credit Data Set");
        Constants.australianCreditDataSet.put(FileName.ALTERNATIVES, "australianCreditAlternatives.csv");
        Constants.australianCreditDataSet.put(FileName.CRITERIA, "australianCreditCriteria.csv");
        Constants.australianCreditDataSet.put(FileName.PERFORMANCE_TABLE, "australianCreditPerformanceTable.csv");
    }
    public static HashMap<Enum<FileName>, String> getGermanCreditDataSet() {
        return germanCreditDataSet;
    }
    public static HashMap<Enum<FileName>, String> getAustralianCreditDataSet() {
        return australianCreditDataSet;
    }
    public static HashMap<Enum<FileName>, String> getCarsDataSet() {
        return carsDataSet;
    }
}
