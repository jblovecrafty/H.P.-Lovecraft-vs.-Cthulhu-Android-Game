/**
 * This is the abstract class for the creatures in this game
 */
package com.example.gameproject;

import java.util.HashMap;

/**
 * @author joejones
 *
 */
public abstract class Creature implements ActionList
{

	//create the variables
	//
	private int health;
	private int sanity;
	private String nameOfCreature;
	private ActionList listOfActions;
	private HashMap <String, CreatureAction> actionArray;
	private HashMap <String, String> creatureImageArray;
	private double attackChance;
	private CreatureAction currentAction;
	
	//methods here
	//
	
	/**
	 * @return the health
	 */
	public int getHealth()
	{
		return health;
	}
	
	/**
	 * @param health the health to set
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	/**
	 * @return the sanity
	 */
	public int getSanity()
	{
		return sanity;
	}
	
	/**
	 * @param sanity the sanity to set
	 */
	public void setSanity(int sanity)
	{
		this.sanity = sanity;
	}
	
	/**
	 * @return the nameOfCreature
	 */
	public String getNameOfCreature()
	{
		return nameOfCreature;
	}
	
	/**
	 * @param nameOfCreature the nameOfCreature to set
	 */
	public void setNameOfCreature(String nameOfCreature)
	{
		this.nameOfCreature = nameOfCreature;
	}
	
	/**
	 * @return the listOfActions
	 */
	public ActionList getListOfActions()
	{
		return listOfActions;
	}
	
	/**
	 * @param listOfActions the listOfActions to set
	 */
	public void setListOfActions(ActionList listOfActions)
	{
		this.listOfActions = listOfActions;
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
	 * Get a creature action by name
	 * @param passedName
	 * @return Creature Action
	 */
	public CreatureAction getSingleAction(String passedName)
	{
		return this.getActionArray().get(passedName);
	}
	
	/**
	 * This method sets our actions one at a time
	 * @param passedCreatureAction
	 */
	public void setSingleAction(CreatureAction passedCreatureAction)
	{
		this.getActionArray().put(passedCreatureAction.getActionName(), passedCreatureAction);
	}
	
	/**
	 * @return the creatureImageArray
	 */
	public HashMap<String, String> getCreatureImageArray()
	{
		return creatureImageArray;
	}
	/**
	 * @param creatureImageArray the creatureImageArray to set
	 */
	public void setCreatureImageArray(HashMap<String, String> creatureImageArray)
	{
		this.creatureImageArray = creatureImageArray;
	}
	
	/**
	 * Get a single string that represents the image from the creature array
	 * @param passedStringOfImage
	 * @return
	 */
	public String getCreatureImage(String passedStringOfImage)
	{
		return this.getCreatureImageArray().get(passedStringOfImage);
	}

	/**
	 * @return the attackChance
	 */
	public double getAttackChance()
	{
		return attackChance;
	}

	/**
	 * @param attackChance the attackChance to set
	 */
	public void setAttackChance(double attackChance)
	{
		this.attackChance = attackChance;
	}

	/**
	 * @return the currentAction
	 */
	public CreatureAction getCurrentAction()
	{
		return currentAction;
	}

	/**
	 * @param currentAction the currentAction to set
	 */
	public void setCurrentAction(CreatureAction currentAction)
	{
		this.currentAction = currentAction;
	}


}
