package com.runcom.jiazhangbang.web;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class SSLSocketFactoryEx extends SSLSocketFactory
{

	SSLContext sslContext = SSLContext.getInstance("TLS");

	public SSLSocketFactoryEx(KeyStore truststore) throws NoSuchAlgorithmException , KeyManagementException , KeyStoreException , UnrecoverableKeyException
	{
		super(truststore);

		TrustManager tm = new X509TrustManager()
		{
			public java.security.cert.X509Certificate[] getAcceptedIssuers()
			{
				return null;
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain , String authType ) throws java.security.cert.CertificateException
			{
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain , String authType ) throws java.security.cert.CertificateException
			{
			}
		};
		sslContext.init(null ,new TrustManager []
		{ tm } ,null);
	}

	@Override
	public Socket createSocket(Socket socket , String host , int port , boolean autoClose ) throws IOException , UnknownHostException
	{
		return sslContext.getSocketFactory().createSocket(socket ,host ,port ,autoClose);
	}

	@Override
	public Socket createSocket() throws IOException
	{
		return sslContext.getSocketFactory().createSocket();
	}

	public static HttpClient getNewHttpClient()
	{
		try
		{
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null ,null);

			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params ,HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params ,HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http" , PlainSocketFactory.getSocketFactory() , 80));
			registry.register(new Scheme("https" , sf , 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params , registry);

			return new DefaultHttpClient(ccm , params);
		}
		catch(Exception e)
		{
			return new DefaultHttpClient();
		}
	}

}
