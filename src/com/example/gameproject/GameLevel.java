/**
 * this is the game level class 
 */
package com.example.gameproject;

import java.util.HashMap;

/**
 * @author joejones
 *
 */
public class GameLevel 
{

	//set up vars here
	//
	private String levelName;
	private Creature foe;
	private int foeImageResource;
	private int pointsForLevel;
	private HashMap <String, CreatureAction> actionArray;
	private int soundId;
	
	//constructor for GameLevel
	//
	public GameLevel(String passedLevelName, Creature passedFoe, int passedPointsForLevel,
					HashMap <String, CreatureAction> passedActionArray, int passedSoundId, int passedFoeImageResource)
	{
		this.setLevelName(passedLevelName);
		this.setFoe(passedFoe);
		this.setPointsForLevel(passedPointsForLevel);
		this.setActionArray(passedActionArray);
		this.setSoundId(passedSoundId);
		this.setFoeImageResource(passedFoeImageResource);
	}
	
	
	//methods here
	//
	/**
	 * @return the levelName
	 */
	public String getLevelName() 
	{
		return levelName;
	}
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) 
	{
		this.levelName = levelName;
	}
	/**
	 * @return the foe
	 */
	public Creature getFoe() 
	{
		return foe;
	}
	/**
	 * @param foe the foe to set
	 */
	public void setFoe(Creature foe) 
	{
		this.foe = foe;
	}
	/**
	 * @return the pointsForLevel
	 */
	public int getPointsForLevel() 
	{
		return pointsForLevel;
	}
	/**
	 * @param pointsForLevel the pointsForLevel to set
	 */
	public void setPointsForLevel(int pointsForLevel) 
	{
		this.pointsForLevel = pointsForLevel;
	}


	/**
	 * @return the actionArray
	 */
	public HashMap<String, CreatureAction> getActionArray()
	{
		return actionArray;
	}


	/**
	 * @param actionArray the actionArray to set
	 */
	public void setActionArray(HashMap<String, CreatureAction> actionArray)
	{
		this.actionArray = actionArray;
	}


	/**
	 * @return the soundId
	 */
	public int getSoundId()
	{
		return soundId;
	}


	/**
	 * @param soundId the soundId to set
	 */
	public void setSoundId(int soundId)
	{
		this.soundId = soundId;
	}


	/**
	 * @return the foeImageResource
	 */
	public int getFoeImageResource()
	{
		return foeImageResource;
	}


	/**
	 * @param foeImageResource the foeImageResource to set
	 */
	public void setFoeImageResource(int foeImageResource)
	{
		this.foeImageResource = foeImageResource;
	}
	

}
