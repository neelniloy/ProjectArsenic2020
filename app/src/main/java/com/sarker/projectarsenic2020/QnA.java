package com.sarker.projectarsenic2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class QnA extends AppCompatActivity {

    private ImageView back;
    private String sl,kn, wn,uc, tc,dc,hn,fn,mn,pn,v,p,u,t,d,current_user_id,saveCurrentDate,saveCurrentTime;
    private Button submit;
    private EditText a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference FormRef,tempRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_qn);


        sl = getIntent().getStringExtra("sl");
        kn = getIntent().getStringExtra("kn");
        wn = getIntent().getStringExtra("wn");
        uc = getIntent().getStringExtra("uc");
        tc = getIntent().getStringExtra("tc");
        dc = getIntent().getStringExtra("dc");
        hn = getIntent().getStringExtra("hn");
        fn = getIntent().getStringExtra("fn");
        mn = getIntent().getStringExtra("mn");
        pn = getIntent().getStringExtra("pn");
        v = getIntent().getStringExtra("v");
        p = getIntent().getStringExtra("p");
        u = getIntent().getStringExtra("u");
        t = getIntent().getStringExtra("t");
        d = getIntent().getStringExtra("d");


        back = findViewById(R.id.back);
        submit = findViewById(R.id.btn_submit);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);
        a9 = findViewById(R.id.a9);
        a10 = findViewById(R.id.a10);
        a11 = findViewById(R.id.a11);
        a12 = findViewById(R.id.a12);
        a13 = findViewById(R.id.a13);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String aa1 = a1.getText().toString();
                String aa2 = a2.getText().toString();
                String aa3 = a3.getText().toString();
                String aa4 = a4.getText().toString();
                String aa5 = a5.getText().toString();
                String aa6 = a6.getText().toString();
                String aa7 = a7.getText().toString();
                String aa8 = a8.getText().toString();
                String aa9 = a9.getText().toString();
                String aa10 = a10.getText().toString();
                String aa11 = a11.getText().toString();
                String aa12 = a12.getText().toString();
                String aa13 = a13.getText().toString();



                if (aa1.isEmpty()) {
                    a1.setError("*required field");
                    a1.requestFocus();

                }
                else if (aa2.isEmpty()) {
                    a2.setError("*required field");
                    a2.requestFocus();
                }
                else if (aa3.isEmpty()) {
                    a3.setError("*required field");
                    a3.requestFocus();
                }
                else if (aa4.isEmpty()) {
                    a4.setError("*required field");
                    a4.requestFocus();
                }

                else if (aa5.isEmpty()) {
                    a5.setError("*required field");
                    a5.requestFocus();
                }
                else if (aa6.isEmpty()) {
                    a6.setError("*required field");
                    a6.requestFocus();
                }
                else if (aa7.isEmpty()) {
                    a7.setError("*required field");
                    a7.requestFocus();
                }
                else if (aa8.isEmpty()) {
                    a8.setError("*required field");
                    a8.requestFocus();
                }
                else if (aa9.isEmpty()) {
                    a9.setError("*required field");
                    a9.requestFocus();
                }
                else if (aa10.isEmpty()) {
                    a10.setError("*required field");
                    a10.requestFocus();
                }
                else if (aa11.isEmpty()) {
                    a11.setError("*required field");
                    a11.requestFocus();
                }
                else if (aa12.isEmpty()) {
                    a12.setError("*required field");
                    a12.requestFocus();
                }
                else if (aa13.isEmpty()) {
                    a13.setError("*required field");
                    a13.requestFocus();
                }

                else {

                    progressDialog = new ProgressDialog(QnA.this);
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage("Saving data...");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progressDialog.dismiss();

                            sendUserData();

                        }
                    },2500);



                }




            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void sendUserData() {

        //current_user_id = mAuth.getCurrentUser().getUid();
        FormRef = FirebaseDatabase.getInstance().getReference().child("FormData");
        tempRef = FirebaseDatabase.getInstance().getReference("tempData");


        String aa1 = a1.getText().toString();
        String aa2 = a2.getText().toString();
        String aa3 = a3.getText().toString();
        String aa4 = a4.getText().toString();
        String aa5 = a5.getText().toString();
        String aa6 = a6.getText().toString();
        String aa7 = a7.getText().toString();
        String aa8 = a8.getText().toString();
        String aa9 = a9.getText().toString();
        String aa10 = a10.getText().toString();
        String aa11 = a11.getText().toString();
        String aa12 = a12.getText().toString();
        String aa13 = a13.getText().toString();



        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calFordDate.getTime());

        Map reg = new HashMap();

        reg.put("serialNo", sl);
        reg.put("khanaNo", kn);
        reg.put("wordNo", wn);
        reg.put("unionCode", uc);
        reg.put("thanaCode", tc);
        reg.put("disCode", dc);
        reg.put("headName", hn);
        reg.put("fatherName", fn);
        reg.put("motherName", mn);
        reg.put("phone", pn);
        reg.put("village", v);
        reg.put("post", p);
        reg.put("union", u);
        reg.put("thana", t);
        reg.put("district", d);

        reg.put("q1", "১| আপনার পরিবার একক নাকি যৌথ?");
        reg.put("a1", aa1);
        reg.put("q2", "২। আপনার পরিবারের সদস্য সংখ্যা কতো?");
        reg.put("a2", aa2);
        reg.put("q3", "৩। আপনার পরিবারের আয়ের উৎস কি?");
        reg.put("a3", aa3);
        reg.put("q4", "৪। আপনার পরিবারের নলকূপ কয়টি?");
        reg.put("a4", aa4);
        reg.put("q5", "৫। নলকূপ কতো সালে বসানো হয়েছে?");
        reg.put("a5", aa5);
        reg.put("q6", "৬। নলকূপ বসানোর গভীরতা কতো?");
        reg.put("a6", aa6);
        reg.put("q7", "৭। আপনার নলকূপ আর্সেনিক মূক্ত কিনা আপনি জানেন?");
        reg.put("a7", aa7);
        reg.put("q8", "৮। আপনার নলকূপের পূর্বে আর্সেনিক পরীক্ষা হয়েছে কি?");
        reg.put("a8", aa8);
        reg.put("q9", "৯। আপনার নলকূপ বসানোর খরচ কতো?");
        reg.put("a9", aa9);
        reg.put("q10", "১০। আপনার পরিবারের স্বাস্থসম্মত টয়লেট আছে কি?");
        reg.put("a10", aa10);
        reg.put("q11", "১১। আপনার নলকূপ একক পরিবারের নাকি যৌথ?");
        reg.put("a11", aa11);
        reg.put("q12", "১২। আপনার পরিবারে প্রতিবন্ধী আছে কিনা-থাকলে কতোজন?");
        reg.put("a12", aa12);
        reg.put("q13", "১৩। কোনো প্রকার সরকারি ভাতা পান কি?");
        reg.put("a13", aa13);

        reg.put("addedTime",saveCurrentTime);
        reg.put("addedDate",saveCurrentDate);

        FormRef.push().updateChildren(reg);

        Map temp = new HashMap();


        temp.put("wordNo", wn);
        temp.put("unionCode", uc);
        temp.put("thanaCode", tc);
        temp.put("disCode", dc);
        temp.put("village", v);
        temp.put("post", p);
        temp.put("union", u);
        temp.put("thana", t);
        temp.put("district", d);

        tempRef.updateChildren(temp);


        progressDialog.dismiss();
        Toast.makeText(QnA.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(QnA.this, MainActivity.class));
        finish();


    }
}