package com.example.voucher.data;

import com.example.voucher.model.VoucherResponse;

import java.util.List;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class Callbacks {
    public Callbacks() {
    }

    public interface OnVoucherPostComplete {
        void onSuccess(String var1, String var2);

        void onError(String var1, String var2);
    }

    public interface OnListVoucherRequestComplete {
        void onSuccess(List<VoucherResponse> var1, String var2);

        void onError(String var1, String var2);
    }
}
