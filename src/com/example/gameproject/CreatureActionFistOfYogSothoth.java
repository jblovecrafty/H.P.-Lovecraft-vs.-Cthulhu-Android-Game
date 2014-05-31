/**
 * Attack spell that does damage and causes user sanity loss
 */
package com.example.gameproject;

import java.util.Random;

import android.util.Log;

/**
 * @author joejones
 *
 */
public class CreatureActionFistOfYogSothoth extends CreatureAction
{
	private static final String NAME = "Fist of Yog Sothoth";
	private static final String INTERNAL_NAME = "fist_of_yog_sothoth";
	private static final String DESCRIPTION = "The power of Yog Sothoth and some sanity loss";
	private static final int SANITY_LOSS_MAX = 5;
	private static final int DAMAGE = 8;
	private static final boolean IS_MAGIC = true;
	private static final boolean IS_ATTACK=  true;
	private static final boolean IS_TARGET_SELF=  false;
	
	//set up constructor here
	//
	
	/**
	 * Constructor for the class
	 * @param passedUser
	 * @param passedTarget
	 */
	public CreatureActionFistOfYogSothoth(Creature passedUser, Creature passedTarget)
	{
		this.setUser(passedUser);
		this.setTarget(passedTarget);
		this.initSetUp();
	}
	
	/**
	 * Parameterless Constructor
	 */
	public CreatureActionFistOfYogSothoth()
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
	 * This is the action that deals with sanity loss or potential sanity loss
	 * and do damage
	 */
	public void executeAction()
	{
		//random sanity loss amount in integer
		//
		Random generator = new Random();
		int sanLoss = generator.nextInt(SANITY_LOSS_MAX);
		Log.d("Fist of Yogsothoth Sanity Loss",String.valueOf(sanLoss));
		
		this.getUser().setSanity(this.getUser().getSanity() - sanLoss);
		this.getTarget().setHealth(this.getTarget().getHealth()-DAMAGE);
	}

}
