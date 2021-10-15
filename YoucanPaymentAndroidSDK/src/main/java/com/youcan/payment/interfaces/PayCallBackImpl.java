package com.youcan.payment.interfaces;

import com.youcan.payment.models.YCPayResult;

public interface PayCallBackImpl {
    void onPaySuccess(YCPayResult result);

    void onPayFailure(String message);

    void on3DsResult(YCPayResult result);
}