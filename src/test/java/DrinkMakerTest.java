import com.business.DrinkMaker;
import org.junit.Assert;
import org.junit.Test;

public class DrinkMakerTest {
    @Test
    public void shouldReceiveCorrectInstruction() {

        Assert.assertEquals(true, DrinkMaker.getInstruction("COFFEE"));
        Assert.assertEquals(true, DrinkMaker.getInstruction("TEE"));
        Assert.assertEquals(true, DrinkMaker.getInstruction("CHOCOLATE"));

    }

    @Test
    public void shouldReturnOneTEEOneSugar() {
        String expected = "T:1:0";
        Assert.assertEquals(expected, DrinkMaker.forwardMessage("TEE", 1));

    }

    @Test
    public void shouldReturnOneCoffeAndTWOSugar() {
        String expected = "C:2:0";
        Assert.assertEquals(expected, DrinkMaker.forwardMessage("COFFEE", 2));

    }

    @Test
    public void shouldReturnOneChocolateNoSugar() {
        String expected = "H::";
        Assert.assertEquals(expected, DrinkMaker.forwardMessage("CHOCOLATE", 0));

    }

    @Test
    public void shouldReturnAnyMessageToMachine() {
        String expected = "M:message-content";
        Assert.assertEquals(expected, DrinkMaker.forwardMessage("OTHER", 0));

    }

    @Test
    public void shouldMakeDrinkWhenCorrectAmontOfMoney() {
        String expected = "ok your drink start making...";
        Assert.assertEquals(expected, DrinkMaker.makeDrink("TEE", 0.4));
        Assert.assertEquals(expected, DrinkMaker.makeDrink("COFFEE", 0.6));
        Assert.assertEquals(expected, DrinkMaker.makeDrink("CHOCOLATE", 0.5));
        Assert.assertEquals(expected, DrinkMaker.makeDrink("ORANGE_JUICE", 0.6));
    }


    @Test
    public void shouldSendMessageWhenNotEnoughAmontOfMoney() {
        String expectedTee = "missing amont of:-0.2";
        String expectedCoffee = "missing amont of:-0.3";
        String expectedChocolate = "missing amont of:-0.3";
        String expectedJuice = "missing amont of:-0.3";
        Assert.assertEquals(expectedTee, DrinkMaker.makeDrink("TEE", 0.2));
        Assert.assertEquals(expectedCoffee, DrinkMaker.makeDrink("COFFEE", 0.3));
        Assert.assertEquals(expectedChocolate, DrinkMaker.makeDrink("CHOCOLATE", 0.2));
        Assert.assertEquals(expectedJuice, DrinkMaker.makeDrink("ORANGE_JUICE", 0.3));

    }

    @Test
    public void shouldReturnOneOrangeJuice() {
        String expected = "O::";
        Assert.assertEquals(expected, DrinkMaker.forwardMessage("ORANGE_JUICE", 19));

    }

    @Test
    public void shouldReturnExtraHotcoffeWithNoSugar() {
        String expected = "Ch::";
        Assert.assertEquals(expected, DrinkMaker.forwardMessageToMaker("COFFEE", 0, true));

    }

    @Test
    public void shouldReturnExtraHotChocolateWithNoSugarAndStick() {
        String expected = "Hh:1:0";
        Assert.assertEquals(expected, DrinkMaker.forwardMessageToMaker("CHOCOLATE", 1, true));

    }

    @Test
    public void shouldReturnExtraHotTeeWithTooSugarAndStick() {
        String expected = "Th:2:0";
        Assert.assertEquals(expected, DrinkMaker.forwardMessageToMaker("TEE", 2, true));

    }


}
