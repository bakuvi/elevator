package service;

import entity.Floor;

import java.util.List;

public interface ElevatorWorkService {
    void start(int maxFloor, List<Floor> passengerList) throws InterruptedException;
}
