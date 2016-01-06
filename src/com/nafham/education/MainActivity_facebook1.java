package com.nafham.education;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class MainActivity_facebook1 extends FragmentActivity {
	private MainFragment1 mainFragment;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      /*  setContentView(R.layout.main);
	        final TextView welcome = (TextView) findViewById(R.id.userInfoTextView);
	        Session.isPublishPermission("email");
	        Session.openActiveSession(this, true, new Session.StatusCallback() {

	            // callback when session changes state

			@SuppressWarnings("deprecation")
			@Override
			public void call(Session session, SessionState state,
					Exception exception) {
				// TODO Auto-generated method stub
				  if (session.isOpened()) {

		                // make request to the /me API
		                Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

		                  // callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							// TODO Auto-generated method stub 
							 if (user != null) {
			                      
			                     // welcome.setText("Hello " + user.getName() + "!");

			                      welcome.setText(user.getName() + ","
			                              + user.getUsername() + ","
			                              + user.getId() + "," + user.getLink()
			                              + "," + user.getFirstName()+ (String)response.getGraphObject().getProperty("email"));

			                    }
						}
		                });
		              }
				  else{
					  welcome.setText("waaa2 :(");
				  }
			}

	          });*/
	    /*    if (savedInstanceState == null) {
	        	// Add the fragment on initial activity setup
	        	mainFragment = new MainFragment1();
	            getSupportFragmentManager()
	            .beginTransaction()
	            .add(android.R.id.content, mainFragment)
	            .commit();
	        } else {
	        	// Or set the fragment from restored state info
	        	mainFragment = (MainFragment1) getSupportFragmentManager()
	        	.findFragmentById(android.R.id.content);
	        }*/
	    }
	   
	  @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
		  super.onActivityResult(requestCode, resultCode, data);
        //  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	  }

}
