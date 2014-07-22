package com.example.phonecall;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrayContactAdapter extends BaseAdapter {
	List<Contact> contactList;
	LayoutInflater contactInflater;

	static class ViewHolder {
		public TextView nameView;
		public TextView phone;
		public ImageView iconeView;
	}

	public ArrayContactAdapter(Context context, List<Contact> list) {
		this.contactList = list;
		contactInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		ViewHolder holder;

		if (rowView == null) {
			rowView = contactInflater.inflate(R.layout.contact_info, null);
			holder = new ViewHolder();
			holder.nameView = (TextView) rowView.findViewById(R.id.name);
			holder.phone = (TextView) rowView.findViewById(R.id.phone);
			holder.iconeView = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}

		Contact contact = getItem(position);

		holder.nameView.setText(contact.getName());
		holder.phone.setText(contact.getPhone());
		//holder.iconeView.setImageResource(contact.getPhoto());
		return rowView;
	}

	@Override
	public int getCount() {
		return contactList.size();
	}

	@Override
	public Contact getItem(int position) {
		return contactList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

}
