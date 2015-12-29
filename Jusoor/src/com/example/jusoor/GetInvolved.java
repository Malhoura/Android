package com.example.jusoor;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class GetInvolved extends Activity{

private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_involve);
		
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://jusoorsyria.com/#about");
	}

}
