package com.jishengsoft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.slf4j.*;

public class MyHttpClient {
	private String x_id = "13868980148";
	private String x_pwd = "x068144";
	private Logger log = LoggerFactory.getLogger(MyHttpClient.class);
	public String SendSms(String mobile, String content)
			throws UnsupportedEncodingException {
		Integer x_ac = 10;// 发送信息
		HttpURLConnection httpconn = null;
		String result = "Error";
		StringBuilder sb = new StringBuilder();
		sb.append("http://service.winic.org:8009/sys_port/gateway/index.asp?");

		// 以下是参数
		// 为了你的测试方便收到短信！请短信内容编辑为：你的验证码为：123456【中正云通信】
		sb.append("id=").append(URLEncoder.encode(x_id, "gb2312"));
		sb.append("&pwd=").append(x_pwd);
		sb.append("&to=").append(mobile);
		sb.append("&content=").append(URLEncoder.encode(content, "gb2312"));
		sb.append("&time=").append("");
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					httpconn.getInputStream()));
			result = rd.readLine();
			rd.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}

		}
		return result;
	}
	/**
     * 快递查询接口方法
     * 
     * @param key
     *            ：商家用户key值，在http://www.kuaidi100.com/openapi申请的
     * @param com
     *            ：快递公司代码，在http://www.kuaidi100.com/openapi网上的技术文档里可以查询到
     * @param nu
     *            ：快递单号，请勿带特殊符号，不支持中文（大小写不敏感）
     * @return 快递100返回的url，然后放入页面iframe标签的src即可
     * @see
     */
    public String searchkuaiDiInfo(String key, String com, String nu)
    {
        String content = "";
        try
        {
            URL url = new URL("http://www.kuaidi100.com/applyurl?key=" + key + "&com=" + com
                              + "&nu=" + nu);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1)
            {
                numRead = urlStream.read(b);
                if (numRead != -1)
                {
                    // String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, "UTF-8");
                    content += newContent;
                }
            }
            urlStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
           log.error("快递查询错误");
        }
        return content;
    }

}
