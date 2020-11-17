package Kripto;

public abstract class Account {
    private String name;
    private int numberAcc;
    private double state;

    public Account(String name, int numberAcc, double state) {
        this.name = name;
        this.numberAcc = numberAcc;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberAcc() {
        return numberAcc;
    }

    public void setNumberAcc(int numberAcc) {
        this.numberAcc = numberAcc;
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public void increment(double sum){
        this.state+=sum;
    }
    public void substract(double sum){
        this.state-=sum;
    }
}
