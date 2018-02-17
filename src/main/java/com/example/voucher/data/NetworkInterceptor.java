package com.example.voucher.data;

import android.util.Base64;

import com.example.voucher.model.Constants;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.Okio;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class NetworkInterceptor {
    final String APPId = "";
    final String SecretKey = "";

    public NetworkInterceptor(){
    }

    public OkHttpClient Intercept(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                String output = original.body().toString();


                String nonce = UUID.randomUUID().toString().replaceAll("-", "");
                String requestTimeStamp = getUTC();
                String contentBase64 = MD5Hash(output.getBytes());
                String requestSignatureBase64String = requestSignature(APPId, SecretKey, original.method(), original.url().toString(), requestTimeStamp, nonce, contentBase64);
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "epdx " + String.format("{0}:{1}:{2}:{3}", APPId, requestSignatureBase64String, nonce, requestTimeStamp)); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        }).addNetworkInterceptor(logging)
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS);

        return httpClient.build();
    }

    private String requestSignature(String appId, String APIKey, String requestHttpMethod, String requestUri, String requestTimeStamp, String nonce, String requestContentBase64String)
    {
        try {
            String signatureRawData = String.format("{0}{1}{2}{3}{4}{5}", appId, requestHttpMethod, requestUri, requestTimeStamp, nonce, requestContentBase64String);

            byte[] secretKeyByteArray = Base64.decode(APIKey, Base64.DEFAULT);

            byte[] signature = signatureRawData.getBytes("UTF-8");

            final Mac sha256_HMAC;
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(secretKeyByteArray, "HmacSHA256");
            sha256_HMAC.init(secret_key);
            final byte[] mac_data = sha256_HMAC.doFinal(signature);
            return Base64.encodeToString(mac_data, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        //convert mac_data to base64String
        return "";
    }

    private String MD5Hash(byte[] input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash =  md.digest(input);
            return Base64.encodeToString(hash, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getUTC(){
        Long epochStart = new Date().UTC(1970, 01, 01, 0, 0, 0);
        Date dt = new Date();
        Long timeSpan = new Date().UTC(dt.getYear(), dt.getMonth(),
                dt.getDate(), dt.getHours(), dt.getMinutes(), dt.getSeconds()) - epochStart;
        return timeSpan.toString();
    }
}
