package entity;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private final int capacity = 5;
    private ElevatorsState state;

    public ElevatorsState getState() {
        return state;
    }

    public void setState(ElevatorsState state) {
        this.state = state;
    }

    List<Passenger> currentPassengers = new ArrayList<>();

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getCurrentPassengers() {
        return currentPassengers;
    }
}