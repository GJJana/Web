package Kripto;

public class PlatinumCheckingAccount extends Account implements InterestBearingAccount {
    public PlatinumCheckingAccount(String name, int numberAcc, double state) {
        super(name, numberAcc, state);
    }

    @Override
    public void addInterest() {
        this.setState(this.getState()+InterestCheckingAccount.INTEREST*2*this.getState());
    }
}
