package com.example.trainticket.model;

import com.example.trainticket.customEexceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Section {
    private String sectionName;
    private List<Seat> seats;

    public Section(String sectionName){
        this.sectionName = sectionName;
        this.seats = new ArrayList<Seat>(Collections.nCopies(10, new Seat(0)));
        for(int i=0; i < seats.size(); i++){
            seats.set(i, new Seat(i));
        }
        System.out.printf("section have seats initialised, seatno is %d", this.seats.get(2).getSeatNo());
    }

    public String getSectionName(){
        return this.sectionName;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSectionName(String sectionName){
        this.sectionName = sectionName;
    }

    public int getEmptySeats(){
        int seatCounter = 0;
        for(Seat seat: seats){
            if(seat.getUser() == null){
                seatCounter++;
            }
        }
        System.out.printf("%s has %d available seats\n", this.getSectionName(), seatCounter);
        System.out.println();
        return seatCounter;
    }

    public int setNextEmptySeats(User user){
        for(Seat seat: seats){
            if(seat.getUser() == null){
                seat.setUser(user);
                return seat.getSeatNo();
            }
        }
        return -1;
    }

    public void deallocateSeat(int seatNo) throws NotFoundException {
        for(Seat seat: seats){
            if(seat.getSeatNo() == seatNo){
                seat.removeUser();
                return;
            }
        }
        throw new NotFoundException("Seat no does not exist");
    }


}
