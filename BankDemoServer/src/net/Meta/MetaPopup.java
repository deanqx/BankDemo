package net.Meta;

import javax.swing.JOptionPane;

public class MetaPopup {
	public static void Show(String Message, int Mode) {
		if (Mode == 0) {
			MetaConsole.out("MetaPopup Information : " + Message, 0, false);
			JOptionPane.showMessageDialog(null, Message, "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (Mode == 1) {
			MetaConsole.out("MetaPopup Error : " + Message, 0, false);
			JOptionPane.showMessageDialog(null, Message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static boolean YesOrNo(String Message) {
		MetaConsole.out("MetaPopup Confirm : " + Message, 0, false);
		int YesOrNo = JOptionPane.showConfirmDialog(null, Message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (YesOrNo == JOptionPane.YES_OPTION) {
			return true;
		} else if (YesOrNo == JOptionPane.NO_OPTION) {
			return false;
		}
		
		return false;
	}
}