
public class Speaker extends Thread {
	
	final long startTime = System.currentTimeMillis();
	public static boolean startPresentation=false;  //used for start and ending of presentation.
	
	public Speaker() {//constructor
		setName("Speaker ");
	}
	
	//prints message with integrated task of age() function. 
	public void msg(String m) {
		System.out.println("[" + ((System.currentTimeMillis() - Project1.time)) + "]" 
				+ getName() + ":" + m); //getName is built in
	}
	
	
	public synchronized void run(){
		
		msg(" arrives and waits for the show to start");
		try {Thread.sleep(30000);} //sleeps until interrupted for the 1st presentation.
		catch (InterruptedException e) {
			try {Thread.sleep(3000);} catch (InterruptedException ex){}  //so that six of the visitors can take their seats for the 1st session
			msg(" starts presentation for 1st session.");
			startPresentation = true;
			try {Thread.sleep(4000);} catch (InterruptedException ex) {}//giving presentation
			startPresentation = false;
			msg(" finishes the presentation for the 1st session.");		
			
		}//end try for the first session
		
		try {
			Thread.sleep(30000); //sleeps until interrupted for the 2nd presentation.
		} catch (InterruptedException e) {
			try {Thread.sleep(3000);} catch (InterruptedException ex){} //so that six of the visitors can take their seats for the 2nd session
			msg(" starts presentation for 2nd session.");
			startPresentation = true;
			try {Thread.sleep(4000);} catch (InterruptedException ex) {}//giving presentation
			startPresentation = false;
			msg(" finishes the presentation for the 2nd session.");			
			
			try {
				Thread.sleep(20000);// sleeps again until interrupted for the end of the day
			} catch (InterruptedException ex) {
				leave(); //cannot leave before clock ends the day.			
			}
		}
	}
	
	private void leave(){
		msg(" leaves.");
	}
	
}
