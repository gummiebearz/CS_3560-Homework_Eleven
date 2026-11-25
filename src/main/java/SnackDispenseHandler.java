public abstract class SnackDispenseHandler {
    protected SnackDispenseHandler handler;
    public SnackDispenseHandler(SnackDispenseHandler next) {}
    abstract boolean handle(Snack s);
}
