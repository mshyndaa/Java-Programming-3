
public class Rollcake extends Cake{

	private String FillingName;
	
	public Rollcake(String pastryID, String pastryName, String pastryType, int pastryPrice, String FillingName) {
		super(pastryID, pastryName, pastryType, pastryPrice);
		// TODO Auto-generated constructor stub
		
		this.FillingName = FillingName;
	}

	public String getFillingName() {
		return FillingName;
	}

	public void setFillingName(String fillingName) {
		FillingName = fillingName;
	}

	@Override
	public int setTotalPrice(int qty) {
		// TODO Auto-generated method stub
		return (this.getPastryPrice()*qty) - 25000;
	}

	
	
}
