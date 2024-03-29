package view;

import base.BaseView;
import contoller.ShowExpensesController;

import javax.swing.*;
import java.util.List;
import java.util.Vector;

public class ShowExpensesWindow extends JFrame implements BaseView {
    private JTable table = new JTable();
    private JButton backBtn = new JButton("Back");
    private Vector<String> columnNames = new Vector<>();

    private ShowExpensesController controller = new ShowExpensesController();

    ShowExpensesWindow(int id) {
        super("Траты");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        controller.attachView(this);
        controller.setId(id);
        columnNames.addAll(List.of("Номер", "Название", "Сумма", "Фамилия", "Дата"));
        getUsersFromDB();
        initWindow();
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        getContentPane().add(new JScrollPane(table));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, table, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, table, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(backBtn);
        backBtn.addActionListener(e -> controller.navigateBack());
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, backBtn, 20, SpringLayout.SOUTH, table);

        setSize(500, 300);
    }

    private void getUsersFromDB() {
        controller.getUsersFromDB();
    }

    public void onUsersLoaded(Vector<Vector> users) {
        table = new JTable(users, columnNames);
    }

}
