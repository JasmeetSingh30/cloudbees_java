package com.example.trainticket.model;

public class Seat {
    private int seatNo;
    private User user;

    Seat(int seatNo){
        this.seatNo = seatNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeUser(){
        this.user = null;
    }
}
