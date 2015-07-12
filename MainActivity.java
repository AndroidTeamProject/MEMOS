package com.android.databasebuilder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView tv;
    private TextView tv1;
    private int managerID = 0;
    private int techID = 0;

    Button b;
    private DatabaseAdapter myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button)findViewById(R.id.button);
        tv = (TextView)findViewById(R.id.tv);
        tv1 = (TextView)findViewById(R.id.tv1);

        System.out.println(myDB.CREATE_MANAGER_TABLE);
        //Calendar c = Calendar.getInstance();

        openDB();
    }

    public void displayManagerData(View view)
    {
        Cursor c = myDB.getAllRowsForManager();
        Cursor t = myDB.getAllRowsForTech();
        String message = "";
        String message1 = "";

        if(c.moveToFirst())
        {
            do
            {
                int manager_id = c.getInt(myDB.COL_MANAGER_ID);
                String first = c.getString(myDB.COL_MANAGER_FIRST);
                String last = c.getString(myDB.COL_MANAGER_LAST);
                String email = c.getString(myDB.COL_MANAGER_EMAIL);
                int dept = c.getInt(myDB.COL_MANAGER_DEPT);

                message += manager_id + " " + first + " "
                        +  last + " " + " " + email + " " +
                           dept + "\n";

            }while (c.moveToNext());
            tv.setText(message);
        }
        else
        {
            tv.setText("NO DATA");
        }

        if(t.moveToFirst())
        {
            do
            {
                int tech_id = t.getInt(myDB.COL_TECH_ID);
                String tech_first = t.getString(myDB.COL_TECH_FIRST);
                String tech_last = t.getString(myDB.COL_TECH_LAST);
                String tech_email = t.getString(myDB.COL_TECH_EMAIL);

                message1 += tech_id + " " + tech_first + " "
                        +  tech_last + " " + " " + tech_email + " " +
                        "\n";

            }while (t.moveToNext());

            tv1.setText(message1);
        }
        else
        {
            tv1.setText("NO DATA");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }


    private void openDB() {
        myDB = new DatabaseAdapter(this);
        myDB.open();
    }
    private void closeDB() {
        myDB.close();
    }

    public void insertData(View view)
    {
        Manager ma = new Manager(managerID, "duly", "Bonheur","dulybon1@hotmail.com",909);
        Technologist te = new Technologist(techID, "duly", "Bonheur","dulybon1@hotmail.com");

        myDB.insertRowTech(te.getEmployeeID(), te.getFirstName(), te.getLastName(), te.getEmail());
        myDB.insertRowManager(ma.getEmployeeID(), ma.getFirstName(), ma.getLastName(), ma.getEmail(), ma.getDepartmentNumber());

        managerID +=1;
        techID += 1;
    }

    public void clearData(View view)
    {
        //myDB.clearManagerTable();
        myDB.clearTable(myDB.MANAGER_TABLE, myDB.ALL_MANAGER_KEYS);
        myDB.clearTechnologistTable();

    }
}
