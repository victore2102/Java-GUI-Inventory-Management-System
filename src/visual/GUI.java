package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import stocks.StockManager;
import transactions.TransactionsManager;
import visual.util.NewIncomingTransaction;
import visual.util.NewOutgoingTransaction;
import visual.util.NewProduct;
import visual.util.NewReportsMenu;
import visual.util.NewStore;


public class GUI implements ActionListener{

    private JButton productButton;
    private JLabel productLabel;
    private JButton storeButton;
    private JLabel storeLabel;
    private JButton incomingTransButton;
    private JLabel incomingTransLabel;
    private JButton outgoingTransButton;
    private JLabel outgoingTransLabel;
    private JButton reportButton;
    private JLabel reportLabel;
    private JLabel menuTitle;
    private JLabel credit;
    private JFrame menu;
    private StockManager systemInventory;
    private TransactionsManager transManager;

    //Constructor that adds elements to Main Menu GUI and shows GUI
    public GUI(StockManager sys, TransactionsManager tMan)
    {
        systemInventory = sys;
        transManager = tMan;
        GridBagConstraints layoutConst = null;
        menu = new JFrame("Inventory Management System Menu");
        menuTitle = new JLabel("Welcome to the Main Menu");
        menuTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        credit = new JLabel("Designed by: Victor Ekpenyong");
        credit.setFont(new Font("Serif", Font.PLAIN, 12));
        productLabel = new JLabel("Add A Product");
        storeLabel = new JLabel("Add A Store");
        incomingTransLabel = new JLabel("Perform Incoming Transaction");
        outgoingTransLabel = new JLabel("Perform Outgoing Transaction");
        reportLabel = new JLabel("Generate Reports");

        productButton = new JButton("New Product!");
        productButton.addActionListener(this);
        storeButton = new JButton("New Store!");
        storeButton.addActionListener(this);
        incomingTransButton = new JButton("New Incoming Transaction!");
        incomingTransButton.addActionListener(this);
        outgoingTransButton = new JButton("New Outgoing Transaction!");
        outgoingTransButton.addActionListener(this);
        reportButton = new JButton("Reports!");
        reportButton.addActionListener(this);

        
        menu.setLayout(new GridBagLayout());

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        menu.add(menuTitle, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        menu.add(credit, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        menu.add(productLabel, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 2;
        menu.add(productButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        menu.add(storeLabel, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 3;
        menu.add(storeButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        menu.add(incomingTransLabel, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        menu.add(incomingTransButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 5;
        menu.add(outgoingTransLabel, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 5;
        menu.add(outgoingTransButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 1);
        layoutConst.gridx = 0;
        layoutConst.gridy = 6;
        menu.add(reportLabel, layoutConst);
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 5, 10, 10);
        layoutConst.gridx = 1;
        layoutConst.gridy = 6;
        menu.add(reportButton, layoutConst);

        menu.getContentPane().setBackground(new Color(222,127,107) );
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.pack();
		menu.setVisible(true);
    }
    

    //Action Listener for buttons
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();
    
        if (source == productButton) 
        {
            menu.dispose();
            NewProduct p = new NewProduct(systemInventory, transManager);
        } 
        else if (source == storeButton) 
        {
            menu.dispose();
            NewStore s = new NewStore(systemInventory, transManager);
        } 
        else if (source == incomingTransButton)
        {
            menu.dispose();
            NewIncomingTransaction i = new NewIncomingTransaction(systemInventory, transManager);
        } 
        else if (source == outgoingTransButton)
        {
            menu.dispose();
            NewOutgoingTransaction o = new NewOutgoingTransaction(systemInventory, transManager);
        }
        else
        {
            menu.dispose();
            NewReportsMenu r = new NewReportsMenu(systemInventory, transManager);
        }
    
    }


}
