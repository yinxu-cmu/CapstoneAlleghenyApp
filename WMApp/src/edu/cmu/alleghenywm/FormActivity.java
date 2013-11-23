/**
 * 
 */
package edu.cmu.alleghenywm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author yinxu
 *
 */
public class FormActivity extends Activity{
	
	private TextView barcode;
	private EditText storeId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_activity);
		//barcode = (TextView) findViewById(R.id.tv_barcode_result);
		storeId = (EditText) findViewById(R.id.form_storeId_input);
		
		Bundle bundle1 = this.getIntent().getExtras();
		storeId.setText(bundle1.getString("barcode_result"));
	}
	
}
