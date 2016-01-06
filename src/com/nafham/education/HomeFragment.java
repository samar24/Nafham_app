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

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.nafham.education.R;
 
public class HomeFragment extends Fragment {
     
	
	ArrayList <String> SubjectNames;
	ListView ls;
	private JSONObject jObj;
	ArrayList  <String> ID;
	ArrayList <String> Subjects_thumbs;
	String Grade_id="";
	String Sems_id="";
	 UserFunctions userFunction = new UserFunctions();
     String json;
	private String User_ID;
	private String Username;
	private String user_TOken;
	private String User_thumb;
	private EasyTracker easyTracker = null;
	 JSONArray jsonArray;
    public HomeFragment(){
    	
    	
    }
public HomeFragment(String Grade_ID,String UT,String SemsterID){
    	
    	this.Grade_id=Grade_ID;
    	this.Sems_id=SemsterID;
    	this.user_TOken=UT;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        easyTracker = EasyTracker.getInstance(getActivity());
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        SubjectNames=new ArrayList<String>();
        ID=new ArrayList<String>();
        Subjects_thumbs=new ArrayList <String>();
        if(Grade_id.equals("")){
        User_ID = getArguments().getString("UserID");  
   	 Username=getArguments().getString("User_name");  
   	 user_TOken=getArguments().getString("user_TOken");  
   	 User_thumb=getArguments().getString("User_thumb"); 
        Grade_id = getArguments().getString("user_grade");   
        Sems_id = getArguments().getString("semsterID"); }
       ls=(ListView) rootView.findViewById(R.id.LSsubjects);
   	if(Sems_id.equals("")){
  		Sems_id=CurrentSemster();
  	}
   /*	if(savedInstanceState!=null){
   		this.Grade_id=savedInstanceState.getString("GRADEID").toString();
   		this.Sems_id=savedInstanceState.getString("SemseterID").toString();
   		this.user_TOken=savedInstanceState.getString("UT").toString();
   		
   	}*/
       new ProgressTask().execute();

       
    	
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
 	        // inflate the listview_item_row.xml parent
 	      LayoutInflater vi;
 	        vi = LayoutInflater.from(getContext());
 	        listItem = vi.inflate(layoutResourceId, parent, false);

 	       
 	      TextView SubjectName = (TextView) listItem
 		         .findViewById(R.id.subjectName);

 		 ImageView IM_subject_picture=(ImageView)listItem
		          .findViewById(R.id.Subject_Image);
 		RelativeLayout LR=(RelativeLayout)listItem
		          .findViewById(R.id.linearLayout1);

 	 SubjectName.setTypeface(typeface1);
  SubjectName.setText(data.get(position));
  if((data.get(position).contains("«··€… «·⁄—»Ì…")||data.get(position).contains("«··€… «·⁄—»Ì…"))&&!data.get(position).contains("HTML")){
	  IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.arabic));
	  LR.setBackgroundColor(Color.parseColor("#74ce8e"));
	  SubjectName.setTextColor(Color.parseColor("#16669b"));
  }
  else if(data.get(position).contains("«·ﬂÌ„Ì«¡") ||data.get(position).contains("Chemistry") || data.get(position).contains("chemistry")){
	  LR.setBackgroundColor(Color.parseColor("#78d0e6"));
	  IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.chemistry));
	  SubjectName.setTextColor(Color.parseColor("#16669b"));
  }else if(data.get(position).contains("«·œ—«”«  «·«Ã „«⁄Ì…")){
	  LR.setBackgroundColor(Color.parseColor("#e46459"));
	  IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.social_studies));
	  SubjectName.setTextColor(Color.parseColor("#16669b"));
	  }
  else if(data.get(position).contains("«·—Ì«÷Ì« ") || data.get(position).contains("Mathematics") || data.get(position).contains("Differential Calculus and Trigonometry") || data.get(position).contains("Mechanics")|| data.get(position).contains("Math")||data.get(position).contains("Mechanics")||data.get(position).contains("Algebra")|| data.get(position).contains("mathematics")){
	  LR.setBackgroundColor(Color.parseColor("#446381"));
	  IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.math));
	  SubjectName.setTextColor(Color.parseColor("#16669b"));
  }else if(data.get(position).contains("«·«Õ’«¡") ||  data.get(position).contains("Statistics") ||  data.get(position).contains("statistics")){
	  LR.setBackgroundColor(Color.parseColor("#52c4df"));
	  IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.statistics));
	  SubjectName.setTextColor(Color.parseColor("#16669b"));
}else if(data.get(position).contains("«·«ﬁ ’«œ") || data.get(position).contains("Economy")|| data.get(position).contains("economy")||data.get(position).contains("«·«ﬁ ’«œ 2014") ){
	 LR.setBackgroundColor(Color.parseColor("#60618f"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.economics));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}else if(data.get(position).contains("⁄·Ê„ »Ì∆Ì… ÊÃÌÊ·ÊÃÌ«")  ||data.get(position).contains("Geology") ||data.get(position).contains("geology")||data.get(position).contains("⁄·Ê„ »Ì∆Ì… ÊÃÌÊ·ÊÃÌ« 2014")){
	 LR.setBackgroundColor(Color.parseColor("#2ab2be"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.geology));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«·›Ì“Ì«¡") || data.get(position).contains("Physics") || data.get(position).contains("physics")){
	 LR.setBackgroundColor(Color.parseColor("#f04d4e"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.physics));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«·√ÕÌ«¡") || data.get(position).contains("Biology")|| data.get(position).contains("biology")|| data.get(position).contains("«·√ÕÌ«¡ 2014")||data.get(position).contains("«·√ÕÌ«¡ ")){
	 LR.setBackgroundColor(Color.parseColor("#92c44b"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.biology));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("⁄·„ «·‰›” Ê«·«Ã „«⁄")|| data.get(position).contains("⁄·„ «·‰›” 2014")|| data.get(position).contains("psychology")||data.get(position).contains("⁄·„ «·≈Ã „«⁄ 2014")||data.get(position).contains("⁄·„ «·‰›”")||data.get(position).contains("⁄·„ «·≈Ã „«⁄")){
	 LR.setBackgroundColor(Color.parseColor("#3a6b9b"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.pyscology));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«··€… «·√·„«‰Ì…")|| data.get(position).contains("Germany")|| data.get(position).contains("Deutsch")||data.get(position).contains("German 2014")){
	 LR.setBackgroundColor(Color.parseColor("#4d4d4d"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.german));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«··€… «·›—‰”Ì…")|| data.get(position).contains("French")|| data.get(position).contains("franÁais")||data.get(position).contains("«··€… «·›—‰”Ì… 2014")){
	 LR.setBackgroundColor(Color.parseColor("#52c4df"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.francis));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«·⁄·Ê„") ||data.get(position).contains("Science") ||data.get(position).contains("science") ){
	 LR.setBackgroundColor(Color.parseColor("#2980b9"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.science));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("‰Ã·Ì“Ì…")||data.get(position).contains("English")||data.get(position).contains("english")||data.get(position).contains("«··€… «·«‰Ã·Ì“Ì…")){
	 LR.setBackgroundColor(Color.parseColor("#2e2e2e"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.english));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
} 
else if(data.get(position).contains("«·Õ«”» «·¬·Ì")||data.get(position).contains("Computer") ||data.get(position).contains("computer")){
	 LR.setBackgroundColor(Color.parseColor("#088fe9"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.computer));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«·›·”›… Ê«·„‰ÿﬁ")||data.get(position).contains("Philosophy")||data.get(position).contains("„»«œ∆ «· ›ﬂÌ— «·›·”›Ï Ê«·⁄·„Ì")||data.get(position).contains("«·›·”›… Ê«·„‰ÿﬁ 2014")||data.get(position).contains("„»«œ∆ «·›·”›…")){
	 LR.setBackgroundColor(Color.parseColor("#65c180"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.logic));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«·Ã€—«›Ì«")||data.get(position).contains("Ã€—«›Ì« 2014")||data.get(position).contains("Ã€—«›Ì«")){
	 LR.setBackgroundColor(Color.parseColor("#f7971e"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.geography));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}
else if(data.get(position).contains("«· «—ÌŒ")){
	 LR.setBackgroundColor(Color.parseColor("#57a237"));
	 IM_subject_picture.setImageDrawable(getResources().getDrawable(R.drawable.history));
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
}

else{
	
	 SubjectName.setTextColor(Color.parseColor("#16669b"));
	 IM_subject_picture.setTag(Subjects_thumbs.get(position));
	new LoadImage(IM_subject_picture).execute();
}
  new LoadImage1(ID.get(position),SubjectName).execute();
 		      // lsa mafrod ha3ml Set le ImageView with the subject Image , weather hagbha mn API OR ha save it with subjectname and check with condtion we kda
 	        return listItem;
 	    }

 	}
   
    public class listViewItem{
		public int imagespp;
		public String title;
		public String subtitle;
	}
    public String CurrentSemster(){
    	String Semster="";
    	Calendar c= Calendar.getInstance();
    			int cyear = c.get(Calendar.YEAR);//calender year starts from 1900 so you must add 1900 to the value recevie.i.e., 1990+112 = 2012
    			int cmonth = c.get(Calendar.MONTH);//this is april so you will receive  3 instead of 4.
    			int cday = c.get(Calendar.DAY_OF_MONTH);
    			Semster=Integer.toString(cmonth);
    			if(Semster.equals("1") || Semster.equals("2" )||Semster.equals("3" )|| Semster.equals("4") || Semster.equals("5") || Semster.equals("6") || Semster.equals("7")){
    				Semster="2";
    				return "2";
    			}
    			if(Semster.equals("0" )|| Semster.equals("11") ||Semster.equals("8" )|| Semster.equals("9") || Semster.equals("10") ){
    				
    				Semster="1";
    				return "1";
    			}
    			return Semster;
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
      			   try {
      				 json = userFunction.GetSubject_grade_semseter(Grade_id,Sems_id);
      				 Log.d("AL GRADE","LOOK ! "+Grade_id);
          		     jObj = new JSONObject(json);
          		  
      		  //  if(jObj.getString("subjects").equals(null)||jObj.getString("subjects").equals("[]")){
      		    	   
      		  //  json=userFunction.GetSubject_grade_semseter(Grade_id,"1");
      		 // jObj = new JSONObject(json);
      		    //  }
      		      
      		    
	 		       jsonArray=jObj.getJSONArray("subjects");
      		 	Log.d("heehehehehehehehehehehe7", json);
      		       for (int i = 0; i < jsonArray.length(); i++) {
      				     try {
      				    	
      						JSONObject obj1 = jsonArray.getJSONObject(i);
      						String Subject_ID=obj1.getString("id");
      						String SUbject_name=obj1.getString("name");
      						String thumb=obj1.getString("thumb");
      						SubjectNames.add(SUbject_name);
      						ID.add(Subject_ID);
      						Subjects_thumbs.add(thumb);
      					} catch (JSONException e) {
      						// TODO Auto-generated catch block
      						e.printStackTrace();
      					}
      				}
      		    } catch (JSONException e) {
      		        Log.e("JSON Parser", "Error parsing data " + e.toString()+Grade_id+Sems_id);
      		    }
      			
      		} catch (IOException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		} 
              getActivity().runOnUiThread(new Runnable() {

            	    @Override
            	    public void run() {
            	    	 ADAPTER adabter= new ADAPTER(getActivity(),R.layout.listitemk,SubjectNames);
            	       		ls.setAdapter(adabter);
            	       		 ls.setOnItemClickListener(new OnItemClickListener() {

            	       				@Override
            	       				public void onItemClick(AdapterView<?> parent, View view,
            	       						int position, long id) {
            	       					CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
            	       		            mainAct.changeFragment(ID.get(position),user_TOken,Sems_id);
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
    class LoadImage extends AsyncTask<Object, Void, Bitmap>{

        private ImageView imv;
        private String path;
		
        public LoadImage(ImageView imv) {
             this.imv = imv;
             this.path = imv.getTag().toString();
            
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
		

        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap result) {
    	 Log.d("eh !! ", this.path );
    	if (!imv.getTag().toString().equals(path)) {
            /* The path is not same. This means that this
               image view is handled by some other async task. 
               We don't do anything and return. */
            return;
     }

     if(result != null && imv != null){
         imv.setVisibility(View.VISIBLE);
         imv.setImageBitmap(result);
         Log.d("eh !! ", "tab ma eshta !!" );
     }else{
     	Log.d("mmmmm yarb",  this.path);
         imv.setVisibility(View.GONE);
     }
     easyTracker.send(MapBuilder.createEvent("HomePage",
				"Home", "theMainPage", null).build());
    }

}
    class LoadImage1 extends AsyncTask<Object, Void, String>{

       
        TextView TV8;
       
		private JSONArray json_array1;
		private int temp2;
		private int temp3;
       private String SubjectID;
        public LoadImage1(String SubjectId,TextView TV5) {
            
             this.SubjectID=SubjectId;
             this.TV8=TV5;
        }

    @Override
    protected String doInBackground(Object... params) {
      
		 try {
	    		
 			json = userFunction.getSubjectDetails(SubjectID);
 		//	Log.d("al Json",json);
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		   try {
 			 
 	     jObj = new JSONObject(json);
 	     jsonArray=jObj.getJSONArray("units");
 	    
 	     for (int i = 0; i < jsonArray.length(); i++) {
 			     try {
 			    	
 					JSONObject obj1 = jsonArray.getJSONObject(i);
 					String UnitName=obj1.getString("unit_name");
 					
 					json_array1=obj1.getJSONArray("lessons");
 					  for (int j = 0; j < json_array1.length(); j++) {
 							JSONObject obj2 = json_array1.getJSONObject(j);
 							
 							 String temp=obj2.getString("videos_count");
 							 int temp1=Integer.parseInt(temp);
 							temp2=temp2+temp1;
 							
 							
 							
 					  }
 					 
 				
 				} catch (JSONException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			}
 	   
 	  } catch (JSONException e) {
 	      Log.e("JSON Parser", "Error parsing data " + e.toString());
 	  }

		

        return "";
    }
    @Override
    protected void onPostExecute(String result) {
    	if(temp2==0){
    		TV8.setTextColor(Color.parseColor("#A8A8A8"));
    	}
    	else{
    		TV8.setTextColor(Color.parseColor("#16669b"));
    	}
    	
     easyTracker.send(MapBuilder.createEvent("HomePage",
				"Home", "theMainPage", null).build());
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
}