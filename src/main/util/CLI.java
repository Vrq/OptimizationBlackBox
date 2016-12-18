package main.util;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by jtomasik on 17/12/2016.
 * <p>
 * Command Line Interface for the Optimization Black Box Tool:
 */
public class CLI {
    public static void startInterface() {
        System.out.println("Welcome to OptimizationBlackBox - the ultimate multi-criteria optimization toolbox");
        System.out.println("Below you can find the available commands:");
        System.out.println("1 - Example optimization process");
        System.out.println("2 - Custom optimization with own data sources");
        System.out.println("3 - Custom optimization with default data sources");
        System.out.println("4 - Bananas");
        System.out.println("\nChoose your command and hit enter:");
        Scanner userInput = new Scanner(System.in);
        do {
            switch (userInput.nextLine()) {
                case "1":
                    PreparationHelper.showMeTheMagic();
                    break;
                case "2":
                    System.out.println("Get the premium account in order to perform custom optimization with own data sources");
                    break;
                case "3":
                    PreparationHelper.loadDefaultData();
                    PreparationHelper.printLoadedData();
                    getPrometheeIIParamsFromUser();
                    PreparationHelper.runPrometheeIIMethod();
                    break;
                case "4":
                    System.out.println("Hooray! Bananas!!");
                    break;
                default:
                    System.out.println("Unknown command, please try again");
            }
            System.out.println("\nChoose your command and hit enter:");
        } while (userInput.hasNext());
    }

    private static void getPrometheeIIParamsFromUser() {
        Scanner userInputStream = new Scanner(System.in);
        HashMap<String, Double> criteriaWeightsMap = new HashMap<>();
        HashMap<String, Double> preferenceThresholdValuesMap = new HashMap<>();
        HashMap<String, Double> indifferenceThresholdValuesMap = new HashMap<>();

        System.out.println("\nSetting weights for criteria:");
        for (String criterion : PreparationHelper.getCriteriaList()) {
            System.out.println("How important is " + criterion + " (1 to 10)?");
            if(userInputStream.hasNext()) {
                criteriaWeightsMap.put(criterion, userInputStream.nextDouble());
                userInputStream.nextLine();
            }
        }
        System.out.println("\nSetting indifference and preference thresholds");
        for (String criterion : PreparationHelper.getCriteriaList()) {
            System.out.println("Up to which value of the difference for  " + criterion + " the alternatives are considered equal?");
            if(userInputStream.hasNext()) {
                indifferenceThresholdValuesMap.put(criterion, userInputStream.nextDouble());
                userInputStream.nextLine();
            }
            System.out.println("\nFrom which value of the difference for  " + criterion + " one alternative is strong preferable?");
            if(userInputStream.hasNext()) {
                preferenceThresholdValuesMap.put(criterion, userInputStream.nextDouble());
                userInputStream.nextLine();
            }
        }
        PreparationHelper.preparePrometheeIIRun(criteriaWeightsMap, preferenceThresholdValuesMap, indifferenceThresholdValuesMap);
    }

}
