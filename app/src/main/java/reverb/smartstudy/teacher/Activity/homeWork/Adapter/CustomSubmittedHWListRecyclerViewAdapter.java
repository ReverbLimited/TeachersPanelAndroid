package reverb.smartstudy.teacher.Activity.homeWork.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mdjahirulislam.youtubestyletabs.R;

import java.util.ArrayList;
import reverb.smartstudy.teacher.ViewHolder.SubmittedHWViewHolder;
import reverb.smartstudy.teacher.responseModelClass.SubmittedHomeWorkResponseModel;

/**
 * Created by mdjahirulislam on 30/12/17.
 */

public class CustomSubmittedHWListRecyclerViewAdapter extends RecyclerView.Adapter<SubmittedHWViewHolder> {
    private static final String TAG = CustomSubmittedHWListRecyclerViewAdapter.class.getSimpleName();
    private ArrayList<SubmittedHomeWorkResponseModel.SubmittedStudentList> studentLists;
    private Context context;

    public CustomSubmittedHWListRecyclerViewAdapter(Context context, ArrayList<SubmittedHomeWorkResponseModel.SubmittedStudentList> submittedHomeWorkResponseModelArrayList) {
        this.studentLists = submittedHomeWorkResponseModelArrayList;
        this.context = context;
    }

    @Override
    public SubmittedHWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.single_home_work_submission_list_design,parent,false);
        return new SubmittedHWViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubmittedHWViewHolder holder, int position) {

        SubmittedHomeWorkResponseModel.SubmittedStudentList submittedStudentList = studentLists.get( position );


        holder.setData(submittedStudentList);

    }

    @Override
    public int getItemCount() {
        return studentLists.size();
    }
}
