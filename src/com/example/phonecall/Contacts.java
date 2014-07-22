package com.example.phonecall;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.webkit.WebChromeClient.CustomViewCallback;

public class Contacts extends Application{

    
      
    private Uri QUERY_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private String CONTACT_ID = ContactsContract.Contacts._ID;
    private String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private Uri EMAIL_CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    private String EMAIL_CONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    private String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
    private String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
    private String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    private Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    private String STARRED_CONTACT = ContactsContract.Contacts.STARRED;
    private String PHOTO_CONTACT = ContactsContract.Contacts.PHOTO_ID;
    List<Contact> contactList = new ArrayList<Contact>();
	public List<Contact> getContactList(Context context){
		
		String[] projection = new String[]{CONTACT_ID,DISPLAY_NAME,HAS_PHONE_NUMBER,PHONE_NUMBER,STARRED_CONTACT,PHOTO_CONTACT};
	    ContentResolver cr = context.getContentResolver();
		Cursor cursor =cr.query(QUERY_URI,projection,null,null,null);
		
     if (cursor.getCount() > 0) {
	  		 while (cursor.moveToNext()) {
	            Contact contact = getContact(cursor);
	            contactList.add(contact);   
	        }
	 
	    }
     
     
     cursor.close();
     return contactList;

	}
	
	
	private Contact getContact(Cursor cursor) {
	
        String contactId = cursor.getString(cursor.getColumnIndex(CONTACT_ID));
        String name = (cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
        Uri uri = Uri.withAppendedPath(QUERY_URI, String.valueOf(contactId));
        String phone = (cursor.getString(cursor.getColumnIndex(PHONE_NUMBER)));
        String photo =(cursor.getString(cursor.getColumnIndex(PHOTO_CONTACT)));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        String intentUriString = intent.toUri(0);
 
        Contact contact = new Contact();
        contact.id = Integer.valueOf(contactId);
        contact.name = name;
        contact.uriString = intentUriString;
        contact.phone = phone;
        contact.photo = photo;
 
    
        return contact;
    }
	
}
