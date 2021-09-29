package com.youcan.payment;


import android.util.Log;

import com.youcan.payment.instrafaces.PayCallBack;
import com.youcan.payment.instrafaces.YCPayTokenizerCallBack;
import com.youcan.payment.models.YCPayTokenizer;
import com.youcan.payment.models.Pay;
import com.youcan.payment.models.YCPayResult;
import com.youcan.payment.models.YCPayToken;
import com.youcan.payment.task.YCPayLoad3DSPageTask;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class YCPay {

    static public Pay pay = new Pay();
    static public PayCallBack payListener;
    static YCPayTokenizerCallBack listener = new YCPayTokenizerCallBack() {
        @Override
        public void onResponse(YCPayToken token) {
            if(token!=null) {
                pay.setToken(token);
                Log.e("YCPay", "onResponse: "+token.toString() );

            }
        }

        @Override
        public void onError(String response) {
            Log.e("YCPay", "onError: "+response );
        }
    };

    static public YCPayTokenizer tokenizer = new YCPayTokenizer(listener);

    static public void setTokenId(String tokenId){
        YCPayToken token = new YCPayToken();
        token.setId(tokenId);
        pay.setToken(token);
    }

    static public void load3DsPage(YCPayResult result) {
        RequestBody form = new FormBody.Builder()
                .add("PaReq", result.paReq)
                .add("MD", result.transactionId)
                .add("TermUrl", result.callBackUrl)
                .build();

        new YCPayLoad3DSPageTask(result.redirectUrl,result.listenUrl,form).execute("");
    }


}