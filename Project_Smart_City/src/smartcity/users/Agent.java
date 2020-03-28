package smartcity.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import smartcity.drone.Drone;
import smartcity.drone.MultiDrone;
import smartcity.drone.TouristDrone;

public class Agent extends User {

	Drone D;
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	
	@Override
	public void introduce() throws IOException, Exception {
		System.out.println("Hello Agent! Please enter your location: ");
		String location = reader.readLine();
		setLocation(location);
		
		D = ControlPanel.getTouristDroneInLocation(location);
		
		if(D instanceof TouristDrone){
			D.isOccupied = true;
			System.out.println("TouristDrone " + D.getName() + " at " + D.getLocation() + " is here.");
		}
		else if(D instanceof MultiDrone){
			D.isOccupied = true;
			System.out.println("MultiDrone " + D.getName() + " at " + D.getLocation() + " is here.");
		}
		new Agent().interact();

	}

	@Override
	public void interact() throws IOException, Exception {
		System.out.println("Agent: [1. Register with Tourist Service, "
				+ "2. Send Drone to Tourist, 3. Report Suspicious Activity, 4. Exit]");
		int choice = Integer.parseInt(reader.readLine());
		String place;
		String[] coordinates;
		
		switch(choice){
		case 1: //Add his details to Database
			
		case 2: //Get Tourist passport number from Agent, look in database, get location, go there
			
		case 3: //Call Police Drones
			
		case 4: System.exit(0);

		}	
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
