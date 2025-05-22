package net.BankDemoServer.Engine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import net.Meta.MetaArray;
import net.Meta.MetaConsole;
import net.Meta.MetaExpress;
import net.Meta.MetaTime;
import net.BankDemoServer.Modules.Ban;
import net.BankDemoServer.Modules.ChangePassword;
import net.BankDemoServer.Modules.Login;
import net.BankDemoServer.Modules.Register;
import net.BankDemoServer.Modules.Transfer;

public class Server implements Runnable {
	static boolean ThreadReady = true;
	private static ServerSocket Socket;
	static Socket sClient;

	static void Start() {
		try {
			Socket = new ServerSocket(57634);
			MetaConsole.out("Hosting on Port: " + 57634, 0, true);
			new Thread(new Server()).start();
		} catch (IOException e) {
			MetaConsole.out("Server is already started.", 2, true);
			System.exit(0);
		}
	}

	public void run() {
		while (true) {
			if (ThreadReady) {
				ThreadReady = false;

				try {
					sClient = Socket.accept();
					new Thread(new ClientListener()).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean out(DataOutputStream Writer, String Package) {
		try {
			Writer.writeInt(Package.getBytes().length);
			Writer.write(Package.getBytes());
		} catch (IOException e) {
			--Import.OnlineUsers;
			MetaConsole.out(Writer + " Disconnected", 0, Import.FullClientLogging);

			return true;
		}

		return false;
	}

	static boolean outByte(DataOutputStream Writer, byte Byte) {
		try {
			Writer.writeInt(1);
			Writer.write(Byte);
		} catch (IOException e) {
			--Import.OnlineUsers;
			MetaConsole.out(Writer + " Disconnected", 0, Import.FullClientLogging);

			return true;
		}

		return false;
	}
}

class ClientListener implements Runnable {
	public void run() {
		Socket Client = Server.sClient;
		Server.ThreadReady = true;

		++Import.OnlineUsers;
		MetaConsole.out(Client + " Connected", 0, Import.FullClientLogging);

		int PackageLength = 0;
		DataInputStream Reader = null;
		DataOutputStream Writer = null;
		try {
			Writer = new DataOutputStream(Client.getOutputStream());
			Reader = new DataInputStream(Client.getInputStream());
		} catch (IOException e1) {
			--Import.OnlineUsers;
			MetaConsole.out(Client + " Disconnected", 0, Import.FullClientLogging);
		}

		boolean LoggedIn = false;
		String Username = "";
		String Password = "";

		String ImputFromClient = "";
		String[] InputSplit;

		while (true) {
			if (LoggedIn) {
				if (!MetaArray.isExists(Username)) {
					MetaArray.add(Username, "");
				}

				if (Ban.Checkban(Username).equals("true")
						|| System.currentTimeMillis() <= Long.parseLong(Ban.Checkban(Username))) {
					try {
						Client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				assert Reader != null;
				PackageLength = Reader.readInt();
				if (PackageLength > 0) {
					byte[] Package = new byte[PackageLength];

					Reader.readFully(Package, 0, Package.length);
					ImputFromClient = new String(Package);
				}
			} catch (IOException e) {
				break;
			}

			if (ImputFromClient.equals("version")) {
				Server.out(Writer, Import.Version);

				continue;
			}

			if (ImputFromClient.equals("news")) {
				Server.out(Writer, Import.News);

				continue;
			}

			if (ImputFromClient.equals("logout")) {
				LoggedIn = false;
				Username = "";
				Password = "";

				MetaArray.remove(Username);

				continue;
			}

			InputSplit = ImputFromClient.split(";");

			if (InputSplit[0].equals("balance")) {
				if (LoggedIn && InputSplit[1].equals(Username) && InputSplit[2].equals(Password)) {
					String Path = ("BankDemoServer/Userbank/" + Username + "/properties.user");

					if (MetaTime.getDate().equals(MetaExpress.getPropertie(Path, "LastDailyReward"))) {
						Server.out(Writer, MetaExpress.getPropertie(Path, "Balance") + ";false");
					} else {
						int BonusBalance = (Integer.parseInt(MetaExpress.getPropertie(Path, "Balance")) + 25);

						MetaExpress.setPropertie(Path, "Balance", BonusBalance + "");
						MetaExpress.Write("BankDemoServer/Userbank/" + Username + "/TransferHistory.list",
								"++Server;In;25 Daily Reward;" + MetaTime.getDate() + " " + MetaTime.getTime(), false);
						MetaExpress.setPropertie(Path, "LastDailyReward", MetaTime.getDate());
						Server.out(Writer, BonusBalance + ";true");
					}
				}

				continue;
			}

			if (InputSplit[0].equals("getHistory")) {
				if (LoggedIn && InputSplit[1].equals(Username) && InputSplit[2].equals(Password)) {
					String Path = "BankDemoServer/Userbank/" + Username + "/TransferHistory.list";
					List<String> ReadHistory = MetaExpress.Read(Path);
					String History = "";

					if (14 > ReadHistory.size()) {
						for (int x = 0; x != ReadHistory.size(); ++x) {
							History = ReadHistory.get(x) + "&" + History;
						}
					} else {
						MetaExpress.Delete(Path);
						MetaExpress.Create(Path);

						for (int x = 13; x >= 0; --x) {
							MetaExpress.Write(Path, ReadHistory.get(x), false);
						}

						List<String> NewReadHistory = MetaExpress.Read(Path);
						int sizeNewReadHistory = NewReadHistory.size();
						History = NewReadHistory.get(0);

						for (int x = 1; x < sizeNewReadHistory; ++x) {
							History = History + "&" + NewReadHistory.get(x);
						}
					}

					Server.out(Writer, History);
				}

				continue;
			}

			if (InputSplit[0].equals("transfer")) {
				if (LoggedIn) {
					if (Username.equals(InputSplit[1])) {
						Server.out(Writer, "transfer;SameUser");
					} else {
						Transfer.TransferYon(Writer, Username, InputSplit[1], InputSplit[2]);
					}
				}

				continue;
			}

			if (InputSplit[0].equals("install")) {
				switch (InputSplit[1]) {
					case "BankDemo.jar":
						Server.out(Writer, Import.BankDemoFile.length + "");
						for (int Letter = 0; Letter < Import.BankDemoFile.length; ++Letter) {
							if (Server.outByte(Writer, Import.BankDemoFile[Letter])) {
								break;
							}
						}

						continue;

					case "Updater.jar":
						Server.out(Writer, Import.UpdaterFile.length + "");
						for (int Letter = 0; Letter < Import.UpdaterFile.length; ++Letter) {
							if (Server.outByte(Writer, Import.UpdaterFile[Letter])) {
								break;
							}
						}

						continue;
				}

			}

			if (InputSplit[0].equals("login")) {
				String[] LoginArray = Login.SignIn(Writer, InputSplit[1], InputSplit[2]);
				if (LoginArray[2].equals("true")) {
					LoggedIn = true;
					Username = LoginArray[0];
					Password = LoginArray[1];

					MetaExpress.setPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "LastLogin",
							MetaTime.getDate() + " " + MetaTime.getTime());
				}

				continue;
			}

			if (InputSplit[0].equals("register")) {
				if (Register.SignUp(Writer, InputSplit[1], InputSplit[2])) {
					Register.AddRegisterNumber();
					Register.SubRegisterNumber();
				}
			}

			if (InputSplit[0].equals("editpassword")) {
				if (InputSplit[1].equals(Password)) {
					if (ChangePassword.CPassword(Username, InputSplit[2])) {
						Password = InputSplit[2];
					}
				}

				continue;
			}

			if (InputSplit[0].equals("deleteacc")) {
				if (InputSplit[1].equals(Username) && InputSplit[2].equals(Password)) {
					MetaConsole.out("sas", 0, true);

					MetaExpress.DeleteFolder("BankDemoServer/Userbank/" + Username);
					Register.SubRegisterNumber();

					LoggedIn = false;
					Username = "";
					Password = "";
				}

				continue;
			}

			if (LoggedIn) {
				Ban.Tempban(Username, 14, "Client manipulating");
			}
		}

		if (LoggedIn) {
			MetaArray.remove(Username);
		}

		--Import.OnlineUsers;
		MetaConsole.out(Client + " Disconnected", 0, Import.FullClientLogging);

		try {
			Reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}