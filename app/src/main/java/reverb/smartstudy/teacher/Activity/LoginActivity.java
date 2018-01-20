package reverb.smartstudy.teacher.Activity;

import android.app.ProgressDialog;
import android.content.Context;
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
import reverb.smartstudy.teacher.preference.SharedPref;
import reverb.smartstudy.teacher.responseModelClass.UserLoginResponseModel;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    //boolean valid = true;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;

    EditText etUsername;
    EditText etPassword;
    Button btLoginButton;
    TextView tvWrongpassword,tvWrongActivation;
    String mode = "";
    String studentname = "";
    String nickname = "";
    Context context=this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeGUI();
    }


    public void onLoginClick() {

        Log.d(TAG, "Login");
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        login(username,password);
        btLoginButton.setEnabled(false);
        startProgressDialog();
    }

    public void onChangeActivationClick(){
        SchoolPref.getInstance(getApplicationContext()).clearAll();
        Intent intent = new Intent(LoginActivity.this, ActivationActivity.class);
        startActivity(intent);
    }

    public void onLoginClickBackup() {

        Log.d(TAG, "Login");
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        pref = getSharedPreferences("login.conf", Context.MODE_PRIVATE);
        editor = pref.edit();
        //Username and Password needed to be saved in editor as later I would check in php, if right person trying to authorize php
        editor.putString("username", etUsername.getText().toString());
        editor.putString("password", etPassword.getText().toString());
        editor.apply();

        login(username,password);

        btLoginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.MyAlertDialogStyle);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.


    }

    public void initializeGUI(){
        setContentView(R.layout.activity_login);
        tvWrongpassword = (TextView) findViewById(R.id.tvWrongPassword);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLoginButton = (Button) findViewById(R.id.btLoginButton);
        TextView tvSchoolName=(TextView) findViewById(R.id.tvSchooolName);
        tvSchoolName.setText(SchoolPref.getInstance(getApplicationContext()).getSchoolname());
        btLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });
        tvWrongActivation=(TextView) findViewById(R.id.tvWrongActivationCode);
        tvWrongActivation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onChangeActivationClick();
            }
        });

    }

    public void startProgressDialog(){
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.MyAlertDialogStyle);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
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


    public void login(final String username, final String password) {
        String baseurl=SchoolPref.getInstance(getApplicationContext()).getSchoolbaseurl();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SchoolPref.getInstance(getApplicationContext()).getSchoolbaseurl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ConnectionApi service = retrofit.create(ConnectionApi.class);
        UserRequest loginRequest = new UserRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call<UserLoginResponseModel> loginRespionseCall = service.login(loginRequest);
        loginRespionseCall.enqueue(new Callback<UserLoginResponseModel>() {
            @Override
            public void onResponse(Call<UserLoginResponseModel> call, Response<UserLoginResponseModel> response) {
                int statusCode=response.code();
                //If statuscode is 200, the followingcode will set all information for user, assuming user
                //is a student. If user is a gurdian, it will be handled in next.
                if(statusCode==200) {  //means data received successfully

                    UserLoginResponseModel user=response.body();
                    if(user.getRole()!=null) {
                        SharedPref.getInstance(getApplicationContext()).setUsername(username);
                        SharedPref.getInstance(getApplicationContext()).setPassword(password);
                        SharedPref.getInstance(getApplicationContext()).setRole(user.getRole());
                        SharedPref.getInstance(getApplicationContext()).setName(user.getName());
                        SharedPref.getInstance(getApplicationContext()).setUserid(user.getUserid());
                        SharedPref.getInstance(getApplicationContext()).setStudentUserid(user.getUserid());
                        tvWrongpassword.setVisibility( View.INVISIBLE);





                        Intent in = new Intent(LoginActivity.this, MainActivity.class);
                        SharedPref.getInstance(getApplicationContext()).setLoggedIn("true");
                        startActivity(in);
                        finish();
                    }else{
                        tvWrongpassword.setVisibility( View.VISIBLE);
                        tvWrongpassword.setText("Login Failed - Incorrect login information!");
                        // progressDialog.cancel();
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        btLoginButton.setEnabled(true);
                        Log.d("LoginActivity","On Response: Failed");

                    }

                }
                Log.d("LoginActivity","On Response: "+response.code());

            }

            @Override
            public void onFailure(Call<UserLoginResponseModel> call, Throwable t) {

                tvWrongpassword.setVisibility( View.VISIBLE);
                tvWrongpassword.setText("Login Failed - Check network connection");
                // progressDialog.cancel();
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                btLoginButton.setEnabled(true);
                Log.d("LoginActivity","On Response: Failed");

            }
        });

    }






}