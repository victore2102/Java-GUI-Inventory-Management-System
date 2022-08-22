package visual.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

import stocks.Product;
import stocks.StockManager;
import transactions.TransactionsManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JFormattedTextField;

import visual.GUI;

//Class for display of Adding a Product
public class NewProduct implements ActionListener{
    GridBagConstraints layoutConst = null; 
    JLabel back;
    JButton back2Main;
    JFrame ProductNew;
    JLabel newProduct;
    JTextField p;
    JButton addP;
    JLabel newProductAmt;
    JTextField amt;
    JButton addAmt;
    private StockManager systemInventory;
    private TransactionsManager transManager;

    //Constructor which displays GUI
    public NewProduct(StockManager sys, TransactionsManager tMan)
    {
        systemInventory = sys;
        transManager = tMan;
        ProductNew = new JFrame("Add A Product");
        back = new JLabel("Go Back To Main Menu");
        back2Main = new JButton("Main Menu");
        back2Main.addActionListener(this);

        newProduct = new JLabel("Enter Product Name: ");
        p = new JFormattedTextField();
        p.setEditable(true);
        p.setColumns(15);
        addP = new JButton("Press to add Product");
        addP.addActionListener(this);
        newProductAmt = new JLabel("Enter Product Amount: ");
        amt = new JFormattedTextField();
        amt.setEditable(true);
        amt.setColumns(15);

        ProductNew.setLayout(new GridBagLayout());


        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        ProductNew.add(newProduct, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        ProductNew.add(p, layoutConst);

        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        ProductNew.add(newProductAmt, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        ProductNew.add(amt, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 2;
        layoutConst.gridy = 1;
        ProductNew.add(addP, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        ProductNew.add(back, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        ProductNew.add(back2Main, layoutConst);

        ProductNew.getContentPane().setBackground(new Color(136,136,198) );
        ProductNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ProductNew.pack();
		ProductNew.setVisible(true);

    }

    //Action Listener for button use
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();
    
        if (source == back2Main) 
        {
            ProductNew.dispose();
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
            if(p.getText().equals("") || p.getText().contains("|"))
            {
                p.setText("Product name may not be empty and cannot contain the character '|'.\nPlease enter a valid product name:");
                valid = false;
            }
            else if(valid == true)
            {
                Product newP = new Product(p.getText(), Integer.parseInt(amount));
                systemInventory.addProduct(newP);
                p.setText("Product Added!");
                amt.setText("Clear both fields to add another");
            }
        }
    
    }


}
