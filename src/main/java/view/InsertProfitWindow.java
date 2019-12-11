package view;

import base.BaseView;
import contoller.InsertProfitController;

import javax.swing.*;
import java.awt.*;

public class InsertProfitWindow extends JFrame implements BaseView {
    private InsertProfitController controller = new InsertProfitController();

    private JLabel nameLB = new JLabel("Название: ");
    private JTextField nameTF = new JTextField();
    private JLabel amountLB = new JLabel("Сумма: ");
    private JTextField amountTF = new JTextField();
    private JLabel employeeLB = new JLabel("Работник: ");
    private JTextField employeeTF = new JTextField();
    private JLabel dateLB = new JLabel("Дата: ");
    private JTextField dateTF = new JTextField();
    private JButton insertBtn = new JButton("Вставить");


    private int id;

    InsertProfitWindow(int id) {
        super("Прибыль");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.id = id;
        controller.attachView(this);
        controller.setId(id);
        initWindow();
    }

    public void onDataSaved() {
        JOptionPane.showMessageDialog(this, "Сохранено", "Сохранено!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void initWindow() {
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);


        getContentPane().add(nameLB);
        springLayout.putConstraint(SpringLayout.WEST, nameLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, nameLB, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(nameTF);
        nameTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, nameTF, 20, SpringLayout.EAST, nameLB);
        springLayout.putConstraint(SpringLayout.NORTH, nameTF, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(amountLB);
        springLayout.putConstraint(SpringLayout.WEST, amountLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, amountLB, 20, SpringLayout.SOUTH, nameTF);

        getContentPane().add(amountTF);
        amountTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, amountTF, 20, SpringLayout.EAST, amountLB);
        springLayout.putConstraint(SpringLayout.NORTH, amountTF, 20, SpringLayout.SOUTH, nameTF);

        getContentPane().add(employeeLB);
        springLayout.putConstraint(SpringLayout.WEST, employeeLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, employeeLB, 20, SpringLayout.SOUTH, amountTF);

        getContentPane().add(employeeTF);
        employeeTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, employeeTF, 20, SpringLayout.EAST, employeeLB);
        springLayout.putConstraint(SpringLayout.NORTH, employeeTF, 20, SpringLayout.SOUTH, amountTF);

        getContentPane().add(dateLB);
        springLayout.putConstraint(SpringLayout.WEST, dateLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, dateLB, 20, SpringLayout.SOUTH, employeeTF);

        getContentPane().add(dateTF);
        dateTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, dateTF, 20, SpringLayout.EAST, dateLB);
        springLayout.putConstraint(SpringLayout.NORTH, dateTF, 20, SpringLayout.SOUTH, employeeTF);



        getContentPane().add(insertBtn);
        insertBtn.addActionListener(e -> {
            try {

                String name = nameTF.getText();
                int amount = Integer.parseInt(amountTF.getText());
                int employee = Integer.parseInt(employeeTF.getText());
                String date = dateTF.getText();
                controller.insertProfit(name, amount, employee, date);
            } catch (NullPointerException | NumberFormatException exc) {
                JOptionPane.showMessageDialog(this, "Заполните поля правильно", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                exc.printStackTrace();
            }
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, insertBtn, 0, SpringLayout.HORIZONTAL_CENTER, dateTF);
        springLayout.putConstraint(SpringLayout.NORTH, insertBtn, 20, SpringLayout.SOUTH, dateTF);


        setSize(450, 350);
    }
}