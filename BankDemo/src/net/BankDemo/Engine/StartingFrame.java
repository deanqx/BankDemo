package net.BankDemo.Engine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.Dimension;

public class StartingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static StartingFrame Frame;
	public static boolean Started = false;

	private JPanel ContentPanel;
	private JLabel lblIcon;

	public static void StartJFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame = new StartingFrame();
					Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartingFrame() {
		setType(Type.UTILITY);
		setMinimumSize(new Dimension(520, 520));
		setAlwaysOnTop(true);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));
		setLocationRelativeTo(null);

		ContentPanel = new JPanel();
		ContentPanel.setBackground(new Color(0, 0, 0, 0));
		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPanel);
		ContentPanel.setLayout(null);

		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(StartingFrame.class.getResource("/BankDemo Icon.png")));
		lblIcon.setBounds(10, 11, 500, 500);
		ContentPanel.add(lblIcon);

		Started = true;
	}

	public void Close() {
		ContentPanel.setVisible(false);
		lblIcon.setVisible(false);
		Frame.setVisible(false);
		Frame.dispose();
	}
}