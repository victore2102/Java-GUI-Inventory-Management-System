package transactions;

import java.util.Date;

import stocks.Product;

import threads.UpdateProductThread;

public class IncomingTransaction extends Transaction
{
	/**
	 * Loops all products added to the transaction and increments their each of their stock counts as specified in the transaction.
	 */
	private UpdateProductThread update;
	
	public IncomingTransaction(Date date) {
		super(date);
	}
	
	public IncomingTransaction() {
		super();
	}
	
	//Method that calls on Update Product thread to concurrently increment the count of products
	@Override
	public void updateProductStock()
	{
		for (Product p : productList.keySet())
			update = new UpdateProductThread(p, productList.get(p), 'i');
	}
}