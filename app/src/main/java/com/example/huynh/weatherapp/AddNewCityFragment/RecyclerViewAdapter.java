package com.example.huynh.weatherapp.AddNewCityFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huynh.weatherapp.MyCityList;
import com.example.huynh.weatherapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<String> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public RecyclerViewAdapter(Context context,ArrayList<String> mList) {
        this.mList = mList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

//        public TextView title, year, genre;
        public TextView cityName;
        public CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.m_title);
//            genre = (TextView) itemView.findViewById(R.id.m_genre);
//            year = (TextView) itemView.findViewById(R.id.m_year);
            cityName = itemView.findViewById(R.id.cityName);
            checkBox = itemView.findViewById(R.id.checkbox);


            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) MyCityList.getInstance().AddNewCity(cityName.getText().toString());
                    else MyCityList.getInstance().RemoveCity(cityName.getText().toString());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext,title.getText(),Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    Toast.makeText(mContext,"Long item clicked " + title.getText() + , Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = mLayoutInflater.inflate(R.layout.recycler_view_row,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
//        Movie movie = movieList.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
        String name = mList.get(position);
        holder.cityName.setText(name);
        holder.checkBox.setChecked(MyCityList.getInstance().CheckCityName(name));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}