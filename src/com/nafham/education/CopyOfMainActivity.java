package com.nafham.education;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

@SuppressLint("Recycle")
public class CopyOfMainActivity extends Activity implements OnQueryTextListener{
	 private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle; 
	    private JSONObject jObj;
	    JSONArray jsonArray;
	    Fragment fragment = null;
	    // nav drawer title
	    private CharSequence mDrawerTitle;
	 
	    // used to store app title
	    private CharSequence mTitle;
	    TextView tv;
	    String SemID="";
	    String Uid="";
	    String  Uname="";
	    String Thumb=""; 
	    String User_Grade="";
	    UserFunctions userFunction = new UserFunctions();
        String json="";
	    String UserToken="";
	    boolean Flag=false;
	    // slide menu items
	    private String[] navMenuTitles;
	    private TypedArray navMenuIcons;
	    ArrayList <String>Notifications=new ArrayList<String>();
	    ArrayList <String> Grades=new ArrayList<String>();
	    ArrayList  <String>Semsters=new ArrayList<String>();
	    ArrayList<String> Grades_semCount=new ArrayList<String>();
	    ArrayList<String> Grades_IDs=new ArrayList<String>();
	    private ArrayList<NavDrawerItem> navDrawerItems;
	    private NavDrawerListAdapter adapter;
		String Count_notifications="";
		private Stack<Fragment> mFragmentStack;
		private String json3;
		private int counter;
		String CountryID="1";
		 String user_type="";
		private boolean gg;
		private String Link="http://www.nafham.com/competition";
		private SearchView SV;
		private WebView TheBannerOnActionBar;
	//	private WebView TheBannerOnActionBar1;
		private String TargetWOrd;
		LinearLayout lN;
	EditText ET1;
	 Typeface typeface1;
	 Typeface typeface2;
	 String User_Grade_not_talp="";
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	         if (connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)){ 
	             //You are connected, do something online.
	        	// Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
	         }else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {             
	             //Not connected.        
	       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
	       finish();
	         } 
	        setContentView(R.layout.activity_main);
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	    	.permitAll().build();
	    	StrictMode.setThreadPolicy(policy);
	    	////////////////
	    	
	    	  typeface1 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	      	   typeface2 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	    	
	    	///////////
	    	  mFragmentStack = new Stack<Fragment>();
	       if(getIntent().getExtras() != null){
			if(getIntent().getExtras().getString("ThesemsterID") ==null){
	    	   SemID="1";}
	    	   else{SemID=getIntent().getExtras().getString("ThesemsterID");}
	    		if(!getIntent().getExtras().getString("TheUserID").equals(null)){
	    	 Uid=getIntent().getExtras().getString("TheUserID");
	    		}
	    		else{
	    		}
	    		if(!getIntent().getExtras().getString("TheUserName").equals(null)){
	    	 Uname=getIntent().getExtras().getString("TheUserName");
	    	
	    		}
	    		else{
	    		}
	    		if(!getIntent().getExtras().getString("TheUserThumb").equals(null)){
	    	 Thumb=getIntent().getExtras().getString("TheUserThumb");
	    	
	    		}
	    		else{	
	    		}
	    		if(!getIntent().getExtras().getString("TheUserGrade").equals(null)){
	    	 User_Grade=getIntent().getExtras().getString("TheUserGrade");
	    	
	    		}
	    		else{
	    			
	    		}
	    		if(!getIntent().getExtras().getString("TheUserToken").equals(null)){
	    	 UserToken=getIntent().getExtras().getString("TheUserToken");
	    	
	    		}
	    		else{}
	    		if(!getIntent().getExtras().getString("user_TYPE").equals(null)){
	   	    	 user_type=getIntent().getExtras().getString("user_TYPE");
	   	    	
	   	    		}
	   	    		else{}
	    		if(!getIntent().getExtras().getString("CountryID").equals(null)){
		   	    	 CountryID=getIntent().getExtras().getString("CountryID");
		   	    		}
		   	    		else{}
	    		if(!getIntent().getExtras().getString("Competition").equals("0") &&!getIntent().getExtras().getString("link").equals("")){
	    			try {
	    				
	    				gg=true;
	    				Link=getIntent().getExtras().getString("link");
	    				
						displayView(1);
						
						Uid="4";
						User_Grade="5";
						SemID="1";
						user_type="2";
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		   	    		}
	    	//	if(!getIntent().getExtras().getString("CountryID").equals(null)){
	    		//	CountryID=getIntent().getExtras().getString("CountryID");
		   	    	//	}
	    		else{}
	       }
	    	 
	    	 
	       else {
	    	   SemID="1"; 
	    	 
	       }
	     Semsters.add("");
	        Semsters.add("«·›’· «·œ—«”Ï «·«Ê·");
			   Semsters.add("«·›’· «·œ—«”Ï «·À«‰Ï");
			   mTitle = mDrawerTitle = getTitle();
	 
	         //load slide menu items
	      
	       mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	 mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
	ET1=(EditText) findViewById(R.id.EditText01);
	 ET1.setTypeface(typeface2);
	
 lN=(LinearLayout)findViewById(R.id.left_drawer);
	  navDrawerItems = new ArrayList<NavDrawerItem>();
	      new ProgressTask().execute();
	      
	      
	      //////////////////////////////////////////////////////////
      /////////////////////////////////////////////////////////////////
	       /////////////////////////////////////////hna aly gwa al run 
	 
	        // enabling action bar app icon and behaving it as toggle button
	       getActionBar().setDisplayHomeAsUpEnabled(false);
	     getActionBar().setDisplayShowHomeEnabled(false);
	       getActionBar().setHomeButtonEnabled(true);
	        getActionBar().setDisplayUseLogoEnabled(false);
	       getActionBar().setDisplayShowTitleEnabled(false);
	        
	       mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_launcher, //nav menu toggle icon
	                R.string.app_name, // nav drawer open - description for accessibility
	                R.string.app_name // nav drawer close - description for accessibility
	        ) {
	            public void onDrawerClosed(View view) {
	             //   getActionBar().setTitle(mTitle);
	                // calling onPrepareOptionsMenu() to show action bar icons
	               // invalidateOptionsMenu();
	            }
	 
	            public void onDrawerOpened(View drawerView) {
	             //  getActionBar().setTitle(mDrawerTitle);
	                // calling onPrepareOptionsMenu() to hide action bar icons
	               // invalidateOptionsMenu();
	            }
	        };
	      
	      mDrawerLayout.setDrawerListener(mDrawerToggle);
	            // on first time display view for first nav item
	            try {
	            	if(user_type.equals("ÿ«·»"))
					displayView(1);
	            	   else{
	            		   if(User_Grade.equals("")||User_Grade.equals(null)){
	            		   User_Grade="0";
	            	       SemID="1";
	            		   }
	            		   
	            	       displayView(1);
	            		}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	       
	       
	        ET1.setOnEditorActionListener(
				    new EditText.OnEditorActionListener() {
				    	@Override
				    	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				    	    if (actionId == EditorInfo.IME_ACTION_SEARCH ||
				    	            actionId == EditorInfo.IME_ACTION_DONE ||
				    	            event.getAction() == KeyEvent.ACTION_DOWN ||
				    	            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
				    	        if (!event.isShiftPressed()) {
				    	           // the user is done typing. 
				    	        	 TargetWOrd=ET1.getText().toString();
				    	        	 android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
				    				 ft.hide(fragment);
				    				Bundle bundle4 = new Bundle();
				    	        	bundle4.putString("UserID",Uid);
				    	        	bundle4.putString("User_name",Uname);
				    	        	bundle4.putString("user_TOken",UserToken);
				    	        	bundle4.putString("user_grade",User_Grade);
				    	        	bundle4.putString("User_thumb",Thumb);
				    	        	bundle4.putString("the_searchableWord",TargetWOrd);
				    	        	// set Fragmentclass Arguments
				    	        	 fragment = new search_fragment();
				    	        	 fragment.setArguments(bundle4); 
				    		           ////
				    			        ft.add(R.id.frame_container,fragment);
				    			        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				    			        ft.addToBackStack(null);
				    			        ft.commit();
				    			       getFragmentManager().executePendingTransactions();
				    			       mDrawerLayout.closeDrawer(lN);
				    	           return true; // consume.
				    	        }                
				    	    }
				    	    return false; // pass on to other listeners. 
				    	}
				    	});
	    }
	
	    @Override
		public void setTitle(CharSequence title) {
			// TODO Auto-generated method stub
			super.setTitle(title);
		}

		/**
	     * Slide menu item click listener
	     * */
	   class SlideMenuClickListener implements
	            ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position,
	                long id) {
	            // display view for selected nav drawer item
	            try {
	            	if(position==3){
	            		
	            	}else{
					displayView(position);}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        }
	     /*  try {
			displayView(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	   
	 
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // toggle nav drawer on selecting action bar app icon/title
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	      
	        // Handle action bar actions click
	        switch (item.getItemId()) {
	       case R.id.Nav_drawerr:
	    /*   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	            // You can also use "GravityCompat.END" instead of mDrawerListRight
	            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
	                mDrawerLayout.closeDrawer(mDrawerList);
	            } else {
	                mDrawerLayout.openDrawer(mDrawerList);
	            }*/
	            return true;
	          
	     
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	 
	    /***
	     * Called when invalidateOptionsMenu() is triggered
	     */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // if nav drawer is opened, hide the action items
	    	   MenuItem item = menu.findItem(R.id.Logo_home);

	    	 tv  = (TextView) item.getActionView().findViewById(R.id.TheTitle);
	    	 tv.setTypeface(typeface2);
	    	tv.setText(getTitle());
	      //  boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	       // menu.findItem(R.id.action_search).setVisible(!drawerOpen);
	      // MenuItem item2 = menu.findItem(R.id.Nav_drawerr);
	       // MenuItem item3 = menu.findItem(R.id.action_search);
	     
	        // Use this if you set with default actionbar item
	        //item.setTitle("sampleText");
	    	 ImageView Nav_drawer_menu_pic = (ImageView) item.getActionView().findViewById(R.id.Nav_drawerr);
		        ImageView THE_actionBAr_logo = (ImageView) item.getActionView().findViewById(R.id.TheMainLogo);
		        // SV=(SearchView) item.getActionView().findViewById(R.id.fragment_address_search);
		     //    SV.setVisibility(View.GONE);
		      //  SV.setOnQueryTextListener(this);
		         TheBannerOnActionBar=(WebView) item.getActionView().findViewById(R.id.BannerActionBar);
		        // TheBannerOnActionBar1=(WebView) item.getActionView().findViewById(R.id.BannerActionBar1);
		       TheBannerOnActionBar.setVisibility(View.GONE);
		      // TheBannerOnActionBar1.setVisibility(View.GONE);
		       THE_actionBAr_logo.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				//	Intent in=new Intent(getApplicationContext(),Browsing.class);
					//startActivity(in);
				}
			});
	       // Nav_drawer_menu_pic.setVisibility(View.GONE);
	        Nav_drawer_menu_pic.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				 mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			            // You can also use "GravityCompat.END" instead of mDrawerListRight
			            if (mDrawerLayout.isDrawerOpen(lN)) {
			                mDrawerLayout.closeDrawer(lN);
			            } else {
			                mDrawerLayout.openDrawer(lN);
			            }
				}
			});
	   
	      
	        return super.onPrepareOptionsMenu(menu);
	    }
	 
		/**
	     * Diplaying fragment view for selected nav drawer list item
	     * @throws JSONException 
	     * @throws IOException 
	     * */
	    private void displayView(int position) throws IOException, JSONException {
	        // update the main content by replacing fragments
	       
	    	
	        switch (position) {
	      
	        case 0:
	        	if(!Uid.equals("")||!Uid.equals(null)){
	        	Bundle bundle1 = new Bundle();
	        	bundle1.putString("UserID",Uid);
	        	bundle1.putString("User_name",Uname);
	        	bundle1.putString("user_TOken",UserToken);
	        	bundle1.putString("user_grade",User_Grade);
	        	bundle1.putString("User_thumb",Thumb);
	        	// set Fragmentclass Arguments
	        	 fragment = new Profile();
	        	 fragment.setArguments(bundle1);
	          //  fragment =new HomeFragment();
	        	}
	            break;
	        case 1:
	        	  mDrawerList.setSelection(position);
	        	Bundle bundle = new Bundle();
	        	bundle.putString("UserID",Uid);
	        	bundle.putString("User_name",Uname);
	        	bundle.putString("user_TOken",UserToken);
	        	bundle.putString("user_grade",User_Grade);
	        	bundle.putString("User_thumb",Thumb);
	        	bundle.putString("semsterID",SemID);
	        	// set Fragmentclass Arguments
	        	//Toast.makeText(getApplicationContext(), "ALGRADE "+User_Grade,Toast.LENGTH_LONG).show();
	        	 fragment = new HomeFragment();
	        	 fragment.setArguments(bundle);
	        	
	            break;
	        case 2:
	        //competition 
	         //WEB VIEW !
	        	
	        	Bundle bundle1 = new Bundle();
	        	bundle1.putString("Link",Link);
	        	 fragment = new Competition_Fragment();
	        	fragment.setArguments(bundle1);
	        	
	            break;
	        case 3:
	        	//browsing
	            break;
	        case 4:
	       /* 	if(!Uid.equals("")||!Uid.equals(null)){
	        	Bundle bundle2 = new Bundle();
	        	bundle2.putString("UserID",Uid);
	        	bundle2.putString("User_name",Uname);
	        	bundle2.putString("user_TOken",UserToken);
	        	bundle2.putString("user_grade",User_Grade);
	        	bundle2.putString("User_thumb",Thumb);
	        	// set Fragmentclass Arguments
	        	 fragment = new Sittings_fragment();
	        	 fragment.setArguments(bundle2);
	          //  fragment =new HomeFragment();
	           * 
	        	}*/
	        /*	if(!Uid.equals("")||!Uid.equals(null)){
		        	// THE Notifications
		        	Bundle bundle3 = new Bundle();
		        	bundle3.putString("UserID",Uid);
		        	bundle3.putString("User_name",Uname);
		        	bundle3.putString("user_TOken",UserToken);
		        	bundle3.putString("user_grade",User_Grade);
		        	bundle3.putString("User_thumb",Thumb);
		        	bundle3.putStringArrayList("notificationsList", Notifications);
		        	// set Fragmentclass Arguments
		        	 fragment = new Notifications_user_fragment();
		        	 fragment.setArguments(bundle3);
		          //  fragment = new HomeFragment();
		        	}*/
	        	Bundle bundle8 = new Bundle();
	        	bundle8.putString("UserID",Uid);
	        	bundle8.putString("User_name",Uname);
	        	bundle8.putString("user_TOken",UserToken);
	        	bundle8.putString("user_grade","0");
	        	bundle8.putString("User_thumb",Thumb);
	        	bundle8.putString("semsterID","1");
	        	// set Fragmentclass Arguments
	        	//Toast.makeText(getApplicationContext(), "ALGRADE "+User_Grade,Toast.LENGTH_LONG).show();
	        	 fragment = new HomeFragment();
	        	 fragment.setArguments(bundle8);
	        	
	            break;
	        case 5:
	        
	        	if(!Uid.equals("")||!Uid.equals(null)){
		        	//LOGOUT
	        	/*	Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_LONG).show();
	        		Intent output = new Intent();
	        		
	        		setResult(RESULT_OK, output);
	        		finish();*/
	        		 SharedPreferences preferences =getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
	   	             SharedPreferences.Editor editor = preferences.edit();
	   	             editor.clear();
	   	             editor.commit();
	   	           Intent in=new Intent(getApplicationContext(),MiddleScreen_home.class);
	   	           startActivity(in);
		        	}
	            break;
	        case 6:
	        	
	        	
	            break;
	        case 7:
	        
	            break;
	        default:
	            break;
	        }
	       
	        if (fragment != null ) {
	        	
	           
	           getFragmentManager().beginTransaction()
	                 .replace(R.id.frame_container,   fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
	          
	            // update selected item and title, then close the drawer
	            mDrawerList.setItemChecked(position, true);
	            mDrawerList.setSelection(position);//navMenuTitles[position]
	            if(position==0){ setTitle(Uname);}
	            else if(position==1){
	            	//setTitle("«·—∆Ì”Ì…");
	            }
	            else{setTitle("");}
	           
	          
	            mDrawerLayout.closeDrawer(lN);
	        
	        } else {
	            // error in creating fragment
	            Log.e("MainActivity", "Error in creating fragment");
	        }
	      
	    }
	 
	   
	    /**
	     * When using the ActionBarDrawerToggle, you must call it during
	     * onPostCreate() and onConfigurationChanged()...
	     */
	 
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	      //  mDrawerToggle.syncState();
	    }
	 
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	      mDrawerToggle.onConfigurationChanged(newConfig);
	        

	       if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
	        {
	           // Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
	            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        }
	        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
	        {
	          //  Toast.makeText(this, "landscape", Toast.LENGTH_LONG).show();
	            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	        } 
	    }
	    public static Bitmap getBitmapFromURL(String src) {
		    try {
		        Log.e("src",src);
		        URL url = new URL(src);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setDoInput(true);
		        connection.connect();
		        InputStream input = connection.getInputStream();
		        Bitmap myBitmap = BitmapFactory.decodeStream(input);
		        Log.e("Bitmap","returned");
		        return myBitmap;
		    } catch (IOException e) {
		        e.printStackTrace();
		        Log.e("Exception",e.getMessage());
		        return null;
		    }
		}
	    public String CurrentSemster(){
	    	String Semster="";
	    	Calendar c= Calendar.getInstance();
	    			int cyear = c.get(Calendar.YEAR);//calender year starts from 1900 so you must add 1900 to the value recevie.i.e., 1990+112 = 2012
	    			int cmonth = c.get(Calendar.MONTH);//this is april so you will receive  3 instead of 4.
	    			int cday = c.get(Calendar.DAY_OF_MONTH);
	    			Semster=Integer.toString(cmonth);
	    			if(Semster.equals("1") || Semster.equals("2" )||Semster.equals("3" )|| Semster.equals("4") || Semster.equals("5") || Semster.equals("6") || Semster.equals("7")){
	    				Semster="2";
	    				return "2";
	    			}
	    			if(Semster.equals("0" )|| Semster.equals("11") ||Semster.equals("8" )|| Semster.equals("9") || Semster.equals("10") ){
	    				
	    				Semster="1";
	    				return "1";
	    			}
	    			return Semster;
	    }
	    public void changeFragment(String Subject_ID,String UT,String sem_ID) {
	    	
	    	fragment = new Subject_fragment(Subject_ID,UT,sem_ID);
	        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
	      
	        ft.replace(R.id.frame_container,fragment);
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	      ft.addToBackStack(null);
	        ft.commit();
	       getFragmentManager().executePendingTransactions();
	    }
	    public void changeFragment1(String LessonID,String UserToken) {
	    	fragment =  new Single_lesson_fragment(LessonID,UserToken);
	    	android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
	    	 
	        ft.replace(R.id.frame_container, fragment);
	       ft.addToBackStack(null);
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        ft.commit();
	       getFragmentManager().executePendingTransactions();
	    }
	    public void changeFragment11(String LessonID,String UserToken,String SemID) {
	    	fragment =  new LessonViewsFragment(LessonID,UserToken,SemID);
	    	android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
	    	 
	        ft.replace(R.id.frame_container,fragment);
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	      ft.addToBackStack(null);
	        ft.commit();
	       getFragmentManager().executePendingTransactions();
	    }
	    public void changeFragment13(String Grade_ID,String UT,String Semster_ID) {
	    	fragment =  new HomeFragment(Grade_ID,UT,Semster_ID);
	    	android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
	    	 
	        ft.replace(R.id.frame_container,   fragment);
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	    ft.addToBackStack(null);
	        ft.commit();
	       getFragmentManager().executePendingTransactions();
	    }
	    public void changeFragment2(String UserID,String UserToken) {
	    	fragment =  new Profile(UserID,UserToken);
	    	android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
	    	 
	        ft.replace(R.id.frame_container,   fragment);
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	      ft.addToBackStack(null);
	        ft.commit();
	       
	       getFragmentManager().executePendingTransactions();
	    }
	   
	    public void changeFragment1114(String LessonID,String UserToken,String Vid_ID) {
	    	 android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		        ft.hide(fragment);
	    	 fragment =  new Single_lesson_fragment(LessonID,UserToken,Vid_ID);
	    	
	        ft.add(R.id.frame_container,   fragment);
	      //  ft.hide( fragment);
	        ft.addToBackStack(null);
	       ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        ft.commit();
	       getFragmentManager().executePendingTransactions();
	    }
	    public void changeFragment113(String LessonID,String UserToken,String SemID) {
	    	 android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		        ft.hide(fragment);
	    	 fragment =  new Lesson_parts(LessonID,UserToken,SemID);
	    	
	        ft.add(R.id.frame_container,   fragment);
	      //  ft.hide( fragment);
	        ft.addToBackStack(null);
	       ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	        ft.commit();
	       getFragmentManager().executePendingTransactions();
	    }
	   

		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			android.os.Process.killProcess(android.os.Process.myPid());
			
		}

		  class ProgressTask extends AsyncTask<String, Integer, String> {

		      
				private ProgressDialog progressDialog;
				private String json6;
				private String LINK;
				private String Pic;
				private String Hegi;
				private String Wedith;
				 
				

				@Override
		        protected void onPreExecute() {
		            // TODO Auto-generated method stub
		            super.onPreExecute();
		                    progressDialog = new ProgressDialog(CopyOfMainActivity.this,R.style.NewDialog);
		            progressDialog .setMessage("Loading...");
		          //  progressDialog.setIcon(R.drawable.preloader);
		            progressDialog .setCancelable(false);
		        
		            progressDialog .show();
		        }

		        @Override
		        protected String doInBackground(String... params) {
		        	try {
		    			json = userFunction.Grades(CountryID);
		    		} catch (IOException e1) {
		    			// TODO Auto-generated catch block
		    			e1.printStackTrace();
		    		} catch (JSONException e1) {
		    			// TODO Auto-generated catch block
		    			e1.printStackTrace();
		    		}
		        	 try {
						 
						   Grades.add("");
						   Grades_semCount.add("");
						   Grades_IDs.add("");
				       jObj = new JSONObject(json);
				       jsonArray=jObj.getJSONArray("grades");
				       for (int i = 0; i < jsonArray.length(); i++) {
						     try {
								JSONObject obj1 = jsonArray.getJSONObject(i);
								String Grade_ID=obj1.getString("id");
								String Grade_name=obj1.getString("name");
								String SEM_count=obj1.getString("semesters_count");
								Grades.add(Grade_name);
								Grades_semCount.add(SEM_count);
								Grades_IDs.add(Grade_ID);
								Log.d("tagatagatag",SEM_count );
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				       
				    } catch (JSONException e) {
				        Log.e("JSON Parser", "Error parsing data " + e.toString());
				    }
		    /////////////////////////////
		    		/*		try {
		    					json3 = userFunction.User_Notifications(Uid);
		    					Log.d("NOTIFICATIONS", json3);
		    				} catch (IOException e1) {
		    					// TODO Auto-generated catch block
		    					e1.printStackTrace();
		    				}
		    					   try {
		    						
		    				      // jObj = new JSONObject(json);
		    				       jsonArray=new JSONArray(json3);
		    				       for (int i = 0; i < jsonArray.length(); i++) {
		    				    	   String One_notifications=jsonArray.getString(i);
		    				    	   Notifications.add(One_notifications);
		    				    	    counter+=1;
		    						}
		    				      
		    				    } catch (JSONException e) {
		    				        Log.e("JSON Parser", "Error parsing data " + e.toString());
		    				    }
		    					   try {
		    							json6 = userFunction.GetBanner5Splash();
		    							Log.d("333333333333333333333333", json6);
		    						    	   JSONObject obj1 = new JSONObject(json6);
		    						    	    LINK=obj1.getString("link");
		    									 Pic=obj1.getString("content");
		    									  Hegi=obj1.getString("height");
		    							    	   Wedith=obj1.getString("width");
		    								
		    						} catch (IOException e) {
		    							// TODO Auto-generated catch block
		    							e.printStackTrace();
		    						} catch (JSONException e) {
		    							// TODO Auto-generated catch block
		    							e.printStackTrace();
		    						}
		    						*/
		        	  runOnUiThread(new Runnable() {

		          	     String BGCOlor="";

						@Override
		          	    public void run() {
		        	       Count_notifications=String.valueOf(counter);
		          	      navDrawerItems.add(new NavDrawerItem(Uname, getBitmapFromURL(Thumb)));
		      	        // MainScreen1
		      	        navDrawerItems.add(new NavDrawerItem("«·—∆Ì”Ì…",R.drawable.home ));
		      	        // competition2
		      	        navDrawerItems.add(new NavDrawerItem("«·„”«»ﬁ…", R.drawable.cup));
		      	        //SpinnerBrowsing3
		      	        navDrawerItems.add(new NavDrawerItem(" ’›Õ «·„Ê«œ «·—∆Ì”Ì…",Grades,Grades_semCount,Grades_IDs,CountryID,Semsters,Uid,Uname,Thumb,UserToken,user_type,R.drawable.dropdown_nav_menu));
		      	        // sittings4
		      	      //  navDrawerItems.add(new NavDrawerItem("«⁄œ«œ« ", R.drawable.settings));
		      	       //Notifications
		      	       // navDrawerItems.add(new NavDrawerItem("«· ‰»ÌÂ« ",Count_notifications ,R.drawable.notifications_sidebar));
		      	      //Search
		      	    //    navDrawerItems.add(new NavDrawerItem("«»ÕÀ ⁄‰ œ—”",R.drawable.search));
		      	      navDrawerItems.add(new NavDrawerItem("«· ⁄·Ì„ «·Õ—", R.drawable.ta3lem_7or));
		      	      //Logout
		      	        navDrawerItems.add(new NavDrawerItem(" ”ÃÌ· «·Œ—ÊÃ", R.drawable.log_out));
		          	    	
		      	        // Recycle the typed array5
		      	    //    navMenuIcons.recycle();
		      	 
		      	       mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		      	 
		      	        // setting the nav drawer list adapter
		      	        adapter = new NavDrawerListAdapter(getApplicationContext(),
		      	             navDrawerItems);
		      	       mDrawerList.setAdapter(adapter);
		      	 /*    if(!Pic.equals("")){
         	    		 TheBannerOnActionBar.setVisibility(View.VISIBLE);
         	    	 }
         	    			 if(!LINK.equals("")){
         	   	       
         	   	     //   TheBannerOnActionBar1.setVisibility(View.VISIBLE);
         	   	        }
         	    			 TheBannerOnActionBar.getSettings().setJavaScriptEnabled(true);
						      TheBannerOnActionBar.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
						      TheBannerOnActionBar.getSettings().setPluginsEnabled (true);
						      TheBannerOnActionBar.getSettings().setSupportMultipleWindows (false);
						   //   TheBannerOnActionBar.getSettings().setSupportZoom (true);
						    //  TheBannerOnActionBar.getSettings().setBuiltInZoomControls(true);
						     TheBannerOnActionBar.getSettings().setUseWideViewPort(false); 
						     TheBannerOnActionBar.setVerticalScrollBarEnabled(false);
						     TheBannerOnActionBar.setHorizontalScrollBarEnabled(false);
						    //  TheBannerOnActionBar.setVerticalScrollBarEnabled (true); 
						     //  TheBannerOnActionBar.setHorizontalScrollBarEnabled (true);
						     TheBannerOnActionBar.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
						      TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON);
						      TheBannerOnActionBar.getSettings().setAllowFileAccess(true);
						    TheBannerOnActionBar.getSettings().setLoadWithOverviewMode(false);
					   	//webView.getSettings().setAllowContentAccess(true);
					   	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
						       TheBannerOnActionBar.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
						       TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
						       TheBannerOnActionBar.setBackgroundColor(Color.TRANSPARENT);  
         				       json6="";
         				       if(!Pic.equals("")){
         				    	  Log.d("hna al Pic", Pic);
         				json6="<html><body bgcolor="+BGCOlor +">"+
	          				
"<img src="+Pic+" height="+Hegi+"width="+Wedith +" align="+"MIDDLE"+ ">"+ 
         				
         				"</body></html>"
         				;
         			//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
         				TheBannerOnActionBar.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
         				 
         				       }
         				   /*   TheBannerOnActionBar1.getSettings().setJavaScriptEnabled(true);
         				      TheBannerOnActionBar1.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
         				      TheBannerOnActionBar1.getSettings().setPluginsEnabled (true);
         				      TheBannerOnActionBar1.getSettings().setSupportMultipleWindows (false);
         				    //  TheBannerOnActionBar1.getSettings().setSupportZoom (true);
         				    //  TheBannerOnActionBar1.getSettings().setBuiltInZoomControls(true);
         				     TheBannerOnActionBar1.getSettings().setUseWideViewPort(true); 
         				     // TheBannerOnActionBar1.setVerticalScrollBarEnabled (true); 
         				     //  TheBannerOnActionBar1.setHorizontalScrollBarEnabled (true);
         				     TheBannerOnActionBar1.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
         				      TheBannerOnActionBar1.getSettings().setPluginState(WebSettings.PluginState.ON);
         				      TheBannerOnActionBar1.getSettings().setAllowFileAccess(true);
         				    TheBannerOnActionBar1.getSettings().setLoadWithOverviewMode(true);
         			   	//webView.getSettings().setAllowContentAccess(true);
         			   	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
         				       TheBannerOnActionBar1.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
         				       TheBannerOnActionBar1.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
         				      TheBannerOnActionBar1.setBackgroundColor(Color.TRANSPARENT);  
         				       json6="";
         				       if(!LINK.equals("")){
         				    	   Log.d("hna al LINK", LINK);
         				json6="<html><center><body bgcolor="+BGCOlor +">"+
         				
"<img src="+LINK+" height="+Hegi+"width="+Wedith +" align="+"left"+ ">"+
         				
         				"</body></center></html>"
         				;
         			//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
         				  TheBannerOnActionBar1.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
         				 
         				       }
*/
		      	  		          	    }
		          	});
		      		 return "done";
		        }

		        @Override
		        protected void onPostExecute(String result) {
		            // TODO Auto-generated method stub
		            super.onPostExecute(result);
		          
		            if(progressDialog!=null)
		            progressDialog.dismiss();
		        }
		        
		    }
		 
		  @Override
		    public void onBackPressed() {
		    	  if (getFragmentManager().getBackStackEntryCount()>0) {
				//  ft.show(fragment);
		    	getFragmentManager().popBackStack();
		    		/*  mFragmentStack.pop();
		    		  android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		    	
		    	        ft.show( mFragmentStack.peek());
		    	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		    	        ft.commit();
		    		 */
		    		 
		    	    } else {
		    	    	 Intent intent = new Intent(Intent.ACTION_MAIN);
	                       intent.addCategory(Intent.CATEGORY_HOME);
	                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                       startActivity(intent);
		       this.finish();
		     
		        }
		    	//  mFragmentStack.pop();
		    	   
		    	 
		    }
		  @Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getApplicationContext(), "text Change", Toast.LENGTH_LONG).show();
				return true;
			}
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				TargetWOrd=query;
			//	Toast.makeText(getApplicationContext(), "text Submit", Toast.LENGTH_LONG).show();
				// SV.setVisibility(View.INVISIBLE);
				// SV.setVisibility(View.VISIBLE);
				 SV.clearFocus();
				 SV.setQuery(query, false);
				 SV.setFocusable(false);
				// final InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			//	 imm. hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
				 android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
				 ft.hide(fragment);
				Bundle bundle4 = new Bundle();
	        	bundle4.putString("UserID",Uid);
	        	bundle4.putString("User_name",Uname);
	        	bundle4.putString("user_TOken",UserToken);
	        	bundle4.putString("user_grade",User_Grade);
	        	bundle4.putString("User_thumb",Thumb);
	        	bundle4.putString("the_searchableWord",TargetWOrd);
	        	// set Fragmentclass Arguments
	        	 fragment = new search_fragment();
	        	 fragment.setArguments(bundle4); 
		           ////
			        ft.add(R.id.frame_container,fragment);
			        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			        ft.commit();
			       getFragmentManager().executePendingTransactions();
				return true;
			}
			 
			@Override
		    public void onStart() {
		        super.onStart();
		        EasyTracker.getInstance(this).activityStart(this);
		    }
		 
		    @Override
		    public void onStop() {
		        super.onStop();
		        EasyTracker.getInstance(this).activityStop(this);
		    }
}
