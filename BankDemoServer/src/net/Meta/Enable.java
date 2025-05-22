package net.Meta;

import java.io.File;

public class Enable {
	public static boolean LogMode = false;
	static String LogFolder = "logs";
	static boolean FullLogging = false;
	public static String LocalFolder = "";

	public static void Setup(boolean setLogMode, String setLogFolder, boolean setFullLogging) {
		LogMode = setLogMode;
		LogFolder = setLogFolder;
		FullLogging = setFullLogging;

		LocalFolder = System.getProperty("user.dir");
		char[] LocalFolderChar = LocalFolder.toCharArray();
		for (int Char = 0; Char < LocalFolderChar.length; ++Char) {
			if (LocalFolderChar[Char] == '\\') {
				LocalFolderChar[Char] = '/';
			}
		}
		LocalFolder = new String(LocalFolderChar) + "/";
	}
}