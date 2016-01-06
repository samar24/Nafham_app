package com.nafham.education;


import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.analytics.tracking.android.EasyTracker;
import com.nafham.education.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import com.pushlink.android.PushLink;
public class SPLASH extends Activity{
	UserFunctions userFunction = new UserFunctions();
	private String json6;
	private WebView TheBannerOnActionBar;
	private WebView TheBannerOnActionBar1;
	  String Hegi="";
	  String Wedith="";
	  String LINK="";
	  String Pic="";
	  String Content;
	  String BGCOlor="";
	  TextView TVVV;
	  PushLink pl;
	private Typeface typeface1;
	private Typeface typeface2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		PushLink.start(this,0,"0","0");
		 EasyTracker.getInstance(this).activityStart(this); 
		 ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
         if (connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)){ 
             //You are connected, do something online.
        	// Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
         }else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {             
             //Not connected.        
       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
       this.finish();
         } 
		  typeface1 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  typeface2 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
		  TheBannerOnActionBar=(WebView) findViewById(R.id.SplashBanner);
		  TheBannerOnActionBar1=(WebView) findViewById(R.id.SplashBanner1);
		  TheBannerOnActionBar.setVisibility(View.GONE);
		  TheBannerOnActionBar1.setVisibility(View.GONE);
		  TVVV=(TextView)findViewById(R.id.textView1);
		  TVVV.setVisibility(View.GONE);
	      //  if(!json6.equals("")){
	        	//TheBannerOnActionBar.setVisibility(View.VISIBLE);
	      //  }
	    
		new Thread(myThread).start();
		
	}
	
	@Override
	  public void onStop() {
	    super.onStop();
	  
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }
	private Runnable myThread = new Runnable(){

	    private int myProgress;
		public void run() {
	        // TODO Auto-generated method stub
			
		      
		/*	try {
				json6 = userFunction.GetBanner4ActionBar();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				json6 = userFunction.GetBanner4ActionBar();
				Log.d("sha2sha2", json6);
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
                 @Override
                 public void run() {

                	 if(!Pic.equals("")&&!LINK.equals("")){
		          	        TheBannerOnActionBar.setVisibility(View.VISIBLE);
		          	      TheBannerOnActionBar1.setVisibility(View.VISIBLE);
		          	      TVVV.setVisibility(View.VISIBLE);
		          	      TVVV.setTypeface(typeface2);
		          	        }
						  TheBannerOnActionBar.getSettings().setJavaScriptEnabled(true);
						      TheBannerOnActionBar.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
						      TheBannerOnActionBar.getSettings().setPluginsEnabled (true);
						      TheBannerOnActionBar.getSettings().setSupportMultipleWindows (false);
						     // TheBannerOnActionBar.getSettings().setSupportZoom (true);
						     // TheBannerOnActionBar.getSettings().setBuiltInZoomControls(true);
						     TheBannerOnActionBar.getSettings().setUseWideViewPort(true); 
						    //  TheBannerOnActionBar.setVerticalScrollBarEnabled (true); 
						     //  TheBannerOnActionBar.setHorizontalScrollBarEnabled (true);
						     TheBannerOnActionBar.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
						      TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON);
						      TheBannerOnActionBar.getSettings().setAllowFileAccess(true);
						    TheBannerOnActionBar.getSettings().setLoadWithOverviewMode(true);
					   	//webView.getSettings().setAllowContentAccess(true);
					   	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
						       TheBannerOnActionBar.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
						       TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
						       TheBannerOnActionBar.setBackgroundColor(Color.TRANSPARENT);  
						       json6="";
						       if(!Pic.equals("")){
						json6="<html><body bgcolor="+BGCOlor +">"+
						
"<img src="+Pic+" height="+Hegi+"width="+Wedith +" align="+"right"+ ">"+
						
						"</body></html>"
						;
					//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
						  TheBannerOnActionBar.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
						       }
						  ////////////////////////////////////////
						  TheBannerOnActionBar1.getSettings().setJavaScriptEnabled(true);
					      TheBannerOnActionBar1.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
					      TheBannerOnActionBar1.getSettings().setPluginsEnabled (true);
					      TheBannerOnActionBar1.getSettings().setSupportMultipleWindows (false);
					     // TheBannerOnActionBar1.getSettings().setSupportZoom (true);
					    //  TheBannerOnActionBar1.getSettings().setBuiltInZoomControls(true);
					     TheBannerOnActionBar1.getSettings().setUseWideViewPort(true); 
					    //  TheBannerOnActionBar1.setVerticalScrollBarEnabled (true); 
					    //   TheBannerOnActionBar1.setHorizontalScrollBarEnabled (true);
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
					json6="<html><body bgcolor="+BGCOlor +">"+
					
            "<img src="+LINK+" height="+Hegi+"width="+Wedith +" align="+"left"+ ">"+
					
					"</body></html>"
					;
				//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
					  TheBannerOnActionBar1.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
					 
						      
                 }
                 }  });
	        while (myProgress<200){
	            try{
	                myHandle.sendMessage(myHandle.obtainMessage());
	                Thread.sleep(15);
	            }
	            catch(Throwable t){

	            }
	        }
	    }
	    Handler myHandle = new Handler(){

	        @Override
	        public void handleMessage(Message msg) {
	            // TODO Auto-generated method stub
	            myProgress++;
	          
	            if(myProgress==200){
	            Intent i = new Intent(SPLASH.this,MiddleScreen_home.class);
		     	startActivity(i);
				SPLASH.this.finish();
				}
	        }
	    };
	};

}
