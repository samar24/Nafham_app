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


import com.nafham.education.R;
import com.nafham.education.HomeFragment.ADAPTER;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class search_fragment extends Fragment{
	String theTargetWord="";
	//EditText searched_word;
	ListView TheResults;
	 boolean flag_loading=false;
	//RelativeLayout Results_layout;
	//TextView Target_word;
	ProgressBar PB;
	 boolean flag=false;
	  ADAPTER adabter;
	  int Len=0;
	  int j=0;
	//Button Search;
	List <String> results_grade_names;
	List <String> results_SubjectNames;
	List <String> results_thumbnails;
	List <String> results_IDs;
	List <String>results_name;
	List <String> results_grade_names1;
	List <String> results_SubjectNames1;
	List <String> results_thumbnails1; 
	List <String> results_IDs1;
	List <String>results_name1;
	List <Bitmap> Images_trials;
	 JSONObject jObj;
	 UserFunctions userFunction = new UserFunctions();
	 Typeface typeface2;
     String json;
	private JSONArray jsonArray;
	private String user_TOken;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		
	    	//Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	    	
	    	
	        View rootView = inflater.inflate(R.layout.search, container, false);
	        results_grade_names=new ArrayList<String>();
	        results_SubjectNames=new ArrayList<String>();
	        results_thumbnails=new ArrayList<String>();
	        results_IDs=new ArrayList<String>();
	        results_name=new ArrayList<String>();
	        results_grade_names1=new ArrayList<String>();
	        results_SubjectNames1=new ArrayList<String>();
	        results_thumbnails1=new ArrayList<String>();
	        results_IDs1=new ArrayList<String>();
	        results_name1=new ArrayList<String>();
	        Images_trials=new ArrayList<Bitmap>();
	        user_TOken=getArguments().getString("user_TOken");  
	        Log.d("mmmm tayep", theTargetWord);
	        theTargetWord = getArguments().getString("the_searchableWord");
	       // Results_layout=(RelativeLayout)rootView.findViewById(R.id.linearLayout1);
	        //Target_word=(TextView)rootView.findViewById(R.id.textView1);
	        TheResults=(ListView)rootView.findViewById(R.id.listView1);
	        PB=(ProgressBar)rootView.findViewById(R.id.productlist_progressbar);
	        PB.setVisibility(View.GONE);
	    	//Results_layout.setVisibility(View.GONE);
	    	typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	    	Log.d("mmmm tayep", theTargetWord);
	        if(!theTargetWord.equals("")){
	        	//  Results_layout.setVisibility(View.VISIBLE);
	        	 // Target_word.setTypeface(typeface2);
		        	//Target_word.setText("‰ «∆Ã «·»ÕÀ ⁄‰ "+theTargetWord);
		        	 new ProgressTask1().execute();
	        }else{
	        	
	        }
	       
	      
	        return rootView;
	    }
	 public class ADAPTER extends ArrayAdapter<String> {

	 	   Context mContext;
	 	   int layoutResourceId;
	 	   List<String> data;

	 	    /*
	 	     * @mContext - app context
	 	     * 
	 	     * @layoutResourceId - the listview_item_row.xml
	 	     * 
	 	     * @data - the ListItem data
	 	     */
	 	    public ADAPTER(Context mContext, int layoutResourceId, List<String>data) {

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
		    	 Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	 	        // inflate the listview_item_row.xml parent
	 	      LayoutInflater vi;
	 	        vi = LayoutInflater.from(getContext());
	 	        listItem = vi.inflate(layoutResourceId, parent, false);

	 	    
	 	      ImageView thumbnail = (ImageView) listItem
	 		         .findViewById(R.id.Lesson_Image);
	 	     thumbnail.setScaleType(ImageView.ScaleType.FIT_XY);  
	 		 TextView Lesson_name=(TextView)listItem
			          .findViewById(R.id.textView1);
	 		TextView Lesson_GradeNamePlusLesson_subject=(TextView)listItem
			          .findViewById(R.id.textView2);
	 		
	 		Lesson_name.setTypeface(typeface1);
	 		Lesson_GradeNamePlusLesson_subject.setTypeface(typeface2);
	 		
	 		Lesson_name.setText(results_name.get(position));
	 		Lesson_GradeNamePlusLesson_subject.setText(results_grade_names.get(position)+"-"+results_SubjectNames.get(position));
	 		thumbnail.setImageBitmap(Images_trials.get(position));
	 		
	 	        return listItem;
	 	    }

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
	 class ProgressTask extends AsyncTask<String, Integer, String> {

	        private ProgressDialog progressDialog;
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
			        	
						json = userFunction.Search_lesson(theTargetWord);
						//jObj = new JSONObject(json);
						
						  jsonArray=new JSONArray(json);
						  Len=jsonArray.length();
						  j=results_name.size()-1;
					       for (int i = j; i <jsonArray.length(); i++) {
							     try {
							    	
									JSONObject obj1 = jsonArray.getJSONObject(i);
									results_name.add(obj1.getString("name"));
									results_IDs.add(obj1.getString("id"));
									results_grade_names.add(obj1.getString("grade_name"));
									Images_trials.add(getBitmapFromURL(obj1.getString("thumb")));
									
									results_SubjectNames.add(obj1.getString("subject_name"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							     k++;
							     if(k<9){
							     j=results_name.size()-1;
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
	          	    	//Toast.makeText(getActivity(), ""+json, Toast.LENGTH_LONG).show();
	          	    	if(!json.equals(null)||!json.equals("")){
	          	    		
							}}
	          	});
	      		 return "done";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            // TODO Auto-generated method stub
	        	if(json.equals(null)||json.equals("")){
	        		Toast.makeText(getActivity(),"No Result Found please try another word.",Toast.LENGTH_LONG).show();
      	    	}
	        	adabter.notifyDataSetChanged();
	            super.onPostExecute(result);
	         //   PB.setVisibility(View.GONE);
	            if(progressDialog!=null)
	            progressDialog.dismiss();
	        }
	    }
	 
	 class ProgressTask1 extends AsyncTask<String, Integer, String> {

	        private ProgressDialog progressDialog;
			
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
			        	
						json = userFunction.Search_lesson(theTargetWord);
						//jObj = new JSONObject(json);
						Log.d("alSearchSS", json);
						  jsonArray=new JSONArray(json);
						  Len=jsonArray.length();
					       for (int i = j; i <9; i++) {
							     try {
							    	
									JSONObject obj1 = jsonArray.getJSONObject(i);
									results_name.add(obj1.getString("name"));
									results_IDs.add(obj1.getString("id"));
									results_grade_names.add(obj1.getString("grade_name"));
									Images_trials.add(getBitmapFromURL(obj1.getString("thumb")));
									results_SubjectNames.add(obj1.getString("subject_name"));
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

	          	    @Override
	          	    public void run() {
	          	    	//Toast.makeText(getActivity(), ""+json, Toast.LENGTH_LONG).show();
	          	    	if(!json.equals(null)||!json.equals("")){
	          	    	  adabter = new ADAPTER(getActivity(),R.layout.list_item_search,results_name);
	          	    	adabter.notifyDataSetChanged();
	        			  TheResults.setAdapter(adabter);
	        			  TheResults.setOnScrollListener(new OnScrollListener() {
	        					
	        					

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
	        							if(results_name.size()>=Len){
	        								//Toast.makeText(getActivity(), "he3he3 ", Toast.LENGTH_LONG).show();
	        							}else if(results_name.size()-1<Len){
	        								//Toast.makeText(getActivity(), "hooooooo3hoooo3 ", Toast.LENGTH_LONG).show();
	        							 new ProgressTask().execute();}
	        							}
	        					}

	        					
	        				});
	        			  TheResults.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {
									   CopyOfMainActivity mainAct = (CopyOfMainActivity)getActivity();
							            mainAct.changeFragment1(results_IDs.get(position),user_TOken);
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
	        	if(json.equals(null)||json.equals("")){
	        		Toast.makeText(getActivity(),"No Result Found please try another word.",Toast.LENGTH_LONG).show();
   	    	}
	        	
	            super.onPostExecute(result);
	         //   PB.setVisibility(View.GONE);
	            if(progressDialog!=null)
	            progressDialog.dismiss();
	        }
	    }
	 
}
