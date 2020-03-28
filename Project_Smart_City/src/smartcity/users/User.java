package smartcity.users;

import java.io.IOException;

import smartcity.drone.Geolocator;

abstract class User {

	public abstract void introduce() throws IOException, Exception;

	public abstract void interact() throws IOException, Exception;

	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	

}
