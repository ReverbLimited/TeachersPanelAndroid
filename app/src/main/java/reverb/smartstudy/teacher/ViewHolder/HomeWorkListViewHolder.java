package reverb.smartstudy.teacher.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import reverb.smartstudy.teacher.Activity.homeWork.Activity.HomeWorkDetailsActivity;
import reverb.smartstudy.teacher.database.HomeWorkListTableItems;

/**
 * Created by mdjahirulislam on 10/12/17.
 */

public class HomeWorkListViewHolder extends RecyclerView.ViewHolder {
    String hwTitle;
    String hwUniqueId;
    String hwDeadLine;
    String hwTask;
    public TextView hwTitleTV;
    public TextView hwDeadLineTV;
    public TextView hwCreatedAtTV;
    Context context;

    public HomeWorkListViewHolder(final View itemView) {
        super(itemView);
        context=itemView.getContext();
        hwTitleTV = (TextView) itemView.findViewById( R.id.homeWorkTitleTV);
        hwDeadLineTV = (TextView) itemView.findViewById(R.id.homeWorkDeadLineTV);
        hwCreatedAtTV = (TextView) itemView.findViewById(R.id.homeWorkCreateAt);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("hi", "Element " + getPosition() + " clicked. "+newsName);
                Intent myIntent = new Intent(context,HomeWorkDetailsActivity.class);
                myIntent.putExtra("hwUniqueId",hwUniqueId);
                myIntent.putExtra("title",hwTitle);
                myIntent.putExtra("task", hwTask);
                myIntent.putExtra("deadLine", hwDeadLine);
                context.startActivity(myIntent);
            }
        });
    }

    public void setData(Cursor c) {

        hwTitleTV.setText(c.getString(c.getColumnIndex( HomeWorkListTableItems.HW_TITLE)));
        hwDeadLineTV.setText(c.getString(c.getColumnIndex(HomeWorkListTableItems.HW_DEADLINE)));
        hwCreatedAtTV.setText(c.getString(c.getColumnIndex(HomeWorkListTableItems.CREATED_AT)));
        hwUniqueId=c.getString(c.getColumnIndex(HomeWorkListTableItems.HW_ID));
        hwTitle=c.getString(c.getColumnIndex(HomeWorkListTableItems.HW_TITLE));
        hwDeadLine =c.getString(c.getColumnIndex(HomeWorkListTableItems.HW_DEADLINE));
        hwTask=c.getString(c.getColumnIndex(HomeWorkListTableItems.HW_TASK));
    }


}