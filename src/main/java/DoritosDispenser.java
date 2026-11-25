public class DoritosDispenser extends SnackDispenseHandler {
    public DoritosDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        // This handler only deals with Doritos
        if (snack.getName().equals("Doritos")) {
            // [CLAUDE] Dispense only if there is quantity left; never pass Doritos down the chain
            if (snack.getQuantity() > 0) {
                snack.decreaseQuantity();
                return true;
            }
            return false;
        }

        // If Not Doritos: pass the request along, or fail at the end of the chain
        if (this.next != null) {
            return this.next.handle(snack);
        }
        return false;
    }
}
