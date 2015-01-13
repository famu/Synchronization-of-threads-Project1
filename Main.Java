/**
 * CS340 Project1.java
 * @author  Muhammad Faisal
 */

import javax.swing.*;

import java.awt.Component;
import java.util.*;


public class Main  {
	
	public static long time = System.currentTimeMillis();
	public static int Num_visitors=15;
	static Visitors[] Visitor= new Visitors[15];
	static Clock clock;
	static Speaker speaker;
	public static void main(String[] args){
//	all initializations are in main function
		for(int i=0;i<Num_visitors;i++){ 
			Visitor[i] = new Visitors(i+1);
			Visitor[i].start();
		}

		speaker = new Speaker();
		speaker.start();
		
		clock = new Clock();
		clock.start();

    }


}
