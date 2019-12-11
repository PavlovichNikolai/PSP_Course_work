package view;

import base.BaseView;
import contoller.EvaluationController;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class EvaluationWindow extends JFrame implements BaseView {
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JButton backBtn = new JButton("Назад");
    private Vector<String> columnNames = new Vector<>();

    private EvaluationController controller = new EvaluationController();

    EvaluationWindow(int id) {
        super("Оценка");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        controller.attachView(this);
        controller.setId(id);
        columnNames.addAll(List.of());
        getUsersFromDB();
        initWindow();
    }

    public void setLabel1(String text) {
        label1.setText(text);
    }
    public void setLabel2(String text) {
        label2.setText(text);
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        getContentPane().add(new JScrollPane(label1));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label1, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, label1, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(new JScrollPane(label2));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label2, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, label1, 20, SpringLayout.SOUTH, label1);


        getContentPane().add(backBtn);
        backBtn.addActionListener(e -> controller.navigateBack());
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, backBtn, 20, SpringLayout.SOUTH, label2);

        setSize(600, 300);
    }

    private void getUsersFromDB() {
        controller.getUsersFromDB();
    }

    //public void onUsersLoaded(Vector<Vector> users) {
      //  label = new JLabel(users, columnNames);
   // }

}
