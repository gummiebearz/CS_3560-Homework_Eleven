import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnickersDispenserTest {
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

    private Snack snickers;
    private Snack coke;

    @BeforeEach
    void setUp() {
        snickers = new Snack("Snickers", 1.25, 3);
        coke = new Snack("Coke", 2.50, 3);
    }

    @Test
    void testHandleDispensesSnickersWhenInStock() {
        SnickersDispenser dispenser = new SnickersDispenser(null);
        assertTrue(dispenser.handle(snickers));
    }

    @Test
    void testHandleDecreasesSnickersQuantityWhenDispensed() {
        SnickersDispenser dispenser = new SnickersDispenser(null);
        dispenser.handle(snickers);
        assertEquals(2, snickers.getQuantity());
    }

    @Test
    void testHandleDoesNotDispenseSnickersWhenOutOfStock() {
        Snack emptySnickers = new Snack("Snickers", 1.25, 0);
        SnickersDispenser dispenser = new SnickersDispenser(null);
        assertFalse(dispenser.handle(emptySnickers));
        assertEquals(0, emptySnickers.getQuantity());
    }

    @Test
    void testHandleDispensesLastSnickersThenRunsOut() {
        Snack lastSnickers = new Snack("Snickers", 1.25, 1);
        SnickersDispenser dispenser = new SnickersDispenser(null);
        assertTrue(dispenser.handle(lastSnickers));
        assertEquals(0, lastSnickers.getQuantity());
        assertFalse(dispenser.handle(lastSnickers));
        assertEquals(0, lastSnickers.getQuantity());
    }

    @Test
    void testHandleDoesNotPassOutOfStockSnickersToNext() {
        Snack emptySnickers = new Snack("Snickers", 1.25, 0);
        StubNext next = new StubNext(true);
        SnickersDispenser dispenser = new SnickersDispenser(next);
        assertFalse(dispenser.handle(emptySnickers));
        assertNull(next.lastHandled);
    }

    @Test
    void testHandlePassesOtherSnackToNext() {
        StubNext next = new StubNext(true);
        SnickersDispenser dispenser = new SnickersDispenser(next);
        assertTrue(dispenser.handle(coke));
        assertSame(coke, next.lastHandled);
        assertEquals(3, coke.getQuantity());
    }

    @Test
    void testHandleReturnsNextResultForOtherSnack() {
        StubNext next = new StubNext(false);
        SnickersDispenser dispenser = new SnickersDispenser(next);
        assertFalse(dispenser.handle(coke));
        assertSame(coke, next.lastHandled);
    }

    @Test
    void testHandleReturnsFalseForOtherSnackAtEndOfChain() {
        SnickersDispenser dispenser = new SnickersDispenser(null);
        assertFalse(dispenser.handle(coke));
        assertEquals(3, coke.getQuantity());
    }
}
