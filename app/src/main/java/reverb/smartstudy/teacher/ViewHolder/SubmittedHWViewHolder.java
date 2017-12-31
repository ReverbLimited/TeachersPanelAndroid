package reverb.smartstudy.teacher.ViewHolder;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

import de.hdodenhof.circleimageview.CircleImageView;
import reverb.smartstudy.teacher.database.HomeWorkListTableItems;
import reverb.smartstudy.teacher.model.SubmittedHomeWorkResponseModel;

/**
 * Created by mdjahirulislam on 30/12/17.
 */

public class SubmittedHWViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private CircleImageView studentImageIV;
    private CircleImageView studentStatusIV;
    private TextView studentNameTV;
    private TextView studentRollTV;
    private TextView studentSubmittedDateTV;

    public SubmittedHWViewHolder(View itemView) {
        super( itemView );
        context=itemView.getContext();
        studentImageIV = (CircleImageView) itemView.findViewById( R.id.singleSubmittedStudentIV);
        studentStatusIV = (CircleImageView) itemView.findViewById( R.id.singleSubmittedStudentStatusIV);
        studentNameTV = (TextView) itemView.findViewById( R.id.singleSubmittedStudentNameTV);
        studentRollTV = (TextView) itemView.findViewById(R.id.singleSubmittedStudentRollTV);
        studentSubmittedDateTV = (TextView) itemView.findViewById(R.id.singleSubmittedStudentSubmittedDateTV);
    }

    public void setData(SubmittedHomeWorkResponseModel.SubmittedStudentList submittedStudentList) {

        studentNameTV.setText(submittedStudentList.getStatus()+" --> Change to Student Name");
        studentRollTV.setText("Roll: "+String.valueOf(submittedStudentList.getStudentsId())+" --> Change to Student Roll");
        studentSubmittedDateTV.setText( submittedStudentList.getCreatedAt() );
    }

}
