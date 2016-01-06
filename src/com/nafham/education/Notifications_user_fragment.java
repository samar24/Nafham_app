package com.nafham.education;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import com.nafham.education.R;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Notifications_user_fragment extends Fragment {
	ArrayList <String>Notifications=new ArrayList <String>();
	ListView Notificationss;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		  Notifications=getArguments().getStringArrayList("notificationsList");  
	    	 Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	    	 Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	    	  View rootView = inflater.inflate(R.layout.notifications_user_alerts_list, container, false);
	    	
	    	  Notificationss=(ListView)rootView.findViewById(R.id.Notifications_alerts_list);
	    	  Notifications=getArguments().getStringArrayList("notificationsList");
	    	  ADAPTER adabter= new ADAPTER(getActivity(),R.layout.list_item_of_notifications,Notifications);
	    	  Notificationss.setAdapter(adabter);
	    	  Notificationss.setOnItemClickListener(new OnItemClickListener() {

	  				@Override
	  				public void onItemClick(AdapterView<?> parent, View view,
	  						int position, long id) {
	  					  
	  				}
	  				
	  			});
	        return rootView;
	    }
	 public class ADAPTER extends ArrayAdapter<String> {

	  	   Context mContext;
	  	   int layoutResourceId;
	  	   ArrayList<String> data;

	  	    /*
	  	     * @mContext - app context
	  	     * 
	  	     * @layoutResourceId - the listview_item_row.xml
	  	     * 
	  	     * @data - the ListItem data
	  	     */
	  	    public ADAPTER(Context mContext, int layoutResourceId, ArrayList<String>data) {

	  	        super(mContext, layoutResourceId, data);
	  	        this.layoutResourceId = layoutResourceId;
	  	        this.mContext = mContext;
	  	        this.data = data;
	  	    }

	  	    /*
	  	     * @We'll overried the getView method which is called for every ListItem we
	  	     * have.
	  	     * 
	  	     * @There are lots of different caching techniques for Android ListView to
	  	     * achieve better performace especially if you are going to have a very long
	  	     * ListView.
	  	     */
	  	  @Override
		    public View getView(int position, View convertView, ViewGroup parent) {
		    	
		        View listItem = convertView;
		       Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
		    	 Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
		        // inflate the listview_item_row.xml parent
		      LayoutInflater vi;
		        vi = LayoutInflater.from(getContext());
		        listItem = vi.inflate(layoutResourceId, parent, false);
			 TextView The_notification=(TextView)listItem
			          .findViewById(R.id.theNotification);
			 The_notification.setTypeface(typeface2);
			 The_notification.setText(data.get(position));
		        return listItem;
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
