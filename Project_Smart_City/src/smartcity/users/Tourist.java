package smartcity.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import smartcity.drone.Drone;
import smartcity.drone.MultiDrone;
import smartcity.drone.PoliceDrone;
import smartcity.drone.TouristDrone;
import smartcity.drone.Geolocator;
import smartcity.webcams.Selfie;

public class Tourist extends User {

	Drone D;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	

	@Override
	public void introduce() throws IOException, Exception {
		System.out.println("Hello Tourist! Please enter your location: ");
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
		else if(D instanceof Drone){
			D.isOccupied = true;
			System.out.println("Drone " + D.getName() + " at " + D.getLocation() + " is here.");
		}
		new Tourist().interact();
		
	}

	@Override
	public void interact() throws IOException, Exception {
		while(true){
			System.out.println("Tourist: [1. Go with Drone to new location, "
					+ "2. Check whether place is in current city, 3. Report Suspicious Activity, "
					+ "4. Contact Agents, 5. Take Selfie, 6. Exit]");
			int choice=Integer.parseInt(reader.readLine());
			String place;
			String[] coordinates;

			switch(choice){
			case 1:  System.out.println("Please enter place: ");
					 place = reader.readLine();
					 coordinates = Geolocator.geoLocate(place);
					 D.setLatitude(coordinates[0]);
					 D.setLongitude(coordinates[1]);
					 setLocation(place);
					 continue;
		
			case 2: System.out.println("Please enter place: ");
			place = reader.readLine();
			String location = getLocation();
		    TouristDrone.checkPlaceWithinCity(place, location);
		    continue;
		    
			case 3: //Call Police Drone to investigate
				System.out.println("Drone " + D.getName()+" is contacting nearby Police Drones.");
				Drone cop=ControlPanel.getPoliceDroneInLocation(getLocation());
				System.out.println("Drone " + cop.getName() + " is here.");
				//If suspicious activity found, inform Police by sending a message
				
				PoliceDrone PD = (PoliceDrone)D;
				PD.startCamera(10); //Recording movement for 10 seconds
				PD.informPolice(new String("SUSPECT IS BEING MONITORED"));
				PD.informPolice("Location: " + D.getLatitude() + " , " + D.getLongitude());
		    
			case 4: //Contact Agents
				
			case 5: System.out.println("Please enter your name: ");
					Selfie.takeSelfie(reader.readLine());
					continue;
					
			case 6: D.isActivated=false;
					System.exit(0);
		
	}
	
		}
	}
	
}
