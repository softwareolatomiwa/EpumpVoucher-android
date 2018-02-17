package com.example.voucher.voucher;

import android.content.Context;

import com.example.voucher.data.Callbacks;
import com.example.voucher.data.NetworkReqImpl;
import com.example.voucher.model.Payload;
import com.example.voucher.model.VoucherResponse;
import com.example.voucher.voucher.VoucherContract.UserActionsListener;
import com.example.voucher.voucher.VoucherContract.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class VoucherPresenter implements UserActionsListener {
    private Context context;
    private View mView;
    private List<VoucherResponse> vouchers;

    public VoucherPresenter(Context context) {
        this.context = context;
        //this.mView = mView;
    }
    @Override
    public void postVoucher(final Payload payload) {
        VoucherRequestBody body = new VoucherRequestBody();
        body.Amount = payload.getAmount();
        body.Email = payload.getEmail();
        body.PhoneNumber = payload.getPhone();
        body.Channel = payload.getChannel();
        body.FirstName = payload.getFirstname();
        body.LastName = payload.getLastname();
        //this.mView.showProgressIndicator(true);
        (new NetworkReqImpl()).postVoucher(body, new Callbacks.OnVoucherPostComplete() {
            @Override
            public void onSuccess(String resp, String var2) {
                if (resp != null){
                    //hide progressIndicator
                }
                else{
                    //show error
                }
            }

            public void onError(String message, String responseAsJSONString) {
                /*this.mView.showProgressIndicator(false);
                this.mView.onPaymentError(message);*/
            }
        });
    }

    @Override
    public void listVouchers(Payload payload) {
        VoucherRequestBody body = new VoucherRequestBody();
        body.Email = payload.getEmail();
        body.PhoneNumber = payload.getPhone();
        //this.mView.showProgressIndicator(true);
        (new NetworkReqImpl()).listVouchers(body, new Callbacks.OnListVoucherRequestComplete() {
            @Override
            public void onSuccess(List<VoucherResponse> resp, String var2) {
                if (resp != null){
                    vouchers = resp;
                }else{
                    vouchers = new ArrayList<VoucherResponse>();
                    //show error
                }
            }

            public void onError(String message, String responseAsJSONString) {
                /*this.mView.showProgressIndicator(false);
                this.mView.onPaymentError(message);*/
            }
        });
    }

    public List<VoucherResponse> getVouchers(){
        if (vouchers == null){
            vouchers = new ArrayList<VoucherResponse>();
        }
        return vouchers;
    }
}
