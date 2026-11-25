public abstract class SnackDispenseHandler {
    protected SnackDispenseHandler next;
    public SnackDispenseHandler(SnackDispenseHandler next) {
        // Store the next handler in the chain
        this.next = next;
    }
    abstract boolean handle(Snack snack);
}
