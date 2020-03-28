package smartcity.drone;

import java.io.IOException;

public class Drone implements Runnable, Camera {

	private String name;
	private String modelNum;
	private String location;
	private String latitude;
	private String longitude;

	public boolean isOccupied;
	public boolean isActivated = false;
	
	
	public Drone(String name, String modelNum, String location) throws Exception {
		this.setName(name);
		this.setModelNum(modelNum);
		this.setLocation(location);
		String[] coordinates = Geolocator.geoLocate(location);
		this.setLatitude(coordinates[0]);
		this.setLongitude(coordinates[1]);
	}
	
	@Override
	public void startCamera(int time) throws IOException {
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public void run() {
		System.out.println();

	}


}
