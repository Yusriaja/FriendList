package id.aditya.friendlist.kelas;

import id.aditya.friendlist.R;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {
	
	private final Activity ctx;
	private ArrayList<String> dataNama = new ArrayList<String>();
	private ArrayList<String> dataId = new ArrayList<String>();
	private ArrayList<String> dataAge = new ArrayList<String>();
	private ArrayList<String> dataAddress = new ArrayList<String>();

	public ListAdapter(Activity context, ArrayList<String> str_dataNama, ArrayList<String> str_dataId, ArrayList<String> str_dataAge, ArrayList<String> str_dataAddress) {
		super(context, R.layout.list_friend, str_dataNama);
		// TODO Auto-generated constructor stub
		this.ctx = context;
		this.dataNama = str_dataNama;
		this.dataId = str_dataId;
		this.dataAge = str_dataAge;
		this.dataAddress = str_dataAddress;
	}
	
	@SuppressLint({ "ViewHolder", "InflateParams" })
	public View getView(int position, View view, ViewGroup parent){
		LayoutInflater inflater = ctx.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_friend, null, true);
		
		TextView txtName = (TextView)rowView.findViewById(R.id.txtNama);
		TextView txtId = (TextView)rowView.findViewById(R.id.txtId);
		TextView txtAge = (TextView)rowView.findViewById(R.id.txtAge);
		TextView txtAddress = (TextView)rowView.findViewById(R.id.txtAddress);
		ImageView imgContact = (ImageView)rowView.findViewById(R.id.imgFriend);
		
		Typeface font = Typeface.createFromAsset(ctx.getAssets(), "RobotoCondensed-Light.ttf");
		txtId.setText(dataId.get(position));
		txtAge.setText(dataAge.get(position));
		txtAddress.setText(dataAddress.get(position));
		txtName.setText(dataNama.get(position));
		txtName.setTypeface(font);
		imgContact.setImageResource(R.drawable.ic_contact);
		return rowView;
		
	}

}
