package org.chrivin.userinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText edtTextName, edtTextEmail, edtTextPassword, edtTextPassRepeat;
    private Button btnPickImg, btnRegister;
    private TextView txtWarnName,txtWarnEmail,txtWarnPass,txtWarnPassRepeat;
    private Spinner spinnerCountry;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnPickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }
    private void initRegister(){
        Log.d(TAG, "initRegister: Started");

        if (validateData()){
            if(agreementCheck.isChecked()){
                showSnackBar();
            }else{
                Toast.makeText(this, "You need to check the license agreement ", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPass.setVisibility(View.GONE);
        txtWarnPassRepeat.setVisibility(View.GONE);

        String name = edtTextName.getText().toString();
        String email = edtTextEmail.getText().toString();
        String country = spinnerCountry.getSelectedItem().toString();
        String gender = "";
        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rbGendMale:
                gender = "Male";
                break;
            case R.id.rbGendFemale:
                gender = "Female";
                break;
            case R.id.rbGendGajelas:
                gender = "Stress";
                break;
            default:
                gender = "Unknown";
                break;
        }
        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + country;

        Log.d(TAG, "showSnackBar: Snack Bar Text: " + snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtTextName.setText("");
                        edtTextEmail.setText("");
                        edtTextPassword.setText("");
                        edtTextPassRepeat.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: started");
        if (edtTextName.getText().toString().equals("")) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter your name");
            return false;
        }
        if (edtTextEmail.getText().toString().equals("")) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Enter your email");
            return false;
        }
        if (edtTextPassword.getText().toString().equals("")) {
            txtWarnPass.setVisibility(View.VISIBLE);
            txtWarnPass.setText("Enter your password");
            return false;
        }
        if (edtTextPassRepeat.getText().toString().equals("")) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Re enter your password");
            return false;
        }
        if(!edtTextPassword.getText().toString().equals(edtTextPassRepeat.getText().toString())){
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Password doesn't match");
            return false;
        }
        return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");
        edtTextName = findViewById(R.id.edtTextName);
        edtTextEmail = findViewById(R.id.edtTextEmail);
        edtTextPassword = findViewById(R.id.edtTextPassword);
        edtTextPassRepeat = findViewById(R.id.edtTextPassRepeat);

        btnPickImg = findViewById(R.id.btnPickImg);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPass = findViewById(R.id.txtWarnPass);
        txtWarnPassRepeat = findViewById(R.id.txtWarnPassRepeat);

        spinnerCountry = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        agreementCheck = findViewById(R.id.agreementCheck);
        parent = findViewById(R.id.parent);

    }
}