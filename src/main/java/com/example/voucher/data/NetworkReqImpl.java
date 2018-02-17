package com.example.voucher.data;

import android.support.annotation.NonNull;

import com.example.voucher.data.DataRequest.NetworkRequest;
import com.example.voucher.model.Constants;
import com.example.voucher.model.VoucherResponse;
import com.example.voucher.voucher.VoucherRequestBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class NetworkReqImpl implements NetworkRequest {
    private Retrofit retrofit;
    private ApiService service;
    private String errorParsingError = "An error occurred parsing the error response";

    public NetworkReqImpl() {
    }

    private Error parseErrorJson(String errorStr) {
        try {
            Gson e = new Gson();
            Type type = (new TypeToken() {
            }).getType();
            return (Error)e.fromJson(errorStr, type);
        } catch (Exception var4) {
            var4.printStackTrace();
            return new Error("error", this.errorParsingError);
        }
    }

    public void postVoucher(VoucherRequestBody body, final Callbacks.OnVoucherPostComplete callback) {
        this.createService();
        Call call = this.service.postVoucher(body);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    callback.onSuccess("Successfully Posted", (String)response.body());
                } else {
                    try {
                        String e1 = response.errorBody().string();
                        Error error1 = NetworkReqImpl.this.parseErrorJson(e1);
                        callback.onError(error1.getMessage(), e1);
                    } catch (NullPointerException | IOException var6) {
                        var6.printStackTrace();
                        callback.onError("error", NetworkReqImpl.this.errorParsingError);
                    }
                }

            }

            public void onFailure(Call call, Throwable t) {
                callback.onError(t.getMessage(), "");
            }
        });
    }

    public void listVouchers(VoucherRequestBody body, final Callbacks.OnListVoucherRequestComplete callback) {
        this.createService();
        Call call = this.service.listVouchers(body);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()) {
                    Gson e = new Gson();
                    Type error = (new TypeToken() {
                    }).getType();
                    List<VoucherResponse> chargeResponse = (List<VoucherResponse>)e.fromJson((String)response.body(), error);
                    callback.onSuccess(chargeResponse, (String)response.body());
                } else {
                    try {
                        String e1 = response.errorBody().string();
                        Error error1 = NetworkReqImpl.this.parseErrorJson(e1);
                        callback.onError(error1.getMessage(), e1);
                    } catch (NullPointerException | IOException var6) {
                        var6.printStackTrace();
                        callback.onError("error", NetworkReqImpl.this.errorParsingError);
                    }
                }

            }

            public void onFailure(Call call, Throwable t) {
                callback.onError(t.getMessage(), "");
            }
        });
    }
    private void createService() {
        Builder httpClient = new Builder();
        NetworkInterceptor networkInterceptor = new NetworkInterceptor();
        OkHttpClient okHttpClient = networkInterceptor.Intercept();
        if(this.retrofit == null) {
            this.retrofit = (new retrofit2.Retrofit.Builder()).baseUrl(Constants.LIVE_URL).client(okHttpClient).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        }

        this.service = (ApiService)this.retrofit.create(ApiService.class);
    }
}