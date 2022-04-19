package service.impl;

import entity.Elevator;
import entity.ElevatorsState;
import entity.Floor;
import entity.Passenger;
import service.ElevatorLoadService;
import service.ElevatorWorkService;
import service.PassengerUnLoadService;

import java.util.List;

public class ElevatorWorkServiceImpl implements ElevatorWorkService {
    private final PassengerUnLoadService passengerUnLoadService = new PassengerUnLoadServiceImpl();
    private final ElevatorLoadService elevatorLoadService = new ElevatorLoadServiceImpl();

    @Override
    public void start(int maxFloor, List<Floor> passengerList) throws InterruptedException {
        Elevator elevator = new Elevator();
        List<Passenger> passengersInElevator = elevator.getCurrentPassengers();
        elevator.setState(ElevatorsState.UP);
        int extremeDesiredFloor = 0;
        int currentFloor = 1;
        System.out.println("Max floor is: " + maxFloor);

        while (true) {
            List<Passenger> passengersOfCurrentFloor = passengerList.get(currentFloor - 1).getPassengers();
            if (passengersInElevator.isEmpty() && passengersOfCurrentFloor.isEmpty()) {
                currentFloor = 1;
                elevator.setState(ElevatorsState.UP);
                continue;
            }
            printFloorsInfo(elevator, passengersOfCurrentFloor, currentFloor);

            if (!passengersInElevator.isEmpty()) {
                passengersInElevator = passengerUnLoadService.unLoadPassengers(passengersInElevator, passengersOfCurrentFloor, currentFloor, maxFloor);
            }
            if (passengersInElevator.isEmpty()) {
                extremeDesiredFloor = elevatorLoadService.loadElevatorIfEmpty(elevator, passengersOfCurrentFloor,
                        passengersInElevator, extremeDesiredFloor, currentFloor);
            } else if (passengersInElevator.size() < elevator.getCapacity()) {
                extremeDesiredFloor = elevatorLoadService.loadElevator(elevator, passengersOfCurrentFloor,
                        passengersInElevator, extremeDesiredFloor, currentFloor, elevator.getState());
            }
            printElevatorsInfo(passengersInElevator);

            if (currentFloor < extremeDesiredFloor) {
                currentFloor++;
                elevator.setState(ElevatorsState.UP);
            } else if (currentFloor > extremeDesiredFloor) {
                currentFloor--;
                elevator.setState(ElevatorsState.DOWN);
            }
           Thread.sleep(2000);
        }
    }

    private void printFloorsInfo(Elevator elevator, List<Passenger> passengersOfCurrentFloor, int currentFloor) {
        System.out.println("Elevator is going: " + elevator.getState() + ". Current floor is: " + currentFloor
                + ". Number of passengers on the curent floor: " + passengersOfCurrentFloor.size());
        System.out.print("Passengers desired floor are: ");
        passengersOfCurrentFloor.forEach(p -> System.out.print(p.getDesiredFloor() + " "));

    }

    private void printElevatorsInfo(List<Passenger> passengersInElevator) {
        System.out.println("\nNumber of passengers in elevator: " + passengersInElevator.size());
        System.out.print("Passengers desired floor are: ");
        passengersInElevator.forEach(p -> System.out.print(p.getDesiredFloor() + " "));
        System.out.println("\n********************************************************");
    }
}
