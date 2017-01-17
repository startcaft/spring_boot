package com.startcaft.mvc.config.messageConverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.startcaft.mvc.config.entity.DemoObj;

/**
 * org.springframework.http.converter.HttpMessageConverter<T> 是用来处理 request 和 response里的数据的。
 * Spring 内置了大量的 HttpMessageConverter，例如MappingJackson2HttpMessageConveter，StringHttpMessageConverter等
 * @author startcaft
 *
 */
public class SpringMvcMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
	
	public SpringMvcMessageConverter() {
		//创建一个指定的媒体类型以及编码格式
		super(new MediaType("appliucation","x-startcaft",Charset.forName("UTF-8")));
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		//表明本 HttpMessageConveter 只处理 DemoObj 类型的数据
		return DemoObj.class.isAssignableFrom(clazz);
	}

	@Override
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		//处理客户端请求过来的数据，并且是由"-"隔开的数据，并转换成DemoObj的对象
		String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");
		return new DemoObj(new Long(tempArr[0]),tempArr[1]);
	}

	@Override
	protected void writeInternal(DemoObj t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		//处理如何输出数据到 reponse 中
		String out = "hello:" + t.getId() + "-" + t.getName();
		outputMessage.getBody().write(out.getBytes());
	}

}
