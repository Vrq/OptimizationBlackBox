package main.util;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Varq on 2016-12-16.
 */
public class DefaultConstants {
    String resourcesDir = "resources/";
    String performanceTableFileName = "performanceTable.csv";
    String criteriaFileName = "criteria.csv";
    String alternativesFileName = "alternatives.csv";

    HashMap<String, Double> criteriaWeightsMap = new HashMap<>();
    HashMap<String, Double> preferenceThresholdValuesMap = new HashMap<>();
    HashMap<String, Double> indifferenceThresholdValuesMap = new HashMap<>();

    double generalPreferenceThreshold = 1;
    double generalIndifferenceTreshold = 0;
    double generalWeight = 2;

    public void setCriteriaWeightsMap(List<String> criteriaList) {
        for(String criterion : criteriaList) {
            criteriaWeightsMap.put(criterion, generalWeight);
        }
    }

    public void setPreferenceThresholdValuesMap(List<String> criteriaList) {
        for(String criterion : criteriaList) {
            preferenceThresholdValuesMap.put(criterion, generalPreferenceThreshold);
        }
    }

    public void setIndifferenceThresholdValuesMap(List<String> criteriaList) {
        for(String criterion : criteriaList) {
            indifferenceThresholdValuesMap.put(criterion, generalIndifferenceTreshold);
        }
    }

    public HashMap<String, Double> getPreferenceThresholdValuesMap() {
        return preferenceThresholdValuesMap;
    }

    public HashMap<String, Double> getCriteriaWeightsMap() {
        return criteriaWeightsMap;
    }

    public HashMap<String, Double> getIndifferenceThresholdValuesMap() {
        return indifferenceThresholdValuesMap;
    }

    public String getResourcesDir() {
        return resourcesDir;
    }

    public String getPerformanceTableFileName() {
        return performanceTableFileName;
    }

    public String getCriteriaFileName() {
        return criteriaFileName;
    }

    public String getAlternativesFileName() {
        return alternativesFileName;
    }
}
