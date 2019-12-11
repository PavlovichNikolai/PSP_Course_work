package contoller;

import base.BaseController;
import constants.Actions;
import entities.Profit;
import view.InsertExpensesWindow;

public class InsertExpensesController extends BaseController<InsertExpensesWindow> {
    private int id;

    public void setId(int id) {
        this.id = id;
    }


    public void insertExpenses(String name, int amount, int employee, String date) {
        Profit profit = new Profit(name, amount, employee, date);
        sendDataToServer(Actions.INSERT_EXPENSES);
        sendDataToServer(profit.toString());
        String result = getDataFromServer();
        view.onDataSaved();

    }

}