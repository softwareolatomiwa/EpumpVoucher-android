package com.example.voucher.voucher;

import com.example.voucher.model.Channel;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class VoucherRequestBody {
    double Amount;
    String Email;
    String PhoneNumber;
    Channel Channel;
    String LastName;
    String FirstName;

    public VoucherRequestBody(){
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Channel getChannel() {
        return Channel;
    }

    public void setChannel(Channel channel) {
        Channel = channel;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
}
