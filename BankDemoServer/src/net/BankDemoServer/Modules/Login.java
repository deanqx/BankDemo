package net.BankDemoServer.Modules;

import java.io.DataOutputStream;

import net.Meta.MetaExpress;
import net.BankDemoServer.Engine.Server;

public class Login {
	public static String[] SignIn(DataOutputStream Writer, String Username, String Password) {
		String[] Return = { "", "", "false" };

		if (Username.isEmpty() || Password.isEmpty()) {
			if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
				Ban.Permban(Username, "Client manipulating");
				Server.out(Writer, "login;banned");
			}
		} else {
			char[] CharUsername = Username.toCharArray();
			for (char c : CharUsername) {
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
						if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
							Ban.Permban(Username, "Client manipulating");
							Server.out(Writer, "login;banned");
						}

						return Return;
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
						if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
							Ban.Permban(Username, "Client manipulating");
							Server.out(Writer, "login;banned");
						}

						return Return;
				}
			}

			if (CharUsername.length < 14) {
				if (CharPassword.length < 14) {
					if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
						if (Password.equals(MetaExpress
								.getPropertie("BankDemoServer/Userbank/" + Username + "/properties.user",
										"Password"))) {
							if (Ban.Checkban(Username).equals("true")) {
								Server.out(Writer, "login;banned");
							} else if (System.currentTimeMillis() >= Long.parseLong(Ban.Checkban(Username))) {
								Server.out(Writer, "login;accepted");

								Return[0] = Username;
								Return[1] = Password;
								Return[2] = "true";

								return Return;
							} else {
								long Days = (Long.parseLong(Ban.Checkban(Username)) - System.currentTimeMillis())
										/ 86400000;
								Server.out(Writer, "login;banned;" + Days + ";" + MetaExpress.getPropertie(
										"BankDemoServer/Userbank/" + Username + "/properties.user", "BanReason"));
							}
						} else {
							Server.out(Writer, "login;denied");
						}
					} else {
						Server.out(Writer, "login;denied");
					}
				} else {
					if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
						Ban.Permban(Username, "Client manipulating");
						Server.out(Writer, "login;banned");
					}
				}
			} else {
				if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + Username)) {
					Ban.Permban(Username, "Client manipulating");
					Server.out(Writer, "login;banned");
				}
			}
		}

		return Return;
	}
}