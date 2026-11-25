interface StateOfVendingMachine {
    void selectSnack(VendingMachine machine, Snack snack);
    void insertMoney(VendingMachine machine, double amount);
    void dispenseSnack(VendingMachine machine);
}
