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
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


import com.google.analytics.tracking.android.EasyTracker;


public class Scond_fragment_reg extends Fragment {
   Spinner Gender;
   Spinner Residence_country;
   Spinner Governer;
   Spinner Country_methdology;
   Spinner UserType;
   EditText Telphone;
   Button Next;
   String Telppone;
   TextView bynatAtsal;
   TextView alTelphone;
   String Email;
   TextView VV;
   String Pw;
   String UN;
   String FacebookID;
   String SelectedUserType;
   String SelectedUserTypeId;
   String Res_C;
   String Res_c_id;
   String Country_m;
   String Country_m_id;
   String Govern;
   String Govern_id;
   String Gende;
   String Gende_id;
   String avatar;
   ArrayList <String> UserTypes;
	 ArrayList <String> UserTypes_id;
	 ArrayList <String> ResidenceCountry;
	    ArrayList  <String>ResidenceCountry_ID;
	    ArrayList  <String>Governerr;
	    ArrayList  <String>Governerr_id;
	    ArrayList  <String>Genderr;
	    ArrayList  <String>Gender_id;
	    ArrayList  <String>Country_methdologyy;
	    ArrayList  <String>Country_methdologyy_id;
	    ArrayList  <ArrayList>GovernersOFtheCountries;
		private JSONArray jsonArray;
		 Typeface typeface1;
		 Typeface typeface2;
		 UserFunctions userFunction=new UserFunctions();
    public Scond_fragment_reg(){
    	
    	
    }
 public Scond_fragment_reg(String email,String username,String Pass,String Fb_id,String Avatar){
    	this.Email=email;
    	this.UN=username;
    	this.Pw=Pass;
    	this.FacebookID=Fb_id;
    	this.avatar=Avatar;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.reg_two, container, false);
       UserTypes=new ArrayList<String>();
   UserTypes_id=new ArrayList<String>();
  ResidenceCountry=new ArrayList <String>();
   	   ResidenceCountry_ID=new ArrayList<String>();
   	  Governerr=new ArrayList<String>();
   	  Governerr_id=new ArrayList<String>();
   	  Genderr=new ArrayList<String>();
   	  Gender_id=new ArrayList<String>();
   	  Country_methdologyy=new ArrayList<String>();
   	  Country_methdologyy_id=new ArrayList<String>();
   	  GovernersOFtheCountries=new ArrayList<ArrayList>();
        typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
  	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
        UserType=(Spinner) rootView.findViewById(R.id.spinner1);
        Country_methdology=(Spinner) rootView.findViewById(R.id.spinner13);
        Gender=(Spinner) rootView.findViewById(R.id.spinner19);
        Residence_country=(Spinner) rootView.findViewById(R.id.spinner133);
        Governer=(Spinner) rootView.findViewById(R.id.spinner13333);
        Telphone=(EditText) rootView.findViewById(R.id.editText13);
        Next=(Button) rootView.findViewById(R.id.button2);
        bynatAtsal=(TextView) rootView.findViewById(R.id.textView1);
        alTelphone=(TextView) rootView.findViewById(R.id.textView2);
        VV=(TextView) rootView.findViewById(R.id.textView3);
        UserType.setVisibility(View.GONE);
        Country_methdology.setVisibility(View.GONE);
        Gender.setVisibility(View.GONE);
        Residence_country.setVisibility(View.GONE);
        Governer.setVisibility(View.GONE);
        bynatAtsal.setVisibility(View.GONE);
       alTelphone.setVisibility(View.GONE);
       Telphone.setVisibility(View.GONE);
        Next.setVisibility(View.GONE);
        VV.setVisibility(View.GONE);
        VV.setTypeface(typeface1);
        Next.setTypeface(typeface1); 
        Next.setTextColor(Color.parseColor("#FFFFFF"));
        bynatAtsal.setTypeface(typeface1); 
        alTelphone.setTypeface(typeface1); 
        Telphone.setTypeface(typeface1); 
       
        new ProgressTask().execute();
        return rootView;
    }
    
    class ProgressTask extends AsyncTask<String, Integer, String> {

	    private ProgressDialog progressDialog;
		
		private String json;
		 
		
		@Override
	    protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
	       progressDialog .setMessage(" Õ„Ì·...");
	       progressDialog .setCancelable(false);
	       progressDialog .show();
	       UserTypes=new ArrayList<String>();
	       UserTypes_id=new ArrayList<String>();
	       UserTypes.add("‰Ê⁄ «·„” Œœ„");
	      
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
				json = userFunction.UserTYPES();
			
				UserTypes_id.add("");
				 jsonArray=new JSONArray(json);
				 for (int i = 0; i < jsonArray.length(); i++) {
				     try {
						JSONObject obj1 = jsonArray.getJSONObject(i);
						String usertype_id=obj1.getString("id");
						String UserType=obj1.getString("name");
						
						
						
						UserTypes.add(UserType);
						UserTypes_id.add(usertype_id);
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
					private MyCustomAdapter1 adapter3;
					
					private MyCustomAdapter1 adapter5;
					private MyCustomAdapter1 adapter6;
					private MyCustomAdapter1 adapter7;
				 @Override
		      	    public void run() {
					UserType.setVisibility(View.VISIBLE);
	      	     Country_methdology.setVisibility(View.VISIBLE);
	                Gender.setVisibility(View.VISIBLE); 
	               Residence_country.setVisibility(View.VISIBLE);
	                Governer.setVisibility(View.VISIBLE);
	                bynatAtsal.setVisibility(View.VISIBLE);
	                alTelphone.setVisibility(View.VISIBLE);
	                Telphone.setVisibility(View.VISIBLE);
	                VV.setVisibility(View.VISIBLE);
	                 adapter1=new MyCustomAdapter(getActivity(), UserTypes);
	                 adapter3=new MyCustomAdapter1(getActivity(), Country_methdologyy);
	                 adapter5=new MyCustomAdapter1(getActivity(), Genderr);
	                 adapter6=new MyCustomAdapter1(getActivity(), ResidenceCountry);
	                 adapter7=new MyCustomAdapter1(getActivity(), Governerr);
		             UserType.setAdapter(adapter1);
	                 Country_methdology.setEnabled(false);
	                 Gender.setEnabled(false);
	                 Residence_country.setEnabled(false);
	                 Governer.setEnabled(false);
	                 Country_methdology.setAdapter(adapter3);
	                 Gender.setAdapter(adapter5);
	                 Residence_country.setAdapter(adapter6);
	                 Governer.setAdapter(adapter7);
	                 UserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							if(arg0.getItemAtPosition(arg2).toString().equals("‰Ê⁄ «·„” Œœ„")){
								
								Country_methdology.setEnabled(false);
				                 Gender.setEnabled(false);
				                 Residence_country.setEnabled(false);
				                 Governer.setEnabled(false);
				                 Country_methdology.setAdapter(adapter3);
				                 Gender.setAdapter(adapter5);
				                 Residence_country.setAdapter(adapter6);
				                 Governer.setAdapter(adapter7);
							}
							else{
								SelectedUserType=UserTypes.get(arg2);
								SelectedUserTypeId=UserTypes_id.get(arg2);
							new	LoadCountryMethdology().execute();
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
		EasyTracker.getInstance(getActivity()).activityStart(getActivity());
	}
 
	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(getActivity()).activityStop(getActivity());
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
	    class LoadCountryMethdology extends AsyncTask<Object, Void, String>{
	    	  
			
	    
		private JSONObject jObj;
		
		private String json;

		 ProgressDialog progressDialog;
	        public LoadCountryMethdology() {
	        }
	        @Override
		    protected void onPreExecute() {
		        // TODO Auto-generated method stub
	        super.onPreExecute();
	        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
	       progressDialog .setMessage("Loading...");
	       progressDialog .setCancelable(false);
	       progressDialog .show();
	     
       	  Country_methdologyy=new ArrayList<String>();
       	  Country_methdologyy_id=new ArrayList<String>();
       	  Country_methdologyy.add("„‰ÂÃ «·œÊ·…");
	    	
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
		protected String doInBackground(Object... params) {
			// TODO Auto-generated method stub
			try {
				json = userFunction.Countries();
				
				Country_methdologyy_id.add("");
				 jsonArray=new JSONArray(json);
				 for (int i = 0; i < jsonArray.length(); i++) {
				     try {
						JSONObject obj1 = jsonArray.getJSONObject(i);
						String usertype_id=obj1.getString("id");
						String UserType=obj1.getString("name");
						
						
						
						Country_methdologyy.add(UserType);
						Country_methdologyy_id.add(usertype_id);
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
				
					
					private MyCustomAdapter1 adapter4;
					private MyCustomAdapter adapter9;
					private MyCustomAdapter1 adapter5;
					private MyCustomAdapter1 adapter7;
					
				 @Override
		      	    public void run() {
					
	                 adapter9=new MyCustomAdapter(getActivity(), Country_methdologyy);
	                 adapter4=new MyCustomAdapter1(getActivity(), Genderr);
	                 adapter5=new MyCustomAdapter1(getActivity(), ResidenceCountry);
	                 adapter7=new MyCustomAdapter1(getActivity(), Governerr);
		             Country_methdology.setAdapter(adapter9);
		             Country_methdology.setEnabled(true);
	                 Residence_country.setEnabled(false);
	                 Governer.setEnabled(false);
	                 Gender.setEnabled(false);
	                 Gender.setAdapter(adapter4);
	                 Residence_country.setAdapter(adapter5);
	                 Governer.setAdapter(adapter7);
	                 Country_methdology.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							if(arg0.getItemAtPosition(arg2).toString().equals("„‰ÂÃ «·œÊ·…")){
								
								
				                 Gender.setEnabled(false);
				                 Gender.setAdapter(adapter4);
				                 Residence_country.setEnabled(false);
				                 Governer.setEnabled(false);
				                 Residence_country.setAdapter(adapter5);
				                 Governer.setAdapter(adapter7);
							}
							
							else{
						new	LoadGenederss().execute(); /// hna Create spinner for Genderes
						Country_m=Country_methdologyy.get(arg2);
						Country_m_id=Country_methdologyy_id.get(arg2);
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
	    class LoadGenederss extends AsyncTask<Object, Void, String>{
	    	  
			
		    
			private JSONObject jObj;
			
			private String json;

			 ProgressDialog progressDialog;
		        public LoadGenederss() {
		        }

		       
		        protected void onPreExecute() {
			        // TODO Auto-generated method stub
		        super.onPreExecute();
		        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
		       progressDialog .setMessage(" Õ„Ì·...");
		       progressDialog .setCancelable(false);
		       progressDialog .show();
		      
	       	  Genderr=new ArrayList<String>();
	       	  Gender_id=new ArrayList<String>();
	       	Genderr.add("«·‰Ê⁄");
	    
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
			protected String doInBackground(Object... params) {
				// TODO Auto-generated method stub
				try {
					json = userFunction.Genders();
				
					Gender_id.add("");
					 jsonArray=new JSONArray(json);
					 for (int i = 0; i < jsonArray.length(); i++) {
					     try {
							JSONObject obj1 = jsonArray.getJSONObject(i);
							String usertype_id=obj1.getString("id");
							String UserType=obj1.getString("name");
							
							
							
						 Genderr.add(UserType);
							Gender_id.add(usertype_id);
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
					
						private MyCustomAdapter adapter4;
					
						private MyCustomAdapter1 adapter5;
						private MyCustomAdapter1 adapter7;
						
					 @Override
			      	    public void run() {
						UserType.setVisibility(View.VISIBLE);
		      	    	 Country_methdology.setVisibility(View.VISIBLE);
		                 Gender.setVisibility(View.VISIBLE); 
		                // Residence_country.setVisibility(View.VISIBLE); 
		                // Governer.setVisibility(View.VISIBLE); 
		                 
		               
		                 adapter4=new MyCustomAdapter(getActivity(), Genderr);
		                 adapter5=new MyCustomAdapter1(getActivity(), ResidenceCountry);
		                 adapter7=new MyCustomAdapter1(getActivity(), Governerr);
			            
		                 Residence_country.setEnabled(false);
		                 Governer.setEnabled(false);
		                 Gender.setEnabled(true);
		                 Gender.setAdapter(adapter4);
		                 Residence_country.setAdapter(adapter5);
		                 Governer.setAdapter(adapter7);
		                 Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								if(arg0.getItemAtPosition(arg2).toString().equals("«·‰Ê⁄")){
									
									
					                
					                 Residence_country.setEnabled(false);
					                 Governer.setEnabled(false);
					                 Residence_country.setAdapter(adapter5);
					                 Governer.setAdapter(adapter7);
								}
								
								else{
							new	LoadResidences().execute(); /// hna Create spinner for Genderes
							Gende=Genderr.get(arg2);
							Gende_id=Gender_id.get(arg2);
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
class LoadResidences extends AsyncTask<Object, Void, String>{
	    	  
			
		    
			private JSONObject jObj;
		
			private String json;

			 ProgressDialog progressDialog;
		        public LoadResidences() {
		        }

		       
		        protected void onPreExecute() {
			        // TODO Auto-generated method stub
		        super.onPreExecute();
		        progressDialog = new ProgressDialog(getActivity(),R.style.NewDialog);
		       progressDialog .setMessage(" Õ„Ì·...");
		       progressDialog .setCancelable(false);
		       progressDialog .show();
		       ResidenceCountry=new ArrayList <String>();
	       	   ResidenceCountry_ID=new ArrayList<String>();
	       	  Governerr=new ArrayList<String>();
	       	  Governerr_id=new ArrayList<String>();
	       	 GovernersOFtheCountries=new ArrayList<ArrayList>();
	     	ResidenceCountry.add("»·œ «·«ﬁ«„…");
	    	   Governerr.add("«·„Õ«›Ÿ…");
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
			protected String doInBackground(Object... params) {
				// TODO Auto-generated method stub
				try {
					json = userFunction.AllCountries();
					
					ResidenceCountry_ID.add("");
					 jsonArray=new JSONArray(json);
					 for (int i = 0; i <jsonArray.length(); i++) {
					     try {
							JSONObject obj1 = jsonArray.getJSONObject(i);
							String usertype_id=obj1.getString("id");
							String UserType=obj1.getString("name");
							//String Governers=obj1.getString("governors");
							
							//JSONArray jsArr=new JSONArray(Governers);
						/*	for(int j= 0; i < 10; j++){
								JSONObject obj2 = jsArr.getJSONObject(j);
							 Govern_id=obj2.getString("name");
								Governerr.add(Govern_id);
							}
							GovernersOFtheCountries.add(Governerr);*/
							ResidenceCountry.add(UserType);
							ResidenceCountry_ID.add(usertype_id);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Log.d("SIZEEE", Integer.toString(ResidenceCountry.size()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 getActivity().runOnUiThread(new Runnable() {
					 
					
						private MyCustomAdapter adapter5;
						private MyCustomAdapter1 adapter7;
						private MyCustomAdapter adapter10;
					 @Override
			      	    public void run() {
						UserType.setVisibility(View.VISIBLE);
		      	    	 Country_methdology.setVisibility(View.VISIBLE);
		                 Gender.setVisibility(View.VISIBLE); 
		                 Residence_country.setVisibility(View.VISIBLE); 
		                 bynatAtsal.setVisibility(View.VISIBLE);
		                 Governer.setVisibility(View.VISIBLE); 
							Telphone.setVisibility(View.VISIBLE);
							alTelphone.setVisibility(View.VISIBLE);
							
		                // Governer.setVisibility(View.VISIBLE); 
		           
		                 adapter5=new MyCustomAdapter(getActivity(), ResidenceCountry);
		                 adapter7=new MyCustomAdapter1(getActivity(), Governerr);
		                 adapter10=new MyCustomAdapter(getActivity(), Governerr);
		                 Residence_country.setEnabled(true);
		                 Governer.setEnabled(false);
		                 Governerr.add("«·ﬁ«Â—…");
		                 Governerr.add("«·ÃÌ“…");
		                 Governerr_id.add("");
		                 Governerr_id.add("«·ﬁ«Â—…");
		                 Governerr_id.add("«·ÃÌ“…");
		                 Residence_country.setAdapter(adapter5);
		                 Governer.setAdapter(adapter7);
		                 Residence_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								if(arg0.getItemAtPosition(arg2).toString().equals("»·œ «·«ﬁ«„…")||!arg0.getItemAtPosition(arg2).toString().equals("„’—")){
									
									
					             
					                 Governer.setEnabled(false);
					                 Governer.setAdapter(adapter7);
					                 Next.setVisibility(View.VISIBLE);
					                 Next.setOnClickListener(new View.OnClickListener() {
											
											@Override
											public void onClick(View arg0) {
												// TODO Auto-generated method 
												 Telppone=Telphone.getText().toString();
												 if(!Telppone.equals("")){
												  Registeration mainAct = (Registeration)getActivity();
						 	       		          mainAct.changeFragment1(Email,UN,Pw,FacebookID,SelectedUserType,SelectedUserTypeId,Country_m,Country_m_id,Res_C,Res_c_id,Govern,Govern_id,Gende,Gende_id,avatar,Telppone);
												 }
											}
										});
								}
								
								else{
									
									  Governer.setEnabled(true);
						                 Governer.setAdapter(adapter10);
						                 Governer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

												@Override
												public void onItemSelected(AdapterView<?> arg0,
														View arg1, int arg2, long arg3) {
													// TODO Auto-generated method stub
													if(arg0.getItemAtPosition(arg2).toString().equals("«·„Õ«›Ÿ…")){
														
													}
													
													else{
														 Govern=Governerr.get(arg2);
														  Govern_id=Governerr_id.get(arg2);
														  Next.setVisibility(View.VISIBLE);
														 
									Next.setOnClickListener(new View.OnClickListener() {
										
										@Override
										public void onClick(View arg0) {
											// TODO Auto-generated method 
											 Telppone=Telphone.getText().toString();
											 if(!Telppone.equals("")){
											  Registeration mainAct = (Registeration)getActivity();
					 	       		          mainAct.changeFragment1(Email,UN,Pw,FacebookID,SelectedUserType,SelectedUserTypeId,Country_m,Country_m_id,Res_C,Res_c_id,Govern,Govern_id,Gende,Gende_id,avatar,Telppone);
											 }
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
							Res_C=ResidenceCountry.get(arg2);
							Res_c_id=ResidenceCountry_ID.get(arg2);
							
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

}