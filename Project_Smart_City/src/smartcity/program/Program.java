package smartcity.program;

import smartcity.drone.Geolocator;

public class Program {

	
	public static void main(String args[]) throws Exception
	{
		String[] coordinates=Geolocator.geoLocate("Paris");
		System.out.println("Latitude: "+coordinates[0]);
		System.out.println("Longitude: "+coordinates[1]);
	}
	
	
	
	
}
