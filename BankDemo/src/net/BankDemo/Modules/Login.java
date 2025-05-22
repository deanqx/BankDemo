package net.BankDemo.Modules;

import java.awt.Color;

import net.Meta.MetaExpress;
import net.BankDemo.Engine.Import;
import net.BankDemo.Engine.BankDemoFrame;
import net.BankDemo.Engine.ServerConnect;

public class Login implements Runnable {
	public static String Username = "";
	public static String Password = "";

	public void run() {
		if (Username.isEmpty() || Password.isEmpty()) {
			BankDemoFrame.setStatement(Import.lang.get(52));
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
						BankDemoFrame.pwdPasswordLogin.setText("");
						BankDemoFrame.setStatement(Import.lang.get(74));
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
						BankDemoFrame.pwdPasswordLogin.setText("");
						BankDemoFrame.setStatement(Import.lang.get(74));
						return;
				}
			}

			if (CharUsername.length < 14) {
				if (CharPassword.length < 14) {
					ServerConnect.out("login;" + Username + ";" + Password, false);
					String Input = ServerConnect.read();

					switch (Input) {
						case "login;accepted":
							Import.Username = Username;
							Import.Password = Password;

							new Thread(new YonVerifier()).start();

							if (Import.Remember) {
								MetaExpress.setPropertie("BankDemo/account.yml", "Username", Username);
								MetaExpress.setPropertie("BankDemo/account.yml", "Password", Password);
							}
							BankDemoFrame.lblMyUsername.setText(Username);

							int Brightness = 0;
							for (; Brightness != 254; ++Brightness) {
								BankDemoFrame.PanelSwitch.setBackground(new Color(0, 0, 0, Brightness));
								BankDemoFrame.ContentPanel.repaint();

								try {
									Thread.sleep(6);
								} catch (InterruptedException e) {
								}
							}

							BankDemoFrame.ContentPanel.removeAll();
							BankDemoFrame.ContentPanel.add(BankDemoFrame.PanelSwitch);
							BankDemoFrame.MainPanel.setVisible(true);

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							BankDemoFrame.ContentPanel.add(BankDemoFrame.MainPanel);
							BankDemoFrame.MainPanel.setVisible(true);
							BankDemoFrame.ContentPanel.revalidate();

							for (; Brightness != 0; --Brightness) {
								BankDemoFrame.PanelSwitch.setBackground(new Color(0, 0, 0, Brightness));
								BankDemoFrame.ContentPanel.repaint();

								try {
									Thread.sleep(6);
								} catch (InterruptedException e) {
								}
							}

							BankDemoFrame.txtUsernameLogin.setText("");
							BankDemoFrame.pwdPasswordLogin.setText("");
							BankDemoFrame.chckbxRememberLogin.setSelected(false);
							BankDemoFrame.setStatement("");

							YonVerifier.LoginCompleate = true;
							break;
						case "login;denied":
							BankDemoFrame.pwdPasswordLogin.setText("");
							BankDemoFrame.setStatement(Import.lang.get(74));

							break;
						case "login;banned":
							BankDemoFrame.txtUsernameLogin.setText("");
							BankDemoFrame.pwdPasswordLogin.setText("");
							BankDemoFrame.setStatement(Import.lang.get(75));

							break;
						default:
							String[] s = Input.split(";");
							BankDemoFrame.setStatement(
									Import.lang.get(76) + " " + s[2] + Import.lang.get(77) + " " + s[3]);

							break;
					}
				} else {
					BankDemoFrame.pwdPasswordLogin.setText("");
					BankDemoFrame.setStatement(Import.lang.get(74));
				}
			} else {
				BankDemoFrame.pwdPasswordLogin.setText("");
				BankDemoFrame.setStatement(Import.lang.get(74));
			}
		}
	}
}