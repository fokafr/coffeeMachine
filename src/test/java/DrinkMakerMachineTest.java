import com.business.DrinkDesc;
import com.business.DrinkMakerMachine;
import org.junit.Assert;
import org.junit.Test;

import static com.business.Drink.*;

public class DrinkMakerMachineTest {
    @Test
    public void shouldReceiveSomeCorrectInstruction() {

        DrinkDesc coffee = new DrinkDesc(COFFEE.toString());
        DrinkDesc tee = new DrinkDesc(TEE.toString());
        DrinkDesc chocolate = new DrinkDesc(CHOCOLATE.toString());

        Assert.assertEquals("COFFEE", DrinkMakerMachine.sendInstruction("COFFEE"));
        Assert.assertEquals("TEE", DrinkMakerMachine.sendInstruction("TEE"));
        Assert.assertEquals("CHOCOLATE", DrinkMakerMachine.sendInstruction("CHOCOLATE"));
    }

    @Test
    public void shouldReturnOneTEEWithOneSugar() {
        String expected = "T:1:0";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstruction("TEE", 1));

    }

    @Test
    public void shouldReturnOneCoffeWithTWOSugar() {
        String expected = "C:2:0";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstruction("COFFEE", 2));

    }

    @Test
    public void shouldReturnOneChocolateWithoutSugar() {
        String expected = "H::";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstruction("CHOCOLATE", 0));
    }

    @Test
    public void shouldReturnAnyMessageToMachine() {
        String expected = "M:message-content";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstruction("OTHER", 0));

    }

    @Test
    public void shouldMakeDrinkWhenAmontOfMoneyIsCorrect() {
        String expected = "ok your drink start making...";
        Assert.assertEquals(expected, DrinkMakerMachine.makeDrink("TEE", 0.4));
        Assert.assertEquals(expected, DrinkMakerMachine.makeDrink("COFFEE", 0.6));
        Assert.assertEquals(expected, DrinkMakerMachine.makeDrink("CHOCOLATE", 0.5));
        Assert.assertEquals(expected, DrinkMakerMachine.makeDrink("ORANGE_JUICE", 0.6));
    }

    @Test
    public void shouldSendMessageWhenNotEnoughAmontOfMoney() {
        String expectedTee = "missing amont of:-0.2";
        //String expectedCoffee = "missing amont of:-0.3";
        String expectedCoffee = "missing amont of:-0.2";
        String expectedChocolate = "missing amont of:-0.3";
        String expectedJuice = "missing amont of:-0.3";
        Assert.assertEquals(expectedTee, DrinkMakerMachine.makeDrink("TEE", 0.2));
        Assert.assertEquals(expectedCoffee, DrinkMakerMachine.makeDrink("COFFEE", 0.4));
        Assert.assertEquals(expectedChocolate, DrinkMakerMachine.makeDrink("CHOCOLATE", 0.2));
        Assert.assertEquals(expectedJuice, DrinkMakerMachine.makeDrink("ORANGE_JUICE", 0.3));

    }

    @Test
    public void shouldReturnOneOrangeJuice() {
        String expected = "O::";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstruction("ORANGE_JUICE", 19));

    }

    @Test
    public void shouldReturnExtraHotcoffeWithNoSugar() {
        String expected = "Ch::";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstructionToMaker("COFFEE", 0, true));
    }

    @Test
    public void shouldReturnExtraHotChocolateWithNoSugarAndStick() {
        String expected = "Hh:1:0";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstructionToMaker("CHOCOLATE", 1, true));

    }

    @Test
    public void shouldReturnExtraHotTeeWithTooSugarAndStick() {
        String expected = "Th:2:0";
        Assert.assertEquals(expected, DrinkMakerMachine.sendInstructionToMaker("TEE", 2, true));

    }


}