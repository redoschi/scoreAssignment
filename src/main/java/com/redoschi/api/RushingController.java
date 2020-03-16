package com.redoschi.api;

import com.redoschi.model.Player;
import com.redoschi.processor.PlayerProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;

@RestController
public class RushingController {

    @Autowired
    private PlayerProcessor playerProcessor;

    @RequestMapping(value = "/rushing", method = RequestMethod.GET)
    public List<Player> rushing(
            @RequestParam(value = "sort", defaultValue = "") String sort,
            @RequestParam(value = "filter", defaultValue = "") String filter) {

        List<Player> resultList = processList(sort,filter);

        return resultList;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces="text/plain")
    public String downloadList(
            @RequestParam(value = "sort", defaultValue = "") String sort,
            @RequestParam(value = "filter", defaultValue = "") String filter) {

        List<Player> resultList = processList(sort,filter);

        StringWriter sw = new StringWriter();
        PlayerProcessor.writePlayerList(sw, resultList);

        return sw.toString();
    }

    private List<Player> processList(String sort, String filter){
        List<Player> resultList = null;

        if (StringUtils.isNotBlank(sort)){
            resultList = processSort(sort);
        } else {
            resultList = playerProcessor.copyPlayerList();
        }

        if (StringUtils.isNotBlank(filter)){
            resultList = processFilter(filter, resultList);
        }

        return resultList;
    }

    private List<Player> processSort(String sort){
        List<Player> sortedList = null;

        sort = sort.toLowerCase();
        switch (sort) {
            case "yds":
                sortedList = playerProcessor.sortTotalYards();
                break;
            case "lgn":
                sortedList = playerProcessor.sortLongestRush();
                break;
            case "td":
                sortedList = playerProcessor.sortTotalTouchdowns();
                break;
            default:
                throw new RuntimeException("Please use a valid option for sorting: Yds, Lgn, Td");
        }

        return sortedList;
    }

    private static List<Player> processFilter(String filter, List<Player> fullList){
        List<Player> filteredList = PlayerProcessor.filterList(fullList, filter);
        return filteredList;
    }





}
