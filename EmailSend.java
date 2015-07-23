package assignment4.android.com.assignment4_works;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import org.apache.http.protocol.HTTP;

// **** Send Log In Email Activity ****//
// Author: Kara Beason
// (sorry about the strange naming convention.)
// In my version I set an onlick for a button in my
//      main layout, which then launches this activity (using an intent).
//      This activity allows user to enter a numeric manager ID.  It then (will eventually)
//      get the manager associated with that ID, and that manager's email.  It then allows
//      the user to click a button to email said manager with the log from the report
//      screen attached.  I still have to figure out the attachment part.  This is a very preliminary
//      version.

public class EmailSend extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);


    }
    public void sendLog(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        // at this point we will use the value from the managerID numeric text field
        //  use getManagerByID to return Manager.
        // and then set Manager.getEmail() as the recipient of the email.
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        // need to use the already queried log rows (report) here as attachment to email
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        // You can also attach multiple items by passing an ArrayList of Uris
        startActivity(Intent.createChooser(emailIntent , "Send email..."));
        // this pulls up email accounts set up by user on phone, then pulls up email
        // with pre-filled recipient, subject, body, and attachment(s).
    }

}
