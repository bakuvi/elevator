package service.impl;

import entity.Passenger;
import service.PassengerUnLoadService;

import java.util.ArrayList;
import java.util.List;

public class PassengerUnLoadServiceImpl implements PassengerUnLoadService {
    @Override
    public List<Passenger> unLoadPassengers(List<Passenger> passengersInElevator, List<Passenger> passengersOfCurrentFloor, int currentFloor, int maxFloor) {
        List<Passenger> remainingPassengers = new ArrayList<>(passengersInElevator);
        for (Passenger passenger : passengersInElevator) {
            if (passenger.getDesiredFloor() == currentFloor) {
                System.out.print("\nPassenger with desired floor of " + passenger.getDesiredFloor() + " has left");
                remainingPassengers.remove(passenger);
                passenger.setCurrentFloor(currentFloor);
                passenger.setDesiredFloor(currentFloor, maxFloor);
                passengersOfCurrentFloor.add(passenger);
            }
        }
        return remainingPassengers;

    }
}
