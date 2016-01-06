package com.nafham.education;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.nafham.education.HomeFragment.ADAPTER;
import com.nafham.education.R;



public class Lesson_parts extends Fragment  {
	 
	 UserFunctions userFunction = new UserFunctions();
	
	
	private String json;
	private JSONArray jsonArray;
	
	 JSONArray jsonArray1;
	 JSONArray jsonArray2;
	boolean have_parts=false;
	private ArrayList<String> Lesson_parts_orders;
	private ArrayList<String> Lesson_Videos_ids_parts;
	 View rootView;
	 private String LessonName;
	  boolean youTubePlayerIsFullScreen;
	 
	private String json1="";
	private JSONObject jObj2;
	 Typeface typeface1;
	 Typeface typeface2;
	ListView LV_parts;
	private TableLayout table_related_Tamren;
	private TableLayout table_related_TTbekat;
	 String SemID="";
RelativeLayout theLayoutWithTit;
TextView Lesson_title;

	private String Lesson_id;


	private String User_token;
	
	public Lesson_parts(String LessonID,String UT,String SemsterID) {
		
		this.Lesson_id=LessonID;
		this.User_token=UT;
		this.SemID=SemsterID;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		 rootView = inflater.inflate(R.layout.lesson_parts, container, false);
		  typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
    	  Lesson_parts_orders=new ArrayList<String>();
    	  Lesson_Videos_ids_parts=new ArrayList<String>();
    	  LV_parts=(ListView) rootView.findViewById(R.id.lS_parts);
    	  theLayoutWithTit=(RelativeLayout)rootView.findViewById(R.id.relativeLayout1);
    	  Lesson_title=(TextView)rootView.findViewById(R.id.LessonName);
    	  theLayoutWithTit.setVisibility(View.GONE);
    	  Lesson_title .setVisibility(View.GONE);
    			    new ProgressTask().execute();
		   
	    return rootView;
	}

		
		
		
		class ProgressTask extends AsyncTask<String, Integer, String>  {

	        private ProgressDialog progressDialog;
			private JSONObject jObj;
			
			
			

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
		 			} catch (IOException e1) {
		 				// TODO Auto-generated catch block
		 				e1.printStackTrace();
		 			}
		 			   try {
		 		     jObj = new JSONObject(json);
		 		      LessonName=jObj.getString("lesson_name");
		 		    
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
		 		  
		 		   if(!jObj.getString("parts").equals(null)){
			 		     jsonArray1=jObj.getJSONArray("parts");
			 		     Log.d("hereherehereherehere",jObj.getString("parts") );
			 		     for (int i = 0; i < jsonArray1.length(); i++) {
			 		    	JSONObject obj1 = jsonArray1.getJSONObject(i);
		 					String Part_order=obj1.getString("order");
		 					String Part_video_ID=obj1.getString("video_id");
			 			   Lesson_parts_orders.add(Part_order);
			 			   Lesson_Videos_ids_parts.add(Part_video_ID);
			 			}
			 		  
			 		    }
		 		  
		 		  } catch (JSONException e) {
		 		      Log.e("JSON Parser", "Error parsing data " + e.toString());
		 		  }
		 		
	        	  getActivity().runOnUiThread(new Runnable() {
                           
				
					@Override
	          	    public void run() {
						 ADAPTER adabter= new ADAPTER(getActivity(),R.layout.lesson_parts_item,Lesson_parts_orders);
         	       		LV_parts.setAdapter(adabter);
         	       		 LV_parts.setOnItemClickListener(new OnItemClickListener() {

         	       				@Override
         	       				public void onItemClick(AdapterView<?> parent, View view,
         	       						int position, long id) {
         	       				CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
      				          mainAct.changeFragment1114(Lesson_id,User_token,Lesson_Videos_ids_parts.get(position));
         	       				}
         	       				
         	       			});
					}});
	      		 return "done";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            // TODO Auto-generated method stub
	            super.onPostExecute(result);
	            theLayoutWithTit.setVisibility(View.VISIBLE);
	            Lesson_title.setVisibility(View.VISIBLE);
	            Lesson_title.setText(LessonName);
	            Lesson_title.setTypeface(typeface1);
	            if(progressDialog!=null)
	            progressDialog.dismiss();
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
		 	    	
		 	        View listItem = convertView;
		 	       typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
		     	  typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
		 	        // inflate the listview_item_row.xml parent
		 	      LayoutInflater vi;
		 	        vi = LayoutInflater.from(getContext());
		 	        listItem = vi.inflate(layoutResourceId, parent, false);
		 	       TextView Order = (TextView) listItem
		 	 		         .findViewById(R.id.Order_part);

		 	 		 TextView Name=(TextView)listItem
		 			          .findViewById(R.id.Part_name);
		 	 		RelativeLayout LR_Order=(RelativeLayout)listItem
		 			          .findViewById(R.id.linearLayout1);
		 	 		RelativeLayout LR_name=(RelativeLayout)listItem
		 			          .findViewById(R.id.linearLayout3);
		 	 		 if ((position % 2) == 0) {
		 	 			Order.setText(Lesson_parts_orders.get(position));
		 	 			Name.setText(LessonName+" "+"-"+"Ì"+Lesson_parts_orders.get(position));
		 	 			Order.setTextColor(Color.parseColor("#222222"));
		 	 			Name.setTextColor(Color.parseColor("#005580"));
		 	 			LR_Order.setBackgroundColor(Color.parseColor("#f9f9f9"));
		 	 			LR_name.setBackgroundColor(Color.parseColor("#f2f2f2"));
		 	 		 }
		 	 		 else{
		 	 			Order.setText(Lesson_parts_orders.get(position));
		 	 			Name.setText(LessonName+" "+"-"+"Ì"+Lesson_parts_orders.get(position));
		 	 			Order.setTextColor(Color.parseColor("#222222"));
		 	 			Name.setTextColor(Color.parseColor("#005580"));
		 	 			LR_Order.setBackgroundColor(Color.parseColor("#f9f9f9"));
		 	 			LR_name.setBackgroundColor(Color.parseColor("#f7f7f7"));
		 	 		 }
		 	 		Name.setTypeface(typeface2);
		 	 		Order.setTypeface(typeface1);
		 	      // lsa mafrod ha3ml Set le ImageView with the subject Image , weather hagbha mn API OR ha save it with subjectname and check with condtion we kda
		 	        return listItem;
		 	    }

		 	}
		   
		}