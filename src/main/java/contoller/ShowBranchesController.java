package contoller;

import base.BaseController;
import constants.Actions;
import view.MenuWindow;
import view.ShowBranchesWindow;

import java.util.Arrays;
import java.util.Vector;

public class ShowBranchesController extends BaseController<ShowBranchesWindow> {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void getUsersFromDB() {
        Vector<Vector> rowData = new Vector<>();
        sendDataToServer(Actions.GET_ALL_BRANCHES);
        sendDataToServer(" ");
        int rows = Integer.parseInt(getDataFromServer());
        for (int i = 0; i < rows; i++) {
            String row = getDataFromServer();
            String[] arr = row.split(" ");
            Vector<Object> object = new Vector<>(Arrays.asList(arr));
            rowData.add(object);
        }
        view.onUsersLoaded(rowData);
    }

    public void navigateBack() {
        new MenuWindow(1, id).setVisible(true);
        view.setVisible(false);
    }

}
