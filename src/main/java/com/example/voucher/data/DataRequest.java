package com.example.voucher.data;

import com.example.voucher.voucher.VoucherRequestBody;
import com.example.voucher.data.Callbacks.OnListVoucherRequestComplete;
import com.example.voucher.data.Callbacks.OnVoucherPostComplete;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public interface DataRequest {
    public interface NetworkRequest {
        void postVoucher(VoucherRequestBody var1, OnVoucherPostComplete var2);

        void listVouchers(VoucherRequestBody var1, OnListVoucherRequestComplete var2);
    }
}
