/**
 * Claws of a Deep One
 */
package com.example.gameproject;

import android.util.Log;

/**
 * @author joejones
 *
 */
public class CreatureActionDeepOneClaws extends CreatureAction
{
	private static final String NAME = "Claws of a Deep One";
	private static final String INTERNAL_NAME = "claws_of_deep_one";
	private static final String DESCRIPTION = "Claw slash of a Deep One";
	private static final int DAMAGE = 4;
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
	public CreatureActionDeepOneClaws(Creature passedUser, Creature passedTarget)
	{
		this.setUser(passedUser);
		this.setTarget(passedTarget);
		this.initSetUp();
	}
	
	/**
	 * Parameterless Constructor
	 */
	public CreatureActionDeepOneClaws()
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
	 */
	public void executeAction()
	{
		//first lets see if the attack was successful
		//
		double attackRoll = Math.random();
		Log.d("Deep One Claw Attack",String.valueOf(attackRoll));
		
		//if the roll is less than the attack chance then there is a hit
		//
		if (attackRoll < this.getUser().getAttackChance())
		{	
			this.getTarget().setHealth(this.getTarget().getHealth()-DAMAGE);
			Log.d("Deep One Claw Damage",String.valueOf(this.getTarget().getHealth()));
		}
	}
}
