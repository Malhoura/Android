package com.example.jusoor;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Tabs extends TabActivity {
	private TabHost mTabHost;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		
		super.onCreate(arg0);
		setContentView(R.layout.activity_tabs);
		
		mTabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		
		//Home tab
				intent = new Intent(this, Home.class);
				spec = mTabHost.newTabSpec("home")
				.setIndicator("Home")
				.setContent(intent);
				mTabHost.addTab(spec);
		
		//About tab
				intent = new Intent(this, About.class);
				spec = mTabHost.newTabSpec("about")
						.setIndicator("About")
						.setContent(intent);
				mTabHost.addTab(spec);
				
		//Contact tab
				intent = new Intent(this, Contact.class);
				spec = mTabHost.newTabSpec("contact")
						.setIndicator("Contact")
						.setContent(intent);
				mTabHost.addTab(spec);
				
				
	}	

	
}
