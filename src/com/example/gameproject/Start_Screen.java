package com.example.gameproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class Start_Screen extends Activity implements OnClickListener
{
	Button startButton;
	TextView scoreText;
	TextView diedText;
	TableRow scoreTableRow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start__screen);
		
		this.scoreText 		= (TextView) findViewById(R.id.current_end_score);
		this.scoreTableRow 	= (TableRow) findViewById(R.id.tableRowScore);
		this.diedText 		= (TextView) findViewById(R.id.game_end_reason_screen);
		
		//ok if we are coming from the game activity fill out the fields else hide them
		//
		Intent intent = getIntent();
		
		if(intent.hasExtra(getString(R.string.game_end_bundle_name)))
		{
			this.scoreText.setText(intent.getExtras().getString(getString(R.string.score_bundle_name)));
			this.diedText.setText(intent.getExtras().getString(getString(R.string.game_end_bundle_name)));
		}
		else
		{
			this.scoreTableRow.setVisibility(View.GONE);
			this.diedText.setVisibility(View.GONE); 
		}
		
		startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start__screen, menu);
		return true;
	}

	@Override
	public void onClick(View arg0)
	{
		//ok now let us call the new activity
		//
		Intent intent = new Intent(this, MainGameActivity.class);
	
		//ok now send us off
		//
		startActivity(intent);
		
	}

}
