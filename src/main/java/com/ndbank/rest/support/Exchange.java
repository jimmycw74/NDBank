package com.ndbank.rest.support;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//FROM https://freecurrencyapi.net/

public class Exchange {

	public String getCurrency(String currency) {
		String returnValue = "";
		try {
			String apikey = "df3396a0-98b0-11ec-bae6-37524ac6880a";
			String url = "https://freecurrencyapi.net/api/v2/latest?apikey=" + apikey + "&base_currency=" + currency;
			URL urlForGetRequest = new URL(url);
			String readLine = null;
			HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			conection.setRequestMethod("GET");
			int responseCode = conection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
				StringBuffer response = new StringBuffer();
				while ((readLine = in.readLine()) != null) {
					response.append(readLine);
				}
				in.close();
				returnValue = response.toString();
			} else {
				throw new Exception("Error in API Call");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnValue;
	}

}
