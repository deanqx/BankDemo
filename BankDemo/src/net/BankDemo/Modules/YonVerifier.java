package net.BankDemo.Modules;

import java.text.DecimalFormat;

import net.BankDemo.Engine.Import;
import net.BankDemo.Engine.BankDemoFrame;
import net.BankDemo.Engine.ServerConnect;

public class YonVerifier implements Runnable {
	public static boolean ThreadEnabled = true;
	public static boolean LoginCompleate = false;

	String SplitInput[];

	public void run() {
		while (ThreadEnabled) {
			ServerConnect.out("balance;" + Import.Username + ";" + Import.Password, true);
			SplitInput = ServerConnect.read().split(";");
			Import.Balance = Integer.parseInt(SplitInput[0]);
			BankDemoFrame.lblBalanceMain.setText(new DecimalFormat().format(Import.Balance));
			BankDemoFrame.lblBalanceTransfer.setText(new DecimalFormat().format(Import.Balance));
			BankDemoFrame.lblBalanceSettings.setText(new DecimalFormat().format(Import.Balance));

			if (SplitInput[1].equals("true")) {
				while (!LoginCompleate) {
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
					}
				}

				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				PopUp.Show(Import.lang.get(85), 0, false, "", Import.lang.get(86));
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}