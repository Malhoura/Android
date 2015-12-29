package com.example.jusoor;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ServerRequests {
	
	//show a loading bar when server request is being excuted 
	ProgressDialog progressDialog;
	//time in milliseconds for connection to persist before it stops 
	public static final int CONNECTION_TIMEOUT = 1000 * 15;
	//server address to access the php files
	public static final String SERVER_ADDRESS = "http://mazen.net46.net/";
	
	//constructor
	public ServerRequests(Context context){
		//Instantiate the progressDialog
		progressDialog = new ProgressDialog(context);
		//so a user can't cancel a progress dialog while it's loading
		progressDialog.setCancelable(false);
		progressDialog.setTitle("processing");
		progressDialog.setMessage("please wait...");
	
	}
	
	//method to store users data in the server
	public void storeUserDataInBackground(User user, GetUserCallback userCallback){
		progressDialog.show();
		new StoreUserDataAsyncTask(user, userCallback).execute();
	}
	
	//method to access users data in the server 
	public void fetchUserDataInBackground(User user, GetUserCallback callBack){
		progressDialog.show();
		new fetchUserDataAsyncTask(user, callBack).execute();
	}

	//background task in android is called AsyncTask
	//1-first void means we're not sending anything to this task when it's being executed,
	//we send it to the constructor instead of the AsyncTask 
	//2-second void is how we want to receive the progress from the AsyncTask
	//3-third void is what we want from the AsyncTask to return
	public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{

		User user;
		GetUserCallback userCallback;
		
		//constructor
		public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {

			this.user = user;
			this.userCallback = userCallback;
		}
		
		 /**
         * Before starting background thread Show Progress Dialog
         * */
        
		@Override
		protected Void doInBackground(Void... params) {
			
			//accessing the server
			//a NameValuePair holds a key and hold the data which will be stored in the key
			ArrayList<NameValuePair> dataToSend = new ArrayList<>();
			//adding values to the array
			dataToSend.add(new BasicNameValuePair("username", user.username));
			dataToSend.add(new BasicNameValuePair("emailaddress", user.emailaddress));
			dataToSend.add(new BasicNameValuePair("password", user.password));
			
			//setting attributes to the HTTP post
			
			//this allows us to change the attributes of the HTTP request
			HttpParams httpRequestParams =  new BasicHttpParams();
			//change connection timeout, set the timeout before the post is executed 
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			//time we want to wait to receive data from the server
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

			//set up the client
			//client allows us to make request to the server
			//we pass "httpRequestParams" so it knows the connection timeout
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");
			
			try{
				//encode the dataToSend, we can't just pass the dataToSend without encoding it
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				client.execute(post);
			}catch(Exception e){
				//display an error if something goes wrong
				e.printStackTrace();
			}
			
			return null;
		}
		
		//when the AsyncTask is finished
		@Override
		protected void onPostExecute(Void aVoid){
			progressDialog.dismiss();
			userCallback.done(null);
			super.onPostExecute(aVoid);
			
			
			
		}
		
	}
	
	//fetch needs to return a User
	public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User>{

		User user;
		GetUserCallback userCallback;
		
		public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) {

			this.user = user;
			this.userCallback = userCallback;
		}

		@Override
		protected User doInBackground(Void... params) {
			
			//accessing the server
			ArrayList<NameValuePair> dataToSend = new ArrayList<>();
			//we only need username and password to fetch users
			dataToSend.add(new BasicNameValuePair("username", user.username));
			dataToSend.add(new BasicNameValuePair("password", user.password));
			
			//setting attributes to the HTTP post
			
			//this allows us to chaange the attributes of the HTTP request
			HttpParams httpRequestParams =  new BasicHttpParams();
			//change connection timeout, set the timeout before the post is executed 
			HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			//time we want to wait to receive data from the server
			HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
			
			//set up the client
			//client allows us to make request to the server
			//we pass "httpRequestParams" so it knows the connection timeout
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			//hold the data we're going to post to the server
			HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");
			
			User returnedUser = null;
			try{
				//encode the dataToSend, we can't just pass the dataToSend without encoding it
				post.setEntity(new UrlEncodedFormEntity(dataToSend));
				HttpResponse httpResponse = client.execute(post);
				
				//getting the data from the response 
				HttpEntity entity = httpResponse.getEntity();
				//converting the data to a string
				String result = EntityUtils.toString(entity);
				//JSONObject to allow us to read different attributes in the JSON file, decoding
				JSONObject jObject = new JSONObject(result);
				
				if(jObject.length() == 0){
					returnedUser = null;
				}else{
					String username = jObject.getString("username");
					String emailaddress = jObject.getString("emailaddress");
					
					returnedUser = new User(user.username, emailaddress, user.password);
				}
				
			}catch(Exception e){
				//display an error if something goes wrong
				e.printStackTrace();
			}
			
			return returnedUser;
		}

		//when the AsyncTask is finished
		@Override
		protected void onPostExecute(User returnedUser){
			progressDialog.dismiss();
			userCallback.done(returnedUser);
			super.onPostExecute(returnedUser);
			
			
		}
	}	
	
}
