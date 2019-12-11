package entities;

public class Profit {
    private String name;
    private int amount;
    private int employee;
    private String date;


    public String getProfit() {
        return name;
    }

    public void setProfit(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setCAmount(int amount) {
        this.amount = amount;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Profit(String name, int amount, int employee, String date) {
        this.name = name;
        this.amount = amount;
        this.employee = employee;
        this.date = date;
    }
    @Override
    public String toString(){
        return name+" "+amount+" "+employee+" "+date;
    }

}
