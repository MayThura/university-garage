package com.mycloud.pyaephyo.materialnav.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycloud.pyaephyo.materialnav.R;



public class FavCursorAdapter extends RecyclerView.Adapter<FavCursorAdapter.MyViewHolder> {
    Context context;
    Cursor cursor;

    int _id;
    String uniId, uniName, uniLocation, uniCost, uniUniform, uniPhone, uniEmail, uniType, uniWebsite, uniMark, uniRegion, uniLat, uniLng;

    SQLiteDatabase mDatabase;


    public FavCursorAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        this.cursor.moveToFirst();
    }



    @Override
    public FavCursorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_card, parent, false);
        return new FavCursorAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(FavCursorAdapter.MyViewHolder holder, final int position)
    {

        _id = cursor.getInt(cursor.getColumnIndex("_id"));
        uniId=cursor.getString(cursor.getColumnIndex("uniId"));
        uniName = cursor.getString(cursor.getColumnIndex("uniName"));
        Bitmap bm= BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndex("uniPhoto")),0,cursor.getBlob(cursor.getColumnIndex("uniPhoto")).length);

        holder.txtName.setText(uniName);
        holder.favImage.setImageBitmap(bm);
        cursor.moveToNext();
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtName;
        public CardView cardView;
        public ImageView favImage;
        public MyViewHolder(View view)
        {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtFav);
            favImage=(ImageView)view.findViewById(R.id.favPhoto);
            cardView=(CardView)view.findViewById(R.id.fav_card);

        }
    }

}


