
public class Cupcake extends Cake {
	
	private String ToppingName;
	
	public Cupcake(String pastryID, String pastryName, String pastryType, int pastryPrice, String ToppingName) {
		super(pastryID, pastryName, pastryType, pastryPrice);
		// TODO Auto-generated constructor stub
		
		this.ToppingName = ToppingName;
	}

	public String getToppingName() {
		return ToppingName;
	}

	public void setToppingName(String toppingName) {
		ToppingName = toppingName;
	}

	@Override
	public int setTotalPrice(int qty) {
		// TODO Auto-generated method stub
		return (this.getPastryPrice()*qty) - 15000;
	}
	
	
}
