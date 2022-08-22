package main;
import stocks.StockManager;
import transactions.TransactionsManager;
import visual.GUI;


public class Main
{
	/**
	 * Manages the data for stores and products in the system.
	 */
	public StockManager systemInventory;

	/**
	 * Manages transactions in the system.
	 */
	public TransactionsManager transManager;


	private Main()
	{
		systemInventory = new StockManager();
		transManager = new TransactionsManager(systemInventory);
	}

	public static void main(String args[]) throws Exception
	{
		Main main = new Main();
		main.runGUI();
	}

	//Runs the basic program loop for interacting with the system.
	public void runGUI()
	{
		GUI mainMenu = new GUI(systemInventory, transManager);
	}

}