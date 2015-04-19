package uk.co.terragaming.code.terracraft.StaffMechanics;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.co.terragaming.code.terracraft.CharacterMechanics.Character;
import uk.co.terragaming.code.terracraft.CharacterMechanics.CharacterInterface;
import uk.co.terragaming.code.terracraft.CharacterMechanics.CharacterMechanics;
import uk.co.terragaming.code.terracraft.CoreMechanics.AccountMechanics.Account;
import uk.co.terragaming.code.terracraft.CoreMechanics.AccountMechanics.AccountMechanics;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.annotations.Command;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.annotations.CommandDescription;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.annotations.CommandParent;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.annotations.CommandUsage;
import uk.co.terragaming.code.terracraft.CoreMechanics.CommandMechanics.annotations.HelpCommand;
import uk.co.terragaming.code.terracraft.utils.Txt;

public class StaffCommands {

	@Command({"staff","admin","a"})
	@CommandDescription("Staff Command Set")
	@CommandUsage("/staff help")
	@HelpCommand
	public void onStaffCommand(CommandSender sender){
		sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] Use <c>/staff help<r> for a list of commands."));
	}
	
	@Command({"char", "character","c"})
	@CommandDescription("Staff Command Set")
	@CommandParent("staff")
	@CommandUsage("/staff help")
	@HelpCommand
	public void onCharCommand(CommandSender sender){
		sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] Use <c>/staff char help<r> for a list of commands."));
	}
	
		@Command({"download","down","d"})
		@CommandDescription("Force Download Character")
		@CommandParent("staff char")
		@CommandUsage("/staff char download")
		public void onCharDownloadCommand(Player sender){
			CharacterMechanics characterMechanics = CharacterMechanics.getInstance();
			AccountMechanics accountMechanics = AccountMechanics.getInstance();
			
			Account account = accountMechanics.getRegistry().getAccount(sender);
			Character character = characterMechanics.getAccountActiveCharater(account);
			
			character.setActiveCharacter();
			sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] Successfully download character from database."));
		}
		
		@Command({"switch","s"})
		@CommandDescription("Switch Character")
		@CommandParent("staff char")
		@CommandUsage("/staff char switch")
		public void onCharSwitchCommand(Player sender){
			CharacterMechanics characterMechanics = CharacterMechanics.getInstance();
			AccountMechanics accountMechanics = AccountMechanics.getInstance();
			
			Account account = accountMechanics.getRegistry().getAccount(sender);
			Character character = characterMechanics.getAccountActiveCharater(account);
			
			character.setLocation(sender.getLocation());
			character.setCurExp(Math.round(sender.getExp() * 100));
			character.setCurHitpoints((int) Math.round(sender.getHealth()));
			character.setCurHunger(sender.getFoodLevel());
			character.setCurLevel(sender.getLevel());
			
			character.uploadData();
			
			new CharacterInterface(sender.getUniqueId());
			sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] Successfully switched character."));
		}
	
	@Command({"item","i"})
	@CommandDescription("Staff Command Set")
	@CommandParent("staff")
	@CommandUsage("/staff help")
	@HelpCommand
	public void onItemCommand(CommandSender sender){
		sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] Use <c>/staff item help<r> for a list of commands."));
	}
	
		@Command({"create","c"})
		@CommandDescription("Spawn Item")
		@CommandParent("staff item")
		@CommandUsage("/staff item create")
		public void onItemSpawnCommand(Player sender){
			sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] Not Yet Implemented"));
		}
}
