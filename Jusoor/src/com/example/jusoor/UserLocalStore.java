package com.example.jusoor;

import android.R.bool;
import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {
	
	// define a file to save users info
	public static final String SP_NAME = "userDetails";
	//sharedPreference Interface allows to store data on the phone 
	SharedPreferences userLocalDatabaase;
	
	//create a constructor
	public UserLocalStore(Context context){
		userLocalDatabaase = context.getSharedPreferences(SP_NAME, 0);
	}
	
	public void storeUserData(User user){
		SharedPreferences.Editor spEditor = userLocalDatabaase.edit();
		
		spEditor.putString("username", user.username);
		spEditor.putString("emailaddress", user.emailaddress);
		spEditor.putString("password", user.password);
		spEditor.commit();
	}
	
	//create a method to return a user from type "User"
	public User getloggedInUser(){
		String emailaddress = userLocalDatabaase.getString("emailadress", "");
		String username = userLocalDatabaase.getString("username", "");
		String password = userLocalDatabaase.getString("password", "");
		
		User storedUser = new User(username, emailaddress, password);
		return storedUser;
	}
	
	//creating a method to check if user is logged in
	public void setUserLoggedIn(boolean loggedIn){
		SharedPreferences.Editor spEditor = userLocalDatabaase.edit();
		
		spEditor.putBoolean("loggedIn", loggedIn);
		spEditor.commit();
	}
	
	public boolean getUserLoggedIn(){
		if(userLocalDatabaase.getBoolean("loggedIn", false) == true){
			return true;
		}else{
			return false;
		}
	}
	
	//creating a method to clear user data when user logs out
	public void clearUserData(){
		SharedPreferences.Editor spEditor = userLocalDatabaase.edit();
		spEditor.clear();
		spEditor.commit();
		
	}
}
