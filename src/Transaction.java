
public class Transaction {
	private String TransactionID;
	private String PastryID;
	private int Quantity;
	
	
	
	public Transaction(String transactionID, String pastryID, int quantity) {
		super();
		TransactionID = transactionID;
		PastryID = pastryID;
		Quantity = quantity;
	}
	
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public String getPastryID() {
		return PastryID;
	}
	public void setPastryID(String pastryID) {
		PastryID = pastryID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	
}
