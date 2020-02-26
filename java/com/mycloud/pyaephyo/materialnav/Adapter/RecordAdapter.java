package com.mycloud.pyaephyo.materialnav.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.Recording.RecordedFileList;

import java.io.File;

/**
 * Created by Lullaby on 26-Oct-16.
 */

public class RecordAdapter extends ArrayAdapter<File>{

    TextView txtName,txtPhone;
    RecordedFileList fileList;
    public RecordAdapter(Context context, int resource) {

        super(context, resource);
    }
    @Override
    public void add(File object) {
        super.add(object);
    }

    @Nullable
    @Override
    public File getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Record record=(Record)getItem(position);

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.record_row, null);
        }
        txtName=(TextView) convertView.findViewById(R.id.simpleName);
        File f = getItem(position);
        txtName.setText(f.getName().toString());

        return convertView;
    }

}
