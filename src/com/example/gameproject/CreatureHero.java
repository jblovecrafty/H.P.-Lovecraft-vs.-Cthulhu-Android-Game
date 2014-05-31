/**
 * Class for the user hero of the game
 */
package com.example.gameproject;

import java.util.HashMap;

/**
 * @author joejones
 *
 */
public class CreatureHero extends Creature implements ActionList
{
	//set up constants here for initial hero stats
	//
	private static final int HEALTH = 25;
	private static final int SANITY = 25;
	private static final double ATTACK_CHANCE = .50;
	private static final String NAME = "H.P. Lovecraft";

	//initial gear
	//
	private CreatureActionCultsDeGhoulsBook cultsDeGhouls;
	private CreatureActionGun gunForHero;
	private HashMap <String, CreatureAction> heroActionArray;
	
	public CreatureHero(Creature passedEnemy)
	{
		this.setHealth(HEALTH);
		this.setSanity(SANITY);
		this.setAttackChance(ATTACK_CHANCE);
		this.setNameOfCreature(NAME);
		
		//create actions for the hero
		//
		this.cultsDeGhouls = new CreatureActionCultsDeGhoulsBook(this);
		this.gunForHero = new CreatureActionGun(this, passedEnemy);
		this.heroActionArray = new HashMap <String, CreatureAction>();
		this.setActionArray(this.heroActionArray);
		this.setSingleAction(this.cultsDeGhouls);
		this.setSingleAction(this.gunForHero);
	}

	/**
	 * Not used for user controlled hero
	 */
	@Override
	public void performAction()
	{	
	}
	
	/**
	 * Method for setting the target of the action if it is not a self action
	 * @param passedCreature
	 */
	public void setEnemyTarget(Creature passedCreature)
	{
		for (String key : this.heroActionArray.keySet())
        {
			if(!this.heroActionArray.get(key).isTargetSelf())
			{
				this.heroActionArray.get(key).setTarget(passedCreature);
			}
        }
	}

}
