package com.nafham.education;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.nafham.education.R;
import com.nafham.education.HomeFragment.ADAPTER;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
public class Subject_fragment extends Fragment {
	 ExpandableListAdapter listAdapter;
	    ExpandableListView expListView;
	    List<String> listDataHeader_UNIT;
	    private EasyTracker easyTracker = null;
	    HashMap<String, List<String>> listDataChild_Lessons;
	    HashMap<String, List<String>> listDataChild_Lessons_ids;
	    HashMap<String, List<String>> listDataChild_Lessons_months;
	    HashMap<String, List<String>> listDataChild_Lessons_HAVEvideosOrnot;
	    List<String> Unit_lessons ;
   	 List<String> Unit_lessons_IDs ;
   	 List<String> Unit_lessons_months ;
	 List<String> Unit_lessons_haveVideosOrnot;
	    String SUbject_ID;
	    UserFunctions userFunction = new UserFunctions();
        String json;
		private JSONObject jObj;
		private JSONArray jsonArray;
		private  JSONArray json_array1;
		String LessonID="";
		private String SubjectNName;
		RelativeLayout RL;
		TextView Tv3;
		Button GoTOBrowser;
		String Videos_Counter="";
		String Lessons_counter="";
		String semster_ID="";
		String User_TOKEN="";
		int temp2;
		 int temp3;
		 Typeface typeface1 ;
		  Typeface typeface2 ;
public Subject_fragment(String Subject_id,String UT,String Semsteer_ID){
    	this.SUbject_ID=Subject_id;
    	this.User_TOKEN=UT;
    	this.semster_ID=Semsteer_ID;
    }
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
	 View rootView = inflater.inflate(R.layout.activity_main__dashboard, container, false);
	 getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	 easyTracker = EasyTracker.getInstance(getActivity());
	 if(savedInstanceState!=null){
		 this.SUbject_ID=savedInstanceState.getString("SubjectID").toString();
		 this.semster_ID=savedInstanceState.getString("UserSemster").toString();
		 this.User_TOKEN=savedInstanceState.getString("UserToken").toString();
	 }
	
	  typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	   typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	
	 
	 // get the listview
	 listDataHeader_UNIT = new ArrayList<String>();
     listDataChild_Lessons = new HashMap<String, List<String>>();
     listDataChild_Lessons_ids=new HashMap<String, List<String>>();
     listDataChild_Lessons_months=new HashMap<String, List<String>>();
     listDataChild_Lessons_HAVEvideosOrnot=new HashMap<String, List<String>>();
     expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
     RL=(RelativeLayout) rootView.findViewById(R.id.RL1);
     Tv3=(TextView) rootView.findViewById(R.id.textView1);
     GoTOBrowser=(Button) rootView.findViewById(R.id.button90);
     RL.setVisibility(View.GONE);
     Tv3.setVisibility(View.GONE);
     GoTOBrowser.setVisibility(View.GONE);
     expListView.setGroupIndicator(null);
    expListView.setFocusable(false);
     new ProgressTask().execute();
    return rootView;
}

class ProgressTask extends AsyncTask<String, Integer, String> {

    private static final int List = 0;
	private ProgressDialog progressDialog;
	private java.util.List<String> dd=new ArrayList <String>();

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
    		
    			json = userFunction.getSubjectDetails(SUbject_ID);
    		//	Log.d("al Json",json);
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		   try {
    			   Log.d("jahgdfjhasgfjsf",json);
    	     jObj = new JSONObject(json);
    	      SubjectNName=jObj.getString("subject_name");
    	  
    	     jsonArray=jObj.getJSONArray("units");
    	    
    	     for (int i = 0; i < jsonArray.length(); i++) {
    			     try {
    			    	 Unit_lessons=new ArrayList<String>();
    			    	 Unit_lessons_IDs=new ArrayList<String>();
    			    	 Unit_lessons_months=new ArrayList<String>();
    			    	 Unit_lessons_haveVideosOrnot=new ArrayList<String>();
    					JSONObject obj1 = jsonArray.getJSONObject(i);
    					String UnitName=obj1.getString("unit_name");
    					listDataHeader_UNIT.add(i,UnitName);
    					json_array1=obj1.getJSONArray("lessons");
    					  for (int j = 0; j < json_array1.length(); j++) {
    							JSONObject obj2 = json_array1.getJSONObject(j);
    							String LessonName=obj2.getString("name");
    							 LessonID=obj2.getString("main_lesson_id");
    							 String month_lesson=obj2.getString("month");
    							 String temp=obj2.getString("videos_count");
    							 int temp1=Integer.parseInt(temp);
    							temp2=temp2+temp1;
    							temp3+=1;
    							 Unit_lessons_IDs.add(LessonID);
    							Unit_lessons.add(LessonName);
    							Unit_lessons_months.add(month_lesson);
    							if(temp.equals("0")){
    							Unit_lessons_haveVideosOrnot.add("1");}
    							else {
    								Unit_lessons_haveVideosOrnot.add("0");
    							}
    					  }
    					  listDataChild_Lessons.put(listDataHeader_UNIT.get(i), Unit_lessons); 
    					listDataChild_Lessons_ids.put(listDataHeader_UNIT.get(i), Unit_lessons_IDs); 
    					listDataChild_Lessons_months.put(listDataHeader_UNIT.get(i), Unit_lessons_months); 
    					listDataChild_Lessons_HAVEvideosOrnot.put(listDataHeader_UNIT.get(i), Unit_lessons_haveVideosOrnot); 
    				
    				} catch (JSONException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    	   
    	  } catch (JSONException e) {
    	      Log.e("JSON Parser", "Error parsing data " + e.toString());
    	  }

    	
      	        	  getActivity().runOnUiThread(new Runnable() {

      	    @Override
      	    public void run() {
      	 

      	    }
      	});
  		 return "done";
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        easyTracker.send(MapBuilder.createEvent("SubjectScreen",
				"SubjectScreen", "SubjectScreen", null).build());
        View header = (View)getActivity().getLayoutInflater().inflate(R.layout.main_dashboard_title, null);
		 LinearLayout Subject_BG=(LinearLayout)header.findViewById(R.id.Subject_backGround);
		   ImageView 	 Image_subject=(ImageView)header.findViewById(R.id.Subject_Image);
		   TextView 	 SubjectName=(TextView)header.findViewById(R.id.Subject_name);
		    TextView	 Vidieo_statiscs=(TextView)header.findViewById(R.id.Vidio_statiscs);
		  
		    SubjectName.setTypeface(typeface1);
		    SubjectName.setText(SubjectNName);
		  String  Videos_Counter=String.valueOf(temp2);
		  String   Lessons_counter=String.valueOf(temp3);
		  if(temp2==0){
			  RL.setVisibility(View.VISIBLE);
			  Tv3.setVisibility(View.VISIBLE);
			  GoTOBrowser.setTypeface(typeface2);
			  GoTOBrowser.setVisibility(View.VISIBLE);
			  Tv3.setTypeface(typeface2);
			  Tv3.setTextColor(Color.parseColor("#808080"));
			  Tv3.setText("åÐå ÇáãÇÏÉ áÇ íæÌÏ ÈåÇ ÝíÏíæåÇÊ ÍÊì ÇáÂä¡ íãßäß Ãä Êßæä Ãæá ãä íÓÇåã ÈÔÑÍ ÏÑÓ ÝíåÇ");
			  GoTOBrowser.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
					    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nafham.com/guide/create"));
					    startActivity(myIntent);
					} catch (ActivityNotFoundException e) {
					    Toast.makeText(getActivity(), "No application can handle this request."
					        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
					    e.printStackTrace();
					}
				}
			});
		  }
		     Vidieo_statiscs.setTypeface(typeface2);
		     Vidieo_statiscs.setText(Videos_Counter +" ÝíÏíæ ÊÔÑÍ "+Lessons_counter+" ÏÑÓ ");
		    if(SubjectNName.contains("ÇááÛÉ ÇáÚÑÈíÉ")){
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.arabic));
		    	Subject_BG.setBackgroundColor(Color.parseColor("#74ce8e"));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáßíãíÇÁ") ||SubjectNName.contains("Chemistry") || SubjectNName.contains("chemistry")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#78d0e6"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.chemistry));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}else if(SubjectNName.contains("ÇáÏÑÇÓÇÊ ÇáÇÌÊãÇÚíÉ")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#e46459"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.social_studies));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÑíÇÖíÇÊ") || SubjectNName.contains("Mathematics") || SubjectNName.contains("mathematics") || SubjectNName.contains("math")|| SubjectNName.contains("Math")||SubjectNName.contains("Mechanics")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#446381"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.math));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}else if(SubjectNName.contains("ÇáÇÍÕÇÁ") ||  SubjectNName.contains("Statistics") ||  SubjectNName.contains("statistics")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#52c4df"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.statistics));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}else if(SubjectNName.contains("ÇáÇÞÊÕÇÏ") || SubjectNName.contains("Economy")|| SubjectNName.contains("economy")||SubjectNName.contains("ÇáÇÞÊÕÇÏ 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#60618f"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.economics));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}else if(SubjectNName.contains("Úáæã ÈíÆíÉ æÌíæáÌíÇ")  ||SubjectNName.contains("Geology") ||SubjectNName.contains("geology")||SubjectNName.contains("Úáæã ÈíÆíÉ æÌíæáæÌíÇ 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#2ab2be"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.geography));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÝíÒíÇÁ") || SubjectNName.contains("Physics") || SubjectNName.contains("physics")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#f04d4e"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.physics));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÇÍíÇÁ") || SubjectNName.contains("Biology")|| SubjectNName.contains("biology")|| SubjectNName.contains("ÇáÃÍíÇÁ 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#92c44b"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.geology));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("Úáã ÇáäÝÓ æÇáÇÌÊãÇÚ")|| SubjectNName.contains("Úáã ÇáäÝÓ 2014")|| SubjectNName.contains("psychology")||SubjectNName.contains("Úáã ÇáÅÌÊãÇÚ 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#3a6b9b"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.pyscology));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇááÛÉ ÇáÇáãÇäíÉ")|| SubjectNName.contains("Germany")|| SubjectNName.contains("germany")||SubjectNName.contains("German 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#4d4d4d"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.german));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇááÛÉ ÇáÝÑäÓíÉ")|| SubjectNName.contains("French")|| SubjectNName.contains("french")||SubjectNName.contains("ÇááÛÉ ÇáÝÑäÓíÉ 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#52c4df"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.francis));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÚáæã") ||SubjectNName.contains("Science") ||SubjectNName.contains("science")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#2980b9"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.science));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("äÌáíÒíÉ")||SubjectNName.contains("English")||SubjectNName.contains("english")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#2e2e2e"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.english));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÍÇÓÈ ÇáÇáì")||SubjectNName.contains("Computer") ||SubjectNName.contains("computer")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#088fe9"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.computer));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÝáÓÝÉ æÇáãäØÞ")||SubjectNName.contains("Philosophy")||SubjectNName.contains("philosophy")||SubjectNName.contains("ÇáÝáÓÝÉ æÇáãäØÞ 2014")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#65c180"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.logic));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÌÛÑÇÝíÇ")||SubjectNName.contains("ÌÛÑÇÝíÇ 2014")||SubjectNName.contains("ÌÛÑÇÝíÇ")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#f7971e"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.geography));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    	else if(SubjectNName.contains("ÇáÊÇÑíÎ")){
		    	Subject_BG.setBackgroundColor(Color.parseColor("#57a237"));
		    	Image_subject.setImageDrawable(getResources().getDrawable(R.drawable.history));
		    	SubjectName.setTextColor(Color.parseColor("#16669b"));
		    	}
		    expListView.addHeaderView(header);
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader_UNIT, listDataChild_Lessons,listDataChild_Lessons_months,listDataChild_Lessons_HAVEvideosOrnot,SubjectNName,temp2,temp3);
            // Log.d("al arrrrrrrrrrray", listDataChild_Lessons_HAVEvideosOrnot.toString());
     // setting list adapter                      
     expListView.setAdapter(listAdapter);
     // Listview Group click listener
     expListView.setOnGroupClickListener(new OnGroupClickListener() {

     public boolean onGroupClick(ExpandableListView parent, View v,
     int groupPosition, long id) {
     // Toast.makeText(getApplicationContext(),
     // "Group Clicked " + listDataHeader.get(groupPosition),
     // Toast.LENGTH_SHORT).show();
     return false;
     }
     });

     // Listview Group expanded listener
     expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

     public void onGroupExpand(int groupPosition) {

     }
     });

     // Listview Group collasped listener
     expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

     public void onGroupCollapse(int groupPosition) {

     }
     });

     // Listview on child click listener
     expListView.setOnChildClickListener(new OnChildClickListener() {

     public boolean onChildClick(ExpandableListView parent, View v,
     int groupPosition, int childPosition, long id) {
     // TODO Auto-generated method stub
     if(listDataChild_Lessons_HAVEvideosOrnot.get(
     		listDataHeader_UNIT.get(groupPosition)).get(
     			      childPosition).equals("0")){
     CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
     mainAct.changeFragment11(listDataChild_Lessons_ids.get(
     listDataHeader_UNIT.get(groupPosition)).get(
           childPosition),User_TOKEN,semster_ID);
     }
     else{
    	 Toast.makeText(getActivity(), "áÇ ÊæÌÏ ÝíÏíæåÇÊ ÊÔÑÍ åÐÇ ÇáÏÑÓ ÍÊì ÇáÂä", Toast.LENGTH_LONG).show();
     }
     return false;
     }
     });
     				
        if(progressDialog!=null)
        progressDialog.dismiss();
    }
}


@Override
public void onResume() {
    super.onResume();
   
	
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



@Override
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  // Initialize the Fragment.
  
}
@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
  super.onSaveInstanceState(savedInstanceState);
  // Save UI state changes to the savedInstanceState.
  // This bundle will be passed to onCreate if the process is
  // killed and restarted.
 
  // etc.
}
}
