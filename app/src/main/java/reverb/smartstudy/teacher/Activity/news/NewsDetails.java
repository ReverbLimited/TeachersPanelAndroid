package reverb.smartstudy.teacher.Activity.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mdjahirulislam.youtubestyletabs.R;

/**
 * Created by HP on 12/13/2016.
 */

public class NewsDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetails);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");
        String date = intent.getStringExtra("date");
        TextView tvEventName= (TextView) findViewById(R.id.tvNewsNameDetails);
        tvEventName.setText(title);
        TextView tvEventDetails= (TextView) findViewById(R.id.tvEventDetailsDetails);
        tvEventDetails.setText(body);
        TextView tvEventDate= (TextView) findViewById(R.id.tvEventDateDetails);
        tvEventDate.setText(date);

    }
}
