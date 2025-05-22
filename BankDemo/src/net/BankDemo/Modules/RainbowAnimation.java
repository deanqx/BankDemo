package net.BankDemo.Modules;

public class RainbowAnimation implements Runnable {
	public static boolean ThreadEnabled = true;

	public void run() {
		int Speed = 4;

		int R = 244;
		int G = 66;
		int B = 66;

		while (ThreadEnabled) {
			R = 244;
			G = 66;
			B = 66;

			for (; G != 244; ++G) {
				try {
					setSelectorColor(R, G, B);
					Thread.sleep(Speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			for (; R != 66; --R) {
				try {
					setSelectorColor(R, G, B);
					Thread.sleep(Speed);
				} catch (InterruptedException e) {
				}
			}

			for (; B != 244; ++B) {
				try {
					setSelectorColor(R, G, B);
					Thread.sleep(Speed);
				} catch (InterruptedException e) {
				}
			}

			for (; G != 66; --G) {
				try {
					setSelectorColor(R, G, B);
					Thread.sleep(Speed);
				} catch (InterruptedException e) {
				}
			}

			for (; R != 244; ++R) {
				try {
					setSelectorColor(R, G, B);
					Thread.sleep(Speed);
				} catch (InterruptedException e) {
				}
			}

			for (; B != 66; --B) {
				try {
					setSelectorColor(R, G, B);
					Thread.sleep(Speed);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void setSelectorColor(int R, int G, int B) {
		// BankDemoFrame.Selected1.setForeground(new Color(R, G, B));
		// BankDemoFrame.Selected1.setBackground(new Color(R, G, B));

		// BankDemoFrame.Selected2.setForeground(new Color(R, G, B));
		// BankDemoFrame.Selected2.setBackground(new Color(R, G, B));

		// BankDemoFrame.Selected3.setForeground(new Color(R, G, B));
		// BankDemoFrame.Selected3.setBackground(new Color(R, G, B));

		// BankDemoFrame.Selected4.setForeground(new Color(R, G, B));
		// BankDemoFrame.Selected4.setBackground(new Color(R, G, B));
	}
}