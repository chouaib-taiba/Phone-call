package com.example.phonecall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BlockedListXml bl = new BlockedListXml();
		ArrayList<ArrayList<Contact>> blockedList = new ArrayList<ArrayList<Contact>>();
		
		try {
			/*blockedList =*/ bl.getBlockedListXml(this);

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

		final ListView contactList = (ListView) findViewById(R.id.list_view);
		Contacts phoneContacts = new Contacts();
		List<Contact> listContact = phoneContacts
				.getContactList(getBaseContext());
		contactList.setAdapter(new ArrayContactAdapter(this, listContact));
		contactList.setOnItemClickListener(new OnItemClickListener() {
			// TelephonyManager tm = (TelephonyManager)
			// getSystemService(TELEPHONY_SERVICE);
			// String phone = tm.getLine1Number();

			@Override
			public void onItemClick(AdapterView<?> Arg0, View v, int position,
					long arg3) {
				Contact contact = (Contact) contactList.getAdapter().getItem(
						position);
				Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
						+ contact.getPhone()));
				startActivity(call);

			}
		});

	}
}
