package net.BankDemo.Modules;

import net.Meta.MetaExpress;
import net.BankDemo.Engine.Import;
import net.BankDemo.Engine.BankDemoFrame;
import net.BankDemo.Engine.ServerConnect;

public class DeleteAccount implements Runnable {
	public void run() {
		if (PopUp.Show(Import.lang.get(50), 1, true, Import.lang.get(45), Import.lang.get(48))) {
			if (PopUp.Show(Import.lang.get(51), 1, true, Import.lang.get(46), Import.lang.get(49))) {
				ServerConnect.out("deleteacc;" + Import.Username + ";" + Import.Password, false);

				MetaExpress.setPropertie(Import.$account, "Remember", "false");
				MetaExpress.setPropertie(Import.$account, "Username", "");
				MetaExpress.setPropertie(Import.$account, "Password", "");

				Import.Username = "";
				Import.Password = "";

				BankDemoFrame.ContentPanel.removeAll();
				BankDemoFrame.ContentPanel.add(BankDemoFrame.LoginPanel);
				BankDemoFrame.ContentPanel.revalidate();
			}
		}
	}
}