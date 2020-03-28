package smartcity.users;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import smartcity.drone.Drone;
import smartcity.drone.MultiDrone;
import smartcity.drone.PoliceDrone;
import smartcity.drone.TouristDrone;

public class ControlPanel {

	private static ArrayList<Drone> dronesActive = new ArrayList<Drone>();

	public static void activateDrones(Drone d) {
		Manufacturer.removeDrones(d); // remove the drone from the storage
		d.isActivated = true; // set it to active
		dronesActive.add(d); // put it on the active drone list
		System.out.println("Drone " + d.getName() + " activated. ");
	}

	public static void deactivateDrones(Drone d) {
		dronesActive.remove(d); // remove the drone from the active drone list
		d.isActivated = false; // deactivate it
		Manufacturer.addDrones(d); // add it to the storage
		System.out.println("Drone " + d.getName() + " deactivated. ");
	}

	// Method to get the list of active drones
	public static void viewActivated() {
		int i = 1;
		for (Drone drone : dronesActive) {
			System.out.println(i + "." + drone.getName() + " " + drone.getModelNum() + " " + drone.getLocation());
			i++;
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Choose: 1. Control, 2. Users, 3. Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				controlPanel();
				continue;
			case 2:
				users();
				continue;
			case 3:
				System.exit(0);
			}
		}
	}

	// Method to create a User
	public static void users() throws Exception {
		User U;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean condition = true;

		while (condition) {
			System.out.println("Users: [1. Tourist, 2. Police, 3. Cab/Taxi Agent, 4. Exit]");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				U = new Tourist();
				U.introduce();
				U.interact();
				continue;
			case 2:
				U = new Police();
				U.introduce();
				U.interact();
				continue;
			case 3:
				U = new Agent();
				U.introduce();
				U.interact();
				continue;
			case 4:
				ControlPanel.viewActivated();
				continue;
			case 5:
				Manufacturer.manufactureDrone();
				continue;
			case 6:
				condition = false;
				break;
			}
		}
	}

	// CONTROL PANEL
	public static void controlPanel() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean condition = true;
		while (condition) {
			System.out.println(
					"Control Panel: [1. Activate Drones, 2. Deactivate Drones, 3. View Drones in Storage, 4. View Activated Drones, 5. Order Drone, 6. Exit");
			int choice = Integer.parseInt(reader.readLine());

			switch (choice) {
			case 1:
				Manufacturer.viewDrones();
				System.out.println("Drone number: ");
				// With the number entered, we get a drone from storage and activate it
				ControlPanel.activateDrones(Manufacturer.getDrone(Integer.parseInt(reader.readLine()) - 1));
				continue;

			case 2:
				ControlPanel.viewActivated();
				System.out.println("Drone number: ");
				ControlPanel.deactivateDrones(Manufacturer.getDrone(Integer.parseInt(reader.readLine()) - 1));
				continue;

			case 3:
				Manufacturer.viewDrones();
				continue;

			case 4:
				ControlPanel.viewActivated();
				continue;

			case 5:
				Manufacturer.manufactureDrone();
				continue;

			case 6:
				condition = false;
				break;
			}
		}
	}

	public static Drone getTouristDroneInLocation(String s) {

		for (Drone drone : dronesActive) {
			if (drone instanceof TouristDrone || drone instanceof MultiDrone) {
				if (drone.getLocation().equalsIgnoreCase(s)) {
					return (TouristDrone) drone;
				}
			}
		}
		return null;
	}

	public static Drone getPoliceDroneInLocation(String s) {

		for (Drone drone : dronesActive) {
			if (drone instanceof PoliceDrone || drone instanceof MultiDrone) {
				if (drone.getLocation().equalsIgnoreCase(s)) {
					return (PoliceDrone) drone;
				}
			}
		}
		return null;
	}

	public static Drone getDrone(int i) {
		return dronesActive.get(i);
	}

}