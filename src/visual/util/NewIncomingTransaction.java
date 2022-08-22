package visual.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

import stocks.Product;
import stocks.StockManager;
import transactions.IncomingTransaction;
import transactions.TransactionsManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import visual.GUI;

//GUI interface for Incoming Transactions
public class NewIncomingTransaction implements ActionListener{
    
    GridBagConstraints layoutConst = null; 
    JLabel available;
    JComboBox<String> choices;
    JButton addIncoming;
    JLabel back;
    JButton back2Main;
    JFrame IncomingNew;
    JLabel IncomingAmt;
    JTextField amt;
    private StockManager systemInventory;
    private TransactionsManager transManager;
    private IncomingTransaction transaction;

    //Constructor which displays GUI
    public NewIncomingTransaction(StockManager sys, TransactionsManager tMan)
    {
        systemInventory = sys;
        transManager = tMan;
        transaction  = new IncomingTransaction();
        IncomingNew = new JFrame("Perform Incoming Transaction");
        back = new JLabel("Go Back To Main Menu");
        back2Main = new JButton("Main Menu");
        back2Main.addActionListener(this);
        available = new JLabel("List of available Products. Select 1: ");

        String[] allProducts = new String[systemInventory.getProductList().length];
        int i = 0;
        for (Product p : systemInventory.getProductList())// print products in the form "id: name" for easy selection
        {
            allProducts[i] = p.getID() + ": " + p.getName();
            i++;
        }
        choices = new JComboBox<String>(allProducts);

        IncomingAmt = new JLabel("Enter the amount you would like to add to this transaction");
        amt = new JFormattedTextField();
        amt.setEditable(true);
        amt.setColumns(15);
        addIncoming = new JButton("Press to add to Transaction");
        addIncoming.addActionListener(this);


        IncomingNew.setLayout(new GridBagLayout());


        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        IncomingNew.add(available, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        IncomingNew.add(choices, layoutConst);

        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        IncomingNew.add(IncomingAmt, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        IncomingNew.add(amt, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 2;
        layoutConst.gridy = 1;
        IncomingNew.add(addIncoming, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        IncomingNew.add(back, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        IncomingNew.add(back2Main, layoutConst);

        IncomingNew.getContentPane().setBackground(new Color(136,136,198) );
        IncomingNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IncomingNew.pack();
		IncomingNew.setVisible(true);

    }

    //Action Listener for actions following button use
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();
    
        if (source == back2Main) 
        {
            transManager.addTransaction(transaction);
            systemInventory.saveUpdatedStock();
            IncomingNew.dispose();
            GUI main = new GUI(systemInventory, transManager);
        }
        else
        {
            boolean valid = true;
            String amount = amt.getText();
            for(int i = 0; i<amount.length(); i++)
            {
                if(!(amount.charAt(i) >= '0' && amount.charAt(i) <= '9'))
                {
                    amt.setText("Invalid input. Enter only numeric digits");
                    valid = false;
                    break;
                }
            }

            if(valid == true)
            {
                String selected = choices.getSelectedItem().toString();
                String c = "";
                for(int i = 0; i<selected.length(); i++)
                {
                    if(selected.charAt(i) == ':')
                    {
                        break;
                    }
                    else
                    {
                        c = c + selected.charAt(i);
                    }
                }
                Product pr = systemInventory.findProductByID(Integer.parseInt(c));
                transaction.addProduct(pr, Integer.parseInt(amount));
                amt.setText("Product Added to Transaction!");
            }
        }
    }


}
