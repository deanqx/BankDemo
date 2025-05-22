package net.BankDemo.Modules;

import net.BankDemo.Engine.Import;
import net.BankDemo.Engine.BankDemoFrame;
import net.BankDemo.Engine.ServerConnect;

public class ChangePassword {
	public static void CPassword(String Password) {
		char[] CharPassword = Password.toCharArray();
		for (char c1 : CharPassword) {
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
					BankDemoFrame.lblState.setText(Import.lang.get(71));
					return;
			}
		}

		if (CharPassword.length < 14) {
			ServerConnect.out("editpassword;" + Import.Password + ";" + Password, false);
			Import.Password = Password;
		} else {
			BankDemoFrame.lblState.setText(Import.lang.get(72));
		}
	}
}