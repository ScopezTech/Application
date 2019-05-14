package com.example.my_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidhire.splashscreen.R;

import java.util.ArrayList;

public class infoListAdapter extends ArrayAdapter<Info> {
    private static final String TAG = "infoListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    private ArrayList<Info> listofobjects = new ArrayList<>();

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView location;
        TextView date;
        TextView time;
        TextView fee;
        TextView instructor;
    }

    @Override
    public int getCount() {
        return listofobjects.size();
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public infoListAdapter(Context context, int resource, ArrayList<Info> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        listofobjects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String loc = getItem(position). getLocation();
        String date  = getItem(position).getDate();
        String time = getItem(position).getTime();
        String fees = getItem(position).getFees();
        String ins = getItem(position).getInstructor();

        //Create the person object with the information
        Info person = new Info(loc,date,time,fees,ins);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.location = (TextView) convertView.findViewById(R.id.location);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            holder.fee= (TextView) convertView.findViewById(R.id.fee);
            holder.instructor= (TextView) convertView.findViewById(R.id.instructor);



            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

    Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.location.setText(person.getLocation());
        holder.date.setText(person.getDate());
        holder.time.setText(person.getTime());
        holder.fee.setText(person.getFees());
        holder.instructor.setText(person.getInstructor());

        return convertView;
    }
}
