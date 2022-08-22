package visual.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

import stocks.Product;
import stocks.Store;
import stocks.StockManager;
import transactions.OutgoingTransaction;
import transactions.TransactionsManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import visual.GUI;

//Class which displays GUI for Outgoing Transaction
public class NewOutgoingTransaction implements ActionListener{
    GridBagConstraints layoutConst = null; 
    JLabel availableP;
    JLabel availableS;
    JComboBox<String> choicesP;
    JComboBox<String> Schoices;
    JButton addOutgoing;
    JLabel back;
    JButton back2Main;
    JFrame OutgoingNew;
    JLabel OutgoingAmt;
    JTextField amt;
    private String recent;
    private StockManager systemInventory;
    private TransactionsManager transManager;
    private OutgoingTransaction transaction;

    //Constructor which displays GUI
    public NewOutgoingTransaction(StockManager sys, TransactionsManager tMan)
    {
        systemInventory = sys;
        transManager = tMan;
        
        OutgoingNew = new JFrame("Perform Outgoing Transaction");
        back = new JLabel("Go Back To Main Menu");
        back2Main = new JButton("Main Menu");
        back2Main.addActionListener(this);
        availableP = new JLabel("List of available Products. Select 1: ");
        availableS = new JLabel("List of available Stores. Select 1: ");
        recent = "";
        String[] allProducts = new String[systemInventory.getProductList().length];
        int i = 0;
        for (Product p : systemInventory.getProductList())// print products in the form "id: name" for easy selection
        {
            allProducts[i] = p.getID() + ": " + p.getName();
            i++;
        }
        choicesP = new JComboBox<String>(allProducts);

        String[] allStores = new String[systemInventory.getStoreList().length];
        i = 0;
        for (Store s : systemInventory.getStoreList())// print store in the form "id: name" for easy selection
        {
            allStores[i] = s.getID() + ": " + s.getName();
            i++;
        }
        Schoices = new JComboBox<String>(allStores);

        OutgoingAmt = new JLabel("Enter the amount you would like to send");
        amt = new JFormattedTextField();
        amt.setEditable(true);
        amt.setColumns(15);
        addOutgoing = new JButton("Press to send to Store");
        addOutgoing.addActionListener(this);


        OutgoingNew.setLayout(new GridBagLayout());


        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        OutgoingNew.add(availableS, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        OutgoingNew.add(Schoices, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        OutgoingNew.add(availableP, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        OutgoingNew.add(choicesP, layoutConst);

        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        OutgoingNew.add(OutgoingAmt, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        OutgoingNew.add(amt, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 2;
        layoutConst.gridy = 2;
        OutgoingNew.add(addOutgoing, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        OutgoingNew.add(back, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        OutgoingNew.add(back2Main, layoutConst);

        OutgoingNew.getContentPane().setBackground(new Color(136,136,198) );
        OutgoingNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OutgoingNew.pack();
		OutgoingNew.setVisible(true);
    }
    

    //Action listener for button use
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();
    
        if (source == back2Main) 
        {
            if(!recent.equals(""))
            {
                transManager.addTransaction(transaction);
            }
            systemInventory.saveUpdatedStock();
            OutgoingNew.dispose();
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

            String selected = choicesP.getSelectedItem().toString();
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
            if(!(Integer.parseInt(amount) <= pr.getCount()))
            {
                amt.setText("Enter a value <= " + pr.getCount());
                valid = false;
            }

            if(valid == true)
            {
                selected = Schoices.getSelectedItem().toString();
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
                Store st = systemInventory.findStoreByID(Integer.parseInt(sID));
                if(recent.equals(""))
                {
                    recent = sID;
                    transaction = new OutgoingTransaction(st);
                }
                if(recent.equals(sID))
                {
                    transaction.addProduct(pr, Integer.parseInt(amount));
                }
                else
                {
                    transManager.addTransaction(transaction);
                    transaction = new OutgoingTransaction(st);
                    transaction.addProduct(pr, Integer.parseInt(amount));
                }
                amt.setText("Product added to " + selected + " Transaction");
            }
        }
    }


}
