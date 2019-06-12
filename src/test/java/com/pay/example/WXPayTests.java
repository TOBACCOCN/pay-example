package com.pay.example;

import com.github.wxpay.sdk.WXPay;
import com.pay.example.util.QRCodeUtil;
import com.pay.example.wxpay.WXPayConfig;
import com.pay.example.wxpay.WXPayConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WXPayTests {

    private static Logger logger = LoggerFactory.getLogger(WXPayTests.class);

    @Autowired
    private WXPay wxPay;
    @Autowired
    private WXPayConfig wxPayConfig;

    // 统一下单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
    @Test
    public void unifiedOrder() throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put(WXPayConstant.body, "Ground Coffee 40 oz * 1");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        reqData.put(WXPayConstant.outTradeNo, uuid);
        logger.info(">>>>> OUT_TRADE_NO: {}", uuid);
        reqData.put(WXPayConstant.totalFee, "1");
        reqData.put(WXPayConstant.spbillCreateIp, "127.0.0.1");
        reqData.put(WXPayConstant.notifyUrl, wxPayConfig.getNotifyUrl());
        reqData.put(WXPayConstant.tradeType, WXPayConstant.tradeTypeNative);
        Map<String, String> map = wxPay.unifiedOrder(reqData);
        logger.info(">>>>> RESPONSE: {}", map);

        QRCodeUtil.encode(map.get(WXPayConstant.codeUrl), 258, 258,
                "png", "D:\\" + uuid + ".png");
    }

    // 查询订单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_2
    @Test
    public void orderQuery() throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put(WXPayConstant.outTradeNo, "21a4b60d6e534b3ea865b554b3297f6a");
        Map<String, String> map = wxPay.orderQuery(reqData);
        logger.info(">>>>> RESPONSE: {}", map);
    }

    // 关闭订单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_3
    @Test
    public void closeOrder() throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put(WXPayConstant.outTradeNo, "23ec2a06d5b54da2a7f84f9aa4aab37a");
        Map<String, String> map = wxPay.closeOrder(reqData);
        logger.info(">>>>> RESPONSE: {}", map);
    }

    // 申请退款 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
    @Test
    public void refund() throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put(WXPayConstant.outTradeNo, "938a22e734f643ce995d1e6db91c63ce");
        reqData.put(WXPayConstant.outRefundNo, "938a22e734f643ce995d1e6db91c63ce");
        reqData.put(WXPayConstant.totalFee, "1");
        reqData.put(WXPayConstant.refundFee, "1");
        Map<String, String> map = wxPay.refund(reqData);
        logger.info(">>>>> RESPONSE: {}", map);
    }

    // 查询退款 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_5
    @Test
    public void refundQuery() throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put(WXPayConstant.outTradeNo, "21a4b60d6e534b3ea865b554b3297f6a");
        Map<String, String> map = wxPay.refundQuery(reqData);
        logger.info(">>>>> RESPONSE: {}", map);
    }

}
