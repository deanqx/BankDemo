package net.BankDemoServer.Engine;

import net.Meta.MetaArray;
import net.Meta.MetaConsole;
import net.Meta.MetaExpress;
import net.BankDemoServer.Modules.Ban;

import java.text.DecimalFormat;
import java.util.Scanner;

public class BankConsole implements Runnable {
	Scanner commandSc;

	public void run() {
		while (true) {
			commandSc = new Scanner(System.in);
			String[] CommandSplit = commandSc.nextLine().split(" ");

			if ("help".equals(CommandSplit[0])) {
				MetaConsole.out("- Account Help -", 0, true);
				MetaConsole.out("info [USER]                    > Show infos about the user.", 0, true);
				MetaConsole.out("online                         > Show how much users online.", 0, true);
				MetaConsole.out("online [USER]                  > Show if the user online.", 0, true);
				MetaConsole.out("list                           > Show all registered users of the server.", 0, true);
				MetaConsole.out("ban [USER] [REASON]            > Ban a permanently user.", 0, true);
				MetaConsole.out("tempban [USER] [DAYS] [REASON] > Ban a user temporary.", 0, true);
				MetaConsole.out("unban [USER]                   > Unban a user.", 0, true);
				System.out.println("");

				MetaConsole.out("- Yon Bank System Help -", 0, true);
				MetaConsole.out("balance [USER]      > show account Yon balance.", 0, true);
				MetaConsole.out(
						"set [USER] [AMOUNT] > set the account balance to a amount between ( 0 - 999.999.999 ).", 0,
						true);
				MetaConsole.out("add [USER] [AMOUNT] > add amount to the account balance between ( 0 - 999.999.999 ).",
						0, true);
				System.out.println("");

				MetaConsole.out("- System Help -", 0, true);
				MetaConsole.out("version     > Shows the version of the server.", 0, true);
				MetaConsole.out("reload      > Reload the server.", 0, true);
				MetaConsole.out("restart     > Restarts the server. Can use for updating the Server.", 0, true);

				continue;
			}

			if ("info".equals(CommandSplit[0])) {
				if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + CommandSplit[1])) {
					MetaConsole.out("Name:       " + CommandSplit[1], 0, true);
					MetaConsole.out(
							"Password:   " + MetaExpress.getPropertie(
									"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "Password"),
							0, true);

					if (Ban.Checkban(CommandSplit[1]).equals("true")) {
						MetaConsole.out("Ban Level:  PERMANENTLY", 0, true);
					} else if (System.currentTimeMillis() >= Long.parseLong(Ban.Checkban(CommandSplit[1]))) {
						MetaConsole.out("Ban Level:  NOT BANNED", 0, true);
					} else {
						long Days = ((Long.parseLong(Ban.Checkban(CommandSplit[1])) - System.currentTimeMillis())
								/ 86400000);
						MetaConsole.out("Ban Level:  TEMPORARY <-> Days left: " + Days + " <-> Reason: ( "
								+ MetaExpress.getPropertie(
										"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "BanReason")
								+ " )", 0, true);
					}

					MetaConsole.out(
							"Last Login: " + MetaExpress.getPropertie(
									"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "LastLogin"),
							0, true);
					MetaConsole.out(
							"Created:    " + MetaExpress.getPropertie(
									"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "Created"),
							0, true);
				} else {
					MetaConsole.out("Account not found ( " + CommandSplit[1] + " ).", 1, true);
				}

				continue;
			}

			if ("ban".equals(CommandSplit[0])) {
				if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + CommandSplit[1])) {
					Ban.Permban(CommandSplit[1], CommandSplit[2]);
					MetaConsole.out(
							"Account ( " + CommandSplit[1] + " ) is banned for Reason ( " + CommandSplit[2] + " ).", 0,
							true);
				} else {
					MetaConsole.out("Account not found ( " + CommandSplit[1] + " ).", 1, true);
				}

				continue;
			}

			if ("unban".equals(CommandSplit[0])) {
				if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + CommandSplit[1])) {
					Ban.Unban(CommandSplit[1]);
					MetaConsole.out("Account ( " + CommandSplit[1] + " ) is unbanned.", 0, true);
				} else {
					MetaConsole.out("Account not found ( " + CommandSplit[1] + " ).", 1, true);
				}

				continue;
			}

			if ("tempban".equals(CommandSplit[0])) {
				if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + CommandSplit[1])) {
					Ban.Tempban(CommandSplit[1], Integer.parseInt(CommandSplit[2]), CommandSplit[3]);
					MetaConsole.out("Account ( " + CommandSplit[1] + " ) is temporary banned for " + CommandSplit[2]
							+ " Day(s) and for reason ( " + CommandSplit[3] + " ).", 0, true);
				} else {
					MetaConsole.out("Account not found ( " + CommandSplit[1] + " ).", 1, true);
				}

				continue;
			}

			if ("online".equals(CommandSplit[0])) {
				if (CommandSplit.length == 2) {
					if (MetaArray.isExists(CommandSplit[1])) {
						MetaConsole.out("User ( " + CommandSplit[1] + " ) is Online.", 0, true);
					} else {
						MetaConsole.out(
								"User ( " + CommandSplit[1] + " ) is Offline. Last login: " + MetaExpress.getPropertie(
										"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "LastLogin"),
								0, true);
					}
				} else {
					MetaConsole.out("Online users: " + Import.OnlineUsers, 0, true);
				}

				continue;
			}

			if ("list".equals(CommandSplit[0])) {
				MetaConsole.out("Registered users: " + Import.RegisteredUsers, 0, true);

				continue;
			}

			if ("version".equals(CommandSplit[0])) {
				MetaConsole.out("Version: " + Import.Version, 0, true);

				continue;
			}

			if ("balance".equals(CommandSplit[0])) {
				MetaConsole.out(
						CommandSplit[1] + "'s balance is "
								+ new DecimalFormat().format(Integer.parseInt(MetaExpress.getPropertie(
										"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "Balance"))),
						0, true);

				continue;
			}

			if ("set".equals(CommandSplit[0])) {
				if (Long.parseLong(CommandSplit[2]) <= 999999999 && Long.parseLong(CommandSplit[2]) >= 0) {
					MetaConsole.out(CommandSplit[1] + "'s balance setted from "
							+ new DecimalFormat().format(Integer.parseInt(MetaExpress.getPropertie(
									"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "Balance")))
							+ " to " + CommandSplit[2], 0, true);
					MetaExpress.setPropertie("BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user",
							"Balance",
							CommandSplit[2]);
				} else {
					MetaConsole.out("To large amount! Use amount in range ( 0 - 999.999.999 ).", 2, true);
				}

				continue;
			}

			if ("add".equals(CommandSplit[0])) {
				if (Long.parseLong(CommandSplit[2]) <= 999999999 && Long.parseLong(CommandSplit[2]) >= 0) {
					int NewBalance = (Integer
							.parseInt(MetaExpress.getPropertie(
									"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "Balance"))
							+ Integer.parseInt(CommandSplit[2]));
					MetaExpress.setPropertie("BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user",
							"Balance",
							NewBalance + "");
					MetaConsole.out(new DecimalFormat().format(Integer.parseInt(CommandSplit[2]))
							+ " added to the Account. " + CommandSplit[1] + "'s balance is now "
							+ new DecimalFormat().format(Integer.parseInt(MetaExpress.getPropertie(
									"BankDemoServer/Userbank/" + CommandSplit[1] + "/properties.user", "Balance")))
							+ ".", 0, true);
				} else {
					MetaConsole.out("To large amount! Use amount in range ( 0 - 999.999.999 ).", 2, true);
				}

				continue;
			}

			if ("reload".equals(CommandSplit[0])) {
				Main.Loading();
				MetaConsole.out("Config Reloaded.", 0, true);

				continue;
			}

			if ("restart".equals(CommandSplit[0])) {
				System.exit(0);
			}

			MetaConsole.out("Unknown command or Syntax, type ( help ) for the commandlist!", 1, true);
		}
	}
}