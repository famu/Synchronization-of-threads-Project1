import java.text.SimpleDateFormat;
import java.util.*;


public class Clock extends Thread{
			public static boolean sessionStart = false;
			public static boolean dayEnds = false;
			public static int session_count = 0;
			public static int Number_sessions =2;
			private final static long startTime = System.currentTimeMillis();
			
	public Clock() {//constructor
		setName("Clock");
	}

		public void msg(String m) {
			System.out.println("[" + ((System.currentTimeMillis() - Project1.time)) + "]" 
					+ getName() + ":" + m);
		}
		
		
		public synchronized void run() {
			msg(" sets session 1 and notifies all visitors to enter the room.");
			sessionStart = true;
			Project1.speaker.interrupt();
			try {this.sleep(10000);} catch (InterruptedException e) {}
			session_count+=1;
			msg(" ends session 1 announces break and notifies attendent visitors to visit the Museum.");
			sessionStart = false;
			for(int i=0;i<15;i++){
				if(Project1.Visitor[i].gotSeat==true){
					Project1.Visitor[i].interrupt();
				}
			}
			
			//break
			try {Thread.sleep(5000);} catch (InterruptedException e) {} 
			//break ends
			
			msg(" sets session 2 and notifies all visitors to enter the room.");
			Visitors.theater_capacity = 6;
			sessionStart = true;
			Project1.speaker.interrupt();//waking up speaker to begin his presentation
			try {this.sleep(10000);} catch (InterruptedException e) {}
			session_count+=1;
			msg(" ends session 2 and notifies all (attendant/wating) visitors to visit the Museum.");
			sessionStart = false;
			for(int i=0;i<15;i++){//interrupting all visitors to go to Museum.
				if(Project1.Visitor[i].gotSeat==true){//only those visitors are interrupted who watched movie
					Project1.Visitor[i].interrupt();
				}
			}
			try {this.sleep(3000);} catch (InterruptedException e) {}//letting all visitors entering and visit museum.
			dayEnds = true;
			msg(" signals the end of the day and notifies visitors to leave the Museum.");
			Project1.speaker.interrupt();//waking speaker up to leave
			try {
				Thread.sleep(20000);//gives time to visitors to leave the Museum
				msg(" Museum closed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		   
		}

	
}
