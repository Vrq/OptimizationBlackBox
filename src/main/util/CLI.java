package main.util;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by jtomasik on 17/12/2016.
 *
 * Command Line Interface for the Optimization Black Box Tool:
 */
public class CLI {
    public static void startInterface() {
        printStartMessage();
        Scanner userInput = new Scanner(System.in);
        do {
            switch (userInput.nextLine()) {
                case "1":
                    performExamplePrometheeRun();
                    break;
                case "2":
                    performCustomizedPrometheeRun(userInput);
                    break;
                default:
                    System.out.println("Unknown command, please try again");
            }
            printCommandMenu();
        } while (userInput.hasNext());
    }

    private static void printCommandMenu() {
        System.out.println("\nBelow you can find the available commands:");
        System.out.println("1 - Example optimization process");
        System.out.println("2 - Custom optimization with own data sources");
        System.out.println("\nChoose your command and hit enter:");
    }

    private static void performCustomizedPrometheeRun(Scanner userInput) {
        long startTime;
        long stopTime;
        double elapsedTimeSec;
        chooseDataSetFrom(userInput);
        PreparationHelper.validateInputData();
        PreparationHelper.printLoadedData();
        System.out.println("\nData set successfully loaded");
        getPrometheeIIParamsFromUser();
        startTime = System.currentTimeMillis();
        System.out.println("\nPerforming Promethee II method in progress...");
        PreparationHelper.runPrometheeIIMethod();
        stopTime = System.currentTimeMillis();
        elapsedTimeSec = (stopTime - startTime)/1000d;
        System.out.println("\nPromethee II executed in: " + elapsedTimeSec + "s");
    }

    private static void performExamplePrometheeRun() {
        PreparationHelper.loadDefaultData();
        PreparationHelper.printLoadedData();
        long startTime = System.currentTimeMillis();
        System.out.println("\nPerforming Promethee II method in progress...");
        PreparationHelper.performExamplePrometheeIIRun();
        long stopTime = System.currentTimeMillis();
        double elapsedTimeSec = (stopTime - startTime)/1000d;
        System.out.println("\nPromethee II executed in: " + elapsedTimeSec + "s");
    }

    private static void getPrometheeIIParamsFromUser() {
        Scanner userInputStream = new Scanner(System.in);
        System.out.println("\nDo you want to set the weights, preference and indifference thresholds manually? (Y/N)");
        if(!userInputStream.nextLine().equals("Y")) {
            return;
        }
        HashMap<String, Double> criteriaWeightsMap = new HashMap<>();
        HashMap<String, Double> preferenceThresholdValuesMap = new HashMap<>();
        HashMap<String, Double> indifferenceThresholdValuesMap = new HashMap<>();

        setWeightsFrom(userInputStream, criteriaWeightsMap);
        setPreferenceAndIndifferenceThresholdsFrom(userInputStream, preferenceThresholdValuesMap, indifferenceThresholdValuesMap);
        PreparationHelper.preparePrometheeIIRun(criteriaWeightsMap, preferenceThresholdValuesMap, indifferenceThresholdValuesMap);
    }

    private static void setWeightsFrom(Scanner userInputStream, HashMap<String, Double> criteriaWeightsMap) {
        System.out.println("\nSetting weights for criteria:");
        for (String criterion : PreparationHelper.getCriteriaList()) {
            System.out.println("How important is " + criterion + " (1 to 10)?");
            if (userInputStream.hasNext()) {
                criteriaWeightsMap.put(criterion, userInputStream.nextDouble());
                userInputStream.nextLine();
            }
        }
    }

    private static void setPreferenceAndIndifferenceThresholdsFrom(Scanner userInputStream, HashMap<String, Double> preferenceThresholdValuesMap, HashMap<String, Double> indifferenceThresholdValuesMap) {
        System.out.println("\nSetting indifference and preference thresholds");
        for (String criterion : PreparationHelper.getCriteriaList()) {
            System.out.println("\nUp to which value of the difference for  " + criterion + " the alternatives are considered equal?");
            if (userInputStream.hasNext()) {
                indifferenceThresholdValuesMap.put(criterion, userInputStream.nextDouble());
                userInputStream.nextLine();
            }
            System.out.println("\nFrom which value of the difference for  " + criterion + " one alternative is strong preferable?");
            if (userInputStream.hasNext()) {
                preferenceThresholdValuesMap.put(criterion, userInputStream.nextDouble());
                userInputStream.nextLine();
            }
        }
    }
    private static void printStartMessage() {
        System.out.println("Welcome to OptimizationBlackBox - the ultimate multi-criteria optimization toolbox");
        System.out.println("Below you can find the available commands:");
        System.out.println("1 - Example optimization process");
        System.out.println("2 - Custom optimization with own data sources");
        System.out.println("\nChoose your command and hit enter:");
    }
    private static void chooseDataSetFrom(Scanner userInput) {
        System.out.println("Choose the data set to be used:");
        System.out.println("1 - Cars data set");
        System.out.println("2 - Germany credit data set");
        System.out.println("0 - Back to main menu");
        System.out.println("\nChoose a data set number and hit enter:");
        int chosenNumber = userInput.nextInt();
        while (chosenNumber != 1 && chosenNumber != 2 && chosenNumber != 3 && chosenNumber != 0) {
            userInput.nextLine();
            chosenNumber = userInput.nextInt();
            System.out.println("Please choose a valid number");
        }
        switch (chosenNumber) {
            case 1:
                PreparationHelper.loadDataSet(Constants.getCarsDataSet());
                break;
            case 2:
                PreparationHelper.loadDataSet(Constants.getGermanCreditDataSet());
                break;
            default:
                System.out.println("Back to main menu");
        }
    }

}
