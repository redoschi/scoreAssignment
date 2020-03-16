package com.redoschi.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PlayerTest {

    private static final String PLAYER = "{\n" +
            "    \"Player\":\"Joe Banyard\",\n" +
            "    \"Team\":\"JAX\",\n" +
            "    \"Pos\":\"RB\",\n" +
            "    \"Att\":2,\n" +
            "    \"Att/G\":0.5,\n" +
            "    \"Yds\":7,\n" +
            "    \"Avg\":3.5,\n" +
            "    \"Yds/G\":7,\n" +
            "    \"TD\":4,\n" +
            "    \"Lng\":\"7T\",\n" +
            "    \"1st\":1,\n" +
            "    \"1st%\":0.2,\n" +
            "    \"20+\":2,\n" +
            "    \"40+\":3,\n" +
            "    \"FUM\":4\n" +
            "  }";

    private static final String PLAYER2 = "{\n" +
            "    \"Player\":\"LeSean McCoy\",\n" +
            "    \"Team\":\"BUF\",\n" +
            "    \"Pos\":\"RB\",\n" +
            "    \"Att\":234,\n" +
            "    \"Att/G\":15.6,\n" +
            "    \"Yds\":\"1,267\",\n" +
            "    \"Avg\":5.4,\n" +
            "    \"Yds/G\":84.5,\n" +
            "    \"TD\":13,\n" +
            "    \"Lng\":\"75T\",\n" +
            "    \"1st\":55,\n" +
            "    \"1st%\":23.5,\n" +
            "    \"20+\":11,\n" +
            "    \"40+\":3,\n" +
            "    \"FUM\":3\n" +
            "  }";

    @Test
    public void testSerialize (){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Player player = mapper.readValue(PLAYER, Player.class);

            assertEquals(player.getName(), "Joe Banyard");
            assertEquals(player.getLng(), "7T");
            assertEquals(player.getCleanLongestRush(), 7);
        } catch (Exception e){
            fail(e);
        }
    }

    @Test
    public void testSerialize2 (){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Player player2 = mapper.readValue(PLAYER2, Player.class);
            assertEquals(player2.getName(), "LeSean McCoy");
            assertEquals(player2.getYds(), "1,267");
            assertEquals(player2.getCleanTotalYards(), 1267);
            assertEquals(player2.getLng(), "75T");
            assertEquals(player2.getCleanLongestRush(), 75);
        } catch (Exception e){
            fail(e);
        }
    }

    @Test
    public void testSerializeSimpleList(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = "[" + PLAYER + "," + PLAYER2 + "]";
            List<Player> playerList = null;
            try {
                playerList = mapper.readValue(json, new TypeReference<List<Player>>(){});
            } catch (Exception e){
                fail(e);
            }
            Player player = playerList.get(0);
            assertEquals(player.getName(), "Joe Banyard");
            assertEquals(player.getLng(), "7T");
            assertEquals(player.getCleanLongestRush(), 7);

            Player player2 = playerList.get(1);
            assertEquals(player2.getName(), "LeSean McCoy");
            assertEquals(player2.getCleanTotalYards(), 1267);
            assertEquals(player2.getYds(), "1,267");
            assertEquals(player2.getLng(), "75T");
            assertEquals(player2.getCleanLongestRush(), 75);
        } catch (Exception e){
            fail(e);
        }
    }
}