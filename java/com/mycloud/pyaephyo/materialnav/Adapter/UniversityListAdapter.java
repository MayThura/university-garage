package com.mycloud.pyaephyo.materialnav.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Windows on 10/31/2016.
 */

public class UniversityListAdapter extends BaseAdapter {
    private Context mContext;
    private List<UniversityInfo> uniList;
    public UniversityListAdapter(Context mContext, List<UniversityInfo> uniList)
    {
        this.mContext=mContext;
        this.uniList=uniList;

    }

    @Override
    public long getItemId(int position) {
        return uniList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(mContext, R.layout.university_row_layout,null);
        ImageView imgPhoto=(ImageView)v.findViewById(R.id.imgPhoto);
        TextView txtName=(TextView)v.findViewById(R.id.txtName);
        TextView txtMark=(TextView)v.findViewById(R.id.txtMark);
        Bitmap bm= BitmapFactory.decodeByteArray(uniList.get(position).getUniPhoto(),0,uniList.get(position).getUniPhoto().length);
        imgPhoto.setImageBitmap(bm);
        txtName.setText(uniList.get(position).getUniName());
        txtMark.setText(uniList.get(position).getUniMark());
        return v;

    }

    @Override
    public int getCount() {
        return uniList.size();
    }

    @Override
    public Object getItem(int position) {
        return uniList.get(position);
    }
}
