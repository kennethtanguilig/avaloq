package com.kenneth.avaloq.dice;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diceSummary")
public class DiceSummary {

    DiceRequest diceRequest;
    DiceResponse diceResponse;

    public DiceRequest getDiceRequest() {
        return diceRequest;
    }

    public void setDiceRequest(DiceRequest diceRequest) {
        this.diceRequest = diceRequest;
    }

    public DiceResponse getDiceResponse() {
        return diceResponse;
    }

    public void setDiceResponse(DiceResponse diceResponse) {
        this.diceResponse = diceResponse;
    }
}
