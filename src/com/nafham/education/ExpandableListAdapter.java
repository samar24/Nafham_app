package com.nafham.education;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nafham.education.R;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<String> _listDataHeader; // header titles
    UserFunctions userFunction = new UserFunctions();
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private HashMap<String, List<String>> _listDataChild_months;
    private HashMap<String, List<String>> _listDataChild_haveORnot;
    private String SubjectNName;
    private int temp2;
    private int temp3;
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<String>> listChildData, HashMap<String, List<String>> listChildData_Months,HashMap<String, List<String>> listChildData_HaveORNOT,String SbN,int VideosCo,int LessCo) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._listDataChild_months=listChildData_Months;
        this._listDataChild_haveORnot=listChildData_HaveORNOT;
        this.SubjectNName=SbN;
        this.temp2=VideosCo;
        this.temp3=LessCo;
    }
 
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
    public Object getChild_month(int groupPosition, int childPosititon) {
        return this._listDataChild_months.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
    public Object getChild_HaveOrnot(int groupPosition, int childPosititon) {
        return this._listDataChild_haveORnot.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
    	  Typeface typeface1 = Typeface.createFromAsset(_context.getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  Typeface typeface2 = Typeface.createFromAsset(_context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
    	  LayoutInflater infalInflater = (LayoutInflater) this._context
                  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
    	  final String childText = (String) getChild(groupPosition, childPosition);
	         final String Child_text_month=(String) getChild_month(groupPosition, childPosition);
	         final String Child_text_haveORnot=(String) getChild_HaveOrnot(groupPosition, childPosition);
        if (convertView == null) {
        	
        	
        		
            convertView = infalInflater.inflate(R.layout.list_item, null);
          
        	
            }
       
        ImageView Month_image=(ImageView)convertView
                .findViewById(R.id.imageView1);
        TextView txtListChild = (TextView) convertView 
                .findViewById(R.id.lblListItem);
       if(Child_text_month.equals("1")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.jan));
        }
        if(Child_text_month.equals("2")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.jan));
        }
        if(Child_text_month.equals("3")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.march));
        }
        if(Child_text_month.equals("4")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.april));
        }
        if(Child_text_month.equals("5")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.may));
        }
        if(Child_text_month.equals("6")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.june));
        }
        if(Child_text_month.equals("7")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.july));
        }
        if(Child_text_month.equals("8")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.augst));
        }
        if(Child_text_month.equals("9")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.sept));
        }
        if(Child_text_month.equals("10")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.oct));
        }
        if(Child_text_month.equals("11")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.nov));
        }
        if(Child_text_month.equals("12")){
        	Month_image.setImageDrawable(_context.getResources().getDrawable(R.drawable.december));
        }
        if(Child_text_haveORnot.equals("0")){
        	txtListChild.setTextColor(Color.parseColor("#0088cf"));
        }
        else if(Child_text_haveORnot.equals("1")){
        	txtListChild.setTextColor(Color.parseColor("#A8A8A8"));
        }
        txtListChild.setTypeface(typeface2);
 
        txtListChild.setText(childText);
        return convertView;
    }
 
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
    	  Typeface typeface1 = Typeface.createFromAsset(_context.getAssets(), "fonts/HelveticaNeueW23-Bd.ttf");
    	  Typeface typeface2 = Typeface.createFromAsset(_context.getAssets(), "fonts/HelveticaNeueW23-Reg.ttf");
    /*	
    	*/
    
    	
    	 if (convertView == null) {
    		 LayoutInflater infalInflater = (LayoutInflater) this._context
                     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		/* if(groupPosition==0){
    			 convertView = infalInflater.inflate(R.layout.main_dashboard_title, null);
    			 LinearLayout Subject_BG=(LinearLayout)convertView.findViewById(R.id.Subject_backGround);
    			   ImageView 	 Image_subject=(ImageView)convertView.findViewById(R.id.Subject_Image);
    			   TextView 	 SubjectName=(TextView)convertView.findViewById(R.id.Subject_name);
    			    TextView	 Vidieo_statiscs=(TextView)convertView.findViewById(R.id.Vidio_statiscs);
    			  
    			    SubjectName.setTypeface(typeface1);
    			    SubjectName.setText(SubjectNName);
    			  String  Videos_Counter=String.valueOf(temp2);
    			  String   Lessons_counter=String.valueOf(temp3);
    			     Vidieo_statiscs.setTypeface(typeface2);
    			     Vidieo_statiscs.setText(Videos_Counter +" ›ÌœÌÊ  ‘—Õ "+Lessons_counter+" œ—” ");
    			    if(SubjectNName.contains("«··€… «·⁄—»Ì…")){
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.arabic));
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#74ce8e"));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·ﬂÌ„Ì«¡") ||SubjectNName.contains("Chemistry") || SubjectNName.contains("chemistry")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#78d0e6"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.chemistry));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}else if(SubjectNName.contains("«·œ—«”«  «·«Ã „«⁄Ì…")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#e46459"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.social_studies));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·—Ì«÷Ì« ") || SubjectNName.contains("Mathematics") || SubjectNName.contains("mathematics") || SubjectNName.contains("math")|| SubjectNName.contains("Math")||SubjectNName.contains("Mechanics")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#446381"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.math));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}else if(SubjectNName.contains("«·«Õ’«¡") ||  SubjectNName.contains("Statistics") ||  SubjectNName.contains("statistics")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#52c4df"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.statistics));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}else if(SubjectNName.contains("«·«ﬁ ’«œ") || SubjectNName.contains("Economy")|| SubjectNName.contains("economy")||SubjectNName.contains("«·«ﬁ ’«œ 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#60618f"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.economics));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}else if(SubjectNName.contains("⁄·Ê„ »Ì∆Ì… ÊÃÌÊ·ÃÌ«")  ||SubjectNName.contains("Geology") ||SubjectNName.contains("geology")||SubjectNName.contains("⁄·Ê„ »Ì∆Ì… ÊÃÌÊ·ÊÃÌ« 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#2ab2be"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.geography));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·›Ì“Ì«¡") || SubjectNName.contains("Physics") || SubjectNName.contains("physics")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#f04d4e"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.physics));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·«ÕÌ«¡") || SubjectNName.contains("Biology")|| SubjectNName.contains("biology")|| SubjectNName.contains("«·√ÕÌ«¡ 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#92c44b"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.geology));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("⁄·„ «·‰›” Ê«·«Ã „«⁄")|| SubjectNName.contains("⁄·„ «·‰›” 2014")|| SubjectNName.contains("psychology")||SubjectNName.contains("⁄·„ «·≈Ã „«⁄ 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#3a6b9b"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.pyscology));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«··€… «·«·„«‰Ì…")|| SubjectNName.contains("Germany")|| SubjectNName.contains("germany")||SubjectNName.contains("German 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#4d4d4d"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.german));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«··€… «·›—‰”Ì…")|| SubjectNName.contains("French")|| SubjectNName.contains("french")||SubjectNName.contains("«··€… «·›—‰”Ì… 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#52c4df"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.francis));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·⁄·Ê„") ||SubjectNName.contains("Science") ||SubjectNName.contains("science")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#2980b9"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.science));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("‰Ã·Ì“Ì…")||SubjectNName.contains("English")||SubjectNName.contains("english")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#2e2e2e"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.english));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·Õ«”» «·«·Ï")||SubjectNName.contains("Computer") ||SubjectNName.contains("computer")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#088fe9"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.computer));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·›·”›… Ê«·„‰ÿﬁ")||SubjectNName.contains("Philosophy")||SubjectNName.contains("philosophy")||SubjectNName.contains("«·›·”›… Ê«·„‰ÿﬁ 2014")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#65c180"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.logic));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«·Ã€—«›Ì«")||SubjectNName.contains("Ã€—«›Ì« 2014")||SubjectNName.contains("Ã€—«›Ì«")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#f7971e"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.geography));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    			    	else if(SubjectNName.contains("«· «—ÌŒ")){
    			    	Subject_BG.setBackgroundColor(Color.parseColor("#57a237"));
    			    	Image_subject.setImageDrawable(_context.getResources().getDrawable(R.drawable.history));
    			    	SubjectName.setTextColor(Color.parseColor("#16669b"));
    			    	}
    		 }else{*/
    			 
    	             convertView = infalInflater.inflate(R.layout.list_group, null);
    	          
    		// }
           
    	 }
    	   String headerTitle = (String) getGroup(groupPosition);
           ExpandableListView eLV = (ExpandableListView) parent;
           eLV.expandGroup(groupPosition);
           TextView lblListHeader = (TextView) convertView
                   .findViewById(R.id.lblListHeader);
           lblListHeader.setTypeface(typeface1);
           lblListHeader.setText(headerTitle);
   
        return convertView;
    }
 
    public boolean hasStableIds() {
        return false;
    }
 
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}