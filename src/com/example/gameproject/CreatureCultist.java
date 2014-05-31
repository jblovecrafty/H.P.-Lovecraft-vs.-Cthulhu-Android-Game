/**
 * This is the creature type of cultist
 */
package com.example.gameproject;

import java.util.HashMap;

/**
 * @author joejones
 *
 */
public class CreatureCultist extends Creature implements ActionList
{
	//set up constants here for the cultist
	//
	private static final int HEALTH = 10;
	private static final int SANITY = 5;
	private static final double ATTACK_CHANCE = .25;
	private static final String NAME = "Crazed Cultist of Hastur";
	private static final int HEALTH_LOWER_BOUND = 4;
	
	//action based vars
	//
	private CreatureActionKnife cultistKnifeAction;
	private CreatureActionMinorHealingSpell healingSpellAction;
	private HashMap <String, CreatureAction> cultisActionArray;
	
	public CreatureCultist(Creature passedEnemy)
	{
		this.setHealth(HEALTH);
		this.setSanity(SANITY);
		this.setAttackChance(ATTACK_CHANCE);
		this.setNameOfCreature(NAME);
		
		//create actions for the cultist
		//
		this.cultistKnifeAction = new CreatureActionKnife(this, passedEnemy);
		this.healingSpellAction = new CreatureActionMinorHealingSpell(this);
		this.cultisActionArray = new HashMap <String, CreatureAction>();
		this.setActionArray(this.cultisActionArray);
		this.setSingleAction(this.cultistKnifeAction);
		this.setSingleAction(this.healingSpellAction);
	}

	/**
	 * List of actions to take depending on the state of the cultist
	 */
	public void performAction()
	{
		//the cultist always attacks until the health is 4 or below
		//
		if(this.getHealth() <= HEALTH_LOWER_BOUND)
		{
			this.setCurrentAction(this.healingSpellAction);
			this.getCurrentAction().executeAction();
		}
		else
		{
			this.setCurrentAction(this.cultistKnifeAction);
			this.getCurrentAction().executeAction();
		}
		
	}
	
}
