package net.BankDemo.Modules;

import net.BankDemo.Engine.Import;
import net.BankDemo.Engine.BankDemoFrame;
import net.BankDemo.Engine.ServerConnect;

public class Register {
	public static void SignUp(String Username, String Password, String PasswordConfirmed) {
		if (Username.isEmpty() || Password.isEmpty() || PasswordConfirmed.isEmpty()) {
			BankDemoFrame.setStatement(Import.lang.get(52));
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
						BankDemoFrame.setStatement(Import.lang.get(81));

						return;
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
						BankDemoFrame.setStatement(Import.lang.get(71));
						return;
				}
			}

			if (Password.equals(PasswordConfirmed)) {
				if (CharUsername.length < 14) {
					if (CharPassword.length < 14) {
						ServerConnect.out("register;" + Username + ";" + Password, false);

						String Input = ServerConnect.read();
						if (Input.equals("register;accepted")) {
							BankDemoFrame.ContentPanel.removeAll();
							BankDemoFrame.ContentPanel.add(BankDemoFrame.LoginPanel);
							BankDemoFrame.ContentPanel.revalidate();

							BankDemoFrame.setStatement(Import.lang.get(83));

							BankDemoFrame.txtUsernameLogin.setText("");
							BankDemoFrame.pwdPasswordLogin.setText("");
						} else if (Input.equals("register;denied")) {
							BankDemoFrame.setStatement(Import.lang.get(84));
						}
					} else {
						BankDemoFrame.setStatement(Import.lang.get(82));
					}
				} else {
					BankDemoFrame.setStatement(Import.lang.get(72));
				}
			} else {
				BankDemoFrame.setStatement(Import.lang.get(54));
			}
		}
	}
}