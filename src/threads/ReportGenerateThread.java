package threads;

import stocks.StockManager;
import transactions.TransactionsManager;
import visual.util.MakeReportPanels;
import javax.swing.JPanel;

//Thread Class which generates multiple reports through threading, uses MakeReportPanels class
public class ReportGenerateThread implements Runnable {
    JPanel view;
    Thread t;
    int count;
    String tName;
    Integer type;
    MakeReportPanels generate;
    int num;
    int index;
    int[] dropBox;
    StockManager sys;
    TransactionsManager tMan;
    
    //Constructor which initializes variables
    public ReportGenerateThread(Integer rType, int[] selection, StockManager stocks, TransactionsManager trans)
    {
        generate = new MakeReportPanels();
        type = rType;
        dropBox = selection;
        sys = stocks;
        tMan = trans;
        tName = generate.getName(type) + " Update Thread";
        t = new Thread(this, tName);
        t.start();
    }


    public void run() 
    {
        try 
        {
            if(type == 1)
            {
                System.out.println(tName + " Generating...");
                generate.make1(sys);
                Thread.sleep(1000);
            }
            else if(type == 2)
            {
                System.out.println(tName + " Generating...");
                generate.make2(sys);
                Thread.sleep(1000);
            }
            else if(type == 3)
            {
                System.out.println(tName + " Generating...");
                generate.make3(tMan);
                Thread.sleep(1000);
            }
            else if(type == 4)
            {
                System.out.println(tName + " Generating...");
                generate.make4(sys, tMan, dropBox[0]);
                Thread.sleep(1000);
            }
            else if(type == 5)
            {
                System.out.println(tName + " Generating...");
                generate.make5(tMan);
                Thread.sleep(1000);
            }
            else if(type == 6)
            {
                System.out.println(tName + " Generating...");
                generate.make6(tMan);
                Thread.sleep(1000);
            }
            else if(type == 7)
            {
                System.out.println(tName + " Generating...");
                generate.make7(tMan, dropBox[1]);
                Thread.sleep(1000);
            }
            else if(type == 8)
            {
                System.out.println(tName + " Generating...");
                generate.make8(tMan, dropBox[2]);
                Thread.sleep(1000);
            }
            else if(type == 9)
            {
                System.out.println(tName + " Generating...");
                generate.make9(sys, tMan, dropBox[3]);
                Thread.sleep(1000);
            }
            else if(type == 10)
            {
                System.out.println(tName + " Generating...");
                generate.make10(sys, tMan, dropBox[4]);
                Thread.sleep(1000);
            }
            else if(type == 11)
            {
                System.out.println(tName + " Generating...");
                generate.make11(tMan);
                Thread.sleep(1000);
            }
            else if(type == 12)
            {
                System.out.println(tName + " Generating...");
                generate.make12(tMan);
                Thread.sleep(1000);
            }
        }
       catch (InterruptedException e) {
            System.out.println(tName + "Interrupted");
       }
       System.out.println(tName + " Complete");
    }

}
