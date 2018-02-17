package com.example.voucher;

import org.parceler.Parcel;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

@Parcel
public class EPumpInitializer {
    String email;
    double amount;
    String publicKey;
    String secretKey;
    String fName;
    String lName;
    String phone;

    public EPumpInitializer(String publicKey, String secretKey, String fName, String lName,
                            String email, String phone, double amount){
        this.amount = amount;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public EPumpInitializer(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
