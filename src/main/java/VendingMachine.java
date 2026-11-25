import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine state;
    private List<Snack> availableSnacks;
    private Snack selectedSnack;
    private double insertedMoney;
    private SnackDispenseHandler dispenseHandler;

    public VendingMachine(List<Snack> availableSnacks, SnackDispenseHandler dispenseHandler) {}
    void setState(StateOfVendingMachine state) {
        this.state = state;
    }
    StateOfVendingMachine getState() { return this.state; }
    public Snack getSelectedSnack() { return this.selectedSnack; }
    public void selectSnack(Snack snack) {}
    public void insertMoney(double amount) {}
    public void dispenseSnack() {}
    void setSelectedSnack(Snack snack) {
        this.selectedSnack = snack;
    }
    void setInsertedMoney(double amt) {}
    void returnMoney() {}
    public double getInsertedMoney() { return 0.0; }
    public SnackDispenseHandler getDispenseHandler() { return null; }
}
