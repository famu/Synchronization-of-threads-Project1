import java.util.Random;

public class Visitors extends Thread {
	public static int[] arrivalTimes = new int[15];
	public static int theater_capacity=6;
	public boolean gotSeat = false;
	public int vID;

	public Visitors(int i) {//constructor
		vID=i;
		setName("Visitor-" + i);
	}

	public synchronized void run() {
		enterLobby(); //enter lobby and wait.
		if(gotSeat==true){
			takeSeat(); 
			while(Speaker.startPresentation==true){//busy-waiting until speaker ends presentation.
			}
			try {Thread.sleep(60000);} //watches movie and interrupted by clock when movie session is ended by clock.
			catch (InterruptedException e) 
			{
				msg(" goes to Museum after movie");//interrupted by clock to move on for visiting museum
			try {Thread.sleep(15000);} catch (InterruptedException ex) {}
			}			
		}
		else{
			this.setPriority(5); //priority is reset to default
			this.yield();//give up
			msg(" goes to Museum without attendenting movies");
			try {Thread.sleep(15000);} catch (InterruptedException e) {}
		}
		vID-=1;//vID becomes the index for Visitor array in Project1 class
		if(vID<14){//making sure that vID is not the 14th thread.
			if(Project1.Visitor[vID+1].isAlive()==true){
				try {
					Project1.Visitor[vID+1].join(); //current thread has to wait until it above indexed thread dies.
				} catch (InterruptedException e) {
				
				}
			}
		}
		
		msg(" leaves the Museum.");
	}
	
	//makes sure that no more than 6 visitors can get seat and one at a time.
	private void enterLobby() {
		arrivalTimes[vID-1] = (int) (System.currentTimeMillis() - Project1.time);//useful for knowing which came first.
		msg("arrives Ellis Island and waits in the lobby.");
		while(Clock.sessionStart==false||gotSeat==false){ /*Busy waiting as long as any of the condition is true*/
			if(vID-1 == minArrivalTime(arrivalTimes)&&theater_capacity>0){//first come first serve.
				gotSeat=true;	
			}
			else {
				this.setPriority(6); //got no seat and rushes for second session.
			}
			if(Clock.session_count==2){
				
				break;//no reason to be waiting
			}
		}	
	}
	
	private void takeSeat() {
		theater_capacity-=1;
		msg(" enters the room and takes an available seat.");
		arrivalTimes[vID-1] = (int) (System.currentTimeMillis() - Project1.time);//now minimum time is removed from the array.
	}
	//prints message with integrated task of age() function. 
	public void msg(String m) {
		System.out.println("[" + ((System.currentTimeMillis() - Project1.time))	+ "]" 
	                          + getName() + ":" + m);
	}
	
	//calculates the minimum arrival time from the array of visitors' arrival times
	private int minArrivalTime(int arr[]){
		int at=0;
		for(int i=1;i<15;i++){
			if(arr[i]<arr[at])
				at=i;
		}
		
		return at; //simply returns the index of the minimum time.
	}
			
}

	
	
	
	

