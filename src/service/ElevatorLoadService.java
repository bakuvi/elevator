package service;

import entity.Elevator;
import entity.ElevatorsState;
import entity.Passenger;

import java.util.List;

public interface ElevatorLoadService {
    int loadElevator(Elevator elevator, List<Passenger> passengersOfCurrentFloor,
                     List<Passenger> passengersInElevator, int extremeDesiredFloor, int currentFloor, ElevatorsState state);

    int loadElevatorIfEmpty(Elevator elevator, List<Passenger> passengersOfCurrentFloor,
                            List<Passenger> passengersInElevator, int extremeDesiredFloor, int currentFloor);
}
