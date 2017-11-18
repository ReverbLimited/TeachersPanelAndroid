package reverb.smartstudy.teacher.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.news.NewsDetails;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    String newsName;
    String newsDate;
    String newsDescription;
    public TextView name,date;
    Context context;

    public CustomViewHolder(final View itemView) {
        super(itemView);
        context=itemView.getContext();
        name = (TextView) itemView.findViewById(R.id.tvName);
        date = (TextView) itemView.findViewById(R.id.tvDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hi", "Element " + getPosition() + " clicked. "+newsName);
               Intent myIntent = new Intent(context,NewsDetails.class);
               myIntent.putExtra("title",newsName);
               myIntent.putExtra("body", newsDescription);
               myIntent.putExtra("date", newsDate);
                context.startActivity(myIntent);
            }
        });
    }

    public void setData(Cursor c) {
        name.setText(c.getString(c.getColumnIndex("name")));
        date.setText(c.getString(c.getColumnIndex("created_at")));
        newsName=c.getString(c.getColumnIndex("name"));
        newsDate=c.getString(c.getColumnIndex("created_at"));
        newsDescription=c.getString(c.getColumnIndex("description"));
    }


}