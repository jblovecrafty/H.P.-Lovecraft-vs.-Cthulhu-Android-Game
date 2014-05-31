/**
 * Action abstract class for creatures 
 */
package com.example.gameproject;

/**
 * @author joejones
 *
 */
public abstract class CreatureAction 
{
	private Creature user;
	private Creature target;
	private String actionName;
	private String internalName;
	private String Description;
	private boolean isAttack;
	private boolean isMagic;
	private boolean isTargetSelf;
	
	public abstract void executeAction();

	/**
	 * @return the user
	 */
	public Creature getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Creature user)
	{
		this.user = user;
	}

	/**
	 * @return the target
	 */
	public Creature getTarget()
	{
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Creature target)
	{
		this.target = target;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName()
	{
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		Description = description;
	}

	/**
	 * @return the internalName
	 */
	public String getInternalName()
	{
		return internalName;
	}

	/**
	 * @param internalName the internalName to set
	 */
	public void setInternalName(String internalName)
	{
		this.internalName = internalName;
	}

	/**
	 * @return the isAttack
	 */
	public boolean isAttack()
	{
		return isAttack;
	}

	/**
	 * @param isAttack the isAttack to set
	 */
	public void setAttack(boolean isAttack)
	{
		this.isAttack = isAttack;
	}

	/**
	 * @return the isMagic
	 */
	public boolean isMagic()
	{
		return isMagic;
	}

	/**
	 * @param isMagic the isMagic to set
	 */
	public void setMagic(boolean isMagic)
	{
		this.isMagic = isMagic;
	}

	/**
	 * @return the isTargetSelf
	 */
	public boolean isTargetSelf()
	{
		return isTargetSelf;
	}

	/**
	 * @param isTargetSelf the isTargetSelf to set
	 */
	public void setTargetSelf(boolean isTargetSelf)
	{
		this.isTargetSelf = isTargetSelf;
	}
}
