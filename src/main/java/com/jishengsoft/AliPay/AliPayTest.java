package com.jishengsoft.AliPay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

public class AliPayTest {
	private static String URL = "https://openapi.alipay.com/gateway.do";
	private static String APP_ID = "2017071207724021";
	private static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCKfUaOgjks8tgwgZ95suOrDYmgc6JK5z1pi+DcT1tgZDxWq2LS2LdsjjxuG8nALSd1PrOxGa3znyEncC0JacouXyXdCiLInlNiOR3vtk061Qz/lxZWDjnBwGel1xMkLt6VEsULNorFwCeBSq/qOCzvOyNEcO1KjhTYiYy5zYoWLWyt259BuAcZgKY2BIxy118+c4RdL5V/VGXwn9lSImQml3y3al63WOQagEyhjUMs0hCkzFMIbkqxD1Dn2Vke6LpojvfJ3ntXT34ECKKX8cPSlxiLm9FfAwGlAENZWulHZn1oqyU6LUKwMgmO/RQ+aDtOc12Z58DEm4sB78Q4dDzJAgMBAAECggEALIAy4uofez0RkQyl9RIxrC5ucfuWQ+FoT3keKUzqvN+Yr/TkjYIL9rsB2hPd0X3w9vDOBvOMfQM+QRD/t82hpSL35L40DSkq/B4Ea5m12VjKk3q2N7LchlDQPW8N/It/BIIChSbiksa7KwWX33hrn/ftzjlHVrSvKCGS8cWFQLie4SvzGkVB2gf5E1e5Pg6TdQROQUe4LRTwsib3lyrf7nXjgr5+m5lo8uYL/SACerBe8QbDFgl2yzKqBuz8L7MHm1uOhNw9Pzkq+xvBcYHrFVBpxeTFKb5AGX5Wt1tomUuYUXz+rBjFAc/6wei9vpiaN4JotlIHoMY0w60qQcZijQKBgQDisWYhA7vc5RdaHDPH3ArFTESnYYSQgiMeWx58uSnpIoa8WqybLLmL3NMRNsw1p1xyg9BgXtNAJEQixWyfCqYhBfUOX2JErIFG3+JTDegrm1lisiiU6W/MYadmx+dBGihTNsyiln0P8sG83NuPKEj85Cvx6/eQJDZJOWsNa2wKQwKBgQCcZLOVlGApShzL1ezE2/JUDVfZ3SsLWWWULgwFFSEw27VovnQnLG9egoSbZrfeEROeL07UDisPKUz+rmFb/jfWS7iVbEA1aNP/05rlMUefAiLePbuzBptqXXEAYmfBpdfl/BLaffY6wY9QyzpJWJzn7wfUBTxmCBpnaJTh/jiKAwKBgH33d7nGumB/yJ8g5sEbC70gqVGdRa2LMvqORX2m4pwSvG3zsNA1ZRFL7tk06vqxehSEHIlAiACvZlEwBTDHGRNmnQqUhCWG+fspMzlVk/qPQ3ctapF8ucwHQ59AW8d4Qooi/EkoeGwhR/irfTGqgAxOugCfT5yAEs5ToqH9XDbDAoGBAJNxNQW+PvJuVrutUW0+1zV65uoU4zHt9CBBUT+xbfs8B8laVPNhpxV5Az/nFU2c19v4Sd8OBHZkv5wUFuG9yDKWbM0io143GDdF7fBKB3XXc7wGg2ECx579vT1ZiOw+SE8g2T14J525Qj4veyIPEs5uwfiXfxvKY+o95K8zF2a1AoGBAKrVcxMRxTUDO5AwFYNamXd5qX8xqmYJAHLLlpJrtFfp8u3DvHf2TPlgDwli4+r3c1ppjUcxBk22hKcu6ZlAh/3ovUGVqUDfK4ioo5N+PZ0m347zdsrTlsZS6f5tLQlBro/RI2yjS3fZp1qXhV2IGrY6oxnDRLLehxUK8vo44ura";
	private static String FORMAT = "json";
	private static String CHARSET = "UTF-8";
	private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAin1GjoI5LPLYMIGfebLjqw2JoHOiSuc9aYvg3E9bYGQ8Vqti0ti3bI48bhvJwC0ndT6zsRmt858hJ3AtCWnKLl8l3QoiyJ5TYjkd77ZNOtUM/5cWVg45wcBnpdcTJC7elRLFCzaKxcAngUqv6jgs7zsjRHDtSo4U2ImMuc2KFi1srdufQbgHGYCmNgSMctdfPnOEXS+Vf1Rl8J/ZUiJkJpd8t2pet1jkGoBMoY1DLNIQpMxTCG5KsQ9Q59lZHui6aI73yd57V09+BAiil/HD0pcYi5vRXwMBpQBDWVrpR2Z9aKslOi1CsDIJjv0UPmg7TnNdmefAxJuLAe/EOHQ8yQIDAQAB";
	private static String SIGN_TYPE = "RSA2";
	public static void main(String[] args){
		AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY,FORMAT,
				CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
	    alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
	    alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
	    alipayRequest.setBizContent("{" +
	        "    \"out_trade_no\":\"20150320010101001\"," +
	        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	        "    \"total_amount\":0.01," +
	        "    \"subject\":\"Iphone6 16G\"," +
	        "    \"body\":\"Iphone6 16G\"," +
	        "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
	        "    \"extend_params\":{" +
	        "    \"sys_service_provider_id\":\"2088511833207846\"" +
	        "    }"+
	        "  }");//填充业务参数
	    String form="";
	    try {
	        form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
	    System.out.println(form);
	}
}
