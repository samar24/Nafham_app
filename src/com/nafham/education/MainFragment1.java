package com.nafham.education;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.nafham.education.R;

public class MainFragment1 extends Fragment {
    private static final String TAG = "MainFragment";
    private UiLifecycleHelper uiHelper;
    private TextView userInfoTextView;
    GraphUser graphUser;
    private String user_ID;
	private String profileName;
	private TextView userNameView;
	UserFunctions userFunction = new UserFunctions();
		 String json;
		 JSONObject jObj;
		 String json4;
		 JSONObject jObj4;
		 String id="";
		 String email="";
		 String name;

		 private boolean Flag=false;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };
	
    private void onSessionStateChange(final Session session, SessionState state,
            Exception exception) {
        if (state.isOpened()) {
            Log.d(TAG, "Logged in...");
          //  userInfoTextView.setVisibility(View.VISIBLE);
         //   userInfoTextView.setText("Logged in...");
            Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
              

				
				
				@Override
                public void onCompleted(GraphUser user, Response response) {
					 GraphObject graphObject = response.getGraphObject();
                     Log.d("user",""+user.asMap());
						 if (user != null) {
		                        String firstName = user.getFirstName();
		                        String lastName = user.getLastName();
		                      id = user.getId();
		                        email = user.asMap().get("email").toString();
		                        name=user.asMap().get("name").toString();
		                       Log.d("facebookid", id);
		                      Log.d("firstName", firstName);
		                      Log.d("name", name);
		                     Log.d("email", email);
		                   
		                      new ProgressTask().execute();
		           
		                    }
                    
                }   
            }); 
            Request.executeBatchAsync(request);
             
        } else if (state.isClosed()) {
            Log.d(TAG, "Logged out...");
           // userInfoTextView.setText(":((((((");
        } else {
            Log.d(TAG, "Unknown state: " + state);
        }
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
      
    }

    @Override
    public void onResume() {
        super.onResume();

        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null && (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main, container, false);
       
     /*   try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "your.root.package", 
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
        userInfoTextView = (TextView) view.findViewById(R.id.userInfoTextView);
        userInfoTextView.setVisibility(View.GONE);
      /*  LoginButton authButton = (LoginButton) view
                .findViewById(R.id.authButton);
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays
                .asList("user_location", "user_birthday", "user_likes","email","public_profile"));
        authButton.setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
        authButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
           

			@Override
           public void onUserInfoFetched(GraphUser user) {
			
               graphUser = user;
               if(user!=null){
            	//Log.d("name",user.getName());
            	//Log.d("emaillll", user.getProperty("email"));
            	//Log.d("FacebookID",user.getId());
               }
            }
       });*/
        return view;
    }
   

    class ProgressTask extends AsyncTask<String, Integer, String> {

        private ProgressDialog progressDialog;
		private String countryID;
		
		

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
        	 try {
        		 Log.d("idddd", id);
        		 Log.d("emailll", email);
    				json = userFunction.Login_facebook(id,email);
    				Log.d("heee7", json);
    				jObj = new JSONObject(json);
    				if(jObj.isNull("user_id")){
    	                   Flag=true;
    				}
    				else{  
    			//  SharedPreferences settings = getActivity().getSharedPreferences("LoginDetails", 0);
    			  	String user_id=	jObj.getString("user_id");
    				String User_token=	jObj.getString("user_token");
    				String UserName=	jObj.getString("user_name");
    				String User_grade_id=	jObj.getString("user_grade_id");
    				String User_Thumb=	jObj.getString("user_thumb"); 
    				countryID=jObj.getString("user_country_id");
    				json4=userFunction.User_details(user_id);
    				jObj4=new JSONObject(json4);
    				String user_type=jObj4.getString("type");
    				if(!user_type.equals("ÿ«·»")){
	   					User_grade_id="0";
	   				}
    				//settings.edit().putString("name", email).putString("facebookID", id).putString("TheUserID", user_id).putString("TheUserName", UserName).putString("TheUserThumb", User_Thumb).putString("TheUserGrade", User_grade_id).putString("TheUserToken", User_token).putString("userType", user_type).commit();
    				 Intent in=new Intent(getActivity(),CopyOfMainActivity.class);
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
     	  
        	  
        	  getActivity().runOnUiThread(new Runnable() {
        		 
          	    @Override
          	    public void run() {
          	    	 if(Flag==true){
     	            	
     	            	Toast.makeText(getActivity(), "wrong Facebook EMail , it is not registered Please register", Toast.LENGTH_LONG).show();
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
    
}