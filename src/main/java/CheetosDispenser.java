public class CheetosDispenser extends SnackDispenseHandler {
    public CheetosDispenser(SnackDispenseHandler next) {
        super(next);
    }

    boolean handle(Snack snack) {
        // This handler only deals with Cheetos
        if (snack.getName().equals("Cheetos")) {
            // Dispense only if there is quantity left; never pass Cheetos down the chain
            if (snack.getQuantity() > 0) {
                snack.decreaseQuantity();
                return true;
            }
            return false;
        }

        // If Not Cheetos: pass the request along, or fail at the end of the chain
        if (this.next != null) {
            return this.next.handle(snack);
        }
        return false;
    }
}
