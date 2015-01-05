package uk.co.terragaming.code.terracraft.StaffMechanics;

import uk.co.terragaming.code.terracraft.Mechanic;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.CommandManager;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.CommandMechanics;
import uk.co.terragaming.code.terracraft.StaffMechanics.commands.StaffSpawnCustomItem;
import uk.co.terragaming.code.terracraft.StaffMechanics.commands.TestCommandListener;

public class StaffMechanics implements Mechanic{

	public boolean isEnabled() 	{ return true; }
	public boolean isCore() 	{ return false; }

	// Mechanic Variables
	
	
	// Mechanic Methods
	
	// Mechanic Events
	
	@Override
	public void PreInitialize() {

	}

	@Override
	public void Initialize() {
		CommandMechanics commandMechanics = CommandMechanics.getInstance();

		CommandManager commandManger = commandMechanics.createCommandManager("staff", "a");
		commandManger.registerCommand("spawncustomitem", new StaffSpawnCustomItem(), "Allows spawning of custom Items.", "/staff spawnCustomItem <ItemId>");
		commandManger.registerCommand("citem", new StaffSpawnCustomItem());
		
		for (int i=1; i < 11; i++){
			commandManger.registerCommand("test" + i, new TestCommandListener(), "Test " + i, "/test" + i + " [test]");
		}
	}

	@Override
	public void PostInitialize() {
		
	}

	@Override
	public void PreDenitialize() {
		
	}

	@Override
	public void Denitialize() {
		
	}

	@Override
	public void PostDenitialize() {
		
	}
}