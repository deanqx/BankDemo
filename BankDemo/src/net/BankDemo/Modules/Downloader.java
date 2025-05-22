package net.BankDemo.Modules;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.Meta.MetaConsole;
import net.Meta.MetaExpress;
import net.BankDemo.Engine.Import;
import net.BankDemo.Engine.ServerConnect;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class Downloader extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Downloader Frame;
	private static JLabel lblProgress;
	private static JProgressBar DownloadProgress;

	private static boolean Cancel = false;
	private static boolean NoRepeat = true;
	private static boolean Started = false;

	private static void StartJFrame() {
		if (NoRepeat) {
			NoRepeat = false;
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Frame = new Downloader();
						Frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public static void StartDownload(String ServerFile, String ToFile) {
		Downloader.StartJFrame();
		MetaConsole.out("-----------------------------", 0, true);

		if (MetaExpress.isExists(ToFile)) {
			MetaExpress.Delete(ToFile);
		}
		MetaExpress.Create(ToFile);

		ServerConnect.out("install;" + ServerFile, true);

		double DoublePercent;
		int Size = Integer.parseInt(ServerConnect.read());

		OutputStream Writer = null;
		try {
			Writer = new FileOutputStream(new File(ToFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (!Started) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Started = false;

		MetaConsole.out("Download " + ServerFile + "...", 0, true);
		for (int Byte = 0; Byte < Size; ++Byte) {
			DoublePercent = Math.round(((Byte * 100.0) / Size) * 100) / 100.0;
			try {
				lblProgress.setText(Import.lang.get(73) + " | " + DoublePercent + "%");
			} catch (Exception e) {
				lblProgress.setText("Download | " + DoublePercent + "%");
			}
			DownloadProgress.setValue((int) Math.round((long) DoublePercent * 100.0));

			if (Cancel) {
				Frame.setVisible(false);
				Frame.dispose();

				MetaConsole.out("Cancel complete!", 0, true);

				Cancel = false;
				break;
			} else {
				try {
					Writer.write(ServerConnect.readBytes()[0]);
				} catch (IOException e) {
					Frame.setVisible(false);
					Frame.dispose();

					MetaConsole.out("Cancel complete!", 0, true);

					Cancel = false;
					break;
				}
				ServerConnect.LocalWaitForResponse = true;
			}
		}
		ServerConnect.LocalWaitForResponse = false;

		Frame.setVisible(false);
		Frame.dispose();

		MetaConsole.out("Download complete!", 0, true);

		try {
			Writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		MetaConsole.out("-----------------------------", 0, true);
	}

	private Downloader() {
		setBackground(new Color(51, 51, 51));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Downloader.class.getResource("/net/BankDemo/Ressourcen/BankDemo Icon.png")));
		setTitle("BankDemo | Downloader");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 158);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setEnabled(true);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MetaConsole.out("Cancel Download...", 1, true);
				Cancel = true;
				btnCancel.setEnabled(false);
			}
		});
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 18));
		btnCancel.setForeground(new Color(204, 204, 204));
		btnCancel.setBackground(new Color(51, 51, 51));
		btnCancel.setBounds(10, 89, 524, 31);
		contentPane.add(btnCancel);

		DownloadProgress = new JProgressBar();
		DownloadProgress.setMaximum(10000);
		DownloadProgress.setBackground(new Color(204, 204, 204));
		DownloadProgress.setForeground(new Color(102, 204, 0));
		DownloadProgress.setBounds(10, 47, 524, 31);
		contentPane.add(DownloadProgress);

		lblProgress = new JLabel("Calculating...");
		lblProgress.setFont(new Font("Arial", Font.PLAIN, 20));
		lblProgress.setForeground(new Color(204, 204, 204));
		lblProgress.setBounds(10, 11, 524, 25);
		contentPane.add(lblProgress);

		Started = true;
	}
}