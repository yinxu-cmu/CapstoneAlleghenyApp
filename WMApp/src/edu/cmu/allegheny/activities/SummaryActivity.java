/**
 * 
 */
package edu.cmu.allegheny.activities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//import com.itextpdf.text.DocumentException;

import edu.cmu.allegheny.R;
import edu.cmu.allegheny.util.PDFGenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
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
 * @author yinxu
 * 
 */
public class SummaryActivity extends Activity {

	private TextView text;
	private String storeId;
	private Button signBtn;
	private Button emailBtn;
	private Button mainBtn;

	public static final int SIGNATURE_ACTIVITY = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// File file = new File(this.getCacheDir(), "Summary_Report");
		File file = new File("/mnt/sdcard/download", "Summary_Report.pdf");
		String path = file.getAbsolutePath();

		super.onCreate(savedInstanceState);
		// mContext = this;
		setContentView(R.layout.summary_activity);

		text = (TextView) findViewById(R.id.summary_activity_text);
		signBtn = (Button) findViewById(R.id.summary_btn_sign);
		emailBtn = (Button) findViewById(R.id.summary_btn_email);
		mainBtn = (Button) findViewById(R.id.summary_btn_main);

		text.setText(path);

		// get the storeID
		Bundle bundle1 = this.getIntent().getExtras();
		storeId = bundle1.getString("storeId");

		try {
			PDFGenerator report = new PDFGenerator(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.d("REPORT", "dead");
			e.printStackTrace();
		}

		setListeners();
	}

	/**
	 * set listeners
	 */
	public void setListeners() {
		signBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SummaryActivity.this,
						CaptureSignatureActivity.class);
				startActivityForResult(intent, SIGNATURE_ACTIVITY);
			}
		});

		emailBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent emailIntent = new Intent(SummaryActivity.this,
				// MailActivity.class);
				// startActivity(emailIntent);

				// modify start here
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

								Intent formIntent = new Intent(
										CheckPumpActivity.this,
										FormActivity.class);
								Bundle bundle = new Bundle();
								bundle.putString("barcode_result", sDeviceId);
								formIntent.putExtras(bundle);
								startActivity(formIntent);
							}
						});
				builder.setNegativeButton("Cancel", null);
				builder.show();
				// end =here
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
	 * after sign do this
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SIGNATURE_ACTIVITY:
			if (resultCode == RESULT_OK) {

				Bundle bundle = data.getExtras();
				String status = bundle.getString("status");
				if (status.equalsIgnoreCase("done")) {
					Toast toast = Toast
							.makeText(this, "Signature capture successful!",
									Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.TOP, 105, 50);
					toast.show();
				}
			}
			break;
		}

	}
}
