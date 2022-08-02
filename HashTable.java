
public class HashTable {

	public static void main (String[] args) throws Exception
	{
		Dollar [] arr = new Dollar[29];
		HashTable table = new HashTable();
		//table.hashArray(arr, new Dollar(2));
	}
	
	public void hashArray(Dollar [] arr, Dollar key)
	{
		int keyIndex = hashFunction(key);
		int i =0;
		
		while(arr[keyIndex]!= null)
		{
			keyIndex = (keyIndex + i +i*i)%arr.length;
		}
		
		arr[keyIndex] = key;
	}
	
	
	public int hashFunction(Dollar key)
	{
		int w = key.getCurrNoteVal();
		int f = key.getCurrCoinVal();
		int m = 2;
		int n = 3;
		int size = 29;
		
		int index = (m*w +  n*f) % size;
		
		return index;
	}
	
	
}
