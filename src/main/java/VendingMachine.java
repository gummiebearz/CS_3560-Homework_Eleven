import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine state;
    private List<Snack> availableSnacks;
    private Snack selectedSnack;
    private double insertedMoney;
    private SnackDispenseHandler dispenseHandler;

    public VendingMachine(List<Snack> availableSnacks, SnackDispenseHandler dispenseHandler) {
        // Store the inventory and chain head; the machine starts in Idle
        this.availableSnacks = availableSnacks;
        this.dispenseHandler = dispenseHandler;
        this.state = new Idle();
    }

    void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    StateOfVendingMachine getState() {
        return this.state;
    }

    public Snack getSelectedSnack() {
        return this.selectedSnack;
    }

    public void selectSnack(Snack snack) {
        // Delegate to the current state
        this.state.selectSnack(this, snack);
    }

    public void insertMoney(double amount) {
        // Delegate to the current state
        this.state.insertMoney(this, amount);
    }

    public void dispenseSnack() {
        // Delegate to the current state
        this.state.dispenseSnack(this);
    }

    void setSelectedSnack(Snack snack) {
        this.selectedSnack = snack;
    }

    void setInsertedMoney(double amount) {
        this.insertedMoney = amount;
    }

    void returnMoney() {
        // Giving the money back so the machine no longer holds any
        this.insertedMoney = 0.0;
    }

    public double getInsertedMoney() {
        return 0.0;
    }

    public SnackDispenseHandler getDispenseHandler() {
        return this.dispenseHandler;
    }
}
