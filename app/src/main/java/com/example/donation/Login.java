package com.example.donation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private static final String TAG = "Donation";
    private FirebaseAuth firebaseAuth;
    private SignInClient signInClient;
    private FirebaseFirestore firebaseFirestore;
    private EditText emailEdit, passwordEdit;

//    private final ActivityResultLauncher<IntentSenderRequest> signInLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    handleSignInResult(result.getData());
//                }
//            }
//    );

//    private void handleSignInResult(Intent intent) {
//        try {
//            SignInCredential credential = signInClient.getSignInCredentialFromIntent(intent);
//            String idToken = credential.getGoogleIdToken();
//            firebaseAuthWithGoogle(idToken);
//
//        }catch (ApiException e) {
//            e.printStackTrace();
//        }
//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
//        Task<AuthResult> authResultTask = firebaseAuth.signInWithCredential(authCredential);
//        authResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    FirebaseUser user = firebaseAuth.getCurrentUser();
//                    updateUIgoogle(user);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//    }

//    private void updateUIgoogle(FirebaseUser user) {
//        if(user != null){
//            Intent intent = new Intent(Login.this, profile.class);
//            startActivity(intent);
//        }
//    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Donation_FullScreen);//full screen
        setContentView(R.layout.login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        emailEdit = findViewById(R.id.login_email);
        passwordEdit = findViewById(R.id.login_password);

//        signInClient = Identity.getSignInClient(getApplicationContext());
//
//        findViewById(R.id.google).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//            }
//        });






        Button signup = findViewById(R.id.signup1);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
                finish();
            }
        });



        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Log.i(TAG, "singInWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);

                                }else {
                                    Log.w(TAG, "singInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void updateUI(FirebaseUser user) {


        if(user != null){
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
            finish();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        updateUI(user);
//    }

//    private void signIn(){
//        GetSignInIntentRequest signInIntentRequest = GetSignInIntentRequest.builder()
//                .setServerClientId(getString(R.string.web_client_id)).build();
//
//        Task<PendingIntent> signInIntent = signInClient.getSignInIntent(signInIntentRequest);
//        signInIntent.addOnSuccessListener(new OnSuccessListener<PendingIntent>() {
//            @Override
//            public void onSuccess(PendingIntent pendingIntent) {
//                launchSignIn(pendingIntent);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//    private void launchSignIn(PendingIntent pendingIntent) {
//        IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(pendingIntent).build();
//        signInLauncher.launch(intentSenderRequest);
//    }




//    private void updateUI(FirebaseUser user) {
//
//
//    }
}