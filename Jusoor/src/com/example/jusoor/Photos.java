package com.example.jusoor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class Photos extends FragmentActivity{

	private Integer[] pics = {R.drawable.j1, R.drawable.j2, R.drawable.j3, R.drawable.j4,
		R.drawable.j5, R.drawable.j6, R.drawable.j7, R.drawable.j8};
	
	public final static int PAGES = 5;
	// You can choose a bigger number for LOOPS, but you know, nobody will fling
	// more than 1000 times just in order to test your "infinite" ViewPager :D 
	public final static int LOOPS = 1000; 
	public final static int FIRST_PAGE = PAGES * LOOPS / 2;
	public final static float BIG_SCALE = 1.0f;
	public final static float SMALL_SCALE = 0.7f;
	public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
	
	public MyPagerAdapter adapter;
	public ViewPager pager;
	
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photos);
		
		pager = (ViewPager) findViewById(R.id.myviewpager);
		adapter = new MyPagerAdapter(this, this.getSupportFragmentManager());
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(adapter);
		
				// Set current item to the middle page so we can fling to both
				// directions left and right
				pager.setCurrentItem(FIRST_PAGE);
				
				// Necessary or the pager will only have one extra page to show
				// make this at least however many pages you can see
				pager.setOffscreenPageLimit(3);
				
				// Set margin for pages as a negative number, so a part of next and 
				// previous pages will be showed
				pager.setPageMargin(-200);
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		
		//create adapter gallery
		gallery.setAdapter(new ImageAdapter(this));
		imageView = (ImageView) findViewById(R.id.imageView1);
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			//imageView.setImageResource(pics[position]);
				
			}
		});
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}public class ImageAdapter extends BaseAdapter{
		private Context context;
		int imageBackground;
		
		public ImageAdapter(Context context){
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pics.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(pics[position]);
			return imageView;
		}
	}
	
	
}
