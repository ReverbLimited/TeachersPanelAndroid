package reverb.smartstudy.teacher.Activity;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.mdjahirulislam.youtubestyletabs.R;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TableRow tableRow3;


    private Toolbar toolbar;
    private SearchBox search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        tableRow3 = (TableRow) findViewById(R.id.tableRow3);

        toolbar = (Toolbar) findViewById(R.id.toolbarAttendance);
        this.setSupportActionBar(toolbar);
        toolbar.setTitle("Smart Study");
        search = (SearchBox) findViewById(R.id.searchBoxAttendance);
        search.enableVoiceRecognition(this);

        tableRow3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tableRow3.setAlpha(0.2f);

                        tableRow3.setBackgroundResource(R.color.colorAccent);
                        tableRow3.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        tableRow3.setAlpha(1.0f);
                        startActivity(new Intent(AttendanceActivity.this,StudentsAttendanceListActivity.class));


//                        Toast.makeText(AttendanceActivity.this, "Index No: "+v.getId(), Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });


        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                openSearch();
                return true;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.attendance_present_today) {
            startActivity(new Intent(AttendanceActivity.this,StudentsAttendanceListActivity.class));
            finish();
        } else if (id == R.id.attendance_absent_today) {
//            sortedByTV.setText("Shorted by: Absent Today");

        } else if (id == R.id.attendance_highest) {
//            sortedByTV.setText("Shorted by: Highest Attendance");

        } else if (id == R.id.attendance_lowest) {
//            sortedByTV.setText("Shorted by: Lowest Attendance");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public void openSearch() {
//        toolbar.setTitle("Search here.....");
        search.setHint("Search here.....");
        search.revealFromMenuItem(R.id.action_search, this);
//        for (int x = 0; x < 10; x++) {
//            SearchResult option = new SearchResult("Result "
//                    + Integer.toString(x), getResources().getDrawable(
//                    R.drawable.ic_history));
//            search.addSearchable(option);
//        }
        search.setMenuListener(new SearchBox.MenuListener() {

            @Override
            public void onMenuClick() {
                // Hamburger has been clicked
                Toast.makeText(AttendanceActivity.this, "Menu click",
                        Toast.LENGTH_LONG).show();
            }

        });
        search.setSearchListener(new SearchBox.SearchListener() {

            @Override
            public void onSearchOpened() {
                // Use this to tint the screen

            }

            @Override
            public void onSearchClosed() {
                // Use this to un-tint the screen
                closeSearch();
            }

            @Override
            public void onSearchTermChanged(String term) {
                // React to the search term changing
                // Called after it has updated results
            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(AttendanceActivity.this, searchTerm + " Searched",
                        Toast.LENGTH_LONG).show();
                toolbar.setTitle(searchTerm);

            }

            @Override
            public void onResultClick(SearchResult result) {
                //React to result being clicked
            }

            @Override
            public void onSearchCleared() {

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void closeSearch() {
        search.hideCircularly(this);
        if(search.getSearchText().isEmpty())toolbar.setTitle("");
    }



//    public void row_click(View view){
//        switch (view.getId()){
//            case R.id.tableRow2:
//                tableRow2.setBackgroundColor(Color.GREEN);
//                intent = new Intent(getApplicationContext(),Attendance_details.class);
//                extra = new Bundle();
//                extra.putString("Class",t21);
//                extra.putString("Subject",t22);
//                extra.putString("Section",t23);
//                intent.putExtras(extra);
//                startActivity(intent);
//                bColorYellow=false;
//
//
//                Toast.makeText(this, "row2", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tableRow3:
//                intent = new Intent(getApplicationContext(),Attendance_details.class);
//                extra = new Bundle();
//                extra.putString("Class",t31);
//                extra.putString("Subject",t32);
//                extra.putString("Section",t33);
//                intent.putExtras(extra);
//                startActivity(intent);
//                startActivity(intent);
//                Toast.makeText(this, "row3", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tableRow4:
//                intent = new Intent(getApplicationContext(),Attendance_details.class);
//                extra.putString("Class",t41);
//                extra.putString("Subject",t42);
//                extra.putString("Section",t43);
//                intent.putExtras(extra);
//                startActivity(intent);
//                Toast.makeText(this, "row4", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tableRow5:
//                intent = new Intent(getApplicationContext(),Attendance_details.class);
//                extra.putString("Class",t51);
//                extra.putString("Subject",t52);
//                extra.putString("Section",t53);
//                intent.putExtras(extra);
//                startActivity(intent);
//                Toast.makeText(this, "row5", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tableRow6:
//                intent = new Intent(getApplicationContext(),Attendance_details.class);
//                extra.putString("Class",t61);
//                extra.putString("Subject",t62);
//                extra.putString("Section",t63);
//                intent.putExtras(extra);
//                startActivity(intent);
//                Toast.makeText(this, "row6", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tableRow7:
//                intent = new Intent(getApplicationContext(),Attendance_details.class);
//                extra.putString("Class",t71);
//                extra.putString("Subject",t72);
//                extra.putString("Section",t73);
//                intent.putExtras(extra);
//                startActivity(intent);
//                Toast.makeText(this, "row7", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

}
