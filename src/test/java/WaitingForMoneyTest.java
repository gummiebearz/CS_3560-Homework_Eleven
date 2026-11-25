import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WaitingForMoneyTest {
    private VendingMachine machine;
    private WaitingForMoney waitingForMoney;

    @BeforeEach
    void setUp() {
        Snack snack = new Snack("Coke", 2.50, 5);
        machine = new VendingMachine(List.of(snack), null);
        waitingForMoney = new WaitingForMoney();
        machine.setState(waitingForMoney);
        machine.setSelectedSnack(snack);
    }

    @Test
    void testInsertMoneyBelowPriceStaysInWaitingForMoney() {
        waitingForMoney.insertMoney(machine, 1.00);
        assertInstanceOf(WaitingForMoney.class, machine.getState());
    }

    @Test
    void testInsertMoneyEqualToPriceTransitionsToDispensingSnack() {
        waitingForMoney.insertMoney(machine, 2.50);
        assertInstanceOf(DispensingSnack.class, machine.getState());
    }

    @Test
    void testInsertMoneyAbovePriceTransitionsToDispensingSnack() {
        waitingForMoney.insertMoney(machine, 5.00);
        assertInstanceOf(DispensingSnack.class, machine.getState());
    }
}