package questionNo01;

public class VehicleParking {

	private String vehicleNumber;
	private String enteryTime;
	private String exitTime;

	public VehicleParking() {

	}

	public VehicleParking(String vehicleNumber, String enteryTime, String exitTime) {

		this.vehicleNumber = vehicleNumber;
		this.enteryTime = enteryTime;
		this.exitTime = exitTime;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getEnteryTime() {
		return enteryTime;
	}

	public void setEnteryTime(String enteryTime) {
		this.enteryTime = enteryTime;
	}

	public String getExitTime() {
		return exitTime;
	}

	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
}