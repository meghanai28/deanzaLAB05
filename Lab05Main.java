import java.util.Scanner;
/**
 * Lab 05
 * @author Lab Group 7: Meghana Indukuri & Joseph Khamisy
 * This program creates the main, where the hashtable is tested with various operations. This main allows
 * for user interactivity and prints the value to console. 
 * Aug 4th 2022
 */
public class Lab05Main {
	public static void main(String [] args) throws Exception
	{
		
		System.out.println("Welcome to lab 05! Today we will be exploring HashTables!\n\n");
		HashTable table = new HashTable(29);
		Dollar[] firstTwenty = {new Dollar(57.12),new Dollar(23.44), new Dollar(87.43), new Dollar(68.99),
				new Dollar(111.22),new Dollar(44.55),new Dollar(77.77),new Dollar(18.36),new Dollar(543.21),
				new Dollar(20.21),new Dollar(345.67),new Dollar(36.18),new Dollar(48.48),new Dollar(101.00),
				new Dollar(11.00),new Dollar(21.00),new Dollar(51.00),new Dollar(1.00),new Dollar(251.00),
				new Dollar(151.00)};
		for(int i =0; i<firstTwenty.length; i++)
		{
			table.hashArray(firstTwenty[i]);
		}
		
		System.out.println("Here is the number of dollars we have loaded into the HashTable currently:");
		System.out.println(table.getCount() + "\n");
		
		System.out.println("Here is the load factor the current HashTable:");
		System.out.println(table.getLoadFactor()+ "\n");
		
		System.out.println("Here is the number of collisions that have occured when we loaded dollars into the HashTable:");
		System.out.println(table.getCollisions()+ "\n");
		

		
		
		Scanner readInput = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("\n\nHere is a menu of operations you the user can do on our Hash Tavle: \n"
					+ "i: enter i to insert an dollar object into the HashTable\n"
					+ "s: enter s to search for a dollar value in the HashTable and get the index of the dollar if it exists\n"
					+ "q: enter q to exit the program.\n");
			System.out.println("Please enter a menu option:\n");
			String val = readInput.nextLine();
			if(!(val.equalsIgnoreCase("i")|| val.equalsIgnoreCase("s") || val.equalsIgnoreCase("q")))
			{
				System.out.println("INVALID MENU OPTION! Please try again!\n");
				continue;
			}
			
			 char value = val.charAt(0);
			 if(value == 'i') 
			 {
			 	while(true)
			 	{
			 		System.out.println("Please enter a dollar(double) value to insert:\n");
		 			String input = readInput.nextLine();
		 			double dollarInput;
		 			try
		 			{
		 				dollarInput = Double.parseDouble(input);
		 			}
		 			catch(Exception e)
		 			{
		 				String error = "\n\n"+ input + " Is not a Double! Please try again!\n\n";
		 				System.out.println(error);
		 				continue;
		 			}
		 			Dollar insert;
		 			try
		 			{
		 				insert = new Dollar(dollarInput);
		 			}
		 			catch(Exception e)
		 			{
		 				String error = "\n\n"+ dollarInput + ": " + e.getMessage();
		 				System.out.println(error);
	            		continue;
		 			}
		 			
		 			System.out.println("Inserting "+ insert + " into our HashTable.");
		 			table.hashArray(insert);
		 			System.out.println(insert + " has been sucessfuly inserted!");
		 			break;
			 			
			 	}
			 	continue;
			 }

			 if(value == 's') 
			 {
			 	while(true)
			 	{
			 		System.out.println("Please enter a dollar(double) value to search: \n");
		 			String input = readInput.nextLine();
		 			double dollarInput;
		 			try
		 			{
		 				dollarInput = Double.parseDouble(input);
		 			}
		 			catch(Exception e)
		 			{
		 				String error = "\n\n"+ input + " Is not a Double! Please try again!\n";
		 				System.out.println(error);
		 				continue;
		 			}
		 			Dollar search;
		 			try
		 			{
		 				search = new Dollar(dollarInput);
		 			}
		 			catch(Exception e)
		 			{
		 				String error = "\n\n"+ dollarInput + ": " + e.getMessage();
		 				System.out.println(error);
	            		continue;
		 			}
		 			
		 			int index = table.hashSearch(search);
		 			if(index == -1)
		 			{
		 				System.out.println(search + " is Invalid Data. Has not been found in our hashtable \n");
		 			}
		 			else
		 			{
		 				System.out.println(search + " exists in this table! It is at index " + index + "\n");
		 			}
		 			break;
			 			
			 	}
			 	continue;
			 }
			 if(value == 'q') 
			 {
				 System.out.println("Thanks for using our hash table!\n");
				 System.out.println("Here is the number of dollars we have loaded into the HashTable currently:");
					System.out.println(table.getCount() + "\n");
					
					System.out.println("Here is the load factor the current HashTable:");
					System.out.println(table.getLoadFactor()+ "\n");
					
					System.out.println("Here is the number of collisions that have occured when we loaded dollars into the HashTable:");
					System.out.println(table.getCollisions()+ "\n");
					
				 
			 	 System.out.println("\n bye!");
			 	 break;
			 }
			
			
		}
		
		table = null; //cleanup
		readInput.close(); // cleanup
	 }
		
		
	
}
