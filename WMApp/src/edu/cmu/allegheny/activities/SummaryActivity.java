/**
 * 
 */
package edu.cmu.allegheny.activities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.DocumentException;

//import com.itextpdf.text.DocumentException;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.data.FormHandler;
import edu.cmu.allegheny.data.FormLine;
import edu.cmu.allegheny.data.Store;
import edu.cmu.allegheny.data.StoreHandler;
import edu.cmu.allegheny.util.PDFGenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * When all pump checking is done, users will be directed to SummaryActivity where
 * they can: 
 * 1. review the measurement result;
 * 2. sign the summary report;
 * 3. email the report to specified addresses.
 * 
 * Note: 
 * 1. the Summary PDF report is generated when users entering into SummaryActivity.
 * 2. The default path for the report is hardcoding: /mnt/sdcard/download/Summary_Report.pdf
 * 
 * @author yinxu
 * 
 */
public class SummaryActivity extends Activity {

	private TextView text;
	private String storeId;
	private Button signBtn;
	private Button emailBtn;
	private Button mainBtn;
	private Button previewBtn;

	public static final int SIGNATURE_ACTIVITY = 1;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_activity);

		signBtn = (Button) findViewById(R.id.summary_btn_sign);
		emailBtn = (Button) findViewById(R.id.summary_btn_email);
		mainBtn = (Button) findViewById(R.id.summary_btn_main);
		previewBtn = (Button) findViewById(R.id.summary_btn_viewreport);

		// get the storeID
		Bundle bundle1 = this.getIntent().getExtras();
		storeId = bundle1.getString("storeId");
		

		try {
			PDFGenerator report = new PDFGenerator(storeId, this);

		} catch (Exception e) {
			
		}

		setListeners();
	}

	/**
	 * set listeners for buttons.
	 */
	public void setListeners() {
		previewBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// view the report
				Intent intent = new Intent(Intent.ACTION_VIEW,
				        Uri.parse("/mnt/sdcard/download/Summary_Report.pdf"));
				intent.setType("application/pdf");
				PackageManager pm = getPackageManager();
				List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
				if (activities.size() > 0) {
				    startActivity(intent);
				} else {
					// no application available
					Toast toast = Toast.makeText(getApplicationContext(), "Install a PDF reader first!",
									Toast.LENGTH_SHORT);
				}
			}
		});
		
		signBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SummaryActivity.this,
						CaptureSignatureActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("storeId", storeId);
				intent.putExtras(bundle);
				startActivityForResult(intent, SIGNATURE_ACTIVITY);
				
			}
		});

		emailBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Popup email input dialoge
				LayoutInflater inflater = SummaryActivity.this.getLayoutInflater();
				View layout = inflater.inflate(R.layout.manual_input_dialog,
						null);

				AlertDialog.Builder builder = new Builder(
						SummaryActivity.this);
				builder.setTitle("Input Email Address");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				// builder.setView(new EditText(mContext));
				builder.setView(layout);

				final EditText emailAddr = (EditText) layout
						.findViewById(R.id.manual_input_deviceId);

				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// get the EditText value
								String emailAddrStr = emailAddr.getText()
										.toString();
								
								sendEmail(emailAddrStr);
								
							}
						});
				builder.setNegativeButton("Cancel", null);
				builder.show();
			}
		});

		mainBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mainIntent = new Intent(SummaryActivity.this,
						OptionMenuActivity.class);
				startActivity(mainIntent);
			}
		});
	}

	/**
	 * called after signing the signature.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SIGNATURE_ACTIVITY:
			if (resultCode == RESULT_OK) {

				Bundle bundle = data.getExtras();
				String status = bundle.getString("status");
				if (status.equalsIgnoreCase("done")) {
					Toast toast = Toast.makeText(this, "Signature capture successful!",
									Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 105, 50);
					toast.show();
				}
			}
			break;
		}

	}
	
	/**
	 * send out an email with the summary report attached.
	 */
	public void sendEmail(String addr) {
		try {
			//clear the form table first
			FormHandler fh = new FormHandler(getApplicationContext());
			fh.onUpgrade(fh.getWritableDatabase(), fh.getVersion(), fh.getVersion()+1);
			//
            String email = addr;
            String subject = "Summary Report";
            String message = "Weights and Measures Report " + new Date();
            
            Uri URI = Uri.parse("file://" + "mnt/sdcard/download/Summary_Report.pdf");

            final Intent emailIntent = new Intent(
                          android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                          new String[] { email });
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                          subject);
            if (URI != null) {
                   emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
            }
            emailIntent
                          .putExtra(android.content.Intent.EXTRA_TEXT, message);
            this.startActivity(Intent.createChooser(emailIntent,
                          "Sending email..."));

      } catch (Throwable t) {
            Toast.makeText(this,
                          "Request failed try again: " + t.toString(),
                          Toast.LENGTH_LONG).show();
      }
	}
}
