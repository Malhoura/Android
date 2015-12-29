package com.example.jusoor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Home extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		try{
		String[] items = {"Events", "News", "Photos", "Get Involved"};
		ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	
		ListView listView = (ListView) findViewById(R.id.homeListView);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new 
				AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						//Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
				                  //Toast.LENGTH_SHORT).show();
						
						String sText = ((TextView) view).getText().toString();
			             Intent intent = null;
			             
			             if(sText.equals("Events")){
			            	 intent = new Intent(getBaseContext(), Events.class);
			            	 startActivity(intent);
			             }else if(sText.equals("News")){
			            	 intent = new Intent(getBaseContext(), News.class);
			            	 startActivity(intent);
			             }else if(sText.equals("Photos")){
			            	 intent = new Intent(getBaseContext(), Photos.class);
			            	 startActivity(intent);
			             }else if(sText.equals("Get Involved")){
			            	 intent = new Intent(getBaseContext(), GetInvolved.class);
			            	 startActivity(intent);
			             }else{
			            	 
			             }
						
						
			             
					}
					
				});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
