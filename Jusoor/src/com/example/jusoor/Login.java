package com.example.jusoor;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends ActionBarActivity implements View.OnClickListener{

	Button login;
	EditText etUsername, etPassword;
	TextView tvRegisterLink;
	UserLocalStore userLocalStore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etUsername = (EditText) findViewById(R.id.username);
		etPassword = (EditText) findViewById(R.id.password);
		tvRegisterLink = (TextView) findViewById(R.id.registerLink);
		login = (Button) findViewById(R.id.login);
		
		
		login.setOnClickListener(this);
		tvRegisterLink.setOnClickListener(this);
		
		userLocalStore = new UserLocalStore(this);
		
	}
	
	

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.login:
			
			String username = etUsername.getText().toString();
			String password = etPassword.getText().toString();
			
			User user = new User(username, password);
			
			authenticate(user);
			//store info about the user
			userLocalStore.storeUserData(user);
			userLocalStore.setUserLoggedIn(true);
			break;
		
		case R.id.registerLink:
			startActivity(new Intent(this, Register.class));
			break;

		
		}
	}
	
	private void authenticate(User user){
		//access server
		ServerRequests serverRequests = new ServerRequests(this);
		//fetch user data in background
		serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
			
			@Override
			public void done(User returnedUser) {
				if(returnedUser == null){
					showErrorMessage();
				}else{
					logUserIn(returnedUser);
				}
			}
		});
	}
	private void showErrorMessage(){
		//show a dialog of an error message
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
		dialogBuilder.setMessage("Incorrect user details");
		dialogBuilder.setPositiveButton("Ok", null);
		dialogBuilder.show();
	}
	
	private void logUserIn(User returnedUser){
		//store info about the user
		userLocalStore.storeUserData(returnedUser);
		userLocalStore.setUserLoggedIn(true);
		
		startActivity(new Intent(this, MainActivity.class));
	}
}
