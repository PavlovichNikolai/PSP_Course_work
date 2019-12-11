package contoller;

import base.BaseController;
import constants.Actions;
import entities.Profit;
import view.InsertProfitWindow;

public class InsertProfitController extends BaseController<InsertProfitWindow> {
    private int id;

    public void setId(int id) {
        this.id = id;
    }


    public void insertProfit(String name, int amount, int employee, String date) {
        Profit profit = new Profit(name, amount, employee, date);
        sendDataToServer(Actions.INSERT_PROFIT);
        sendDataToServer(profit.toString());
        String result = getDataFromServer();
        view.onDataSaved();


    }

}