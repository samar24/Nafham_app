package com.nafham.education;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Stack;
import android.support.v4.app.FragmentActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.nafham.education.R;

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
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.*;
import android.support.v4.app.*;

import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.widget.LoginButton;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;

public class Login_page  extends Activity{
	EditText UsernameEmail;
	 EditText Password;
	 Fragment mainFragment;
	 String userEmail;
	 String pass;
	 String UserName="";
	 Button Login;
	 LoginButton LogInViaFaceBook;
	 
	 String json1;
	 JSONObject jObj1;


	 String UserID;
	 boolean Flag=false;
	 
	 String FILENAME = "AndroidSSO_data";
	 SharedPreferences dhj;
	 
	
	 String json;
	 JSONObject jObj;
	 String json4;
	 JSONObject jObj4;
	private TextView tv;
	private SearchView SV;
	private WebView TheBannerOnActionBar;
	//private WebView TheBannerOnActionBar1;
	    @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			
		}
	   
		
	    UserFunctions userFunction = new UserFunctions();
		private EasyTracker easyTracker;
      
	
	    @Override
	    protected void onCreate(final Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        mainFragment = new MainFragment1();
	        ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	         if (connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)){ 
	             //You are connected, do something online.
	        	// Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
	         }else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {             
	             //Not connected.        
	       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
	       finish();
	         } 
	        // easyTracker = EasyTracker.getInstance(Login_page.this);
	         requestWindowFeature(Window.FEATURE_ACTION_BAR);
	      //  setContentView(R.layout.login_page);
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	    	.permitAll().build();
	    	StrictMode.setThreadPolicy(policy);
	    	 //////////////////////////////////////////
	     setContentView(R.layout.login_page);
	    	
	    			  
	    	      
	    	
		/*	dhj = this.getSharedPreferences("LoginDetails", MODE_WORLD_READABLE);
		
		       if(dhj.getString("TheUserID", null)!=null )  {
		    	   Intent in=new Intent(getApplicationContext(),CopyOfMainActivity.class);
                   in.putExtra("TheUserID", dhj.getString("TheUserID", null));
                   in.putExtra("TheUserName", dhj.getString("TheUserName", null));
                   in.putExtra("TheUserThumb", dhj.getString("TheUserThumb", null));
                   in.putExtra("TheUserGrade", dhj.getString("TheUserGrade", null));
                   in.putExtra("TheUserToken", dhj.getString("TheUserToken", null));
                   in.putExtra("user_TYPE", dhj.getString("userType", null));
                   in.putExtra("CountryID", dhj.getString("UserCountry", null));
                   in.putExtra("Competition","0");
	                 in.putExtra("link","");
                   setContentView(R.layout.login_page);
                   startActivity(in);
		      // Toast.makeText(getApplicationContext(), "the User was here before dude ! ", Toast.LENGTH_LONG).show();
		       }
		      
		       else{
		    	   //Toast.makeText(getApplicationContext(), "logout>>11", Toast.LENGTH_LONG).show();
		    			 setContentView(R.layout.login_page);
		    		 
		       }*/
		       getActionBar().setDisplayHomeAsUpEnabled(false);
			     getActionBar().setDisplayShowHomeEnabled(false);
			       getActionBar().setHomeButtonEnabled(true);
			        getActionBar().setDisplayUseLogoEnabled(false);
			       getActionBar().setDisplayShowTitleEnabled(false);
			       getActionBar().setDisplayShowCustomEnabled(true);
			   	LogInViaFaceBook=(LoginButton)findViewById(R.id.button3);
			       LogInViaFaceBook.setOnClickListener(new View.OnClickListener() {
						
						

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
						/*	Intent in=new Intent(getApplicationContext(),MainActivity_facebook1.class);
						startActivity(in);*/
							 if (savedInstanceState == null) {
						        	// Add the fragment on initial activity setup
								
						            getFragmentManager()
						            .beginTransaction()
                .replace(R.id.frame_container,   mainFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						            .commit();
						        } else {
						        
						        	// Or set the fragment from restored state info
						        	mainFragment = (MainFragment1) getFragmentManager()
						        	.findFragmentById(R.id.frame_container);
						        }
						}
					});
					//LogInViaFaceBook.setFragment(mainFragment);
					LogInViaFaceBook.setReadPermissions(Arrays
			                .asList("user_location", "user_birthday", "user_likes","email","public_profile"));
					LogInViaFaceBook.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
		       linking();
	     	    }
	    private void linking() {
	    	
			Login=(Button)findViewById(R.id.button2);
			UsernameEmail=(EditText)findViewById(R.id.editText1);
			Password=(EditText)findViewById(R.id.editText3);
			Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DroidSansArabic.ttf");
			Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Regular.ttf");
			UsernameEmail.setTypeface(typeface);
		
			Password.setTypeface(typeface);
			
			Login.setTypeface(typeface1); 
		
			Login.setTextColor(Color.parseColor("#FFFFFF"));
			LogInViaFaceBook.setTextColor(Color.parseColor("#FFFFFF"));
			
			Login.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					 userEmail=	UsernameEmail.getText().toString();
				      pass=Password.getText().toString();
					new ProgressTask().execute();
				}
			});
			
		}
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	 
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // toggle nav drawer on selecting action bar app icon/title
	       // if (mDrawerToggle.onOptionsItemSelected(item)) {
	          //  return true;
	       // }
	      
	        // Handle action bar actions click
	        switch (item.getItemId()) {
	       case R.id.Nav_drawerr:
	    /*	   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
	    	 tv.setText(" ”ÃÌ· «·œŒÊ·");
	      //  boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	      //  menu.findItem(R.id.action_search).setVisible(!drawerOpen);
	      //  MenuItem item2 = menu.findItem(R.id.Nav_drawerr);
	       // MenuItem item3 = menu.findItem(R.id.action_search);
	     
	        // Use this if you set with default actionbar item
	        //item.setTitle("sampleText");
	        ImageView Nav_drawer_menu_pic = (ImageView) item.getActionView().findViewById(R.id.Nav_drawerr);
	        ImageView THE_actionBAr_logo = (ImageView) item.getActionView().findViewById(R.id.TheMainLogo);
	       //  SV=(SearchView) item.getActionView().findViewById(R.id.fragment_address_search);
	       //  SV.setVisibility(View.GONE);
	      
	         TheBannerOnActionBar=(WebView) item.getActionView().findViewById(R.id.BannerActionBar);
	      //   TheBannerOnActionBar1=(WebView) item.getActionView().findViewById(R.id.BannerActionBar1);
	       TheBannerOnActionBar.setVisibility(View.GONE);
	      // TheBannerOnActionBar1.setVisibility(View.GONE);
	      
	        THE_actionBAr_logo.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
		    //	Intent in=new Intent(getApplicationContext(),Browsing.class);
					//startActivity(in);
				}
			});
	        Nav_drawer_menu_pic.setVisibility(View.GONE);
	        Nav_drawer_menu_pic.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					/*  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			            // You can also use "GravityCompat.END" instead of mDrawerListRight
			            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
			                mDrawerLayout.closeDrawer(mDrawerList);
			            } else {
			                mDrawerLayout.openDrawer(mDrawerList);
			            }*/
				}
			});
	  
	      
	        return super.onPrepareOptionsMenu(menu);
	    }
	
	    @Override
		public void setTitle(CharSequence title) {
			// TODO Auto-generated method stub
			super.setTitle(title);
		}

		/**
	     * Diplaying fragment view for selected nav drawer list item
	     * @throws JSONException 
	     * @throws IOException 
	     * */
	   
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
	 
	    }
	   
	  
	    @Override
	    public void onBackPressed() {
	    	  Intent in=new Intent(getApplicationContext(),MiddleScreen_home.class);
	          startActivity(in);
	    }

		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			android.os.Process.killProcess(android.os.Process.myPid());
			
		}      
		 
		@Override
	    public void onStart() {
	        super.onStart();
	     //   EasyTracker.getInstance(this).activityStart(this);
	    }
	 
	    @Override
	    public void onStop() {
	        super.onStop();
	        EasyTracker.getInstance(this).activityStop(this);
	    }
	    class ProgressTask extends AsyncTask<String, Integer, String> {

	        private ProgressDialog progressDialog;
			 String countryID;
			
			
			

			@Override
	        protected void onPreExecute() {
	            // TODO Auto-generated method stub
	            super.onPreExecute();
	                    progressDialog = new ProgressDialog(Login_page.this,R.style.NewDialog);
	            progressDialog .setMessage("Loading...");
	            progressDialog .setCancelable(false);
	            progressDialog .show();
	        }

	        @Override
	        protected String doInBackground(String... params) {
	          
	        	   try {
	   				json = userFunction.Login(userEmail, pass);
	   				Log.d("al LOGIN DETIALS", json);
	   				jObj = new JSONObject(json);
	   				if(jObj.isNull("user_id")){
	   	                   Flag=true;
	   				}
	   				else{
	   		   SharedPreferences settings = getSharedPreferences("LoginDetails", 0);
	   					
	   				String user_id=	jObj.getString("user_id");
	   				String User_token=	jObj.getString("user_token");
	   				 UserName=	jObj.getString("user_name");
	   				String User_grade_id=	jObj.getString("user_grade_id");
	   				String User_Thumb=	jObj.getString("user_thumb");
	   				countryID=jObj.getString("user_country_id");
	   				json4=userFunction.User_details(user_id);
	   				Log.d("al LOGIN DETIALSssssss", json4);
	   				jObj4=new JSONObject(json4);
	   				String user_type=jObj4.getString("type");
	   				if(!user_type.equals("ÿ«·»")){
	   					User_grade_id="0";
	   				}
	   			settings.edit().putString("name", userEmail).putString("pwd", pass).putString("TheUserID", user_id).putString("TheUserName", UserName).putString("TheUserThumb", User_Thumb).putString("TheUserGrade", User_grade_id).putString("TheUserToken", User_token).putString("userType", user_type).putString("UserCountry",countryID ).commit();
	   				 Intent in=new Intent(getApplicationContext(),CopyOfMainActivity.class);
	   	              in.putExtra("TheUserID", user_id);
	   	              in.putExtra("TheUserName", UserName);
	   	              in.putExtra("TheUserThumb", User_Thumb);
	   	              in.putExtra("TheUserGrade", User_grade_id);
	   	              in.putExtra("TheUserToken", User_token);
	   	              in.putExtra("user_TYPE", user_type);
	   	               in.putExtra("CountryID", countryID);
	   	               in.putExtra("Competition", "0");
	                 in.putExtra("link", "");
	   	              startActivity(in);
	   			
	   	            
	   				}
	   			} catch (IOException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			} catch (JSONException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
	        	  runOnUiThread(new Runnable() {

	          	    @Override
	          	    public void run() {
	          	  
	          	    	 if(Flag==true){
	     	            	
	     	            	Toast.makeText(getApplicationContext(), "Please enter the right name and password!", Toast.LENGTH_LONG).show();
	     	            }
	          	    	 else{
	          	    	//	Toast.makeText(getApplicationContext(), "Hello"+UserName, Toast.LENGTH_LONG).show();
		          	    	//Toast.makeText(getApplicationContext(), "First LOGIN", Toast.LENGTH_LONG).show();
	          	    	 }
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
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	    }
	 
}
