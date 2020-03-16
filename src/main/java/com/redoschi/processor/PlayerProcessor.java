package com.redoschi.processor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redoschi.model.Player;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PlayerProcessor {

    private List<Player> playerList;

    public PlayerProcessor() {
        this.playerList = new ArrayList<>();
    }

    @PostConstruct
    public void init() throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.lines(Paths.get("rushing.json")).forEach(l -> sb.append(l));

        String input = sb.toString();
        this.readPlayerList(input);
    }

    public void readPlayerList(String input) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            this.playerList = mapper.readValue(input, new TypeReference<List<Player>>(){});
        } catch (Exception e){
            System.out.println("Error reading input into Player list: " + e.getMessage() + "\n Stacktrace:\n" + e.getStackTrace());
        }
    }

    public static void writePlayerList(Writer writer, List<Player> playerList){
        PrintWriter pw = new PrintWriter(writer);
        pw.println(Player.CSV_HEADERS);
        for(Player player : playerList){
            pw.print(player.csvLine() + "\n");
        }
        pw.close();
    }

    public static List<Player> filterList(List<Player> fullList, String filter) {
        List<Player> filteredList = fullList.stream().filter(p -> StringUtils.containsIgnoreCase(p.getName(),filter)).collect(Collectors.toList());
        return filteredList;
    }

    public List<Player> copyPlayerList(){
        List<Player> list = new ArrayList<>();
        list.addAll(this.playerList);
        return list;
    }

    public List<Player> sortTotalYards(){
        List<Player> sortedList = copyPlayerList();
        Collections.sort(sortedList, Comparator.comparing(Player::getCleanTotalYards).reversed());
        return sortedList;
    }

    public List<Player> sortLongestRush(){
        List<Player> sortedList = copyPlayerList();
        Collections.sort(sortedList, Comparator.comparing(Player::getCleanLongestRush).reversed());
        return sortedList;
    }

    public List<Player> sortTotalTouchdowns(){
        List<Player> sortedList = copyPlayerList();
        Collections.sort(sortedList, Comparator.comparing(Player::getTd).reversed());
        return sortedList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
