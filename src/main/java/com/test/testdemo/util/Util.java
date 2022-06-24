package com.test.testdemo.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

//用coinmarket提供的ApiKey來抓API
public class Util {
	//APIKEY
	private String apiKey = "f138b789-56c0-4277-a305-186b7266c54d";
	
	//API字串整理
	public String apiString() {
		String api = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
		//截斷留下obj部分
		api = api.substring(api.indexOf("["),api.lastIndexOf("]")+1);
		return api;
	}
	
	//ApiURL轉字串
	private String getApi() {
		String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
		List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
		paratmers.add(new BasicNameValuePair("start", "1"));
		paratmers.add(new BasicNameValuePair("limit", "5000"));
		paratmers.add(new BasicNameValuePair("convert", "USD"));

		try {
			String result = makeAPICall(uri, paratmers);	
			return result;
		} catch (IOException e) {
			System.out.println("Error: cannont access content - " + e.toString());
			return null;
		} catch (URISyntaxException e) {
			System.out.println("Error: Invalid URL " + e.toString());
			return null;
		}
	}

	private String makeAPICall(String uri, List<NameValuePair> parameters)
			throws URISyntaxException, IOException {
		String response_content = "";

		URIBuilder query = new URIBuilder(uri);
		query.addParameters(parameters);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(query.build());

		request.setHeader(HttpHeaders.ACCEPT, "application/json");
		request.addHeader("X-CMC_PRO_API_KEY", apiKey);

		CloseableHttpResponse response = client.execute(request);

		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			response_content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}

		return response_content;
	}

}
