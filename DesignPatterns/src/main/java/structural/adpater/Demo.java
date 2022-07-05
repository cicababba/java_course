package structural.adpater;

import structural.adpater.adapters.Movable;
import structural.adpater.adapters.MovableAdapter;
import structural.adpater.adapters.MovableAdapterImpl;
import structural.adpater.model.BugattiVeyron;

public class Demo {
    public static void main(String[] args) {

        Movable bugattiVeyron = new BugattiVeyron();

        MovableAdapter bugattiVeyronAdapter = new MovableAdapterImpl(bugattiVeyron);

        System.out.println(bugattiVeyronAdapter.getSpeed());// KM/H
        System.out.println(bugattiVeyron.getSpeed());// MPH
    }
}
