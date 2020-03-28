package smartcity.drone;

import java.io.IOException;

import smartcity.users.Police;
import smartcity.webcams.DisplayImage;

public class MultiDrone extends Drone implements Surveillance {

	public MultiDrone(String name, String modelNum, String location) throws Exception {
		super(name, modelNum, location);
	}

	@Override
	public void informPolice(Object o) {
		Police.addInbox(o);
		
	}

	@Override
	public void showImage(String s) throws IOException {
		DisplayImage.displayImage(s);
		
	}

	
	
	
	
	
	
	
	
	
}
