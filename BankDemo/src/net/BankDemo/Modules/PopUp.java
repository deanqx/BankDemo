package net.BankDemo.Modules;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import net.BankDemo.Engine.Import;
import java.awt.Dimension;

public class PopUp extends JFrame {
	private static final long serialVersionUID = 2L;
	static int Selected = 0;
	public static boolean SetupMode = true;

	static JButton btnButton2 = new JButton();
	static JLabel lblInformation = new JLabel();
	static JTextPane txtrText = new JTextPane();
	static JButton btnButton1 = new JButton();

	public PopUp() {
		setType(Type.UTILITY);
		setMinimumSize(new Dimension(450, 200));
		setUndecorated(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();

		JPanel ContentPanel = new JPanel();
		ContentPanel.setBackground(new Color(65, 65, 65));
		ContentPanel.setBorder(new LineBorder(new Color(153, 153, 153)));
		setContentPane(ContentPanel);

		btnButton2.setBounds(235, 155, 204, 33);
		btnButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Selected = 2;
			}
		});
		btnButton2.setFont(new Font("Arial", Font.PLAIN, 17));
		btnButton2.setForeground(new Color(204, 204, 204));
		btnButton2.setBackground(new Color(51, 51, 51));

		lblInformation.setBounds(11, 12, 428, 34);
		lblInformation.setBackground(new Color(51, 51, 51));
		lblInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformation.setFont(new Font("Arial", Font.PLAIN, 22));
		lblInformation.setForeground(new Color(255, 255, 255));

		JScrollPane scrlPopup = new JScrollPane();
		scrlPopup.setBounds(11, 57, 428, 88);
		scrlPopup.setBorder(null);
		scrlPopup.setViewportBorder(null);

		btnButton1.setBounds(11, 155, 206, 33);
		btnButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Selected = 1;
			}
		});
		btnButton1.setForeground(new Color(204, 204, 204));
		btnButton1.setFont(new Font("Arial", Font.PLAIN, 17));
		btnButton1.setBackground(SystemColor.desktop);
		ContentPanel.setLayout(null);

		txtrText.setFont(new Font("Arial", Font.PLAIN, 15));
		txtrText.setBackground(new Color(65, 65, 65));
		txtrText.setForeground(new Color(255, 255, 255));
		txtrText.setEditable(false);
		scrlPopup.setViewportView(txtrText);
		ContentPanel.add(scrlPopup);
		ContentPanel.add(lblInformation);
		ContentPanel.add(btnButton1);
		ContentPanel.add(btnButton2);
	}

	public static boolean Show(String Text, int Mode, boolean TwoOptionsMode, String Button1Text, String Button2Text) {
		try {
			PopUp frame = new PopUp();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (SetupMode) {
			switch (Mode) {
				case 0:
					// Icon List:
					// https://stackoverflow.com/questions/12394608/java-uimanager-key-list
					lblInformation.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
					lblInformation.setText("Information");
					break;
				case 1:
					lblInformation.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
					lblInformation.setText("Warning");
					break;
				case 2:
					lblInformation.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
					lblInformation.setText("Error");
					break;
			}
		} else {
			switch (Mode) {
				case 0:
					lblInformation.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
					lblInformation.setText(Import.lang.get(78));
					break;
				case 1:
					lblInformation.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
					lblInformation.setText(Import.lang.get(79));
					break;
				case 2:
					lblInformation.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
					lblInformation.setText(Import.lang.get(80));
					break;
			}
		}

		txtrText.setText(Text);
		btnButton2.setText(Button2Text);

		if (TwoOptionsMode) {
			btnButton1.setText(Button1Text);
			while (Selected == 0) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
				}
			}

			if (Selected == 1) {
				Selected = 0;
				return true;
			} else {
				Selected = 0;
				return false;
			}
		} else {
			btnButton1.setVisible(false);

			while (Selected == 0) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
				}
			}

			Selected = 0;
			return false;
		}
	}
}