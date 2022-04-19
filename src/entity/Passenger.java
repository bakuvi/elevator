package entity;

import java.util.Random;

public class Passenger {

    private int currentFloor;
    private int desiredFloor;

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDesiredFloor() {
        return desiredFloor;
    }

    public void setDesiredFloor(int current, int maxFloor) {
        Random rand = new Random();
        int desiredFloor = rand.nextInt(1, maxFloor);
        this.desiredFloor = desiredFloor >= current ? ++desiredFloor : desiredFloor;
    }
}
