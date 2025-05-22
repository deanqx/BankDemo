package net.BankDemoServer.Engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import net.Meta.Enable;
import net.Meta.MetaConsole;
import net.Meta.MetaExpress;
import net.Meta.MetaTime;

public class Main {
	public static void main(String[] args) {
		MetaTime.StartStopWatch();

		MetaConsole.out("Starting Server...", 0, true);

		if (!MetaExpress.isFolderExists("BankDemoServer")) {
			MetaConsole.out("Setup...", 0, true);
			Setup();
			MetaConsole.out("Setup complete.", 0, true);
		} else {
			MetaExpress.setPropertie(Import.$config, "Version", Import.Version);
		}

		MetaConsole.out("Loading Library...", 0, true);
		Loading();
		MetaConsole.out("Loading Library complete.", 0, true);
		Enable.Setup(true, "BankDemoServer/logs", false);
		MetaExpress.setPropertie(Import.$config, "Version", Import.Version);

		MetaConsole.outPrint("Starting Console...", 0, true);
		new Thread(new BankConsole()).start();
		System.out.println("Complete");

		Server.Start();

		double StartTime = (MetaTime.EndStopWatch() / 1000.0);
		MetaConsole.out("- Starting Complete ( " + StartTime + "s )! -", 0, true);
		MetaConsole.out("Type ( help ) for the command list.", 0, true);
	}

	private static void Setup() {
		MetaConsole.out("-----------------------------", 0, true);

		MetaExpress.CreateFolder("BankDemoServer");

		if (!MetaExpress.isExists(Import.$config)) {
			MetaExpress.Create(Import.$config);
			MetaExpress.setPropertie(Import.$config, "Version", Import.Version);
		}

		if (!MetaExpress.isExists(Import.$News)) {
			MetaExpress.Create(Import.$News);
		}

		if (!MetaExpress.isFolderExists("BankDemoServer/Userbank")) {
			MetaExpress.CreateFolder("BankDemoServer/Userbank");
			MetaExpress.Create(Import.$list);
			MetaExpress.setPropertie(Import.$list, "Registered", "0");
		}

		if (!MetaExpress.isFolderExists("BankDemoServer/Upload")) {
			MetaExpress.CreateFolder("BankDemoServer/Upload");
		}

		while (true) {
			if (MetaExpress.isExists(Import.$BankDemo) && MetaExpress.isExists(Import.$Updater)) {
				break;
			} else {
				MetaConsole.out(
						"Upload files don't exists! Place `BankDemo.jar` and `Updater.jar` in `./BankDemoServer/Upload`",
						2,
						true);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		MetaConsole.out("-----------------------------", 0, true);
	}

	static void Loading() {
		MetaConsole.out("-----------------------------", 0, true);

		Import.News = "";

		LT(Import.$config);
		Import.Version = MetaExpress.getPropertie(Import.$config, "Version");
		LTC();

		LT(Import.$list);
		String s = MetaExpress.getPropertie(Import.$list, "Registered");
		if (!s.equals("")) {
			Import.RegisteredUsers = Integer.parseInt(s);
		}
		LTC();

		LT(Import.$News);
		List<String> ReadNews = MetaExpress.Read(Import.$News);
		for (String s1 : ReadNews) {
			Import.News = (Import.News + s1 + "\n");
		}
		LTC();

		LT(Import.$BankDemo);
		try {
			Import.BankDemoFile = Files.readAllBytes(Paths.get(Import.$BankDemo));
			LTC();
		} catch (IOException e) {
		}

		LT(Import.$Updater);
		try {
			Import.UpdaterFile = Files.readAllBytes(Paths.get(Import.$Updater));
			LTC();
		} catch (IOException e) {
		}

		MetaConsole.out("-----------------------------", 0, true);
	}

	// LT = LoadingTemplate
	static void LT(String LoadingText) {
		MetaConsole.outPrint("Loading " + LoadingText + "...", 0, true);
	}

	// LTC = LoadingTemplateComplete
	static void LTC() {
		System.out.println("Complete");
	}
}