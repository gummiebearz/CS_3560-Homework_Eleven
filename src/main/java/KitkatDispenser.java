public class KitkatDispenser extends SnackDispenseHandler {
    public KitkatDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        // This handler only deals with KitKat
        if (snack.getName().equals("KitKat")) {
            // [CLAUDE] Dispense only if there is quantity left; never pass KitKat down the chain
            if (snack.getQuantity() > 0) {
                snack.decreaseQuantity();
                return true;
            }
            return false;
        }

        // If Not KitKat: pass the request along, or fail at the end of the chain
        if (this.next != null) {
            return this.next.handle(snack);
        }
        return false;
    }
}
