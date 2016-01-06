package com.nafham.education;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.nafham.education.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.facebook.widget.UserSettingsFragment;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.nafham.education.HomeFragment.ADAPTER;
import com.nafham.education.HomeFragment.LoadImage;
import com.nafham.education.HomeFragment.LoadImage1;
import com.nafham.education.HomeFragment.ProgressTask;

public class First_reg_frag extends Fragment {
    TextView UserN;
    TextView Passwordd;
	TextView Emaill;
	TextView re_add_pass; 
	ImageView Img;
	Button Register;
	String UN;
	String PW;
	String email="";
	String readd;
	String FB_id="";
	UserFunctions userfunctions=new UserFunctions();
	 String Avatar;
    public First_reg_frag(String EM, String FacebookID){
    	this.email=EM;
    	this.FB_id=FacebookID;
    }
public First_reg_frag(){
    	
    	
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reg_one, container, false);
        Img=(ImageView) rootView.findViewById(R.id.button1);
        UserN=(TextView) rootView.findViewById(R.id.editText1);
        Emaill=(TextView) rootView.findViewById(R.id.editText2);
        Passwordd=(TextView) rootView.findViewById(R.id.editText3);
        re_add_pass=(TextView) rootView.findViewById(R.id.editText4);
        Register=(Button)rootView.findViewById(R.id.button2);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DroidSansArabic.ttf");
		Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/DroidKufi-Regular.ttf");
       
              Register.setTypeface(typeface1); 
        Register.setTextColor(Color.parseColor("#FFFFFF"));
        UserN.setTypeface(typeface1); 
        Emaill.setTypeface(typeface1); 
        Passwordd.setTypeface(typeface1); 
        re_add_pass.setTypeface(typeface1); 
        if(!email.equals("")&&!FB_id.equals("")){
        	Log.d("LOOK !", email);
        	Passwordd.setVisibility(View.GONE);
        	re_add_pass.setVisibility(View.GONE);
        	Emaill.setText(email);
        	  email=Emaill.getText().toString();
        }
       
      
  Register.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  			
  				new ProgressTask().execute();
  			}
  		});
       
        Img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 100);    
			}
		});
        return rootView;
    }
    
    class ProgressTask extends AsyncTask<String, Integer, String> {

        private ProgressDialog progressDialog;
		
		private String json;
		private JSONObject jObj;
		boolean Flag_vailed;
		@Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
                    progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
            progressDialog .setMessage("Loading...");
            progressDialog .setCancelable(false);
         //   progressDialog.setIcon(R.drawable.preloader);
            progressDialog .show();
        }

        @Override
        protected String doInBackground(String... params) {
        	
   			   try {
   				  email=Emaill.getText().toString();
   				 json = userfunctions.Register_vaildation(email);
       		     try {
					jObj = new JSONObject(json);
					Flag_vailed =jObj.getBoolean("valid");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   			   }
   		 catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} 
              getActivity().runOnUiThread(new Runnable() {

            	    @Override
            	    public void run() {
            	    	
            	          UN=UserN.getText().toString();
            	          PW=Passwordd.getText().toString();
            	          readd=re_add_pass.getText().toString();
            	    	 if(Flag_vailed==true&&!email.equals("")&&!UN.equals("")&&!PW.equals("")&&!readd.equals("")&&PW.equals(readd)){
            	    		 Registeration mainAct = (Registeration)getActivity();
 	       		             mainAct.changeFragment(email,UN,PW,FB_id,Avatar);
            	    		 //Toast.makeText(getActivity(), "Hello BUDDY !! :)", Toast.LENGTH_LONG).show();
            	    	 }
            	    	 else if(Flag_vailed==true&&!email.equals("")&&!UN.equals("")&&!FB_id.equals("")){
            	    		 Registeration mainAct = (Registeration)getActivity();
 	       		             mainAct.changeFragment(email,UN,PW,FB_id,Avatar);
            	    		// Toast.makeText(getActivity(), "Hello BUDDY !! :)", Toast.LENGTH_LONG).show();
            	    	 }
            	    	 else if(email.equals("")&&UN.equals("")&&PW.equals("")&&readd.equals("")){
            	    		 Toast.makeText(getActivity(), "please complete the registeration credentials", Toast.LENGTH_LONG).show();
            	    	 } 
            	    	 else if(Flag_vailed==false){
            	    		 Toast.makeText(getActivity(), "The email is already regsitered or the passwords are not identicals", Toast.LENGTH_LONG).show();
            	    	 }
            	    	 else if (!PW.equals(readd)){
            	    		 Toast.makeText(getActivity(), " the passwords are not identicals", Toast.LENGTH_LONG).show();
            	    	 }
            	    	
            	    }
            	});
             
      		 return "done";
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
           
            progressDialog.dismiss();
        }
        
    }
    
    @Override
	public void onStart() {
		super.onStart();
		//EasyTracker.getInstance(getActivity()).activityStart(getActivity());
	}
 
	@Override
	public void onStop() {
		super.onStop();
		//EasyTracker.getInstance(getActivity()).activityStop(getActivity());
	} 
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case 100:
	       
	            Uri selectedImage = imageReturnedIntent.getData();
			InputStream imageStream = null;
			try {
				imageStream = getActivity().getContentResolver().openInputStream(selectedImage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
	            Img.setImageBitmap(yourSelectedImage);
	            Avatar=getPath(selectedImage);
	        
	    }
	  
	}
	public String getPath(Uri uri) {

        if( uri == null ) {
            return null;
        }

        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }

        return uri.getPath();
}
}