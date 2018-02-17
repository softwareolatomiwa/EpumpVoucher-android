package com.example.voucher.model;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class PayloadBuilder {
    private String lastname;
    private String firstname;
    private double amount;
    private String email;
    private String phone;
    private Channel channel;

    public PayloadBuilder() {
    }

    public PayloadBuilder setLastName(String lastname){
        this.lastname = lastname;
        return this;
    }

    public PayloadBuilder setFirstName(String firstname){
        this.firstname = firstname;
        return this;
    }

    public PayloadBuilder setAmount(double amount){
        this.amount = amount;
        return this;
    }

    public PayloadBuilder setEmail(String email){
        this.email = email;
        return this;
    }

    public PayloadBuilder setPhone(String phone){
        this.phone = phone;
        return this;
    }

    public PayloadBuilder setChannel(Channel channel){
        this.channel = channel;
        return this;
    }

    public Payload createPayload() {
        return new Payload(this.email, this.phone, this.amount, this.firstname, this.lastname, this.channel);
    }

    public Payload createListPayload() {
        return new Payload(this.email, this.phone);
    }
}
