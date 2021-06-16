package com.example.fetch_json_internet;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Hero_data> {

    private List<Hero_data> dataList;
    private Bitmap bitmap;
    private Context mCtx;

    public MyAdapter(List<Hero_data> dataList, Context mctx){
        super(mctx, R.layout.list_items, dataList);
        this.dataList = dataList;
        this.mCtx = mctx;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        convertView = inflater.inflate(R.layout.list_items, null, true);
        holder = new ViewHolder();
        //getting text views
        holder.name = convertView.findViewById(R.id.name);
        holder.email = convertView.findViewById(R.id.email);
        holder.gender = convertView.findViewById(R.id.gender);
        holder.ip_address = convertView.findViewById(R.id.ip_address);
        holder.imageView = convertView.findViewById(R.id.imageView);

        convertView.setTag(holder);

        Hero_data hero_data = dataList.get(position);
        String imageUrl = hero_data.getImage_url();
        String first_name = hero_data.getFirst_name().toString();
        String last_name = hero_data.getLast_name().toString();
        String gender =  hero_data.getGender().toString();
        String ip_address = hero_data.getIp_address().toString();
        String email = hero_data.getEmail().toString();

        holder.name.setText(first_name + last_name);
        holder.email.setText(email);
        holder.gender.setText(gender);
        holder.ip_address.setText(ip_address);

        if(holder.imageView != null){
            new ImageDownloaderTask(holder.imageView).execute(imageUrl);
        }
        holder.imageView.setImageBitmap(bitmap);
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView email;
        TextView gender;
        TextView ip_address;
        ImageView imageView;
    }
}
