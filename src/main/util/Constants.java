package main.util;

import java.util.HashMap;

/**
 * Created by jtomasik on 17/12/2016.
 */
public class Constants {
    private static final HashMap<Enum<FileName>, String> carsDataSet = new HashMap<>();
    private static final HashMap<Enum<FileName>, String> germanCreditDataSet = new HashMap<>();
    public enum FileName {
        ALTERNATIVES, CRITERIA, PERFORMANCE_TABLE, DATASET, PREFERENCE_THRESH, INDIFFERENCE_THRESH, WEIGTHS
    }
    static {
        Constants.carsDataSet.put(FileName.DATASET, "Cars Data Set");
        Constants.carsDataSet.put(FileName.ALTERNATIVES, "carsAlternatives.csv");
        Constants.carsDataSet.put(FileName.CRITERIA, "carsCriteria.csv");
        Constants.carsDataSet.put(FileName.PERFORMANCE_TABLE, "carsPerformanceTable.csv");
        Constants.carsDataSet.put(FileName.PREFERENCE_THRESH, "carsPreferenceThresholds.csv");
        Constants.carsDataSet.put(FileName.INDIFFERENCE_THRESH, "carsIndifferenceThresholds.csv");
        Constants.carsDataSet.put(FileName.WEIGTHS, "carsWeights.csv");

        Constants.germanCreditDataSet.put(FileName.DATASET, "German Credit Data Set");
        Constants.germanCreditDataSet.put(FileName.ALTERNATIVES, "germanCreditAlternatives.csv");
        Constants.germanCreditDataSet.put(FileName.CRITERIA, "germanCreditCriteria.csv");
        Constants.germanCreditDataSet.put(FileName.PERFORMANCE_TABLE, "germanCreditPerformanceTable.csv");
        Constants.germanCreditDataSet.put(FileName.PREFERENCE_THRESH, "germanCreditPreferenceThresholds.csv");
        Constants.germanCreditDataSet.put(FileName.INDIFFERENCE_THRESH, "germanCreditIndifferenceThresholds.csv");
        Constants.germanCreditDataSet.put(FileName.WEIGTHS, "germanCreditWeights.csv");
    }
    public static HashMap<Enum<FileName>, String> getGermanCreditDataSet() {
        return germanCreditDataSet;
    }
    public static HashMap<Enum<FileName>, String> getCarsDataSet() {
        return carsDataSet;
    }
}
