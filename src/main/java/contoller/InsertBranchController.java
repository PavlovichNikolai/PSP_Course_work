package contoller;

import base.BaseController;
import constants.Actions;
import entities.Branch;
import view.InsertBranchWindow;

public class InsertBranchController extends BaseController<InsertBranchWindow> {
    private int id;

    public void setId(int id) {
        this.id = id;
    }


    public void insertBranch(int b_id, String name, String city, String street, String house, String begin, String end) {
        Branch branch = new Branch(b_id, name, city, street, house, begin, end);
        sendDataToServer(Actions.INSERT_BRANCH);
        sendDataToServer(branch.toString());
        String result = getDataFromServer();
        view.onDataSaved();

    }

}