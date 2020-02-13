import java.text.NumberFormat;
import java.util.Scanner;

public class RentalLocations2 {

	static int zip;
	static double days;
	static double discount;
	static double dailyRate;
	static double total;
	
	static String type;
	static String trailer;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
//		System.out.println("how many days would you like to rent a vehicle?");
//		days = Integer.parseInt(sc.nextLine());
		System.out.println("Please enter vehicle type: car, truck, van");
		type = sc.nextLine();
		System.out.println("Please input zip of rental location: ");
		zip = sc.nextInt();
		
		sc.close();	
	
//}
//	public double getRates(double dailyRates) {
	System.out.println(type);
		if ((days > 0) && (days < 5)) {
			if (type.equalsIgnoreCase("car")) {	
				if ((zip >= 98001) && (zip <= 98100)) {
					dailyRate = 27.50;
				}
				if ((zip >= 98101) && (zip <= 98200)) {
					dailyRate = 22.49;
				}
				if ((zip >= 98201) && (zip <= 98300)) {
					dailyRate = 31.00;
				}
				if ((zip >= 98301) && (zip <= 98400)) {
					dailyRate = 12.25;
				}
				if ((zip >= 98401) && (zip <= 98500)) {
					dailyRate = 75.24;
				}
				if ((zip >= 98501) && (zip <= 98600)) {
					dailyRate = 34.99;
				}
				if ((zip >= 98601) && (zip <= 98700)) {
					dailyRate = 28.99;
				}
				if ((zip >= 98701) && (zip <= 98800)) {
					dailyRate = 33.00;
				}
				if ((zip >= 98801) && (zip <= 98900)) {
					dailyRate = 19.99;
				}
				if ((zip >= 98901) && (zip <= 99000)) {
					dailyRate = 9.99;
				}
			}
			if (type.equalsIgnoreCase("truck")) {	
				if ((zip >= 98001) && (zip <= 98100)) {
					dailyRate = 55.00;
				}
				if ((zip >= 98101) && (zip <= 98200)) {
					dailyRate = 44.98;
				}
				if ((zip >= 98201) && (zip <= 98300)) {
					dailyRate = 61.49;
				}
				if ((zip >= 98301) && (zip <= 98400)) {
					dailyRate = 24.49;
				}
				if ((zip >= 98401) && (zip <= 98500)) {
					dailyRate = 98.74;
				}
				if ((zip >= 98501) && (zip <= 98600)) {
					dailyRate = 69.99;
				}
				if ((zip >= 98601) && (zip <= 98700)) {
					dailyRate = 56.49;
				}
				if ((zip >= 98701) && (zip <= 98800)) {
					dailyRate = 62.79;
				}
				if ((zip >= 98801) && (zip <= 98900)) {
					dailyRate = 27.99;
				}
				if ((zip >= 98901) && (zip <= 99000)) {
					dailyRate = 28.99;
				}
			}
			if (type.equalsIgnoreCase("van")) {	
				if ((zip >= 98001) && (zip <= 98100)) {
					dailyRate = 37.50;
				}
				if ((zip >= 98101) && (zip <= 98200)) {
					dailyRate = 32.49;
				}
				if ((zip >= 98201) && (zip <= 98300)) {
					dailyRate = 41.00;
				}
				if ((zip >= 98301) && (zip <= 98400)) {
					dailyRate = 22.25;
				}
				if ((zip >= 98401) && (zip <= 98500)) {
					dailyRate = 85.24;
				}
				if ((zip >= 98501) && (zip <= 98600)) {
					dailyRate = 44.99;
				}
				if ((zip >= 98601) && (zip <= 98700)) {
					dailyRate = 38.99;
				}
				if ((zip >= 98701) && (zip <= 98800)) {
					dailyRate = 43.00;
				}
				if ((zip >= 98801) && (zip <= 98900)) {
					dailyRate = 29.99;
				}
				if ((zip >= 98901) && (zip <= 99000)) {
					dailyRate = 19.99;
				}
			}
			total = dailyRate * days;
			System.out.println(NumberFormat.getCurrencyInstance().format(total));
//			return NumberFormat.getCurrencyInstance().format(dailyRate);
	}
		if (trailer.equalsIgnoreCase("twoWheels")) {
			
		}
			
//		return dailyRate;
//		}
//		if (days >= 5 ) { // weekly rate
//			if ((zip >= 98000) && (zip <= 99000)) {
//				discount = 0.2;
//				dailyRate = 27.50;				
//				dailyRate = (dailyRate * discount) + dailyRate;
//				
//			}
//			if (zip == 2) {
//				dailyRate = 22.49;
//				discount = 0.2;
//				dailyRate = (dailyRate * discount) + dailyRate;
//			
//			}
//			if (zip == 3) {
//				dailyRate = 31.00;
//				discount = 0.2;
//				dailyRate = (dailyRate * discount) + dailyRate;
//				
//			}
//			if (zip == 4) {
//				dailyRate = 12.25;discount = 0.2;
//				dailyRate = (dailyRate * discount) + dailyRate;
//				
//			}
//		}
		
//		System.out.println("the total is: " + NumberFormat.getCurrencyInstance().format(total));
//		return dailyRate;
			
		}
}
//	}

	

