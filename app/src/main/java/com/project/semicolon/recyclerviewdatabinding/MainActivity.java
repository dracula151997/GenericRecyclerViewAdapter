package com.project.semicolon.recyclerviewdatabinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ContactModel> contacts = new ArrayList<>();
        contacts.add(new ContactModel("Contact 1", "Phone 1"));
        contacts.add(new ContactModel("Contact 2", "Phone 2"));
        contacts.add(new ContactModel("Contact 3", "Phone 3"));
        contacts.add(new ContactModel("Contact 4", "Phone 4"));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        GenericAdapter<ContactModel> contactAdapter = new GenericAdapter<>(R.layout.list_item_contact);
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.setOnListItemViewClickListener(new OnListItemViewClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(MainActivity.this, "Position " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        contactAdapter.addItems(contacts);
    }
}
