package visual.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;

import stocks.Store;
import stocks.StockManager;
import transactions.TransactionsManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JFormattedTextField;

import visual.GUI;

//Class which displays adding a new Store
public class NewStore implements ActionListener{
    GridBagConstraints layoutConst = null; 
    private StockManager systemInventory;
    JFrame StoreNew;
    JLabel back;
    JButton back2Main;
    JLabel nStore;
    JFormattedTextField s;
    JButton addS;
    JLabel newStoreAddress;
    JFormattedTextField address;
    private TransactionsManager transManager;

    //Constructor which displays GUI
    public NewStore(StockManager sys, TransactionsManager tMan)
    {
        systemInventory = sys;
        transManager = tMan;
        StoreNew = new JFrame("Add A Store");
        back = new JLabel("Go Back To Main Menu");
        back2Main = new JButton("Main Menu");
        back2Main.addActionListener(this);

        nStore = new JLabel("Enter Store name: ");
        s = new JFormattedTextField();
        s.setEditable(true);
        s.setColumns(15);
        addS = new JButton("Press to add Store");
        addS.addActionListener(this);
        newStoreAddress = new JLabel("Enter Store Address: ");
        address = new JFormattedTextField();
        address.setEditable(true);
        address.setColumns(15);

        StoreNew.setLayout(new GridBagLayout());


        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        StoreNew.add(nStore, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        StoreNew.add(s, layoutConst);

        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        StoreNew.add(newStoreAddress, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        StoreNew.add(address, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 2;
        layoutConst.gridy = 1;
        StoreNew.add(addS, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        StoreNew.add(back, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        StoreNew.add(back2Main, layoutConst);

        StoreNew.getContentPane().setBackground(new Color(136,136,198) );
        StoreNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StoreNew.pack();
		StoreNew.setVisible(true);
    }


    //Action Listener for Buttons
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();
    
        if (source == back2Main) 
        {
            StoreNew.dispose();
            GUI main = new GUI(systemInventory, transManager);
        } 
        else
        {
            boolean valid = true;
            if(s.getText().equals("") || s.getText().contains("|"))
            {
                s.setText("Store name may not be empty and cannot contain the character '|'.\nPlease enter a valid store name:");
                valid = false;
            }
            if(address.getText().equals("") || address.getText().contains("|"))
            {
                address.setText("Address may not be empty and cannot contain the character '|'.\nPlease enter a valid address:");
                valid = false;
            }
            else if(valid == true)
            {
                Store newS = new Store(s.getText(), address.getText());
                systemInventory.addStore(newS);
                s.setText("Store Added!");
                address.setText("Clear both fields to add another");
            }
        }
    
    }



}
