package view;

import base.BaseView;
import contoller.InsertBranchController;

import javax.swing.*;
import java.awt.*;

public class InsertBranchWindow extends JFrame implements BaseView {
    private InsertBranchController controller = new InsertBranchController();

    private JLabel idLB = new JLabel("Номер: ");
    private JTextField idTF = new JTextField();
    private JLabel nameLB = new JLabel("Название: ");
    private JTextField nameTF = new JTextField();
    private JLabel cityLB = new JLabel("Город: ");
    private JTextField cityTF = new JTextField();
    private JLabel streetLB = new JLabel("Улица: ");
    private JTextField streetTF = new JTextField();
    private JLabel houseLB = new JLabel("Номер дома: ");
    private JTextField houseTF = new JTextField();
    private JLabel beginLB = new JLabel("Дата начала работы:");
    private JTextField beginTF = new JTextField();
    private JLabel endLB = new JLabel("Дата окончания работы:");
    private JTextField endTF = new JTextField();
    private JButton insertBtn = new JButton("Вставить");

    private int id;

    InsertBranchWindow(int id) {
        super("Филиал");
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

        getContentPane().add(idLB);
        springLayout.putConstraint(SpringLayout.WEST, idLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, idLB, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(idTF);
        idTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, idTF, 20, SpringLayout.EAST, idLB);
        springLayout.putConstraint(SpringLayout.NORTH, idTF, 20, SpringLayout.NORTH, getContentPane());

        getContentPane().add(nameLB);
        springLayout.putConstraint(SpringLayout.WEST, nameLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, nameLB, 20, SpringLayout.SOUTH, idTF);

        getContentPane().add(nameTF);
        nameTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, nameTF, 20, SpringLayout.EAST, nameLB);
        springLayout.putConstraint(SpringLayout.NORTH, nameTF, 20, SpringLayout.SOUTH, idTF);

        getContentPane().add(cityLB);
        springLayout.putConstraint(SpringLayout.WEST, cityLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, cityLB, 20, SpringLayout.SOUTH, nameTF);

        getContentPane().add(cityTF);
        cityTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, cityTF, 20, SpringLayout.EAST, cityLB);
        springLayout.putConstraint(SpringLayout.NORTH, cityTF, 20, SpringLayout.SOUTH, nameTF);

        getContentPane().add(streetLB);
        springLayout.putConstraint(SpringLayout.WEST, streetLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, streetLB, 20, SpringLayout.SOUTH, cityTF);

        getContentPane().add(streetTF);
        streetTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, streetTF, 20, SpringLayout.EAST, streetLB);
        springLayout.putConstraint(SpringLayout.NORTH, streetTF, 20, SpringLayout.SOUTH, cityTF);

        getContentPane().add(houseLB);
        springLayout.putConstraint(SpringLayout.WEST, houseLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, houseLB, 20, SpringLayout.SOUTH, streetTF);

        getContentPane().add(houseTF);
        houseTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, houseTF, 20, SpringLayout.EAST, houseLB);
        springLayout.putConstraint(SpringLayout.NORTH, houseTF, 20, SpringLayout.SOUTH, streetTF);

        getContentPane().add(beginLB);
        springLayout.putConstraint(SpringLayout.WEST, beginLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, beginLB, 20, SpringLayout.SOUTH, houseTF);

        getContentPane().add(beginTF);
        beginTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, beginTF, 20, SpringLayout.EAST, beginLB);
        springLayout.putConstraint(SpringLayout.NORTH, beginTF, 20, SpringLayout.SOUTH, houseTF);

        getContentPane().add(endLB);
        springLayout.putConstraint(SpringLayout.WEST, endLB, 20, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, endLB, 20, SpringLayout.SOUTH, beginTF);

        getContentPane().add(endTF);
        endTF.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, endTF, 20, SpringLayout.EAST, endLB);
        springLayout.putConstraint(SpringLayout.NORTH, endTF, 20, SpringLayout.SOUTH, beginTF);

        getContentPane().add(insertBtn);
        insertBtn.addActionListener(e -> {
            try {

                int b_id = Integer.parseInt(idTF.getText());
                String name = nameTF.getText();
                String city = cityTF.getText();
                String street = streetTF.getText();
                String house = houseTF.getText();
                String begin = beginTF.getText();
                String end = endTF.getText();
                controller.insertBranch(b_id, name, city, street, house, begin, end);
            } catch (NullPointerException | NumberFormatException exc) {
                JOptionPane.showMessageDialog(this, "Заполните поля правильно", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                exc.printStackTrace();
            }
        });
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, insertBtn, 0, SpringLayout.HORIZONTAL_CENTER, endTF);
        springLayout.putConstraint(SpringLayout.NORTH, insertBtn, 20, SpringLayout.SOUTH, endTF);

        setSize(450, 450);
    }
}