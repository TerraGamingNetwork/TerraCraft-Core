package uk.co.terragaming.Core.Util;

import javax.inject.Singleton;

import org.bukkit.Server;

import uk.co.terragaming.Core.Plugin;
import uk.co.terragaming.Core.Foundation.Mechanic;
import uk.co.terragaming.Core.Util.Logger.TerraLogger;
import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule extends Mechanic{
	@Provides @Singleton Plugin providePlugin(){
		return Plugin.plugin;
	}
	
	@Provides @Singleton Server provideServer(){
		return Plugin.server;
	}
	
	@Provides @Singleton TerraLogger provideLogger(Plugin plugin){
		return new TerraLogger(plugin);
	}
}
