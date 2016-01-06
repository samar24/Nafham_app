package com.nafham.education;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
 
public class UserFunctions {
     
   
    private static String Grades ="http://www.nafham.com/api2/gradesof/";
    private static String Subjects_currentSemster ="http://www.nafham.com/api2/subjectsof/";
    private static String Subject_details ="http://www.nafham.com/api2/lessonsof/";
	private String Lessons_details="http://www.nafham.com/api2/lesson/";
    private String LOGIN="http://www.nafham.com/api2/login";
    private String Registeration="http://www.nafham.com/api2/rmi";
    private String Fehmt_Lesson="http://www.nafham.com/api2/fahemt/"; // +"LESSON_ID"
    private String UserDetails="http://www.nafham.com/api2/user/"; // +"USER_ID"
    private String Search_url="http://www.nafham.com/api2/search/";// +"SEARCH_TERM"
    private String User_Notifications="http://www.nafham.com/api2/user/";// +"USER_ID/notifications"
    private String User_latest_videos="http://www.nafham.com/api2/user/";// +"USER_ID/lessons"
    private String Banner4="http://www.nafham.com/api2/banner/4";
    private String Banner5="http://www.nafham.com/api2/banner/5";
    private String Banner6="http://www.nafham.com/api2/banner/6";
    private String  apiSecret = "b34034d8007f9ce33b40e27adfb4e73a";
    private String Countries="http://www.nafham.com/api2/countries";
    private String CheckEmail_register="http://www.nafham.com/api2/rmv";
    private String UserTypes="http://www.nafham.com/api2/usertype";
    private String Genders="http://www.nafham.com/api2/gender";
    private String Specailities="http://www.nafham.com/api2/specialty";
    private String EducationsSystems="http://www.nafham.com/api2/educationsystem";
    private String AllCountries="http://www.nafham.com/api2/allcountries";
    private String Schools="http://www.nafham.com/api2/school/";//+Governor_ID
    private String SchoolsTypes="http://www.nafham.com/api2/schooltype";
    private String JOBS="http://www.nafham.com/api2/job";
    private String Faculities="http://www.nafham.com/api2/faculty";
    private String Register="http://www.nafham.com/api2/register";
    // constructor
    public UserFunctions(){
      
    }
    public String Register(String Email,String Usrrname,String Password,String FB_id,String UserType,String UserTypeID,String CountryM,String CountryM_id,String Country,String Country_id,String Governer,String GovernerID,String Gender,String GenderID,String Subjects,String Grade,String GradeID,String School,String JobID,String University,String Faculty_id,String Son, String Phone, String Avatar) throws IOException{
   	 String json;
   	   JsonParser1 jsonParser = new JsonParser1();

   	 // Building Parameters ( you can pass as many parameters as you want)
   	 List<NameValuePair> params = new ArrayList<NameValuePair>();

   	 params.add(new BasicNameValuePair("email",Email ));
   	 params.add(new BasicNameValuePair("password",Password ));
   	 params.add(new BasicNameValuePair("name",Usrrname ));
   	 params.add(new BasicNameValuePair("country_id",CountryM_id ));
   	 params.add(new BasicNameValuePair("gender_id",GenderID ));
   	 params.add(new BasicNameValuePair("subject",Subjects ));
   	 params.add(new BasicNameValuePair("usertype_id",UserTypeID ));
   	 params.add(new BasicNameValuePair("governor_id",GovernerID ));
   	 params.add(new BasicNameValuePair("school_add",School ));
   	 params.add(new BasicNameValuePair("job_id",JobID ));
   	 params.add(new BasicNameValuePair("faculty_id",Faculty_id ));
   	 params.add(new BasicNameValuePair("university",University ));
   	 params.add(new BasicNameValuePair("fbid ",FB_id ));
   	 params.add(new BasicNameValuePair("phone",Phone ));
   	 params.add(new BasicNameValuePair("avatar",Avatar ));
   	 params.add(new BasicNameValuePair("son",Son ));
	 params.add(new BasicNameValuePair("section",GradeID ));
   	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
   	  json = jsonParser.makeHttpRequest(Register, "POST", params);
   	 return json;
   	
   }
    public String Countries() throws IOException, JSONException{
   	 String json;
   	   JsonParser1 jsonParser = new JsonParser1();
    
   	 // Building Parameters ( you can pass as many parameters as you want)
   	 List<NameValuePair> params = new ArrayList<NameValuePair>();

   	 params.add(new BasicNameValuePair("name", ""));
   	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
   	  json = jsonParser.makeHttpRequest(Countries, "GET", params);
   	 return json;
    }
    public String UserTYPES() throws IOException, JSONException{
      	 String json;
      	   JsonParser1 jsonParser = new JsonParser1();
       
      	 // Building Parameters ( you can pass as many parameters as you want)
      	 List<NameValuePair> params = new ArrayList<NameValuePair>();
      	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
      	  json = jsonParser.makeHttpRequest(UserTypes, "GET", params);
      	 return json;
       }
    public String Genders() throws IOException, JSONException{
     	 String json;
     	   JsonParser1 jsonParser = new JsonParser1();
      
     	 // Building Parameters ( you can pass as many parameters as you want)
     	 List<NameValuePair> params = new ArrayList<NameValuePair>();
     	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
     	  json = jsonParser.makeHttpRequest(Genders, "GET", params);
     	 return json;
      }
    public String Specailities() throws IOException, JSONException{
    	 String json;
    	   JsonParser1 jsonParser = new JsonParser1();
     
    	 // Building Parameters ( you can pass as many parameters as you want)
    	 List<NameValuePair> params = new ArrayList<NameValuePair>();
    	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
    	  json = jsonParser.makeHttpRequest(Specailities, "GET", params);
    	 return json;
     }
    public String EducationsSystems() throws IOException, JSONException{
   	 String json;
   	   JsonParser1 jsonParser = new JsonParser1();
    
   	 // Building Parameters ( you can pass as many parameters as you want)
   	 List<NameValuePair> params = new ArrayList<NameValuePair>();
   	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
   	  json = jsonParser.makeHttpRequest(EducationsSystems, "GET", params);
   	 return json;
    }
    public String AllCountries() throws IOException, JSONException{
      	 String json;
      	   JsonParser1 jsonParser = new JsonParser1();
       
      	 // Building Parameters ( you can pass as many parameters as you want)
      	 List<NameValuePair> params = new ArrayList<NameValuePair>();
      	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
      	  json = jsonParser.makeHttpRequest(AllCountries, "GET", params);
      	 return json;
       }
    public String Schools(String Governor_ID) throws IOException, JSONException{
     	 String json;
     	   JsonParser1 jsonParser = new JsonParser1();
      
     	 // Building Parameters ( you can pass as many parameters as you want)
     	 List<NameValuePair> params = new ArrayList<NameValuePair>();
     	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
     	 params.add(new BasicNameValuePair("Governor_ID", Governor_ID));
     	  json = jsonParser.makeHttpRequest(Schools, "GET", params);
     	 return json;
      }
    public String SchoolsTypes() throws IOException, JSONException{
     	 String json;
     	   JsonParser1 jsonParser = new JsonParser1();
      
     	 // Building Parameters ( you can pass as many parameters as you want)
     	 List<NameValuePair> params = new ArrayList<NameValuePair>();
     	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
     	  json = jsonParser.makeHttpRequest(SchoolsTypes, "GET", params);
     	 return json;
      }
    public String JOBS() throws IOException, JSONException{
    	 String json;
    	   JsonParser1 jsonParser = new JsonParser1();
     
    	 // Building Parameters ( you can pass as many parameters as you want)
    	 List<NameValuePair> params = new ArrayList<NameValuePair>();
    	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
    	  json = jsonParser.makeHttpRequest(JOBS, "GET", params);
    	 return json;
     }
    public String Faculities() throws IOException, JSONException{
   	 String json;
   	   JsonParser1 jsonParser = new JsonParser1();
    
   	 // Building Parameters ( you can pass as many parameters as you want)
   	 List<NameValuePair> params = new ArrayList<NameValuePair>();
   	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
   	  json = jsonParser.makeHttpRequest(Faculities, "GET", params);
   	 return json;
    }
 public String Grades(String COUNtry_ID) throws IOException, JSONException{
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();
 
	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	// params.add(new BasicNameValuePair("name", ""));
   params.add(new BasicNameValuePair("country_id", COUNtry_ID));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Grades+COUNtry_ID, "GET", params);
	 return json;
 }
 public String GetSubject_grade_semseter(String GradeID,String CurrentSemster) throws IOException, JSONException{
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("semester", CurrentSemster));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Subjects_currentSemster+GradeID, "GET", params);
	 return json;
 }

public String getSubjectDetails(String sUbject_ID) throws IOException {
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("SUBJECT_ID",sUbject_ID ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Subject_details+sUbject_ID, "GET", params);
	 return json;
}

public String GetLessonDetails(String lesson_id) throws IOException {
	// TODO Auto-generated method stub
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("LESSON_ID",lesson_id ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Lessons_details+lesson_id, "GET", params);
	 return json;
	
}
public String Login(String UN, String PW) throws IOException{
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("uname",UN ));
	 params.add(new BasicNameValuePair("pword",PW ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(LOGIN, "POST", params);
	 return json;
	
}
public String Register_vaildation(String email) throws IOException{
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("email",email ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(CheckEmail_register, "POST", params);
	 return json;
	
}
public String Login_facebook( String Fb_ID,String Email) throws IOException{
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();
	 params.add(new BasicNameValuePair("fbid",Fb_ID ));
	 params.add(new BasicNameValuePair("uname",Email ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(LOGIN, "POST", params);
	 return json;
	
}
public String REGisteration(String email) throws IOException{
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("email",email ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Registeration, "POST", params);
	 return json;
	
}
public String Lesson_fehmt(String U_id,String U_Token,String Lesson_ID) throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("user_id",U_id ));
	 params.add(new BasicNameValuePair("user_token",U_Token ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Fehmt_Lesson+Lesson_ID, "POST", params);
	 return json;
}

public String User_details(String User_ID) throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("USER_ID",User_ID ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(UserDetails+User_ID, "GET", params);
	 return json;
}
public String User_Notifications(String User_ID) throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("USER_ID",User_ID ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(User_Notifications+User_ID+"/notifications", "GET", params);
	 return json;
}

public String GetBanner4ActionBar() throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Banner4, "GET", params);
	 return json;
}
public String GetBanner5Splash() throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Banner5, "GET", params);
	 return json;
}
public String GetBanner6insideScreens() throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();
	   List<NameValuePair> params = new ArrayList<NameValuePair>();
	   params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	 // Building Parameters ( you can pass as many parameters as you want)
	

	  json = jsonParser.makeHttpRequest(Banner6, "GET", params);
	 return json;
}
public String User_LatestVideos(String User_ID) throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("USER_ID",User_ID ));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(User_latest_videos+User_ID+"/lessons", "GET", params);
	 return json;
}
public String Search_lesson(String Item) throws IOException{
	
	 String json;
	   JsonParser1 jsonParser = new JsonParser1();

	 // Building Parameters ( you can pass as many parameters as you want)
	 List<NameValuePair> params = new ArrayList<NameValuePair>();

	 params.add(new BasicNameValuePair("SEARCH_TERM", Item));
	 params.add(new BasicNameValuePair("apiSecret", "b34034d8007f9ce33b40e27adfb4e73a"));
	  json = jsonParser.makeHttpRequest(Search_url+Item, "GET", params);
	 return json;
}
}