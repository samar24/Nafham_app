package com.nafham.education;

import java.util.ArrayList;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.sax.StartElementListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.nafham.education.R;
public class NavDrawerListAdapter extends BaseAdapter implements OnQueryTextListener{
     
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    ImageView imgIcon;
    TextView txtTitle;
    TextView title;
    TextView Browse_title;
    ImageView broswe_ICon;
    Spinner Grades_browsing_dropDownList;
    Spinner SemstersSpinner;
    String Grade_id="";
    String SEM_ID="";
	private ImageView imgIcon1;
	private TextView txtTitle1;
	private TextView Counter_text;
    LinearLayout NotificationsICON;
	private SearchView SearchEditText;
	private String TargetWord;
	private Bundle bundle4;
	private search_fragment fragment=null;
    
    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }
 
    @Override
    public int getCount() {
        return navDrawerItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return navDrawerItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
     if( (position==1 || position==2 ||position==4 ||position==5)&& convertView == null){
    	  LayoutInflater mInflater = (LayoutInflater)
                  context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
          convertView = mInflater.inflate(R.layout.drawer_list_item, null);
    	 Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
   	  Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
   	 
         imgIcon = (ImageView) convertView.findViewById(R.id.icon);
         txtTitle = (TextView) convertView.findViewById(R.id.title);
       /*  if(position==0){
        	imgIcon.setImageBitmap(navDrawerItems.get(position).Get_user_icon()) ; 
        	 txtTitle.setText(navDrawerItems.get(position).Get_user_name());
            txtTitle.setTypeface(typeface2);
      	  }
         else{*/
        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());        
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        txtTitle.setTypeface(typeface2);
        	// }
     }
     if(position==0&&convertView == null){
    	  LayoutInflater mInflater = (LayoutInflater)
                  context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
          convertView = mInflater.inflate(R.layout.drawerlistitemprofile, null);
    	 Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
   	  Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
   	 
         imgIcon = (ImageView) convertView.findViewById(R.id.icon);
         txtTitle = (TextView) convertView.findViewById(R.id.title);
     
        	imgIcon.setImageBitmap(navDrawerItems.get(position).Get_user_icon()) ; 
        	 txtTitle.setText(navDrawerItems.get(position).Get_user_name());
            txtTitle.setTypeface(typeface2); 
     }
      /*  if(position==5&&convertView == null){
        	 Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
          	  Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
        	 LayoutInflater mInflater = (LayoutInflater)
                     context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
             convertView = mInflater.inflate(R.layout.drawer_list_item1, null);
             imgIcon1 = (ImageView) convertView.findViewById(R.id.icon);
             txtTitle1 = (TextView) convertView.findViewById(R.id.title);
             Counter_text=(TextView) convertView.findViewById(R.id.Counter);
             NotificationsICON=(LinearLayout) convertView.findViewById(R.id.linearLayout1);
             imgIcon1.setImageResource(navDrawerItems.get(position).getNotifiication_ICon());  
             txtTitle1.setTypeface(typeface2);
             txtTitle1.setText(navDrawerItems.get(position).getNotifications_title());
             Counter_text.setVisibility(View.GONE);
             NotificationsICON.setVisibility(View.GONE);
             if(!navDrawerItems.get(position).getCount().equals("")){
             Counter_text.setTypeface(typeface2);
             Counter_text.setText(navDrawerItems.get(position).getCount());
             Counter_text.setVisibility(View.VISIBLE);
             NotificationsICON.setVisibility(View.VISIBLE);
             }
        }*/
        if(position==3&&convertView == null){
        	
        	 Typeface typeface1 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
         	  Typeface typeface2 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
       	 LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item2, null);
            broswe_ICon=(ImageView)convertView.findViewById(R.id.icon);
            Browse_title=(TextView)convertView.findViewById(R.id.title);
            Browse_title.setTypeface(typeface2);
            Browse_title.setText(" ’›Õ «·„Ê«œ «·œ—«”Ì…");
            broswe_ICon.setImageResource(navDrawerItems.get(position).Get_icon_browse());  
              Grades_browsing_dropDownList  = (Spinner) convertView.findViewById(R.id.Grades_drop_down_list);
            SemstersSpinner=(Spinner) convertView.findViewById(R.id.Grades_Semster_drop_down_list1);
            SemstersSpinner.setVisibility(View.GONE);
           ArrayAdapter<String> adapter1=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, navDrawerItems.get(position).GetGrades_array());
           adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Grades_browsing_dropDownList.setAdapter(adapter1); 
            
            Grades_browsing_dropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                 
            
        
				@Override
				 public void onItemSelected(AdapterView<?> parent, View view, 
	                    int pos, long id) {
					// TODO Auto-generated method stub
					if(parent.getItemAtPosition(pos).toString().equals("")){
						//DO NO THING AT ALL ,, let the user choose
						Browse_title.setText(" ’›Õ «·„Ê«œ «·œ—«”Ì…");
					}
					else {
				Grade_id=navDrawerItems.get(position).GetGrades_array_IDs().get(pos);
				Toast.makeText(context, "selectedd"+Grade_id, Toast.LENGTH_LONG).show();
				if(navDrawerItems.get(position).GetGrades_array_semCount().get(pos).equals("1")){
					//Browse_title.setText("«Œ — «·›’· «·œ—«”Ï");
				//	Grades_browsing_dropDownList.setVisibility(View.GONE);
					//SemstersSpinner.setVisibility(View.VISIBLE);
					Intent in=new Intent(context,CopyOfMainActivity.class);
					  in.putExtra("ThesemsterID","1");
						 in.putExtra("TheUserID", navDrawerItems.get(position).Get_userID());
				       in.putExtra("TheUserName", navDrawerItems.get(position).Get_userName());
				       in.putExtra("TheUserThumb",navDrawerItems.get(position).getUserThumb());
				       in.putExtra("TheUserGrade", Grade_id);
				       in.putExtra("TheUserToken",navDrawerItems.get(position).getUstOken());
				       in.putExtra("user_TYPE",navDrawerItems.get(position).getUserType());
				       in.putExtra("CountryID",navDrawerItems.get(position).Get_CountryID());
				       in.putExtra("Competition","0");
		                 in.putExtra("link","");
				       in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				       context.startActivity(in);
					
				}
				else if (!navDrawerItems.get(position).GetGrades_array_semCount().get(pos).equals("")) {
					Browse_title.setText("«Œ — «·›’· «·œ—«”Ï");
					Grades_browsing_dropDownList.setVisibility(View.GONE);
					SemstersSpinner.setVisibility(View.VISIBLE);
  ArrayAdapter<String> adapter1=new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, navDrawerItems.get(position).Get_semsters_array());
  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  adapter1.notifyDataSetChanged();
  SemstersSpinner.setAdapter(adapter1); 
  SemstersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	@Override
	public void onItemSelected(AdapterView<?> Parent1, View view1, int pos1,
			long id1) {
		// TODO Auto-generated method stub
		if(Parent1.getItemAtPosition(pos1).toString()=="«·›’· «·œ—«”Ï «·«Ê·"){
	SEM_ID="1";
	Browse_title.setText("");
	Intent in=new Intent(context,CopyOfMainActivity.class);
	  in.putExtra("ThesemsterID","1");
		 in.putExtra("TheUserID", navDrawerItems.get(position).Get_userID());
       in.putExtra("TheUserName", navDrawerItems.get(position).Get_userName());
       in.putExtra("TheUserThumb",navDrawerItems.get(position).getUserThumb());
       in.putExtra("TheUserGrade", Grade_id);
       in.putExtra("TheUserToken",navDrawerItems.get(position).getUstOken());
       in.putExtra("user_TYPE",navDrawerItems.get(position).getUserType());
       in.putExtra("CountryID",navDrawerItems.get(position).Get_CountryID());
       in.putExtra("Competition","0");
       in.putExtra("link","");
       in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
       context.startActivity(in);
		}
		if(Parent1.getItemAtPosition(pos1).toString()=="«·›’· «·œ—«”Ï «·À«‰Ï"){
			SEM_ID="2";
			Browse_title.setText("");
			Intent in=new Intent(context,CopyOfMainActivity.class);
			  in.putExtra("ThesemsterID","2");
				 in.putExtra("TheUserID", navDrawerItems.get(position).Get_userID());
		       in.putExtra("TheUserName", navDrawerItems.get(position).Get_userName());
		       in.putExtra("TheUserThumb",navDrawerItems.get(position).getUserThumb());
		       in.putExtra("TheUserGrade", Grade_id);
		       in.putExtra("TheUserToken",navDrawerItems.get(position).getUstOken());
		       in.putExtra("user_TYPE",navDrawerItems.get(position).getUserType());
		       in.putExtra("CountryID",navDrawerItems.get(position).Get_CountryID());
		       in.putExtra("Competition","0");
               in.putExtra("link","");
		       in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		       context.startActivity(in);
				}
		else{	
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
});
					}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			}); 
       }
      
        // displaying count
        // check whether it set visible or not
       
         
        return convertView;
    }

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		TargetWord=arg0;
		//	Toast.makeText(getApplicationContext(), "text Submit", Toast.LENGTH_LONG).show();
			// SV.setVisibility(View.INVISIBLE);
			// SV.setVisibility(View.VISIBLE);
			// SearchEditText.clearFocus();
			// SearchEditText.setQuery(arg0, false);
			// SearchEditText.setFocusable(false);
			
			// final InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		//	 imm. hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			/*	CopyOfMainActivity mainAct = (CopyOfMainActivity)context;
			 android.app.FragmentTransaction ft = mainAct.getFragmentManager().beginTransaction();
			
			
        	bundle4.putString("the_searchableWord",TargetWord);
        	// set Fragmentclass Arguments
        	 fragment = new search_fragment();
        	 fragment.setArguments(bundle4); 
		        ft.add(R.id.frame_container,fragment);
		        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		        ft.commit();
		        mainAct.getFragmentManager().executePendingTransactions();*/
		return false;
	}
 
}