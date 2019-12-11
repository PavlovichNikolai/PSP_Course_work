package view;

import base.BaseView;
import contoller.ChartsController;

import javax.swing.*;

public class MenuWindow extends JFrame implements BaseView {
    private JButton evaluationBtn = new JButton("Дать оценку управлению бюджетом");
    private JButton showAllBranchesBtn = new JButton("Показать все филиалы");
    private JButton showCharts = new JButton("Показать график");
    private JButton showAllUsersBtn = new JButton("Показать всех пользователей");
    private JButton showProfitBtn = new JButton("Показать прибыль");
    private JButton showExpensesBtn = new JButton("Показать расходы");
    private JButton showInsertBranchBtn = new JButton("Вставить филиал");
    private JButton showInsertProfitBtn = new JButton("Вставить прибыль");
    private JButton showInsertExpensesBtn = new JButton("Вставить расходы");


    private int role;
    private int id;

    public MenuWindow(int role, int id) {
        super("Меню");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.id = id;
        this.role = role;
        initWindow();
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        getContentPane().add(evaluationBtn);
        evaluationBtn.setVisible(role != 3);
        evaluationBtn.addActionListener(e -> new EvaluationWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, evaluationBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, evaluationBtn, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(showAllBranchesBtn);
        showAllBranchesBtn.addActionListener(e -> new ShowBranchesWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showAllBranchesBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showAllBranchesBtn, 20, SpringLayout.SOUTH, evaluationBtn);

        getContentPane().add(showCharts);
        showCharts.setVisible(role == 1 || role ==2);
        //showCharts.addActionListener(e -> new ChartsController().showCharts());
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showCharts, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showCharts, 20, SpringLayout.SOUTH, showAllBranchesBtn);

        getContentPane().add(showAllUsersBtn);
        showAllUsersBtn.setVisible(role == 1);
        showAllUsersBtn.addActionListener(e -> new ShowUsersWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showAllUsersBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showAllUsersBtn, 20, SpringLayout.SOUTH, showCharts);

        getContentPane().add(showProfitBtn);
        showProfitBtn.setVisible(role == 1 || role ==2);
        showProfitBtn.addActionListener(e -> new ShowProfitWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showProfitBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showProfitBtn, 20, SpringLayout.SOUTH, showAllUsersBtn);

        getContentPane().add(showExpensesBtn);
        showExpensesBtn.setVisible(role == 1 || role ==2);
        showExpensesBtn.addActionListener(e -> new ShowExpensesWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showExpensesBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showExpensesBtn, 20, SpringLayout.SOUTH, showProfitBtn);

        getContentPane().add(showInsertBranchBtn);
        showInsertBranchBtn.setVisible(role == 1);
        showInsertBranchBtn.addActionListener(e -> new InsertBranchWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showInsertBranchBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showInsertBranchBtn, 20, SpringLayout.SOUTH, showExpensesBtn);

        getContentPane().add(showInsertProfitBtn);
        showInsertProfitBtn.setVisible(role == 1 || role ==2);
        showInsertProfitBtn.addActionListener(e -> new InsertProfitWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showInsertProfitBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showInsertProfitBtn, 20, SpringLayout.SOUTH, showInsertBranchBtn);

        getContentPane().add(showInsertExpensesBtn);
        showInsertExpensesBtn.setVisible(role == 1 || role ==2);
        showInsertExpensesBtn.addActionListener(e -> new InsertExpensesWindow(id).setVisible(true));
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, showInsertExpensesBtn, 0, SpringLayout.HORIZONTAL_CENTER, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, showInsertExpensesBtn, 20, SpringLayout.SOUTH, showInsertProfitBtn);


        setSize(400, 500);

    }
}
