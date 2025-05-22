package net.BankDemoServer.Modules;

import java.io.DataOutputStream;

import net.Meta.MetaExpress;
import net.Meta.MetaTime;
import net.BankDemoServer.Engine.Server;

public class Transfer {
	public static void TransferYon(DataOutputStream Writer, String FromUsername, String ToUsername, String Amount) {
		if (MetaExpress.isFolderExists("BankDemoServer/Userbank/" + ToUsername)) {
			if (Integer.parseInt(Amount) <= Integer.parseInt(MetaExpress
					.getPropertie("BankDemoServer/Userbank/" + FromUsername + "/properties.user", "Balance"))) {
				MetaExpress.setPropertie("BankDemoServer/Userbank/" + FromUsername + "/properties.user", "Balance",
						(Integer.parseInt(MetaExpress
								.getPropertie("BankDemoServer/Userbank/" + FromUsername + "/properties.user",
										"Balance"))
								- Integer.parseInt(Amount)) + "");
				MetaExpress.setPropertie("BankDemoServer/Userbank/" + ToUsername + "/properties.user", "Balance",
						(Integer.parseInt(MetaExpress
								.getPropertie("BankDemoServer/Userbank/" + ToUsername + "/properties.user", "Balance"))
								+ Integer.parseInt(Amount)) + "");

				MetaExpress.Write("BankDemoServer/Userbank/" + FromUsername + "/TransferHistory.list",
						ToUsername + ";Out;" + Amount + ";" + MetaTime.getDate() + " " + MetaTime.getTime(), false);
				MetaExpress.Write("BankDemoServer/Userbank/" + ToUsername + "/TransferHistory.list",
						FromUsername + ";In;" + Amount + ";" + MetaTime.getDate() + " " + MetaTime.getTime(), false);

				Server.out(Writer, "transfer;Accepted");
			} else {
				Server.out(Writer, "transfer;NotEnough");
			}
		} else {
			Server.out(Writer, "transfer;UserNotFound");
		}
	}
}