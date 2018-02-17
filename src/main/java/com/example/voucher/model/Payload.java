package com.example.voucher.model;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class Payload {
    private String lastname;
    private String firstname;
    private String phone;
    private double amount;
    private String email;
    private Channel channel;

    public Payload(String email, String phone, double amount, String firstname, String lastname, Channel channel){
        this.amount = amount;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.channel = channel;
    }

    public Payload(String email, String phone){
        this.email = email;
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
