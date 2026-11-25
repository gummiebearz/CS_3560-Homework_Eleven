import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CokeDispenserTest {
    private static class StubNext extends SnackDispenseHandler {
        private final boolean result;
        private Snack lastHandled;

        StubNext(boolean result) {
            super(null);
            this.result = result;
        }

        @Override
        boolean handle(Snack snack) {
            lastHandled = snack;
            return result;
        }
    }

    private Snack coke;
    private Snack pepsi;

    @BeforeEach
    void setUp() {
        coke = new Snack("Coke", 2.50, 3);
        pepsi = new Snack("Pepsi", 2.50, 3);
    }

    @Test
    void testHandleDispensesCokeWhenInStock() {
        CokeDispenser dispenser = new CokeDispenser(null);
        assertTrue(dispenser.handle(coke));
    }

    @Test
    void testHandleDecreasesCokeQuantityWhenDispensed() {
        CokeDispenser dispenser = new CokeDispenser(null);
        dispenser.handle(coke);
        assertEquals(2, coke.getQuantity());
    }

    @Test
    void testHandleDoesNotDispenseCokeWhenOutOfStock() {
        Snack emptyCoke = new Snack("Coke", 2.50, 0);
        CokeDispenser dispenser = new CokeDispenser(null);
        assertFalse(dispenser.handle(emptyCoke));
        assertEquals(0, emptyCoke.getQuantity());
    }

    @Test
    void testHandleDoesNotPassOutOfStockCokeToNext() {
        Snack emptyCoke = new Snack("Coke", 2.50, 0);
        StubNext next = new StubNext(true);
        CokeDispenser dispenser = new CokeDispenser(next);
        assertFalse(dispenser.handle(emptyCoke));
        assertNull(next.lastHandled);
    }

    @Test
    void testHandlePassesOtherSnackToNext() {
        StubNext next = new StubNext(true);
        CokeDispenser dispenser = new CokeDispenser(next);
        assertTrue(dispenser.handle(pepsi));
        assertSame(pepsi, next.lastHandled);
        assertEquals(3, pepsi.getQuantity());
    }

    @Test
    void testHandleReturnsNextResultForOtherSnack() {
        StubNext next = new StubNext(false);
        CokeDispenser dispenser = new CokeDispenser(next);
        assertFalse(dispenser.handle(pepsi));
        assertSame(pepsi, next.lastHandled);
    }

    @Test
    void testHandleReturnsFalseForOtherSnackAtEndOfChain() {
        CokeDispenser dispenser = new CokeDispenser(null);
        assertFalse(dispenser.handle(pepsi));
        assertEquals(3, pepsi.getQuantity());
    }
}
