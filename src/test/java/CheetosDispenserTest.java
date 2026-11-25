import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheetosDispenserTest {
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

    private Snack cheetos;
    private Snack doritos;

    @BeforeEach
    void setUp() {
        cheetos = new Snack("Cheetos", 1.75, 3);
        doritos = new Snack("Doritos", 1.75, 3);
    }

    @Test
    void testHandleDispensesCheetosWhenInStock() {
        CheetosDispenser dispenser = new CheetosDispenser(null);
        assertTrue(dispenser.handle(cheetos));
    }

    @Test
    void testHandleDecreasesCheetosQuantityWhenDispensed() {
        CheetosDispenser dispenser = new CheetosDispenser(null);
        dispenser.handle(cheetos);
        assertEquals(2, cheetos.getQuantity());
    }

    @Test
    void testHandleDoesNotDispenseCheetosWhenOutOfStock() {
        Snack emptyCheetos = new Snack("Cheetos", 1.75, 0);
        CheetosDispenser dispenser = new CheetosDispenser(null);
        assertFalse(dispenser.handle(emptyCheetos));
        assertEquals(0, emptyCheetos.getQuantity());
    }

    @Test
    void testHandleDoesNotPassOutOfStockCheetosToNext() {
        Snack emptyCheetos = new Snack("Cheetos", 1.75, 0);
        StubNext next = new StubNext(true);
        CheetosDispenser dispenser = new CheetosDispenser(next);
        assertFalse(dispenser.handle(emptyCheetos));
        assertNull(next.lastHandled);
    }

    @Test
    void testHandlePassesOtherSnackToNext() {
        StubNext next = new StubNext(true);
        CheetosDispenser dispenser = new CheetosDispenser(next);
        assertTrue(dispenser.handle(doritos));
        assertSame(doritos, next.lastHandled);
        assertEquals(3, doritos.getQuantity());
    }

    @Test
    void testHandleReturnsNextResultForOtherSnack() {
        StubNext next = new StubNext(false);
        CheetosDispenser dispenser = new CheetosDispenser(next);
        assertFalse(dispenser.handle(doritos));
        assertSame(doritos, next.lastHandled);
    }

    @Test
    void testHandleReturnsFalseForOtherSnackAtEndOfChain() {
        CheetosDispenser dispenser = new CheetosDispenser(null);
        assertFalse(dispenser.handle(doritos));
        assertEquals(3, doritos.getQuantity());
    }
}