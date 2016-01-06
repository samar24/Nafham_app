package com.nafham.education;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.nafham.education.R;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;

public class Competition_Fragment  extends Fragment {
	private WebView webView;
String Link="";
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	 Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	 Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
    	
        View rootView = inflater.inflate(R.layout.competition_fragment, container, false);
        webView = new WebView(getActivity());
    	webView = (WebView) rootView.findViewById(R.id.webView1);
   
    	Link = getArguments().getString("Link"); 
    	 new ProgressTask().execute();
        
        return rootView;
    }
	private class MyWebViewClient extends WebChromeClient { 
        @Override
        public void onProgressChanged(WebView view, int newProgress) {          
          
            super.onProgressChanged(view, newProgress);
        }
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback); 
            if(view instanceof FrameLayout){
                FrameLayout frame  = (FrameLayout)view;
                if(frame.getFocusedChild()instanceof VideoView){
                VideoView video =  (VideoView)frame.getFocusedChild();
                    frame.removeView(video);
                               video.start();

                }
            }
        }
    }
	 class ProgressTask extends AsyncTask<String, Integer, String> {

	        private ProgressDialog progressDialog;

			@Override
	        protected void onPreExecute() {
	            // TODO Auto-generated method stub
	            super.onPreExecute();
	                    progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
	            progressDialog .setMessage("Loading...");
	            progressDialog .setCancelable(false);
	            progressDialog .show();
	        }

	        @Override
	        protected String doInBackground(String... params) {
	        	  webView.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
	              webView.getSettings().setPluginsEnabled (true);
	              webView.getSettings().setSupportMultipleWindows (false);
	              webView.getSettings().setSupportZoom (true);
	              webView.getSettings().setBuiltInZoomControls(true);
	              webView.getSettings().setUseWideViewPort(true); 
	              webView.setVerticalScrollBarEnabled (true); 
	              webView.setHorizontalScrollBarEnabled (true);
	              webView.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
	              webView.getSettings().setPluginState(WebSettings.PluginState.ON);
	        	webView.getSettings().setAllowFileAccess(true);
	        	webView.getSettings().setLoadWithOverviewMode(true);
	        	//webView.getSettings().setAllowContentAccess(true);
	        	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
	        	webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
	        	  webView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
	        	  webView.setWebChromeClient(new MyWebViewClient());
	        	  webView.getSettings().setJavaScriptEnabled(true);
	        	  webView.loadUrl(Link);
	        	  getActivity().runOnUiThread(new Runnable() {

	          	    @Override
	          	    public void run() {
	          	      webView.setWebViewClient(new WebViewClient() {
	            	        public boolean shouldOverrideUrlLoading(WebView view, String url){
	            	        	//webView.getSettings().setJavaScriptEnabled(true);
	            	        	//  webView.getSettings().setLoadWithOverviewMode(true);
	                            //  webView.getSettings().setUseWideViewPort(true);
	                             // webView.getSettings().setPluginsEnabled(true);
	                            // webView.getSettings().setPluginState(PluginState.ON);
	            	          webView.setWebChromeClient(new MyWebViewClient());
	            	        	webView.loadUrl(url);
	            	            //view.loadUrl(url);
	            	        	 return super.shouldOverrideUrlLoading(view, url);
	            	          //  return false; // prevents the default action - opening in system browser
	            	        }
	            	        
	                    });
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
}
