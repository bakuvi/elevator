package service.impl;

import entity.Elevator;
import entity.ElevatorsState;
import entity.Passenger;
import service.ElevatorLoadService;

import java.util.ArrayList;
import java.util.List;

public class ElevatorLoadServiceImpl implements ElevatorLoadService {
    @Override
    public int loadElevator(Elevator elevator, List<Passenger> passengersOfCurrentFloor,
                            List<Passenger> passengersInElevator, int extremeDesiredFloor, int currentFloor, ElevatorsState state) {

        int updatedExtremeDesiredFloor = extremeDesiredFloor;
        List<Passenger> passengersAbleToGetIn = new ArrayList<>();
        for (Passenger passenger : passengersOfCurrentFloor) {
            if (elevator.getState().equals(ElevatorsState.UP) && passenger.getDesiredFloor() > currentFloor) {
                passengersAbleToGetIn.add(passenger);
            } else if (elevator.getState().equals(ElevatorsState.DOWN) && passenger.getDesiredFloor() < currentFloor) {
                passengersAbleToGetIn.add(passenger);
            }
        }
        int passNeeded = Math.min(elevator.getCapacity() - passengersInElevator.size(), passengersAbleToGetIn.size());
        for (int i = 0; i < passNeeded; i++) {
            Passenger passenger = getPassenger(passengersOfCurrentFloor, passengersInElevator, passengersAbleToGetIn, i);
            if (elevator.getState().equals(ElevatorsState.UP)) {
                updatedExtremeDesiredFloor = Math.max(updatedExtremeDesiredFloor, passenger.getDesiredFloor());
            } else if (elevator.getState().equals(ElevatorsState.DOWN)) {
                updatedExtremeDesiredFloor = Math.min(updatedExtremeDesiredFloor, passenger.getDesiredFloor());
            }
        }
        return updatedExtremeDesiredFloor;
    }

    @Override
    public int loadElevatorIfEmpty(Elevator elevator, List<Passenger> passengersOfCurrentFloor,
                                   List<Passenger> passengersInElevator, int extremeDesiredFloor, int currentFloor) {

        List<Passenger> passengersGoingUp = new ArrayList<>();
        List<Passenger> passengersGoingDown = new ArrayList<>();
        int amountOfGoingUp = 0;
        int amountOfGoingDown = 0;
        int updatedExtremeDesiredFloor = extremeDesiredFloor;
        int passNeeded;
        for (Passenger passenger : passengersOfCurrentFloor) {
            if (passenger.getDesiredFloor() > currentFloor) {
                amountOfGoingUp++;
                passengersGoingUp.add(passenger);
            } else {
                amountOfGoingDown++;
                passengersGoingDown.add(passenger);
            }
        }

        if (amountOfGoingUp > amountOfGoingDown) {
            passNeeded = Math.min(elevator.getCapacity(), passengersGoingUp.size());
            for (int i = 0; i < passNeeded; i++) {
                Passenger passenger = getPassenger(passengersOfCurrentFloor, passengersInElevator, passengersGoingUp, i);
                updatedExtremeDesiredFloor = Math.max(updatedExtremeDesiredFloor, passenger.getDesiredFloor());
            }
        } else {
            passNeeded = Math.min(elevator.getCapacity(), passengersGoingDown.size());
            for (int i = 0; i < passNeeded; i++) {
                Passenger passenger = getPassenger(passengersOfCurrentFloor, passengersInElevator, passengersGoingDown, i);
                updatedExtremeDesiredFloor = Math.min(updatedExtremeDesiredFloor, passenger.getDesiredFloor());
            }

        }
        return updatedExtremeDesiredFloor;
    }

    private Passenger getPassenger(List<Passenger> passengersOfCurrentFloor, List<Passenger> passengersInElevator, List<Passenger> passengersAbleToGetIn, int i) {
        Passenger passenger = passengersAbleToGetIn.get(i);
        passengersOfCurrentFloor.remove(passenger);
        passengersInElevator.add(passenger);
        System.out.print("\nPassenger with desired floor of " + passenger.getDesiredFloor() + " has entered the elevator");
        return passenger;
    }
}