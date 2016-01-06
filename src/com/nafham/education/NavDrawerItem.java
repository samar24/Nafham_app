package com.nafham.education;

import java.util.ArrayList;

import javax.security.auth.PrivateCredentialPermission;

import android.graphics.Bitmap;

public class NavDrawerItem {
	 private String title;
	    private int icon;
	  private  String Title_browsee;
	private    int icon_browse;
	private   String title_notifications;
	private int icon_notifcations;
	    private String Notifications_count = "0";
	    private ArrayList<String> Grades_names;
	    private ArrayList<String> Semsters_names;
	   private  ArrayList<String> Grades_SemCount;
	    private ArrayList<String> GradesIDs;
	    // boolean to set visiblity of the counter
	    private boolean isCounterVisible = false;
	     private String userN;
	     private Bitmap btmIcon;
		private String UID;
		private String Uname;
		private String Uthumb;
		private String Utoken;
		private String uType;
		String CountryID;
	    public NavDrawerItem(){}
	 
	    public NavDrawerItem(String title, int icon){
	        this.title = title;
	        this.icon = icon;
	    }
	    public NavDrawerItem(String uName, Bitmap btmIcon){
	        this.userN = uName;
	        this.btmIcon = btmIcon;
	    }
	     
	    public NavDrawerItem( String title,String count ,int icon){
	        this.Notifications_count = count;
	       this.title_notifications=title;
	       this.icon_notifcations=icon;
	    }
	     
	    public NavDrawerItem(  String Titile_brows,ArrayList<String> Grades_names, ArrayList<String> Grades_SemCount,ArrayList<String> GradesIDs,String CountryID, ArrayList<String> Semsters,String Uid,String Uname,String Thumb,String UserToken,String user_type,int icon_browse){
	       this.Title_browsee=Titile_brows;
	        this.Grades_names=Grades_names;
	        this.Semsters_names=Semsters;
	        this.icon_browse=icon_browse;
	        this.UID=Uid;
	        this.Uname=Uname;
	        this.Uthumb=Thumb;
	        this.Utoken=UserToken;
	        this.uType=user_type;
	        this.Grades_SemCount=Grades_SemCount;
	        this.GradesIDs=GradesIDs;
	        this.CountryID=CountryID;
	    }
	    public String getTitle(){
	        return this.title;
	    }
	     
	    public int getIcon(){
	        return this.icon;
	    }
	     
	    public String getCount(){
	        return this.Notifications_count;
	    }
	    public String getNotifications_title(){
	        return this.title_notifications;
	    }
	    public int getNotifiication_ICon(){
	        return this.icon_notifcations;
	    }
	   
	    public ArrayList<String> GetGrades_array(){
	        return this.Grades_names;
	    }
	    public ArrayList<String> GetGrades_array_semCount(){
	        return this.Grades_SemCount;
	    }
	    public ArrayList<String> GetGrades_array_IDs(){
	        return this.GradesIDs;
	    }
	    public ArrayList<String> Get_semsters_array(){
	        return this.Semsters_names;
	    }
	    public String Get_title_browse(){
	        return this.Title_browsee;
	    }
	    public int Get_icon_browse(){
	        return this.icon_browse;
	    }
	    public Bitmap Get_user_icon(){
	        return this.btmIcon;
	    }
	    public String Get_user_name(){
	        return this.userN;
	    }
	    public String Get_CountryID(){
	        return this.CountryID;
	    }
	    public String Get_userID(){
	        return this.UID;
	    }
	    public String Get_userName(){
	        return this.Uname;
	    }
	    public String getUserThumb(){
	        return this.Uthumb;
	    }
	    public String getUstOken(){
	        return this.Utoken;
	    }
	    public String getUserType(){
	        return this.uType;
	    }
	    public boolean getCounterVisibility(){
	        return this.isCounterVisible;
	    }
	     
	    public void setTitle(String title){
	        this.title = title;
	    }
	    public void setTitle_notifications(String title){
	        this.title_notifications = title;
	    }
	    public void setIcon(int icon){
	        this.icon = icon;
	    }
	    public void setIcon_notifications(int icon){
	        this.icon_notifcations = icon;
	    }
	   
	    public void SetGrades(ArrayList<String> gradesNames){
	        this.Grades_names = gradesNames;
	    }
	    public void SetSemsters(ArrayList<String> Semsters){
	        this.Semsters_names = Semsters;
	    }
	    public void setCount(String count){
	        this.Notifications_count = count;
	    }
	     
	    public void setCounterVisibility(boolean isCounterVisible){
	        this.isCounterVisible = isCounterVisible;
	    }
}
