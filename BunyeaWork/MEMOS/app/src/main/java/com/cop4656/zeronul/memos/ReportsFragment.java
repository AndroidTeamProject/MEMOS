package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by Zero Nul (Jason D. Bunyea) on 7/21/2015. Reports are generated from log table of
 * database. They should then be displayed in a Listview and sotred in a .txt file.
 */
public class ReportsFragment extends Fragment implements View.OnClickListener
{
    //developer variable to define number of time to log generic data into ArrayList
    private static final int NUMBER_OF_ROUNDS = 10;

    //filename of log
    private static final String FILENAME = "log.txt";

    //database
    private DatabaseAdapter myDB;

    //widget creation
    RadioGroup radioGroup;
    RadioButton today;
    RadioButton monthToDate;
    RadioButton customDateRange;
    EditText startDate;
    EditText endDate;
    Button selectDate;
    Button sendReport;
    ListView reportField;

    //ArrayList to hold database data for reporting
    ArrayList<String> testReports = new ArrayList<String>();

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ReportsFragment newInstance(int sectionNumber)
    {
        ReportsFragment fragment = new ReportsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ReportsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_reports, container, false);

        //assign widgets
        radioGroup = (RadioGroup)rootView.findViewById( R.id.radioGroup );
        today = (RadioButton)rootView.findViewById( R.id.today );
        monthToDate = (RadioButton)rootView.findViewById( R.id.monthToDate );
        customDateRange = (RadioButton)rootView.findViewById( R.id.customDateRange );
        startDate = (EditText)rootView.findViewById( R.id.startDate );
        endDate = (EditText)rootView.findViewById( R.id.endDate );
        selectDate = (Button)rootView.findViewById( R.id.selectDate );
        sendReport = (Button)rootView.findViewById( R.id.sendReport );
        reportField = (ListView)rootView.findViewById( R.id.reportField );

        //load generic data into ArrayList
        loadTestList( 10 );

        //verify a user is logged in
        if( !( ( MainActivity )getActivity()).isLoggedIn() )
        {
            disableFields();
            CharSequence textMessage = ( getActivity().getString( R.string.login_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

        //open the database
        openDB();

        //set listeners for buttons
        rootView.findViewById( R.id.selectDate ).setOnClickListener(this);
        rootView.findViewById( R.id.sendReport ).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    //method to disable fields in the event a user is not logged in
    private void disableFields()
    {
        today.setEnabled(false);
        monthToDate.setEnabled(false);
        customDateRange.setEnabled(false);
        startDate.setEnabled(false);
        endDate.setEnabled(false);
        selectDate.setEnabled(false);
        sendReport.setEnabled(false);
    }

    //method to handle button clicks
    @Override
    public void onClick(View v)
    {
        //context, duration, and string for toast alerts
        CharSequence textMessage = "";
        Context context = getActivity().getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        //this ****SHOULD**** create the file to contain the report data
        //unable to verify at this point
        String filePath = Environment.getDataDirectory().toString();
        File appDirectory = new File( context.getFilesDir(), "MEMOS" );
        if ( ! appDirectory.exists() )
        {
            appDirectory.mkdirs();
        }

        File logPath = new File( appDirectory, "log.txt" );

        if ( !logPath.exists() )
        {
            logPath.mkdirs();
        }

        //determine which button was pressed
        switch ( v.getId() )
        {
            //if select date button was pressed ...
            // ************* NEED METHOD TO QUERY DATABASE BASED ON DATES!!!******************
            // ************* NEED VARIABLE TO HOLD DATE SELECTION!!!**************************
            case R.id.selectDate:

                /*
                FOR DEBUGGING

                Date d = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String date = df.format(d);
                populateArrayListWithLogsFromInterval("2014-02-01",date);

                */
                //populateArrayListWithLogsFromToday();

                populateArrayListWithAllLogs();

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        context,
                        android.R.layout.simple_list_item_1,
                        testReports );
                arrayAdapter.notifyDataSetChanged();
                reportField.setAdapter(arrayAdapter);

                try
                {
                    //write to file ... ?
                    BufferedWriter writer = new BufferedWriter( new FileWriter( logPath ) ) ;
//                    fos.write(NUMBER_TOKENS.getBytes());
                    String item;
                    for ( String word : testReports )
                    {
                        int i = 0;
                        switch ( i )
                        {
                            case 0:
                                item = "Log ID: ";
                                ++i;
                                break;
                            case 1:
                                item = "Instrument ID: ";
                                ++i;
                                break;
                            case 2:
                                item  = "Employee ID: ";
                                ++i;
                                break;
                            case 3:
                                item = "Date: ";
                                ++i;
                                break;
                            case 4:
                                item = "Time: ";
                                ++i;
                                break;
                            case 5:
                                item = "Shift: ";
                                ++i;
                                break;
                            case 6:
                                item = "Comment: ";
                                i = 0;
                                break;
                            default:
                                item = "ERROR ERROR ERROR";
                        }

                        writer.write( item );
                        writer.write( word );
                        writer.newLine();
                    }

                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                textMessage = "REPORT GENERATED";
                Toast toast = Toast.makeText( context, textMessage, duration );
                toast.show();
                break;
            //if send report button is pressed
            //this activates the option to send message to email
            //this uses configured email account on android device
            //***************
            //email works.
            //***************
            //***************
            // it shows attachment. email does not have attachment upon receipt.
            //assume that this has something to do with file not being log.txt not being created
            //correctly
            case R.id.sendReport:
                // **** Send Log In Email Activity ****//
                // Author: Kara Beason
                // (sorry about the strange naming convention.)
                // In my version I set an onlick for a button in my
                //      main layout, which then launches this activity (using an intent).
                //      This activity allows user to enter a numeric manager ID.  It then
                //      (will eventually) get the manager associated with that ID, and that
                //      manager's email.  It then allows the user to click a button to email
                //      said manager with the log from the report screen attached.  I still have
                //      to figure out the attachment part.  This is a very preliminary version.
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
                // at this point we will use the value from the managerID numeric text field
                //  use getManagerByID to return Manager.
                // and then set Manager.getEmail() as the recipient of the email.
                // recipients
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jasonbunyea@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Emailing report log");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Attachment of report log");
                // need to use the already queried log rows (report) here as attachment to email
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(logPath.toString()));
                // You can also attach multiple items by passing an ArrayList of Uris
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                // this pulls up email accounts set up by user on phone, then pulls up email
                // with pre-filled recipient, subject, body, and attachment(s).
                //**************** END ******************************//
                textMessage = "EMAIL SENT";
                toast = Toast.makeText( context, textMessage, duration );
                toast.show();
                break;
        }
    }


    private void populateArrayListWithAllLogs()
    {
        Cursor cursor = myDB.getAllRowsForLog();

        if(cursor.moveToFirst())
        {
            do {

                //make a string for each log
                String logString = cursor.getString(myDB.COL_LOG_DATE) + " @ " +
                        cursor.getString(myDB.COL_LOG_TIME) + " " +
                        cursor.getString(myDB.COL_LOG_SHIFT) + " ON " +
                        cursor.getString(myDB.COL_LOG_INST_ID) + " " +
                        cursor.getString(myDB.COL_LOG_PROCEDURE_ID) + " BY " +
                        cursor.getString(myDB.COL_LOG_TECH_ID);

                //add it to test reports
                testReports.add(logString);

            }while (cursor.moveToNext());
        }
    }

    //make sure the dates have following format yyyy-MM-dd
    private void populateArrayListWithLogsFromInterval(String start, String end)
    {
        Cursor cursor = myDB.getLogsForDateInterval(start, end);
        if(cursor.moveToFirst())
        {
            do {

                //make a string for each log
                String logString = cursor.getString(myDB.COL_LOG_DATE) + " @ " +
                        cursor.getString(myDB.COL_LOG_TIME) + " " +
                        cursor.getString(myDB.COL_LOG_SHIFT) + " ON " +
                        cursor.getString(myDB.COL_LOG_INST_ID) + " " +
                        cursor.getString(myDB.COL_LOG_PROCEDURE_ID) + " BY " +
                        cursor.getString(myDB.COL_LOG_TECH_ID);

                //add it to test reports
                testReports.add(logString);

            }while (cursor.moveToNext());
        }

    }

    private void populateArrayListWithLogsFromToday()
    {
        Cursor cursor = myDB.getLogsForToday();
        if(cursor.moveToFirst())
        {
            do {

                //make a string for each log
                String logString = cursor.getString(myDB.COL_LOG_DATE) + " @ " +
                        cursor.getString(myDB.COL_LOG_TIME) + " " +
                        cursor.getString(myDB.COL_LOG_SHIFT) + " ON " +
                        cursor.getString(myDB.COL_LOG_INST_ID) + " " +
                        cursor.getString(myDB.COL_LOG_PROCEDURE_ID) + " BY " +
                        cursor.getString(myDB.COL_LOG_TECH_ID);

                //add it to test reports
                testReports.add(logString);

            }while (cursor.moveToNext());
        }

    }

    //method to load generic data into ArrayList
    private void loadTestList( int numberOfRounds )
    {
        for ( int i = 0; i < numberOfRounds; ++i )
        {
            /*
            testReports.add("Lorem");
            testReports.add("Ipsum");
            testReports.add("Neque");
            testReports.add("Porro");
            testReports.add("Quisquam");
            testReports.add("Est");
            testReports.add("Qui");
            testReports.add("Delorem");
            testReports.add("Ipsum");
            testReports.add("Quia");
            testReports.add("Dolor");
            testReports.add("Sit");
            testReports.add("Amet");
            testReports.add("Consectetur");
            testReports.add("Adipisci");
            testReports.add("Velit");
            */
        }
    }

    private void openDB()
    {
        myDB = new DatabaseAdapter(this.getActivity());
        myDB.open();
    }

    private void closeDB() {
        myDB.close();
    }
}
