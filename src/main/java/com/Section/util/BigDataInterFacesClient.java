package com.Section.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Section.view.query_parameter_entity;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BigDataInterFacesClient {
	private static transient final Logger log = LoggerFactory.getLogger(BigDataInterFacesClient.class);
	
	private static final String IMGUR_CLIENT_ID = "...";
	
	public static String requestPost(String url, query_parameter_entity query_para_entity){
		String result="";
		long start = System.currentTimeMillis();
		long duration = 0L;
		
		Builder client = new OkHttpClient.Builder();

		client.hostnameVerifier(new MyHostnameVerifier());
		
		FileWriter fileWriter = null;
		File yjxhFile = null;
		try
		{
			yjxhFile = new File(PropUtil.getTargetProps().getProperty("YJXH_TO_FILE"));
			if (!yjxhFile.exists())
			{
				yjxhFile.createNewFile();
			}
			fileWriter = new FileWriter(yjxhFile, true);
			
			//final String FORM_ACTION = PropUtil.getTargetProps().getProperty("QUERY_ACTION");
			final File ctzp = new File(PropUtil.getTargetProps().getProperty("ctzp")); //F:\\a.png

			/*RequestBody requestBody = new MultipartBody.Builder()
					.setType(MultipartBody.FORM) //
					.addPart(Headers.of("Content-Disposition", "form-data; name=\"query_para\""), RequestBody.create(null, query_para_entity.toString())) //
					.addPart(Headers.of("Content-Disposition", "form-data; name=\"ctzp\";filename=\"" + ctzp.getName() + "\""),
							RequestBody.create(MediaType.parse(contentType(ctzp.getAbsolutePath())), ctzp))//
					.build();*/
			
			RequestBody requestBody = new MultipartBody.Builder()
					.setType(MultipartBody.FORM) //
					.addPart(Headers.of("Content-Disposition", "form-data; name=\"highway_detec_result\""), 
							RequestBody.create(MediaType.parse("application/json;"), query_para_entity.toString())) //
					.build();
			

			Request request = new Request.Builder()//
					.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)//
					.header("Connection", "Close")//
					.url(url).post(requestBody).build();

			Response response= new OkHttpClient().newCall(request).execute();
			
			result=response.body().string();
			
			if (!response.isSuccessful())
				log.error("Unexpected code " + response);
			
				fileWriter.write(query_para_entity.getRowkey() + "\n");
			

			duration = System.currentTimeMillis() - start;
			log.info("duration :" + duration + " ms");
			log.info("throughput : /" + duration + " post/ms");
			fileWriter.write("duration :" + duration + " ms" + "\n" + "throughput :/" + duration + " post/ms");

		} catch (IOException e)
		{
			log.error("", e);
			e.printStackTrace();
		} finally
		{
			if (fileWriter != null)
			{
				try
				{
					fileWriter.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String requestStatisPost(String url,String parms){
		String result="";
		long start = System.currentTimeMillis();
		long duration = 0L;
		
		Builder client = new OkHttpClient.Builder();

		client.hostnameVerifier(new MyHostnameVerifier());
		
		try
		{
			
			RequestBody requestBody = new MultipartBody.Builder()
					.setType(MultipartBody.FORM) //
					.addPart(Headers.of("Content-Disposition", "form-data; name=\"parms\""), 
							RequestBody.create(MediaType.parse("application/json;"), parms)) 
					/*.addPart(Headers.of("Content-Disposition", "form-data; name=\"region\""), 
							RequestBody.create(MediaType.parse("application/json;"), region)) */
					.build();
			

			Request request = new Request.Builder()//
					.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)//
					.header("Connection", "Close")//
					.url(url).post(requestBody).build();

			Response response= new OkHttpClient().newCall(request).execute();
			
			result=response.body().string();
			
			if (!response.isSuccessful())
				log.error("Unexpected code " + response);
			
			duration = System.currentTimeMillis() - start;
			log.info("duration :" + duration + " ms");
			log.info("throughput : /" + duration + " post/ms");

		} catch (IOException e)
		{
			log.error("", e);
			e.printStackTrace();
		} finally
		{
		}
		return result;
	}
	
	static class MyHostnameVerifier implements HostnameVerifier
	{

		@Override
		public boolean verify(String hostname, SSLSession session)
		{
			return true;
		}

	}
	
	static String contentType(String path)
	{
		if (path.endsWith(".png"))
			return "image/png";
		if (path.endsWith(".jpg"))
			return "image/jpeg";
		if (path.endsWith(".jpeg"))
			return "image/jpeg";
		if (path.endsWith(".gif"))
			return "image/gif";
		if (path.endsWith(".html"))
			return "text/html; charset=utf-8";
		if (path.endsWith(".txt"))
			return "text/plain; charset=utf-8";
		return "application/octet-stream";
	}

	
}
