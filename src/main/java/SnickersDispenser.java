public class SnickersDispenser extends SnackDispenseHandler {
    public SnickersDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        // This handler only deals with Snickers
        if (snack.getName().equals("Snickers")) {
            // Dispense only if there is quantity left; never pass Snickers down the chain
            if (snack.getQuantity() > 0) {
                snack.decreaseQuantity();
                return true;
            }
            return false;
        }

        // If Not Snickers: pass the request along, or fail at the end of the chain
        if (this.next != null) {
            return this.next.handle(snack);
        }
        return false;
    }
}
