package smartcity.users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import smartcity.drone.Drone;
import smartcity.drone.PoliceDrone;
import smartcity.drone.TouristDrone;

public class Manufacturer {

	
	private static ArrayList<Drone> dronesStorage=new ArrayList<Drone>();
	
	// Method to produce Drones
	public static void manufactureDrone()throws Exception {
		Drone D;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Manufacture: 1. Tourist Drone, 2. Police Drone");
		int choice = Integer.parseInt(reader.readLine());
			
		switch (choice) {
		case 1: // Creating a Tourist Drone
			System.out.println("Please enter the drone name: ");
			String name = reader.readLine();
			System.out.println("Please enter the drone model number: ");
			String model = reader.readLine();
			System.out.println("Please enter the Drone installation location: ");
			String location = reader.readLine();
			D = new TouristDrone(name, model, location);
			dronesStorage.add(D);
			break;
		case 2: // Creating a Police Drone
			System.out.println("Please enter the drone name: ");
			name = reader.readLine();
			System.out.println("Please enter the drone model number: ");
			model = reader.readLine();
			System.out.println("Please enter the Drone installation location: ");
			location = reader.readLine();
			D = new PoliceDrone(name, model, location);
			break;
		}
	}
	
	public static void addDrones(Drone d) {
		dronesStorage.add(d);
	}
	
	public static void removeDrones(Drone d) {
		dronesStorage.remove(d);
	}
	
	public static Drone getDrone(int i) {
		return Manufacturer.dronesStorage.get(i);
	}
	
	public static void viewDrones() {
		int i = 1;
		for (Drone drone : dronesStorage) {
			System.out.println(i + "." + drone.getName() + " " + drone.getModelNum() + " " + drone.getLocation());
			i++;
		}
	}
	
	
	
}