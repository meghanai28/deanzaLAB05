
public class Lab05Main {
	public static void main(String [] args) throws Exception
	{
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
		
		table.print();
	}
}
