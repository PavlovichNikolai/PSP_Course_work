package contoller;

import base.BaseController;
import constants.Actions;
import view.MenuWindow;
import view.EvaluationWindow;

import java.util.Arrays;
import java.util.Vector;

public class EvaluationController extends BaseController<EvaluationWindow> {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void getUsersFromDB() {
        Vector<Vector> rowData = new Vector<>();
        sendDataToServer(Actions.EVALUATION);
        sendDataToServer(" ");
        String res = getDataFromServer();
        String[] arr = res.split(" ");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);


        if(x>y){
            view.setLabel1("Доходы: "+x+", Расходы: "+y+"\r"+
                    "Доходы превышают расходы, все в норме.");
        } else if(x<y) {
            view.setLabel1("Доходы: "+x+", Расходы: "+y+"\n"+
                    "Расходы превышают доходы, необходимо уменьшить расходы.");
        } else {
            view.setLabel1("Доходы: "+x+", Расходы: "+y+"\n"+
                    "Расходы равны доходам, необходимо уменьшить расходы.");
        }
    }

    public void navigateBack() {
        new MenuWindow(1, id).setVisible(true);
        view.setVisible(false);
    }

}
