import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if (new File(args[0] + "BankDemo - Download.jar").exists()) {
			new File(args[0] + "BankDemo.jar").delete();
			new File(args[0] + "BankDemo - Download.jar").renameTo(new File(args[0] + "BankDemo.jar"));
		}

		try {
			Runtime.getRuntime().exec("java -jar \"" + args[0] + "BankDemo.jar\"");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.exit(0);
	}
}