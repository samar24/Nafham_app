package com.nafham.education;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.nafham.education.R;

import android.R.integer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.test.UiThreadTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class Browsing extends Activity   {
ProgressBar BR;
	TextView TV;
	Spinner Grades;
	Spinner semsters;
	Spinner Country;
	TextView Register;
	TextView LOgin;
	TextView theWelcomeText;
	 String SEM_ID="";
	 UserFunctions userFunction = new UserFunctions();
     String json="";
     String json6="";
     String Hegi="";
     Float d;
     TextView TVV;
	 String Content;
	 String BGCOlor="#eaeaea"; 
	 boolean flag1=false;
   String Wedith="";
 String LINK="";
 String Pic="";
String TheSelectedGrade_ID="";
	 ArrayList <String> Grades_array=new ArrayList<String>();
	 ArrayList <String> Grades_array_IDS=new ArrayList<String>();
	 ArrayList <String> Grades_semsCount=new ArrayList <String>();
	    ArrayList  <String>Semsters=new ArrayList<String>();
	    ArrayList  <String>Countries=new ArrayList<String>();
	    ArrayList  <String>Countries_IDs=new ArrayList<String>();
		private JSONArray jsonArray;
		private WebView TheBannerOnActionBar;
		private WebView TheBannerOnActionBar1;
		 Typeface typeface1;
		 Typeface typeface2;
		 @Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_browsing);
				 ConnectivityManager connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		         if (connec != null && (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) ||(connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED)){ 
		             //You are connected, do something online.
		        	// Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_LONG).show();
		         }else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED ) {             
		             //Not connected.        
		       Toast.makeText(getApplicationContext(), "You must be connected to the internet", Toast.LENGTH_LONG).show();
		       this.finish();
		         } 
				//Register=(TextView)findViewById(R.id.textView2);
				//LOgin=(TextView)findViewById(R.id.textView4);
				  typeface1 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
		    	  typeface2 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
				d=(float) getApplicationContext().getResources().getDisplayMetrics().densityDpi;
				//Log.d("AL DENSITYYYYYYYYYYYYYYY", d.toString());
				//Toast.makeText(getApplicationContext(), "yarab "+d.toString(), Toast.LENGTH_LONG).show();
				  TheBannerOnActionBar=(WebView) findViewById(R.id.BrowsingBanner);
				  TheBannerOnActionBar1=(WebView) findViewById(R.id.BrowsingBanner1);
			       TheBannerOnActionBar.setVisibility(View.GONE);
			       TheBannerOnActionBar1.setVisibility(View.GONE);
				Grades=(Spinner)findViewById(R.id.spinner2);
				semsters=(Spinner)findViewById(R.id.spinner3);
				theWelcomeText=(TextView)findViewById(R.id.imageView2);
				Country=(Spinner)findViewById(R.id.spinner1);
				TVV=(TextView)findViewById(R.id.textView1);
				TVV.setVisibility(View.GONE);
				//Register.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
			//	LOgin.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
				theWelcomeText.setTypeface(typeface2);
				theWelcomeText.setText("„—Õ»« »ﬂ ›Ì √ﬂ»— „‰’…  ⁄·Ì„Ì… \n \u0020 \u0020 \u0020  ⁄—»Ì… ·ÿ·«» «·„œ«—”");
				 Grades_array.add("«Œ — «·⁄«„ «·œ—«”Ï");
			      Semsters.add("«Œ — «·›’· «·œ—«”Ï");
		        Semsters.add("«·›’· «·œ—«”Ï «·«Ê·");
				   Semsters.add("«·›’· «·œ—«”Ï «·À«‰Ï");
		           Country.setVisibility(View.GONE);
		           Grades.setVisibility(View.GONE);
		           semsters.setVisibility(View.GONE);
		          new ProgressTask().execute();
		           
		         /*  Register.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in=new Intent(getApplicationContext(),Registeration.class);
						startActivity(in);
					}
				});
		           
		           LOgin.setOnClickListener(new View.OnClickListener() {
		   			
		   			@Override
		   			public void onClick(View v) {
		   				// TODO Auto-generated method stub
		   				Intent in=new Intent(getApplicationContext(),LogInPage.class);
		   				startActivity(in);
		   			}
		   		});*/
			}
		 @Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
			//	getMenuInflater().inflate(R.menu.browsing, menu);
				
				return true;
			}
		 class ProgressTask extends AsyncTask<String, Integer, String> {

			    private ProgressDialog progressDialog;
				 
				
				@Override
			    protected void onPreExecute() {
			        // TODO Auto-generated method stub
			        super.onPreExecute();
			        progressDialog = new ProgressDialog(Browsing.this,R.style.NewDialog);
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
						json = userFunction.Countries();
						Countries.add("«Œ — «·œÊ·…");
						Countries_IDs.add("");
						 jsonArray=new JSONArray(json);
						 for (int i = 0; i < jsonArray.length(); i++) {
						     try {
								JSONObject obj1 = jsonArray.getJSONObject(i);
								String Country_ID=obj1.getString("id");
								String Country_name=obj1.getString("name");
								Log.d("IDSSS", Country_ID);
								if(Country_ID.equals("2")||Country_ID.equals("3")||Country_ID.equals("4")||Country_ID.equals("5")||Country_ID.equals("6")||Country_ID.equals("7")){
									Country_name+=" ( Ã—Ì»Ï) ";
								}
								
								Countries.add(Country_name);
								Countries_IDs.add(Country_ID);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						 Countries.add(" ⁄·Ì„ Õı—");
							Countries_IDs.add("8");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						json6 = userFunction.GetBanner4ActionBar();
						Log.d("3awww", json6);
						
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
						 private MyCustomAdapter1 adapter2;
							private MyCustomAdapter1 adapter3;
							private MyCustomAdapter adapter1;
							private MyCustomAdapter1 adapter4;
							private MyCustomAdapter adapter5;
						 @Override
				      	    public void run() {
							Country.setVisibility(View.VISIBLE);
			      	    	 Grades.setVisibility(View.VISIBLE);
			                 semsters.setVisibility(View.VISIBLE); 
			                 
			                 adapter1=new MyCustomAdapter(getApplicationContext(), Countries);
			                 adapter3=new MyCustomAdapter1(getApplicationContext(), Grades_array);
			                 adapter4=new MyCustomAdapter1(getApplicationContext(), Semsters);
				             Country.setAdapter(adapter1);
			                 semsters.setEnabled(false);
			                 Grades.setEnabled(false);
			                 Grades.setAdapter(adapter3);
			                 semsters.setAdapter(adapter4);
			                 Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> arg0,
										View arg1, int arg2, long arg3) {
									// TODO Auto-generated method stub
									if(arg0.getItemAtPosition(arg2).toString().equals("«Œ — «·œÊ·…")){
										
							                 semsters.setEnabled(false);
							                 Grades.setEnabled(false);
							                 Grades.setAdapter(adapter3);
							                 semsters.setAdapter(adapter4);
									}
									else if (arg0.getItemAtPosition(arg2).toString().equals(" ⁄·Ì„ Õı—")){
										 Intent in=new Intent(getApplicationContext(),CopyOfMainActivity.class);
										  in.putExtra("ThesemsterID","1");
							        		 in.putExtra("TheUserID", "");
							                 in.putExtra("TheUserName", "");
							                 in.putExtra("TheUserThumb","");
							                 in.putExtra("TheUserGrade", "0");
							                 in.putExtra("TheUserToken","");
							                 in.putExtra("user_TYPE","ÿ«·»");
							                 in.putExtra("Competition","0");
							                 in.putExtra("link",LINK);
							                 startActivity(in);
										
										
									}
									else{
									new	LoadGrades(Countries_IDs.get(arg2),arg0.getItemAtPosition(arg2).toString()).execute();
									}
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> arg0) {
									// TODO Auto-generated method stub
									
								}
			                	 
			                	 
			                 });
			//////////////////////////////////
							 if(!Pic.equals("")){
								  TheBannerOnActionBar.setVisibility(View.VISIBLE);
								  TVV.setVisibility(View.VISIBLE);
					          	  TVV.setTypeface(typeface2);
							 }
									 if(!LINK.equals("")){
				          	      
				          	      TheBannerOnActionBar1.setVisibility(View.VISIBLE);
				          	    TVV.setVisibility(View.VISIBLE);
				          	  TVV.setTypeface(typeface2);
				          	        }
								  TheBannerOnActionBar.getSettings().setJavaScriptEnabled(true);
								      TheBannerOnActionBar.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
								      TheBannerOnActionBar.getSettings().setPluginsEnabled (true);
								      TheBannerOnActionBar.getSettings().setSupportMultipleWindows (false);
								   //   TheBannerOnActionBar.getSettings().setSupportZoom (true);
								    //  TheBannerOnActionBar.getSettings().setBuiltInZoomControls(true);
								     TheBannerOnActionBar.getSettings().setUseWideViewPort(false); 
								    //  TheBannerOnActionBar.setVerticalScrollBarEnabled (true); 
								     //  TheBannerOnActionBar.setHorizontalScrollBarEnabled (true);
								     TheBannerOnActionBar.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
								      TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON);
								      TheBannerOnActionBar.getSettings().setAllowFileAccess(true);
								    TheBannerOnActionBar.getSettings().setLoadWithOverviewMode(false);
								    TheBannerOnActionBar.setVerticalScrollBarEnabled(false);
								     TheBannerOnActionBar.setHorizontalScrollBarEnabled(false);
							   	//webView.getSettings().setAllowContentAccess(true);
							   	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
								       TheBannerOnActionBar.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
								       TheBannerOnActionBar.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
								       TheBannerOnActionBar.setBackgroundColor(Color.TRANSPARENT);  
								// LayoutParams vc=TheBannerOnActionBar.getLayoutParams();
								  //      vc.height=Integer.parseInt(Hegi);
								    //   vc.width=Integer.parseInt(Wedith);
								      //TheBannerOnActionBar.setLayoutParams(vc);
								       json6="";
								       if(!Pic.equals("")){
								json6="<html><center><body bgcolor="+BGCOlor +">"+
								
								"<img src="+Pic+" height="+Hegi+"width="+Wedith + ">"+
								
								"</body></center></html>"
								;
							//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
								  TheBannerOnActionBar.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
								       }
								       TheBannerOnActionBar1.getSettings().setJavaScriptEnabled(true);
									      TheBannerOnActionBar1.getSettings().setJavaScriptCanOpenWindowsAutomatically (true);
									      TheBannerOnActionBar1.getSettings().setPluginsEnabled (true);
									      TheBannerOnActionBar1.getSettings().setSupportMultipleWindows (false);
									     // TheBannerOnActionBar1.getSettings().setSupportZoom (true);
									    //  TheBannerOnActionBar1.getSettings().setBuiltInZoomControls(true);
									     TheBannerOnActionBar1.getSettings().setUseWideViewPort(true); 
									    //  TheBannerOnActionBar1.setVerticalScrollBarEnabled (true); 
									     //  TheBannerOnActionBar1.setHorizontalScrollBarEnabled (true);
									     TheBannerOnActionBar1.getSettings().setFixedFontFamily("file:///android_asset/fonts/HelveticaNeueW23-Reg.ttf");
									      TheBannerOnActionBar1.getSettings().setPluginState(WebSettings.PluginState.ON);
									      TheBannerOnActionBar1.getSettings().setAllowFileAccess(true);
									    TheBannerOnActionBar1.getSettings().setLoadWithOverviewMode(true);
								   	//webView.getSettings().setAllowContentAccess(true);
								   	//webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); 
									       TheBannerOnActionBar1.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
									       TheBannerOnActionBar1.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
									       TheBannerOnActionBar1.setBackgroundColor(Color.TRANSPARENT);  
									// LayoutParams vc=TheBannerOnActionBar.getLayoutParams();
									  //      vc.height=Integer.parseInt(Hegi);
									    //   vc.width=Integer.parseInt(Wedith);
									      //TheBannerOnActionBar.setLayoutParams(vc);
									       json6="";
									       if(!LINK.equals("")){
									json6="<html><body bgcolor="+BGCOlor +">"+
									
									"<img src="+LINK+" height="+Hegi+"width="+Wedith +" align="+"left"+ ">"+
									
									"</body></html>"
									;
								//	Log.d ("URLLLLL","AL URL"+Link+"hhh"+Content);
									  TheBannerOnActionBar1.loadDataWithBaseURL(null,json6, "text/html", "UTF-8",null);
									       }
						 }
				      	});
						
					return null;
				}
				
				}
		 @Override
		    public void onBackPressed() {
		         super.onBackPressed();
		                     Intent intent = new Intent(Intent.ACTION_MAIN);
		                       intent.addCategory(Intent.CATEGORY_HOME);
		                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		                       startActivity(intent);
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
		    class LoadGrades extends AsyncTask<Object, Void, String>{
		    	  
				private MyCustomAdapter1 adapter2;
					private MyCustomAdapter adapter3;
					private MyCustomAdapter1 adapter4;
					private MyCustomAdapter adapter5;
		     private  String ID_country;
		     private String name_country;
			private JSONObject jObj;
		        public LoadGrades(String Country_ID,String Country_name) {
		        	 Grades_array=new ArrayList<String>();
		        	 Grades_array_IDS=new ArrayList<String>();
		        	 Grades_semsCount=new ArrayList<String>();
		        	 adapter2=new MyCustomAdapter1(getApplicationContext(), Semsters);
		             adapter3=new MyCustomAdapter(getApplicationContext(), Semsters);
		             adapter4=new MyCustomAdapter1(getApplicationContext(), Grades_array);
		             adapter5=new MyCustomAdapter(getApplicationContext(), Grades_array);
		            this.ID_country=Country_ID;
		            this.name_country=Country_name;
		            Grades_array.add("«Œ — «·⁄«„ «·œ—«”Ï");
		        }

		    @Override
		    protected String doInBackground(Object... params) {
		    	try {
					json = userFunction.Grades(this.ID_country);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					   try {
						 
						   Grades_array_IDS.add("");
						   Grades_semsCount.add("");
				       jObj = new JSONObject(json);
				       jsonArray=jObj.getJSONArray("grades");
				       for (int i = 0; i < jsonArray.length(); i++) {
						     try {
								JSONObject obj1 = jsonArray.getJSONObject(i);
								String Grade_ID=obj1.getString("id");
								String Grade_name=obj1.getString("name");
								String SEM_count=obj1.getString("semesters_count");
								Grades_array.add(Grade_name);
								Grades_array_IDS.add(Grade_ID);
								Grades_semsCount.add(SEM_count);
								Log.d("tagatagatag",SEM_count );
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				       
				    } catch (JSONException e) {
				        Log.e("JSON Parser", "Error parsing data " + e.toString());
				    }
					  runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
					          // adapter1.setDropDownViewResource(R.layout.spineer_item);
								if(name_country.equals("«Œ — «·œÊ·…")){
							//DO NO THING AT ALL ,, let the user choose
							 Grades.setEnabled(false);
							semsters.setEnabled(false);
							  semsters.setAdapter(adapter2);
							  Grades.setAdapter(adapter4);
						}
						else {
							 Grades.setEnabled(true);
							
					           Grades.setAdapter(adapter5); 
									semsters.setEnabled(false);
								
					            Grades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

									@Override
									public void onItemSelected(AdapterView<?> arg0,
											View arg1, int arg2, long arg3) {
										// TODO Auto-generated method stub
										
										if(arg0.getItemAtPosition(arg2).toString().equals("«Œ — «·⁄«„ «·œ—«”Ï")){
											//DO NO THING AT ALL ,, let the user choose
											
											semsters.setEnabled(false);
											//semsters.setBackgroundColor(Color.parseColor("#E5E4E2"));
											  semsters.setAdapter(adapter2);
										}
										else {
											
											semsters.setEnabled(true);
											 semsters.setAdapter(adapter3);
											//semsters.setBackgroundResource( R.drawable.dropd own_browsing_480 );
											TheSelectedGrade_ID=Grades_array_IDS.get(arg2);
											//Toast.makeText(getApplicationContext(), "selectedd"+TheSelectedGrade_ID, Toast.LENGTH_LONG).show();
											if(Grades_semsCount.get(arg2).equals("1")){
												semsters.setEnabled(false);
												
												  semsters.setAdapter(adapter2);
												 Intent in=new Intent(getApplicationContext(),CopyOfMainActivity.class);
												  in.putExtra("ThesemsterID","1");
									        		 in.putExtra("TheUserID", "");
									                 in.putExtra("TheUserName", "");
									                 in.putExtra("TheUserThumb","");
									                 in.putExtra("TheUserGrade", TheSelectedGrade_ID);
									                 in.putExtra("TheUserToken","");
									                 in.putExtra("user_TYPE","ÿ«·»");
									                 in.putExtra("Competition","0");
									                 in.putExtra("link",LINK);
									                 startActivity(in);
												
											}
											
											 
										  semsters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

												
												@Override
												public void onItemSelected(AdapterView<?> arg0,
														View arg1, int arg2, long arg3) {
													// TODO Auto-generated method stub
													if(arg0.getItemAtPosition(arg2).toString().equals("«Œ — «·’› «·œ—«”Ï")){
														//DO NO THING AT ALL ,, let the user choose
														
													}
													else {
														
														if(arg0.getItemAtPosition(arg2).toString()=="«·›’· «·œ—«”Ï «·«Ê·"){
															SEM_ID="1";
															 Intent in=new Intent(getApplicationContext(),CopyOfMainActivity.class);
															  in.putExtra("ThesemsterID",SEM_ID);
												        		 in.putExtra("TheUserID", "");
												                 in.putExtra("TheUserName", "");
												                 in.putExtra("TheUserThumb","");
												                 in.putExtra("TheUserGrade", TheSelectedGrade_ID);
												                 in.putExtra("TheUserToken","");
												                 in.putExtra("user_TYPE","ÿ«·»");
												                 in.putExtra("Competition","0");
												                 in.putExtra("link",LINK);
												                 startActivity(in);
																}
																if(arg0.getItemAtPosition(arg2).toString()=="«·›’· «·œ—«”Ï «·À«‰Ï"){
																	SEM_ID="2";
																	 Intent in=new Intent(getApplicationContext(),CopyOfMainActivity.class);
																	  in.putExtra("ThesemsterID",SEM_ID);
														        		 in.putExtra("TheUserID", "");
														                 in.putExtra("TheUserName", "");
														                 in.putExtra("TheUserThumb","");
														                 in.putExtra("TheUserGrade", TheSelectedGrade_ID);
														                 in.putExtra("TheUserToken","");
														                 in.putExtra("user_TYPE","ÿ«·»");
														                 in.putExtra("Competition","0");
														                 in.putExtra("link",LINK);
														                 startActivity(in);
																		}
														}
													}
												

												@Override
												public void onNothingSelected(AdapterView<?> arg0) {
													// TODO Auto-generated method stub
													
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
						}
						  
						  
					  });
						    	  
		        return "";
		    }
		    @Override
		    protected void onPostExecute(String result) {
		     
		    }

		}

}