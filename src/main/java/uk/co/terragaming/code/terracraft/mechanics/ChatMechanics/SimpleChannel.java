package uk.co.terragaming.code.terracraft.mechanics.ChatMechanics;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import uk.co.terragaming.code.terracraft.enums.Language;
import uk.co.terragaming.code.terracraft.mechanics.CoreMechanics.AccountMechanics.AccountMechanics;
import uk.co.terragaming.code.terracraft.mechanics.CoreMechanics.AccountMechanics.AccountRegistry;
import uk.co.terragaming.code.terracraft.utils.ChatUtils;
import uk.co.terragaming.code.terracraft.utils.Lang;
import uk.co.terragaming.code.terracraft.utils.Txt;

public class SimpleChannel extends Channel{
	
	@Override
	public void processChatEvent(Player sender, String message) {
		Language lang = Language.ENGLISH;
		AccountRegistry registry = AccountMechanics.getInstance().getRegistry();
		
		if (registry.hasAccount(sender))
			lang = registry.getAccount(sender).getLanguage();
		
		for (UUID uuid : getMutedPlayers()){
			if (uuid.equals(sender.getUniqueId())){
				sender.sendMessage(Txt.parse("[<l>TerraCraft<r>] " + Lang.get(lang, "ChatChannelMuted")));
				return;
			}
		}
		
		String name = "";
		if (sender != null)
			name = ChatUtils.getName(sender, this);
	
		Integer range = getRange();
		
		if (range == -1){
			for (UUID uuid : getJoinedPlayers()){
				Player reciever = Bukkit.getPlayer(uuid);
				reciever.sendMessage(Txt.parse("[<l>%s<r>]%s %s", getTag(), (sender == null ? "" : " <" + name + ">"), message));
			}
		} else {
			sender.sendMessage(Txt.parse("[<l>%s<r>] <%s> %s", getTag(), name, message));
			boolean heared = false;
			for (Entity entity : sender.getNearbyEntities(range, 500, range)){
				if (!(entity instanceof Player)) continue;
				Player reciever = (Player) entity;
				if (contains(reciever.getUniqueId())){
					heared = true;
					reciever.sendMessage(Txt.parse("[<l>%s<r>] <%s> %s", getTag(), name, message));
				}
			}
			if (!heared){
				List<String> messages = Txt.parseWrap(Lang.get(lang, "chatLocalOutOfRange"), false);
				for (String msg : messages){
					sender.sendMessage(msg);
				}
			}
		}
	}

}
