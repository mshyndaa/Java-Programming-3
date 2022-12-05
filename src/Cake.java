public abstract class Cake {
	private String PastryID;
	private String PastryName;
	private String PastryType;
	private int PastryPrice;
	
	public Cake(String pastryID, String pastryName, String pastryType, int pastryPrice) {
		super();
		PastryID = pastryID;
		PastryName = pastryName;
		PastryType = pastryType;
		PastryPrice = pastryPrice;
	}
	
	public abstract int setTotalPrice(int qty);

	public String getPastryID() {
		return PastryID;
	}

	public void setPastryID(String pastryID) {
		PastryID = pastryID;
	}

	public String getPastryName() {
		return PastryName;
	}

	public void setPastryName(String pastryName) {
		PastryName = pastryName;
	}

	public String getPastryType() {
		return PastryType;
	}

	public void setPastryType(String pastryType) {
		PastryType = pastryType;
	}

	public int getPastryPrice() {
		return PastryPrice;
	}

	public void setPastryPrice(int pastryPrice) {
		PastryPrice = pastryPrice;
	}
	
	
}
