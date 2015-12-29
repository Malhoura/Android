package com.example.jusoor;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	Button btLogout, btContinue;
	TextView tvUsername, tvLink;
	UserLocalStore userLocalStore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvUsername = (TextView) findViewById(R.id.tvUsername);
		tvLink = (TextView) findViewById(R.id.tvLink);
		
		
		btLogout = (Button) findViewById(R.id.btLogout);
		btContinue = (Button) findViewById(R.id.btContinue);
		btLogout.setOnClickListener(this);
		btContinue.setOnClickListener(this);
		tvLink.setOnClickListener(this);
		 
		userLocalStore = new UserLocalStore(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if(authenticate() == true){
			//if the user is logged in show the user details
		diplayWelcome();
		}else{
			//if the user is not logged in open the Login activity
			startActivity(new Intent(MainActivity.this, Login.class));
		}
	}
	
	private boolean authenticate(){
		return userLocalStore.getUserLoggedIn();
	}
	
	//display user details
	public void diplayWelcome(){
		User user = userLocalStore.getloggedInUser();
		
		tvUsername.setText(user.username);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btLogout:
			userLocalStore.clearUserData();
			userLocalStore.setUserLoggedIn(false);
			startActivity(new Intent(this, Login.class));
			break;
			
		case R.id.tvLink:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://jusoorsyria.com/"));
			startActivity(browserIntent);
			break;
			
		case R.id.btContinue:
			startActivity(new Intent(this, Tabs.class));
			break;
			
		}
		
	}
}
