package com.example.songmenuandroid;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	EditText usernameET = (EditText)findViewById(R.id.editText1);
	EditText passwordET = (EditText)findViewById(R.id.editText2);
	String URL = "http://localhost/";
	
	private void addListenerOnButton()
	{
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Implement the login function here
				// Get the username, password
				String username = usernameET.getText().toString();
				String password = passwordET.getText().toString();
				HttpClient httpClient = new DefaultHttpClient();
				try {
					HttpResponse response = httpClient.execute(new HttpGet(URL));
					Log.d("Http Response:", response.toString());
					HttpEntity entity = response.getEntity();
					
					if (entity != null) {
						InputStream instream = entity.getContent();
//						String result = convertStreamToString(instream);
						instream.close();
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}

}
