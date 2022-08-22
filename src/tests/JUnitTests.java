package tests;


import stocks.Product;
import stocks.Store;
import stocks.StockManager;
import transactions.IncomingTransaction;
import transactions.OutgoingTransaction;
import transactions.Transaction;
import transactions.TransactionsManager;
import org.junit.Test;

import reports.AllTransactionForProductReport;
import reports.ProductByStoreReport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.List;
import java.util.Map;


public class JUnitTests {

    @Test
    public void testing()
    {
        StockManager testStock = new StockManager();
        TransactionsManager testManager = new TransactionsManager(testStock);
        IncomingTransaction incoming = new IncomingTransaction();
        Product incomingTest = new Product("JUnit Test Product", 1);
        testStock.addProduct(incomingTest);
        incoming.addProduct(incomingTest, 16);
        testManager.addTransaction(incoming);
        testStock.saveUpdatedStock();

        Store outgoingTestStore = new Store("JUnit Test Store", "Test Address");
        testStock.addStore(outgoingTestStore);
        OutgoingTransaction outgoing = new OutgoingTransaction(outgoingTestStore);
        Product outgoingTest = new Product("JUnit Test Product", 20);
        testStock.addProduct(outgoingTest);
        outgoing.addProduct(outgoingTest, 5);
        testManager.addTransaction(outgoing);

        ProductByStoreReport test = new ProductByStoreReport(1, testManager);
        Map<Product, Integer> pByStoreTest = test.returnProductList();
        AllTransactionForProductReport allTransForPTest = new AllTransactionForProductReport('o', outgoingTest, testManager);

        List<OutgoingTransaction> outList = testManager.getOutgoingTransactions();
        List<IncomingTransaction> inList = testManager.getIncomingTransactions();
        List<Transaction> allTransForP = allTransForPTest.returnTrans();

        //Assertions here test functionalities of multiple transaction classes
        // - IncomingTransactions, OutgoingTransactions, Transactions, TransactionsManager, StockManager
        //Tests thread classes (UpdateProductThread)
        //Test Product/Store classes
        //Tests multiple report classes - ProductByStoreReport, AllTransactionForProductReport
        assertEquals(15, outgoingTest.getCount());
        assertNotEquals(25, outgoingTest.getCount());
        assertNotEquals(5, outList.size());
        assertEquals(17, incomingTest.getCount());
        assertNotEquals(1, incomingTest.getCount());
        assertNotEquals(1, inList.size());
        assertEquals(1, pByStoreTest.size());
        assertEquals(1, allTransForP.size());

    }

    
}
