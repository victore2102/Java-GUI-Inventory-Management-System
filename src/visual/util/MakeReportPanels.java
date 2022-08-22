package visual.util;

import java.util.Map;
import java.lang.String;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

import stocks.Product;
import stocks.StockManager;
import transactions.TransactionsManager;
import reports.AllTransactionForProductReport;
import reports.HighVolumeReport;
import reports.ProductByStoreReport;
import reports.TransactionsByMonthReport;
import transactions.Transaction;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.*;


//Class which makes the different types of reports and makes them visible
public class MakeReportPanels {
    
    GridBagConstraints layoutConst = null;
    int index;
    public MakeReportPanels()
    {

    }

    //Method that returns the name of the report that will be generated and used for threading
    public String getName(Integer type)
    {
        if(type == 1)
            return "All Products";
        else if(type == 2)
            return "All Available Products";
        else if(type == 3)
            return "All Stores Products";
        else if(type == 4)
            return "Specific Store Products";
        else if(type == 5)
            return "All Incoming Transactions Report";
        else if(type == 6)
            return "All Outgoing Transactions Report";
        else if(type == 7)
            return "All Incoming Transactions Report";
        else if(type == 8)
            return "All Outgoing Transactions Report";
        else if(type == 9)
            return "Specific Product Incoming Transactions Report";
        else if(type == 10)
            return "Specific Product Outgoing Transactions Report";
        else if(type == 11)
            return "High Volume Products Incoming Report";
        else if(type == 12)
            return "High Volume Products Outgoing Report";
        else
            return "";
    }

    //Methods that make different reports GUI
    public void make1(StockManager systemInventory)
    {
        JFrame view = new JFrame("All Products Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Products Report");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Product p : systemInventory.getProductList())// print products in the form "id: name" for easy selection
        {
            if(left == true)
            {
                JLabel prod = new JLabel(p.getID() + ": " + p.getName());
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                JLabel prod = new JLabel(p.getID() + ": " + p.getName());
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                mid = false;
            }
            else
            {
                JLabel prod = new JLabel(p.getID() + ": " + p.getName());
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make2(StockManager systemInventory)
    {
        JFrame view = new JFrame("All Available Products Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - Available Products Report");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Product p : systemInventory.getProductList())// print products in the form "id: name" for easy selection
        {
            if (p.getCount() > 0)
            {
                if(left == true)
                {
                    JLabel prod = new JLabel(p.getID() + ": " + p.getName());
                    layoutConst = new GridBagConstraints();
                    layoutConst.insets = new Insets(10, 10, 10, 1);
                    layoutConst.gridx = 0;
                    layoutConst.gridy = i;
                    view.add(prod, layoutConst);
                    left = false;
                    mid = true;
                }
                else if(mid == true)
                {
                    JLabel prod = new JLabel(p.getID() + ": " + p.getName());
                    layoutConst = new GridBagConstraints();
                    layoutConst.insets = new Insets(10, 10, 10, 1);
                    layoutConst.gridx = 1;
                    layoutConst.gridy = i;
                    view.add(prod, layoutConst);
                    mid = false;
                }
                else
                {
                    JLabel prod = new JLabel(p.getID() + ": " + p.getName());
                    layoutConst = new GridBagConstraints();
                    layoutConst.insets = new Insets(10, 10, 10, 1);
                    layoutConst.gridx = 2;
                    layoutConst.gridy = i;
                    view.add(prod, layoutConst);
                    left = true;
                    i++;
                }
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make3(TransactionsManager transManager)
    {
        JFrame view = new JFrame("All Stores Product Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        Map<Product, Integer> productList = new ProductByStoreReport(-1, transManager).returnProductList();
        JLabel all = new JLabel(" - All Stores Products Report");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Product p : productList.keySet())
        {
            if(left == true)
            {
                JLabel prod = new JLabel(p.getName() + "(" + p.getID() + "): " + productList.get(p));
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                JLabel prod = new JLabel(p.getName() + "(" + p.getID() + "): " + productList.get(p));
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                mid = false;
            }
            else
            {
                JLabel prod = new JLabel(p.getName() + "(" + p.getID() + "): " + productList.get(p));
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make4(StockManager systemInventory, TransactionsManager transManager, int selection)
    {
        JFrame view = new JFrame(systemInventory.findStoreByID(selection).getName() + " Store Products Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        Map<Product, Integer> productList = new ProductByStoreReport(selection, transManager).returnProductList();
        JLabel all = new JLabel(" - " + systemInventory.findStoreByID(selection).getName() + " Store Products Report");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Product p : productList.keySet())
        {
            if(left == true)
            {
                JLabel prod = new JLabel(p.getName() + "(" + p.getID() + "): " + productList.get(p));
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                JLabel prod = new JLabel(p.getName() + "(" + p.getID() + "): " + productList.get(p));
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                mid = false;
            }
            else
            {
                JLabel prod = new JLabel(p.getName() + "(" + p.getID() + "): " + productList.get(p));
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(prod, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make5(TransactionsManager transManager)
    {
        JFrame view = new JFrame("All Incoming Transactions Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Incoming Transactions Report");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        TransactionsByMonthReport allT = new TransactionsByMonthReport('i', -1, transManager);
        for (Transaction t : allT.returnTrans())
        {
            if(left == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                mid = false;
            }
            else
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make6(TransactionsManager transManager)
    {
        JFrame view = new JFrame("All Outgoing Transactions Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Outgoing Transactions Report");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        TransactionsByMonthReport allT = new TransactionsByMonthReport('o', -1, transManager);
        for (Transaction t : allT.returnTrans())
        {
            if(left == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                mid = false;
            }
            else
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make7(TransactionsManager transManager, int selection)
    {
        TransactionsByMonthReport allT = new TransactionsByMonthReport('i', selection-1, transManager);
        JFrame view = new JFrame("All Incoming Transactions Report for " + allT.getMonthNameHelp(selection-1) + " Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Incoming Transactions Report for " + allT.getMonthNameHelp(selection-1));
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Transaction t : allT.returnTrans())
        {
            if(left == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                mid = false;
            }
            else
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make8(TransactionsManager transManager, int selection)
    {
        TransactionsByMonthReport allT = new TransactionsByMonthReport('o', selection-1, transManager);
        JFrame view = new JFrame("All Outgoing Transactions Report for " + allT.getMonthNameHelp(selection-1) + " Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Outgoing Transactions Report for " + allT.getMonthNameHelp(selection-1));
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Transaction t : allT.returnTrans())
        {
            if(left == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                mid = false;
            }
            else
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make9(StockManager systemInventory, TransactionsManager transManager, int selection)
    {
        Product p = systemInventory.findProductByID(selection);
        AllTransactionForProductReport allT = new AllTransactionForProductReport('i', p, transManager);
        JFrame view = new JFrame("All Incoming Transactions Report for " + p.getName() + " Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Incoming Transactions Report for " + p.getName());
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Transaction t : allT.returnTrans())
        {
            if(left == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                mid = false;
            }
            else
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make10(StockManager systemInventory, TransactionsManager transManager, int selection)
    {
        Product p = systemInventory.findProductByID(selection);
        AllTransactionForProductReport allT = new AllTransactionForProductReport('o', p, transManager);
        JFrame view = new JFrame("All Outgoing Transactions Report for " + p.getName() + " Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        JLabel all = new JLabel(" - All Outgoing Transactions Report for " + p.getName());
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        boolean left = true;
        boolean mid = false;
        for (Transaction t : allT.returnTrans())
        {
            if(left == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 0;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                mid = false;
            }
            else
            {
                String input = allT.getFormat(t);
                JLabel allIn = new JLabel(input);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = i;
                view.add(allIn, layoutConst);
                left = true;
                i++;
            }
        }
        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }

    public void make11(TransactionsManager transManager)
    {
        JFrame allview = new JFrame("High Volume Products Incoming Report Generated");
        JPanel view = new JPanel();
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        HighVolumeReport allT = new HighVolumeReport('i', transManager);
        JLabel all = new JLabel(" - High Volume Products Incoming Report ");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 1;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        
        String[] input = allT.returnOutput().split(" ");
        boolean left = true;
        boolean mid = false;
        int v = 1;
        for(int j = 0; j< input.length; j++)
        {
            if(left == true)
            {
                JLabel allIn = new JLabel(input[j]);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 1;
                layoutConst.gridy = v;
                view.add(allIn, layoutConst);
                left = false;
                mid = true;
            }
            else if(mid == true)
            {
                JLabel allIn = new JLabel(input[j]);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 2;
                layoutConst.gridy = v;
                view.add(allIn, layoutConst);
                left = false;
                mid = false;
            }
            else
            {
                JLabel allIn = new JLabel(input[j]);
                layoutConst = new GridBagConstraints();
                layoutConst.insets = new Insets(10, 10, 10, 1);
                layoutConst.gridx = 3;
                layoutConst.gridy = v;
                view.add(allIn, layoutConst);
                left = false;
                left = true;
                v++;
            }
        }

        view.setBackground(new Color(138,212,167));
        JScrollPane scroll = new JScrollPane(view);
        allview.getContentPane().add(scroll);
        allview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        allview.pack();
        allview.setVisible(true);
    }

    public void make12(TransactionsManager transManager)
    {
        JFrame view = new JFrame("High Volume Products Outgoing Report Generated");
        view.setLayout(new GridBagLayout()); 
        int i = 0;
        HighVolumeReport allT = new HighVolumeReport('o', transManager);
        JLabel all = new JLabel(" - High Volume Products Outgoing Report ");
        all.setFont(new Font("Serif", Font.PLAIN, 18));
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 1;
        layoutConst.gridy = i;
        i++;
        view.add(all, layoutConst);
        
        String input = allT.returnOutput();
        JLabel allIn = new JLabel(input);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 1;
        layoutConst.gridy = i;
        view.add(allIn, layoutConst);

        view.getContentPane().setBackground(new Color(138,212,167));
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.pack();
        view.setVisible(true);
    }





}
