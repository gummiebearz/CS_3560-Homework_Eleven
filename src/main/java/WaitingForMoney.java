public class WaitingForMoney implements StateOfVendingMachine {
    public void selectSnack(VendingMachine machine, Snack snack) {}
    public void insertMoney(VendingMachine machine, double amount) {
        // Only move on once the money covers the price; otherwise keep waiting
        if (amount >= machine.getSelectedSnack().getPrice()) {
            machine.setInsertedMoney(amount);
            machine.setState(new DispensingSnack());
        }
    }
    public void dispenseSnack(VendingMachine machine) {}
}
