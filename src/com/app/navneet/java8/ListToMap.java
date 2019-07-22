package com.app.navneet.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class ListToMap {
	 public static void freq(ArrayList<Character> clist, char c)
	    {
	     
	        if(clist.contains(c))
	        System.out.println(/*Your code here*/);
	        else
	        System.out.println("Not Present");
	        
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int data=	IntStream.range(1, 10).map(i -> i * i).findFirst().getAsInt(); 
	System.out.println(data);
	List<String> list = Arrays.asList("B", "A", "A", "C", "B", "A");
	System.out.println(""+ ": " + Collections.frequency(list, "A"));


	}

}
