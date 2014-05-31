/**
 * Creature Action Class for a Gun
 */
package com.example.gameproject;

import android.util.Log;

/**
 * @author joejones
 *
 */
public class CreatureActionGun extends CreatureAction
{
	private static final String NAME = "Hand Gun";
	private static final String INTERNAL_NAME = "hand_gun";
	private static final String DESCRIPTION = "Fairly standard handgun, nothing fancy";
	private static final int DAMAGE = 5;
	private static final boolean IS_MAGIC = false;
	private static final boolean IS_ATTACK=  true;
	private static final boolean IS_TARGET_SELF=  false;
	
	//set up constructor here
	//
	
	/**
	 * Constructor for the class
	 * @param passedUser
	 * @param passedTarget
	 */
	public CreatureActionGun(Creature passedUser, Creature passedTarget)
	{
		this.setUser(passedUser);
		this.setTarget(passedTarget);
		this.initSetUp();
	}
	
	/**
	 * Parameterless Constructor
	 */
	public CreatureActionGun()
	{
		this.initSetUp();
	}
	
	/**
	 * Set up init for the object
	 */
	private void initSetUp()
	{
		this.setActionName(NAME);
		this.setDescription(DESCRIPTION);
		this.setMagic(IS_MAGIC);
		this.setAttack(IS_ATTACK);
		this.setTargetSelf(IS_TARGET_SELF);
		this.setInternalName(INTERNAL_NAME);
	}
	
	/**
	 * This is the action and this does a single increment of damage
	 * other guns should do a variable amount randomly
	 */
	public void executeAction()
	{
		//first lets see if the attack was successful
		//
		double attackRoll = Math.random();
		Log.d("Gun Hit",String.valueOf(attackRoll));
		
		//if the roll is less than the attack chance then there is a hit
		//
		if (attackRoll < this.getUser().getAttackChance())
		{	
			this.getTarget().setHealth(this.getTarget().getHealth()-DAMAGE);
			Log.d("Gun Damage",String.valueOf(this.getTarget().getHealth()));
		}
	}
}
