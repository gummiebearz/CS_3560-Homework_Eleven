public abstract class SnackDispenseHandler {
    protected SnackDispenseHandler next;
    public SnackDispenseHandler(SnackDispenseHandler next) {}
    abstract boolean handle(Snack snack);
}
