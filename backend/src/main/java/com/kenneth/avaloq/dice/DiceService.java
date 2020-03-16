package com.kenneth.avaloq.dice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("diceService")
public class DiceService {

    @Autowired
    private DiceRepository repository;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public DiceResponse rollDice(DiceRequest diceRequest) {
        if (diceRequest.getDiceCount() < 1
                || diceRequest.getDiceRollCount() < 1
                || diceRequest.getDiceFaces() < 4) {
            throw new RuntimeException("Invalid parameters!");
        }
        DiceResponse response = new DiceResponse();
        Map<Integer, Integer> sumResult = new HashMap<>();
        for (int ctr = diceRequest.getDiceRollCount(); ctr > 0; ctr--) {
            int sum = getDiceSum(diceRequest.getDiceFaces(), diceRequest.getDiceCount());
            int count = sumResult.get(sum) == null ? 1 : sumResult.get(sum) + 1;
            sumResult.put(sum, count);
        }
        response.setSumTotal(sumResult);
        DiceSummary diceSummary = new DiceSummary();
        diceSummary.setDiceRequest(diceRequest);
        diceSummary.setDiceResponse(response);
        repository.save(diceSummary);
        return response;
    }

    public Map<DiceRequest, SimulationResponse> getSimulationResponse() {
        Map<DiceRequest, SimulationResponse> response = new HashMap<>();
        List<DiceSummary> summaries = repository.findAll();
        for (DiceSummary summary : summaries) {
            SimulationResponse simulationResponse = new SimulationResponse();
            int totalRollCount = summary.getDiceRequest().getDiceRollCount();
            int simulationCount = response.get(summary.getDiceRequest()) == null
                    ? 1 : response.get(summary.getDiceRequest()).getSimulationCount() + 1;
            simulationResponse.setTotalRollCount(totalRollCount);
            simulationResponse.setSimulationCount(simulationCount);
            response.put(summary.getDiceRequest(), simulationResponse);
        }
        return response;
    }

    public Map<Integer, String> getRolledSumPercentage() {
        Map<Integer, Integer> tempResponse = new HashMap<>();
        List<DiceSummary> summaries = repository.findAll();
        int rollCount = 0;
        for (DiceSummary summary : summaries) {
            for (Map.Entry<Integer, Integer> entry : summary.getDiceResponse().getSumTotal().entrySet()){
                int count = tempResponse.get(entry) == null ? entry.getValue()
                        : tempResponse.get(entry) + entry.getValue() ;
                tempResponse.put(entry.getKey(), count);
            }
            rollCount += summary.getDiceRequest().getDiceRollCount();
        }
        Map<Integer, String> response = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : tempResponse.entrySet()){
            double percentage = ((double)entry.getValue() / rollCount) * 100;
            response.put(entry.getKey(), df2.format(percentage));
        }
        return response;
    }

    private Integer getDiceSum(int faces, int count) {
        int sum = 0;
        for (int ctr = count; ctr > 0; ctr--) {
            Random rand = new Random();
            sum += rand.nextInt((faces - 1) + 1) + 1;
        }
        return sum;
    }
}
