package com.example.desktopn.a5_8proveedorcontenidoscalllog;

import android.database.Cursor;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter adapter;
    ArrayList<Item> list;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Item>();
        recyclerView=findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CallLog-Backup");

        //Obtener el historial de llamadas
        Cursor mCursor=managedQuery(CallLog.Calls.CONTENT_URI,null,null,null,null);
        int name=mCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number=mCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int date=mCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration=mCursor.getColumnIndex(CallLog.Calls.DURATION);
        int type=mCursor.getColumnIndex(CallLog.Calls.TYPE);
        while(mCursor.moveToNext()){
            String nametxt=mCursor.getString(name);
            String numbertxt=mCursor.getString(number);
            String datetxt=mCursor.getString(date);
            String durationtxt=mCursor.getString(duration);
            String typetxt=mCursor.getString(type);
            switch (Integer.parseInt(typetxt)){
                case CallLog.Calls.OUTGOING_TYPE:
                    typetxt="Saliente";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    typetxt="Entrante";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    typetxt="Perdida";
                    break;
                case CallLog.Calls.REJECTED_TYPE:
                    typetxt="Rechazada";
                    break;
            }

            Date fecha = new Date(Long.parseLong(datetxt));
            list.add(new Item(nametxt,numbertxt,fecha.toString(),durationtxt+" s",typetxt));
        }

        adapter=new ItemAdapter(list);
        recyclerView.setAdapter(adapter);

    }

    public void respaldar(View view) {
        myRef.setValue(list);
        Toast.makeText(this,"Respaldando...",Toast.LENGTH_LONG).show();
    }

}