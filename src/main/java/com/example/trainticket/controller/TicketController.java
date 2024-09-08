package com.example.trainticket.controller;

import java.util.List;

import com.example.trainticket.customEexceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.trainticket.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/purchase", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity purchaseTicket(@RequestParam String firstName,
                                         @RequestParam String lastName,
                                         @RequestParam String email,
                                         @RequestParam String from,
                                         @RequestParam String to) {
        int ticketId = 0;
        try {
            ticketId = ticketService.purchaseTicket(firstName, lastName, email, from, to);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        if (ticketId != -1) {
            return ResponseEntity.ok("Ticket purchased successfully! Ticket ID: " + ticketId);
        } else {
            return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body("No seats available.");
        }
    }

    @GetMapping("/receipt/{ticketId}")
    public String getReceipt(@PathVariable int ticketId) {
        return ticketService.getReceipt(ticketId);
    }

    @GetMapping("/view/{from}/{to}")
    public List<String> viewUsersAndSeats(@PathVariable String from, @PathVariable String to) {
        return ticketService.viewUsersAndSeats(from, to);
    }

    @DeleteMapping("/remove/{ticketId}")
    public ResponseEntity removeUser(@PathVariable int ticketId) {
        try {
            ticketService.removeUser(ticketId);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return ResponseEntity.ok("Passenger has been removed successfully");
    }

    @PutMapping("/modify/{ticketId}")
    public ResponseEntity modifySeat(@PathVariable int ticketId, @RequestParam String newSection, @RequestParam int seatNo) {
        boolean modified = false;
        try {
            modified = ticketService.modifySeat(ticketId, newSection, seatNo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
        return ResponseEntity.ok(modified ? "Seat modified successfully!" : "Modification failed. No seats available in Section " + newSection);
    }
}
