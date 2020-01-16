package com.jishengsoft.postal;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration 
public class CaptchaConfig {
	@Bean
	public DefaultKaptcha captchaProducer(){
		//com.google.code.kaptcha.Producer
		DefaultKaptcha captchaProducer =new DefaultKaptcha();
		Properties properties =new Properties();
		properties.setProperty("kaptcha.border","yes");
		properties.setProperty("kaptcha.border.color","105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color","blue");
		properties.setProperty("kaptcha.image.width","80");
		properties.setProperty("kaptcha.image.height","32");
		properties.setProperty("kaptcha.textproducer.font.size","32");
		properties.setProperty("kaptcha.session.key","code");
		properties.setProperty("kaptcha.textproducer.char.length","4");
		properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
		properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		Config config=new Config(properties);
		captchaProducer.setConfig(config);
		return  captchaProducer;
	}

}
