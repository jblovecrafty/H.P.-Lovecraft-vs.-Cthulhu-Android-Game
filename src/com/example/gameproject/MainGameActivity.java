/**
 * This is the main game activity controller
 */
package com.example.gameproject;


import java.util.ArrayList;
import java.util.HashMap;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainGameActivity extends Activity implements OnItemSelectedListener, OnClickListener
{
	//set up constants here
	//
	private static final String LEVEL_1_NAME = " 1 (lair of cultists)";
	private static final int LEVEL_1_POINTS = 400;
	
	private static final String LEVEL_2_NAME = " 2 (deep ones arise)";
	private static final int LEVEL_2_POINTS = 800;
	
	private static final String HERO_STARTING_ACTION = "handGun";

	
	//here we set up the hero for the game without any enemy
	//
	CreatureHero hero = new CreatureHero(null);
	private int heroHealth = hero.getHealth();
	private int heroSanity = hero.getSanity();
	private int currentLevel = 0;
	private int currentScore = 0;
	private HashMap<String, String> heroImageList;
	
	private CreatureAction currentHeroAction;
	private CreatureAction currentEnemyAction;
	private Creature currentEnemy;
	
	//set up bad guys
	//
	CreatureCultist cultist;
	CreatureDeepOne deepOne;
	int cultistImage;
	int deepOneImage;
	
	//set up bad guy sounds
	//
	private SoundPool soundPool;
    private HashMap<Integer, Integer> soundsMap;
	int cultistSound;
	int deepOneSound;
	
	//set up level loot here
	//
	private HashMap <String, CreatureAction> heroLevel1;
	CreatureActionFistOfYogSothoth levelOneAction;
	
	private HashMap <String, CreatureAction> heroLevel2;
	CreatureActionDreadCurseOfAzathoth levelTwoAction;
	
	//set up level array of level classes
	//
	GameLevel[] arrayOfGameLevels = new GameLevel[2];

	//set up UI items
	//
	TextView levelText;
	TextView healthHeroText;
	TextView sanityHeroText;
	TextView creatureNameText;
	TextView creatureHealthText;
	TextView currentHeroActionText;
	TextView currentEnemyActionText;
	TextView descriptionActionText;
	TextView currentScoreText;
	
	ImageView creatureImageView;
	ImageView heroImageView;
	
	Button commitButton;
	
	Spinner actionListSpinner;
	
	//set up spinner array
	//
	ArrayList<String> spinnerArrayList = new ArrayList<String>(hero.getActionArray().keySet());
	private ArrayAdapter<String> dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_game);
		
		//build loot and creatures
		//
		this.cultist = new CreatureCultist(this.hero);
		this.deepOne = new CreatureDeepOne(this.hero);
		
		this.heroLevel1 = new HashMap <String, CreatureAction>();
		this.levelOneAction = new CreatureActionFistOfYogSothoth();
		this.heroLevel1.put(this.levelOneAction.getActionName(), this.levelOneAction);
		this.cultistSound = R.raw.cultist;
		this.cultistImage = R.drawable.cultist;
		
		this.heroLevel2 = new HashMap <String, CreatureAction>();
		this.levelTwoAction = new CreatureActionDreadCurseOfAzathoth();
		this.heroLevel2.put(this.levelTwoAction.getActionName(), this.levelTwoAction);
		this.deepOneSound = R.raw.deep_one;
		this.deepOneImage = R.drawable.deep_one;
		
		//set up levels here and then add to array
		//
		GameLevel gameLevelOne = new GameLevel(LEVEL_1_NAME, this.cultist, LEVEL_1_POINTS,
				this.heroLevel1,this.cultistSound, this.cultistImage);
		GameLevel gameLevelTwo = new GameLevel(LEVEL_2_NAME, this.deepOne, LEVEL_2_POINTS,
				this.heroLevel2,this.deepOneSound, this.deepOneImage);
		
		//build levels
		//
		this.arrayOfGameLevels[0] = gameLevelOne;
		this.arrayOfGameLevels[1] = gameLevelTwo;
		
		//get all text and other ui items
		//
		this.levelText 				= (TextView) findViewById(R.id.levelInfo);
		this.healthHeroText 		= (TextView) findViewById(R.id.healthInfoHP);
		this.sanityHeroText 		= (TextView) findViewById(R.id.sanityInfoHP);
		this.creatureNameText 		= (TextView) findViewById(R.id.creatureName);
		this.creatureHealthText 	= (TextView) findViewById(R.id.healthInfoCreature);
		this.currentHeroActionText 	= (TextView) findViewById(R.id.currentActionInfo);
		this.currentEnemyActionText = (TextView) findViewById(R.id.currentEnemyActionInfo);
		this.descriptionActionText 	= (TextView) findViewById(R.id.actionDescriptionInfo);
		this.currentScoreText  		= (TextView) findViewById(R.id.current_score);
		
		this.creatureImageView  	= (ImageView) findViewById(R.id.creatureImageView);
		this.heroImageView  		= (ImageView) findViewById(R.id.hpImageView);
		
		this.commitButton 			= (Button) findViewById(R.id.commit_button);
		
		this.actionListSpinner 		= (Spinner) findViewById(R.id.spinner1);
		
		//build spinner
		//
		this.dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerArrayList);
		this.dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		this.actionListSpinner.setAdapter(dataAdapter);
		this.actionListSpinner.setOnItemSelectedListener(this);
		
		//set up button
		//
		this.commitButton.setOnClickListener(this);
		
		//set up sounds
		//
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        soundsMap = new HashMap<Integer, Integer>();
        
        soundsMap.put(this.cultistSound, soundPool.load(this, this.cultistSound, 1));
        soundsMap.put(this.deepOneSound, soundPool.load(this, this.deepOneSound, 1));
		
		//set up initial state for the hero
		//
        heroImageList = new HashMap<String, String>();
        
        this.currentHeroAction = this.hero.getSingleAction(HERO_STARTING_ACTION);
        this.hero.setCurrentAction(this.currentHeroAction);
        initializeCreatureImageList(this.hero, this.heroImageList);
		initLevel(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_game, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		CreatureAction currentSelectedAction 	= this.hero.getSingleAction(arg0.getItemAtPosition(arg2).toString());
		this.currentHeroAction 					= currentSelectedAction;
		this.descriptionActionText.setText(currentSelectedAction.getDescription());
		
		int imageToUse = getResources().getIdentifier(this.heroImageList.get(this.currentHeroAction.getActionName()),"drawable",this.getPackageName());
		Log.d("Resource Id", String.valueOf(imageToUse));
		
		this.heroImageView.setImageResource(imageToUse);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This updates the heros actions as they progress thru levels
	 * Like loot from fighting each monster
	 * @param passedLevel
	 */
	public void updateHeroActions(int passedLevel)
	{
		for (String key : arrayOfGameLevels[passedLevel].getActionArray().keySet())
        {
			this.arrayOfGameLevels[passedLevel].getActionArray().get(key).setUser(this.hero);
			this.hero.setSingleAction(this.arrayOfGameLevels[passedLevel].getActionArray().get(key));

			//update image list
			//
			updateImageList(this.arrayOfGameLevels[passedLevel].getActionArray().get(key));
			
			this.dataAdapter.add(key);
        }

		//update the spinner
		//
		this.dataAdapter.notifyDataSetChanged();
	}
	
	public void initializeCreatureImageList(Creature passedCreature, HashMap<String, String> passedCreatureImageList)
	{
		for(String key : passedCreature.getActionArray().keySet())
		{
			String actionResource = (passedCreature.getActionArray().get(key).getInternalName() + "_image");
			passedCreatureImageList.put(key, actionResource);
		}
	}
	
	/**
	 * Helper method to update images for hero based on actions
	 * @param passedAction
	 */
	public void updateImageList(CreatureAction passedAction)
	{
		//update image list
		//
		this.heroImageList.put(passedAction.getActionName(), 
		(passedAction.getInternalName() + "_image"));
		Log.d("Hero Image List", this.heroImageList.get(passedAction.getActionName()));
		
	}
	
	
	/**
	 * Method for updating the UI
	 */
	public void updateUI(CreatureAction passedCurrentAction, Boolean passedIsHero)
	{
		//update creature
		//
		this.creatureHealthText.setText(String.valueOf(this.currentEnemy.getHealth()));
		
		//update current action depending on if it is a hero or enemy action
		//
		if(passedIsHero)
		{
			this.currentHeroActionText.setText(passedCurrentAction.getActionName());
		}
		else
		{
			this.currentEnemyActionText.setText(passedCurrentAction.getActionName());
		}
		
		//update hero
		//
		this.healthHeroText.setText(String.valueOf(this.hero.getHealth()));
		this.sanityHeroText.setText(String.valueOf(this.hero.getSanity()));	
		
		//update points
		//
		this.currentScoreText.setText(String.valueOf(this.currentScore));
	}
	

	/**
	 * This is the main part of the game since it keeps the game state going between levels
	 * @param viewForButton
	 */
	public void playerAction(View viewForButton)
	{
		//set up booleans for if hero is alive, sane or if enemy is dead
		//
		boolean isPlayerDead = false;
		boolean isPlayerInsane = false;

		//check hero sanity
		//
		if(this.hero.getSanity() <= 0)
		{
			isPlayerInsane = true;
			endOfGame(this.hero.getHealth(), this.hero.getSanity(), false, this.currentScore);
		}
		
		//check hero health
		//
		if(this.hero.getHealth() <= 0)
		{
			isPlayerDead = true;
			endOfGame(this.hero.getHealth(), this.hero.getSanity(), false, this.currentScore);
		}
		
		//ok player has submitted thier action
		//
		Log.d("Button Pressed", "Woot");
		
		//disable button
		//
		this.commitButton.setEnabled(false);
		
		//perform action and update ui
		//
		if((!isPlayerInsane) || (!isPlayerDead))
		{
			this.currentHeroAction.executeAction();
			Log.d("Current Hero Action", this.currentHeroAction.getActionName());
		
			//update UI method
			//
			updateUI(this.currentHeroAction, true);
		}
		
		//check player state since they might have gone mad casting a spell
		//
		if(this.hero.getSanity() <= 0)
		{
			isPlayerInsane = true;
			endOfGame(this.hero.getHealth(), this.hero.getSanity(), false, this.currentScore);
		}
		
		//initate enemy action and update ui
		//
		if((this.currentEnemy.getHealth() > 0) && ((!isPlayerInsane) || (!isPlayerDead)))
		{	
			//play enemy sound
			//
			this.soundPool.play(soundsMap.get(this.arrayOfGameLevels[this.currentLevel].getSoundId()), 1, 1, 0,0, 1);
			
			//perform enemy action
			//
			this.currentEnemy.performAction();
			this.currentEnemyAction = this.currentEnemy.getCurrentAction();
			
			Log.d("Current Enemy Action", this.currentEnemyAction.getActionName());
			Log.d("Current Sound ID", String.valueOf(this.arrayOfGameLevels[this.currentLevel].getSoundId()));
			
			//update UI method
			//
			updateUI(this.currentEnemyAction,false);
			
			//if player dies then end condition
			//
			if(this.hero.getHealth() <= 0)
			{
				isPlayerDead = true;
				endOfGame(this.hero.getHealth(), this.hero.getSanity(), false, this.currentScore);
			}
		}
		else
		{
			Log.d("Enemy Dead", this.currentEnemy.getNameOfCreature());
		}
		
		//ok check if enemy is dead and hero still alive and sane
		//
		if(this.currentEnemy.getHealth() <= 0 && ((!isPlayerInsane) || (!isPlayerDead)))
		{
			//start next level
			//
			if((this.currentLevel+1) < this.arrayOfGameLevels.length)
			{
				this.currentLevel += 1;
				endOfLevelMethod(this.currentLevel, this);
				Log.d("Current Level", String.valueOf(this.currentLevel));
			}
			else
			{
				//end condition since passed all levels
				//
				this.currentScore += this.arrayOfGameLevels[this.currentLevel].getPointsForLevel();
				endOfGame(this.hero.getHealth(), this.hero.getSanity(), true, this.currentScore);
			}
		}
		
		//initalize button again
		//
		this.commitButton.setEnabled(true);
		
	}
	
	@Override
	public void onClick(View arg0)
	{
		//call the player action method
		//
		playerAction(arg0);
		
	}
	
	//end game method (send back to start screen with score, reason, and retry button)
	//
	
	
	/**
	 * This method is used to initialize a level from the level array
	 * @param passedLevel
	 */
	public void initLevel(int passedLevel)
	{
		this.levelText.setText(this.arrayOfGameLevels[passedLevel].getLevelName());
		
		//set up creature
		//
		this.currentEnemy = this.arrayOfGameLevels[passedLevel].getFoe();
		this.creatureNameText.setText(this.currentEnemy.getNameOfCreature());
		this.creatureHealthText.setText(String.valueOf(this.currentEnemy.getHealth()));
		this.creatureImageView.setImageResource(this.arrayOfGameLevels[passedLevel].getFoeImageResource());
		
		//set up hero (resetting health and sanity)
		//
		updateHeroActions(passedLevel);
		this.hero.setEnemyTarget(this.currentEnemy);
		this.hero.setHealth(this.heroHealth);
		this.hero.setSanity(this.heroSanity);
		this.healthHeroText.setText(String.valueOf(this.hero.getHealth()));
		this.sanityHeroText.setText(String.valueOf(this.hero.getSanity()));	
		this.currentScoreText.setText(String.valueOf(this.currentScore));
	}
	
	/**
	 * This alert is used to transition the user from level to level
	 * @param passedLevel
	 * @param passedContext
	 */
	private void endOfLevelMethod(int passedLevel, Context passedContext)
	{
		
		//set up alert object
		//
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(passedContext);
		
		//set dialog title
		//
		alertDialogBuilder.setTitle("Next Level " + this.arrayOfGameLevels[passedLevel].getLevelName());
		
		//set level score
		//
		alertDialogBuilder.setMessage("Your Score is " + String.valueOf(this.arrayOfGameLevels[passedLevel-1].getPointsForLevel()+this.currentScore));
		this.currentScore += this.arrayOfGameLevels[passedLevel-1].getPointsForLevel();
		
		//set button
		//
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() 
			{
                public void onClick(DialogInterface dialog, int id) 
                {
                    dialog.cancel();
                }
            });
		
		//initialize level and show alert
		//
		initLevel(passedLevel);
		alertDialogBuilder.show();
	}
	
	
	/**
	 * This is the method that handles the end of the game
	 * @param passedHeroHealth
	 * @param passedHeroSanity
	 * @param isEndOfGameLevels
	 * @param passedScore
	 */
	public void endOfGame(int passedHeroHealth, int passedHeroSanity, boolean isEndOfGameLevels, int passedScore)
	{
		String endGameReason = "";
		
		//ok if the user is 0 health then they died
		//
		if(passedHeroHealth <= 0)
		{
			endGameReason = getString(R.string.died_game_over_text);
		}
		
		//if the user is insane 
		//
		else if(passedHeroSanity <= 0)
		{
			endGameReason = getString(R.string.went_insane_game_over_text);
		}
		
		//if the user passed all of the levels
		//
		else if(isEndOfGameLevels)
		{
			endGameReason = getString(R.string.passed_game_over_text);
		}
		
		//package up game end reason and score and send off to start screen
		//
		//ok now let us pack up the information and call the new activity
		//
		Intent intent = new Intent(this, Start_Screen.class);
		Bundle extras = new Bundle();
		
		extras.putString(getString(R.string.game_end_bundle_name), endGameReason);
		extras.putString(getString(R.string.score_bundle_name), String.valueOf(passedScore));
		
		intent.putExtras(extras);
		
		//ok now send us off
		//
		startActivity(intent);
	}
	
}
