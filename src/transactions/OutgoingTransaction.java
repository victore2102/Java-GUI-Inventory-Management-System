package transactions;

import java.util.Date;

import stocks.Product;
import stocks.Store;

import threads.UpdateProductThread;


public class OutgoingTransaction extends Transaction
{
	/**
	 * The store on which the transaction is occurring.
	 */

	private UpdateProductThread update;

	private Store store;

	/**
	 * Default constructor
	 */
	public OutgoingTransaction(Store s)
	{
		super();
		store = s;
	}
	
	public OutgoingTransaction(Store s, Date date)
	{
		super(date);
		store = s;
	}

	/**
	 * Loops all products added to the transaction and decrements their each of their stock counts as specified in the transaction, simulating sending that stock to a store.
	 * Note: negativity of stocks is not tracked or prevented in any way.
	 */

	//Method that calls on Update Product thread to concurrently decrement the count of products
	@Override
	public void updateProductStock()
	{
		for (Product p : productList.keySet())
		{
			update = new UpdateProductThread(p, productList.get(p), 'o');
		}
	}

	public int getTransactionStoreID()
	{
		return store.getID();
	}

	public String toString()
	{
		return this.getTransactionStoreID() + "," + super.toString();
	}
}