package net.BankDemoServer.Modules;

import net.Meta.MetaExpress;

public class Ban {
	public static void Permban(String Username, String Reason) {
		MetaExpress.setPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "Banned", "true");
		MetaExpress.setPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "BanReason", Reason);
	}

	public static void Tempban(String Username, int Days, String Reason) {
		long Millis = ((86400000 * Days) + System.currentTimeMillis());
		MetaExpress.setPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "Banned", Millis + "");
		MetaExpress.setPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "BanReason", Reason);
	}

	public static String Checkban(String Username) {
		String Info = MetaExpress.getPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "Banned");
		if (Info.equals("true")) {
			return "true";
		} else {
			return Long.parseLong(Info) + "";
		}
	}

	public static void Unban(String Username) {
		MetaExpress.setPropertie("BankDemoServer/Userbank/" + Username + "/properties.user", "Banned",
				System.currentTimeMillis() + "");
	}
}