package net.BankDemo.Engine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.Meta.Enable;
import net.Meta.MetaExpress;
import net.BankDemo.Modules.Login;
import net.BankDemo.Modules.Register;
import net.BankDemo.Modules.DeleteAccount;
import net.BankDemo.Modules.YonVerifier;
import net.BankDemo.Modules.ChangePassword;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BankDemoFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static String getNews = "";

	public static JPanel ContentPanel;
	public static JPanel PanelSwitch;
	public static JPanel LoginPanel;
	public static JPanel RegisterPanel;
	public static JPanel MainPanel;
	public static JPanel SettingsPanel;
	public static JPanel TransferPanel;

	public static JPanel SettingsCardLayout;
	public static JPanel MyAccountPanel;
	public static JPanel CPasswordPanel;
	public static JPanel GeneralPanel;
	public static JPanel NewsPanel;
	public static JPanel CreditsPanel;
	public static JPanel PatchLogPanel;

	private static JTextPane txtpnStatementLogin;
	public static JTextField txtUsernameLogin;
	public static JPasswordField pwdPasswordLogin;
	private JTextField txtUsernameRegister;
	private JPasswordField pwdPasswordConfirmRegister;
	private JPasswordField pwdPasswordRegister;
	private static JTextPane txtpnStatementRegister;
	public static JLabel lblBalanceMain;
	public static JCheckBox chckbxRememberLogin;

	private JSeparator SeparatorSettingsLeft;
	private JLabel lblCopyright;
	public static JLabel lblMyUsername;
	public static JLabel lblBalanceSettings;
	private static JComboBox<String> cbbLanguage = new JComboBox<String>();

	private JButton btnMyAccount;
	private JButton btnGeneral;
	private JButton btnNews;
	private JButton btnCredits;
	private JButton btnPatchLog;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmPassword;
	private JPasswordField pwdOldPassword;
	public static JLabel lblStatusTransfer;
	public static JButton btnRefresh;
	public static JButton btnTransfer;
	private static JTextPane txtpnTransferHistory;
	public static JLabel lblState;
	public static JLabel lblBalanceTransfer;

	private static JButton btnLogModeED = new JButton("Enabled");
	private static boolean LogModeED = Enable.LogMode;
	private static JButton btnServerED = new JButton("Disabled");
	private static boolean ServerED = false;
	static JTextField txtTransferTo;
	static JSpinner sprTransferAmount;

	static void StartJFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankDemoFrame Visibility = new BankDemoFrame();
					Visibility.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BankDemoFrame() {
		setResizable(false);
		setMinimumSize(new Dimension(1000, 625));
		setIconImage(Toolkit.getDefaultToolkit().getImage(BankDemoFrame.class.getResource("/BankDemo Icon.png")));
		setFocusable(true);
		setTitle("BankDemo");
		setBackground(new Color(51, 51, 51));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();

		ContentPanel = new JPanel();
		ContentPanel.setBackground(new Color(51, 51, 51));
		ContentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(ContentPanel);
		ContentPanel.setLayout(new CardLayout(0, 0));

		PanelSwitch = new JPanel();
		PanelSwitch.setBackground(new Color(0, 0, 0, 0));

		LoginPanel = new JPanel();
		LoginPanel.setBackground(new Color(51, 51, 51));
		LoginPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login.Username = txtUsernameLogin.getText();
					Login.Password = new String(pwdPasswordLogin.getPassword());
					new Thread(new Login()).start();
				}
			}
		});

		ContentPanel.removeAll();
		ContentPanel.add(PanelSwitch);
		PanelSwitch.setVisible(true);
		ContentPanel.add(LoginPanel);
		LoginPanel.setVisible(true);
		ContentPanel.revalidate();

		JLabel lblLogin = new JLabel(Import.lang.get(2));
		lblLogin.setBounds(500, 135, 494, 49);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(new Color(204, 204, 204));
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 40));

		JLabel lblInfoLogin = new JLabel(Import.Copyright + "  |  " + "v" + Import.Version);
		lblInfoLogin.setBounds(500, 575, 494, 21);
		lblInfoLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoLogin.setForeground(new Color(204, 204, 204));
		lblInfoLogin.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblUsernameLogin = new JLabel(Import.lang.get(3));
		lblUsernameLogin.setBounds(590, 207, 315, 21);
		lblUsernameLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsernameLogin.setForeground(new Color(204, 204, 204));
		lblUsernameLogin.setFont(new Font("Arial", Font.PLAIN, 17));

		txtUsernameLogin = new JTextField("");
		txtUsernameLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login.Username = txtUsernameLogin.getText();
					Login.Password = new String(pwdPasswordLogin.getPassword());
					new Thread(new Login()).start();
				}
			}
		});
		txtUsernameLogin.setBounds(590, 227, 315, 31);
		if (Import.Remember) {
			txtUsernameLogin.setText(MetaExpress.getPropertie("BankDemo/account.yml", "Username"));
		}
		txtUsernameLogin.setBackground(new Color(51, 51, 51));
		txtUsernameLogin.setForeground(new Color(204, 204, 204));
		txtUsernameLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		txtUsernameLogin.setColumns(10);

		JLabel lblPasswordLogin = new JLabel(Import.lang.get(4));
		lblPasswordLogin.setBounds(590, 269, 315, 21);
		lblPasswordLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasswordLogin.setForeground(new Color(204, 204, 204));
		lblPasswordLogin.setFont(new Font("Arial", Font.PLAIN, 17));

		pwdPasswordLogin = new JPasswordField("");
		pwdPasswordLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login.Username = txtUsernameLogin.getText();
					Login.Password = new String(pwdPasswordLogin.getPassword());
					new Thread(new Login()).start();
				}
			}
		});
		pwdPasswordLogin.setBounds(590, 289, 315, 31);
		if (Import.Remember) {
			pwdPasswordLogin.setText(MetaExpress.getPropertie("BankDemo/account.yml", "Password"));
		}
		pwdPasswordLogin.setBackground(new Color(51, 51, 51));
		pwdPasswordLogin.setForeground(new Color(204, 204, 204));
		pwdPasswordLogin.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton btnSignInLogin = new JButton(Import.lang.get(6));
		btnSignInLogin.setBounds(730, 330, 175, 35);
		btnSignInLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnSignInLogin);

				Login.Username = txtUsernameLogin.getText();
				Login.Password = new String(pwdPasswordLogin.getPassword());
				new Thread(new Login()).start();
			}
		});
		btnSignInLogin.setBackground(new Color(51, 51, 51));
		btnSignInLogin.setForeground(new Color(204, 204, 204));
		btnSignInLogin.setFont(new Font("Arial", Font.PLAIN, 20));

		JButton btnCreateAccountLogin = new JButton(Import.lang.get(7));
		btnCreateAccountLogin.setBounds(590, 376, 315, 35);
		btnCreateAccountLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRootPane().setDefaultButton(btnCreateAccountLogin);

				ContentPanel.removeAll();
				ContentPanel.add(RegisterPanel);
				ContentPanel.revalidate();

			}
		});
		btnCreateAccountLogin.setForeground(new Color(204, 204, 204));
		btnCreateAccountLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCreateAccountLogin.setBackground(new Color(51, 51, 51));

		JSeparator Separator1Login = new JSeparator();
		Separator1Login.setBounds(500, 0, 2, 596);
		Separator1Login.setBackground(new Color(204, 204, 204));
		Separator1Login.setOrientation(SwingConstants.VERTICAL);
		Separator1Login.setForeground(new Color(204, 204, 204));

		chckbxRememberLogin = new JCheckBox(Import.lang.get(5));
		chckbxRememberLogin.setBounds(590, 330, 134, 35);
		if (Import.Remember) {
			chckbxRememberLogin.setSelected(true);
		} else {
			chckbxRememberLogin.setSelected(false);
		}
		chckbxRememberLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxRememberLogin.isSelected()) {
					Import.Remember = true;

					MetaExpress.setPropertie("BankDemo/account.yml", "Remember", "true");
				} else {
					Import.Remember = false;

					MetaExpress.setPropertie("BankDemo/account.yml", "Remember", "false");
					MetaExpress.setPropertie("BankDemo/account.yml", "Username", "");
					MetaExpress.setPropertie("BankDemo/account.yml", "Password", "");
				}
			}
		});
		chckbxRememberLogin.setToolTipText(Import.lang.get(44));
		chckbxRememberLogin.setFont(new Font("Arial", Font.PLAIN, 17));
		chckbxRememberLogin.setForeground(new Color(204, 204, 204));
		chckbxRememberLogin.setBackground(new Color(51, 51, 51));

		txtpnStatementLogin = new JTextPane();
		txtpnStatementLogin.setBounds(590, 435, 315, 129);
		txtpnStatementLogin.setForeground(new Color(204, 204, 204));
		txtpnStatementLogin.setBackground(new Color(51, 51, 51));
		txtpnStatementLogin.setFont(new Font("Arial", Font.PLAIN, 18));
		txtpnStatementLogin.setEditable(false);
		txtpnStatementLogin.setText("");

		JSeparator Separator2Login = new JSeparator();
		Separator2Login.setBounds(590, 422, 315, 2);
		Separator2Login.setForeground(new Color(204, 204, 204));
		Separator2Login.setBackground(new Color(204, 204, 204));

		JLabel lblNewslogin = new JLabel(Import.lang.get(1));
		lblNewslogin.setBounds(0, 11, 500, 49);
		lblNewslogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewslogin.setForeground(new Color(204, 204, 204));
		lblNewslogin.setFont(new Font("Arial", Font.PLAIN, 40));

		JScrollPane scrlNewsLogin = new JScrollPane();
		scrlNewsLogin.setBounds(10, 71, 480, 514);
		scrlNewsLogin.setBorder(null);
		scrlNewsLogin.setViewportBorder(null);

		JTextArea txtrNewslogin = new JTextArea("");
		txtrNewslogin.setText(getNews);
		txtrNewslogin.setForeground(new Color(204, 204, 204));
		txtrNewslogin.setBackground(new Color(51, 51, 51));
		txtrNewslogin.setEditable(false);
		txtrNewslogin.setFont(new Font("Arial", Font.PLAIN, 19));
		scrlNewsLogin.setViewportView(txtrNewslogin);
		LoginPanel.setLayout(null);
		LoginPanel.add(lblNewslogin);
		LoginPanel.add(scrlNewsLogin);
		LoginPanel.add(chckbxRememberLogin);
		LoginPanel.add(lblPasswordLogin);
		LoginPanel.add(txtpnStatementLogin);
		LoginPanel.add(lblLogin);
		LoginPanel.add(btnCreateAccountLogin);
		LoginPanel.add(Separator2Login);
		LoginPanel.add(txtUsernameLogin);
		LoginPanel.add(btnSignInLogin);
		LoginPanel.add(lblInfoLogin);
		LoginPanel.add(lblUsernameLogin);
		LoginPanel.add(pwdPasswordLogin);
		LoginPanel.add(Separator1Login);

		RegisterPanel = new JPanel();
		RegisterPanel.setBackground(SystemColor.desktop);
		ContentPanel.add(RegisterPanel, "name_347813980400528");

		JLabel lblRegister = new JLabel(Import.lang.get(8));
		lblRegister.setBounds(500, 85, 494, 49);
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(new Color(204, 204, 204));
		lblRegister.setFont(new Font("Arial", Font.PLAIN, 40));

		JLabel lblUsernameRegister = new JLabel(Import.lang.get(9));
		lblUsernameRegister.setBounds(590, 145, 315, 21);
		lblUsernameRegister.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsernameRegister.setForeground(new Color(204, 204, 204));
		lblUsernameRegister.setFont(new Font("Arial", Font.PLAIN, 17));

		txtUsernameRegister = new JTextField("");
		txtUsernameRegister.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Register.SignUp(txtUsernameRegister.getText(), new String(pwdPasswordRegister.getPassword()),
							new String(pwdPasswordConfirmRegister.getPassword()));
				}
			}
		});
		txtUsernameRegister.setBounds(590, 165, 315, 31);
		txtUsernameRegister.setForeground(new Color(204, 204, 204));
		txtUsernameRegister.setFont(new Font("Arial", Font.PLAIN, 20));
		txtUsernameRegister.setColumns(10);
		txtUsernameRegister.setBackground(SystemColor.desktop);

		JLabel lblPasswordConfirmRegister = new JLabel(Import.lang.get(11));
		lblPasswordConfirmRegister.setBounds(590, 269, 315, 21);
		lblPasswordConfirmRegister.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasswordConfirmRegister.setForeground(new Color(204, 204, 204));
		lblPasswordConfirmRegister.setFont(new Font("Arial", Font.PLAIN, 17));

		pwdPasswordConfirmRegister = new JPasswordField("");
		pwdPasswordConfirmRegister.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Register.SignUp(txtUsernameRegister.getText(), new String(pwdPasswordRegister.getPassword()),
							new String(pwdPasswordConfirmRegister.getPassword()));
				}
			}
		});
		pwdPasswordConfirmRegister.setBounds(590, 289, 315, 31);
		pwdPasswordConfirmRegister.setForeground(new Color(204, 204, 204));
		pwdPasswordConfirmRegister.setFont(new Font("Arial", Font.PLAIN, 20));
		pwdPasswordConfirmRegister.setBackground(SystemColor.desktop);

		JButton btnSignUpRegister = new JButton(Import.lang.get(12));
		btnSignUpRegister.setBounds(590, 330, 315, 35);
		btnSignUpRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnSignUpRegister);

				Register.SignUp(txtUsernameRegister.getText(), new String(pwdPasswordRegister.getPassword()),
						new String(pwdPasswordConfirmRegister.getPassword()));
			}
		});
		btnSignUpRegister.setForeground(new Color(204, 204, 204));
		btnSignUpRegister.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSignUpRegister.setBackground(SystemColor.desktop);

		JButton btnReturnToLoginRegister = new JButton(Import.lang.get(17));
		btnReturnToLoginRegister.setBounds(590, 376, 315, 35);
		btnReturnToLoginRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnReturnToLoginRegister);

				ContentPanel.removeAll();
				ContentPanel.add(LoginPanel);
				ContentPanel.revalidate();
			}
		});
		btnReturnToLoginRegister.setForeground(new Color(204, 204, 204));
		btnReturnToLoginRegister.setFont(new Font("Arial", Font.PLAIN, 20));
		btnReturnToLoginRegister.setBackground(SystemColor.desktop);

		JLabel lblInfoRegister = new JLabel(Import.Copyright + "  |  " + "v" + Import.Version);
		lblInfoRegister.setBounds(500, 575, 494, 21);
		lblInfoRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoRegister.setForeground(new Color(204, 204, 204));
		lblInfoRegister.setFont(new Font("Arial", Font.PLAIN, 12));

		JSeparator Separator1Register = new JSeparator();
		Separator1Register.setBounds(500, 0, 2, 596);
		Separator1Register.setOrientation(SwingConstants.VERTICAL);
		Separator1Register.setForeground(new Color(204, 204, 204));
		Separator1Register.setBackground(new Color(204, 204, 204));

		txtpnStatementRegister = new JTextPane();
		txtpnStatementRegister.setBounds(590, 435, 315, 129);
		txtpnStatementRegister.setText("");
		txtpnStatementRegister.setForeground(new Color(204, 204, 204));
		txtpnStatementRegister.setFont(new Font("Arial", Font.PLAIN, 18));
		txtpnStatementRegister.setEditable(false);
		txtpnStatementRegister.setBackground(SystemColor.desktop);

		JSeparator Separator2Register = new JSeparator();
		Separator2Register.setBounds(590, 422, 315, 2);
		Separator2Register.setForeground(new Color(204, 204, 204));
		Separator2Register.setBackground(new Color(204, 204, 204));

		JLabel lblNewsRegister = new JLabel(Import.lang.get(1));
		lblNewsRegister.setBounds(0, 11, 500, 49);
		lblNewsRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewsRegister.setForeground(new Color(204, 204, 204));
		lblNewsRegister.setFont(new Font("Arial", Font.PLAIN, 40));

		JScrollPane scrlNewsRegister = new JScrollPane();
		scrlNewsRegister.setBounds(10, 71, 480, 514);
		scrlNewsRegister.setViewportBorder(null);
		scrlNewsRegister.setBorder(null);

		JTextArea txtrNewsRegister = new JTextArea("");
		scrlNewsRegister.setViewportView(txtrNewsRegister);
		txtrNewsRegister.setText(getNews);
		txtrNewsRegister.setForeground(new Color(204, 204, 204));
		txtrNewsRegister.setFont(new Font("Arial", Font.PLAIN, 19));
		txtrNewsRegister.setEditable(false);
		txtrNewsRegister.setBackground(SystemColor.desktop);

		JLabel lblPasswordRegister = new JLabel(Import.lang.get(10));
		lblPasswordRegister.setBounds(590, 207, 315, 21);
		lblPasswordRegister.setHorizontalAlignment(SwingConstants.LEFT);
		lblPasswordRegister.setForeground(new Color(204, 204, 204));
		lblPasswordRegister.setFont(new Font("Arial", Font.PLAIN, 17));

		pwdPasswordRegister = new JPasswordField("");
		pwdPasswordRegister.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Register.SignUp(txtUsernameRegister.getText(), new String(pwdPasswordRegister.getPassword()),
							new String(pwdPasswordConfirmRegister.getPassword()));
				}
			}
		});
		pwdPasswordRegister.setBounds(590, 227, 315, 31);
		pwdPasswordRegister.setForeground(new Color(204, 204, 204));
		pwdPasswordRegister.setFont(new Font("Arial", Font.PLAIN, 20));
		pwdPasswordRegister.setBackground(SystemColor.desktop);
		RegisterPanel.setLayout(null);
		RegisterPanel.add(lblNewsRegister);
		RegisterPanel.add(scrlNewsRegister);
		RegisterPanel.add(lblRegister);
		RegisterPanel.add(lblPasswordConfirmRegister);
		RegisterPanel.add(btnSignUpRegister);
		RegisterPanel.add(txtpnStatementRegister);
		RegisterPanel.add(lblUsernameRegister);
		RegisterPanel.add(lblInfoRegister);
		RegisterPanel.add(lblPasswordRegister);
		RegisterPanel.add(pwdPasswordRegister);
		RegisterPanel.add(txtUsernameRegister);
		RegisterPanel.add(Separator1Register);
		RegisterPanel.add(btnReturnToLoginRegister);
		RegisterPanel.add(pwdPasswordConfirmRegister);
		RegisterPanel.add(Separator2Register);

		MainPanel = new JPanel();
		MainPanel.setForeground(new Color(204, 204, 204));
		MainPanel.setBackground(new Color(51, 51, 51));
		ContentPanel.add(MainPanel, "name_410480288958600");

		lblBalanceMain = new JLabel("0");
		lblBalanceMain.setToolTipText(Import.lang.get(14));
		lblBalanceMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				requestHistory();

				ContentPanel.removeAll();
				ContentPanel.add(TransferPanel);
				ContentPanel.revalidate();
			}
		});
		lblBalanceMain.setBounds(20, 11, 912, 42);
		lblBalanceMain.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalanceMain.setFont(new Font("Arial", Font.PLAIN, 22));
		lblBalanceMain.setIcon(new ImageIcon(BankDemoFrame.class.getResource("/Yon Icon.png")));
		lblBalanceMain.setForeground(new Color(51, 204, 0));

		TransferPanel = new JPanel();
		TransferPanel.setBackground(new Color(51, 51, 51));
		ContentPanel.add(TransferPanel, "name_447082011795500");
		TransferPanel.setLayout(null);

		JScrollPane scrlTransferHistory = new JScrollPane();
		scrlTransferHistory.setAutoscrolls(false);
		scrlTransferHistory.setBorder(null);
		scrlTransferHistory.setViewportBorder(null);
		scrlTransferHistory.setBounds(0, 319, 994, 277);
		TransferPanel.add(scrlTransferHistory);

		txtpnTransferHistory = new JTextPane();
		scrlTransferHistory.setViewportView(txtpnTransferHistory);
		txtpnTransferHistory.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtpnTransferHistory.setForeground(new Color(204, 204, 204));
		txtpnTransferHistory.setBackground(new Color(51, 51, 51));
		txtpnTransferHistory.setEditable(false);
		MainPanel.setLayout(null);
		MainPanel.add(lblBalanceMain);

		JLabel lblClickSettings = new JLabel("");
		lblClickSettings.setIcon(new ImageIcon(BankDemoFrame.class.getResource("/Settings Icon.png")));
		lblClickSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ContentPanel.removeAll();
				ContentPanel.add(SettingsPanel);
				ContentPanel.revalidate();
			}
		});
		lblClickSettings.setToolTipText(Import.lang.get(15));
		lblClickSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblClickSettings.setForeground(Color.BLACK);
		lblClickSettings.setFont(new Font("Arial", Font.PLAIN, 22));
		lblClickSettings.setBounds(942, 11, 42, 42);
		MainPanel.add(lblClickSettings);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(20, 76, 500, 500);
		MainPanel.add(lblNewLabel);

		SettingsPanel = new JPanel();
		SettingsPanel.setBackground(new Color(51, 51, 51));
		ContentPanel.add(SettingsPanel, "name_424601440634000");
		SettingsPanel.setLayout(null);

		SeparatorSettingsLeft = new JSeparator();
		SeparatorSettingsLeft.setForeground(new Color(204, 204, 204));
		SeparatorSettingsLeft.setBackground(new Color(204, 204, 204));
		SeparatorSettingsLeft.setOrientation(SwingConstants.VERTICAL);
		SeparatorSettingsLeft.setBounds(267, 0, 2, 596);
		SettingsPanel.add(SeparatorSettingsLeft);

		JScrollPane scrlMenu = new JScrollPane();
		scrlMenu.setViewportBorder(null);
		scrlMenu.setBounds(0, 0, 269, 596);
		SettingsPanel.add(scrlMenu);

		JPanel Menu = new JPanel();
		Menu.setBorder(null);
		Menu.setBackground(new Color(51, 51, 51));
		scrlMenu.setViewportView(Menu);
		Menu.setLayout(null);

		btnMyAccount = new JButton(Import.lang.get(25));
		btnMyAccount.setEnabled(false);
		btnMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnMyAccount);

				btnMyAccount.setEnabled(false);
				btnGeneral.setEnabled(true);
				btnNews.setEnabled(true);
				btnCredits.setEnabled(true);
				btnPatchLog.setEnabled(true);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(MyAccountPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnMyAccount.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMyAccount.setBackground(new Color(51, 51, 51));
		btnMyAccount.setForeground(new Color(204, 204, 204));
		btnMyAccount.setBounds(10, 33, 247, 35);
		Menu.add(btnMyAccount);

		JSeparator SeparatorSettings1 = new JSeparator();
		SeparatorSettings1.setBounds(10, 91, 247, 2);
		Menu.add(SeparatorSettings1);
		SeparatorSettings1.setForeground(new Color(153, 153, 153));
		SeparatorSettings1.setBackground(new Color(153, 153, 153));

		JSeparator SeparatorSettings2 = new JSeparator();
		SeparatorSettings2.setForeground(new Color(153, 153, 153));
		SeparatorSettings2.setBackground(new Color(153, 153, 153));
		SeparatorSettings2.setBounds(10, 183, 247, 2);
		Menu.add(SeparatorSettings2);

		btnGeneral = new JButton(Import.lang.get(26));
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnGeneral);

				btnMyAccount.setEnabled(true);
				btnGeneral.setEnabled(false);
				btnNews.setEnabled(true);
				btnCredits.setEnabled(true);
				btnPatchLog.setEnabled(true);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(GeneralPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnGeneral.setForeground(new Color(204, 204, 204));
		btnGeneral.setFont(new Font("Arial", Font.PLAIN, 20));
		btnGeneral.setBackground(SystemColor.desktop);
		btnGeneral.setBounds(10, 126, 247, 35);
		Menu.add(btnGeneral);

		btnPatchLog = new JButton(Import.lang.get(27));
		btnPatchLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnPatchLog);

				btnMyAccount.setEnabled(true);
				btnGeneral.setEnabled(true);
				btnNews.setEnabled(true);
				btnCredits.setEnabled(true);
				btnPatchLog.setEnabled(false);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(PatchLogPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnPatchLog.setForeground(new Color(204, 204, 204));
		btnPatchLog.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPatchLog.setBackground(SystemColor.desktop);
		btnPatchLog.setBounds(10, 265, 247, 35);
		Menu.add(btnPatchLog);

		btnCredits = new JButton("Credits");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnCredits);

				btnMyAccount.setEnabled(true);
				btnGeneral.setEnabled(true);
				btnNews.setEnabled(true);
				btnCredits.setEnabled(false);
				btnPatchLog.setEnabled(true);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(CreditsPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnCredits.setForeground(new Color(204, 204, 204));
		btnCredits.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCredits.setBackground(SystemColor.desktop);
		btnCredits.setBounds(10, 311, 247, 35);
		Menu.add(btnCredits);

		JLabel lblVersion = new JLabel("v" + Import.Version);
		lblVersion.setBounds(0, 554, 267, 18);
		Menu.add(lblVersion);
		lblVersion.setFont(new Font("Arial", Font.PLAIN, 12));
		lblVersion.setForeground(new Color(204, 204, 204));
		lblVersion.setHorizontalAlignment(SwingConstants.CENTER);

		lblCopyright = new JLabel(Import.Copyright);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setForeground(new Color(204, 204, 204));
		lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCopyright.setBounds(0, 571, 267, 23);
		Menu.add(lblCopyright);

		JLabel lblUserSettings = new JLabel(Import.lang.get(22));
		lblUserSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserSettings.setForeground(new Color(204, 204, 204));
		lblUserSettings.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUserSettings.setBounds(10, 11, 247, 23);
		Menu.add(lblUserSettings);

		JLabel lblProgrammSettings = new JLabel(Import.lang.get(23));
		lblProgrammSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgrammSettings.setForeground(new Color(204, 204, 204));
		lblProgrammSettings.setFont(new Font("Arial", Font.PLAIN, 14));
		lblProgrammSettings.setBounds(10, 104, 247, 23);
		Menu.add(lblProgrammSettings);

		JLabel lblInformations = new JLabel(Import.lang.get(24));
		lblInformations.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformations.setForeground(new Color(204, 204, 204));
		lblInformations.setFont(new Font("Arial", Font.PLAIN, 14));
		lblInformations.setBounds(10, 196, 247, 23);
		Menu.add(lblInformations);

		btnNews = new JButton("News");
		btnNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRootPane().setDefaultButton(btnNews);

				btnMyAccount.setEnabled(true);
				btnGeneral.setEnabled(true);
				btnNews.setEnabled(false);
				btnCredits.setEnabled(true);
				btnPatchLog.setEnabled(true);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(NewsPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnNews.setForeground(new Color(204, 204, 204));
		btnNews.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNews.setBackground(SystemColor.desktop);
		btnNews.setBounds(10, 219, 247, 35);
		Menu.add(btnNews);

		JButton btnLogout = new JButton(Import.lang.get(29));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnLogout);

				ServerConnect.out("logout", false);
				YonVerifier.ThreadEnabled = false;
				YonVerifier.LoginCompleate = false;
				// RainbowAnimation.ThreadEnabled = false;

				MetaExpress.setPropertie(Import.$account, "Remember", "false");
				MetaExpress.setPropertie(Import.$account, "Username", "");
				MetaExpress.setPropertie(Import.$account, "Password", "");

				ContentPanel.removeAll();
				ContentPanel.add(LoginPanel);
				ContentPanel.revalidate();
			}
		});
		btnLogout.setForeground(new Color(128, 0, 0));
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLogout.setBackground(SystemColor.desktop);
		btnLogout.setBounds(10, 382, 247, 35);
		Menu.add(btnLogout);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(153, 153, 153));
		separator_2.setBackground(new Color(153, 153, 153));
		separator_2.setBounds(10, 369, 247, 2);
		Menu.add(separator_2);

		JButton btnBackSettings = new JButton(Import.lang.get(17));
		btnBackSettings.setBounds(874, 11, 110, 35);
		SettingsPanel.add(btnBackSettings);
		btnBackSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnBackSettings);

				ContentPanel.removeAll();
				ContentPanel.add(MainPanel);
				ContentPanel.revalidate();
			}
		});
		btnBackSettings.setForeground(new Color(204, 204, 204));
		btnBackSettings.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBackSettings.setBackground(SystemColor.desktop);

		lblBalanceSettings = new JLabel("0");
		lblBalanceSettings.setIcon(new ImageIcon(BankDemoFrame.class.getResource("/Yon Icon.png")));
		lblBalanceSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalanceSettings.setForeground(new Color(51, 204, 0));
		lblBalanceSettings.setFont(new Font("Arial", Font.PLAIN, 22));
		lblBalanceSettings.setBounds(279, 11, 585, 35);
		SettingsPanel.add(lblBalanceSettings);

		JSeparator SeparatorSettingsTop = new JSeparator();
		SeparatorSettingsTop.setForeground(new Color(204, 204, 204));
		SeparatorSettingsTop.setBackground(new Color(204, 204, 204));
		SeparatorSettingsTop.setBounds(267, 54, 727, 2);
		SettingsPanel.add(SeparatorSettingsTop);

		SettingsCardLayout = new JPanel();
		SettingsCardLayout.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.setBounds(270, 57, 723, 539);
		SettingsPanel.add(SettingsCardLayout);
		SettingsCardLayout.setLayout(new CardLayout(0, 0));

		MyAccountPanel = new JPanel();
		MyAccountPanel.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.add(MyAccountPanel, "name_777587855909100");
		MyAccountPanel.setLayout(null);

		JLabel lblMyAccount = new JLabel(Import.lang.get(25));
		lblMyAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyAccount.setFont(new Font("Arial", Font.PLAIN, 25));
		lblMyAccount.setForeground(new Color(204, 204, 204));
		lblMyAccount.setBounds(10, 11, 707, 40);
		MyAccountPanel.add(lblMyAccount);

		JLabel lblUsername = new JLabel(Import.lang.get(3));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setForeground(new Color(153, 153, 153));
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUsername.setBounds(10, 71, 703, 24);
		MyAccountPanel.add(lblUsername);

		JButton btnChangePassword = new JButton(Import.lang.get(30));
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRootPane().setDefaultButton(btnChangePassword);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(CPasswordPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnChangePassword.setForeground(new Color(204, 204, 204));
		btnChangePassword.setFont(new Font("Arial", Font.PLAIN, 21));
		btnChangePassword.setBackground(SystemColor.desktop);
		btnChangePassword.setBounds(10, 447, 703, 35);
		MyAccountPanel.add(btnChangePassword);

		lblMyUsername = new JLabel("");
		lblMyUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblMyUsername.setForeground(new Color(204, 204, 204));
		lblMyUsername.setFont(new Font("Arial", Font.PLAIN, 21));
		lblMyUsername.setBounds(10, 96, 703, 24);
		MyAccountPanel.add(lblMyUsername);

		JButton btnDeleteAccount = new JButton(Import.lang.get(31));
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRootPane().setDefaultButton(btnDeleteAccount);
				new Thread(new DeleteAccount()).start();
			}
		});
		btnDeleteAccount.setForeground(new Color(204, 204, 204));
		btnDeleteAccount.setFont(new Font("Arial", Font.PLAIN, 21));
		btnDeleteAccount.setBackground(new Color(128, 0, 0));
		btnDeleteAccount.setBounds(10, 493, 703, 35);
		MyAccountPanel.add(btnDeleteAccount);

		CPasswordPanel = new JPanel();
		CPasswordPanel.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.add(CPasswordPanel, "name_858321356014300");
		CPasswordPanel.setLayout(null);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.setForeground(new Color(204, 204, 204));
		lblChangePassword.setFont(new Font("Arial", Font.PLAIN, 25));
		lblChangePassword.setBounds(10, 11, 707, 40);
		CPasswordPanel.add(lblChangePassword);

		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdPassword.setForeground(new Color(204, 204, 204));
		pwdPassword.setBackground(new Color(51, 51, 51));
		pwdPassword.setBounds(10, 168, 703, 26);
		CPasswordPanel.add(pwdPassword);

		JLabel lblPassword = new JLabel(Import.lang.get(33));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setForeground(new Color(204, 204, 204));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPassword.setBounds(10, 140, 703, 31);
		CPasswordPanel.add(lblPassword);

		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setForeground(new Color(204, 204, 204));
		pwdConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdConfirmPassword.setBackground(SystemColor.desktop);
		pwdConfirmPassword.setBounds(10, 243, 703, 26);
		CPasswordPanel.add(pwdConfirmPassword);

		JLabel lblConfirmPassword = new JLabel(Import.lang.get(34));
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmPassword.setForeground(new Color(204, 204, 204));
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(10, 215, 703, 31);
		CPasswordPanel.add(lblConfirmPassword);

		JButton btnCPassword = new JButton(Import.lang.get(30));
		btnCPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnCPassword);

				String OldPw = new String(pwdOldPassword.getPassword());
				String Pw = new String(pwdPassword.getPassword());
				String ConfirmPw = new String(pwdConfirmPassword.getPassword());

				if (OldPw.isEmpty() || Pw.isEmpty() || ConfirmPw.isEmpty()) {
					lblState.setText(Import.lang.get(52));
				} else {
					if (Import.Password.equals(OldPw)) {
						if (!Import.Password.equals(Pw)) {
							if (Pw.equals(ConfirmPw)) {
								ChangePassword.CPassword(Pw);
								lblState.setText(Import.lang.get(53));

								try {
									Thread.sleep(5000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								SettingsCardLayout.removeAll();
								SettingsCardLayout.add(MyAccountPanel);
								SettingsCardLayout.revalidate();
							} else {
								lblState.setText(Import.lang.get(54));
							}
						} else {
							lblState.setText(Import.lang.get(55));
						}
					} else {
						lblState.setText(Import.lang.get(56));
					}
				}
			}
		});
		btnCPassword.setForeground(new Color(204, 204, 204));
		btnCPassword.setFont(new Font("Arial", Font.PLAIN, 21));
		btnCPassword.setBackground(SystemColor.desktop);
		btnCPassword.setBounds(10, 280, 703, 35);
		CPasswordPanel.add(btnCPassword);

		pwdOldPassword = new JPasswordField();
		pwdOldPassword.setForeground(new Color(204, 204, 204));
		pwdOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdOldPassword.setBackground(SystemColor.desktop);
		pwdOldPassword.setBounds(10, 90, 703, 26);
		CPasswordPanel.add(pwdOldPassword);

		JLabel lblOldPassword = new JLabel(Import.lang.get(32));
		lblOldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblOldPassword.setForeground(new Color(204, 204, 204));
		lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		lblOldPassword.setBounds(10, 62, 703, 31);
		CPasswordPanel.add(lblOldPassword);

		JButton btnCancel = new JButton(Import.lang.get(13));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnCancel);

				SettingsCardLayout.removeAll();
				SettingsCardLayout.add(MyAccountPanel);
				SettingsCardLayout.revalidate();
			}
		});
		btnCancel.setForeground(new Color(204, 204, 204));
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 21));
		btnCancel.setBackground(SystemColor.desktop);
		btnCancel.setBounds(10, 326, 703, 35);
		CPasswordPanel.add(btnCancel);

		lblState = new JLabel("");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setForeground(new Color(204, 204, 204));
		lblState.setFont(new Font("Arial", Font.PLAIN, 20));
		lblState.setBounds(10, 372, 707, 156);
		CPasswordPanel.add(lblState);

		GeneralPanel = new JPanel();
		GeneralPanel.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.add(GeneralPanel, "name_777587871969000");
		GeneralPanel.setLayout(null);

		JScrollPane scrlGeneral = new JScrollPane();
		scrlGeneral.setBorder(null);
		scrlGeneral.setBounds(0, 0, 723, 539);
		GeneralPanel.add(scrlGeneral);

		JPanel General = new JPanel();
		General.setBorder(null);
		General.setBackground(new Color(51, 51, 51));
		scrlGeneral.setViewportView(General);
		General.setLayout(null);

		JLabel lblGeneral = new JLabel(Import.lang.get(26));
		lblGeneral.setBounds(10, 11, 703, 40);
		General.add(lblGeneral);
		lblGeneral.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneral.setForeground(new Color(204, 204, 204));
		lblGeneral.setFont(new Font("Arial", Font.PLAIN, 25));

		JLabel lblLogMode = new JLabel(Import.lang.get(39));
		lblLogMode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogMode.setForeground(new Color(204, 204, 204));
		lblLogMode.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLogMode.setBounds(10, 203, 474, 34);
		General.add(lblLogMode);

		btnLogModeED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRootPane().setDefaultButton(btnLogModeED);

				if (!LogModeED) {
					LogModeED = true;
					btnLogModeED.setText(Import.lang.get(35));
					btnLogModeED.setForeground(new Color(0, 128, 0));
				} else {
					LogModeED = false;
					btnLogModeED.setText(Import.lang.get(36));
					btnLogModeED.setForeground(new Color(128, 0, 0));
				}
			}
		});
		btnLogModeED.setFont(new Font("Arial", Font.PLAIN, 22));
		btnLogModeED.setBackground(new Color(51, 51, 51));
		btnLogModeED.setBounds(494, 202, 219, 35);
		General.add(btnLogModeED);

		JLabel lblLang = new JLabel("Language");
		lblLang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLang.setForeground(new Color(204, 204, 204));
		lblLang.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLang.setBounds(10, 110, 474, 35);
		General.add(lblLang);

		cbbLanguage.setFont(new Font("Arial", Font.PLAIN, 22));
		cbbLanguage.setBackground(new Color(51, 51, 51));
		cbbLanguage.setForeground(new Color(204, 204, 204));
		cbbLanguage.setBounds(494, 110, 219, 35);
		General.add(cbbLanguage);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(153, 153, 153));
		separator.setBackground(new Color(153, 153, 153));
		separator.setBounds(10, 97, 703, 2);
		General.add(separator);

		JLabel lblLanguage = new JLabel(Import.lang.get(37));
		lblLanguage.setHorizontalAlignment(SwingConstants.LEFT);
		lblLanguage.setForeground(new Color(204, 204, 204));
		lblLanguage.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLanguage.setBounds(10, 62, 703, 35);
		General.add(lblLanguage);

		JLabel lblLocalSettings = new JLabel(Import.lang.get(38));
		lblLocalSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalSettings.setForeground(new Color(204, 204, 204));
		lblLocalSettings.setFont(new Font("Arial", Font.PLAIN, 20));
		lblLocalSettings.setBounds(10, 156, 703, 35);
		General.add(lblLocalSettings);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(153, 153, 153));
		separator_1.setBackground(new Color(153, 153, 153));
		separator_1.setBounds(10, 191, 703, 2);
		General.add(separator_1);

		JButton btnApply = new JButton(Import.lang.get(41));
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnApply);
				saveSettings();
			}
		});
		btnApply.setForeground(new Color(204, 204, 204));
		btnApply.setFont(new Font("Arial", Font.PLAIN, 25));
		btnApply.setBackground(new Color(0, 128, 0));
		btnApply.setBounds(10, 488, 343, 40);
		General.add(btnApply);

		JLabel lblConnectTestServer = new JLabel(Import.lang.get(40));
		lblConnectTestServer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConnectTestServer.setForeground(new Color(204, 204, 204));
		lblConnectTestServer.setFont(new Font("Arial", Font.PLAIN, 20));
		lblConnectTestServer.setBounds(10, 249, 474, 34);
		General.add(lblConnectTestServer);

		btnServerED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnServerED);

				if (!ServerED) {
					ServerED = true;
					btnServerED.setText("Enabled");
					btnServerED.setForeground(new Color(0, 128, 0));
				} else {
					ServerED = false;
					btnServerED.setText("Disabled");
					btnServerED.setForeground(new Color(128, 0, 0));
				}
			}
		});
		btnServerED.setFont(new Font("Arial", Font.PLAIN, 22));
		btnServerED.setBackground(SystemColor.desktop);
		btnServerED.setBounds(494, 248, 219, 35);
		General.add(btnServerED);

		JButton btnUndo = new JButton(Import.lang.get(42));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnUndo);

				loadSettings();
				saveSettings();
			}
		});
		btnUndo.setForeground(new Color(204, 204, 204));
		btnUndo.setFont(new Font("Arial", Font.PLAIN, 25));
		btnUndo.setBackground(new Color(128, 0, 0));
		btnUndo.setBounds(370, 488, 343, 40);
		General.add(btnUndo);

		NewsPanel = new JPanel();
		NewsPanel.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.add(NewsPanel, "name_777587887233300");
		NewsPanel.setLayout(null);

		JLabel lblNews = new JLabel(Import.lang.get(1));
		lblNews.setHorizontalAlignment(SwingConstants.CENTER);
		lblNews.setForeground(new Color(204, 204, 204));
		lblNews.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNews.setBounds(10, 11, 707, 40);
		NewsPanel.add(lblNews);

		JScrollPane scrlNews = new JScrollPane();
		scrlNews.setViewportBorder(null);
		scrlNews.setBorder(null);
		scrlNews.setBounds(10, 62, 703, 466);
		NewsPanel.add(scrlNews);

		JTextArea txtrNews = new JTextArea(getNews);
		txtrNews.setForeground(new Color(204, 204, 204));
		txtrNews.setFont(new Font("Arial", Font.PLAIN, 19));
		txtrNews.setEditable(false);
		txtrNews.setBackground(SystemColor.desktop);
		scrlNews.setViewportView(txtrNews);

		PatchLogPanel = new JPanel();
		PatchLogPanel.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.add(PatchLogPanel, "name_777587905269300");
		PatchLogPanel.setLayout(null);

		JLabel lblChangelog = new JLabel(Import.lang.get(27));
		lblChangelog.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangelog.setForeground(new Color(204, 204, 204));
		lblChangelog.setFont(new Font("Arial", Font.PLAIN, 25));
		lblChangelog.setBounds(10, 11, 707, 40);
		PatchLogPanel.add(lblChangelog);

		JScrollPane scrlPatchLog = new JScrollPane();
		scrlPatchLog.setViewportBorder(null);
		scrlPatchLog.setBorder(null);
		scrlPatchLog.setBounds(10, 62, 703, 466);
		PatchLogPanel.add(scrlPatchLog);

		JTextPane txtpnPatchlog = new JTextPane();
		txtpnPatchlog.setForeground(new Color(204, 204, 204));
		txtpnPatchlog.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnPatchlog.setEditable(false);
		txtpnPatchlog.setBackground(new Color(51, 51, 51));
		txtpnPatchlog.setText("PatchLog");
		scrlPatchLog.setViewportView(txtpnPatchlog);

		CreditsPanel = new JPanel();
		CreditsPanel.setBackground(new Color(51, 51, 51));
		SettingsCardLayout.add(CreditsPanel, "name_777587919359700");
		CreditsPanel.setLayout(null);

		JLabel lblCredits = new JLabel(Import.lang.get(28));
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setForeground(new Color(204, 204, 204));
		lblCredits.setFont(new Font("Arial", Font.PLAIN, 25));
		lblCredits.setBounds(10, 11, 707, 40);
		CreditsPanel.add(lblCredits);

		JScrollPane scrlCredits = new JScrollPane();
		scrlCredits.setViewportBorder(null);
		scrlCredits.setBorder(null);
		scrlCredits.setBounds(10, 62, 703, 466);
		CreditsPanel.add(scrlCredits);

		JTextArea txtrCredits = new JTextArea(Import.lang.get(43));
		scrlCredits.setViewportView(txtrCredits);
		txtrCredits.setForeground(new Color(204, 204, 204));
		txtrCredits.setFont(new Font("Arial", Font.PLAIN, 19));
		txtrCredits.setEditable(false);
		txtrCredits.setBackground(SystemColor.desktop);

		JButton btnBackTransfer = new JButton(Import.lang.get(17));
		btnBackTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getRootPane().setDefaultButton(btnBackTransfer);

				ContentPanel.removeAll();
				ContentPanel.add(MainPanel);
				ContentPanel.revalidate();
			}
		});
		btnBackTransfer.setForeground(new Color(204, 204, 204));
		btnBackTransfer.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBackTransfer.setBackground(SystemColor.desktop);
		btnBackTransfer.setBounds(844, 11, 140, 35);
		TransferPanel.add(btnBackTransfer);

		lblBalanceTransfer = new JLabel("0");
		lblBalanceTransfer.setIcon(new ImageIcon(BankDemoFrame.class.getResource("/Yon Icon.png")));
		lblBalanceTransfer.setHorizontalAlignment(SwingConstants.LEFT);
		lblBalanceTransfer.setForeground(new Color(51, 204, 0));
		lblBalanceTransfer.setFont(new Font("Arial", Font.PLAIN, 22));
		lblBalanceTransfer.setBounds(10, 10, 639, 35);
		TransferPanel.add(lblBalanceTransfer);

		sprTransferAmount = new JSpinner();
		sprTransferAmount.setModel(new SpinnerNumberModel(1, 1, 9999999, 1));
		sprTransferAmount.getEditor().getComponent(0).setBackground(new Color(51, 51, 51));
		sprTransferAmount.getEditor().getComponent(0).setForeground(new Color(204, 204, 204));
		sprTransferAmount.setFont(new Font("Arial", Font.PLAIN, 22));
		sprTransferAmount.setBounds(506, 96, 478, 35);
		TransferPanel.add(sprTransferAmount);

		JSeparator TransferSeparator = new JSeparator();
		TransferSeparator.setForeground(new Color(204, 204, 204));
		TransferSeparator.setBackground(new Color(204, 204, 204));
		TransferSeparator.setBounds(0, 57, 995, 2);
		TransferPanel.add(TransferSeparator);

		lblStatusTransfer = new JLabel("");
		lblStatusTransfer.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusTransfer.setForeground(new Color(204, 204, 204));
		lblStatusTransfer.setFont(new Font("Arial", Font.PLAIN, 22));
		lblStatusTransfer.setBounds(10, 193, 974, 35);
		TransferPanel.add(lblStatusTransfer);

		btnTransfer = new JButton(Import.lang.get(20));
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnTransfer);
				new Thread(new Transfer()).start();
			}
		});
		btnTransfer.setForeground(new Color(204, 204, 204));
		btnTransfer.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTransfer.setBackground(SystemColor.desktop);
		btnTransfer.setBounds(10, 142, 974, 40);
		TransferPanel.add(btnTransfer);

		txtTransferTo = new JTextField();
		txtTransferTo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtTransferTo.setForeground(new Color(204, 204, 204));
		txtTransferTo.setBackground(new Color(51, 51, 51));
		txtTransferTo.setBounds(10, 96, 478, 35);
		TransferPanel.add(txtTransferTo);
		txtTransferTo.setColumns(10);

		JLabel lblAmount = new JLabel(Import.lang.get(19));
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Arial", Font.PLAIN, 22));
		lblAmount.setForeground(new Color(204, 204, 204));
		lblAmount.setBounds(506, 70, 478, 26);
		TransferPanel.add(lblAmount);

		JLabel lblTransferTo = new JLabel(Import.lang.get(18));
		lblTransferTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransferTo.setForeground(new Color(204, 204, 204));
		lblTransferTo.setFont(new Font("Arial", Font.PLAIN, 22));
		lblTransferTo.setBounds(10, 70, 478, 26);
		TransferPanel.add(lblTransferTo);

		JSeparator TransferHistorySeparator1 = new JSeparator();
		TransferHistorySeparator1.setForeground(new Color(204, 204, 204));
		TransferHistorySeparator1.setBackground(new Color(204, 204, 204));
		TransferHistorySeparator1.setBounds(0, 239, 995, 2);
		TransferPanel.add(TransferHistorySeparator1);

		JLabel lblTransferHistory = new JLabel(Import.lang.get(21));
		lblTransferHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransferHistory.setForeground(new Color(204, 204, 204));
		lblTransferHistory.setFont(new Font("Arial", Font.PLAIN, 22));
		lblTransferHistory.setBounds(10, 252, 974, 26);
		TransferPanel.add(lblTransferHistory);

		JSeparator TransferHistorySeparator2 = new JSeparator();
		TransferHistorySeparator2.setForeground(new Color(153, 153, 153));
		TransferHistorySeparator2.setBounds(0, 316, 995, 1);
		TransferPanel.add(TransferHistorySeparator2);

		btnRefresh = new JButton(Import.lang.get(16));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRootPane().setDefaultButton(btnRefresh);

				new Thread(new RefreshSpamProtection()).start();
				requestHistory();
			}
		});
		btnRefresh.setForeground(new Color(204, 204, 204));
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRefresh.setBackground(SystemColor.desktop);
		btnRefresh.setBounds(659, 11, 175, 35);
		TransferPanel.add(btnRefresh);

		JLabel lblFormatFromTo = new JLabel(
				"<html><font color='#FF00FF'>From</font><html> / <font color='#FF0000'>To</font></html>");
		lblFormatFromTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormatFromTo.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFormatFromTo.setForeground(new Color(153, 153, 153));
		lblFormatFromTo.setBounds(14, 289, 229, 19);
		TransferPanel.add(lblFormatFromTo);

		JLabel lblFormatOutputInput = new JLabel(
				"<html><font color='#00FFF6'>Output</font><html> / <font color='#65FF00'>Input</font></html>");
		lblFormatOutputInput.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormatOutputInput.setForeground(new Color(153, 153, 153));
		lblFormatOutputInput.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFormatOutputInput.setBounds(254, 289, 234, 19);
		TransferPanel.add(lblFormatOutputInput);

		JLabel lblFormatAmount = new JLabel("<html><font color='#FFDD00'>Amount</font>");
		lblFormatAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblFormatAmount.setForeground(new Color(153, 153, 153));
		lblFormatAmount.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFormatAmount.setBounds(495, 289, 217, 19);
		TransferPanel.add(lblFormatAmount);

		JLabel lblFormatDateTime = new JLabel(
				"<html><font color='#00B8FF'>Date</font><html> / <font color='#00B8FF'>Time</font>");
		lblFormatDateTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormatDateTime.setForeground(new Color(153, 153, 153));
		lblFormatDateTime.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFormatDateTime.setBounds(722, 289, 234, 19);
		TransferPanel.add(lblFormatDateTime);

		new Timer(5, this).start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	static void getNews() {
		ServerConnect.out("news", false);
		getNews = ServerConnect.read();
	}

	public static String[] HistoryFormatter(String FromTo, String InOut, String Amount, String DateTime) {
		if (InOut.equals("In")) {
			InOut = "Input";
		} else if (InOut.equals("Out")) {
			InOut = "Output";
		}

		String FormattedFromTo = FromTo;
		String FormattedInOut = InOut;
		String FormattedAmount = Amount;
		String FormattedDateTime = DateTime;

		char[] CharFromTo = FromTo.toCharArray();
		char[] CharInOut = InOut.toCharArray();
		char[] CharAmount = Amount.toCharArray();

		int lengthFromTo = CharFromTo.length;
		while (lengthFromTo != 20) {
			FormattedFromTo = FormattedFromTo + " ";
			++lengthFromTo;
		}

		int lengthInOut = CharInOut.length;
		while (lengthInOut != 20) {
			FormattedInOut = FormattedInOut + " ";
			++lengthInOut;
		}

		int lengthAmount = CharAmount.length;
		while (lengthAmount != 19) {
			FormattedAmount = FormattedAmount + " ";
			++lengthAmount;
		}

		String[] Array = { FormattedFromTo, FormattedInOut, FormattedAmount, FormattedDateTime, FromTo, InOut };
		return Array;
	}

	public static void requestHistory() {
		txtpnTransferHistory.setText("");

		ServerConnect.out("getHistory;" + Import.Username + ";" + Import.Password, true);
		String requestedHistory = ServerConnect.read();

		if (!requestedHistory.equals("")) {
			String[] History = requestedHistory.split("&");

			for (int x = 0; x < History.length; ++x) {
				String[] UnformattedHistory = History[x].split(";");
				boolean bInput = false;

				StyledDocument Text = txtpnTransferHistory.getStyledDocument();
				Style Style = txtpnTransferHistory.addStyle("Classic", null);

				try {
					String[] FormattedAndSwitch = HistoryFormatter(UnformattedHistory[0], UnformattedHistory[1],
							UnformattedHistory[2], UnformattedHistory[3]);

					if (UnformattedHistory[1].equals("In")) {
						bInput = false;
					} else {
						bInput = true;
					}

					if (bInput) {
						StyleConstants.setForeground(Style, new Color(255, 0, 0));// RED
					} else {
						StyleConstants.setForeground(Style, new Color(255, 0, 255));// PINK
					}
					Text.insertString(Text.getLength(), " " + FormattedAndSwitch[0], Style);

					if (bInput) {
						StyleConstants.setForeground(Style, new Color(0, 255, 246));// GREEN
					} else {
						StyleConstants.setForeground(Style, new Color(101, 255, 0));// CYAN
					}
					Text.insertString(Text.getLength(), FormattedAndSwitch[1], Style);

					StyleConstants.setForeground(Style, new Color(255, 221, 0));// GOLD
					Text.insertString(Text.getLength(), FormattedAndSwitch[2], Style);

					StyleConstants.setForeground(Style, new Color(0, 184, 255));// BLUE
					Text.insertString(Text.getLength(), FormattedAndSwitch[3] + "\n", Style);
				} catch (BadLocationException e) {
				}
			}
		}
	}

	public static void setStatement(String Text) {
		txtpnStatementLogin.setText(Text);
		txtpnStatementRegister.setText(Text);
	}

	public static void saveSettings() {
		MetaExpress.setPropertie("BankDemo/config.yml", "Language", cbbLanguage.getSelectedItem() + "");

		MetaExpress.setPropertie("BankDemo/config.yml", "LogMode", LogModeED + "");

		if (ServerED) {
			MetaExpress.setPropertie("BankDemo/config.yml", "Server", "TESTSERVER");
		} else {
			MetaExpress.setPropertie("BankDemo/config.yml", "Server", "MAINSERVER");
		}

		Main.Setup();
	}

	public static void loadSettings() {
		Import.Language = MetaExpress.getPropertie(Import.$config, "Language");
		cbbLanguage.addItem(Import.Language);
		File[] LangFiles = new File("BankDemo/lang").listFiles();

		for (int x = 0; x < LangFiles.length; ++x) {
			if (!LangFiles[x].getName().split("\\.")[0].equals(Import.Language)) {
				cbbLanguage.addItem(LangFiles[x].getName().split("\\.")[0]);
			}
		}

		if (MetaExpress.getPropertie("BankDemo/config.yml", "LogMode").equals("true")) {
			Enable.LogMode = true;

			LogModeED = true;
			btnLogModeED.setText("Enabled");
			btnLogModeED.setForeground(new Color(0, 128, 0));
		} else {
			Enable.LogMode = false;

			LogModeED = false;
			btnLogModeED.setText("Disabled");
			btnLogModeED.setForeground(new Color(128, 0, 0));
		}

		if (!MetaExpress.getPropertie("BankDemo/config.yml", "Server").equals("MAINSERVER")) {
			ServerED = true;
			btnServerED.setText("Enabled");
			btnServerED.setForeground(new Color(0, 128, 0));
		} else {
			ServerED = false;
			btnServerED.setText("Disabled");
			btnServerED.setForeground(new Color(128, 0, 0));
		}
	}
}

class Transfer implements Runnable {
	public void run() {
		BankDemoFrame.btnTransfer.setEnabled(false);

		String TransferTo = BankDemoFrame.txtTransferTo.getText();
		int TransferAmount = (int) BankDemoFrame.sprTransferAmount.getValue();

		if (!TransferTo.equals("")) {
			char[] CharTransferTo = TransferTo.toCharArray();

			for (char Char : CharTransferTo) {
				switch (Char) {
					case 'A':
						break;
					case 'a':
						break;
					case 'B':
						break;
					case 'b':
						break;
					case 'C':
						break;
					case 'c':
						break;
					case 'D':
						break;
					case 'd':
						break;
					case 'E':
						break;
					case 'e':
						break;
					case 'F':
						break;
					case 'f':
						break;
					case 'G':
						break;
					case 'g':
						break;
					case 'H':
						break;
					case 'h':
						break;
					case 'I':
						break;
					case 'i':
						break;
					case 'J':
						break;
					case 'j':
						break;
					case 'K':
						break;
					case 'k':
						break;
					case 'L':
						break;
					case 'l':
						break;
					case 'M':
						break;
					case 'm':
						break;
					case 'N':
						break;
					case 'n':
						break;
					case 'O':
						break;
					case 'o':
						break;
					case 'P':
						break;
					case 'p':
						break;
					case 'Q':
						break;
					case 'q':
						break;
					case 'R':
						break;
					case 'r':
						break;
					case 'S':
						break;
					case 's':
						break;
					case 'T':
						break;
					case 't':
						break;
					case 'U':
						break;
					case 'u':
						break;
					case 'V':
						break;
					case 'v':
						break;
					case 'W':
						break;
					case 'w':
						break;
					case 'X':
						break;
					case 'x':
						break;
					case 'Y':
						break;
					case 'y':
						break;
					case 'Z':
						break;
					case 'z':
						break;
					case '0':
						break;
					case '1':
						break;
					case '2':
						break;
					case '3':
						break;
					case '4':
						break;
					case '5':
						break;
					case '6':
						break;
					case '7':
						break;
					case '8':
						break;
					case '9':
						break;

					default:
						BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(57));
						return;
				}
			}

			if (!TransferTo.equals(Import.Username)) {
				if (TransferAmount <= Import.Balance) {
					ServerConnect.out("transfer;" + TransferTo + ";" + TransferAmount, true);
					String Input = ServerConnect.read();

					if (Input.equals("transfer;Accepted")) {
						BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(58));
					} else if (Input.equals("transfer;NotEnough")) {
						BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(59));
					} else if (Input.equals("transfer;UserNotFound")) {
						BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(57));
					} else if (Input.equals("transfer;SameUser")) {
						BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(61));
					}
				} else {
					BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(59));
				}
			} else {
				BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(61));
			}
		} else {
			BankDemoFrame.lblStatusTransfer.setText(Import.lang.get(52));
		}

		try {
			BankDemoFrame.btnTransfer.setText("5");
			Thread.sleep(1000);
			BankDemoFrame.btnTransfer.setText("4");
			Thread.sleep(1000);
			BankDemoFrame.btnTransfer.setText("3");
			Thread.sleep(1000);
			BankDemoFrame.btnTransfer.setText("2");
			Thread.sleep(1000);
			BankDemoFrame.btnTransfer.setText("1");
			Thread.sleep(1000);
			BankDemoFrame.btnTransfer.setText(Import.lang.get(20));

			BankDemoFrame.btnTransfer.setEnabled(true);
			BankDemoFrame.lblStatusTransfer.setText("");

			BankDemoFrame.requestHistory();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class RefreshSpamProtection implements Runnable {
	public void run() {
		try {
			BankDemoFrame.btnRefresh.setEnabled(false);

			BankDemoFrame.btnRefresh.setText("3");
			Thread.sleep(1000);
			BankDemoFrame.btnRefresh.setText("2");
			Thread.sleep(1000);
			BankDemoFrame.btnRefresh.setText("1");
			Thread.sleep(1000);
			BankDemoFrame.btnRefresh.setText(Import.lang.get(16));

			BankDemoFrame.btnRefresh.setEnabled(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}