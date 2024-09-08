package com.example.trainticket.repository;

import com.example.trainticket.model.Train;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrainRepository {
    private List<Train> trains = new ArrayList<Train>();


    public Train find(String from, String to){
        for (Train t: this.trains){
            if (t.getFrom().equals(from) && t.getTo().equals(to)){
                return t;
            }
        }
        return null;
    }

    public Train create(String from, String to, double price){
        Train train = find(from,to);
        if (train != null){
            return train;
        }

        train = new Train(from, to, price);
        trains.add(train);
        return train;
    }

}
