package com.example.trainticket.service;

import com.example.trainticket.customEexceptions.NotFoundException;
import com.example.trainticket.model.*;
import com.example.trainticket.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
    @Autowired
    TrainRepository trainRepository;

    public void initialise(){
        System.out.println("Initializing trains: Mumbai -> Punjab, Punjab -> New Delhi, New Delhi -> Mumbai");
        trainRepository.create("Mumbai", "Punjab", 200);
        trainRepository.create("Punjab", "New Delhi", 100);
        trainRepository.create("New Delhi", "Mumbai", 150);
    }

    public void setNextAvailableSeat(Train train, Ticket ticket, User user) throws NotFoundException {
        System.out.println(train);
        Section available_section =  getNextAvailableSection(train);
        ticket.setSection(available_section.getSectionName());
        ticket.setSeatNumber(available_section.setNextEmptySeats(user));
    }

    public Section getNextAvailableSection(Train train) throws NotFoundException {
        int mostAvailableSeat = 0;
        Section availableSection = null;
        for(Section section: train.getSections()){
            int availableSeats = section.getEmptySeats();
            if (availableSeats > mostAvailableSeat){
                availableSection = section;
                mostAvailableSeat = availableSeats;
            }
        }

        if (availableSection == null){
            throw new NotFoundException("The Train is Fully booked");
        }
        return availableSection;
    }

    public void deallocateSeat(Train train, String sectionName, int seatNo) throws NotFoundException {
        for (Section section :train.getSections()){
            if (section.getSectionName().equals(sectionName)){
                section.deallocateSeat(seatNo);
            }
        }
    }

}
