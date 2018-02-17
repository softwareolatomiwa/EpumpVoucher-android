package com.example.voucher;

import android.app.Activity;
import android.util.Log;

import com.example.voucher.model.Channel;
import com.example.voucher.model.Constants;
import com.example.voucher.model.Payload;
import com.example.voucher.model.PayloadBuilder;
import com.example.voucher.model.VoucherResponse;
import com.example.voucher.voucher.VoucherPresenter;

import java.util.List;


/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class VoucherManager {
    VoucherPresenter presenter;
    private Activity activity;
    private String p_Key;
    private String s_Key;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private double amount;
    private Channel channel;
    public VoucherManager(Activity activity){
        this.activity = activity;
    }

    public VoucherManager setKeys(String publicKey, String secretKey){
        this.p_Key = publicKey;
        this.s_Key = secretKey;
        return this;
    }

    public VoucherManager setFirstName(String firstName){
        this.fName = firstName;
        return this;
    }

    public VoucherManager setLastName(String lastName){
        this.lName = lastName;
        return this;
    }

    public VoucherManager setEmail(String email){
        this.email = email;
        return this;
    }

    public VoucherManager setPhoneNumber(String phone){
        this.phone = phone;
        return this;
    }

    public VoucherManager setAmount(double amount){
        this.amount = amount;
        return this;
    }

    public VoucherManager setChannel(Channel channel){
        this.channel = channel;
        return this;
    }

    public void initialize(){
        if(this.activity != null) {
            this.createRavePayInitializer();
            this.presenter = new VoucherPresenter(null);
            /*Intent intent = new Intent(this.activity, RavePayActivity.class);
            intent.putExtra(Constants.EPUMP_PARAMS, Parcels.wrap(this.createRavePayInitializer()));
            this.activity.startActivityForResult(intent, Constants.EPUMP_REQUEST_CODE);*/
        } else {
            Log.d(Constants.EPUMP, "Context is required!");
        }
    }

    public void postVoucher(){
        PayloadBuilder builder = new PayloadBuilder();
        builder.setAmount(amount).setEmail(email).setPhone(phone)
                .setFirstName(fName).setLastName(lName).setChannel(channel);
        Payload reqPayload = builder.createPayload();
        this.presenter.postVoucher(reqPayload);
    }

    public List<VoucherResponse> listVouchers(){
        PayloadBuilder builder = new PayloadBuilder();
        builder.setAmount(amount).setEmail(email).setPhone(phone)
                .setFirstName(fName).setLastName(lName).setChannel(channel);
        Payload reqPayload = builder.createPayload();
        this.presenter.listVouchers(reqPayload);

        return this.presenter.getVouchers();
    }

    public EPumpInitializer createRavePayInitializer() {
        return new EPumpInitializer(this.p_Key, this.s_Key, this.fName, this.lName, this.email, this.phone, this.amount);
    }
}
