package com.example.voucher.voucher;

import com.example.voucher.model.Payload;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public interface VoucherContract {
    public interface UserActionsListener{
        void postVoucher(Payload var1);

        void listVouchers(Payload var1);
    }

    public interface View{
        void showProgressIndicator(boolean var1);
    }
}
