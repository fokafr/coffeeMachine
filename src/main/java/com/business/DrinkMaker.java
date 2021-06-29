package com.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrinkMaker {

    final static String CONTENT_MESSAGE = "M:message-content";
    final static double AMONT_TEE = 0.4;
    final static double AMONT_COFFEE = 0.6;
    final static double AMONT_CHOCOLATE = 0.5;
    final static double AMONT_ORANGE_JUICE = 0.6;

    public static Boolean getInstruction(String choice) {
        boolean flag = false;
        for (Drink drink : Drink.values()) {
            if (drink.toString().equalsIgnoreCase(choice)) flag = true;
        }
        return flag;
    }

    public static String forwardMessage(String drinkChoice, int numberOfSugar) {
        String messageToForward = null;
        StringBuilder messageToForwardSb = new StringBuilder("");
        String stik = "0";
        int compteur = 0;
        for (Drink drink : Drink.values()) {
            if (drink.toString().equals(drinkChoice)) {
                compteur++;
                if (drink.toString().equals("CHOCOLATE")) messageToForwardSb.append(drink.toString().substring(1, 2));
                else messageToForwardSb.append(drink.toString().substring(0, 1));
                messageToForwardSb.append(":");
                if (drink.toString().equals("ORANGE_JUICE")) numberOfSugar = 0;
                if (numberOfSugar > 0) {
                    messageToForwardSb.append(String.valueOf(numberOfSugar));
                    messageToForwardSb.append(":");
                    messageToForwardSb.append(stik);

                } else messageToForwardSb.append(":");
            }

        }

        if (compteur > 0) return messageToForwardSb.toString();
        else return "M:message-content";
    }

    public static String makeDrink(String drinkChoosen, double amountGiven) {
        Map<String, Double> drinksToChoise = new HashMap<>();
        drinksToChoise = loadDrinksMap();
        String messageToSend = null;
        for (String i : drinksToChoise.keySet()) {
            if (drinkChoosen.equals(i) && amountGiven == drinksToChoise.get(i)) {
                messageToSend = "ok your drink start making...";
            } else if (drinkChoosen.equals(i) && amountGiven < drinksToChoise.get(i)) {
                double missingMoney = amountGiven - drinksToChoise.get(i);
                StringBuilder stringBuilder = new StringBuilder("");
                stringBuilder.append("missing amont of:");
                stringBuilder.append(String.valueOf(missingMoney));
                messageToSend = stringBuilder.toString();
            }
        }

        return messageToSend;
    }

    private static Map<String, Double> loadDrinksMap() {
        Map<String, Double> drinksMap = new HashMap<>();
        drinksMap.put(Drink.TEE.toString(), AMONT_TEE);
        drinksMap.put(Drink.COFFEE.toString(), AMONT_COFFEE);
        drinksMap.put(Drink.CHOCOLATE.toString(), AMONT_CHOCOLATE);
        drinksMap.put(Drink.ORANGE_JUICE.toString(), AMONT_ORANGE_JUICE);
        return drinksMap;
    }

    public static String forwardMessageToMaker(String dringChoosed, int numberOfSugar, Boolean isHot) {
        StringBuilder forwardedMessage = new StringBuilder("");
        forwardedMessage.append(forwardMessage(dringChoosed, numberOfSugar));
        if (isHot) forwardedMessage.insert(1, "h");
        return forwardedMessage.toString();
    }

}
