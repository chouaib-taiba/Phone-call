package com.example.phonecall;

import java.util.ArrayList;
import java.util.List;

import com.example.phonecall.ArrayContactAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayBlockedContacts extends BaseAdapter {
	ArrayList<Contact> blockedList;
	LayoutInflater blockedInflater;

	static class XmlHolder {
		public TextView nameView;
		public TextView phone;
		public ImageView iconeView;

	}
	
	
	
	public ArrayBlockedContacts(Context context,ArrayList<Contact> list) {
		// TODO Auto-generated constructor stub
		this.blockedList = list;
		blockedInflater = LayoutInflater.from(context);
	}
	
	
	
	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		ViewHolder holder;

		if (rowView == null) {
			rowView = blockedInflater.inflate(R.layout.contact_info, null);
			holder = new ViewHolder();
			holder.nameView = (TextView) rowView.findViewById(R.id.name);
			holder.phone = (TextView) rowView.findViewById(R.id.phone);
			holder.iconeView = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(holder);
		}else {
			holder = (ViewHolder) rowView.getTag();
		}

		Contact contact = getItem(position);

		holder.nameView.setText(contact.getName());
		holder.phone.setText(contact.getPhone());
		//   holder.iconeView.setImageResource(co.getIcone());
		return rowView;
	}

	@Override
	public int getCount() {
		return blockedList.size();
	}
	@Override
	public Contact getItem(int position) {
		return blockedList.get(position);
	}
	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	
	

}

