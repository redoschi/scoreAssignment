package com.redoschi;

import com.redoschi.model.Player;
import com.redoschi.processor.PlayerProcessor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Please provide the path to the input json file as the first and only parameter.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.lines(Paths.get(args[0])).forEach(l -> sb.append(l));
        } catch (Exception e){
            System.out.println("Error opening file. Used argument: + " + args[0] + ".\n" + e.getStackTrace());
            return;
        }

        PlayerProcessor playerProcessor = new PlayerProcessor();

        playerProcessor.readPlayerList(sb.toString());

        if (playerProcessor.getPlayerList().size() != 0){
            System.out.println("Player List loaded successfully with " + playerProcessor.getPlayerList().size() + " players.");
        } else {
            System.out.println("Player List loaded with no players.");
        }

        String outputFilename = "output";

        Scanner scanner = new Scanner(System.in);

        boolean sort = shouldProcess(scanner,"sort");
        List<Player> playerList = null;
        if(sort){
            playerList = processSort(scanner,playerProcessor);
            outputFilename = outputFilename + "-sorted";
        } else {
            playerList = playerProcessor.copyPlayerList();
        }

        boolean filter = shouldProcess(scanner,"filter");
        if(filter){
            playerList = processFilter(scanner,playerList);
            outputFilename = outputFilename + "-filtered";
        }

        try {
            outputFilename = outputFilename + ".csv";
            FileWriter fw = new FileWriter(outputFilename);
            playerProcessor.writePlayerList(fw,playerList);
            fw.close();
            System.out.println("Output file saved to " + outputFilename);

        } catch (IOException e1){
            System.out.println("Failure saving output file. \n" + e1.getStackTrace());
            return;
        }
    }

    private static boolean shouldProcess(Scanner scanner, String processName){
        boolean process = false;
        boolean decision = false;

        System.out.println("Do you want to " + processName + " the provided list? Y/N");
        while (!decision) {
            String input = scanner.nextLine();
            switch (input) {
                case "Yes":
                case "yes":
                case "Y":
                case "y":
                    process = true;
                    decision = true;
                    break;
                case "No":
                case "no":
                case "N":
                case "n":
                    process = false;
                    decision = true;
                    break;
                default:
                    System.out.println("Invalid input. Do you want to " + processName + " the provided list? Y/N");
                    decision = false;
            }
        }
        return process;
    }

    private static List<Player> processSort(Scanner scanner, PlayerProcessor playerProcessor){
        List<Player> sortedList = null;
        boolean decision = false;

        System.out.println("Choose one of the following options for sorting:");
        System.out.println("1 - Total Rushing Yards (Yds)");
        System.out.println("2 - Longest Rush (Lgn)");
        System.out.println("3 - Total Rushing Touchdowns (Td)");
        while (!decision) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                case "Yds":
                    sortedList = playerProcessor.sortTotalYards();
                    decision = true;
                    break;
                case "2":
                case "Lgn":
                    sortedList = playerProcessor.sortLongestRush();
                    decision = true;
                    break;
                case "3":
                case "Td":
                    sortedList = playerProcessor.sortTotalTouchdowns();
                    decision = true;
                    break;
                default:
                    System.out.println("Invalid input. Choose one of the following options for sorting:");
                    System.out.println("1 - Total Rushing Yards (Yds)");
                    System.out.println("2 - Longest Rush (Lgn)");
                    System.out.println("3 - Total Rushing Touchdowns (Td)");
                    decision = false;
            }
        }
        return sortedList;
    }

    private static List<Player> processFilter(Scanner scanner, List<Player> fullList){
        System.out.println("Please type a part of the player name to filter for players:");
        String filter = scanner.nextLine();

        List<Player> filteredList = PlayerProcessor.filterList(fullList, filter);
        return filteredList;
    }



}
