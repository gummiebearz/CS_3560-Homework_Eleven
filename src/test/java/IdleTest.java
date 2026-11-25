import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IdleTest {
    private Snack snack;
    private VendingMachine machine;
    private Idle idle;

    @BeforeEach
    void setUp() {
        snack = new Snack("Coke", 2.50, 5);
        machine = new VendingMachine(List.of(snack), null);
        idle = new Idle();
        machine.setState(idle);
    }

    @Test
    void testSelectSnackSetsSelectedSnack() {
        idle.selectSnack(machine, snack);
        assertSame(snack, machine.getSelectedSnack());
    }

    @Test
    void testSelectSnackTransitionsToWaitingForMoney() {
        idle.selectSnack(machine, snack);
        assertInstanceOf(WaitingForMoney.class, machine.getState());
    }
}
