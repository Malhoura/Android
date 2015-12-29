package com.example.jusoor;

//this class will allow us to inform the activity which performs a server request when a server request is completed
interface GetUserCallback {
	//an interface can only hold abstract methods
	public abstract void done(User returnedUser);
}
