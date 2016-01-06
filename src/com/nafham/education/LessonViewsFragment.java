package com.nafham.education;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.nafham.education.R;



public class LessonViewsFragment extends Fragment  {
	  TableLayout table_related_lessons;
	    TableRow tr;
	 View rootView;
	 String Lesson_id;
	String GradeID="";
	String SubjectID="";
	private EasyTracker easyTracker = null;
	 private JSONObject jObj;
	 TextView LessonsTitle;
	 TextView tmarenTitle;
	 TextView TTbekatTitle;
	 Button Fehmt;
	// TextView The_lesson_authorName;
	// TextView Lesson_Grade;
	// TextView Lesson_SUbject;
	 TextView MainLessOnName;
	// ImageView User_thumb;
	// Bitmap User_photo;
	 String User_token="";
	 RelativeLayout BOX;
	 TextView mnA3dad;
	//LinearLayout GradeLayoutName;
	//LinearLayout SubjectLayoutName;
	 UserFunctions userFunction = new UserFunctions();
	
	
	private String json;
	private JSONArray jsonArray;
	
	 JSONArray jsonArray1;
	 JSONArray jsonArray2;
	String have_parts="3";
	 
	 
	private String Lesson_grade="";
	private String Lesson_subject="";
	private String Lesson_User="";
	private String Author_id="";
	private String MainLessonName="";
	private String Lesson_thumb="";
	private String Lesson_vidio_ID="";
	  boolean youTubePlayerIsFullScreen;
	  RelativeLayout Lessons_image;
	  RelativeLayout TamrenImage;
	  RelativeLayout TTbekatImage;
	private String json1="";
	String User_indiv_name="";
	private JSONObject jObj2;
	 Typeface typeface1;
	 Typeface typeface2;
	 String userThumb1="";
	 String Uname1="";
	private TableLayout table_related_Tamren;
	private TableLayout table_related_TTbekat;
	
	 String SemID="";
	private ArrayList<String> RelatedLessons;
	private ArrayList<List<String>> RelatedLessons_sublist;
	private ArrayList<String> Related_lessons_thumb;
	private ArrayList<List<String>> Related_lessons_thumb_sublist;
	private ArrayList<String> Related_lessons_IDS;
	private ArrayList<List<String>> Related_lessons_IDS_sublist;
	private ArrayList<String> Related_lessons_thumb_sublist_part;
	private ArrayList<String> Related_lessons_IDs_sublist_part;
	private ArrayList<String> Related_lessons_sublist_part;
	private ArrayList<String> RelatedTmaren;
	private ArrayList<List<String>> RelatedTmaren_sublist;
	private ArrayList<String> RelatedTmaren_thumb;
	private ArrayList<List<String>> RelatedTmaren_thumb_sublist;
	private ArrayList<String> RelatedTmaren_IDS;
	private ArrayList<List<String>> RelatedTmaren_IDS_sublist;
	private ArrayList<String> Relatedttbekat;
	private ArrayList<List<String>> Relatedttbekat_sublist;
	private ArrayList<String> Relatedttbekat_thumb;
	private ArrayList<List<String>> Relatedttbekat_thumb_sublist;
	private ArrayList<String> Relatedttbekat_IDS;
	private ArrayList<List<String>> Relatedttbekat_IDS_sublists;
	private ArrayList<String> Related_tamren_thumb_sublist_part;
	private ArrayList<String> Related_tamren_IDs_sublist_part;
	private ArrayList<String> Related_tamren_sublist_part;
	private ArrayList<String> Related_List_lessons_thumbs_users;
	private ArrayList<String> Related_List_Tamren_thumbs_users;
	private ArrayList<String> Related_List_TTbekat_thumbs_users;
	private ArrayList<List<String>> Related_List_lessons_thumbs_users_sublist;
	private ArrayList<List<String>> Related_List_Tamren_thumbs_users_sublist;
	private ArrayList<String> Related_List_Tamren_thumbs_users_part;
	private ArrayList<List<String>> Related_List_TTbekat_thumbs_users_sublist;
	private ArrayList<String> Related_List_TTbekat_thumbs_users_part;
	private ArrayList<String> Related_List_lessons_thumbs_users_part;
	private ArrayList<String> Related_TTbekat_thumb_sublist_part;
	private ArrayList<String> Related_TTbekat_IDs_sublist_part;
	private ArrayList<String> Related_TTbekat_sublist_part;
	 ArrayList<String> RelatedLessons_users_IDS;
	 ArrayList<String> RelatedTTbekat_users_IDS;
	 ArrayList<String> RelatedTamren_users_IDS;
	 ArrayList<List<String>> Related_List_lessons_IDS_users_sublist;
	 ArrayList<String> Related_Lessons_Users_IDs_Part;
	 ArrayList<List<String>> Related_Tamren_users_IDs_sublist;
	 ArrayList<String> Related_list_tamren_usersIDS_part;
	 ArrayList<List<String>> Related_List_TTbekat_IDs_users_sublist;
	 ArrayList<String> Related_TTbekat_UsersIDs_part;
	 ArrayList<String> RelatedLessons_users_IDS_userss;
	 ArrayList<String> RelatedTTbekat_users_IDS_userss;
	 ArrayList<String> RelatedTamren_users_IDS_userss;
	 ArrayList<List<String>> Related_List_lessons_IDS_users_sublist_userss;
	 ArrayList<String> Related_Lessons_Users_IDs_Part_userss;
	 ArrayList<List<String>> Related_Tamren_users_IDs_sublist_userss;
	 ArrayList<String> Related_list_tamren_usersIDS_part_userss;
	 ArrayList<List<String>> Related_List_TTbekat_IDs_users_sublist_userss;
	 ArrayList<String> Related_TTbekat_UsersIDs_part_userss;
	 ArrayList<String>Related_lessons_parts;
	private ArrayList<List<String>> Related_List_lessons_PARTS_lesson_sublist;
	private ArrayList<String> Related_List_lessons_PARTS_lesson_sublist_part;
	public LessonViewsFragment(String LessonID,String UT,String SemsterID) {
		
		this.Lesson_id=LessonID;
		this.User_token=UT;
		this.SemID=SemsterID;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		easyTracker = EasyTracker.getInstance(getActivity());
		if(savedInstanceState!=null){
			this.Lesson_id=savedInstanceState.getString("LessonID").toString();
			this.User_token=savedInstanceState.getString("UserToken").toString();
			this.SemID=savedInstanceState.getString("UserSemster").toString();
		}
		 rootView = inflater.inflate(R.layout.lesson_views, container, false);
		  typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
    	
		   // Lesson_Grade=(TextView)rootView.findViewById(R.id.Grade_name_text);
		  //  Lesson_SUbject=(TextView)rootView.findViewById(R.id.Subject_name_text);
		    MainLessOnName=(TextView)rootView.findViewById(R.id.LessonName);
		   table_related_lessons=(TableLayout)rootView.findViewById(R.id.tableLayout_related_lessons);
		   table_related_Tamren=(TableLayout)rootView.findViewById(R.id.tableLayout_Related_tamaren);
		   table_related_TTbekat=(TableLayout)rootView.findViewById(R.id.tableLayout_related_ttbekat);
		   Lessons_image=(RelativeLayout)rootView.findViewById(R.id.Related_lessons_image);
		   TamrenImage=(RelativeLayout)rootView.findViewById(R.id.Tamaren_lessons_Image);
		   TTbekatImage=(RelativeLayout)rootView.findViewById(R.id.ttbekat_lessons_Image);
		   LessonsTitle=(TextView)rootView.findViewById(R.id.torksharhdars);
		   tmarenTitle =(TextView)rootView.findViewById(R.id.Tammmreeen);
			  TTbekatTitle=(TextView)rootView.findViewById(R.id.tgaorrb);
		   BOX=(RelativeLayout)rootView.findViewById(R.id.relativeLayout1);
		 //  GradeLayoutName=(LinearLayout)rootView.findViewById(R.id.GradeName);
		 //  SubjectLayoutName=(LinearLayout)rootView.findViewById(R.id.SubjectName);
		   
		 //  Lesson_Grade.setVisibility(View.GONE);
		 //  Lesson_SUbject.setVisibility(View.GONE);
		   BOX.setVisibility(View.GONE);
		   MainLessOnName.setVisibility(View.GONE);
		   table_related_lessons.setVisibility(View.GONE);
		   table_related_Tamren.setVisibility(View.GONE);
		   table_related_TTbekat.setVisibility(View.GONE);
		   Lessons_image.setVisibility(View.GONE);
		   TamrenImage.setVisibility(View.GONE);
		   TTbekatImage.setVisibility(View.GONE);
		 //  GradeLayoutName.setVisibility(View.GONE);
		 //  SubjectLayoutName.setVisibility(View.GONE);
		 //  if(savedInstanceState != null) { 
			//   savedInstanceState=null;
		 //  }
	 RelatedLessons = new ArrayList<String>();
			RelatedLessons_sublist = new ArrayList<List<String>>();
			 Related_lessons_thumb = new ArrayList<String>();
			 Related_lessons_thumb_sublist = new ArrayList<List<String>>();
		 Related_lessons_IDS = new ArrayList<String>();
			 Related_lessons_IDS_sublist = new ArrayList<List<String>>();
			 Related_lessons_thumb_sublist_part=new ArrayList<String>();
			 Related_lessons_IDs_sublist_part=new ArrayList<String>();
			 Related_lessons_sublist_part=new ArrayList<String>();
			 RelatedTmaren=new ArrayList<String>();
				 RelatedTmaren_sublist=new ArrayList<List<String>>();
				 RelatedTmaren_thumb=new ArrayList<String>();
				RelatedTmaren_thumb_sublist=new ArrayList<List<String>>();
			 RelatedTmaren_IDS=new ArrayList<String>();
				 RelatedTmaren_IDS_sublist=new ArrayList<List<String>>();
			 Relatedttbekat=new ArrayList<String>();
			 Relatedttbekat_sublist=new ArrayList<List<String>>();
				 Relatedttbekat_thumb=new ArrayList<String>();
			 Relatedttbekat_thumb_sublist=new ArrayList<List<String>>();
			 Relatedttbekat_IDS=new ArrayList<String>();
			 Relatedttbekat_IDS_sublists=new ArrayList<List<String>>();
				 Related_tamren_thumb_sublist_part=new ArrayList<String>();
				 Related_tamren_IDs_sublist_part=new ArrayList<String>();
				Related_tamren_sublist_part=new ArrayList<String>();
				Related_List_lessons_thumbs_users=new ArrayList<String>();
			Related_List_Tamren_thumbs_users=new ArrayList<String>();
				Related_List_TTbekat_thumbs_users=new ArrayList<String>();
				Related_List_lessons_thumbs_users_sublist=new ArrayList<List<String>>();
				Related_List_Tamren_thumbs_users_sublist=new ArrayList<List<String>>();
				Related_List_Tamren_thumbs_users_part=new ArrayList<String>();
				Related_List_TTbekat_thumbs_users_sublist=new ArrayList<List<String>>();
				Related_List_TTbekat_thumbs_users_part=new ArrayList<String>();
				Related_List_lessons_thumbs_users_part=new ArrayList<String>();
				 Related_TTbekat_thumb_sublist_part=new ArrayList<String>();
				 Related_TTbekat_IDs_sublist_part=new ArrayList<String>();
				 Related_TTbekat_sublist_part=new ArrayList<String>();
				 RelatedLessons_users_IDS=new ArrayList<String>();
				 RelatedTTbekat_users_IDS=new ArrayList<String>();
				 RelatedTamren_users_IDS=new ArrayList<String>();
				 Related_List_lessons_IDS_users_sublist=new ArrayList<List<String>>();
				 Related_Lessons_Users_IDs_Part=new ArrayList<String>();
				 Related_Tamren_users_IDs_sublist=new ArrayList<List<String>>();
				 Related_list_tamren_usersIDS_part=new ArrayList<String>();
				 Related_List_TTbekat_IDs_users_sublist=new ArrayList<List<String>>();
				 Related_TTbekat_UsersIDs_part=new ArrayList<String>();

				 RelatedLessons_users_IDS_userss=new ArrayList<String>();
				 RelatedTTbekat_users_IDS_userss=new ArrayList<String>();
				 RelatedTamren_users_IDS_userss=new ArrayList<String>();
				 Related_List_lessons_IDS_users_sublist_userss=new ArrayList<List<String>>();
				 Related_Lessons_Users_IDs_Part_userss=new ArrayList<String>();
				 Related_Tamren_users_IDs_sublist_userss=new ArrayList<List<String>>();
				 Related_list_tamren_usersIDS_part_userss=new ArrayList<String>();
				 Related_List_TTbekat_IDs_users_sublist_userss=new ArrayList<List<String>>();
				 Related_TTbekat_UsersIDs_part_userss=new ArrayList<String>();
				 Related_lessons_parts=new ArrayList<String>();
				 Related_List_lessons_PARTS_lesson_sublist=new ArrayList<List<String>>();
				 Related_List_lessons_PARTS_lesson_sublist_part=new ArrayList<String>();
				 Related_Lessons_Users_IDs_Part_userss=new ArrayList<String>();
				 LessonsTitle.setTypeface(typeface1);
				 tmarenTitle.setTypeface(typeface1);
				 TTbekatTitle .setTypeface(typeface1);
		    new ProgressTask().execute();
		   
	    return rootView;
	}

		public static Bitmap getBitmapFromURL(String src) {
		    try {
		        Log.e("src",src);
		        URL url = new URL(src);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setDoInput(true);
		        connection.connect();
		        InputStream input = connection.getInputStream();
		        Bitmap myBitmap = BitmapFactory.decodeStream(input);
		        Log.e("Bitmap","returned");
		        return myBitmap;
		    } catch (IOException e) {
		        e.printStackTrace();
		        Log.e("Exception",e.getMessage());
		        return null;
		    }
		}
		public class TextImagePair extends RelativeLayout {
		   
		    public TextImagePair(Context context,AttributeSet attributeSet){
		        super(context, attributeSet);
		    }

		    public TextImagePair(Context context,AttributeSet attributeSet, int i){
		        super(context, attributeSet,i);
		    }

		    public TextImagePair(Context context, AttributeSet attributeSet, String text, String bm,final String Lesson_ID_ind,String User_bm,final String Author_T_ID,final String User_ID_formal,String have_PARTS_s) {
		        super(context, attributeSet);

		        // Inflate the layout
		        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        inflator.inflate(R.layout.the_whole_element_in_hscrolview, this);
		       
                 mnA3dad=(TextView)this.findViewById(R.id.textView1);
                 mnA3dad.setTypeface(typeface2);
		        TextView tvText = (TextView)this.findViewById(R.id.TextOnImage11);
		       

		        ImageView imgView = (ImageView)this.findViewById(R.id.imageView111);
		        imgView.setTag(bm);
		     //  imgView.setImageBitmap(getBitmapFromURL(bm));
		        new LoadImage(imgView,User_ID_formal,Lesson_ID_ind,Author_T_ID,tvText,text).execute();
		       
		        
		      //  ImageView imgView_user = (ImageView)this.findViewById(R.id.user_photo_lesson);
		      //  imgView_user.setTag(User_bm);
		       // imgView_user.setImageBitmap(getBitmapFromURL(User_bm));
		      //  imgLoader.DisplayImage(User_bm, loader, imgView_user);
		       // new LoadImage(imgView_user).execute();
		    }
		}
		
		class ProgressTask extends AsyncTask<String, Integer, String>  {

	        private ProgressDialog progressDialog;
			//String json2;
			///JSONObject jObj5;
			 String Uname;
			 String user_thumb;
			private String json3;
			private JSONObject jObj7;
			private String Thumb_less;

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
	 				json = userFunction.GetLessonDetails(Lesson_id);
	 				Log.d("MSG_Lesson", json);
	 			} catch (IOException e1) {
	 				// TODO Auto-generated catch block
	 				e1.printStackTrace();
	 			}
	 			   try {
	 		     jObj = new JSONObject(json);
	 		      Lesson_grade=jObj.getString("grade_name");
	 		      Lesson_subject=jObj.getString("subject_name");
	 		      Lesson_User=jObj.getString("author_name");
	 		      Author_id=jObj.getString("author_id");
	 		      MainLessonName=jObj.getString("lesson_name");
	 		     GradeID=jObj.getString("grade_id");
	 		    SubjectID=jObj.getString("subject_id");
	 		   Thumb_less=jObj.getString("thumb");
	 		  RelatedLessons.add(Lesson_User);
				Related_lessons_thumb.add(Thumb_less);
				Related_lessons_IDS.add(Lesson_id);
			
				RelatedLessons_users_IDS.add("1");
				RelatedLessons_users_IDS_userss.add(Author_id);
				Related_lessons_parts.add(have_parts);
	 		 /*   GradeLayoutName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MainActivity mainAct = (MainActivity)getActivity();
						mainAct.changeFragment13(GradeID, User_token, SemID);
					}
				});*/
	 		    
	 		  /*  SubjectLayoutName.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MainActivity mainAct = (MainActivity)getActivity();
						mainAct.changeFragment(SubjectID, User_token, SemID);
					}
				});*/
	 		  
	 		     if(!(jObj.getString("related_lessons")).equals(null)){
	 		    	 Log.d("yarabyarabyarab", jObj.getString("related_lessons"));
	 		     jsonArray=jObj.getJSONArray("related_lessons");
	 		     for (int i = 0; i < jsonArray.length(); i++) {
	 			     try {
	 			    	 
	 					JSONObject obj1 = jsonArray.getJSONObject(i);
	 					String LessonName=obj1.getString("name");
	 					
	 					String Thumb=obj1.getString("thumb");
	 					String Lesson_id1=obj1.getString("id");
	 					String Lesson_User_ID=obj1.getString("user_id");
	 					String AuthorType_ID=obj1.getString("author_type_id");
	 				//	json2 = userFunction.User_details(Lesson_User_ID);
	 					
		 			 // jObj5 = new JSONObject(json2);
		 			 if(AuthorType_ID.equals("1")){
	 					 
		 					Uname=obj1.getString("youtube");
		 					
		 					
		 				 }else{
		 					
		 				    Uname="";
		 				  
		 				 }
		 				// user_thumb=jObj5.getString("thumb");
	 					RelatedLessons.add(Uname);
	 					Related_lessons_thumb.add(Thumb);
	 					Related_lessons_IDS.add(Lesson_id1);
	 					//Related_List_lessons_thumbs_users.add(user_thumb);
	 					RelatedLessons_users_IDS.add(AuthorType_ID);
	 					RelatedLessons_users_IDS_userss.add(Lesson_User_ID);
	 					Related_lessons_parts.add(have_parts);
	 				} catch (JSONException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} 	 			}
	 		     }
	 		    if(!jObj.getString("related_applications").equals(null)){
	 		     jsonArray1=jObj.getJSONArray("related_applications");
	 		     for (int i = 0; i < jsonArray1.length(); i++) {
	 			     try {
	 			    	 
	 					JSONObject obj1 = jsonArray1.getJSONObject(i);
	 					String LessonName=obj1.getString("name");
	 					String Thumb=obj1.getString("thumb");
	 					
	 					String Lesson_id=obj1.getString("id");
	 					String Lesson_User_ID=obj1.getString("user_id");
	 					String AuthorType_ID=obj1.getString("author_type_id");
	 					//json2 = userFunction.User_details(Lesson_User_ID);
		 				 //  jObj5 = new JSONObject(json2);
		 				  if(AuthorType_ID.equals("1")){
			 					 
			 					Uname1=obj1.getString("youtube");
			 					// userThumb1 =Thumb;
			 					
			 				 }else{
		 					
		 				  Uname1="";
		 				
		 				// userThumb1 =jObj5.getString("thumb");
		 				
		 				 }
			 				 //userThumb1 =jObj5.getString("thumb");
		 				Relatedttbekat.add(Uname1);
		 				Relatedttbekat_thumb.add(Thumb);
		 				Relatedttbekat_IDS.add(Lesson_id);
		 				//Related_List_TTbekat_thumbs_users.add(userThumb1);
		 				RelatedTTbekat_users_IDS.add(AuthorType_ID);
		 				RelatedTTbekat_users_IDS_userss.add(Lesson_User_ID);
	 				} catch (JSONException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 			}
	 		    }
	 		    if(!jObj.getString("related_excercises").equals(null)){
	 		     jsonArray2=jObj.getJSONArray("related_excercises");
	 		     for (int i = 0; i < jsonArray2.length(); i++) {
	 			     try {
	 			    	 
	 					JSONObject obj1 = jsonArray2.getJSONObject(i);
	 					String LessonName=obj1.getString("name");
	 					String Thumb=obj1.getString("thumb");
	 					String Lesson_id=obj1.getString("id");
	 				
	 					String Lesson_User_ID=obj1.getString("user_id");
	 					String AuthorType_ID=obj1.getString("author_type_id");
	 				//	json2 = userFunction.User_details(Lesson_User_ID);
	 				 //  jObj5 = new JSONObject(json2);
	 				  if(AuthorType_ID.equals("1")){
		 					 
		 					Uname1=obj1.getString("youtube");
		 					
		 					
		 				 }else{
	 					
	 				  Uname1="";
	 					
	 				
	 				 }
	 				 // String userThumb=jObj5.getString("thumb");
	 				 // String userThumb=jObj5.getString("thumb");
	 				 RelatedTmaren.add(Uname1);
	 				RelatedTmaren_thumb.add(Thumb);
	 				RelatedTmaren_IDS.add(Lesson_id);
	 				//Related_List_Tamren_thumbs_users.add(userThumb1);
	 				RelatedTamren_users_IDS.add(AuthorType_ID);
	 				RelatedTamren_users_IDS_userss.add(Lesson_User_ID);
	 				} catch (JSONException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} 
	 			}
	 		    }
	 		   
	 		  /* if(!jObj.getString("parts").equals(null)){
	 			   List <String>AR=new ArrayList<String>();
	 			  List <String>AR1=new ArrayList<String>();
		 		     jsonArray1=jObj.getJSONArray("parts");
		 		     Log.d("hereherehereherehere",jObj.getString("parts") );
		 		     for (int i = 0; i < jsonArray1.length(); i++) {
		 			    
		 			   
		 			}
		 		    Related_lessons_parts.add(have_parts);
		 		   
		 		    }
	 		   else{
	 			   have_parts=true;//true ma3naha mafehash Parts
	 			  Related_lessons_parts.add(have_parts);
	 		   }*/
	 		  } catch (JSONException e) {
	 		      Log.e("JSON Parser", "Error parsing data " + e.toString());
	 		  }
	 		
	        	
	        	  getActivity().runOnUiThread(new Runnable() {
                           
				
					@Override
	          	    public void run() {
						 BOX.setVisibility(View.VISIBLE);
				 		   MainLessOnName.setVisibility(View.VISIBLE);
				 		   table_related_lessons.setVisibility(View.VISIBLE);
				 		   table_related_Tamren.setVisibility(View.VISIBLE);
				 		   table_related_TTbekat.setVisibility(View.VISIBLE);
				 		  MainLessOnName.setTypeface(typeface1);
			 		      MainLessOnName.setText(MainLessonName);
			 		     Lessons_image.setVisibility(View.VISIBLE);
			 		    TamrenImage.setVisibility(View.VISIBLE);
			 		   TTbekatImage.setVisibility(View.VISIBLE);
						
						          	    }
	          	});
	      		 return "done";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            // TODO Auto-generated method stub
	            super.onPostExecute(result);
	           
	       	 LinearLayout layout_related_lessons = (LinearLayout) rootView.findViewById(R.id.Related_lessons);
				
  	    	 if(!Related_lessons_thumb.isEmpty()){
  	    		
  	    	
  	    	 table_related_lessons.setStretchAllColumns(true);
  	    	  Related_lessons_thumb_sublist=(ArrayList<List<String>>) chopped(Related_lessons_thumb, 1);
              RelatedLessons_sublist=(ArrayList<List<String>>) chopped(RelatedLessons, 1);
              Related_lessons_IDS_sublist=(ArrayList<List<String>>) chopped(Related_lessons_IDS, 1);
             // Related_List_lessons_thumbs_users_sublist=(ArrayList<List<String>>) chopped(Related_List_lessons_thumbs_users, 1);
              Related_List_lessons_IDS_users_sublist=(ArrayList<List<String>>) chopped(RelatedLessons_users_IDS, 1);
              Related_List_lessons_IDS_users_sublist_userss=(ArrayList<List<String>>) chopped(RelatedLessons_users_IDS_userss, 1);
              Related_List_lessons_PARTS_lesson_sublist=(ArrayList<List<String>>) chopped(Related_lessons_parts, 1);
    for(int i =0; i < Related_lessons_thumb_sublist.size(); i ++){
Related_lessons_thumb_sublist_part=(ArrayList<String>) Related_lessons_thumb_sublist.get(i);
Related_lessons_IDs_sublist_part=(ArrayList<String>) Related_lessons_IDS_sublist.get(i);
Related_lessons_sublist_part=(ArrayList<String>) RelatedLessons_sublist.get(i);
//Related_List_lessons_thumbs_users_part=(ArrayList<String>) Related_List_lessons_thumbs_users_sublist.get(i);
Related_Lessons_Users_IDs_Part=(ArrayList<String>) Related_List_lessons_IDS_users_sublist.get(i);
Related_Lessons_Users_IDs_Part_userss=(ArrayList<String>) Related_List_lessons_IDS_users_sublist_userss.get(i);
Related_List_lessons_PARTS_lesson_sublist_part=(ArrayList<String>) Related_List_lessons_PARTS_lesson_sublist.get(i);
TableRow tableRow = new TableRow(getActivity());
tableRow.setGravity(Gravity.FILL);
tableRow.setLayoutParams(new TableLayout.LayoutParams(
   TableLayout.LayoutParams.FILL_PARENT,
   TableLayout.LayoutParams.FILL_PARENT, 1.0f));
			   for(int k =0; k< Related_lessons_thumb_sublist_part.size(); k ++)
			    {
				  String Flag_have_parts=Related_List_lessons_PARTS_lesson_sublist_part.get(k);
				   String UID_user=Related_Lessons_Users_IDs_Part_userss.get(k);
				 String UID=Related_Lessons_Users_IDs_Part.get(k);
				// String User_thumb=Related_List_lessons_thumbs_users_part.get(k);
			      String Thumb = Related_lessons_thumb_sublist_part.get(k);
			      
			       TextImagePair tip = new TextImagePair(getActivity(),null,Related_lessons_sublist_part.get(k),Thumb,Related_lessons_IDs_sublist_part.get(k),"",UID,UID_user,Flag_have_parts);
			     tableRow.addView(tip);
			      
			    }
			   
			 table_related_lessons.addView(tableRow);
            }
   
  	    	 }
  	    	 else{
  	    		layout_related_lessons.setVisibility(View.GONE);
  	    		Lessons_image.setVisibility(View.GONE);
  	    	 }
  	    	 LinearLayout layout_related_Tmaren = (LinearLayout) rootView.findViewById(R.id.Tamaren_lessons);
  	    
  	     if(!RelatedTmaren_thumb.isEmpty()){
  	    	
			
	    	 table_related_Tamren.setStretchAllColumns(true);
	    	  RelatedTmaren_thumb_sublist=(ArrayList<List<String>>) chopped(RelatedTmaren_thumb, 1);
         RelatedTmaren_sublist=(ArrayList<List<String>>) chopped(RelatedTmaren, 1);
         RelatedTmaren_IDS_sublist=(ArrayList<List<String>>) chopped(RelatedTmaren_IDS, 1);
     // Related_List_lessons_thumbs_users_sublist=(ArrayList<List<String>>) chopped(Related_List_Tamren_thumbs_users, 1);
   Related_Tamren_users_IDs_sublist=(ArrayList<List<String>>) chopped(RelatedTamren_users_IDS, 1);
Related_Tamren_users_IDs_sublist_userss=(ArrayList<List<String>>) chopped(RelatedTamren_users_IDS_userss, 1);
for(int i =0; i < RelatedTmaren_thumb_sublist.size(); i ++){
Related_tamren_thumb_sublist_part=(ArrayList<String>) RelatedTmaren_thumb_sublist.get(i);
Related_tamren_IDs_sublist_part=(ArrayList<String>) RelatedTmaren_IDS_sublist.get(i);
Related_tamren_sublist_part=(ArrayList<String>) RelatedTmaren_sublist.get(i);
//Related_List_Tamren_thumbs_users_part=(ArrayList<String>) Related_List_lessons_thumbs_users_sublist.get(i);
Related_list_tamren_usersIDS_part=(ArrayList<String>) Related_Tamren_users_IDs_sublist.get(i);
Related_list_tamren_usersIDS_part_userss=(ArrayList<String>) Related_Tamren_users_IDs_sublist_userss.get(i);
TableRow tableRow = new TableRow(getActivity());
tableRow.setGravity(Gravity.FILL);
tableRow.setLayoutParams(new TableLayout.LayoutParams(
TableLayout.LayoutParams.FILL_PARENT,
TableLayout.LayoutParams.FILL_PARENT, 1.0f));
		   for(int k =0; k< Related_tamren_thumb_sublist_part.size(); k ++)
		    {
			  String uID_user=Related_list_tamren_usersIDS_part_userss.get(k);
			  String UID=Related_list_tamren_usersIDS_part.get(k);
			//  String User_thumb=Related_List_Tamren_thumbs_users_part.get(k);
		      String Thumb = Related_tamren_thumb_sublist_part.get(k);
		     
		       TextImagePair tip = new TextImagePair(getActivity(),null,Related_tamren_sublist_part.get(k),Thumb,Related_tamren_IDs_sublist_part.get(k),"",UID,uID_user,"0");
		     tableRow.addView(tip);
		      
		
		    }
		   
		 table_related_Tamren.addView(tableRow);
       }
			   
			}
  	     else{
  	    	layout_related_Tmaren.setVisibility(View.GONE);
  	    	TamrenImage.setVisibility(View.GONE);
  	     }
  	   LinearLayout layout_related_TTbekat = (LinearLayout) rootView.findViewById(R.id.ttbekat_lessons);
  		 if(!Relatedttbekat_thumb.isEmpty()){
  			
			 table_related_TTbekat.setStretchAllColumns(true);
    	  Relatedttbekat_thumb_sublist=(ArrayList<List<String>>) chopped(Relatedttbekat_thumb, 1);
        Relatedttbekat_sublist=(ArrayList<List<String>>) chopped(Relatedttbekat, 1);
        Relatedttbekat_IDS_sublists=(ArrayList<List<String>>) chopped(Relatedttbekat_IDS, 1);
       // Related_List_TTbekat_thumbs_users_sublist=(ArrayList<List<String>>) chopped(Related_List_TTbekat_thumbs_users, 1);
      Related_List_TTbekat_IDs_users_sublist=(ArrayList<List<String>>) chopped(RelatedTTbekat_users_IDS, 1);
    Related_List_TTbekat_IDs_users_sublist_userss=(ArrayList<List<String>>) chopped(RelatedTTbekat_users_IDS_userss, 1);
for(int i =0; i < Relatedttbekat_thumb_sublist.size(); i ++){
Related_TTbekat_thumb_sublist_part=(ArrayList<String>) Relatedttbekat_thumb_sublist.get(i);
Related_TTbekat_IDs_sublist_part=(ArrayList<String>) Relatedttbekat_IDS_sublists.get(i);
Related_TTbekat_sublist_part=(ArrayList<String>) Relatedttbekat_sublist.get(i);
//Related_List_TTbekat_thumbs_users_part=(ArrayList<String>) Related_List_TTbekat_thumbs_users_sublist.get(i);
Related_TTbekat_UsersIDs_part=(ArrayList<String>) Related_List_TTbekat_IDs_users_sublist.get(i);
Related_TTbekat_UsersIDs_part_userss=(ArrayList<String>) Related_List_TTbekat_IDs_users_sublist_userss.get(i);
TableRow tableRow = new TableRow(getActivity());
tableRow.setGravity(Gravity.FILL);
tableRow.setLayoutParams(new TableLayout.LayoutParams(
TableLayout.LayoutParams.FILL_PARENT,
TableLayout.LayoutParams.FILL_PARENT, 1.0f));
		   for(int k =0; k< Related_TTbekat_thumb_sublist_part.size(); k ++)
		    {
			  String UID_user=Related_TTbekat_UsersIDs_part_userss.get(k);
			  String UserID=Related_TTbekat_UsersIDs_part.get(k);
			//  String User_thumb= Related_List_TTbekat_thumbs_users_part.get(k);
		      String Thumb = Related_TTbekat_thumb_sublist_part.get(k);
		     
		       TextImagePair tip = new TextImagePair(getActivity(),null,Related_TTbekat_sublist_part.get(k),Thumb,Related_TTbekat_IDs_sublist_part.get(k),"",UserID,UID_user,"0");
		     tableRow.addView(tip);
		     
		    }
		   
		 table_related_TTbekat.addView(tableRow);
      }
	    	 }
	    	 else{
	    		layout_related_TTbekat.setVisibility(View.GONE);
	    		TTbekatImage.setVisibility(View.GONE);
	    	 }

	 		    
	            if(progressDialog!=null)
	            progressDialog.dismiss();
	        }
	    }
		
		class LoadImage extends AsyncTask<Object, Void, Bitmap>{

	        private ImageView imv;
	        private String path;
           private String UID;
		private String json2;
		private String LessonID_indd;
		private JSONObject jObj5;
		private String json3;
		private JSONObject jObj7;
		private TextView TV_UN;
		private String TYPE_AUTHOR;
		private String Y_TUBE_user;
	        public LoadImage(ImageView imv,String u_id,String L_ID,String TYPE_AUTHORR,TextView TV_UNN,String Text_youTube) {
	             this.imv = imv;
	             this.path = imv.getTag().toString();
	             this.UID=u_id;
	             this.LessonID_indd=L_ID;
	             this.TV_UN=TV_UNN;
	             this.TYPE_AUTHOR=TYPE_AUTHORR;
	             this.Y_TUBE_user=Text_youTube;
	        }

	    @Override
	    protected Bitmap doInBackground(Object... params) {
	        Bitmap bitmap = null;
	        URL url = null;
	      
			try {
				try {
					url = new URL( this.path);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();
				   connection.setDoInput(true);
			        connection.connect();
			        InputStream input = connection.getInputStream();
			        bitmap = BitmapFactory.decodeStream(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
			try {
				json2 = userFunction.User_details(this.UID);
				 jObj5 = new JSONObject(json2);
				 User_indiv_name= jObj5.getString("name");
				 
				 json3=userFunction.GetLessonDetails(LessonID_indd);
					 jObj7 = new JSONObject(json3);
					 Log.d("ggeeggeeggee",jObj7.getString("parts") );
					 if(jObj7.getString("parts").equals("null")){
						 have_parts="1";// there is a parts !!!!!
					 }
					 else{
						have_parts="0"; // there is no parts !!!!
					 }
					 Log.d("partsssssss", have_parts);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	        return bitmap;
	    }
	    @Override
	    protected void onPostExecute(Bitmap result) {
	        if (!imv.getTag().toString().equals(path)) {
	               /* The path is not same. This means that this
	                  image view is handled by some other async task. 
	                  We don't do anything and return. */
	               return;
	        }
	        easyTracker.send(MapBuilder.createEvent("LessOnViews",
					"LessonViews", "ViewLessons", null).build());
	        if(result != null && imv != null){
	            imv.setVisibility(View.VISIBLE);
	            imv.setImageBitmap(result);
	            Log.d("eh !! ", "tab ma eshta !!" );
	        }else{
	        	Log.d("mmmmm yarb",  this.path);
	            imv.setVisibility(View.GONE);
	        }
	        if(have_parts=="1"){
	        	imv.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
				          mainAct.changeFragment1(LessonID_indd,User_token);
					}
				});}
		        else if(have_parts=="0"){
		        	imv.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
						          mainAct.changeFragment113(LessonID_indd, User_token,SemID );
							}
						});
		        }
	        TV_UN.setTypeface(typeface2);
	        TV_UN.setTextColor(Color.parseColor("#0088cf"));
	       
	        if(TYPE_AUTHOR.equals("1")){
	        	TV_UN.setText(Y_TUBE_user);
	        
	        }else{
	        	TV_UN.setText(User_indiv_name);
	        	TV_UN.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
			          mainAct.changeFragment2(UID,User_token);
				}
			});
	        }
	    }

	}
	/*	public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
	    	// TODO Auto-generated method stub
	    	int targetWidth = 50;
	    	int targetHeight = 50;
	    	 Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
	    	                        targetHeight,Bitmap.Config.ARGB_8888);

	    	            Canvas canvas = new Canvas(targetBitmap);
	    	 Path path = new Path();
	    	path.addCircle(((float) targetWidth - 1) / 2,
	    	((float) targetHeight - 1) / 2,
	    	(Math.min(((float) targetWidth), 
	    	            ((float) targetHeight)) / 2),
	    	Path.Direction.CCW);

	    	canvas.clipPath(path);
	    	Bitmap sourceBitmap = scaleBitmapImage;
	    	canvas.drawBitmap(sourceBitmap, 
	    	                            new Rect(0, 0, sourceBitmap.getWidth(),
	    	sourceBitmap.getHeight()), 
	    	                            new Rect(0, 0, targetWidth,
	    	targetHeight), null);
	    	return targetBitmap;
	    	}*/
		static <T> List<List<T>> chopped(List<T> list, final int L) {
		    List<List<T>> parts = new ArrayList<List<T>>();
		    final int N = list.size();
		    for (int i = 0; i < N; i += L) {
		        parts.add(new ArrayList<T>(
		            list.subList(i, Math.min(N, i + L)))
		        );
		    }
		    return parts;
		}
		@Override
		public void onSaveInstanceState(Bundle savedInstanceState) {
		  super.onSaveInstanceState(savedInstanceState);
		  // Save UI state changes to the savedInstanceState.
		  // This bundle will be passed to onCreate if the process is
		  // killed and restarted.
		 // savedInstanceState.putString("LessonID", this.Lesson_id);
		//  savedInstanceState.putString("UserToken", this.User_token);
		//  savedInstanceState.putString("UserSemster", this.SemID);
		
		  // etc.
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
		}