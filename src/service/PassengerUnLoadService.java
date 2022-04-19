package service;

import entity.Passenger;

import java.util.List;

public interface PassengerUnLoadService {
    List<Passenger> unLoadPassengers(List<Passenger> passengersInElevator, List<Passenger> passengersOfCurrentFloor, int currentFloor, int maxFloor);
}
