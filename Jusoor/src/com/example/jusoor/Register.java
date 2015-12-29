package com.example.jusoor;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements View.OnClickListener {
	
	Button register;
	EditText etUsername, etPassword, etEmailAddress;
	UserLocalStore userLocalStore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		
		
		etUsername = (EditText) findViewById(R.id.username);
		etPassword = (EditText) findViewById(R.id.password);
		etEmailAddress = (EditText) findViewById(R.id.emailAddress);
		
		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(this);

}
	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
		case R.id.register:
			String username = etUsername.getText().toString();
			String password = etPassword.getText().toString();
			String emailAddress = etEmailAddress.getText().toString();
			//String usernameCounter;
			
			
			User user = new User(username, emailAddress, password);
			
			
			
			 //intent = new Intent(Intent.ACTION_SEND);
			 //intent.setData(Uri.parse("mailto:"));
			 //String[] to = {emailAddress, "mazen.alhourani@gmail.com"};
			 //intent.putExtra(Intent.EXTRA_EMAIL, to);
			 //intent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation");
			 //intent.putExtra(Intent.EXTRA_TEXT, "Your username: "+ username+"/n"+"Your password: " + password);
			 //intent.setType("message/rfc822");
			 
		     
			 //userLocalStore.storeUserData(user);
			//if(usernameCounter == username){
				
			//}else{
			//registerUser(user);
			 
			  
			Mail m = new Mail("username", "password"); 
			 
		      String[] toArr = {"malhoura@hawk.iit.edu"}; 
		      m.setTo(toArr); 
		      m.setFrom("mazen.alhourani@gmail.com"); 
		      m.setSubject("Jusoor Confirmation"); 
		      m.setBody("Your username: "+ username+"/n"+"Your password: " + password); 
		 
		      try { 
		        //m.addAttachment("/sdcard/filelocation"); 
		 
		        if(m.send()) { 
		          Toast.makeText(Register.this, "Confirmation Email was sent successfully.", Toast.LENGTH_LONG).show(); 
		        } else { 
		          Toast.makeText(Register.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
		        } 
		      } catch(Exception e) { 
		        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
		        Log.e("MailApp", "Could not send email", e); 
		      }  
		  }
		//}
}

			 //userLocalStore.getloggedInUser();
			
			//try {
            //    GMailSender sender = new GMailSender("mazen.alhourani@gmail.com", "syria4ever_@1992");
			//	sender.sendMail("Jusoor Confirmation",   
            //            "Your username: "+ username+"/n"+"Your password: " + password,   
            //            "mazen.alhourani@gmail.com",   
            //            emailAddress);
			//} catch (Exception e) {   
            //    Log.e("SendMail", e.getMessage(), e);   
            //} 
			//break;

	
	private void registerUser(User user){
		//start server request
		ServerRequests serverRequests = new ServerRequests(this);
		serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
			
			@Override
			public void done(User returnedUser) {
				startActivity(new Intent(Register.this, Login.class));
			}
		});
	}

}
