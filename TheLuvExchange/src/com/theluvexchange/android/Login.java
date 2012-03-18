package com.theluvexchange.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Tristan Maschke
 * 
 * This is the Login activity
 */
public class Login extends Activity {
	private Activity activity;
	SharedPreferences savedUser;
	private EditText userNameField;
	private EditText passwordField;
	private CheckBox rememberMe;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		savedUser = getPreferences(MODE_PRIVATE);
		boolean ping = WebService.ping();
		if (ping && savedUser.getBoolean("remember", false)) {
			User user = new User(savedUser);
			TheLuvExchange application = (TheLuvExchange)getApplication();
			application.setUser(user);
			startActivity(new Intent(activity, MainMenu.class));
		}

		setContentView(R.layout.main);
		
		userNameField = (EditText)findViewById(R.id.editTextUsername);
		passwordField = (EditText)findViewById(R.id.editTextPassword);
		rememberMe = (CheckBox)findViewById(R.id.rememberCheckbox);

		Button login = (Button)findViewById(R.id.btnLogin);
		login.setOnClickListener(onLogin);

		TextView register = (TextView)findViewById(R.id.link_to_register);
		register.setOnClickListener(onRegister);
		
		if (!ping) {
			Toast.makeText(activity, "Problem connecting to login service.",
					Toast.LENGTH_LONG).show();
		}
	}

	private View.OnClickListener onLogin = new View.OnClickListener() {
		public void onClick(View v) {
			String userName = userNameField.getText().toString();
			Object result = WebService.login(userName, passwordField.getText().toString());
			Editor editor = savedUser.edit();
			if (result instanceof User) {
				User user = (User)result;
				user.setUserName(userName);

				TheLuvExchange application = (TheLuvExchange)getApplication();
				application.setUser(user);

				if (rememberMe.isChecked()) {
					user.save(editor);
				}

				startActivity(new Intent(activity, MainMenu.class));
			} else {
				Toast.makeText(activity, (String)result, Toast.LENGTH_LONG).show();
				editor.putBoolean("remember", false);
				editor.commit();
			}
		}
	};

	private View.OnClickListener onRegister = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://theluvexchange.com/accounts/register/"));
			startActivity(intent);
		}
	};
}
