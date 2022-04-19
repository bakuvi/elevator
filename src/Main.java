import entity.Floor;
import service.ElevatorWorkService;
import service.GenerateService;
import service.impl.ElevatorWorkServiceImpl;
import service.impl.GenerateServiceImpl;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        ElevatorWorkService elevatorWorkService = new ElevatorWorkServiceImpl();
        GenerateService generateService = new GenerateServiceImpl();
        int maxFloor = generateService.generateMaxLevel(rand);
        List<Floor> passengerList = generateService.generatePassengersOnTheFloors(rand, maxFloor);
        elevatorWorkService.start(maxFloor, passengerList);
    }
}