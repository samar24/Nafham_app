package com.nafham.education;

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

import android.R.drawable;
import android.app.Fragment;
import android.app.LauncherActivity.ListItem;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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

public class ThirdFrag extends Fragment   {
	ArrayList <String> SubjectsNames;
	ListView ls;
	 int counter;
	private JSONObject jObj;
	ArrayList  <String> ID;
	String selectedSubjects="";
	 UserFunctions userFunction = new UserFunctions();
     String json;
     int flag=0;
     int flag1=0;
     ArrayList<Integer> selectedIds;
	 JSONArray jsonArray;
	 String Email;
	 String UN;
	 String Pw;
	 String FB_iD;
	 String UserType;
	 String UserType_id;
	 String CountryM;
	 String CountryM_id;
	 String Country;
	 String Country_id;
	 String Governer;
	 String GovernerID;
	 String Gender;
	 String GenderID;
	 String Avatar;
	 String Telephone;
	private Button Btn_next;
	private Typeface typeface1;
	private Typeface typeface2;
	private TextView VV;
	
	public ThirdFrag(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM,String CountryM_id,String Country,String Country_id,String Governer,String GovernerID,String Gender,String GenderID,String Avatar,String Telephone){
		 this. Email=Email;
		 this. UN=Usrrname;
		 this. Pw=Password;
		 this. FB_iD=FB_id;
		 this. UserType=UserType;
		 this. UserType_id=UserTypeID;
		 this. CountryM=CountryM;
		 this .CountryM_id=CountryM_id;
		 this. Country=Country;
		 this. Country_id=Country_id;
		 this .Governer=Governer;
		 this .GovernerID=GovernerID;
		 this. Gender=Gender;
		 this .GenderID=GenderID;
		 this.Avatar=Avatar;
		 this.Telephone=Telephone;
	}
public ThirdFrag(){
    	
    	
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reg_three, container, false);
       
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        SubjectsNames=new ArrayList<String>();
        ID=new ArrayList<String>();
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
        
        ls=(ListView) rootView.findViewById(R.id.registeration_elemnts);
        Btn_next=(Button) rootView.findViewById(R.id.button1);
        Btn_next.setTypeface(typeface1); 
        Btn_next.setTextColor(Color.parseColor("#FFFFFF"));
        Btn_next.setVisibility(View.GONE);
        VV=(TextView) rootView.findViewById(R.id.textView3);
        VV.setVisibility(View.GONE);
        VV.setTypeface(typeface1);
        new ProgressTask().execute();
        return rootView;
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
	 	    public View getView(final int position, View convertView, ViewGroup parent) {
	 	    	
	 	        
	 	       Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	 	      Typeface    typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	 	        // inflate the listview_item_row.xml parent
	 	      if(convertView==null){
	 	      LayoutInflater vi;
	 	        vi = LayoutInflater.from(getContext());
	 	       convertView = vi.inflate(layoutResourceId, parent, false);

	 	      }
	 	      TextView SubjectName = (TextView) convertView
	 		         .findViewById(R.id.tvContent);

	 	     final RelativeLayout	  LR=(RelativeLayout)convertView
			          .findViewById(R.id.rlInner);
	 		SubjectName.setTypeface(typeface1);
	 	SubjectName.setText(data.get(position));
	 	
	 	/*  LR.setOnClickListener(new View.OnClickListener() {
	 			@Override
	             public void onClick(View v) {
	                 // TODO Auto-generated method stub
	             	
	             	if(flag==0){
	             		LR.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.another_background));
	             		flag=1;
	             }
	             	else{
	             		LR.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.rounded_edge_profile));
	             		flag=0;
	             	}
	             	selectedSubjects+=ID.get(position)+",";
	             	Log.d("subjecthh", selectedSubjects);
	             }
	         });

	 	*/
	 	if (selectedIds.get(position)==1)
	 		LR.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.another_background));
 		
	 	else
	 		LR.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.rounded_edge_profile));
 		
	 		return convertView;
	 	    }
	 	   public void toggleSelected(int position) {
	 		   if(selectedIds.get(position) == 0){
	 			  selectedIds.set(position,1);
	 		   }
	 		   else  if(selectedIds.get(position) == 1){
	 			  selectedIds.set(position,0);
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
         //   progressDialog.setIcon(R.drawable.preloader);
            progressDialog .show();
        }

        @Override
        protected String doInBackground(String... params) {
        	try {
    			json = userFunction.Specailities();
    			
    			 jsonArray=new JSONArray(json);
    			 for (int i = 0; i < jsonArray.length(); i++) {
    			     try {
    					JSONObject obj1 = jsonArray.getJSONObject(i);
    					String usertype_id=obj1.getString("id");
    					String UserType=obj1.getString("name");
    					
    					
    					
    					SubjectsNames.add(UserType);
    					ID.add(usertype_id);
    				} catch (JSONException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
              getActivity().runOnUiThread(new Runnable() {

            	     ADAPTER adabter;
					

					@Override
            	    public void run() {
            	    	  Btn_next.setVisibility(View.VISIBLE);
            	    	  VV.setVisibility(View.VISIBLE);
            	    	 selectedIds = new ArrayList<Integer>();
            	    	  int length = ID.size();
            	    	  for(int i = 0; i < length; i++){
            	    	      selectedIds.add(0);             
            	    	  }
            	    	  adabter= new ADAPTER(getActivity(),R.layout.xml_row_matriails,SubjectsNames);
            	    	 ls.setAdapter(adabter);
            	      
            	       		 ls.setOnItemClickListener(new OnItemClickListener() {
            	       				@Override
            	       				public void onItemClick(AdapterView<?> parent, View view,
            	       						int position, long id) {
            	       					adabter.toggleSelected(position);
            	       					adabter.notifyDataSetChanged();  
            	       				}
            	       				
            	       			});
            	       		Btn_next.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									Log.d("USERTYPE", UserType_id);
									  Registeration mainAct = (Registeration)getActivity();
									  if(UserType_id.equals("2")||UserType_id.equals("4")){
			 	       		          mainAct.changeFragment2(Email,UN,Pw,FB_iD,UserType,UserType_id,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,selectedSubjects,Avatar,Telephone);
			 	       		          }
									  if(UserType_id.equals("5")){
										
				 	       		          mainAct.changeFragment3(Email,UN,Pw,FB_iD,UserType,UserType_id,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,selectedSubjects,Avatar,Telephone);
				 	       		          }
									  if(UserType_id.equals("3")){
				 	       		          mainAct.changeFragment4(Email,UN,Pw,FB_iD,UserType,UserType_id,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,selectedSubjects,Avatar,Telephone);
				 	       		          }
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
           
            progressDialog.dismiss();
        }
        
    }
	
	
}