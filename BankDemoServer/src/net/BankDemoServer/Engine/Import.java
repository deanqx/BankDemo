package net.BankDemoServer.Engine;

public class Import {
	// Final Variable
	static String Version = "1.1.0";
	static byte[] BankDemoFile;
	static byte[] UpdaterFile;
	// END

	// Final Paths | ($ = Path)
	static String $config = "BankDemoServer/config.yml";
	static String $News = "BankDemoServer/News.yml";
	static String $list = "BankDemoServer/Userbank/list.yml";
	static String $BankDemo = "BankDemoServer/Upload/BankDemo.jar";
	static String $Updater = "BankDemoServer/Upload/Updater.jar";
	// END

	// Changeable Variable
	static String News = "";
	static boolean FullClientLogging = false;
	static int OnlineUsers = 0;
	public static int RegisteredUsers = 0;
	// END
}