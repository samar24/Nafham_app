package com.nafham.education;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import com.nafham.education.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Sittings_fragment extends Fragment {
     
	EditText TheNameOFtheUser;
	EditText TheSchool;
	EditText Government;
	Button ChangeCoverphoto;
	Button ChangePP;
	Button SubmitNewData;
	   Uri mImageCaptureUri;
	   ImageView mImageView;
	   private final int SELECT_PHOTO = 1;
	   private static final int PICK_FROM_CAMERA = 1;
	    private static final int PICK_FROM_FILE = 2;
    public Sittings_fragment(){
    	
    	
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.sittings, container, false);
        TheNameOFtheUser=(EditText)rootView.findViewById(R.id.editText1);
        TheSchool=(EditText)rootView.findViewById(R.id.School);
        Government= (EditText)rootView.findViewById(R.id.Government);
        mImageView = (ImageView) rootView.findViewById(R.id.User_Photo);
        ChangePP=(Button)rootView.findViewById(R.id.button2);
        SubmitNewData=(Button)rootView.findViewById(R.id.button3);
        ChangeCoverphoto=(Button)rootView.findViewById(R.id.button1);
        String school=TheSchool.getText().toString();
        String NewName=TheNameOFtheUser.getText().toString();
        String GOV=Government.getText().toString();
        /////////////
    
 
  
        
        ////////////////////
         ChangePP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, 1); 
				}
				
		});
        ChangeCoverphoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        SubmitNewData.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        return rootView;
    }
    
    public String getRealPathFromURI(Uri contentUri) {
        String [] proj      = {MediaStore.Images.Media.DATA};
        Cursor cursor       = getActivity().managedQuery( contentUri, proj, null, null,null);
 
        if (cursor == null) return null;
 
        int column_index    = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 
        cursor.moveToFirst();
 
        return cursor.getString(column_index);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 1 && resultCode == Activity.RESULT_OK) { 
    		CopyOfMainActivity activity = (CopyOfMainActivity)getActivity(); 
    		Bitmap bitmap = getBitmapFromCameraData(data, activity); 
    		mImageView.setImageBitmap(bitmap); 
    		
    	} }
   

public static Bitmap getBitmapFromCameraData(Intent data, Context context){ 
	Uri selectedImage = data.getData(); 
String[] filePathColumn = { MediaStore.Images.Media.DATA };
Cursor cursor = context.getContentResolver().query(selectedImage,filePathColumn, null, null, null); 
cursor.moveToFirst(); 
int columnIndex = cursor.getColumnIndex(filePathColumn[0]); 
String picturePath = cursor.getString(columnIndex); 
cursor.close(); 
return BitmapFactory.decodeFile(picturePath); 
}
public static String encodeTobase64(Bitmap image) //this function to Encode the returned BITMap and then we will send it to DB
{
    Bitmap immagex=image;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] b = baos.toByteArray();
    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

    Log.e("LOOK", imageEncoded);
    return imageEncoded;
}
public static Bitmap decodeBase64(String input) 
{
    byte[] decodedByte = Base64.decode(input, 0);
    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
}
}
