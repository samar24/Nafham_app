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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.nafham.education.R;
import com.nafham.education.Subject_fragment.ProgressTask;


public class Single_lesson_fragment extends Fragment  {
	
	 View rootView;
	 String Lesson_id;
	 YouTubePlayer youTubePlayer;
	 private JSONObject jObj;
	 Button Fehmt;
	 TextView The_lesson_authorName;
	// TextView Lesson_Grade;
	// TextView Lesson_SUbject;  
	 TextView MainLessOnName;
	 private EasyTracker easyTracker = null;
	 ImageView User_thumb;
	 Bitmap User_photo;
	 String User_token="";
	 boolean fullScreenMode=false;
	 YouTubePlayerFragment  youTubePlayer1;
	 UserFunctions userFunction = new UserFunctions();
	private static final String YoutubeDeveloperKey = "AIzaSyAqAJgHz6bz_5NtiHJz3KlVbbZ94u3ykXc";
	 
	public static String VIDEO_ID = "o7VVHhK9zf0";   
	String LessonTYPE="";
	 private boolean fullScreen=false;
	private String json;
	private JSONArray jsonArray;
	List<String> RelatedLessons = new ArrayList<String>();
	List<String> Related_lessons_thumb = new ArrayList<String>();
	List<String> Related_lessons_IDS = new ArrayList<String>();
	 JSONArray jsonArray1;
	 JSONArray jsonArray2;
	private List<String> RelatedTmaren=new ArrayList<String>();
	private List<String> RelatedTmaren_thumb=new ArrayList<String>();
	private List<String> RelatedTmaren_IDS=new ArrayList<String>();
	private List<String> Relatedttbekat=new ArrayList<String>();
	private List<String> Relatedttbekat_thumb=new ArrayList<String>();
	private List<String> Relatedttbekat_IDS=new ArrayList<String>();
	private String Lesson_grade="";
	private String Lesson_subject="";
	private String Lesson_User="";
	private String Author_id="";
	private String MainLessonName="";
	private String Lesson_thumb="";
	private String Lesson_vidio_ID="";
	 String FehmoCount="";
	
	private String json1;
	private JSONObject jObj2;
	 Typeface typeface1;
	 Typeface typeface2;
	 
	public Single_lesson_fragment(String LessonID,String UT) {
		
		this.Lesson_id=LessonID;
		this.User_token=UT;
	}
public Single_lesson_fragment(String LessonID,String UT,String VideoID) {
		
		this.Lesson_id=LessonID;
		this.User_token=UT;
		this.Lesson_vidio_ID=VideoID;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		getActivity().getActionBar().hide();
		easyTracker = EasyTracker.getInstance(getActivity());
		 rootView = inflater.inflate(R.layout.activity_single_lesson, container, false);
		
		//if(savedInstanceState!=null){
		//	this.Lesson_id=savedInstanceState.getString("LessonId").toString();
		//}
		 // typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	 // typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
    	
		 //   Fehmt=(Button)rootView.findViewById(R.id.FehmtButton);
		//    The_lesson_authorName=(TextView)rootView.findViewById(R.id.user_lesson_name);
		//    Lesson_Grade=(TextView)rootView.findViewById(R.id.Grade_name_text);
		 //   Lesson_SUbject=(TextView)rootView.findViewById(R.id.Subject_name_text);
		  //  MainLessOnName=(TextView)rootView.findViewById(R.id.LessonName);
		 //   User_thumb=(ImageView)rootView.findViewById(R.id.imageView1);
		  
		   
		  
		    new ProgressTask().execute();
		   
	    return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	@Override
	public void onStart() {
		   youTubePlayer1 = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
		   EasyTracker.getInstance(getActivity()).activityStart(getActivity());
	    super.onStart();
	}
	

	
		 protected YouTubePlayer.Provider getYouTubePlayerProvider() {
	    return (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
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

		    public TextImagePair(Context context, AttributeSet attributeSet, String text, Bitmap bm) {
		        super(context, attributeSet);

		        // Inflate the layout
		        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        inflator.inflate(R.layout.the_whole_element_in_hscrolview, this);

		        TextView tvText = (TextView)this.findViewById(R.id.TextOnImage11);
		        tvText.setText(text);

		        ImageView imgView = (ImageView)this.findViewById(R.id.imageView11);
		        imgView.setImageBitmap(bm);
		    }
		}
		@Override
		public void onDestroyView() {
	           super.onDestroyView(); 
	           //getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	          // youTubePlayer.setFullscreen(false);
	           getActivity().getActionBar().show();
	           if (this.youTubePlayer1 != null
	                   && getFragmentManager().findFragmentById(
	                		   R.id.youtubeplayerfragment  ) != null){
	           Fragment fragment =  (getFragmentManager().findFragmentById(R.id.youtubeplayerfragment));   
	           FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
	           ft.remove(fragment);
	           ft.commit();
	           this.youTubePlayer1 = null;
	           if (fullScreen){
	              youTubePlayer.pause();
	             youTubePlayer.setFullscreen(false);
	             //  Toast.makeText(getActivity(), "out of Full Screen", Toast.LENGTH_LONG).show();
	           }
	         
	           }
	          
	           getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    } 
		class ProgressTask extends AsyncTask<String, Integer, String> implements OnInitializedListener {

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
	 				json = userFunction.GetLessonDetails(Lesson_id);
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
	 		      FehmoCount=jObj.getString("fehmo_count");
	 		    
	 		    /*  json1=userFunction.User_details(Author_id);
	 		      jObj2 = new JSONObject(json1);
	 		      String UserThumb=jObj2.getString("thumb");
	 		      User_photo=getBitmapFromURL(UserThumb);
	 		      User_photo=getRoundedShape(User_photo);*/
	 		     if(Lesson_vidio_ID.equals("")||Lesson_vidio_ID.equals(null)){
	 		 		      Lesson_vidio_ID=jObj.getString("video_id");
	 		 		      }
	 		    
	 		      MainLessonName=jObj.getString("lesson_name");
	 		      Lesson_thumb=jObj.getString("thumb");
	 		    
	 		
	 		  } catch (JSONException e) {
	 		      Log.e("JSON Parser", "Error parsing data " + e.toString());
	 		  } 
	 		
	        	
	        	  getActivity().runOnUiThread(new Runnable() {

	          	    @Override
	          	    public void run() {
	          	    	/* HorizontalScrollView scrollView = (HorizontalScrollView) rootView.findViewById(R.id.RelatedLessons);
	 	 			    LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.RelatedLesson);
	 	 			  
	 	 			  
	 	 			
	 	 			   if(!Related_lessons_thumb.isEmpty()){
	 	 			   for(int i = 0; i < Related_lessons_thumb.size(); i ++)
	 	 			    {
	 	 				  
	 	 			      String Thumb = Related_lessons_thumb.get(i);
	 	 			       Bitmap TheThumb=getBitmapFromURL(Thumb);
	 	 			       TextImagePair tip = new TextImagePair(getActivity(),null,RelatedLessons.get(i),TheThumb);
	 	 			       layout.addView(tip);
	 	 			       final int index=i;
	 	 			       tip.setOnClickListener(new View.OnClickListener() {
	 	 						
	 	 						@Override
	 	 						public void onClick(View v) {
	 	 							// TODO Auto-generated method stub
	 	 							if (youTubePlayer1 != null
	 	 					                   && getFragmentManager().findFragmentById(
	 	 					                		   R.id.youtubeplayerfragment  ) != null){
	 	 					           Fragment fragment =  (getFragmentManager().findFragmentById(R.id.youtubeplayerfragment));   
	 	 					           FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
	 	 					           ft.remove(fragment);
	 	 					           ft.commit();
	 	 					           youTubePlayer1 = null;}
	 	 							MainActivity mainAct = (MainActivity)getActivity();
	 	 				          mainAct.changeFragment1(Related_lessons_IDS.get(index),User_token);
	 	 						}
	 	 					});
	 	 			      
	 	 			    }
	 	 			   }
	 	 			   else{
	 	 				 scrollView.setVisibility(View.GONE);
	 	 			   }
	 	 			   HorizontalScrollView scrollView1 = (HorizontalScrollView) rootView.findViewById(R.id.TAmaren);
	 	 			    LinearLayout layout1 = (LinearLayout) rootView.findViewById(R.id.TmarenLesson);
	 	 			  
	 	 			 if(!RelatedTmaren_thumb.isEmpty()){
	 	 			   
	 	 			   for(int i = 0; i < RelatedTmaren_thumb.size(); i ++)
	 	 			    {
	 	 				  
	 	 			      String Thumb = RelatedTmaren_thumb.get(i);
	 	 			       Bitmap TheThumb=getBitmapFromURL(Thumb);
	 	 			       TextImagePair tip = new TextImagePair(getActivity(),null,RelatedTmaren.get(i),TheThumb);
	 	 			       layout1.addView(tip);
	 	 			       final int index=i;
	 	 			       tip.setOnClickListener(new View.OnClickListener() {
	 	 						
	 	 						@Override
	 	 						public void onClick(View v) {
	 	 							// TODO Auto-generated method stub
	 	 							if (youTubePlayer1 != null
	 	 					                   && getFragmentManager().findFragmentById(
	 	 					                		   R.id.youtubeplayerfragment  ) != null){
	 	 					           Fragment fragment =  (getFragmentManager().findFragmentById(R.id.youtubeplayerfragment));   
	 	 					           FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
	 	 					           ft.remove(fragment);
	 	 					           ft.commit();
	 	 					           youTubePlayer1 = null;}
	 	 							MainActivity mainAct = (MainActivity)getActivity();
	 	 				          mainAct.changeFragment1(RelatedTmaren_IDS.get(index),User_token);
	 	 						}
	 	 					});
	 	 			    }
	 	 			 }
	 	 			 else{
	 	 				scrollView1.setVisibility(View.GONE);
	 	 			 }
	 	 			   HorizontalScrollView scrollView2 = (HorizontalScrollView) rootView.findViewById(R.id.TTbekat);
	 	 			    LinearLayout layout2 = (LinearLayout) rootView.findViewById(R.id.TTBEKAT_LESSON);
	 	 			  
	 	 			if(!Relatedttbekat_thumb.isEmpty()){
	 	 			   
	 	 			   for(int i = 0; i < Relatedttbekat_thumb.size(); i ++)
	 	 			    {
	 	 				  
	 	 			      String Thumb = Relatedttbekat_thumb.get(i);
	 	 			       Bitmap TheThumb=getBitmapFromURL(Thumb);
	 	 			       TextImagePair tip = new TextImagePair(getActivity(),null,Relatedttbekat.get(i),TheThumb);
	 	 			   
	 	 			       layout2.addView(tip);
	 	 			       final int index=i;
	 	 			       tip.setOnClickListener(new View.OnClickListener() {
	 	 					
	 	 					@Override
	 	 					public void onClick(View v) {
	 	 						// TODO Auto-generated method stub
	 	 						if (youTubePlayer1 != null
	 	 				                   && getFragmentManager().findFragmentById(
	 	 				                		   R.id.youtubeplayerfragment  ) != null){
	 	 				           Fragment fragment =  (getFragmentManager().findFragmentById(R.id.youtubeplayerfragment));   
	 	 				           FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
	 	 				           ft.remove(fragment);
	 	 				           ft.commit();
	 	 				           youTubePlayer1 = null;}
	 	 						MainActivity mainAct = (MainActivity)getActivity();
	 	 			          mainAct.changeFragment1(Relatedttbekat_IDS.get(index),User_token);
	 	 					}
	 	 				});
	 	 			    }
	 	 			}
	 	 			else{
	 	 				scrollView2.setVisibility(View.GONE);
	 	 			}*/
	          	    }
	          	});
	      		 return "done";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            // TODO Auto-generated method stub
	            super.onPostExecute(result);
	            easyTracker.send(MapBuilder.createEvent("SingleLessonScreen",
						"SingleLessonScreen", "SingleLesson/id", null).build());
	       /*     User_thumb.setImageBitmap(User_photo);
	 		      
	 		      The_lesson_authorName.setTypeface(typeface2);
	 		      The_lesson_authorName.setText(Lesson_User);
	 		      
	 		      The_lesson_authorName.setOnClickListener(new View.OnClickListener() {
	 				
	 				@Override
	 				public void onClick(View v) {
	 					// TODO Auto-generated method stub
	 					 MainActivity mainAct = (MainActivity)getActivity();
	 			            mainAct.changeFragment2(Author_id,User_token);
	 				}
	 			});
	 		      
	 		      Lesson_Grade.setTypeface(typeface2);
	 		      Lesson_Grade.setText(Lesson_grade);
	 		      
	 		      Lesson_SUbject.setTypeface(typeface2);
	 		      Lesson_SUbject.setText(Lesson_subject);
	 		      
	 		      MainLessOnName.setTypeface(typeface1);
	 		      MainLessOnName.setText(MainLessonName);
	 		   
	 		    Fehmt.setOnClickListener(new View.OnClickListener() {
		 			
		 			 String json3;
		 			 JSONObject jObj3;

		 			@Override
		 			public void onClick(View v) {
		 				// TODO Auto-generated method stub
		 				try {
		 					json3 = userFunction.Lesson_fehmt(Author_id, User_token, Lesson_id);
		 					//jObj3 = new JSONObject(json3);
		 				
		 				      Fehmt.setText(FehmoCount+"ÝåãæÇ!");
		 				     Fehmt.setTextColor(Color.parseColor("#FFFFFF"));
		 				   
		 				    Fehmt.setPressed(true);
		 				     Fehmt.setBackgroundDrawable(getResources().getDrawable(R.drawable.fahemt2));
		 				} catch (IOException e1) {
		 					// TODO Auto-generated catch block
		 					e1.printStackTrace();}
		 			}
		 		});*/
	            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	            (youTubePlayer1).initialize(YoutubeDeveloperKey, this);
	            if(progressDialog!=null)
	            progressDialog.dismiss();
	        }

			@Override
			public void onInitializationFailure(Provider arg0,
					YouTubeInitializationResult arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
				      boolean wasRestored) {
				// TODO Auto-generated method stub
				
			youTubePlayer = player;
				   
				 
				  
				  if (!wasRestored&& !Lesson_vidio_ID.equals("")) {
					  youTubePlayer.setShowFullscreenButton(true);
				        int fullScreenFlags = youTubePlayer.getFullscreenControlFlags();
				      fullScreenFlags &= ~YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT;
				      
				       youTubePlayer.setFullscreenControlFlags(fullScreenFlags);
					// youTubePlayer.setFullscreen(true);
				       
				        youTubePlayer.setOnFullscreenListener(new OnFullscreenListener() {
							@Override
				            public void onFullscreen(boolean _isFullScreen) {
				                fullScreen = _isFullScreen;
				                if(_isFullScreen){
				               // Toast.makeText(getActivity(), "Please reduce the fullscreen before going back", Toast.LENGTH_LONG).show();
				                	}
				            }
				        });    
					  youTubePlayer.loadVideo(Lesson_vidio_ID);
					 
				      }
				  else {
					  Toast.makeText(getActivity(), "there is no Video ID", Toast.LENGTH_LONG).show();
					  
				  }
				  youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
					  
			            @Override 
			            public void onPlaying() {
			            	//getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			            	
						       
						      /*  youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
						           

									@Override
						            public void onFullscreen(boolean isFullScreen) {
						                youTubePlayerIsFullScreen = isFullScreen;
						                youTubePlayer.setFullscreen(true);
						                if(isFullScreen)
						                {
						                	Toast.makeText(getActivity(), "fullscreen", Toast.LENGTH_LONG).show();
						              
						                	
						                              
						                }
						                else
						                {
						                	  Toast.makeText(getActivity(), "NOTfullscreen", Toast.LENGTH_LONG).show();
						                	  youTubePlayer.play();
						                	   youTubePlayer.setFullscreen(false);
						                                                                     
						                }
						            }
									
						        });
						        */
			            }
			 
			            @Override
			            public void onPaused() {
			            	
			            }
			 
			            @Override
			            public void onStopped() {
			            	
			            }
			 
			            @Override
			            public void onBuffering(boolean isBuffering) {
			            }
			 
			            @Override
			            public void onSeekTo(int newPositionMillis) {
			            }
			        });
			 
			      
				

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
		  @Override
		    public void onConfigurationChanged(Configuration newConfig) {
		        super.onConfigurationChanged(newConfig);
		        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)                 
			    {
			          //To re-enable full screen:
		        	 getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			         
			   }
			   else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) 
			   {
			          //To disable full screen:
				   getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			   }


		    }
		  @Override
		  public void onSaveInstanceState(Bundle savedInstanceState) {
		    super.onSaveInstanceState(savedInstanceState);
		    // Save UI state changes to the savedInstanceState.
		    // This bundle will be passed to onCreate if the process is
		    // killed and restarted.
		  //  savedInstanceState.putString("LessonId", this.Lesson_id);
		  
		    // etc.
		  }
		 
		 
			@Override
			public void onStop() {
				super.onStop();
				EasyTracker.getInstance(getActivity()).activityStop(getActivity());
			}
		}