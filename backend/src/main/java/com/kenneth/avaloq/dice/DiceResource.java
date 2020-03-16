package com.kenneth.avaloq.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dice")
public class DiceResource {

    @Autowired private DiceService diceService;

    @ResponseBody
    @RequestMapping(value = "/rollDice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public DiceResponse rollDice(@RequestBody DiceRequest diceRequest) {
        return diceService.rollDice(diceRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/getSimulationDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<DiceRequest, SimulationResponse> getSimulationDetails() {
        return diceService.getSimulationResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/getRolledSumPercentage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, String> getRolledSumPercentage() {
        return diceService.getRolledSumPercentage();
    }
}
