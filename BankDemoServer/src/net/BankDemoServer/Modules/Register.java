package net.BankDemoServer.Modules;

import java.io.DataOutputStream;

import net.Meta.MetaExpress;
import net.Meta.MetaTime;
import net.BankDemoServer.Engine.Import;
import net.BankDemoServer.Engine.Server;

public class Register {
	public static boolean SignUp(DataOutputStream Writer, String Username, String Password) {
		if (Username.isEmpty() || Password.isEmpty()) {
			return false;
		} else {
			char[] CharUsername = Username.toCharArray();
			for (char c1 : CharUsername) {
				switch (c1) {
					case 'A':
						break;
					case 'a':
						break;
					case 'B':
						break;
					case 'b':
						break;
					case 'C':
						break;
					case 'c':
						break;
					case 'D':
						break;
					case 'd':
						break;
					case 'E':
						break;
					case 'e':
						break;
					case 'F':
						break;
					case 'f':
						break;
					case 'G':
						break;
					case 'g':
						break;
					case 'H':
						break;
					case 'h':
						break;
					case 'I':
						break;
					case 'i':
						break;
					case 'J':
						break;
					case 'j':
						break;
					case 'K':
						break;
					case 'k':
						break;
					case 'L':
						break;
					case 'l':
						break;
					case 'M':
						break;
					case 'm':
						break;
					case 'N':
						break;
					case 'n':
						break;
					case 'O':
						break;
					case 'o':
						break;
					case 'P':
						break;
					case 'p':
						break;
					case 'Q':
						break;
					case 'q':
						break;
					case 'R':
						break;
					case 'r':
						break;
					case 'S':
						break;
					case 's':
						break;
					case 'T':
						break;
					case 't':
						break;
					case 'U':
						break;
					case 'u':
						break;
					case 'V':
						break;
					case 'v':
						break;
					case 'W':
						break;
					case 'w':
						break;
					case 'X':
						break;
					case 'x':
						break;
					case 'Y':
						break;
					case 'y':
						break;
					case 'Z':
						break;
					case 'z':
						break;
					case '0':
						break;
					case '1':
						break;
					case '2':
						break;
					case '3':
						break;
					case '4':
						break;
					case '5':
						break;
					case '6':
						break;
					case '7':
						break;
					case '8':
						break;
					case '9':
						break;

					default:
						return false;
				}
			}

			char[] CharPassword = Password.toCharArray();
			for (char c : CharPassword) {
				switch (c) {
					case 'A':
						break;
					case 'a':
						break;
					case 'B':
						break;
					case 'b':
						break;
					case 'C':
						break;
					case 'c':
						break;
					case 'D':
						break;
					case 'd':
						break;
					case 'E':
						break;
					case 'e':
						break;
					case 'F':
						break;
					case 'f':
						break;
					case 'G':
						break;
					case 'g':
						break;
					case 'H':
						break;
					case 'h':
						break;
					case 'I':
						break;
					case 'i':
						break;
					case 'J':
						break;
					case 'j':
						break;
					case 'K':
						break;
					case 'k':
						break;
					case 'L':
						break;
					case 'l':
						break;
					case 'M':
						break;
					case 'm':
						break;
					case 'N':
						break;
					case 'n':
						break;
					case 'O':
						break;
					case 'o':
						break;
					case 'P':
						break;
					case 'p':
						break;
					case 'Q':
						break;
					case 'q':
						break;
					case 'R':
						break;
					case 'r':
						break;
					case 'S':
						break;
					case 's':
						break;
					case 'T':
						break;
					case 't':
						break;
					case 'U':
						break;
					case 'u':
						break;
					case 'V':
						break;
					case 'v':
						break;
					case 'W':
						break;
					case 'w':
						break;
					case 'X':
						break;
					case 'x':
						break;
					case 'Y':
						break;
					case 'y':
						break;
					case 'Z':
						break;
					case 'z':
						break;
					case '0':
						break;
					case '1':
						break;
					case '2':
						break;
					case '3':
						break;
					case '4':
						break;
					case '5':
						break;
					case '6':
						break;
					case '7':
						break;
					case '8':
						break;
					case '9':
						break;

					default:
						return false;
				}
			}

			if (CharUsername.length < 14) {
				if (CharPassword.length < 14) {
					if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
						Server.out(Writer, "register;denied");
					} else {
						MetaExpress.CreateFolder("BankDemoServer/Userbank/" + Username);
						MetaExpress.Create("BankDemoServer/Userbank/" + Username + "/TransferHistory.list");
						AddRegisterNumber();

						String Path = ("BankDemoServer/Userbank/" + Username + "/properties.user");

						MetaExpress.Create(Path);
						MetaExpress.setPropertie(Path, "Balance", "0");
						MetaExpress.setPropertie(Path, "LastDailyReward", MetaTime.getDate());
						MetaExpress.setPropertie(Path, "Created", MetaTime.getDate() + " " + MetaTime.getTime());
						MetaExpress.setPropertie(Path, "LastLogin", MetaTime.getDate() + " " + MetaTime.getTime());
						MetaExpress.setPropertie(Path, "Password", Password);
						MetaExpress.setPropertie(Path, "Banned", System.currentTimeMillis() + "");
						MetaExpress.setPropertie(Path, "BanReason", "No Reason");

						Server.out(Writer, "register;accepted");

						return true;
					}
				}
			}
		}

		return false;
	}

	public static void AddRegisterNumber() {
		Import.RegisteredUsers = Integer
				.parseInt(MetaExpress.getPropertie("BankDemoServer/Userbank/list.yml", "Registered"));
		++Import.RegisteredUsers;
		MetaExpress.setPropertie("BankDemoServer/Userbank/list.yml", "Registered", "" + Import.RegisteredUsers);
	}

	public static void SubRegisterNumber() {
		Import.RegisteredUsers = Integer
				.parseInt(MetaExpress.getPropertie("BankDemoServer/Userbank/list.yml", "Registered"));
		--Import.RegisteredUsers;
		MetaExpress.setPropertie("BankDemoServer/Userbank/list.yml", "Registered", "" + Import.RegisteredUsers);
	}
}