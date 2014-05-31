/**
 * Sanity loss caused by seeing something less terrible than Cthulhu
 */
package com.example.gameproject;

import java.util.Random;

/**
 * @author joejones
 *
 */
public class CreatureActionSanityLossLesser extends CreatureAction
{
	private static final String NAME = "Lesser Sanity Loss";
	private static final String INTERNAL_NAME = "lesser_sanity_loss";
	private static final String DESCRIPTION = "Deal with the fact that things are crazy";
	private static final int SANITY_LOSS_MAX = 4;
	private static final boolean IS_MAGIC = false;
	private static final boolean IS_ATTACK=  false;
	private static final boolean IS_TARGET_SELF=  false;
	
	//set up constructor here
	//
	
	/**
	 * Constructor for the class
	 * @param passedUser
	 * @param passedTarget
	 */
	public CreatureActionSanityLossLesser(Creature passedUser, Creature passedTarget)
	{
		this.setUser(passedUser);
		this.setTarget(passedTarget);
		this.initSetUp();
	}
	
	/**
	 * Parameterless Constructor
	 */
	public CreatureActionSanityLossLesser()
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
	 */
	public void executeAction()
	{
		//random sanity loss amount in integer
		//
		Random generator = new Random();
		int sanLoss = generator.nextInt(SANITY_LOSS_MAX);
		
		this.getTarget().setSanity(this.getTarget().getSanity()-sanLoss);
	}


}
