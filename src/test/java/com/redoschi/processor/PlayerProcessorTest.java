package com.redoschi.processor;

import com.redoschi.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerProcessorTest {

    private PlayerProcessor playerProcessor;

    @BeforeEach
    void init() throws IOException{
        this.playerProcessor = new PlayerProcessor();
        StringBuilder sb = new StringBuilder();
        Files.lines(Paths.get("original-rushing.json")).forEach(l -> sb.append(l));

        String input = sb.toString();
        this.playerProcessor.readPlayerList(input);
    }

    @Test
    void readPlayerList() {
        List<Player> playerList = this.playerProcessor.getPlayerList();
        assertEquals(playerList.size(),326);
    }

    @Test
    void filterList() {
        List<Player> playerList = this.playerProcessor.getPlayerList();
        List<Player> filteredList = PlayerProcessor.filterList(playerList,"Mark");
        assertEquals(filteredList.size(),2);

        filteredList = PlayerProcessor.filterList(playerList,"Mat");
        assertEquals(filteredList.size(),13);
    }


    @Test
    void sortTotalYards() {
        List<Player> playerList = this.playerProcessor.sortTotalYards();
        assertEquals(playerList.size(),326);
        assertEquals(playerList.get(0).getName(),"Ezekiel Elliott");
    }

    @Test
    void sortLongestRush() {
        List<Player> playerList = this.playerProcessor.sortLongestRush();
        assertEquals(playerList.size(),326);
        assertEquals(playerList.get(0).getName(),"Isaiah Crowell");
    }

    @Test
    void sortTotalTouchdowns() {
        List<Player> playerList = this.playerProcessor.sortTotalTouchdowns();
        assertEquals(playerList.size(),326);
        assertEquals(playerList.get(0).getName(),"LeGarrette Blount");
    }

}