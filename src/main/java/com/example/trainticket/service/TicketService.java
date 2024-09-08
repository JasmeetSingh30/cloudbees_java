package com.example.trainticket.service;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import com.example.trainticket.customEexceptions.NotFoundException;
import com.example.trainticket.model.*;
import com.example.trainticket.repository.TicketRepository;
import com.example.trainticket.repository.TrainRepository;
import com.example.trainticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TrainRepository trainRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainService trainService;

    @Autowired
    TicketRepository ticketRepo;


    public int purchaseTicket(String firstName, String lastName, String email, String from, String to) throws NotFoundException {
        Train train =  trainRepo.find(from, to);
        if(train == null){
            throw new NotFoundException("Train does not exist.");
        }

        User user = userRepository.findOrCreate(firstName, lastName, email);

        Ticket ticket = ticketRepo.create(user, train);

        trainService.setNextAvailableSeat(train, ticket, user);

        ticket.setPrice(train.getPrice());

        return ticket.getId();
    }

    public String getReceipt(int ticketId) {
        Ticket ticket = ticketRepo.getTicketById(ticketId);
        return (ticket != null) ? ticket.toString() : "Ticket not found.";
    }

    public List<String> viewUsersAndSeats(String from, String to) {
        List<String> usersAndSeats = new ArrayList<>();

        Train train = trainRepo.find(from, to);
        usersAndSeats.add("---------Train: "+ train.getFrom()+ "-->"+train.getTo() + " ------------");

        for (Section section: train.getSections()){
            usersAndSeats.add("--------------Section: "+ section.getSectionName()+" -----------");
         for (Seat seat: section.getSeats()){
             User user = seat.getUser();
             usersAndSeats.add("Name: " + (user != null ? user.getFirstName(): "Not Occupied") + " " + (user != null ? user.getLastName(): "") +
                     ", Section: " + section.getSectionName()  + ", Seat: " + seat.getSeatNo());
         }
        }
        return usersAndSeats;
    }

    public void removeUser(int ticketId) throws NotFoundException {
        Ticket ticket = ticketRepo.getTicketById(ticketId);
        Train train = trainRepo.find(ticket.getTrain().getFrom(), ticket.getTrain().getTo());
        trainService.deallocateSeat(train, ticket.getSection(), ticket.getSeatNumber());
    }

    public boolean modifySeat(int ticketId, String sectionName, int seatNo) throws Exception {
        Ticket ticket = ticketRepo.getTicketById(ticketId);
        Train train = trainRepo.find(ticket.getTrain().getFrom(), ticket.getTrain().getTo());
        for(Section section: train.getSections()){
            if(section.getSectionName().equals(sectionName)){
                for(Seat seat: section.getSeats()){
                    if(seat.getSeatNo() == seatNo){
                        if(seat.getUser() == null){
                            seat.setUser(ticket.getUser());
                            trainService.deallocateSeat(train, ticket.getSection(), ticket.getSeatNumber());
                            ticketRepo.modifyTicket(ticketId, seatNo, sectionName);
                            return true;
                        }else{
                            throw new Exception("Seat already allocated.");
                        }
                    }
                }
            }
        }
        return false;
    }
}
