package com.pay.example.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AlipayTrade {

	@Autowired
	private AlipayConfig alipayConfig;
	@Autowired
	private AlipayClient alipayClient;

	/**
	 * 
	 * tradePrecreate 交易预创建
	 * https://docs.open.alipay.com/api_1/alipay.trade.precreate/
	 * 
	 */
	public AlipayTradePrecreateResponse tradePrecreate(AlipayTradePrecreateModel model) throws AlipayApiException {
		// 创建 API 对应的 request 类
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		// 设置业务参数
		request.setBizModel(model);
		request.setNotifyUrl(alipayConfig.notifyUrl);
		// 通过 alipayClient 调用 API，获得对应的 response 类
		return alipayClient.execute(request);
	}

	/**
	 * 
	 * rsaCheck 验签 https://docs.open.alipay.com/194/105322/
	 * 
	 */
	public boolean rsaCheck(Map<String, String> map) throws AlipayApiException {
		// //调用 SDK 验证签名
		return AlipaySignature.rsaCheckV1(map, alipayConfig.alipayPublicKey, alipayConfig.charset,
				alipayConfig.signType);
	}

	/**
	 * 
	 * tradeQuery 交易查询 https://docs.open.alipay.com/api_1/alipay.trade.query/
	 * 
	 */
	public AlipayTradeQueryResponse tradeQuery(AlipayTradeQueryModel model) throws AlipayApiException {
		// 创建 API 对应的 request 类
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		// 设置业务参数
		request.setBizModel(model);
		// 通过 alipayClient 调用 API，获得对应的 response 类
		return alipayClient.execute(request);
	}

	/**
	 * 
	 * tradeCancel 交易撤销 https://docs.open.alipay.com/api_1/alipay.trade.cancel/
	 * 
	 */
	public AlipayTradeCancelResponse tradeCancel(AlipayTradeCancelModel model) throws AlipayApiException {
		// 创建 API 对应的 request 类
		AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
		// 设置业务参数
		request.setBizModel(model);
		// 通过 alipayClient 调用 API，获得对应的 response 类
		return alipayClient.execute(request);
	}

	/**
	 * 
	 * tradeRefund 交易退款 https://docs.open.alipay.com/api_1/alipay.trade.refund/
	 * 
	 */
	public AlipayTradeRefundResponse tradeRefund(AlipayTradeRefundModel model) throws AlipayApiException {
		// 创建 API 对应的 request 类
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		// 设置业务参数
		request.setBizModel(model);
		// 通过 alipayClient 调用 API，获得对应的 response 类
		return alipayClient.execute(request);
	}

	/**
	 * 
	 * tradeRefundQuery 交易退款查询
	 * https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query/
	 * 
	 */
	public AlipayTradeFastpayRefundQueryResponse tradeRefundQuery(AlipayTradeFastpayRefundQueryModel model)
			throws AlipayApiException {
		// 创建 API 对应的 request 类
		AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
		// 设置业务参数
		request.setBizModel(model);
		// 通过 alipayClient 调用 API，获得对应的 response 类
		return alipayClient.execute(request);
	}

}