package com.example.donation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.donation.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

public class profile extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    private static final String TAG = "eshop";
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private EditText editfname, editlname, editnic, editmobile,  editdob, editaddress;
    private RadioGroup bloodTypegroup, gendergroup, userTypeGroup;
    private RadioButton selctedbldtyp, selectedgender, selectedUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Donation_FullScreen);//full screen
        setContentView(R.layout.profile);

        drawerLayout = findViewById(R.id.drawer_home);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        editfname = findViewById(R.id.profile_fname);
        editlname = findViewById(R.id.profile_lname);
        editnic = findViewById(R.id.profile_Nic);
        editmobile = findViewById(R.id.profile_Phone);
        editaddress = findViewById(R.id.profile_address);
        editdob = findViewById(R.id.profile_dob);

        gendergroup = findViewById(R.id.profile_gender);
        gendergroup.clearCheck();

        bloodTypegroup = findViewById(R.id.profile_bloodbtn);
        bloodTypegroup.clearCheck();

        userTypeGroup = findViewById(R.id.profile_usertype);
        userTypeGroup.clearCheck();


        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        editdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(profile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        editdob.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });


        Button update = findViewById(R.id.profile_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedGenderId = gendergroup.getCheckedRadioButtonId();
                selectedgender = findViewById(selectedGenderId);

                int selectedBloodGroupId = bloodTypegroup.getCheckedRadioButtonId();
                selctedbldtyp = findViewById(selectedBloodGroupId);

                int selectedUserTypeId = userTypeGroup.getCheckedRadioButtonId();
                selectedUserType = findViewById(selectedUserTypeId);



                String currentuserid = firebaseAuth.getCurrentUser().getUid();
                String fname = editfname.getText().toString();
                String lname = editlname.getText().toString();
                String nic = editnic.getText().toString();
                String mobile = editmobile.getText().toString();
                String dob = editdob.getText().toString();
                String address = editaddress.getText().toString();
                String textgender;
                String textbloodtype;
                String textUserType;
                String imagePath;

                if(TextUtils.isEmpty(fname)){

                    Toast.makeText(profile.this,"Please enter your first name!", Toast.LENGTH_LONG);
                    editfname.setError("First name is required");
                    editfname.requestFocus();
                } else if(TextUtils.isEmpty(lname)){
                    Toast.makeText(profile.this,"Please enter your Second name!", Toast.LENGTH_LONG);
                    editlname.setError("Second name is required");
                    editlname.requestFocus();
                }else if (TextUtils.isEmpty(nic)){
                    Toast.makeText(profile.this,"Please enter your valid NIC number!", Toast.LENGTH_LONG);
                    editnic.setError("NIC is required");
                    editnic.requestFocus();
                }else if (nic.length()>12){
                    Toast.makeText(profile.this,"Please enter your valid NIC number!", Toast.LENGTH_LONG);
                    editnic.setError("NIC number longer than 12 digits");
                    editnic.requestFocus();
                }else if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(profile.this, "Please enter your valid mobile number!", Toast.LENGTH_LONG);
                    editmobile.setError("Mobile number is required");
                    editmobile.requestFocus();
                }else if (mobile.length() !=10){
                    Toast.makeText(profile.this,"Please enter your valid mobile number!", Toast.LENGTH_LONG);
                    editmobile.setError("NIC number should contain 10 digits");
                    editmobile.requestFocus();
                }else if (TextUtils.isEmpty(dob)) {
                    Toast.makeText(profile.this, "Please select your date of birth!", Toast.LENGTH_LONG);
                    editdob.setError("Date of birth is required");
                    editdob.requestFocus();
                }else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(profile.this, "Please enter your address!", Toast.LENGTH_LONG);
                    editaddress.setError("Address is required");
                    editaddress.requestFocus();
                }else if (gendergroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(profile.this, "Please select your gender!", Toast.LENGTH_LONG);
                    selectedgender.setError("Gender is required!");
                    selectedgender.requestFocus();
                }else if (bloodTypegroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(profile.this, "Please select your Blood type!", Toast.LENGTH_LONG);
                    selctedbldtyp.setError("Blood type is required!");
                    selctedbldtyp.requestFocus();
                }else if (userTypeGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(profile.this, "Please select your User type!", Toast.LENGTH_LONG);
                    selectedUserType.setError("User type is required!");
                    selectedUserType.requestFocus();
                }else{
                    textgender = selectedgender.getText().toString();
                    textbloodtype = selctedbldtyp.getText().toString();
                    textUserType = selectedUserType.getText().toString();
                    imagePath = "https://firebasestorage.googleapis.com/v0/b/saveme-3dd89.appspot.com/o/images%2Fpp.jpg?alt=media&token=39b8d235-4999-4c9b-a372-a2458cdf65ff";

                    if (firebaseAuth.getCurrentUser() != null) {

                        addDataToFirestore(currentuserid,fname, lname, nic,mobile,textgender,dob,address,textbloodtype, textUserType,imagePath);
                    }
                }
            }
        });
    }

    private void addDataToFirestore(String currentuserid,String fname, String lname, String nic, String mobile,String textgender, String dob, String address, String textbloodtype, String textUserType, String imagePath) {
        CollectionReference dbCourses = firebaseFirestore.collection("users");

        dbCourses.whereEqualTo("id", currentuserid)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                               for(QueryDocumentSnapshot snapshot : task.getResult()){


                                   User user = snapshot.toObject(User.class);

                                   user.setFname(fname);
                                   user.setLname(lname);
                                   user.setNic(nic);
                                   user.setMobile(mobile);
                                   user.setGender(textgender);
                                   user.setDob(dob);
                                   user.setAddress(address);
                                   user.setBldtyp(textbloodtype);
                                   user.setUserTyp(textUserType);
                                   user.setImagePath(imagePath);

                                  // User user = new User(fname,lname,nic,mobile,dob,address,textgender,textbloodtype);

                                   dbCourses.document(snapshot.getId()).set(user)
                                           .addOnSuccessListener(new OnSuccessListener<Void>() {
                                               @Override
                                               public void onSuccess(Void aVoid) {
                                                   Toast.makeText(profile.this, "Your details has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                                                   Intent i = new Intent(profile.this,otp.class);
//                                                   i.putExtra("user_type",textUserType);
//                                                   System.out.println(textUserType);
                                                   startActivity(i);
                                                   finish();
                                               }
                                           }).addOnFailureListener(new OnFailureListener() {
                                               @Override
                                               public void onFailure(@NonNull Exception e) {
                                                   Toast.makeText(profile.this, "Fail to add details \n" + e, Toast.LENGTH_SHORT).show();
                                               }
                                           });

                               }
                            }

                    }
                });
    }

//    public void showBottomNavigationView(boolean show){
//        if (show){
//            bottomNavigationView.setVisibility(View.VISIBLE);
//        }else{
//            bottomNavigationView.setVisibility(View.GONE);
//        }
//    }


}