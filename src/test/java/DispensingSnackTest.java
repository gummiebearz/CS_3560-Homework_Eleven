import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DispensingSnackTest {
    private static class StubHandler extends SnackDispenseHandler {
        private final boolean result;
        private Snack lastHandled;

        StubHandler(boolean result) {
            super(null);
            this.result = result;
        }

        @Override
        boolean handle(Snack snack) {
            lastHandled = snack;
            return result;
        }
    }

    private Snack snack;
    private DispensingSnack dispensingSnack;

    @BeforeEach
    void setUp() {
        snack = new Snack("Coke", 2.50, 5);
        dispensingSnack = new DispensingSnack();
    }

    private VendingMachine machineWith(StubHandler handler) {
        VendingMachine machine = new VendingMachine(List.of(snack), handler);
        machine.setState(dispensingSnack);
        machine.setSelectedSnack(snack);
        machine.setInsertedMoney(2.50);
        return machine;
    }

    @Test
    void testDispenseSnackPassesSelectedSnackToHandler() {
        StubHandler handler = new StubHandler(true);
        dispensingSnack.dispenseSnack(machineWith(handler));
        assertSame(snack, handler.lastHandled);
    }

    @Test
    void testDispenseSnackWhenDispensedTransitionsToIdle() {
        VendingMachine machine = machineWith(new StubHandler(true));
        dispensingSnack.dispenseSnack(machine);
        assertInstanceOf(Idle.class, machine.getState());
    }

    @Test
    void testDispenseSnackWhenNotDispensedReturnsMoney() {
        VendingMachine machine = machineWith(new StubHandler(false));
        assertEquals(2.50, machine.getInsertedMoney(), 0.001);
        dispensingSnack.dispenseSnack(machine);
        assertEquals(0.0, machine.getInsertedMoney(), 0.001);
    }

    @Test
    void testDispenseSnackWhenNotDispensedTransitionsToIdle() {
        VendingMachine machine = machineWith(new StubHandler(false));
        dispensingSnack.dispenseSnack(machine);
        assertInstanceOf(Idle.class, machine.getState());
    }
}
