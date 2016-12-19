package main.alg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Varq on 2016-12-15.
 */
public class PrometheeIIMethod {
    ArrayList<List<Double>> performanceTable;
    List<String> criteriaList;
    List<String> alternativesList;
    HashMap<String, Double> criteriaWeightsMap;
    HashMap<String, Double> preferenceThresholdValuesMap;
    HashMap<String, Double> indifferenceThresholdValuesMap;
    double weightsSumValue;

    public PrometheeIIMethod(List<String> criteriaList, List<String> alternativesList, ArrayList<List<Double>> performanceTable) {
        this.criteriaList = criteriaList;
        this.alternativesList = alternativesList;
        this.performanceTable = performanceTable;
    }
    public void setCriteriaWeightsMap(HashMap<String, Double> criteriaWeightsMap) {
        this.criteriaWeightsMap = criteriaWeightsMap;
        weightsSumValue = criteriaWeightsMap.values().stream().mapToDouble(Double::doubleValue).sum();
    }
    public void setPreferenceThresholdValuesMap(HashMap<String, Double> preferenceThresholdValuesMap) {
        this.preferenceThresholdValuesMap = preferenceThresholdValuesMap;
    }
    public void setIndifferenceThresholdValuesMap(HashMap<String, Double> indifferenceThresholdValuesMap) {
        this.indifferenceThresholdValuesMap = indifferenceThresholdValuesMap;
    }
    double getPreferenceValueForCriterion(String alternativeX, String alternativeY, String criterion) {
        int alternativeXIndex = alternativesList.indexOf(alternativeX);
        int alternativeYIndex = alternativesList.indexOf(alternativeY);
        int criterionIndex = criteriaList.indexOf(criterion);
        double critPreferenceThreshold = preferenceThresholdValuesMap.get(criterion);
        double critIndifferenceThreshold = indifferenceThresholdValuesMap.get(criterion);
        double critValueForAltX = performanceTable.get(alternativeXIndex).get(criterionIndex);
        double critValueForAltY = performanceTable.get(alternativeYIndex).get(criterionIndex);

        if(critValueForAltX - critValueForAltY > critPreferenceThreshold) {
            return 1;
        } else if(critValueForAltX - critValueForAltY > critIndifferenceThreshold) {
            return (critValueForAltX - critValueForAltY - critIndifferenceThreshold)/(critPreferenceThreshold - critIndifferenceThreshold);
        } else {
            return 0;
        }
    }

    double getPreferenceValueFor(String alternative, String alternativeY) {
        double preferenceSum = 0;
        for(String criterion : criteriaList) {
            preferenceSum += criteriaWeightsMap.get(criterion)*getPreferenceValueForCriterion(alternative, alternativeY, criterion);
        }
        return preferenceSum/weightsSumValue;
    }

    double getPositiveFlowValueFor(String alternative) {
        double positiveFlow = 0;
        for(String alternativeY : alternativesList) {
            positiveFlow += getPreferenceValueFor(alternative, alternativeY);
        }
        return positiveFlow;
    }

    double getNegativeFlowFor(String alternative) {
        double negativeFlow = 0;
        for(String alternativeY : alternativesList) {
            negativeFlow += getPreferenceValueFor(alternativeY, alternative);
        }
        return negativeFlow;
    }

    double getNetFlowFor(String alternativeX) {
        return getPositiveFlowValueFor(alternativeX) - getNegativeFlowFor(alternativeX);
    }

    public LinkedHashMap<String, Double> getAlternativesRanking()  {
        HashMap<String, Double> alternativesNetFlowValuesMap = new HashMap<>();
        for(String alternative : alternativesList) {
            alternativesNetFlowValuesMap.put(alternative, getNetFlowFor(alternative));
        }
        LinkedHashMap<String, Double> sortedAlternativesMap = sortMapByValue(alternativesNetFlowValuesMap);
        return sortedAlternativesMap;
    }

    public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortMapByValue(HashMap<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
