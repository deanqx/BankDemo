package net.BankDemo.Engine;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import net.Meta.Enable;
import net.Meta.MetaConsole;
import net.BankDemo.Modules.Downloader;
import net.BankDemo.Modules.PopUp;

public class ServerConnect {
	static Socket Server;
	private static DataOutputStream Writer;
	private static DataInputStream Reader;

	public static boolean LocalWaitForResponse = false;

	static void StartSocket() {
		while (true) {
			try {
				Server = new Socket(Import.ServerIP, 57634);
				Writer = new DataOutputStream(Server.getOutputStream());
				Reader = new DataInputStream(Server.getInputStream());
				Import.OfflineMode = false;

				break;
			} catch (IOException e) {
				try {
					Server = new Socket("127.0.0.1", 57634);
					Writer = new DataOutputStream(Server.getOutputStream());
					Reader = new DataInputStream(Server.getInputStream());
					Import.OfflineMode = false;

					break;
				} catch (IOException e1) {
					Import.OfflineMode = true;
					MetaConsole.out(Import.lang.get(65), 2, true);
					if (!PopUp.Show(Import.lang.get(65), 0, true, Import.lang.get(66), Import.lang.get(64))) {
						System.exit(0);
					}
				}
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static void CheckForUpdates() {
		ServerConnect.out("version", false);
		String ActivVersion = ServerConnect.read();
		if (!ActivVersion.equals(Import.Version)) {
			MetaConsole.out("Update available!", 1, true);

			if (PopUp.Show(Import.lang.get(67) + ActivVersion + Import.lang.get(68), 0, true, Import.lang.get(63),
					Import.lang.get(64))) {
				Downloader.StartDownload("BankDemo.jar", "BankDemo - Download.jar");

				try {
					Runtime.getRuntime().exec("java -jar \"" + Import.$Updater + "\" \"" + Enable.LocalFolder + "\"");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			System.exit(0);
		}
	}

	public static void out(String Package, boolean WaitForResponse) {
		while (LocalWaitForResponse) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
			}
		}
		LocalWaitForResponse = WaitForResponse;

		try {
			Writer.writeInt(Package.getBytes().length);
			Writer.write(Package.getBytes());
		} catch (IOException e) {
			MetaConsole.out(Import.lang.get(69), 2, true);
			PopUp.Show(Import.lang.get(69), 0, false, "", Import.lang.get(70));
			System.exit(0);
		}
	}

	public static void outByte(byte Package, boolean WaitForResponse) {
		while (LocalWaitForResponse) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
			}
		}
		LocalWaitForResponse = WaitForResponse;

		try {
			Writer.writeInt(1);
			Writer.write(Package);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read() {
		try {
			int PackageLength = Reader.readInt();
			if (PackageLength > 0) {
				byte[] Package = new byte[PackageLength];

				Reader.readFully(Package, 0, Package.length);
				LocalWaitForResponse = false;
				return new String(Package);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		LocalWaitForResponse = false;
		return "";
	}

	public static byte[] readBytes() {
		try {
			int PackageLength = Reader.readInt();
			if (PackageLength > 0) {
				byte[] Package = new byte[PackageLength];

				Reader.readFully(Package, 0, Package.length);
				LocalWaitForResponse = false;
				return Package;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		LocalWaitForResponse = false;
		return null;
	}
}