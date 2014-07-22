package com.example.phonecall;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Xml;

public class BlockedListXml extends Application {
	ArrayList<String> blockedPhone = new ArrayList<String>();
	ArrayList<String> blockedSMS = new ArrayList<String>();
	ArrayList<ArrayList<Contact>> blockedList = new ArrayList<ArrayList<Contact>>();

	private InputStream is = null;

	public ArrayList<ArrayList<Contact>> getBlockedListXml(Activity activity)
			throws XmlPullParserException, IOException {
		XmlPullParser parser = Xml.newPullParser();
		try {
			is = activity.getAssets().open("listBlocked.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("Fichier", "File not found");
		}
		parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		parser.setInput(is, null);
		int event = parser.next();
		String tagName = null;

		while (event != XmlPullParser.END_DOCUMENT) {
			
			tagName = parser.getName();
			while (event != XmlPullParser.END_TAG && !tagName.equals("phone")){
				if(event==XmlPullParser.TEXT){
					Log.i("OK",""+parser.getText());
					event = parser.next();
				}
				
				event = parser.next();
			}
			/*if (event == XmlPullParser.START_TAG && ) {
				while (event != XmlPullParser.END_TAG && tagName.equals("phone")){
					if (event == XmlPullParser.TEXT) 
				Log.i("NOM",""+parser.getText());
				event = parser.next();
				Log.i("Prenom",""+parser.getText());
				
				if (event == XmlPullParser.START_TAG && tagName.equals("name")) {
				
				event = parser.next();
				blockedPhone.add(parser.getText());
				}
			} 
			}else if (event == XmlPullParser.START_TAG
					&& tagName.equals("sms")) {

				event = parser.next();
				blockedSMS.add(parser.getText());
			}
			Log.i("Event",""+event+ "   " +parser.getName());
			event = parser.next();
			Log.i("Text",""+event+ "   " +parser.getText());
	*/	}
			
	
		//blockedList.add(blockedPhone);
		//blockedList.add(blockedSMS);
		return blockedList;
	}

	public ArrayList<Contact> getNameFromBlockedListXml(ArrayList<String> list,
			Context context) {
		ArrayList<Contact> blocked = new ArrayList<Contact>();
		Contact contact = new Contact();

		Uri QUERY_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.PHOTO_ID };
		ContentResolver cr = context.getContentResolver();

		for (String p : list) {
			Log.i("P", "" + p);
			Cursor cursor = cr.query(QUERY_URI, projection,
					ContactsContract.CommonDataKinds.Phone.NUMBER + "= '" + p
							+ "'", null, null);
			if (cursor != null) {
				cursor.moveToFirst();
				Log.i("Cursor", "" + cursor.getCount());
			}
			/*
			 * Log.i("Liste nom ",""+cursor.getString(cursor.getColumnIndex(
			 * ContactsContract.Contacts.DISPLAY_NAME)));
			 * contact.id=Integer.valueOf
			 * (cursor.getString(cursor.getColumnIndex(
			 * ContactsContract.Contacts._ID)));
			 * contact.name=cursor.getString(cursor
			 * .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			 * contact.phone=p;
			 * contact.photo=cursor.getString(cursor.getColumnIndex
			 * (ContactsContract.Contacts.PHOTO_ID)); blocked.add(contact);
			 */
			cursor.close();
		}

		return blocked;

	}

}
