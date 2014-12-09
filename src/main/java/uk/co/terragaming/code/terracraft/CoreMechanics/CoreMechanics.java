package uk.co.terragaming.code.terracraft.CoreMechanics;

import uk.co.terragaming.code.terracraft.CoreMechanics.DatabaseMechanics.DatabaseMechanics;
import uk.co.terragaming.code.terracraft.utils.TerraLogger;

public class CoreMechanics {

	public static void Initialize(){
		TerraLogger.info("CoreMechanics Initialized");
		
		ReloadHandler.Run();
		DatabaseMechanics.Initialize();
		
		TerraLogger.info("");
	}
	
	public static void Denitialize(){
		
		
		// Do Last...
		DatabaseMechanics.Denitialize();
	}
	
	public static void DownloadData(){
	}
}
