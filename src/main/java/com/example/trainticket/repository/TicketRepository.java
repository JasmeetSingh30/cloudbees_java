package com.example.trainticket.repository;

import com.example.trainticket.customEexceptions.NotFoundException;
import com.example.trainticket.model.Ticket;
import com.example.trainticket.model.Train;
import com.example.trainticket.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class TicketRepository {
    private static int ticketCounter = 0;
    public HashMap<Integer, Ticket> tickets = new HashMap<>();

    public Ticket create(User user, Train train){
        Ticket ticket = new Ticket(user, train);
        ticket.setId(generateId());

        tickets.put(ticket.getId(), ticket);

        System.out.printf("Booked ticket from %s to %s  for %s %s at %f with id %d\n", train.getFrom(), train.getTo(), user.getFirstName(), user.getLastName(), train.getPrice(), ticket.getId());
        System.out.println();
        return ticket;
    }

    int generateId(){
        return ++ticketCounter;
    }

    public Ticket getTicketById(int id){
        return tickets.get(id);
    }

    public Ticket modifyTicket(int id, int seatNo, String section) throws NotFoundException {
        Ticket ticket = getTicketById(id);
        if(ticket == null){
            throw new NotFoundException("Ticket Does not exist");
        }

        ticket.setSection(section);
        ticket.setSeatNumber(seatNo);
        return ticket;
    }

}

