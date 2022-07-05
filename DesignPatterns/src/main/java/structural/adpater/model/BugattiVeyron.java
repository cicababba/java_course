package structural.adpater.model;

import structural.adpater.adapters.Movable;

public class BugattiVeyron implements Movable {
    @Override
    public double getSpeed() {
        return 268;
    }// returns MPH speed
}
