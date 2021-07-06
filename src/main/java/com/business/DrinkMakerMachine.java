package com.business;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.business.Drink.*;

public class DrinkMakerMachine {
    final static double AMONT_TEE = 0.4;
    final static double AMONT_COFFEE = 0.6;
    final static double AMONT_CHOCOLATE = 0.5;
    final static double AMONT_ORANGE_JUICE = 0.6;
    private static List<DrinkDesc> drinks = new ArrayList<>();
    private static List<DrinkDesc> drinksWithPrice = new ArrayList<>();

    private static List<DrinkDesc> loadDrinks() {
        List<DrinkDesc> drinks = new ArrayList<>();
        DrinkDesc coffee = new DrinkDesc(COFFEE.toString());
        DrinkDesc tee = new DrinkDesc(TEE.toString());
        DrinkDesc chocolate = new DrinkDesc(CHOCOLATE.toString());
        DrinkDesc orange_juice = new DrinkDesc(ORANGE_JUICE.toString());
        drinks.add(coffee);
        drinks.add(tee);
        drinks.add(chocolate);
        drinks.add(orange_juice);

        return drinks;
    }

    private static List<DrinkDesc> loadDrinksWithPrice() {
        List<DrinkDesc> drinks = new ArrayList<>();
        DrinkDesc coffee = new DrinkDesc(COFFEE.toString());
        DrinkDesc tee = new DrinkDesc(TEE.toString());
        DrinkDesc chocolate = new DrinkDesc(CHOCOLATE.toString());
        DrinkDesc orangeJuice = new DrinkDesc(ORANGE_JUICE.toString());
        coffee.setPrice(AMONT_COFFEE);
        tee.setPrice(AMONT_TEE);
        chocolate.setPrice(AMONT_CHOCOLATE);
        orangeJuice.setPrice(AMONT_ORANGE_JUICE);
        drinks.add(coffee);
        drinks.add(tee);
        drinks.add(chocolate);
        drinks.add(orangeJuice);

        return drinks;
    }

    public static String sendInstruction(String choice) {
        drinks = loadDrinks();
        String returnChoice = null;
        for (DrinkDesc drinkDesc : drinks) {
            if (choice.equalsIgnoreCase(drinkDesc.getName())) returnChoice = drinkDesc.getName();
        }
        return returnChoice;
    }

    public static String sendInstruction(String selectedDrink, int numberOfSugar) {

        String messageToForward = null;
        StringBuilder messageToForwardSb = new StringBuilder("");
        String stik = "0";
        int compteur = 0;
        drinks = loadDrinks();
        for (DrinkDesc drink : drinks) {
            if (selectedDrink.equals(drink.getName())) {
                compteur++;
                if (CHOCOLATE.toString().equals(drink.getName()))
                    messageToForwardSb.append(drink.getName().substring(1, 2));
                else messageToForwardSb.append(drink.getName().substring(0, 1));
                messageToForwardSb.append(":");
                if (drink.getName().equals("ORANGE_JUICE")) numberOfSugar = 0;
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


    public static String makeDrink(String drinkName, double amont) {
        drinksWithPrice = loadDrinksWithPrice();
        String messageToSend = null;
        for (DrinkDesc drink : drinksWithPrice) {
            if (drinkName.equals(drink.getName()) && amont == drink.getPrice()) {
                messageToSend = "ok your drink start making...";
            } else if (drinkName.equals(drink.getName()) && amont < drink.getPrice()) {
                double missingMoney = amont - drink.getPrice();
                BigDecimal missingMoneyBigDec = new BigDecimal(missingMoney);
                MathContext mathContext = new MathContext(1);
                BigDecimal missingMoneyBigDecRounded = missingMoneyBigDec.round(mathContext);
                StringBuilder stringBuilder = new StringBuilder("");
                stringBuilder.append("missing amont of:");
                stringBuilder.append(String.valueOf(missingMoneyBigDecRounded));
                messageToSend = stringBuilder.toString();
            }
        }

        return messageToSend;
    }

    public static String sendInstructionToMaker(String dringChoosed, int numberOfSugar, Boolean isHot) {
        StringBuilder forwardedMessage = new StringBuilder("");
        forwardedMessage.append(sendInstruction(dringChoosed, numberOfSugar));
        if (isHot) forwardedMessage.insert(1, "h");
        return forwardedMessage.toString();
    }
}
