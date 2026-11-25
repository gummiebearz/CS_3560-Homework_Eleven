import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PepsiDispenserTest {
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

    private Snack pepsi;
    private Snack cheetos;

    @BeforeEach
    void setUp() {
        pepsi = new Snack("Pepsi", 2.50, 3);
        cheetos = new Snack("Cheetos", 1.75, 3);
    }

    @Test
    void testHandleDispensesPepsiWhenInStock() {
        PepsiDispenser dispenser = new PepsiDispenser(null);
        assertTrue(dispenser.handle(pepsi));
    }

    @Test
    void testHandleDecreasesPepsiQuantityWhenDispensed() {
        PepsiDispenser dispenser = new PepsiDispenser(null);
        dispenser.handle(pepsi);
        assertEquals(2, pepsi.getQuantity());
    }

    @Test
    void testHandleDoesNotDispensePepsiWhenOutOfStock() {
        Snack emptyPepsi = new Snack("Pepsi", 2.50, 0);
        PepsiDispenser dispenser = new PepsiDispenser(null);
        assertFalse(dispenser.handle(emptyPepsi));
        assertEquals(0, emptyPepsi.getQuantity());
    }

    @Test
    void testHandleDoesNotPassOutOfStockPepsiToNext() {
        Snack emptyPepsi = new Snack("Pepsi", 2.50, 0);
        StubNext next = new StubNext(true);
        PepsiDispenser dispenser = new PepsiDispenser(next);
        assertFalse(dispenser.handle(emptyPepsi));
        assertNull(next.lastHandled);
    }

    @Test
    void testHandlePassesOtherSnackToNext() {
        StubNext next = new StubNext(true);
        PepsiDispenser dispenser = new PepsiDispenser(next);
        assertTrue(dispenser.handle(cheetos));
        assertSame(cheetos, next.lastHandled);
        assertEquals(3, cheetos.getQuantity());
    }

    @Test
    void testHandleReturnsNextResultForOtherSnack() {
        StubNext next = new StubNext(false);
        PepsiDispenser dispenser = new PepsiDispenser(next);
        assertFalse(dispenser.handle(cheetos));
        assertSame(cheetos, next.lastHandled);
    }

    @Test
    void testHandleReturnsFalseForOtherSnackAtEndOfChain() {
        PepsiDispenser dispenser = new PepsiDispenser(null);
        assertFalse(dispenser.handle(cheetos));
        assertEquals(3, cheetos.getQuantity());
    }
}
