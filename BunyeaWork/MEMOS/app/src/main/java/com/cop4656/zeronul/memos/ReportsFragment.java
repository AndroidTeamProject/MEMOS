package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Zero Nul on 7/21/2015.
 */
public class ReportsFragment extends Fragment implements View.OnClickListener
{
    RadioGroup radioGroup;
    RadioButton today;
    RadioButton monthToDate;
    RadioButton customDateRange;
    EditText startDate;
    EditText endDate;
    Button selectDate;
    Button sendReport;

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

        radioGroup = (RadioGroup)rootView.findViewById( R.id.radioGroup );
        today = (RadioButton)rootView.findViewById( R.id.today );
        monthToDate = (RadioButton)rootView.findViewById( R.id.monthToDate );
        customDateRange = (RadioButton)rootView.findViewById( R.id.customDateRange );
        startDate = (EditText)rootView.findViewById( R.id.startDate );
        endDate = (EditText)rootView.findViewById( R.id.endDate );
        selectDate = (Button)rootView.findViewById( R.id.selectDate );
        sendReport = (Button)rootView.findViewById( R.id.sendReport );

        if( !( ( MainActivity )getActivity()).isLoggedIn() )
        {
            disableFields();
            CharSequence textMessage = ( getActivity().getString( R.string.login_error ) );
            Context context = getActivity().getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText( context, textMessage, duration );
            toast.show();
        }

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

    @Override
    public void onClick(View v)
    {
        CharSequence textMessage = "";
        Context context = getActivity().getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        switch ( v.getId() )
        {
            case R.id.selectDate:
                textMessage = "REPORT GENERATED";
                Toast toast = Toast.makeText( context, textMessage, duration );
                toast.show();
                break;
            case R.id.sendReport:
                textMessage = "EMAIL SENT";
                toast = Toast.makeText( context, textMessage, duration );
                toast.show();
                break;
        }
    }
}
