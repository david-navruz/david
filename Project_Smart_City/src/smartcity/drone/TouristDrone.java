package smartcity.drone;

public class TouristDrone extends Drone {

	public TouristDrone(String name, String modelNum, String location) throws Exception {
		super(name, modelNum, location);
	}
	
	// Method to check if the searched place is within the location
	public static void checkPlaceWithinCity(String place, String location) throws Exception {
		String[] coordinates = Geolocator.geoLocate(place);
		String[] coordinates_tourist = Geolocator.geoLocate(location);
		boolean isWithin = false;
		// If latitude mathes?
		if ((int) Integer.parseInt(coordinates_tourist[0]) == (int) Integer.parseInt(coordinates[0])) {
			// If longitude matches ?
			if ((int) Integer.parseInt(coordinates_tourist[1]) == (int) Integer.parseInt(coordinates[1])) {
				// Location is within the place
				isWithin = true;
			}
			if (isWithin) {
				System.out.println(place + " is in " + location + ".");
			} else {
				System.out.println(place + " is not in " + location + ".");
			}
		}

	}

}
