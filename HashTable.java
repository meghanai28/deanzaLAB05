/**
 * Lab 05
 * @author Lab Group 7: Meghana Indukuri & Joseph Khamisy
 * This program implements a hash table which hashes dollar values into an array and can search for them.
 * Aug 4th 2022
 */
public class HashTable {

	private final double MAX_LOAD_RATE = 0.80; // maximum load rate allowed
	private int count; // number of dollars hashed into array
	private Dollar[] arr; // array where dollars are hashed
	private int collisions; // number of collisions that occured when hashing dollars into array
	

	/**
	 * Default constructor intializes private attributes array, count, and collisions.
	 * @pre 
	 * @post arr attribute is given a size of 11, count is set to 0, and collisons is set 0.
	 */
	public HashTable()
	{
		arr = new Dollar[11];
		count = 0;
		collisions = 0;
	}
	
	/**
	 * This constructor intializes private attributes array, count, and collisions.
	 * @pre size - an int that represents the size of the array
	 * @post array is given a size that is entered as a parameter, count is set to 0, and collisions is set to 0.
	 * 		if size is set to 0, illegal argument exception is thrown. 
	 */
	public HashTable(int size)
	{
		if(size == 0)
		{
			throw new IllegalArgumentException();
		}
		arr = new Dollar[size];
		count = 0;
		collisions = 0;
	}
	
	/**
	 * This is a getter method for the private attribute count
	 * @pre
	 * @post
	 * @return count - the count of elements hashed into array
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * This is a getter method for the private attribute collisions
	 * @pre
	 * @post
	 * @return count - the number of collisions that occured during hashing.
	 */
	public int getCollisions()
	{
		return collisions;
	}
	
	/**
	 * This method searches for a given value in the array, using
	 * the hash function and quadratic probing(i+i*i). The method
	 * returns the index at which the given dollar value has been found
	 * if it has not been found it returns -1.
	 * 
	 * @pre value - the dollar value to be searched for.
	 * 
	 * @post the quadratic probing we use for collisions is (i+i*i). 
	 * 
	 * @return int - the index of where the dollar value was found. if not found
	 * 			-1 is returned as  the index.
	 * 		the quadratic probing we use for collisons is 
	 * 		(i+i*i).
	 * 		Whenever we do quadratic probing, we check if the new index is equal to the starting
	 * 		position which tells us we have entered a cyclic hash.
	 * 		Then we default to linear probing whenever we enter a cyclic hash. 
	 * 		if value == null exception is thrown.
	 */
	public int hashSearch (Dollar value) throws Exception
	{
		if(value == null)
		{
			throw new IllegalArgumentException();
		}
		int valIndex = hashFunction(value,arr.length);
		int copy = valIndex;
		int i =1;
		
		boolean cyclic = false; // flag for if quadratic probing has entered a loop.
		while(arr[valIndex]!= null)
		{
			if(arr[valIndex].isEqual(value))
			{
				return valIndex;
			}
			if(!cyclic)
			{
				valIndex = (copy + i +i*i)%arr.length;
			}
			else
			{
				valIndex = (copy + i)%arr.length;
			}
			if(valIndex == copy)
			{
				cyclic = true;
			}
			i++;
		}
		
		return -1;
		
	}
	
	/**
	 * This method hashes a given value into the array using the hash
	 * function and quadratic probing(i+i*i) for collisions. This method
	 * also calls the resize method if the loadfactor has passed the treshold.
	 * 
	 * @pre value - the dollar value to be inserted into the array.
	 * 
	 * @post the quadratic probing we use for collisons is 
	 * 		(i+i*i).
	 * 		Whenever we do quadratic probing, we check if the new index is equal to the starting
	 * 		position which tells us we have entered a cyclic hash.
	 * 		Then we default to linear probing whenever we enter a cyclic hash. 
	 * 		if value == null exception is thrown.
	 */
	public void hashArray(Dollar value)
	{
		if(value == null)
		{
			throw new IllegalArgumentException();
		}
		if(getLoadFactor()>MAX_LOAD_RATE)
		{
			resize();
		}
		
		int valIndex = hashFunction(value,arr.length);
		int copy = valIndex;
		int i =1;
	
		boolean cyclic = false; // flag for if quadratic probing has entered a loop.
		while(arr[valIndex]!= null)
		{
			if(!cyclic)
			{
				valIndex = (copy + i +i*i)%arr.length;
			}
			else
			{
				valIndex = (copy + i)%arr.length;
			}
			if(valIndex == copy)
			{
				cyclic = true;
			}
			collisions++;
			i++;
		}
		
		arr[valIndex] = value;
		count++;
	}
	
	/**
	 * This method returns the loadfactor for the hash array, using the count
	 * of elements, and the array's length.
	 * 
	 * @pre
	 * 
	 * @post calculates the load factor by count/arr.length
	 * 
	 * @return a double which contains the loadfactor
	 */
	public double getLoadFactor()
	{
		double loadFactor = (double)count/(double)arr.length;
		return loadFactor;
	}
	
	
	/**
	 * This method finds the index for a given dollar value using the pseudorandom hash scheme 
	 * - (m*w +  n*f) % size - where size = array size, m = 2, n = 3, w = whole value, f = fractional value.
	 * This method takes the dollar value to be inserted into the array and returns the index at where
	 * it should be hashed according to the hashfunction.
	 * 
	 * @pre key - the dollar value to which the hash function will use to calculate an index.
	 * 		size - int - the size of the array we want to hash into.
	 * 
	 * @post the hashfunction is calculated based on the 
	 * pseudorandom hash scheme :
	 * - (m*w +  n*f) % size - where size = array size, m = 2, n = 3, w = whole value, f = fractional value.
	 * 
	 * @return an int which represnts the index where the dollar key should be inserted. 
	 */
	public int hashFunction(Dollar key,int size)
	{
		int w = key.getCurrNoteVal();
		int f = key.getCurrCoinVal();
		int m = 2;
		int n = 3;
		
		int index = (m*w +  n*f) % size;
		
		return index;
	}
	
	/**
	 * This method is called when the loadfactor of an array is greater than 80. What this method
	 * does is it creates a new array which is the the next prime number after the array's oldsize*2.
	 * (using a helper method called nextprime). After this new array is created, all the old values 
	 * in the old array are rehashed into the new sized array.
	 * @pre 
	 * @post when we resize the array, we set our private attribute array to the new size, and then rehash
	 * 		all our old values into the array. We set count and collisions to 0, because we have to start over
	 * 		when we resize. As we rehash the count and collisions will start increasing.
	 */
	public void resize()
	{
		count =0;
		collisions = 0;
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
	
	/**
	 * This private helped method finds the next prime number after a given value. 
	 * @pre val - the value at which we start the search for the next prime (ignoring the value itself).
	 * @post we use a helper method called is prime to check if the values after val is prime. If a value is
	 * found that is prime then we return that value. 
	 * @return int - we return the next prime number after a given value.
	 */
	private int nextPrime(int val)
	{
		val++;
		while(!isPrime(val))
		{
			val++;
		}
		return val;
		
	}
	
	/**
	 * Algorithm IsPrime (n)
	 * 		
	 * 		This algorithm checks if a value is prime or not. 
	 * 		Pre : n - the value to be checked
	 * 		Post:
	 * 		Return: true or false
	 * 
	 * 			if(n <= 3)
	 * 				return !(array[i] equals 1)
	 * 			end if
	 * 			if (n % 2 equals 0)
	 * 				return false
	 * 			end if
	 * 			int j=3
	 * 			loop(j <= sqrt(n))
	 * 				if(n% j equals 0)
	 * 					return false;
	 * 				end if
	 * 				j+=2
	 * 			end loop
	 * 			return true
	 * 
	 * end IsArrayPrimeIter
	 */
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
	
}
