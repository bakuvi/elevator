package service.impl;

import entity.Floor;
import entity.Passenger;
import service.GenerateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateServiceImpl implements GenerateService {

    @Override
    public int generateMaxLevel(Random rand) {
        return rand.nextInt(5, 21);
    }

    @Override
    public List<Floor> generatePassengersOnTheFloors(Random rand, int maxFloor) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= maxFloor; i++) {
            int k = rand.nextInt(11);
            List<Passenger> passengers = new ArrayList<>();
            Floor floor = new Floor();
            for (int j = 0; j <= k; j++) {
                Passenger passenger = new Passenger();
                passenger.setCurrentFloor(i);
                passenger.setDesiredFloor(i, maxFloor);
                passengers.add(passenger);
            }
            floor.setPassengers(passengers);
            floors.add(floor);
        }
        return floors;
    }
}
