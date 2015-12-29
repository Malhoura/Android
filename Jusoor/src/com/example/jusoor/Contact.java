package com.example.jusoor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Contact extends Activity implements OnClickListener {

	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		imageView = (ImageView) findViewById(R.id.imageView2);
		imageView.setOnClickListener(this);
		
		
	}
	
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.imageView2:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://jusoorsyria.com/"));
			startActivity(browserIntent);
		}
		
	}
	

}
