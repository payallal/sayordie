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
		        "   \"leftPressed\": " + barray[0] + "," +
		        "   \"rightPressed\" : " + barray[1] + "," +
		        "   \"jumpPressed\" : " + barray[2] + "," +
		        "   \"leftReleased\" : " + barray[3] + "," +	
		        "   \"rightReleased\" : " + barray[4] + "," +	
		        "   \"jumpReleased\" : " + barray[5] +	
		        " }");
		this.pw.flush();
	}
}
