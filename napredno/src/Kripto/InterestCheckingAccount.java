package Kripto;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {
    public InterestCheckingAccount(String name, int numberAcc, double state) {
        super(name, numberAcc, state);
    }
    public static final double INTEREST=0.03;
    @Override
    public void addInterest() {
        //3%
        this.setState(this.getState()+INTEREST*this.getState());

    }
}
