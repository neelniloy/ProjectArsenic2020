package com.sarker.projectarsenic2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class NewForm extends AppCompatActivity {

    private Button Next;
    private ImageView back;
    private TextInputEditText slNo,khanaNo, wordNo,unionCode, thanaCode,disCode,headName,FatherName,MotherName,phone,village,post,union,thana,district;
    private TextInputLayout el1,el2,el3,el4,el5,el6,el7,el8,el9,el10,el11,el12,el13,el14,el15;
    private DatabaseReference FormRef,tempRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_new_form);

        Next = findViewById(R.id.btn_next);
        back = findViewById(R.id.back);

        slNo = findViewById(R.id.et_serial_no);
        khanaNo = findViewById(R.id.et_khana_no);
        wordNo = findViewById(R.id.et_word_no);
        unionCode = findViewById(R.id.et_union_code);
        thanaCode = findViewById(R.id.et_thana_code);
        disCode = findViewById(R.id.et_dis_code);
        headName = findViewById(R.id.et_head_name);
        FatherName = findViewById(R.id.et_father_name);
        MotherName = findViewById(R.id.et_mother_name);
        phone = findViewById(R.id.et_phone);
        village = findViewById(R.id.et_village);
        post = findViewById(R.id.et_post);
        union = findViewById(R.id.et_union);
        thana = findViewById(R.id.et_thana);
        district = findViewById(R.id.et_district);

        el1 = findViewById(R.id.el1);
        el2 = findViewById(R.id.el2);
        el3 = findViewById(R.id.el3);
        el4 = findViewById(R.id.el4);
        el5 = findViewById(R.id.el5);
        el6 = findViewById(R.id.el6);
        el7 = findViewById(R.id.el7);
        el8 = findViewById(R.id.el8);
        el9 = findViewById(R.id.el9);
        el10 = findViewById(R.id.el10);
        el11 = findViewById(R.id.el11);
        el12 = findViewById(R.id.el12);
        el13 = findViewById(R.id.el13);
        el14 = findViewById(R.id.el14);
        el15 = findViewById(R.id.el15);


        FormRef = FirebaseDatabase.getInstance().getReference("FormData");
        tempRef = FirebaseDatabase.getInstance().getReference("tempData");

        FormRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    long count = snapshot.getChildrenCount()+1;

                    slNo.setText(""+count);
                    khanaNo.setText(""+count);


                }else {
                    slNo.setText("1");
                    khanaNo.setText("1");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String wn = Objects.requireNonNull(snapshot.child("wordNo").getValue()).toString();
                    String uc = Objects.requireNonNull(snapshot.child("unionCode").getValue()).toString();
                    String tc = Objects.requireNonNull(snapshot.child("thanaCode").getValue()).toString();
                    String dc = Objects.requireNonNull(snapshot.child("disCode").getValue()).toString();
                    String v = Objects.requireNonNull(snapshot.child("village").getValue()).toString();
                    String p = Objects.requireNonNull(snapshot.child("post").getValue()).toString();
                    String u = Objects.requireNonNull(snapshot.child("union").getValue()).toString();
                    String t = Objects.requireNonNull(snapshot.child("thana").getValue()).toString();
                    String d = Objects.requireNonNull(snapshot.child("district").getValue()).toString();

                    wordNo.setText(wn);
                    unionCode.setText(uc);
                    thanaCode.setText(tc);
                    disCode.setText(dc);
                    village.setText(v);
                    post.setText(p);
                    union.setText(u);
                    thana.setText(t);
                    district.setText(d);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sl = slNo.getText().toString();
                String kn = khanaNo.getText().toString();
                String wn = wordNo.getText().toString();
                String uc = unionCode.getText().toString();
                String tc = thanaCode.getText().toString();
                String dc = disCode.getText().toString();
                String hn = headName.getText().toString();
                String fn = FatherName.getText().toString();
                String mn = MotherName.getText().toString();
                String pn = phone.getText().toString();
                String v = village.getText().toString();
                String p = post.getText().toString();
                String u = union.getText().toString();
                String t = thana.getText().toString();
                String d = district.getText().toString();



                if (sl.isEmpty()) {
                    el1.setError("*required field");
                    slNo.requestFocus();

                }
                else if (kn.isEmpty()) {
                    el2.setError("*required field");
                    khanaNo.requestFocus();
                }
                else if (wn.isEmpty()) {
                    el3.setError("*required field");
                    wordNo.requestFocus();
                }
                else if (uc.isEmpty()) {
                    el4.setError("*required field");
                    unionCode.requestFocus();
                }

                else if (tc.isEmpty()) {
                    el5.setError("*required field");
                    thanaCode.requestFocus();
                }
                else if (dc.isEmpty()) {
                    el6.setError("*required field");
                    disCode.requestFocus();
                }
                else if (hn.isEmpty()) {
                    el7.setError("*required field");
                    headName.requestFocus();
                }
                else if (fn.isEmpty()) {
                    el8.setError("*required field");
                    FatherName.requestFocus();
                }
                else if (mn.isEmpty()) {
                    el9.setError("*required field");
                    MotherName.requestFocus();
                }
                else if (pn.isEmpty()) {
                    el10.setError("*required field");
                    phone.requestFocus();
                }
                else if (v.isEmpty()) {
                    el11.setError("*required field");
                    village.requestFocus();
                }
                else if (p.isEmpty()) {
                    el12.setError("*required field");
                    post.requestFocus();
                }
                else if (u.isEmpty()) {
                    el13.setError("*required field");
                    union.requestFocus();
                }
                else if (t.isEmpty()) {
                    el14.setError("*required field");
                    thana.requestFocus();
                }
                else if (d.isEmpty()) {
                    el15.setError("*required field");
                    district.requestFocus();
                }else {

                    Intent n = new Intent(NewForm.this,QnA.class);

                    n.putExtra("sl",sl);
                    n.putExtra("kn",kn);
                    n.putExtra("wn",wn);
                    n.putExtra("uc",uc);
                    n.putExtra("tc",tc);
                    n.putExtra("dc",dc);
                    n.putExtra("hn",hn);
                    n.putExtra("fn",fn);
                    n.putExtra("mn",mn);
                    n.putExtra("pn",pn);
                    n.putExtra("v",v);
                    n.putExtra("p",p);
                    n.putExtra("u",u);
                    n.putExtra("t",t);
                    n.putExtra("d",d);

                    startActivity(n);

                }




            }
        });
    }
}