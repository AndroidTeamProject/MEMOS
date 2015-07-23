package com.cop4656.zeronul.memos;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by Zero Nul on 7/19/2015.
 */
public class AddItemsFragment extends Fragment implements OnItemSelectedListener
{
    private static boolean loggedIn = false;
    private static boolean manager = false;
    private static String userID = "";

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private Spinner itemSpinner;
    private LinearLayout newManager;
    private LinearLayout newTechnologist;
    private LinearLayout newInstrument;
    private LinearLayout newProcedure;
    private Button addButton;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddItemsFragment newInstance(int sectionNumber)
    {
        AddItemsFragment fragment = new AddItemsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AddItemsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_additems, container, false);

        userID = getArguments().getString( "userID", "");
        manager = getArguments().getBoolean( "manager", false);
        loggedIn = getArguments().getBoolean( "loggedIn", false);

        itemSpinner = (Spinner)rootView.findViewById( R.id.itemSpinner );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getActivity(), R.array.items_to_add,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.notifyDataSetChanged();
        itemSpinner.setAdapter(adapter);
        itemSpinner.setOnItemSelectedListener(this);

        newManager = (LinearLayout)rootView.findViewById( R.id.newManager);
        newTechnologist =(LinearLayout)rootView.findViewById( R.id.newTechnologist);
        newInstrument = (LinearLayout)rootView.findViewById( R.id.newInstrument);
        newProcedure = (LinearLayout)rootView.findViewById( R.id.newProcedure);
        addButton = (Button)rootView.findViewById( R.id.addButton );

        newManager.setVisibility(View.GONE);
        newTechnologist.setVisibility(View.GONE);
        newInstrument.setVisibility(View.GONE);
        newProcedure.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        String spinnerChoice = parent.getItemAtPosition(pos).toString();

        Context context = getActivity().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast debugToast = Toast.makeText( context, spinnerChoice, duration);
        debugToast.show();

        switch ( spinnerChoice )
        {
            case "Manager":
                newManager.setVisibility(View.VISIBLE);
                newTechnologist.setVisibility(View.GONE);
                newInstrument.setVisibility(View.GONE);
                newProcedure.setVisibility(View.GONE);
                break;
            case "Technologist":
                newManager.setVisibility(View.GONE);
                newTechnologist.setVisibility(View.VISIBLE);
                newInstrument.setVisibility(View.GONE);
                newProcedure.setVisibility(View.GONE);
                break;
            case "Instrument":
                newManager.setVisibility(View.GONE);
                newTechnologist.setVisibility(View.GONE);
                newInstrument.setVisibility(View.VISIBLE);
                newProcedure.setVisibility(View.GONE);
                break;
            case "Procedure":
                newManager.setVisibility(View.GONE);
                newTechnologist.setVisibility(View.GONE);
                newInstrument.setVisibility(View.GONE);
                newProcedure.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }

}
