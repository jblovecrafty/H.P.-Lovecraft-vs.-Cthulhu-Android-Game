/**
 * Action for using Cults De Ghouls a book for health regeneation
 */
package com.example.gameproject;

/**
 * @author joejones
 *
 */
public class CreatureActionCultsDeGhoulsBook extends CreatureAction
{
	private static final String NAME = "Cults De Ghouls";
	private static final String INTERNAL_NAME = "cults_de_ghouls";
	private static final String DESCRIPTION = "An ancient book that has a healing spell";
	private static final int HEALTH = 8;
	private static final boolean IS_MAGIC = true;
	private static final boolean IS_ATTACK=  false;
	private static final boolean IS_TARGET_SELF = true;
	
	//set up constructor here
	//
	
	/**
	 * Constructor for the class
	 * @param passedUser
	 * @param passedTarget
	 */
	public CreatureActionCultsDeGhoulsBook(Creature passedUser)
	{
		this.setUser(passedUser);
		this.setTarget(passedUser);
		this.initSetUp();
	}
	
	/**
	 * Parameterless Constructor
	 */
	public CreatureActionCultsDeGhoulsBook()
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
	 * This is the action that heals a user
	 */
	public void executeAction()
	{		
		this.getUser().setHealth(this.getUser().getHealth()+HEALTH);
	}

}
