package uk.co.terragaming.code.terracraft.mechanics.CharacterMechanics.listeners;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import uk.co.terragaming.code.terracraft.TerraCraft;
import uk.co.terragaming.code.terracraft.mechanics.CharacterMechanics.interfaces.CharacterSelectInterface;
import uk.co.terragaming.code.terracraft.mechanics.CoreMechanics.AccountMechanics.Account;
import uk.co.terragaming.code.terracraft.mechanics.CoreMechanics.AccountMechanics.AccountMechanics;
import uk.co.terragaming.code.terracraft.utils.Lang;
import uk.co.terragaming.code.terracraft.utils.Txt;

public class LoginListener implements Listener{

	@EventHandler
	public void PlayerLoginEvent(PlayerLoginEvent event){
		try {
			Account account = AccountMechanics.getInstance().getRegistry().getAccount(event.getPlayer());
			account.getCharacters().refreshAll();
		} catch (SQLException e) {
			event.getPlayer().kickPlayer(Txt.parse(Lang.get("accountInternalError")));
			e.printStackTrace();
		}
		
		Player player = event.getPlayer();
		
		Bukkit.getScheduler().runTask(TerraCraft.plugin, new Runnable()
		{
		    @Override
		    public void run()
		    {
		    	 new CharacterSelectInterface(player);
		    }
		});
		
		
	}
	
}
