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
import com.nafham.education.Frag_reg_studen_teach.Register;
import com.nafham.education.HomeFragment.ADAPTER;
import com.nafham.education.HomeFragment.LoadImage;
import com.nafham.education.HomeFragment.LoadImage1;
import com.nafham.education.HomeFragment.ProgressTask;

public class Parent_son_frag extends Fragment {
	   Spinner Grade ;
	   Spinner country_methdology;
	   String Na;
	   String emial1;
	   EditText Name;
	   EditText Emaill;
	   TextView TVY;
	   Button Skip;
	   Button Finish;
	   Button AddAnotherSon;
	   String Email;
	   String Pw;
	   String UN;
	   String FinalName;
	   String SelectedGrade="";
		 String SelectedGrade_id="";
		 String Son_email="";
		 String Son_name="";
	String AVatar;
	   String SelectedCountrym="";
	   String SelectedCountrym_id="";
	   JSONArray JsoonArrayData ;
	   ArrayList <String> Grade1=new ArrayList<String>();
		 ArrayList <String> Grade_id=new ArrayList<String>();
		 ArrayList <String> Count_methd=new ArrayList <String>();
		    ArrayList  <String>Count_methd_id=new ArrayList<String>();
		    UserFunctions userFunction=new UserFunctions();
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
			String telephone;
			String FinalJSON="";
			JSONObject FinalObject;
			private Typeface typeface1;
			private Typeface typeface2;
	     public Parent_son_frag() {
			// TODO Auto-generated constructor stub
		}
	    public Parent_son_frag(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM,String CountryM_id,String Country,String Country_id,String Governer,String GovernerID,String Gender,String GenderID,String Subjects,String AVatar,String telephone){
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
			 this.AVatar=AVatar;
			 this.telephone=telephone;
		}

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.son, container, false);
	        JsoonArrayData=new JSONArray();
	        typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	  	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	      
	        Grade=(Spinner) rootView.findViewById(R.id.spinner1_saf);
	        country_methdology=(Spinner) rootView.findViewById(R.id.spinner13_mnhg_dwla);
	        TVY=(TextView)rootView.findViewById(R.id.textView3);
	        Name=(EditText) rootView.findViewById(R.id.editText13_name);
	        Emaill=(EditText) rootView.findViewById(R.id.editText13_email);
	        AddAnotherSon=(Button) rootView.findViewById(R.id.button1_addANother);
	        Skip=(Button) rootView.findViewById(R.id.button2_Skip);
	        AddAnotherSon.setTypeface(typeface1); 
	        AddAnotherSon.setTextColor(Color.parseColor("#FFFFFF"));
	        Skip.setTypeface(typeface1); 
	        Skip.setTextColor(Color.parseColor("#FFFFFF"));
	        Grade.setVisibility(View.GONE);
	        country_methdology.setVisibility(View.GONE);
	        Emaill.setVisibility(View.GONE);
	        Name.setVisibility(View.GONE);
	        AddAnotherSon.setVisibility(View.GONE);
	        Skip.setVisibility(View.GONE);
	        TVY.setTypeface(typeface1);
	        TVY.setVisibility(View.GONE);
	        new LoadSpecility().execute();
	       
	        return rootView;
	    }
	    class LoadSpecility extends AsyncTask<Object, Void, String>{
	    	  
			
		    
			private JSONObject jObj;
			
			private String json;

			private ProgressDialog progressDialog;
		        public LoadSpecility() {
		        }

		       
		    @Override
		    protected void onPostExecute(String result) {
		    	  super.onPostExecute(result);
			       
			       
			        if(progressDialog!=null)
			        progressDialog.dismiss();
		    }
		    @Override
		    protected void onPreExecute() {
		        // TODO Auto-generated method stub
		        super.onPreExecute();
		        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
		       progressDialog .setMessage(" Õ„Ì·...");
		       progressDialog .setCancelable(false);
		       progressDialog .show();
		      
		       // progressDialog.setContentView(R.layout.loader);
		    }
		   
			@Override
			protected String doInBackground(Object... params) {
				// TODO Auto-generated method stub
				try {
					json = userFunction.Countries();
					Count_methd.add("„‰ÂÃ «·œÊ·…");
					Count_methd_id.add("");
					 jsonArray=new JSONArray(json);
					 for (int i = 0; i < jsonArray.length(); i++) {
					     try {
							JSONObject obj1 = jsonArray.getJSONObject(i);
							String usertype_id=obj1.getString("id");
							String UserType=obj1.getString("name");
							
							
							
							Count_methd.add(UserType);
							Count_methd_id.add(usertype_id);
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
						TVY.setVisibility(View.VISIBLE);
						 country_methdology.setVisibility(View.VISIBLE);
		      	    	 Grade.setVisibility(View.GONE);
		               
		                 adapter1=new MyCustomAdapter(getActivity(),Count_methd);
		                
		                 country_methdology.setAdapter(adapter1);
		                 country_methdology.setEnabled(true);
		                
		                 country_methdology.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								if(arg0.getItemAtPosition(arg2).toString().equals("„‰ÂÃ «·œÊ·…")){       
								}
								else{
									SelectedCountrym=Count_methd.get(arg2);
									SelectedCountrym_id=Count_methd_id.get(arg2);
								new ProgressTask().execute();
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
	    class ProgressTask extends AsyncTask<String, Integer, String> {

		    private ProgressDialog progressDialog;
			
			private String json;

			private JSONObject jObj;
			 
			
			@Override
		    protected void onPreExecute() {
		        // TODO Auto-generated method stub
		        super.onPreExecute();
		        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
		       progressDialog .setMessage(" Õ„Ì·...");
		       progressDialog .setCancelable(false);
		       progressDialog .show();
		      
		       // progressDialog.setContentView(R.layout.loader);
		    }
			
			 @Override
			    protected void onPostExecute(String result) {
			        // TODO Auto-generated method stub
			        super.onPostExecute(result);
			        AddAnotherSon.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
						//	Emaill.setText("");
							//Name.setText("");
							Son_email=Emaill.getText().toString();
							Son_name=Name.getText().toString();
							Toast.makeText(getActivity(), "email w name "+Son_email+Son_name,Toast.LENGTH_LONG).show();
				        	 JSONObject jObjectData = new JSONObject();
							    // Create Json Object using Facebook Data
							    try {
									jObjectData.put("name", Son_name);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    try {
									jObjectData.put("country_id",SelectedCountrym_id);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    try {
									jObjectData.put("section_grade_id", SelectedGrade_id);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    try {
									jObjectData.put("email", Son_email);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    JsoonArrayData.put(jObjectData);
							 //   FinalJSON+=JsoonArrayData.toString();
							 Grade.setVisibility(View.GONE);
						        country_methdology.setVisibility(View.GONE);
						        Emaill.setVisibility(View.GONE);
						        Name.setVisibility(View.GONE);
						        AddAnotherSon.setVisibility(View.GONE);
						        Skip.setVisibility(View.GONE);
							try {
								
								COmbineTheSons();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							   new LoadSpecility().execute();
						}
					});
			       
			        if(progressDialog!=null)
			        progressDialog.dismiss();
			    }
			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				try {
					json = userFunction.Grades(SelectedCountrym_id);
					jObj = new JSONObject(json);
				       jsonArray=jObj.getJSONArray("grades");
					Grade1.add("«·’› «·œ—«”Ï");
					Grade_id.add("");
					// jsonArray=new JSONArray(json);
					 for (int i = 0; i < jsonArray.length(); i++) {
					     try {
							JSONObject obj1 = jsonArray.getJSONObject(i);
							String usertype_id=obj1.getString("id");
							String UserType=obj1.getString("name");
							Grade1.add(UserType);
							Grade_id.add(usertype_id);
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
		      	    	 country_methdology.setVisibility(View.VISIBLE);
		               
		                 adapter1=new MyCustomAdapter(getActivity(),Grade1);
		                
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
										Emaill.setVisibility(View.VISIBLE);
										Name.setVisibility(View.VISIBLE);
										Skip.setVisibility(View.VISIBLE);
										AddAnotherSon.setVisibility(View.VISIBLE);
										SelectedGrade=Grade1.get(arg2);
										SelectedGrade_id=Grade_id.get(arg2);
										
										Skip.setOnClickListener(new View.OnClickListener() {
											
											@Override
											public void onClick(View arg0) {
												// TODO Auto-generated method stub
												Son_email=Emaill.getText().toString();
												Son_name=Name.getText().toString();
												Toast.makeText(getActivity(), "email w name "+Son_email+Son_name,Toast.LENGTH_LONG).show();
									        	 JSONObject jObjectData = new JSONObject();
												    // Create Json Object using Facebook Data
												    try {
														jObjectData.put("name", Son_name);
													} catch (JSONException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												    try {
														jObjectData.put("country_id",SelectedCountrym_id);
													} catch (JSONException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												    try {
														jObjectData.put("section_grade_id", SelectedGrade_id);
													} catch (JSONException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												    try {
														jObjectData.put("email", Son_email);
													} catch (JSONException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												    JsoonArrayData.put(jObjectData);
												    FinalJSON+=JsoonArrayData.toString();
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
			
	 
	    
	    @Override
		public void onStart() {
			super.onStart();
			///EasyTracker.getInstance(getActivity()).activityStart(getActivity());
		}
	 
		@Override
		public void onStop() {
			super.onStop();
			//EasyTracker.getInstance(getActivity()).activityStop(getActivity());
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
	Log.d("nerd", Email+UN+Pw+UserType+UserType_id+CountryM+CountryM_id+Country+Country+ Country_id+ Governer+ GovernerID+Gender+ GenderID+FinalJSON);
		json = userFunction.Register(Email, UN, Pw, FB_iD, UserType, UserType_id, CountryM, CountryM_id, Country, Country_id, Governer, GovernerID, Gender, GenderID, subjects, "", "", "", "", "", "" ,FinalJSON, telephone, AVatar);
		 Log.d("here2!", json);
		JSONObject obj1=new JSONObject(json);
				 FinalName=obj1.getString("name");
				 Final_user_id=obj1.getString("id");
				// Final_user_Stage=obj1.getString("stage");
				// Final_user_StageName=obj1.getString("stage_name");
				 Final_user_Thumb=obj1.getString("thumb");
				 Final_user_Type=obj1.getString("type");
				 Intent in=new Intent(getActivity(),CopyOfMainActivity.class);
	             in.putExtra("TheUserID", Final_user_id);
	             in.putExtra("TheUserName",FinalName );
	             in.putExtra("TheUserThumb",Final_user_Thumb );
	             in.putExtra("TheUserGrade","");
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
						Log.d("sonnn",FinalJSON );
				// Toast.makeText(getActivity(), "welcome "+FinalName, Toast.LENGTH_LONG).show();
					 }
			      	});
				
				return null;
			}
		}
	public void COmbineTheSons() throws JSONException{
		
		Grade1=new ArrayList<String>();
		Count_methd=new ArrayList<String>();
		Count_methd_id=new ArrayList<String>();
		Grade_id=new ArrayList<String>();
		
	    	
	}
}