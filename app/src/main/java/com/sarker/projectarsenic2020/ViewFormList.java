package com.sarker.projectarsenic2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewFormList extends AppCompatActivity {

    private FirebaseAuth mfirebaseAuth;
    private DatabaseReference FormRef;

    private RecyclerView sRecyclerView;
    private String current_user_id;
    private ImageView back;
    private ImageView empty;
    private EditText search;

    FirebaseRecyclerAdapter<FormInfo,SearchViewHolder> firebaseRecyclerAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_view_form_list);

//        mfirebaseAuth = FirebaseAuth.getInstance();
//        current_user_id = mfirebaseAuth.getCurrentUser().getUid();
        FormRef = FirebaseDatabase.getInstance().getReference("FormData");

        back = findViewById(R.id.back);
        empty = findViewById(R.id.emptya_data);
        search = findViewById(R.id.search_field);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        sRecyclerView = findViewById(R.id.form_list_rv);
        sRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerV = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //        linearLayoutManager.setReverseLayout(true);
        //        linearLayoutManager.setStackFromEnd(true);
        sRecyclerView.setLayoutManager(linearLayoutManagerV);

        search.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {

                if(search.getText().toString().length()>0){

                    String str = search.getText().toString();

                    StringBuilder capitalizedString = new StringBuilder();
                    String[] splited = str.trim().split("\\s+");

                    for (String string : splited) {
                        String s1 = string.substring(0, 1).toUpperCase();
                        String nameCapitalized = s1 + string.substring(1);

                        capitalizedString.append(nameCapitalized);
                        capitalizedString.append(" ");
                    }

                    searchStudent(capitalizedString.toString().trim());

                }
                else {
                    searchStudent(search.getText().toString());
                }


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        searchStudent(search.getText().toString());



    }

    private void searchStudent(String search) {


        FirebaseRecyclerOptions<FormInfo> options =
                new FirebaseRecyclerOptions.Builder<FormInfo>()
                        .setQuery(FormRef.orderByChild("headName").startAt(search).endAt(search+"\uf8ff"),FormInfo.class)
                        .build();



        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FormInfo, SearchViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SearchViewHolder holder, final int i, @NonNull FormInfo search) {

                final String key = firebaseRecyclerAdapter.getRef(i).getKey();

                holder.Name.setText(search.getHeadName());
                holder.Phone.setText(search.getPhone());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(), FormDetails.class);
                        intent.putExtra("form_id", key);
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.form_item, parent, false);

                return new SearchViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                // if there are no chat messages, show a view that invites the user to add a message
                empty.setVisibility(
                        firebaseRecyclerAdapter.getItemCount() == 0 ? View.VISIBLE : View.INVISIBLE
                );
            }

        };




        firebaseRecyclerAdapter.startListening();
        sRecyclerView.setAdapter(firebaseRecyclerAdapter);



    }

    //view holder
    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        public TextView Name,Phone;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.full_name);
            Phone = itemView.findViewById(R.id.phone);

        }
    }
}