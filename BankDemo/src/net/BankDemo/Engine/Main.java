package net.BankDemo.Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

import net.Meta.Enable;
import net.Meta.MetaConsole;
import net.Meta.MetaExpress;
import net.Meta.MetaTime;
import net.BankDemo.LanguagePacks.English;
import net.BankDemo.Modules.Downloader;
import net.BankDemo.Modules.PopUp;

public class Main {
	private static boolean Logging = false;
	static boolean ServerStarted;

	public static void main(String[] args) {
		/**
		 * Account löschen gehen nicht :
		 * https://stackoverflow.com/questions/30990246/java-swing-jframe-launch-another-jframe-for-game
		 * MetaWait erstellen
		 */

		MetaTime.StartStopWatch();
		StartingFrame.StartJFrame();

		MetaConsole.out("Starting...", 0, true);

		MetaConsole.out("Loading Library...", 0, true);
		Loading();
		MetaConsole.out("Loading Library complete.", 0, true);
		Enable.Setup(Logging, "BankDemo/logs", false);

		if (!ServerStarted) {
			MetaConsole.out("Connecting to Server...", 0, true);
			ServerConnect.StartSocket();
			MetaConsole.out("Connecting to Server complete.", 0, true);
			ServerStarted = true;
		}

		MetaConsole.out("Checking for Updates...", 0, true);
		ServerConnect.CheckForUpdates();
		MetaConsole.out("No Update available.", 0, true);

		LT("Frame");
		while (!StartingFrame.Started) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
			}
		}
		new StartingFrame().Close();

		BankDemoFrame.loadSettings();
		BankDemoFrame.StartJFrame();

		BankDemoFrame.getNews();
		LTC();

		double StartTime = (MetaTime.EndStopWatch() / 1000.0);
		MetaConsole.out("- Starting complete ( " + StartTime + "s )! -", 0, true);
	}

	private static void Loading() {
		MetaConsole.out("-----------------------------", 0, true);

		if (MetaExpress.isFolderExists("BankDemo")) {
			boolean NewVersion = !MetaExpress.getPropertie(Import.$config, "Version").equals(Import.Version);

			LT(Import.$config);
			if (MetaExpress.getPropertie(Import.$config, "Server").equals("TESTSERVER")) {
				Import.ServerIP = "127.0.0.1";
			}

			Logging = MetaExpress.getPropertie(Import.$config, "LogMode").equals("true");
			LTC();

			LT("Language");
			if (!MetaExpress.isExists(English.$English)) {
				MetaExpress.Create(English.$English);
				English.setLanguage();
			}

			Import.Language = MetaExpress.getPropertie(Import.$config, "Language");
			loadLanguage(Import.Language);
			LTC();

			LT(Import.$account);
			if (MetaExpress.getPropertie(Import.$account, "Remember").equals("true")) {
				Import.Remember = true;
				Import.RUsername = MetaExpress.getPropertie(Import.$account, "Username");
				Import.RPassword = MetaExpress.getPropertie(Import.$account, "Password");
			} else {
				Import.Remember = false;
			}
			LTC();

			if (NewVersion) {
				MetaConsole.out("Updating local files...", 0, true);

				MetaExpress.DeleteFolder("BankDemo");
				Setup();

				MetaConsole.out("Updating local files complete.", 0, true);
			}
		} else {
			MetaConsole.out("Connecting to Server...", 0, true);
			ServerConnect.StartSocket();
			MetaConsole.out("Connecting to Server complete.", 0, true);
			ServerStarted = true;

			Setup();
			Loading();
		}

		MetaConsole.out("-----------------------------", 0, true);
	}

	static void Setup() {
		MetaConsole.out("-----------------------------", 0, false);

		if (!MetaExpress.isFolderExists("BankDemo")) {
			MetaExpress.CreateFolder("BankDemo");
		}

		if (!MetaExpress.isFolderExists("BankDemo/lang")) {
			MetaExpress.CreateFolder("BankDemo/lang");
		}

		if (!MetaExpress.isFolderExists("BankDemo/Modules")) {
			MetaExpress.CreateFolder("BankDemo/Modules");
		}

		if (!MetaExpress.isExists(English.$English)) {
			MetaExpress.Create(English.$English);
		} else {
			MetaExpress.Delete(English.$English);
			MetaExpress.Create(English.$English);
		}
		English.setLanguage();

		if (!MetaExpress.isExists(Import.$config)) {
			MetaExpress.Create(Import.$config);
			MetaExpress.setPropertie(Import.$config, "Version", Import.Version);
			MetaExpress.setPropertie(Import.$config, "Language", Import.Language);
			MetaExpress.setPropertie(Import.$config, "Server", "MAINSERVER");
			MetaExpress.setPropertie(Import.$config, "LogMode", Logging + "");
		}

		if (!MetaExpress.isExists(Import.$account)) {
			MetaExpress.Create(Import.$account);
			MetaExpress.setPropertie(Import.$account, "Remember", Import.Remember + "");
			MetaExpress.setPropertie(Import.$account, "Username", Import.RUsername);
			MetaExpress.setPropertie(Import.$account, "Password", Import.RPassword);
		}

		if (!MetaExpress.isExists(Import.$Updater)) {
			PopUp.SetupMode = true;
			if (PopUp.Show("You still have to download the BankDemo Modules.", 0, true, "Start Download",
					"No, Exit BankDemo.")) {
				Downloader.StartDownload("Updater.jar", Import.$Updater);
			} else {
				System.exit(0);
			}
			PopUp.SetupMode = false;
		}

		MetaConsole.out("-----------------------------", 0, false);
	}

	public static void loadLanguage(String lang) {
		if (!MetaExpress.isExists("BankDemo/lang/" + lang + ".lang")) {
			lang = "English";
		}

		Import.lang = new ArrayList<String>();
		Import.lang.add("");
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "News"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Login"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "LoginUsername"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "LoginPassword"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Remember"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "SIGN_IN"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "CAccount"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Register"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "RegisterUsername"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "RegisterPassword"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "RegisterCPassword"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "SIGN_UP"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Cancel"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "CttransferYon"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Cfsettings"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Refresh"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Back"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Transfer_to_this_Username"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Transfer_Amount"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Transfer"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Transfer_History"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "User_Settings"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Program_Settings"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Informations"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "My_Account"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "General"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Patch_Log"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Credits"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Logout"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Change_Password"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Delete_Account"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Old_Password"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "New_Password"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Confirm_new_Password"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Enabled"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Disabled"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Language"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Local_Settings"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Write_logs_to_debug_BankDemo"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Connect_to_Testserver"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Apply"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Undo"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "SaweyHD"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "RememberToolTip"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Yes"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Yes1"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "No"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "No1"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "No2"));
		Import.lang.add(
				MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang",
						"Do_you_really_want_to_delete_the_account"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang",
				"Do_you_really_really_want_to_delete_the_account_YOU_CANT_UNDO_IT"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Please_fill_the_text_fields"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Password_is_changed"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Passwords_not_match"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang",
				"You_cant_change_the_Password_to_the_same_old"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Wrong_Password"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Account_not_found"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Transfer_Successfully"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "You_have_not_enough_Yon"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "MMMMMMMM"));
		Import.lang.add(
				MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "You_cant_transfer_Yon_to_your_account"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Settings_are_saved"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Start_Download"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "No3"));
		Import.lang
				.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Connection_lost_try_again_in_10s"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Reconnect"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "NewVersionAvailable1"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "NewVersionAvailable2"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Connection_lost_try_again_later"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Okay"));
		Import.lang
				.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Dont_use_symbols_in_your_Password"));
		Import.lang.add(
				MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Please_use_a_Password_under_14_letters"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Download"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Username_or_Password_wrong"));
		Import.lang
				.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "This_account_is_permanently_banned"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "TempBan1"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "TempBan2"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Information"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Warning"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Error"));
		Import.lang
				.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Dont_use_symbols_in_your_username"));
		Import.lang.add(
				MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Please_use_a_username_under_14_letters"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Account_successfully_created"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Username_already_taken"));
		Import.lang
				.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang",
						"You_gained_25_Yon_for_daily_sign_in"));
		Import.lang.add(MetaExpress.getPropertie("BankDemo/lang/" + lang + ".lang", "Thanks_I_take_it"));
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