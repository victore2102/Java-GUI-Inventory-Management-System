package threads;

import stocks.Product;

//Thread Class which increments/decremets product count through threading
public class UpdateProductThread implements Runnable {
    Product p;
    Thread t;
    int count;
    String tName;
    char type;
    

    //Constructor which initializes variables
    public UpdateProductThread(Product prod, int c, char option)
    {
        p = prod;
        count = c;
        type = option;
        tName = p.getName() + " Update Thread";
        t = new Thread(this, tName);
        t.start();
    }


    public void run() 
    {
        try 
        {
            if(type == 'i')
            {
                p.incrementCount(count);
                Thread.sleep(1000);
            }
            else
            {
                p.decrementCount(count);
                Thread.sleep(1000);
            }
       }
       catch (InterruptedException e) {
            System.out.println(tName + "Interrupted");
       }
       System.out.println(tName + " Complete");
    }
}
