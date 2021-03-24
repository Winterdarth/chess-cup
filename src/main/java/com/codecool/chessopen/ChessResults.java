package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String> competitors = new ArrayList<>();
        List<String[]> results = new ArrayList<>();
        Map<String, Integer> sortCompetitors = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));){
            String tempData = "";

            while ((tempData = bufferedReader.readLine()) != null) {
                competitors.add(tempData);
            }

            for (String competitor : competitors) {
                results.add(competitor.split(","));
            }

            for (String[] result : results) {
                Integer maxPoints;
                int firstMatch = Integer.parseInt(result[1]);
                int secondMatch = Integer.parseInt(result[2]);
                int thirdMatch = Integer.parseInt(result[3]);
                int fourthMatch = Integer.parseInt(result[4]);
                int fifthMatch = Integer.parseInt(result[5]);

                maxPoints = firstMatch + secondMatch + thirdMatch + fourthMatch + fifthMatch;

                sortCompetitors.put(result[0], maxPoints);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> orderedCompetitors = new ArrayList<>();
        sortCompetitors.entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(nameAvgGradePair -> orderedCompetitors.add(nameAvgGradePair.getKey()));

        Collections.reverse(orderedCompetitors);
        return orderedCompetitors;
    }
}
