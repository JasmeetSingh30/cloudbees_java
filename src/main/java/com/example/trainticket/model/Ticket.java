package com.example.trainticket.model;

public class Ticket {

    private int id;

    private User user;
    private Train train;
    private double price;
    private String section;
    private int seatNumber;

    public Ticket(User user, Train train) {
        this.user = user;
        this.train = train;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket Receipt: \n" +
                "Ticket ID: "+ this.getId() + "\n"+
                "Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
                "Email: " + user.getEmail() + "\n" +
                "From: " + train.getFrom() + "\n" +
                "To: " + train.getTo() + "\n" +
                "Price: $" + price + "\n" +
                "Section: " + section + "\n" +
                "Seat Number: " + seatNumber;
    }
    
}
