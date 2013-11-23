package edu.cmu.alleghenywm;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	//Views in the activity
	private EditText userNameEText;
	private EditText passwordEText;
	
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		userNameEText = (EditText) findViewById(R.id.usernameEText);
		passwordEText = (EditText) findViewById(R.id.passwordEdTxt);
		loginBtn = (Button) findViewById(R.id.loginBtn);
		
		//config Views
		userNameEText.setCursorVisible(true);
		passwordEText.setCursorVisible(true);
		userNameEText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		
		//config Listerns
		loginBtn.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				String sUserName = userNameEText.getText().toString();
				String sPassword = passwordEText.getText().toString();
				// debug
				Log.d("LOGIN", "login clicked. Matching...");
				
				if(validateFields(sUserName, sPassword)) {
					Log.d("LOGIN", "FILEDS VALID");
					//
					if (checkCredentials()) {
						Toast.makeText(LoginActivity.this, R.string.login_success,
								Toast.LENGTH_SHORT).show();
						Log.d("LOGIN", "Successful");
						Intent optionMenu = new Intent(LoginActivity.this, OptionMenuActivity.class);
						startActivity(optionMenu); // ??? ADD BUNDLE!
					} else {
						Toast.makeText(LoginActivity.this, R.string.login_fail,
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(LoginActivity.this, R.string.insert_valid_data,
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	/**
	 * Checks the consistency of each of the text fields
	 * 
	 * @return true if no errors found, false otherwise
	 */
	private boolean validateFields(String username, String password) {
		if (username.length() == 0) {
			userNameEText.requestFocus();
			return false;
		} else if (password.length() == 0) {
			passwordEText.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * Check the credentials
	 */
	private boolean checkCredentials() {
		//HARD CODING
		return true;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}

}
