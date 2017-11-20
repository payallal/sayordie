package multiplayer;

import java.io.PrintWriter;

public class WriteJSONThread extends Thread{
	
	private PrintWriter pw;
	private boolean [] barray;
	
	public WriteJSONThread (PrintWriter pw, boolean [] barray) {
		this.pw = pw;
		this.barray = barray;
	}
	
	@Override
	public void run() {
		this.pw.println(
				"  {" +
		        "   \"start\": " + barray[0] + "," +
		        "   \"right\" : " + barray[1] + "," +
		        "   \"jump\" : " + barray[2] + "," +
		        "   \"stop\" : " + barray[3] +	
		        " }");
		this.pw.flush();
	}
}
