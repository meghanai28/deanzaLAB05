
public class HashTable {

	private final double MAX_LOAD_RATE = 0.80;
	private int count;
	private Dollar[] arr;
	// implement collision counter

	public HashTable()
	{
		arr = new Dollar[10];
		count = 0;
	}
	
	public HashTable(int size)
	{
		arr = new Dollar[size];
		count = 0;
	}
	
	public int hashSearch (Dollar key) throws Exception
	{
		int keyIndex = hashFunction(key,arr.length);
		int i =0;
		
		while(arr[keyIndex]!= null)
		{
			if(arr[keyIndex].isEqual(key))
			{
				return keyIndex;
			}
			keyIndex = (keyIndex + i +i*i)%arr.length;
			i++;
		}
		
		return -1;
		
	}
	
	public void hashArray(Dollar key)
	{
		int keyIndex = hashFunction(key,arr.length);
		int val = keyIndex;
		int i =0;
		if(getLoadFactor()>MAX_LOAD_RATE)
		{
			resize();
		}
		while(arr[keyIndex]!= null)
		{
			keyIndex = val;
			keyIndex = (keyIndex + i +i*i)%arr.length;
			i++;
		}
		
		arr[keyIndex] = key;
		count++;
	}
	
	public double getLoadFactor()
	{
		double loadFactor = (double)count/(double)arr.length;
		return loadFactor;
	}
	
	public int hashFunction(Dollar key, int size)
	{
		int w = key.getCurrNoteVal();
		int f = key.getCurrCoinVal();
		int m = 2;
		int n = 3;
		
		int index = (m*w +  n*f) % size;
		
		return index;
	}
	
	public void resize()
	{
		count =0;
		Dollar [] temp = arr;
		arr = new Dollar[nextPrime(arr.length *2)];
		
		for(Dollar val : temp)
		{
			if(val!= null)
			{
				hashArray(val);
			}
		}
			
	}
	
	
	private int nextPrime(int val)
	{
		val+=1;
		while(!isPrime(val))
		{
			val++;
		}
		return val;
		
	}
	
	private boolean isPrime(int val)
	{
		if(val<=3)
        {
            return !(val==1);
        }
		if (val % 2 == 0)
		{
			return false;
		}
        for (int j = 3; j <= Math.sqrt(val)+1; j+=2)
        {
            if (val%j == 0)
            {
                return false;
            }
        }
        return true;
	}
	
	//testing method
	public void print()
	{
		for(int i =0; i<arr.length;i++)
		{
			System.out.println(i+" :"+ arr[i]);
		}
	}
	
	
}
