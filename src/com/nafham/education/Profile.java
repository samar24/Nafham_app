package com.nafham.education;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nafham.education.search_fragment.ADAPTER;
import com.nafham.education.search_fragment.ProgressTask;
import com.nafham.education.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
 
public class Profile extends Fragment {
  
	//ImageView User_PP;
	//TextView UN;
	  int j=0;
	  int Len=0;
	  ADAPTER adabter;
	  ADAPTER2 adabter1;
	//TextView UserTypeANdHisGrade;
	//TextView School ;
	//TextView GovernmentANdCountry;
	
	ListView ta7desat;
	//RelativeLayout USERINFOLAYOUT;
//	RelativeLayout TitleOFList;
	//TextView TITLEE;
	
String Username="";
     String User_ID="";
	 String user_TOken="";
	 String User_thumb="";
	 Bitmap UUSer_PP;
		String U_ID="";
			String The_USER_name="";
			String User_stage="";
			String Stage_name="";
			String The_user_thumb="";
			String TheUser_type="";
	 UserFunctions userFunction=new UserFunctions();
	 String json="";
	 JSONObject jObj;
	 ArrayList <String>user_lates_videos_names;
 	ArrayList <Bitmap>user_lates_videos_thumbs;
	ArrayList <String>user_lates_videos_IDs;
	ArrayList <String>user_lates_videos_SubjectNames;
	ArrayList <String>user_lates_videos_GradeNames;
    ArrayList <String> habl=new ArrayList <String>();
	private String json1;
	private JSONObject jObj2;
	private JSONArray jsonArray;
	
	 Typeface typeface1;
	 Typeface typeface2;
	private Bitmap nitmap;
	private static Xfermode setXfermode;
	public Profile(String userID,String UserToken){
    	this.User_ID=userID;
    	this.user_TOken=UserToken;
    	
    }
	public Profile(){
		
		
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
    	 if(User_ID.equals("")){
    	 User_ID = getArguments().getString("UserID");  
    	 Username=getArguments().getString("User_name");  
    	 user_TOken=getArguments().getString("user_TOken");  
    	 User_thumb=getArguments().getString("User_thumb");}
        View rootView = inflater.inflate(R.layout.profile, container, false);
        user_lates_videos_names=new ArrayList<String>();
    	user_lates_videos_thumbs=new ArrayList<Bitmap>();
    	user_lates_videos_IDs=new ArrayList<String>();
    	user_lates_videos_SubjectNames=new ArrayList<String>();
    user_lates_videos_GradeNames=new ArrayList<String>();
    ta7desat=(ListView)rootView.findViewById(R.id.the_list_OF_ta7desaaat);
      //  TITLEE=(TextView)rootView.findViewById(R.id.torksharhdars);
    habl.add("0");
    habl.add("1");
    user_lates_videos_names.add("");//reserving the first two position with headers
    user_lates_videos_names.add("");
    user_lates_videos_thumbs.add(nitmap);
    user_lates_videos_thumbs.add(nitmap);
    user_lates_videos_IDs.add("");
    user_lates_videos_IDs.add("");
    user_lates_videos_SubjectNames.add("");
    user_lates_videos_SubjectNames.add("");
    user_lates_videos_GradeNames.add("");
    user_lates_videos_GradeNames.add("");
         typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
   	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
   	//TITLEE.setTypeface(typeface1);
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
   		 LayoutInflater vi;
 	        View listItem = convertView;
 	       Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
 	    	 Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
 	        // inflate the listview_item_row.xml parent
 	    	 Log.d("yabnnnnnnnnnny", "rabna yahdek -_- ! ");
 	    	 if(position==1){
 	    		 Log.d("yabnnnnnnnnnny", "rabna yahdek ! ");
 	    		  vi = LayoutInflater.from(getContext());
 	  	        listItem = vi.inflate(R.layout.header, parent, false);
 	  	      Log.d("ant hna", "okay2");
 	  	   TextView   TITLEE=(TextView)listItem
 			          .findViewById(R.id.torksharhdars);
 	  	 TITLEE.setTypeface(typeface1);
 	    	 }
 	    	 else if(position==0){
 	    		 Log.d("yabnnnnnnnnnny", "rabna yahdek ! ");
 	    		  vi = LayoutInflater.from(getContext());
 		  	        listItem = vi.inflate(R.layout.header2, parent, false);
 		  	      Log.d("ant hna", "okay1");
 		  //	RelativeLayout      USERINFOLAYOUT=(RelativeLayout) listItem
 			      //    .findViewById(R.id.relativeLayout1);
 		            
 		            //  TitleOFList=(RelativeLayout) rootView.findViewById(R.id.Notifications_layout1);
 		            //  USERINFOLAYOUT.setVisibility(View.GONE);
              
 		            ImageView      User_PP=(ImageView)listItem
 					          .findViewById(R.id.User_Photo);
 		           TextView       UN=(TextView)listItem
 		    			          .findViewById(R.id.User_name);
 		           TextView      UserTypeANdHisGrade  =(TextView)listItem
 		    			          .findViewById(R.id.UserTypeAndHisGrade);
 		           TextView      GovernmentANdCountry=(TextView)listItem
 		    			          .findViewById(R.id.GovernomentAndCountry);
 		           TextView      School=(TextView)listItem
 		    			          .findViewById(R.id.School);
 		           UUSer_PP=getBitmapFromURL(The_user_thumb);
 		           // UUSer_PP1=getRoundedShape(UUSer_PP);
 					User_PP.setImageBitmap(UUSer_PP);
 					UN.setTypeface(typeface1);
 					UN.setText(The_USER_name);
 					
 					
 					UserTypeANdHisGrade.setTypeface(typeface2);
 					UserTypeANdHisGrade.setText(Stage_name+" - "+TheUser_type);
 					
 					GovernmentANdCountry.setTypeface(typeface2);
 					GovernmentANdCountry.setText("«·ﬁ«Â—…"+" - "+"„’—");
 					School.setTypeface(typeface2);
 					School.setText("«·„œ—”…");
 					// USERINFOLAYOUT.setVisibility(View.VISIBLE);
 			
 	    	 }
 	    	 else{
 	    		 if(!user_lates_videos_GradeNames.isEmpty()){
 	    			 Log.d("ant hna", "okay");
 	        vi = LayoutInflater.from(getContext());
 	        listItem = vi.inflate(layoutResourceId, parent, false);

 	       
 	      ImageView thumbnail = (ImageView) listItem
 		         .findViewById(R.id.Lesson_Image);

 		 TextView Lesson_name=(TextView)listItem
 		          .findViewById(R.id.textView1);
 		TextView Lesson_GradeNamePlusLesson_subject=(TextView)listItem
 		          .findViewById(R.id.textView2);
 		
 		Lesson_name.setTypeface(typeface1);
 		Lesson_GradeNamePlusLesson_subject.setTypeface(typeface2);
 		
 		Lesson_name.setText(data.get(position));
 		Lesson_GradeNamePlusLesson_subject.setText(user_lates_videos_GradeNames.get(position)+"-"+user_lates_videos_SubjectNames.get(position));
 		//Lesson_GradeNamePlusLesson_subject.setText(results_grade_names.get(position)+"-"+results_SubjectNames.get(position));
 		thumbnail.setImageBitmap(user_lates_videos_thumbs.get(position));}
 	    	 }
 	        return listItem;
 	    }

   	}
    public class ADAPTER2 extends ArrayAdapter<String> {

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
  	    public ADAPTER2(Context mContext, int layoutResourceId, ArrayList<String>data) {

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
  		 LayoutInflater vi;
	        View listItem = convertView;
	       Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	    	 Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	        // inflate the listview_item_row.xml parent
	    	
	    	 if(position==1){
	    		 Log.d("hay hay", "rabna .. ! ");
	    		  vi = LayoutInflater.from(getContext());
	  	        listItem = vi.inflate(R.layout.header, parent, false);
	  	     
	  	   TextView   TITLEE=(TextView)listItem
			          .findViewById(R.id.torksharhdars);
	  	 TITLEE.setTypeface(typeface1);
	    	 }
	    	 else if(position==0){
	    		 
	    		  vi = LayoutInflater.from(getContext());
		  	        listItem = vi.inflate(R.layout.header2, parent, false);
		  	     
		  //	RelativeLayout      USERINFOLAYOUT=(RelativeLayout) listItem
			      //    .findViewById(R.id.relativeLayout1);
		            
		            //  TitleOFList=(RelativeLayout) rootView.findViewById(R.id.Notifications_layout1);
		            //  USERINFOLAYOUT.setVisibility(View.GONE);
             
		            ImageView      User_PP=(ImageView)listItem
					          .findViewById(R.id.User_Photo);
		           TextView       UN=(TextView)listItem
		    			          .findViewById(R.id.User_name);
		           TextView      UserTypeANdHisGrade  =(TextView)listItem
		    			          .findViewById(R.id.UserTypeAndHisGrade);
		           TextView      GovernmentANdCountry=(TextView)listItem
		    			          .findViewById(R.id.GovernomentAndCountry);
		           TextView      School=(TextView)listItem
		    			          .findViewById(R.id.School);
		           UUSer_PP=getBitmapFromURL(The_user_thumb);
		           // UUSer_PP1=getRoundedShape(UUSer_PP);
					User_PP.setImageBitmap(UUSer_PP);
					UN.setTypeface(typeface1);
					UN.setText(The_USER_name);
					
					
					UserTypeANdHisGrade.setTypeface(typeface2);
					UserTypeANdHisGrade.setText(Stage_name+" - "+TheUser_type);
					
					GovernmentANdCountry.setTypeface(typeface2);
					GovernmentANdCountry.setText("«·ﬁ«Â—…"+" - "+"„’—");
					School.setTypeface(typeface2);
					School.setText("«·„œ—”…");
					// USERINFOLAYOUT.setVisibility(View.VISIBLE);
			
	    	 }
	        return listItem;
	    }

  	}
    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
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
    	 }
        class ProgressTask extends AsyncTask<String, Integer, String> {

        private ProgressDialog progressDialog;
		private String json3;
		private JSONObject jObj3;
int k=0;
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
     			json = userFunction.User_details(User_ID);
     			Log.d("haaaaaaaaaaaaaaaa3", json);
     			jObj = new JSONObject(json);
     			
     				
     			 U_ID=	jObj.getString("id");
     			 The_USER_name =	jObj.getString("name");
     			 TheUser_type=	jObj.getString("type");
     			 if(TheUser_type.equals("ÿ«·»")||TheUser_type.equals("„⁄·„")){
     			 User_stage=	jObj.getString("stage");
     			Stage_name=	jObj.getString("stage_name");}
     			 else {
     				Stage_name="„”«Â„";
     			 }
     			 The_user_thumb=	jObj.getString("thumb");
     			// TheUser_type=	jObj.getString("type");
     		
               
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (JSONException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        	 try {
      			json1 = userFunction.User_LatestVideos(User_ID);
      			 jsonArray=new JSONArray(json1);
     			  Len=jsonArray.length();
      			//jObj2 = new JSONObject(json1);
      			if(Len>0){
      			if(Len>=9){
      				k=9;
      			}
      			else{
      				
      				k=Len;
      			}
      			  for (int i = j; i <k; i++) {
      				     try {
      						JSONObject obj1 = jsonArray.getJSONObject(i);
      						String Lesson_name=obj1.getString("name");
      						String Lesson_ID=obj1.getString("id");
      						String Lesson_thumb=obj1.getString("thumb");
      						json3 = userFunction.GetLessonDetails(Lesson_ID);
      						jObj3 = new JSONObject(json3);
      						String Lesson_subjectName=jObj3.getString("subject_name");
      						String Lesson_Grade_name=jObj3.getString("grade_name");
      						user_lates_videos_names.add(Lesson_name);
      						user_lates_videos_IDs.add(Lesson_ID);
      						user_lates_videos_thumbs.add(getBitmapFromURL(Lesson_thumb));
      						user_lates_videos_SubjectNames.add(Lesson_subjectName);
      						user_lates_videos_GradeNames.add(Lesson_Grade_name);
      					} catch (JSONException e) {
      						// TODO Auto-generated catch block
      						e.printStackTrace();
      					}
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

          	    @Override
          	    public void run() {
          	    	if(Len==0){
          	  adabter1= new ADAPTER2(getActivity(),R.layout.list_item_profile,habl);
          	  ta7desat.setAdapter(adabter1);
          	 // Toast.makeText(getActivity(), "Okay ant re5m", Toast.LENGTH_LONG).show();
          	    	}
          	    	else{
          	    		 adabter= new ADAPTER(getActivity(),R.layout.list_item_profile,user_lates_videos_names);
          	          	adabter.notifyDataSetChanged();
          	          	  ta7desat.setAdapter(adabter);
          	          //	 Toast.makeText(getActivity(), "mesh fahmaaaak", Toast.LENGTH_LONG).show();
          	    	}
          	  	if(Len>0){
          	    	ta7desat.setOnScrollListener(new OnScrollListener() {
      					@Override
      					public void onScrollStateChanged(AbsListView view, int scrollState) {
      						// TODO Auto-generated method stub
      						
      					}
      					
      					@Override
      					public void onScroll(AbsListView view, int firstVisibleItem,
      							int visibleItemCount, int totalItemCount) {
      						// TODO Auto-generated method stub
      						
      						 if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
      				            {
      							if(user_lates_videos_names.size()>=Len){
      								//Toast.makeText(getActivity(), "he3he3 ", Toast.LENGTH_LONG).show();
      							}else if(user_lates_videos_names.size()-1<Len){
      								//Toast.makeText(getActivity(), "hooooooo3hoooo3 ", Toast.LENGTH_LONG).show();
      							 new ProgressTask1().execute();}
      							}
      					}

      					
      				});
          			ta7desat.setOnItemClickListener(new OnItemClickListener() {

          					@Override
          					public void onItemClick(AdapterView<?> parent, View view,
          							int position, long id) {
          						CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
          				            mainAct.changeFragment1(user_lates_videos_IDs.get(position),user_TOken);
          					}
          					
          				});
          	    }
          	    }
          	});
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
        class ProgressTask1 extends AsyncTask<String, Integer, String> {

            private ProgressDialog progressDialog;
    		private String json3;
    		private JSONObject jObj3;
    		private int k=0;
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
         			json = userFunction.User_details(User_ID);
         			Log.d("haaaaaaaaaaaaaaaa3", json);
         			jObj = new JSONObject(json);
         			
         				
         			 U_ID=	jObj.getString("id");
         			 The_USER_name =	jObj.getString("name");
         			 User_stage=	jObj.getString("stage");
         			 Stage_name=	jObj.getString("stage_name");
         			 The_user_thumb=	jObj.getString("thumb");
         			 TheUser_type=	jObj.getString("type");
         		
                   
         		} catch (IOException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		} catch (JSONException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
            	  try {
          			json1 = userFunction.User_LatestVideos(User_ID);
          			//jObj2 = new JSONObject(json1);
          			 jsonArray=new JSONArray(json1);
          			  Len=jsonArray.length();
					  j=user_lates_videos_names.size()-1;
					  for (int i = j; i <jsonArray.length(); i++) {
          				     try {
          						JSONObject obj1 = jsonArray.getJSONObject(i);
          						String Lesson_name=obj1.getString("name");
          						String Lesson_ID=obj1.getString("id");
          						String Lesson_thumb=obj1.getString("thumb");
          						json3 = userFunction.GetLessonDetails(Lesson_ID);
          						jObj3 = new JSONObject(json3);
          						String Lesson_subjectName=jObj3.getString("subject_name");
          						String Lesson_Grade_name=jObj3.getString("grade_name");
          						user_lates_videos_names.add(Lesson_name);
          						user_lates_videos_IDs.add(Lesson_ID);
          						user_lates_videos_thumbs.add(getBitmapFromURL(Lesson_thumb));
          						user_lates_videos_SubjectNames.add(Lesson_subjectName);
          						user_lates_videos_GradeNames.add(Lesson_Grade_name);
          					} catch (JSONException e) {
          						// TODO Auto-generated catch block
          						e.printStackTrace();
          					}
          				   k++;
						     if(k<9){
						     j=user_lates_videos_names.size()-1;
						     }else{
						    	 break;
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
                adabter.notifyDataSetChanged();
                if(progressDialog!=null)
                progressDialog.dismiss();
            }
        }  
}
    