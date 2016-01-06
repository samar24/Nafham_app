package com.nafham.education;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.nafham.education.R;

import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.widget.LoginButton;
import com.nafham.education.Browsing.LoadGrades;
import com.nafham.education.Browsing.MyCustomAdapter;
import com.nafham.education.Browsing.MyCustomAdapter1;

import android.R.integer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.test.UiThreadTest;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class MiddleScreen_home extends Activity   {
Button Enter ;
Button Register;
Fragment mainFragment;
LoginButton RegisterWithFB;
UserFunctions userFunction = new UserFunctions();
private WebView TheBannerOnActionBar;
private WebView TheBannerOnActionBar1;
SharedPreferences dhj;
		 @Override
			protected void onCreate(final Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.middlescreen);
				  mainFragment = new MainFragment();
				Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DroidSansArabic.ttf");
				Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Regular.ttf");
			
				Enter=(Button)findViewById(R.id.button2);
				Register=(Button)findViewById(R.id.button23);
				RegisterWithFB=(LoginButton)findViewById(R.id.button3);
				  TheBannerOnActionBar=(WebView) findViewById(R.id.BrowsingBanner);
				  TheBannerOnActionBar1=(WebView) findViewById(R.id.BrowsingBanner1);
			       TheBannerOnActionBar.setVisibility(View.GONE);
			       TheBannerOnActionBar1.setVisibility(View.GONE);
			       new ProgressTask().execute();
			       dhj = this.getSharedPreferences("LoginDetails", MODE_WORLD_READABLE);
					
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
	                   setContentView(R.layout.middlescreen);
	                   startActivity(in);
			    //  Toast.makeText(getApplicationContext(), "the User was here before dude ! ", Toast.LENGTH_LONG).show();
			       }
			      
			       else{
			    	 //  Toast.makeText(getApplicationContext(), "logout>>11", Toast.LENGTH_LONG).show();
			    			// setContentView(R.layout.middlescreen);
			    		 
			       }
				Enter.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in=new Intent(getApplicationContext(),Login_page.class);
		   	              startActivity(in);
					}
				});
				Register.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in=new Intent(getApplicationContext(),Registeration.class);
		   	              startActivity(in);
					}
				});
           RegisterWithFB.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					//	Intent in=new Intent(getApplicationContext(),MainActivity_facebook.class);
		   	            //  startActivity(in);
						 if (savedInstanceState == null) {
					        	// Add the fragment on initial activity setup
							
					            getFragmentManager()
					            .beginTransaction()
         .replace(R.id.frame_container,   mainFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					            .commit();
					        } else {
					        
					        	// Or set the fragment from restored state info
					        	mainFragment = (MainFragment) getFragmentManager()
					        	.findFragmentById(R.id.frame_container);
					        }
					}
					
				});
           RegisterWithFB.setReadPermissions(Arrays
	                .asList("user_location", "user_birthday", "user_likes","email","public_profile"));
           RegisterWithFB.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
           Enter.setTypeface(typeface1); 
   		
           Enter.setTextColor(Color.parseColor("#FFFFFF"));
           Register.setTypeface(typeface1); 
      		
           Register.setTextColor(Color.parseColor("#FFFFFF"));
           RegisterWithFB.setTypeface(typeface1); 
      		
           RegisterWithFB.setTextColor(Color.parseColor("#FFFFFF"));
			}
		 @Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				
				return true;
			}
				 @Override
		    public void onBackPressed() {
					 
		         super.onBackPressed();
		         Intent intent = new Intent(Intent.ACTION_MAIN);
                 intent.addCategory(Intent.CATEGORY_HOME);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 startActivity(intent);
		                   
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
					        progressDialog = new ProgressDialog(MiddleScreen_home.this,R.style.NewDialog);
					       progressDialog .setMessage("Loading...");
					       progressDialog .setCancelable(false);
					       progressDialog .show();
					      
					       // progressDialog.setContentView(R.layout.loader);
					    }
						
						 @Override
						    protected void onPostExecute(String result) {
						        // TODO Auto-generated method stub
						        super.onPostExecute(result);
						        if(progressDialog!=null)
						        progressDialog.dismiss();
						    }
						@Override
						protected String doInBackground(String... arg0) {
							// TODO Auto-generated method stub
						
							try {
								json6 = userFunction.GetBanner4ActionBar();
								Log.d("3awww", json6);
								
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
							 runOnUiThread(new Runnable() {
								 private MyCustomAdapter1 adapter2;
									private MyCustomAdapter1 adapter3;
									private MyCustomAdapter adapter1;
									private MyCustomAdapter1 adapter4;
									private MyCustomAdapter adapter5;
									private String BGCOlor="";
								 @Override
						      	    public void run() {
								
					//////////////////////////////////
							/*		 if(!Pic.equals("")){
										  TheBannerOnActionBar.setVisibility(View.VISIBLE);
										
									 }
											 if(!LINK.equals("")){
						          	      
						          	      TheBannerOnActionBar1.setVisibility(View.VISIBLE);
						          	  
						          	        }
										  TheBannerOnActionBar.getSettings().setJavaScriptEnabled(true);
										      TheBannerOnActionBar.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
										      TheBannerOnActionBar.getSettings().setPluginsEnabled (true);
										      TheBannerOnActionBar.getSettings().setSupportMultipleWindows (false);
										   //   TheBannerOnActionBar.getSettings().setSupportZoom (true);
										    //  TheBannerOnActionBar.getSettings().setBuiltInZoomControls(true);
										     TheBannerOnActionBar.getSettings().setUseWideViewPort(false); 
										    //  TheBannerOnActionBar.setVerticalScrollBarEnabled (true); 
										     //  TheBannerOnActionBar.setHorizontalScrollBarEnabled (true);
										     TheBannerOnActionBar.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
										      TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON);
										      TheBannerOnActionBar.getSettings().setAllowFileAccess(true);
										    TheBannerOnActionBar.getSettings().setLoadWithOverviewMode(false);
										    TheBannerOnActionBar.setVerticalScrollBarEnabled(false);
										     TheBannerOnActionBar.setHorizontalScrollBarEnabled(false);
									   	//webView.getSettings().setAllowContentAccess(true);
									   	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
										       TheBannerOnActionBar.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
										       TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
										       TheBannerOnActionBar.setBackgroundColor(Color.TRANSPARENT);  
										// LayoutParams vc=TheBannerOnActionBar.getLayoutParams();
										  //      vc.height=Integer.parseInt(Hegi);
										    //   vc.width=Integer.parseInt(Wedith);
										      //TheBannerOnActionBar.setLayoutParams(vc);
										       json6="";
										       if(!Pic.equals("")){
										json6="<html><center><body bgcolor="+BGCOlor +">"+
										
										"<img src="+Pic+" height="+Hegi+"width="+Wedith + ">"+
										
										"</body></center></html>"
										;
									//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
										  TheBannerOnActionBar.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
										       }
										       TheBannerOnActionBar1.getSettings().setJavaScriptEnabled(true);
											      TheBannerOnActionBar1.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
											      TheBannerOnActionBar1.getSettings().setPluginsEnabled (true);
											      TheBannerOnActionBar1.getSettings().setSupportMultipleWindows (false);
											     // TheBannerOnActionBar1.getSettings().setSupportZoom (true);
											    //  TheBannerOnActionBar1.getSettings().setBuiltInZoomControls(true);
											     TheBannerOnActionBar1.getSettings().setUseWideViewPort(true); 
											    //  TheBannerOnActionBar1.setVerticalScrollBarEnabled (true); 
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
											// LayoutParams vc=TheBannerOnActionBar.getLayoutParams();
											  //      vc.height=Integer.parseInt(Hegi);
											    //   vc.width=Integer.parseInt(Wedith);
											      //TheBannerOnActionBar.setLayoutParams(vc);
											       json6="";
											       if(!LINK.equals("")){
											json6="<html><body bgcolor="+BGCOlor +">"+
											
											"<img src="+LINK+" height="+Hegi+"width="+Wedith +" align="+"left"+ ">"+
											
											"</body></html>"
											;
										//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
											  TheBannerOnActionBar1.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
											       }
											       */
								 }
						      	});
								
							return null;
						}
						
						}
				 @Override
				    public void onActivityResult(int requestCode, int resultCode, Intent data) {
				        super.onActivityResult(requestCode, resultCode, data);
				        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
				    }
}