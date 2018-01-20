 package reverb.smartstudy.teacher.Activity;

 import android.app.ProgressDialog;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;

 import com.example.mdjahirulislam.youtubestyletabs.R;

 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;
 import reverb.smartstudy.teacher.interfaces.ConnectionApi;
 import reverb.smartstudy.teacher.model.UserRequest;
 import reverb.smartstudy.teacher.preference.SchoolPref;
 import reverb.smartstudy.teacher.responseModelClass.SchoolResponseModel;


 public class ActivationActivity extends AppCompatActivity {
     private static final String TAG = "ActivationActivity";
     private static final int REQUEST_SIGNUP = 0;
     //boolean valid = true;
     SharedPreferences pref;
     SharedPreferences.Editor editor;
     ProgressDialog progressDialog;


     EditText etActivationCode;
     Button btActivationButton;
     TextView tvWrongActivationCode;
     String mode = "";
     String studentname = "";
     String nickname = "";

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         initializeGUI();

     }


     public void onActivationClick() {

         Log.d(TAG, "Activation");
         String activationcode = etActivationCode.getText().toString();
         checkActivationCode(activationcode);
         btActivationButton.setEnabled(false);
         startProgressDialog();
     }



     public void initializeGUI(){
         setContentView( R.layout.activity_activation);
         tvWrongActivationCode = (TextView) findViewById(R.id.tvWrongActivationCode);
         etActivationCode = (EditText) findViewById(R.id.etActivationCode);
         btActivationButton = (Button) findViewById(R.id.btActivationButton);
         btActivationButton.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 onActivationClick();
             }
         });
     }

     public void startProgressDialog(){
         progressDialog = new ProgressDialog(ActivationActivity.this,
                 R.style.MyAlertDialogStyle);
         progressDialog.setIndeterminate(true);
         progressDialog.setMessage("Checking Activation Code...");
         progressDialog.show();
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == REQUEST_SIGNUP) {
             if (resultCode == RESULT_OK) {

                 // TODO: Implement successful signup logic here
                 // By default we just finish the Activity and log them in automatically
                 this.finish();
             }
         }
     }

     @Override
     public void onBackPressed() {
         // Disable going back to the ChatMainActivity
         moveTaskToBack(true);
     }


     public void checkActivationCode(final String activationCode) {

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://smartstudy.com.bd/demo/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         ConnectionApi service = retrofit.create(ConnectionApi.class);
         UserRequest activationRequest = new UserRequest();
         activationRequest.setActivationcode(activationCode);


         Call<SchoolResponseModel> activationResponseCall = service.checkActivation(activationRequest);
         activationResponseCall.enqueue(new Callback<SchoolResponseModel>() {
             @Override
             public void onResponse(Call<SchoolResponseModel> call, Response<SchoolResponseModel> response) {

                 int statusCode=response.code();
                 Log.d("Activation statuscode", String.valueOf(statusCode));
                 //If statuscode is 200, the followingcode will set all information for user, assuming user
                 //is a student. If user is a gurdian, it will be handled in next.
                 if(statusCode==200) {  //means data received successfully

                     SchoolResponseModel school=response.body();
                     if(school.getBaseurl()!=null) {
                         SchoolPref.getInstance(getApplicationContext()).setSchoolbaseurl(school.getBaseurl());
                         String a=school.getWebappurl();
                         SchoolPref.getInstance(getApplicationContext()).setWebAppurl(school.getWebappurl());
                         SchoolPref.getInstance(getApplicationContext()).setSchoolname(school.getSchoolname());
                         String baseurl=SchoolPref.getInstance(getApplicationContext()).getSchoolbaseurl();
                         String c=school.getBaseurl();
                         tvWrongActivationCode.setVisibility( View.INVISIBLE);
                         Intent in = new Intent(ActivationActivity.this, LoginActivity.class);
                         startActivity(in);
                         finish();
                     }else{
                         tvWrongActivationCode.setVisibility( View.VISIBLE);
                         tvWrongActivationCode.setText("Incorrect Activate Code!");
                         // progressDialog.cancel();
                         if (progressDialog != null) {
                             progressDialog.dismiss();
                         }
                         btActivationButton.setEnabled(true);
                         Log.d("ActivationActivity","On Response: Failed");

                     }

                 }else{
                     tvWrongActivationCode.setVisibility( View.VISIBLE);
                     tvWrongActivationCode.setText("Incorrect Activate Code!");
                     // progressDialog.cancel();
                     if (progressDialog != null) {
                         progressDialog.dismiss();
                     }
                     btActivationButton.setEnabled(true);
                     Log.d("ActivationActivity","On Response: Failed");
                 }
                 Log.d("ActivationActivity","On Response: "+response.code());

             }

             @Override
             public void onFailure(Call<SchoolResponseModel> call, Throwable t) {

                 tvWrongActivationCode.setVisibility( View.VISIBLE);
                 tvWrongActivationCode.setText("Failed - Check network connection");
                 // progressDialog.cancel();
                 if (progressDialog != null) {
                     progressDialog.dismiss();
                 }
                 btActivationButton.setEnabled(true);
                 Log.d("ActivationActivity","On Response: Failed");

             }
         });

     }








 }