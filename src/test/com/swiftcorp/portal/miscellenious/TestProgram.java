package com.swiftcorp.portal.miscellenious;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

	
	  static int a1(int[] a)
	  {
	    int max1 = -1;
	    int max2 = -1;
			
	    for (int i=0; i<a.length; i++)
	    {
	      if (a[i] > max1)
	      {
	        max2 = max1;
	        max1 = a[i];
	      }
	      else if (a[i] != max1 && a[i] > max2)
	        max2 = a[i];
	    }
		System.out.println("max2::"+max2);	
	    return max2;
	  }
	  
	  static int a2(int[] a)
	  {
	    int sumEven = 0;
	    int sumOdd = 0;
			
	    for (int i=0; i<a.length; i++)
	    {
	      if (a[i]%2 == 0)
	        sumEven += a[i];
	      else
	        sumOdd += a[i];
	    }
	    System.out.println("sumOdd - sumEven::"+(sumOdd - sumEven));	
	    return sumOdd - sumEven;
	  }

	  
	  static char[] a3(char[] a, int start, int length)
	  {
	    if (length < 0 || start < 0 || start+length-1>=a.length)
	    {
	      return null;
	    }
			
	    char[] sub = new char[length];
	    for (int i=start, j=0; j<length; i++, j++)
	    {
	      sub[j] = a[i];
	    }
	    System.out.println("sub::"+sub);	
	    return sub;
	  }
	  /*
	   *   a1(new int[]{1, 2, 3, 4});
	    a1(new int[]{4, 1, 2, 3});
	    a1(new int[]{1, 1, 2, 2});
	    a1(new int[]{1, 1});
	    a1(new int[]{1});
	    a1(new int[]{});
	    
	    a2(new int[] {1});
	    a2(new int[] {1, 2});
	    a2(new int[] {1, 2, 3});
	    a2(new int[] {1, 2, 3, 4});
	    a2(new int[] {3, 3, 4, 4});
	    a2(new int[] {3, 2, 3, 4});
	    a2(new int[] {4, 1, 2, 3});
	    a2(new int[] {1, 1});
	    a2(new int[] {});

	    
	    //
	    a3(new char[]{'a', 'b', 'c'}, 0, 4);
	    a3(new char[]{'a', 'b', 'c'}, 0, 3);
	    a3(new char[]{'a', 'b', 'c'}, 0, 2);
	    a3(new char[]{'a', 'b', 'c'}, 0, 1);
	    a3(new char[]{'a', 'b', 'c'}, 1, 3);
	    a3(new char[]{'a', 'b', 'c'}, 1, 2);
	    a3(new char[]{'a', 'b', 'c'}, 1, 1);
	    a3(new char[]{'a', 'b', 'c'}, 2, 2);
	    a3(new char[]{'a', 'b', 'c'}, 2, 1);
	    a3(new char[]{'a', 'b', 'c'}, 3, 1);
	    a3(new char[]{'a', 'b', 'c'}, 1, 0);
	    a3(new char[]{}, 0, 1);
	    a3(new char[]{'a', 'b', 'c'}, -1, 2);
	    a3(new char[]{'a', 'b', 'c'}, -1, -2);
	   */
	public static void main(String args[])
	  {
	  
		isMumtest(8);
		isMumtest(-4);
		isMumtest(2);
		isMumtest(7);
		isMumtest(-7);
		isMumtest(9);
		isMumtest(11);
	    isDaphneArray(new int[]{3, 4, 6, 9, 11});
	    isDaphneArray(new int[]{3, 6, 5, 2, 11});
	    isDaphneArray(new int[]{3, 6, 4, 4, 11});
	    isDaphneArray(new int[]{3, 6, 11});
	    isDaphneArray(new int[]{3, 11});
	    
	    isDoublePoor(new int[]{5, 6, 7, 3, 9});
	    isDoublePoor(new int[]{5, 7, 9, 12});
	    isDoublePoor(new int[]{5, 10, 3, 8, 6});
	  }
	
	  
	  static int isMumtest (int n) 
	  {
		  int mumtest = 0;
		  int sum = n-2;
		  if(sum%3==0)
		  {
			  mumtest = 1;
		  }
		  System.out.println("mumtest::"+mumtest);
		  return mumtest;
	  }
	  static int isDaphneArray(int[ ] a) 
	  {
		  int isDArray = 0;
		  int firstEvenNumberPos = -1;
		  int secondEvenNUmberPos = -1;
		  int evenNumberCount = 0;
		  for(int i=0;i<a.length;i++)
		  {
			  if(a[i]%2==0)
			  {
				  evenNumberCount++;
				  if(firstEvenNumberPos == -1)
				  {
					  firstEvenNumberPos = i;
				  }
				  else if(firstEvenNumberPos>-1 && secondEvenNUmberPos == -1)
				  {
					  secondEvenNUmberPos = i;
				  }
			  }
		  }
		 
		  if(secondEvenNUmberPos-firstEvenNumberPos==1 && evenNumberCount==2)
		  {
			  isDArray = 1;
		  }
		  System.out.println("isDArray::"+isDArray);
		  return isDArray;
	  }
	  /*
	   *    1. There is a number n such that both n and 2*n appear in the array
   			2. There is exactly one n in the array with this property

			For example {5, 6, 7, 3, 9} is a double-poor array because the double of 3 is in the array and this is the only value 
			for which this is true (i.e., 2*5, 2*6, 2*7 and 2*9 are not in the array). 
	   */
	  static  int isDoublePoor(int[ ] a) 
	  {
		  int isDPArray = 0;
		  int doublePoorCount = 0;
		  for(int i=0;i<a.length;i++)
		  {
			  for(int j=0;j<a.length;j++)
			  {
				  if(a[i]*2==a[j])
				  {
					  doublePoorCount++;
				  }
			  }
		  }
		  if(doublePoorCount==1)
		  {
			  isDPArray = 1;
		  }
		  System.out.println("isDPArray::"+isDPArray);
		  return isDPArray;
	  }

}
