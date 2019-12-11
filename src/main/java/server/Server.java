package server;

import constants.Actions;
import entities.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {

    private Connection db;
    private Statement statement;
    private ServerSocket socket;

    public static void main(String[] args) {
        Server server = new Server();
        server.initializeServer();
        System.out.println("Server initialized");
        server.listenConnections();
    }

    private void initializeServer() {
        try {
            db = DriverManager.getConnection("jdbc:mysql://localhost:3306/COMPANY" +
                            "?verifyServerCertificate=false" +
                            "&useSSL=false" +
                            "&requireSSL=false" +
                            "&useLegacyDatetimeCode=false" +
                            "&amp" +
                            "&serverTimezone=UTC",
                    "Nikolai",
                    "12345678");
            statement = db.createStatement();
            socket = new ServerSocket(1024);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listenConnections() {
        System.out.println("Listening connections ... ");
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Socket client = socket.accept();
                new Thread(() -> {
                    System.out.println("Client accepted");
                    try {
                        OutputStream outputStream = client.getOutputStream();
                        InputStream inputStream = client.getInputStream();

                        String clientAction;
                        String queryContent;

                        boolean flag = true;

                        while (flag) {
                            byte[] msg = new byte[100];
                            int k = inputStream.read(msg);
                            clientAction = new String(msg, 0, k);
                            clientAction = clientAction.trim();
                            msg = new byte[100];
                            k = inputStream.read(msg);
                            queryContent = new String(msg, 0, k);
                            queryContent = queryContent.trim();

                            if (clientAction.equalsIgnoreCase(Actions.END)) {
                                flag = false;
                            } else if (clientAction.equalsIgnoreCase(Actions.LOGIN)) {
                                logInUser(outputStream, queryContent);
                            } else if (clientAction.equalsIgnoreCase(Actions.REGISTER)) {
                                String[] arr = queryContent.split(" ");
                                String login = arr[0];
                                String password = arr[1];
                                registerUser(login, password);
                                sendDataToClient(outputStream, "REGISTERED");
                            }else if (clientAction.equalsIgnoreCase(Actions.EVALUATION)) {
                                getEvaluation(outputStream);
                            } else if (clientAction.equalsIgnoreCase(Actions.GET_ALL_USERS)) {
                                getAllUsers(outputStream);
                            } else if (clientAction.equalsIgnoreCase(Actions.GET_ALL_BRANCHES)) {
                                getAllBranches(outputStream);
                            } else if (clientAction.equalsIgnoreCase(Actions.GET_ALL_PROFIT)) {
                                getAllProfit(outputStream);
                            } else if (clientAction.equalsIgnoreCase(Actions.GET_ALL_EXPENSES)) {
                                getAllExpenses(outputStream);
                            } else if (clientAction.equalsIgnoreCase(Actions.GET_PROFIT_EXPENSES)) {
                                getProfitExpenses(outputStream);
                            } else if (clientAction.equalsIgnoreCase(Actions.INSERT_BRANCH)) {
                                insertBranch(outputStream, queryContent);
                            } else if (clientAction.equalsIgnoreCase(Actions.INSERT_PROFIT)) {
                                insertProfit(outputStream, queryContent);
                            } else if (clientAction.equalsIgnoreCase(Actions.INSERT_EXPENSES)) {
                                insertExpenses(outputStream, queryContent);
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void logInUser(OutputStream outputStream, String queryContent) {
        String[] arr = queryContent.split(" ");
        String login = arr[0];
        String password = arr[1];
        int role;
        int id;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT U_ID, U_ROLE FROM USERS INNER JOIN PASSWORDS ON U_ID=PA_ID WHERE U_LOGIN='" + login + "' AND PA_PASSWORD='" + password + "'");
            resultSet.last();
            if (resultSet.getRow() != 0) {
                role = resultSet.getInt("U_ROLE");
                id = resultSet.getInt("U_ID");
            } else {
                role = -1;
                id = -1;
            }
            sendDataToClient(outputStream, String.valueOf(role));
            sendDataToClient(outputStream, String.valueOf(id));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void getAllUsers(OutputStream outputStream) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT U_LOGIN, PA_PASSWORD, U_ROLE FROM USERS INNER JOIN PASSWORDS ON U_ID=PA_ID");
            resultSet.last();
            int rows = resultSet.getRow();
            sendDataToClient(outputStream, String.valueOf(rows));
            resultSet.first();

            //todo prettify this part of code
            String res = resultSet.getString("U_LOGIN") + " " + resultSet.getString("PA_PASSWORD") + " " + resultSet.getInt("U_ROLE");
            sendDataToClient(outputStream, res);
            while (resultSet.next()) {
                res = resultSet.getString("U_LOGIN") + " " + resultSet.getString("PA_PASSWORD") + " " + resultSet.getInt("U_ROLE");
                sendDataToClient(outputStream, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void getEvaluation(OutputStream outputStream) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT SUM(PR_AMOUNT) FROM PROFIT;");
            resultSet.last();
            //todo prettify this part of code
            String res = resultSet.getString("SUM(PR_AMOUNT)");

            resultSet = statement.executeQuery("SELECT SUM(EX_AMOUNT) FROM EXPENSES;");
            resultSet.first();
            //todo prettify this part of code
            res = res+" "+ resultSet.getString("SUM(EX_AMOUNT)");

            sendDataToClient(outputStream, res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getAllBranches(OutputStream outputStream) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM BRANCH");
            resultSet.last();
            int rows = resultSet.getRow();
            sendDataToClient(outputStream, String.valueOf(rows));
            resultSet.first();

            //todo prettify this part of code
            String res = resultSet.getInt("B_ID") + " "
                    + resultSet.getString("B_BRANCH") + " "
                    + resultSet.getString("B_CITY") + " "
                    + resultSet.getString("B_STREET") + " "
                    + resultSet.getString("B_HOUSE") + " "
                    + resultSet.getString("B_BEGIN") + " "
                    + resultSet.getString("B_END");
            sendDataToClient(outputStream, res);
            while (resultSet.next()) {
                res = resultSet.getInt("B_ID") + " "
                        + resultSet.getString("B_BRANCH") + " "
                        + resultSet.getString("B_CITY") + " "
                        + resultSet.getString("B_STREET") + " "
                        + resultSet.getString("B_HOUSE") + " "
                        + resultSet.getString("B_BEGIN") + " "
                        + resultSet.getString("B_END");
                sendDataToClient(outputStream, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getAllProfit(OutputStream outputStream) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT PR_ID, PR_NAME, PR_AMOUNT, EM_LASTNAME, PR_DATE FROM PROFIT INNER JOIN EMPLOYEE ON PR_EMPLOYEE=EM_ID");
            resultSet.last();
            int rows = resultSet.getRow();
            sendDataToClient(outputStream, String.valueOf(rows));
            resultSet.first();

            //todo prettify this part of code
            String res = resultSet.getInt("PR_ID") + " "
                    + resultSet.getString("PR_NAME") + " "
                    + resultSet.getInt("PR_AMOUNT") + " "
                    + resultSet.getString("EM_LASTNAME") + " "
                    + resultSet.getString("PR_DATE");
            sendDataToClient(outputStream, res);
            while (resultSet.next()) {
                res = resultSet.getInt("PR_ID") + " "
                        + resultSet.getString("PR_NAME") + " "
                        + resultSet.getInt("PR_AMOUNT") + " "
                        + resultSet.getString("EM_LASTNAME") + " "
                        + resultSet.getString("PR_DATE");
                sendDataToClient(outputStream, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getAllExpenses(OutputStream outputStream) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT EX_ID, EX_NAME, EX_AMOUNT, EM_LASTNAME, EX_DATE FROM EXPENSES INNER JOIN EMPLOYEE ON EX_EMPLOYEE=EM_ID");
            resultSet.last();
            int rows = resultSet.getRow();
            sendDataToClient(outputStream, String.valueOf(rows));
            resultSet.first();

            //todo prettify this part of code
            String res = resultSet.getInt("EX_ID") + " "
                    + resultSet.getString("EX_NAME") + " "
                    + resultSet.getInt("EX_AMOUNT") + " "
                    + resultSet.getString("EM_LASTNAME") + " "
                    + resultSet.getString("EX_DATE");
            sendDataToClient(outputStream, res);
            while (resultSet.next()) {
                res = resultSet.getInt("EX_ID") + " "
                        + resultSet.getString("EX_NAME") + " "
                        + resultSet.getInt("EX_AMOUNT") + " "
                        + resultSet.getString("EM_LASTNAME") + " "
                        + resultSet.getString("EX_DATE");
                sendDataToClient(outputStream, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void getProfitExpenses(OutputStream outputStream) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT SUM(PR_AMOUNT) FROM PROFIT;");

            resultSet.last();
            int rows = resultSet.getRow();
            sendDataToClient(outputStream, String.valueOf(rows));
            resultSet.first();

            //todo prettify this part of code
            int res = resultSet.getInt("SUM(PR_AMOUNT)");
            sendDataToClient(outputStream, String.valueOf(res));

            resultSet = statement.executeQuery("SELECT SUM(EX_AMOUNT) FROM EXPENSES");

            resultSet.last();
            rows = resultSet.getRow();
            sendDataToClient(outputStream, String.valueOf(rows));
            resultSet.first();

            //todo prettify this part of code
            res = resultSet.getInt("SUM(EX_AMOUNT)");
            sendDataToClient(outputStream, String.valueOf(res));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendDataToClient(OutputStream outputStream, String data) {
        try {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerUser(String login, String password) {
        try {
            statement.executeUpdate("INSERT INTO USERS (U_LOGIN, U_ROLE) VALUES ('" + login + "', 3)");
            statement.executeUpdate("INSERT INTO PASSWORDS (PA_PASSWORD) VALUES ('" + password + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertBranch(OutputStream outputStream, String queryContent) {
        try {
            System.out.println(queryContent);
            String[] arr=queryContent.split(" ");
            Branch branch = new Branch(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
            statement.executeUpdate("INSERT INTO BRANCH VALUES (" + branch.getB_id() + ", '" + branch.getBranch() + "','" + branch.getCity() + "','" + branch.getStreet() + "','" + branch.getHouse() + "','" + branch.getBegin() + "','" + branch.getEnd() + "')");
            outputStream.write("Сохранено".getBytes());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    private void insertProfit(OutputStream outputStream, String queryContent) {
        try {
            System.out.println(queryContent);
            String[] arr=queryContent.split(" ");
            Profit profit = new Profit(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]);
            statement.executeUpdate("INSERT INTO PROFIT(PR_NAME, PR_AMOUNT, PR_EMPLOYEE, PR_DATE) VALUES ('" + profit.getProfit() + "'," + profit.getAmount() + "," + profit.getEmployee() + ",'" + profit.getDate() + "')");
            outputStream.write("Сохранено".getBytes());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    private void insertExpenses(OutputStream outputStream, String queryContent) {
        try {
            System.out.println(queryContent);
            String[] arr=queryContent.split(" ");
            Expenses expenses = new Expenses(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]);
            statement.executeUpdate("INSERT INTO EXPENSES(EX_NAME, EX_AMOUNT, EX_EMPLOYEE, EX_DATE) VALUES ('" + expenses.getProfit() + "'," + expenses.getAmount() + "," + expenses.getEmployee() + ",'" + expenses.getDate() + "')");
            outputStream.write("Сохранено".getBytes());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}