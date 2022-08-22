package visual.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import stocks.Product;
import stocks.Store;
import stocks.StockManager;
import transactions.TransactionsManager;
import threads.ReportGenerateThread;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

import visual.GUI;

//Class which displays GUI of Reports sub menu
public class NewReportsMenu implements ActionListener{
    
    private StockManager systemInventory;
    private TransactionsManager transManager;
    private ReportGenerateThread report;
    JFrame reportSubMenu;
    JLabel rSub;
    JLabel back;
    JButton back2Main;
    JCheckBox allP;
    JCheckBox availableP;
    JCheckBox allStore;
    JCheckBox oneStore;
    JCheckBox allIncoming;
    JCheckBox allOutgoing;
    JCheckBox monthIncoming;
    JCheckBox monthOutgoing;
    JCheckBox productIncomingR;
    JCheckBox productOutgoingR;
    JCheckBox highPIncoming;
    JCheckBox highPOutgoing;
    JComboBox<String> Schoices;
    JComboBox<String> InchoicesP;
    JComboBox<String> OutchoicesP;
    JButton generateReports;
    JComboBox<Integer>monthsIn;
    JComboBox<Integer>monthsOut;

    //Constructor which displays GUI
    public NewReportsMenu(StockManager sys, TransactionsManager tMan)
    {
        GridBagConstraints layoutConst = null;
        systemInventory = sys;
        transManager = tMan;
        reportSubMenu = new JFrame("Reports Sub - Menu");
        back = new JLabel("Go Back To Main Menu");
        back2Main = new JButton("Main Menu");
        back2Main.addActionListener(this);

        rSub = new JLabel("Reports Sub Menu : Select The Reports You'd like to Generate");
        rSub.setFont(new Font("Serif", Font.PLAIN, 20));

        allP = new JCheckBox("1 - All Products Report");
        availableP = new JCheckBox("2 - Available Products Report");
        allStore = new JCheckBox("3 - All Stores Products Report");
        oneStore = new JCheckBox("4 - Specific Store Products Report. Select One");
        String[] allStores = new String[systemInventory.getStoreList().length];
        int i = 0;
        for (Store s : systemInventory.getStoreList())// print store in the form "id: name" for easy selection
        {
            allStores[i] = s.getID() + ": " + s.getName();
            i++;
        }
        Schoices = new JComboBox<String>(allStores);

        allIncoming = new JCheckBox("5 - All Incoming Transactions Report");
        allOutgoing = new JCheckBox("6 - All Outgoing Transactions Report");
        monthIncoming = new JCheckBox("7 - Specific Month Incoming Transactions Report : 1=January, 12=December");
        Integer[] allMonths = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        monthsIn = new JComboBox<Integer>(allMonths);
        monthsOut = new JComboBox<Integer>(allMonths);
        monthOutgoing = new JCheckBox("8 - Specific Month Outgoing Transactions Report : 1=January, 12=December");
        productIncomingR = new JCheckBox("9 - Specific Product Incoming Transactions Report");
        String[] allProducts = new String[systemInventory.getProductList().length];
        i = 0;
        for (Product p : systemInventory.getProductList())// print products in the form "id: name" for easy selection
        {
            allProducts[i] = p.getID() + ": " + p.getName();
            i++;
        }
        InchoicesP = new JComboBox<String>(allProducts);
        OutchoicesP = new JComboBox<String>(allProducts);

        productOutgoingR = new JCheckBox("10 - Specific Product Outgoing Transactions Report");
        highPIncoming = new JCheckBox("11 - High Volume Products Incoming Report");
        highPOutgoing = new JCheckBox("12 - High Volume Products Outgoing Report");
        generateReports = new JButton("Generate Reports!");
        generateReports.addActionListener(this);

        reportSubMenu.setLayout(new GridBagLayout());

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        reportSubMenu.add(rSub, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        reportSubMenu.add(allP, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        reportSubMenu.add(availableP, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        reportSubMenu.add(allStore, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        reportSubMenu.add(oneStore, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        reportSubMenu.add(Schoices, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 5;
        reportSubMenu.add(allIncoming, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 6;
        reportSubMenu.add(allOutgoing, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 7;
        reportSubMenu.add(monthIncoming, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 7;
        reportSubMenu.add(monthsIn, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 8;
        reportSubMenu.add(monthOutgoing, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 8;
        reportSubMenu.add(monthsOut, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 9;
        reportSubMenu.add(productIncomingR, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 9;
        reportSubMenu.add(InchoicesP, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 10;
        reportSubMenu.add(productOutgoingR, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 10;
        reportSubMenu.add(OutchoicesP, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 11;
        reportSubMenu.add(highPIncoming, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 12;
        reportSubMenu.add(highPOutgoing, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 2;
        layoutConst.gridy = 13;
        reportSubMenu.add(generateReports, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 14;
        reportSubMenu.add(back, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 14;
        reportSubMenu.add(back2Main, layoutConst);


        reportSubMenu.getContentPane().setBackground(new Color(203,133,167) );
        reportSubMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reportSubMenu.pack();
		reportSubMenu.setVisible(true);

    }



    //Action Listener for button use
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();
    
        if (source == back2Main) 
        {
            reportSubMenu.dispose();
            GUI main = new GUI(systemInventory, transManager);
        }
        else
        {
            Vector<Integer> reportsToView = new Vector<Integer>();
            int[] selection = new int[5];
            if(allP.isSelected())
            {
                reportsToView.add(1);
            }
            if(availableP.isSelected())
            {
                reportsToView.add(2);
            }
            if(allStore.isSelected())
            {
                reportsToView.add(3);
            }
            if(oneStore.isSelected())
            {
                reportsToView.add(4);
                String selected = Schoices.getSelectedItem().toString();
                String sID = "";
                for(int i = 0; i<selected.length(); i++)
                {
                    if(selected.charAt(i) == ':')
                    {
                        break;
                    }
                    else
                    {
                        sID = sID + selected.charAt(i);
                    }
                }
                selection[0] = Integer.parseInt(sID);
            }
            if(allIncoming.isSelected())
            {
                reportsToView.add(5);
            }
            if(allOutgoing.isSelected())
            {
                reportsToView.add(6);
            }
            if(monthIncoming.isSelected())
            {
                reportsToView.add(7);
                selection[1] = (int)monthsIn.getSelectedItem();
            }
            if(monthOutgoing.isSelected())
            {
                reportsToView.add(8);
                selection[2] = (int)monthsOut.getSelectedItem();
            }
            if(productIncomingR.isSelected())
            {
                reportsToView.add(9);
                String selected = InchoicesP.getSelectedItem().toString();
                String pID = "";
                for(int i = 0; i<selected.length(); i++)
                {
                    if(selected.charAt(i) == ':')
                    {
                        break;
                    }
                    else
                    {
                        pID = pID + selected.charAt(i);
                    }
                }
                selection[3] = Integer.parseInt(pID);
            }
            if(productOutgoingR.isSelected())
            {
                reportsToView.add(10);
                String selected = OutchoicesP.getSelectedItem().toString();
                String pID = "";
                for(int i = 0; i<selected.length(); i++)
                {
                    if(selected.charAt(i) == ':')
                    {
                        break;
                    }
                    else
                    {
                        pID = pID + selected.charAt(i);
                    }
                }
                selection[4] = Integer.parseInt(pID);
            }
            if(highPIncoming.isSelected())
            {
                reportsToView.add(11);
            }
            if(highPOutgoing.isSelected())
            {
                reportsToView.add(12);
            }
            for(int i = 0; i<reportsToView.size(); i++)
            {
                report = new ReportGenerateThread(reportsToView.elementAt(i), selection, systemInventory, transManager);
            }
        }

    }













}
