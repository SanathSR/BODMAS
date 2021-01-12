package questionNo01;

import java.util.Scanner;

public class ParkingApplication {

	private static Scanner scanner = new Scanner(System.in);
	private static byte count = 100;

	public static void main(String[] args) {

		VehicleParking[] parkingArray = null;
//		byte count = 100;

		boolean flag = true;

		do {

			byte choice = mainMenu();

			switch (choice) {

			case 1:
				checkForParkingAvilability();
				break;

			case 2:
				parkingArray = getVehicleNumber(parkingArray);
				break;

			case 3:
				parkingArray = enterVehicleTime(parkingArray);
				break;

			case 4:
				allocateSlotForParking(parkingArray);
				break;

			case 5:
				parkingArray = exitFromParkig(parkingArray);
				break;

			case 6:
				System.out.println("Thankyou for using this application");
				flag = false;
				break;
			}

		} while (flag);

	}

	private static VehicleParking[] exitFromParkig(VehicleParking[] parkingArray) {

		if (parkingArray != null) {

			System.out.println("Enter the vehicleNumber first");
			String vehicleNumber = scanner.next();

			byte index = getIndex(parkingArray, vehicleNumber);
			if (index >= 0) {
				if (parkingArray[index].getEnteryTime() == null) {
					System.out.println("First enter the entry time and the continue");
				} else {
					System.out.println("Enter the exit time for vehicle " + vehicleNumber);
					String exitTime = scanner.next();
					exitTime = checkTime(exitTime);

					parkingArray[index].setExitTime(exitTime);

					calculateAmount(parkingArray[index]);
					count++;

					VehicleParking[] temporary = new VehicleParking[parkingArray.length - 1];
					byte cnt = 0;
					for (byte j = 0; j < parkingArray.length; j++) {

						if (j == index)
							continue;
						else {
							temporary[cnt] = parkingArray[j];
							cnt++;
						}
					}
					parkingArray = temporary;
				}
			} else
				System.out.println(
						"There is no such vehicle number entered parking area\nplease enter the vehicle number first in main menu\n");
		} else
			System.out.println("There are no vehicles in slot\nenter the vehicle number first in 2nd option\n");

		return parkingArray;
	}

	private static void calculateAmount(VehicleParking vehicleParking) {

		int entryHour = getHour(vehicleParking.getEnteryTime());
		int exitHour = getHour(vehicleParking.getExitTime());

		int entryMinutes = getMinutes(vehicleParking.getEnteryTime());
		int exitMinutes = getMinutes(vehicleParking.getExitTime());

		int parkingMinutes = 0, parkingHour = 0;

		if (entryHour <= exitHour) {
			if (entryMinutes <= exitMinutes) {
				parkingMinutes = exitMinutes - entryMinutes;
				parkingHour = exitHour - entryHour;
			} else {
				parkingMinutes = 60 - (entryMinutes - exitMinutes);
				parkingHour = (exitHour - 1) - entryHour;
			}
		} else {
			if (entryMinutes <= exitMinutes) {
				parkingMinutes = exitMinutes - entryMinutes;
				parkingHour = 24 - (entryHour - exitHour);
			} else {
				parkingMinutes = 60 - (entryMinutes - exitMinutes);
				parkingHour = 24 - (entryHour - exitHour);
			}
		}

		if (parkingHour < 2)
			System.out.println("Your parking charge is Rs50");
		else if (parkingHour == 2 && parkingMinutes > 0)
			System.out.println("Your parking charge is Rs80");
		else {
			int temp = parkingHour - 2;
			int amount = 50;
			while (temp != 0) {
				amount += 30;
				temp--;
			}
			if (parkingMinutes <= 0)
				System.out.println("Your parking charge is Rs" + amount);
			else
				System.out.println("Your parking charge is Rs" + (amount + 30));
		}
	}

	private static void allocateSlotForParking(VehicleParking[] parkingArray) {

		if (parkingArray != null) {
			System.out.println("Enter vehicle number first");
			String vehicleNumber = scanner.next();

			byte index = getIndex(parkingArray, vehicleNumber);

			if (index >= 0) {
				if (parkingArray[index].getEnteryTime() == null) {
					System.out.println("First enter the entry time and the continue");
				} else {
					int slotNumber = (vehicleNumber.length() * vehicleNumber.charAt(0));

					System.out.println("Your parking slot number is " + slotNumber);
				}
			} else
				System.out.println(
						"There is no such vehicle number entered parking area\nplease enter the vehicle number first in main menu\n");

		} else
			System.out.println("There are no vehicles in slot\nenter the vehicle number first in 2nd option\n");
	}

	private static VehicleParking[] enterVehicleTime(VehicleParking[] parkingArray) {

		if (parkingArray != null) {

			System.out.println("Enter the vehicle number first");
			String vehicleNumber = scanner.next();

			byte index = getIndex(parkingArray, vehicleNumber);

			if (index >= 0) {
				if (parkingArray[index].getEnteryTime() != null) {
					System.out.println("Entry time for this vehicle is already entered");
				} else {
					System.out.println("Enter the time of entry in HH:MM format only");
					String enterTime = scanner.next();
					enterTime = checkTime(enterTime);

					parkingArray[index].setEnteryTime(enterTime);
				}
			} else
				System.out.println(
						"There is no such vehicle number entered parking area\nplease enter the vehicle number first in main menu");

		} else
			System.out.println("There are no vehicles in slot\nenter the vehicle number first in 2nd option\n");

		return parkingArray;
	}

	private static String checkTime(String time) {

		if (time.length() == 5)
			if (time.charAt(2) == ':') {
				int hour = getHour(time);
				if (hour >= 0 && hour <= 23) {
					int minutes = getMinutes(time);
					if (minutes >= 0 && minutes <= 59) {
						return time;
					} else {
						System.out.println("Please enter correct time");
						time = scanner.next();
						return checkTime(time);
					}
				} else {
					System.out.println("Please enter correct time");
					time = scanner.next();
					return checkTime(time);
				}
			} else {
				System.out.println("Please enter time in HH:MM format only");
				time = scanner.next();
				return checkTime(time);
			}
		else {
			System.out.println("Please enter time in HH:MM format only");
			time = scanner.next();
			return checkTime(time);
		}
	}

	private static int getMinutes(String time) {

		String temp = "";
		temp += time.charAt(3);
		temp += time.charAt(4);

		int minutes = stringToInt(temp);

		return minutes;
	}

	private static int getHour(String time) {

		String temp = "";
		temp += time.charAt(0);
		temp += time.charAt(1);

		int hour = stringToInt(temp);

		return hour;
	}

	private static int stringToInt(String temp) {

		int finalNumber = 0;
		for (int i = 0; i < temp.length(); i++) {
			int temporary = (int) temp.charAt(i);
			finalNumber = (finalNumber * 10) + (temporary - 48);
		}
		return finalNumber;
	}

	private static byte getIndex(VehicleParking[] parkingArray, String vehicleNumber) {

		byte index = -1;

		for (byte i = 0; i < parkingArray.length; i++)
			if (parkingArray[i].getVehicleNumber().compareTo(vehicleNumber) == 0)
				index = i;

		return index;
	}

	private static VehicleParking[] getVehicleNumber(VehicleParking[] parkingArray) {

		if (count > 0) {

			System.out.println("Enter the vehicle number");
			String vehicleNumber = scanner.next();
			VehicleParking temp = new VehicleParking(vehicleNumber, null, null);

			parkingArray = takeToArray(parkingArray, temp);
			count--;

		} else
			System.out.println(
					"Parking Slots are full, please check for parking space in 1st option from main menu and enter");

		return parkingArray;
	}

	private static VehicleParking[] takeToArray(VehicleParking[] parkingArray, VehicleParking temp) {

		if (parkingArray == null) {
			VehicleParking[] temporary = new VehicleParking[1];
			temporary[0] = temp;
			return temporary;
		} else {

			VehicleParking[] temporary = new VehicleParking[parkingArray.length + 1];
			for (byte i = 0; i < parkingArray.length; i++)
				temporary[i] = parkingArray[i];
			temporary[parkingArray.length] = temp;
			return temporary;
		}
	}

	private static void checkForParkingAvilability() {

		if (count > 0)
			System.out.println("There are " + count + " scapces for parking\n you can park here");
		else
			System.out.println("Sorry there are no parking slots free at this time");
	}

	private static byte mainMenu() {

		System.out.println("Choose from below menu\n");
		System.out.println("1 ---> Check vehicle parking");
		System.out.println("2 ---> vehicle number for parking");
		System.out.println("3 ---> Entry time for vehicle number");
		System.out.println("4 ---> Allocate empty slot for vehicle parking");
		System.out.println("5 ---> Exit vehicle from parking");
		System.out.println("6 ---> Exit the menu");

		byte choice = scanner.nextByte();
		return choice;
	}

}
