package net.BankDemo.Engine;

import java.util.ArrayList;
import java.util.List;

public class Import {
	// Final Variable
	static String Version = "1.1.0";
	static String Copyright = "Copyright SaweyHD. All Rights Reserved.";
	static String ServerIP = "127.0.0.1";
	// END

	// Final Paths | ($ = Path)
	static String $config = "BankDemo/config.yml";
	public static String $account = "BankDemo/account.yml";
	static String $BankDemo = "BankDemo/Modules/BankDemo.jar";
	static String $Updater = "BankDemo/Modules/Updater.jar";
	// END

	// Changeable Variable
	static boolean OfflineMode = true;
	public static int Balance = 0;
	public static String Username = "";
	public static String Password = "";
	public static boolean Remember = false;
	static String RUsername = "";
	static String RPassword = "";
	static String Language = "English";
	public static List<String> lang = new ArrayList<String>();
	// END
}