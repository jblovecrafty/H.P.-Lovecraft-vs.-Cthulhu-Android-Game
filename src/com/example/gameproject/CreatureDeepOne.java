/**
 * Creature Class for the Deep Ones
 */
package com.example.gameproject;

import java.util.HashMap;

/**
 * @author joejones
 *
 */
public class CreatureDeepOne extends Creature implements ActionList
{
	//set up constants here for the deep one
	//
	private static final int HEALTH = 18;
	private static final int SANITY = 5;
	private static final double ATTACK_CHANCE = .35;
	private static final String NAME = "Slimy Deep One";
	private static final double ATTACK_TYPE_BITE_CHANCE = .15;

	//action based vars
	//
	private CreatureActionBiteOfADeepOne biteOfADeepOne;
	private CreatureActionDeepOneClaws clawsOfADeepOne;
	private HashMap <String, CreatureAction> deepOneActionArray;
	
	public CreatureDeepOne(Creature passedEnemy)
	{
		this.setHealth(HEALTH);
		this.setSanity(SANITY);
		this.setAttackChance(ATTACK_CHANCE);
		this.setNameOfCreature(NAME);
		
		//create actions for the deep one
		//
		this.biteOfADeepOne = new CreatureActionBiteOfADeepOne(this, passedEnemy);
		this.clawsOfADeepOne = new CreatureActionDeepOneClaws(this, passedEnemy);
		this.deepOneActionArray = new HashMap <String, CreatureAction>();
		this.setActionArray(this.deepOneActionArray);
		this.setSingleAction(this.biteOfADeepOne);
		this.setSingleAction(this.clawsOfADeepOne);
	}

	/**
	 * List of actions to take depending on attack type
	 */
	@Override
	public void performAction()
	{
		//what attack to use
		//
		double attackType = Math.random();

		if (attackType <= ATTACK_TYPE_BITE_CHANCE)
		{	
			this.setCurrentAction(this.biteOfADeepOne);
			this.getSingleAction(this.biteOfADeepOne.getActionName()).executeAction();
		}
		else
		{
			this.setCurrentAction(this.clawsOfADeepOne);
			this.getSingleAction(this.clawsOfADeepOne.getActionName()).executeAction();
		}
		
	}
	

}
