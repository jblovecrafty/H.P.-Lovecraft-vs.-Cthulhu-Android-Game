/**
 * Minor healing spell
 */
package com.example.gameproject;

/**
 * @author joejones
 *
 */
public class CreatureActionMinorHealingSpell extends CreatureAction
{
	private static final String NAME = "Minor Healing Spell";
	private static final String INTERNAL_NAME = "minor_healing_spell";
	private static final String DESCRIPTION = "Small healing spell without sanity loss";
	private static final int HEALTH = 5;
	private static final boolean IS_MAGIC = true;
	private static final boolean IS_ATTACK=  false;
	private static final boolean IS_TARGET_SELF=  true;
	
	//set up constructor here
	//
	
	/**
	 * Constructor for the class
	 * @param passedUser
	 */
	public CreatureActionMinorHealingSpell(Creature passedUser)
	{
		this.setUser(passedUser);
		this.setTarget(passedUser);
		this.initSetUp();
	}
	
	/**
	 * Parameterless Constructor
	 */
	public CreatureActionMinorHealingSpell()
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
