import java.sql.SQLException;
import java.util.*;

public class Main {

	Scanner in = new Scanner(System.in);
	int menu = 0, buy = 0, qty = 0, transactionCount = 0;
	DBAccess db = new DBAccess();
	Vector<Cake> listCake = new Vector<Cake>();
	Vector<Transaction> listTransaction = new Vector<Transaction>();
	Vector<Cupcake> listCupcake = new Vector<Cupcake>();
	Vector<Rollcake> listRollcake = new Vector<Rollcake>();
	
	public Main() throws SQLException {
		// TODO Auto-generated constructor stub
		
		// RUN SCRIPT FOR THE FIRST TIME
		
//		try {
//			db.runScript("D:\\bakeroo.sql");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		listCupcake.addAll(db.selectCupcake());
		listRollcake.addAll(db.selectRollcake());
		listTransaction.addAll(db.selectTransaction());
		listCake.addAll(listCupcake);
		listCake.addAll(listRollcake);
		
		do {
			System.out.println("Bakeroo");
			System.out.println("=======");
			System.out.println("1. Insert Transaction");
			System.out.println("2. View Transaction");
			System.out.println("3. Delete Transaction");
			System.out.println("4. Exit");
			System.out.print(">> ");
			
			try {
				menu = in.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
			}
			in.nextLine();
			
			if (menu == 1) {
				insert();
			} else if (menu == 2) {
				view();
			} else if (menu == 3) {
				delete();
			}
			
		} while (menu != 4);
		
	}
	
	public void insert() throws SQLException {
		
		
		System.out.println("==============================================");
		System.out.println("|No |ID\t |Name\t\t |Type\t |Price\t |Cream Name\t\t |");
		int count = 0;
		for (int i = 0; i < listCupcake.size(); i++) {
			count++;
			System.out.println("|" + count + " | " + listCupcake.get(i).getPastryID() + "\t |" + listCupcake.get(i).getPastryName() + "\t\t |" + listCupcake.get(i).getPastryType() + "\t |" + listCupcake.get(i).getPastryPrice() + "\t |" + listCupcake.get(i).getToppingName() + "\t\t |");
		}
		for (int i = 0; i < listRollcake.size(); i++) {
			count++;
			System.out.println("|" + count + " | " + listRollcake.get(i).getPastryID() + "\t |" + listRollcake.get(i).getPastryName() + "\t\t |" + listRollcake.get(i).getPastryType() + "\t |" + listRollcake.get(i).getPastryPrice() + "\t |" + listRollcake.get(i).getFillingName() + "\t\t |");
		}
		System.out.println("==============================================");
		System.out.println();
		
		
		// Select Buy
		do {
			System.out.print("Input the number of index you want to buy[1 .. "+count+"] : ");
			try {
				buy = in.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
			}
			in.nextLine();
			
		} while (buy < 1 || buy > count);
		
		// Select qty
		do {
			System.out.print("Input quantity[>0] : ");
			try {
				qty = in.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
			}
			in.nextLine();
			
		} while (qty < 1);
		transactionCount++;
		String tid = "PI0"+transactionCount;
		String cid = "";
		
		if(buy > listCupcake.size()) {
			buy-=1;
			buy = buy - listCupcake.size();
			System.out.println(listCupcake.size());
			System.out.println(buy);
			cid = listRollcake.get(buy).getPastryID();
			db.insertTransaction(tid, cid, qty);
			
			System.out.println("Detail Transaction");
			System.out.println("==================");
			System.out.println("Transaction ID: " + tid);
			System.out.println("Cake Name: " + listRollcake.get(buy).getPastryName());
			System.out.println("Cake Price: " + listRollcake.get(buy).getPastryPrice());
			System.out.println("Cake Quantity: " + qty);
			System.out.println("Discount: " + 25000);
			System.out.println("Total Price: " + listRollcake.get(buy).setTotalPrice(qty));
			System.out.println("==================");
		} else {
			buy-=1;
			cid = listCupcake.get(buy).getPastryID();
			db.insertTransaction(tid, cid, qty);
			
			System.out.println("Detail Transaction");
			System.out.println("==================");
			System.out.println("Transaction ID: " + tid);
			System.out.println("Cake Name: " + listCupcake.get(buy).getPastryName());
			System.out.println("Cake Price: " + listCupcake.get(buy).getPastryPrice());
			System.out.println("Cake Quantity: " + qty);
			System.out.println("Discount: " + 15000);
			System.out.println("Total Price: " + listCupcake.get(buy).setTotalPrice(qty));
			System.out.println("==================");
		}
		
		listTransaction.removeAllElements();
		listTransaction.addAll(db.selectTransaction());
		System.out.println("Succesfully inserted new transaction!");
		System.out.println("Press any key to continue...");
		in.nextLine();
		
	}
	
	public void view() throws SQLException {
		
		for (int i = 0; i < listTransaction.size(); i++) {
			System.out.println("Transaction ID: " + listTransaction.get(i).getTransactionID());
			if(listTransaction.get(i).getPastryID().contains("CC")) {
				for (int j = 0; j < listCupcake.size(); j++) {	
					if (listCupcake.get(j).getPastryID() == listTransaction.get(i).getPastryID()) {
						int discount = 15000;
						System.out.println("Pastry Name: " + listCupcake.get(j).getPastryName());
						System.out.println("Pastry Type: " + listCupcake.get(j).getPastryType());
						System.out.println("Pastry Price: " + listCupcake.get(j).getPastryPrice());
						System.out.println("Quantity: " + listTransaction.get(i).getQuantity());
						System.out.println("Discount: " + discount);
						System.out.println("Total Price: " + ((listCupcake.get(j).getPastryPrice()*listTransaction.get(i).getQuantity())-discount));
					}
				}
			} else {
				for (int j = 0; j < listRollcake.size(); j++) {	
					if (listRollcake.get(j).getPastryID() == listTransaction.get(i).getPastryID()) {
						int discount = 15000;
						System.out.println("Pastry Name: " + listRollcake.get(j).getPastryName());
						System.out.println("Pastry Type: " + listRollcake.get(j).getPastryType());
						System.out.println("Pastry Price: " + listRollcake.get(j).getPastryPrice());
						System.out.println("Quantity: " + listTransaction.get(i).getQuantity());
						System.out.println("Discount: " + discount);
						System.out.println("Total Price: " + ((listRollcake.get(j).getPastryPrice()*listTransaction.get(i).getQuantity())-discount));
					}
				}
			}
		}
		System.out.println();
		System.out.print("Press any key to continue...");
		in.nextLine();
	}
	
	public void delete() {
		if (listTransaction.isEmpty()) {
			System.out.println("There is no transaction ...");
		} else {
			for (int i = 0; i < listTransaction.size(); i++) {
				System.out.println("Transaction ID: " + listTransaction.get(i).getTransactionID());
				if(listTransaction.get(i).getPastryID().contains("CC")) {
					for (int j = 0; j < listCupcake.size(); j++) {	
						if (listCupcake.get(j).getPastryID() == listTransaction.get(i).getPastryID()) {
							int discount = 15000;
							System.out.println("Pastry Name: " + listCupcake.get(j).getPastryName());
							System.out.println("Pastry Type: " + listCupcake.get(j).getPastryType());
							System.out.println("Pastry Price: " + listCupcake.get(j).getPastryPrice());
							System.out.println("Quantity: " + listTransaction.get(i).getQuantity());
							System.out.println("Discount: " + discount);
							System.out.println("Total Price: " + ((listCupcake.get(j).getPastryPrice()*listTransaction.get(i).getQuantity())-discount));
						}
					}
				} else {
					for (int j = 0; j < listRollcake.size(); j++) {	
						if (listRollcake.get(j).getPastryID() == listTransaction.get(i).getPastryID()) {
							int discount = 15000;
							System.out.println("Pastry Name: " + listRollcake.get(j).getPastryName());
							System.out.println("Pastry Type: " + listRollcake.get(j).getPastryType());
							System.out.println("Pastry Price: " + listRollcake.get(j).getPastryPrice());
							System.out.println("Quantity: " + listTransaction.get(i).getQuantity());
							System.out.println("Discount: " + discount);
							System.out.println("Total Price: " + ((listRollcake.get(j).getPastryPrice()*listTransaction.get(i).getQuantity())-discount));
						}
					}
				}
			}
			String tid;
			
			while (true){
				System.out.print("Input Transaction ID[]must exist](Case Insensitive): ");
				
				try {
					tid = in.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				for (int i = 0; i < listTransaction.size; i++) {
					if(tid.toLowerCase() == listTransaction.get(i).getTransactionID().toLowerCase()) {
						
					}
				}
				
			} 
		}
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new Main();
	}

}
