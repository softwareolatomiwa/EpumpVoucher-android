package com.example.voucher.data;

import com.example.voucher.model.VoucherResponse;
import com.example.voucher.voucher.VoucherRequestBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public interface ApiService {
    @POST("/api/voucher/list")
    Call<String> postVoucher(@Body VoucherRequestBody var1);

    @POST("/api/voucher/list")
    Call<List<VoucherResponse>> listVouchers(@Body VoucherRequestBody var1);
}
