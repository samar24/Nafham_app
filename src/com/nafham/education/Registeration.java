package com.nafham.education;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Stack;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.analytics.tracking.android.MapBuilder;
import com.nafham.education.R;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class Registeration extends Activity implements OnQueryTextListener{
	 android.app.FragmentManager fragmentManager;
	  Fragment fragment = null;
	  Stack<Fragment> mFragmentStack;
	private WebView TheBannerOnActionBar;
	//private WebView TheBannerOnActionBar1;
	private SearchView SV;
	private TextView tv;
	String FacebookID="";
	String Email="";
	private Typeface typeface1;
	private Typeface typeface2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeration_page);
		 fragmentManager=getFragmentManager();
		 typeface1 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
	 	  typeface2 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	    	.permitAll().build();
	    	StrictMode.setThreadPolicy(policy);
	    	  mFragmentStack = new Stack<Fragment>();
	    	  getActionBar().setDisplayHomeAsUpEnabled(false);
	 	     getActionBar().setDisplayShowHomeEnabled(false);
	 	       getActionBar().setHomeButtonEnabled(true);
	 	        getActionBar().setDisplayUseLogoEnabled(false);
	 	       getActionBar().setDisplayShowTitleEnabled(false);
	 	       getActionBar().setDisplayShowCustomEnabled(true);
	 	      if(getIntent().getExtras()!=null){
		 	    	Email=getIntent().getExtras().getString("Email");
		 	    	FacebookID=getIntent().getExtras().getString("facebookID");
		 	     }
		 	    Log.d("look11", FacebookID);
	 	     try {
				displayView();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// HNA A DISPLAY the First Reg Fragment 
	 	    
	}
 public String ConvertToBase64(String filepath){
	 
	// String filepath = "/sdcard/temp.png";
	 File imagefile = new File(filepath);
	 FileInputStream fis = null;
	 try {
	     fis = new FileInputStream(imagefile);
	     } catch (FileNotFoundException e) {
	     e.printStackTrace();
	 }

	 Bitmap bm = BitmapFactory.decodeStream(fis);
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	 bm.compress(Bitmap.CompressFormat.JPEG, 100 , baos);    
	 byte[] b = baos.toByteArray(); 
	String encImage = Base64.encodeToString(b, Base64.DEFAULT);
	return encImage;
 }
 public String getPath(Uri uri) {
	    String[] projection = { MediaStore.Images.Media.DATA };
	    Cursor cursor = managedQuery(uri, projection, null, null, null);
	    int column_index = cursor
	        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}
 public Bitmap getPreview(String fileName) {
	    File image = new File(fileName);

	    BitmapFactory.Options bounds = new BitmapFactory.Options();
	    bounds.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(image.getPath(), bounds);
	    if ((bounds.outWidth == -1) || (bounds.outHeight == -1)) {
	        return null;
	    }
	    int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
	        : bounds.outWidth;
	    BitmapFactory.Options opts = new BitmapFactory.Options();
	    opts.inSampleSize = originalSize / 64;
	    return BitmapFactory.decodeFile(image.getPath(), opts);
	}
	
 public void RecordVidio(){
	  
 }
 
 
 public boolean vaildation (String name,String pw,String ConfirmingPass,String Email){
	 boolean flag=true;
	if(name.length()>0){
		
	}
	 else
     {
         Toast.makeText(getApplicationContext(), "invalid name ", Toast.LENGTH_LONG).show();
        flag=false;
     }
	if(pw.equals(ConfirmingPass)){
		
		
	}
	 else
     {
         Toast.makeText(getApplicationContext(), "Please confirm the password again , passwords are not match ", Toast.LENGTH_LONG).show();
        flag=false;
     }

	 if (Email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && Email.length() > 0)
     {
		
     }
     else
     {
         Toast.makeText(getApplicationContext(), "invalid email", Toast.LENGTH_LONG).show();
        flag=false;
     }
	 return flag;
 }
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
     getMenuInflater().inflate(R.menu.main, menu);
     return true;
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     // toggle nav drawer on selecting action bar app icon/title
    // if (mDrawerToggle.onOptionsItemSelected(item)) {
       //  return true;
    // }
   
     // Handle action bar actions click
     switch (item.getItemId()) {
    case R.id.Nav_drawerr:
 /*	   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         // You can also use "GravityCompat.END" instead of mDrawerListRight
         if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
             mDrawerLayout.closeDrawer(mDrawerList);
         } else {
             mDrawerLayout.openDrawer(mDrawerList);
         }*/
         return true;
       
  
     default:
         return super.onOptionsItemSelected(item);
     }
 }

 /***
  * Called when invalidateOptionsMenu() is triggered
  */
 @Override
 public boolean onPrepareOptionsMenu(Menu menu) {
     // if nav drawer is opened, hide the action items
	
 	   MenuItem item = menu.findItem(R.id.Logo_home);


 	  tv  = (TextView) item.getActionView().findViewById(R.id.TheTitle);
 	 tv.setTypeface(typeface2);
 	tv.setText(getTitle());
 	tv.setVisibility(View.GONE);
   //  boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
   //  menu.findItem(R.id.action_search).setVisible(!drawerOpen);
   //  MenuItem item2 = menu.findItem(R.id.Nav_drawerr);
    // MenuItem item3 = menu.findItem(R.id.action_search);
  
     // Use this if you set with default actionbar item
     //item.setTitle("sampleText");
 	 ImageView Nav_drawer_menu_pic = (ImageView) item.getActionView().findViewById(R.id.Nav_drawerr);
     ImageView THE_actionBAr_logo = (ImageView) item.getActionView().findViewById(R.id.TheMainLogo);
    //  SV=(SearchView) item.getActionView().findViewById(R.id.fragment_address_search);
    //  SV.setVisibility(View.GONE);
    // SV.setOnQueryTextListener(this);
      TheBannerOnActionBar=(WebView) item.getActionView().findViewById(R.id.BannerActionBar);
  //    TheBannerOnActionBar1=(WebView) item.getActionView().findViewById(R.id.BannerActionBar1);
    TheBannerOnActionBar.setVisibility(View.GONE);
 //   TheBannerOnActionBar1.setVisibility(View.GONE);
    Nav_drawer_menu_pic.setVisibility(View.GONE);
   
    
   
     return super.onPrepareOptionsMenu(menu);
 }
@Override
public boolean onQueryTextChange(String arg0) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean onQueryTextSubmit(String arg0) {
	// TODO Auto-generated method stub
	return false;
}
private void displayView() throws IOException, JSONException {
    // update the main content by replacing fragments
	
	
	// set Fragmentclass Arguments
	//Toast.makeText(getApplicationContext(), "ALGRADE "+User_Grade,Toast.LENGTH_LONG).show();
	 fragment = new First_reg_frag(Email,FacebookID);
	
   
    if (fragment != null ) {
    	
    	 
       getFragmentManager().beginTransaction()
             .replace(R.id.frame_container,   fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
       setTitle("ÊÓÌíá ÇáÈíÇäÇÊ");
        // update selected item and title, then close the drawer
       // mDrawerList.setItemChecked(position, true);
       // mDrawerList.setSelection(position);//navMenuTitles[position]
     /*   if(position==0){ setTitle(Uname);}
        else if(position==1){setTitle("");}
        else{setTitle("");}
       */
    
      //  mDrawerLayout.closeDrawer(mDrawerList);
    
    } else {
        // error in creating fragment
        Log.e("MainActivity", "Error in creating fragment");
    }
  
}
 @Override
    public void onBackPressed() {
	  if (getFragmentManager().getBackStackEntryCount()>0) {
	//  ft.show(fragment);
	getFragmentManager().popBackStack();
		/*  mFragmentStack.pop();
		  android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
	
	        ft.show( mFragmentStack.peek());
	        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

	        ft.commit();
		 */
		 
	    } else {
    	
	    	/* Intent intent = new Intent(Intent.ACTION_MAIN);
             intent.addCategory(Intent.CATEGORY_HOME);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);*/
	    	 Intent in=new Intent(getApplicationContext(),MiddleScreen_home.class);
	          startActivity(in);
    }
	//  mFragmentStack.pop();
	   
	 
}
 public void changeFragment(String Email,String Usrrname,String Password,String FB_id,String Avatar) {
     android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    //  ft.remove(fragment);
      fragment = new Scond_fragment_reg(Email,Usrrname,Password,FB_id,Avatar);
      ft.replace(R.id.frame_container,  fragment);
      ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
     ft.addToBackStack(null);
      ft.commit();
     
     getFragmentManager().executePendingTransactions();
  }
 public void changeFragment1(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM, String CountryM_id,String Country,String Country_id,String Governer , String GovernerID,String Gender, String GenderID,String Avatar,String telephone) {
	
     android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    //  ft.remove(fragment);
      fragment = new ThirdFrag(Email,Usrrname,Password,FB_id,UserType,UserTypeID,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,Avatar,telephone);
      ft.replace(R.id.frame_container,  fragment);
      ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
     ft.addToBackStack(null);
      ft.commit();
     
     getFragmentManager().executePendingTransactions();
  }
 public void changeFragment2(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM, String CountryM_id,String Country,String Country_id,String Governer , String GovernerID,String Gender, String GenderID,String Subjects,String Avatar,String Telephone) {
	 	
     android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    //  ft.remove(fragment);
      fragment = new Frag_reg_studen_teach(Email,Usrrname,Password,FB_id,UserType,UserTypeID,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,Subjects,Avatar,Telephone);
      ft.replace(R.id.frame_container,  fragment);
      ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
     ft.addToBackStack(null);
      ft.commit();
     
     getFragmentManager().executePendingTransactions();
  }
 public void changeFragment3(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM, String CountryM_id,String Country,String Country_id,String Governer , String GovernerID,String Gender, String GenderID,String Subjects,String Avatar,String Telephone) {
	 	
     android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    //  ft.remove(fragment);
      fragment = new Parent_son_frag(Email,Usrrname,Password,FB_id,UserType,UserTypeID,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,Subjects,Avatar,Telephone);
      ft.replace(R.id.frame_container,  fragment);
      ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
     ft.addToBackStack(null);
      ft.commit();
     
     getFragmentManager().executePendingTransactions();
  }
 public void changeFragment4(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM, String CountryM_id,String Country,String Country_id,String Governer , String GovernerID,String Gender, String GenderID,String Subjects,String Avatar,String Telephone) {
	 	
     android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
    //  ft.remove(fragment);
      fragment = new Other_Frag(Email,Usrrname,Password,FB_id,UserType,UserTypeID,CountryM,CountryM_id,Country,Country_id,Governer,GovernerID,Gender,GenderID,Subjects,Avatar,Telephone);
      ft.replace(R.id.frame_container,  fragment);
      ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
     ft.addToBackStack(null);
      ft.commit();
     
     getFragmentManager().executePendingTransactions();
  }
 @Override
	public void setTitle(CharSequence title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}

 
}
