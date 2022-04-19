package service;

import entity.Floor;

import java.util.List;
import java.util.Random;

public interface GenerateService {
     int generateMaxLevel(Random rand);

     List<Floor> generatePassengersOnTheFloors(Random rand, int maxFloor);
}
