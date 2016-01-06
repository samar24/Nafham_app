package com.nafham.education;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Character.UnicodeBlock;
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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.facebook.widget.UserSettingsFragment;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.nafham.education.Browsing.LoadGrades;
import com.nafham.education.Browsing.MyCustomAdapter;
import com.nafham.education.Browsing.MyCustomAdapter1;
import com.nafham.education.HomeFragment.ADAPTER;
import com.nafham.education.HomeFragment.LoadImage;
import com.nafham.education.HomeFragment.LoadImage1;
import com.nafham.education.HomeFragment.ProgressTask;
import com.nafham.education.Scond_fragment_reg.LoadCountryMethdology;


public class Frag_reg_studen_teach extends Fragment {
   Spinner Stage;
   Spinner Grade;
   Spinner Speacility;
   String school;
   EditText Scool;
   String Email;
   String Pw;
   String UN;
   String FinalName="";
   String SelectedGrade="";
	 String SelectedGrade_id="";
	 
   UserFunctions userFunction=new UserFunctions();
   ArrayList <String> Stagee=new ArrayList<String>();
	 ArrayList <String> Stage_id=new ArrayList<String>();
	 ArrayList <String> Specaility=new ArrayList <String>();
	    ArrayList  <String>Specaility_id=new ArrayList<String>();
	    ArrayList  <String>Gradee=new ArrayList<String>();
	    ArrayList  <String>Gradee_id=new ArrayList<String>();
	  
		private JSONArray jsonArray;
		private String FB_iD;
		private String UserType;
		private String UserType_id;
		private String CountryM;
		private String CountryM_id;
		private String Country;
		private String Country_id;
		private String Governer;
		private String GovernerID;
		private String Gender;
		private String GenderID;
		private String subjects;
		private Button Finish;
		String Telephone;
		String Avatar;
		String TheSchool;
		 String SelectedSpecility;
		 String SelectedSpeciality_id;
		private Typeface typeface1;
		private Typeface typeface2;
    public Frag_reg_studen_teach(){
    	
    	
    }
    public Frag_reg_studen_teach(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM,String CountryM_id,String Country,String Country_id,String Governer,String GovernerID,String Gender,String GenderID,String Subjects,String Avatar,String telephone){
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
		 this.subjects=Subjects;
		 this.Avatar=Avatar;
		 this.Telephone=telephone;
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.student, container, false);
      
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
        
        Stage=(Spinner) rootView.findViewById(R.id.Stages);
        Grade=(Spinner) rootView.findViewById(R.id.spinner1_grades);
        Scool=(EditText) rootView.findViewById(R.id.editText13_School);
        Finish=(Button)rootView.findViewById(R.id.button2);
        Stage.setVisibility(View.GONE);
        Finish.setTypeface(typeface1); 
        Finish.setTextColor(Color.parseColor("#FFFFFF"));
        new ProgressTask().execute();
        return rootView;
    }
    
    class ProgressTask extends AsyncTask<String, Integer, String> {

	    private ProgressDialog progressDialog;
		
		private String json;

		private JSONObject jObj;
		 
		
		@Override
	    protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
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
				if(UserType_id.equals("2")){
					
					
				json = userFunction.Grades(CountryM_id);
				   jObj = new JSONObject(json);
			       jsonArray=jObj.getJSONArray("grades");
				Gradee.add("«·’› «·œ—«”Ï");
				Gradee_id.add("");
				Log.d("al CountryID", json);
				 for (int i = 0; i < jsonArray.length(); i++) {
				     try {
						JSONObject obj1 = jsonArray.getJSONObject(i);
						String usertype_id=obj1.getString("id");
						String UserType=obj1.getString("name");
						Gradee.add(UserType);
						Gradee_id.add(usertype_id);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
				else if (UserType_id.equals("4")){
					
					Stagee.add("«·„—Õ·… «·œ—«”Ì…");
					Stage_id.add("");
					Stagee.add("«·„—Õ·… «·«» œ«∆Ì…");
					Stage_id.add("1");
					Stagee.add("«·„—Õ·… «·«⁄œ«œÌ…");
					Stage_id.add("2");
					Stagee.add("«·„—Õ·… «·À«‰ÊÌ…");
					Stage_id.add("3");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 getActivity().runOnUiThread(new Runnable() {
				
					
				 private MyCustomAdapter adapter1;

				@Override
		      	    public void run() {
					if(UserType_id.equals("2")){
					 Grade.setVisibility(View.VISIBLE);
	      	    	 Stage.setVisibility(View.GONE);
	               
	                 adapter1=new MyCustomAdapter(getActivity(),Gradee);
	                
	                 Grade.setAdapter(adapter1);
	                 Grade.setEnabled(true);
	                
	                 Grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							if(arg0.getItemAtPosition(arg2).toString().equals("«·’› «·œ—«”Ï")){       
							}
							else{
								SelectedGrade=Gradee.get(arg2);
								SelectedGrade_id=Gradee_id.get(arg2);
							school=	Scool.getText().toString();
							Finish.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									new Register().execute();
								}
							});
							}
						}

						@Override
						public void onNothingSelected(
								AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
	                	 
	                  
	                 });
					}
					else if (UserType_id.equals("4")){
						
						 Grade.setVisibility(View.VISIBLE);
		      	    	 Stage.setVisibility(View.GONE);
		               
		                 adapter1=new MyCustomAdapter(getActivity(),Stagee);
		                
		                 Grade.setAdapter(adapter1);
		                 Grade.setEnabled(true);
		                
		                 Grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								if(arg0.getItemAtPosition(arg2).toString().equals("«·„—Õ·… «·œ—«”Ì…")){       
								}
								else{
									SelectedGrade=Stagee.get(arg2);
									SelectedGrade_id=Stage_id.get(arg2);
									
									new LoadSpecility().execute();
								}
							}

							@Override
							public void onNothingSelected(
									AdapterView<?> arg0) {
								// TODO Auto-generated method stub
								
							}
		                	 
		                  
		                 });
					}
				 }
		      	});
			
			return null;
		}
		
		}
		
 
    
    @Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(getActivity()).activityStart(getActivity());
	}
 
	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(getActivity()).activityStop(getActivity());
	} 
	class LoadSpecility extends AsyncTask<Object, Void, String>{
  	  
		
	    
		private JSONObject jObj;
		
		private String json;
	        public LoadSpecility() {
	        }

	       
	    @Override
	    protected void onPostExecute(String result) {
	     
	    }

		@Override
		protected String doInBackground(Object... params) {
			// TODO Auto-generated method stub
			try {
				json = userFunction.Specailities();
				Specaility.add("√· Œ’’");
				Specaility_id.add("");
				 jsonArray=new JSONArray(json);
				 for (int i = 0; i < jsonArray.length(); i++) {
				     try {
						JSONObject obj1 = jsonArray.getJSONObject(i);
						String usertype_id=obj1.getString("id");
						String UserType=obj1.getString("name");
						
						
						
						Specaility.add(UserType);
						Specaility_id.add(usertype_id);
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
				
					
				 private MyCustomAdapter adapter1;

				@Override
		      	    public void run() {
					 Grade.setVisibility(View.VISIBLE);
	      	    	 Stage.setVisibility(View.VISIBLE);
	               
	                 adapter1=new MyCustomAdapter(getActivity(),Specaility);
	                
	                 Stage.setAdapter(adapter1);
	                 Stage.setEnabled(true);
	                
	                 Stage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							if(arg0.getItemAtPosition(arg2).toString().equals("√· Œ’’")){       
							}
							else{
								SelectedSpecility=Specaility.get(arg2);
								SelectedSpeciality_id=Specaility_id.get(arg2);
								school=Scool.getText().toString();
						Finish.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								new Register().execute();
							}
						});
							}
						}

						@Override
						public void onNothingSelected(
								AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
	                	 
	                  
	                 });
				 }
		      	});
			
			return null;
		}

		

	}
	class MyCustomAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> list;
        private int defaultPosition;

        public int getDefaultPosition() {
            return defaultPosition;
        }

        public MyCustomAdapter(Context context, ArrayList<String> objects) {
            super(context, 0, objects);
            this.context = context;
            list = objects;
        }

        public void setDefaultPostion(int position) {
            this.defaultPosition = position;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustom(position, convertView, parent);
        }

        public View getCustom(int position, View convertView, ViewGroup parent) {

            View row = LayoutInflater.from(context).inflate(
                   R.layout.spineer_item, parent, false);
            TextView label = (TextView) row.findViewById(android.R.id.text1);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
            label.setTypeface(tf);
            label.setText(list.get(position));

            return row;
        }

        public View getCustomView(int position, View convertView,
                ViewGroup parent) {

            View row = LayoutInflater.from(context).inflate(
                  R.layout.spineer_item, parent, false);
            TextView label = (TextView) row.findViewById(android.R.id.text1);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
            label.setTypeface(tf);
            label.setText(list.get(position));

            return row;
        }
    }
    class MyCustomAdapter1 extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> list;
        private int defaultPosition;

        public int getDefaultPosition() {
            return defaultPosition;
        }

        public MyCustomAdapter1(Context context, ArrayList<String> objects) {
            super(context, 0, objects);
            this.context = context;
            list = objects;
        }

        public void setDefaultPostion(int position) {
            this.defaultPosition = position;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustom(position, convertView, parent);
        }

        public View getCustom(int position, View convertView, ViewGroup parent) {

            View row = LayoutInflater.from(context).inflate(
                   R.layout.spineer_item, parent, false);
            TextView label = (TextView) row.findViewById(android.R.id.text1);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
            label.setTypeface(tf);
            label.setText(list.get(position));

	 label.setTextColor(Color.parseColor("#E5E4E2"));
            return row;
        }

        public View getCustomView(int position, View convertView,
                ViewGroup parent) {

            View row = LayoutInflater.from(context).inflate(
                  R.layout.spineer_item, parent, false);
            TextView label = (TextView) row.findViewById(android.R.id.text1);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
            label.setTypeface(tf);
            label.setText(list.get(position));

            return row;
        }
    }
class Register extends AsyncTask<Object, Void, String>{
  	  
		
	    
		private JSONObject jObj;
		
		private String json;

		private String Final_user_id;

		private String Final_user_Stage;

		private String Final_user_StageName;

		private String Final_user_Thumb;

		private String Final_user_Type;
	        public Register() {
	        }

	       
	    @Override
	    protected void onPostExecute(String result) {
	     
	    }

		@Override
		protected String doInBackground(Object... params) {
			// TODO Auto-generated method stub
			try {
				Log.d("nerd", Email+UN+Pw+UserType+UserType_id+CountryM+CountryM_id+Country+Country+ Country_id+ Governer+ GovernerID+Gender+ GenderID);
				
	json = userFunction.Register(Email, UN, Pw, FB_iD, UserType, UserType_id, CountryM, CountryM_id, Country, Country_id, Governer, GovernerID, Gender, GenderID, subjects, SelectedGrade, SelectedGrade_id, school, "", "","" , "", Telephone, Avatar);
	 Log.d("here11", json);
			JSONObject obj1=new JSONObject(json);
			 FinalName=obj1.getString("name");
			 Final_user_id=obj1.getString("id");
			 Final_user_Stage=obj1.getString("stage");
			 Final_user_StageName=obj1.getString("stage_name");
			 Final_user_Thumb=obj1.getString("thumb");
			 Final_user_Type=obj1.getString("type");
			 Intent in=new Intent(getActivity(),CopyOfMainActivity.class);
             in.putExtra("TheUserID", Final_user_id);
             in.putExtra("TheUserName",FinalName );
             in.putExtra("TheUserThumb",Final_user_Thumb );
             in.putExtra("TheUserGrade",Final_user_Stage);
             in.putExtra("TheUserToken", "");
             in.putExtra("user_TYPE", Final_user_Type);
             in.putExtra("CountryID", "CountryM_id");
             in.putExtra("Competition","0");
             in.putExtra("link","");
             startActivity(in);
			 Log.d("here!", json);
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
			// Toast.makeText(getActivity(), "welcome "+FinalName, Toast.LENGTH_LONG).show();
				 }
		      	});
			
			return null;
		}

		

	}
}