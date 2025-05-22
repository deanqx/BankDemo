package net.Meta;

import java.util.ArrayList;
import java.util.List;

public class MetaArray {
	private static List<String> Namelist = new ArrayList<String>();
	private static List<String> Array = new ArrayList<String>();

	public static void add(String Name, String Content) {
		Namelist.add(Name);
		Array.add(Content);
	}

	public static void set(String Name, String Content) {
		if (!Namelist.isEmpty()) {
			for (int x = 0; x < Namelist.size(); ++x) {
				if (Namelist.get(x).equals(Name)) {
					Array.set(x, Content);
					return;
				}
			}
		}
	}

	public static String get(String Name) {
		if (!Namelist.isEmpty()) {
			for (int x = 0; x < Namelist.size(); ++x) {
				if (Namelist.get(x).equals(Name)) {
					return Array.get(x);
				}
			}
		}

		return "";
	}

	public static boolean contains(String Name) {
		if (!Namelist.isEmpty()) {
			for (int x = 0; x < Namelist.size(); ++x) {
				if (Namelist.get(x).equals(Name)) {
					return Array.contains(x);
				}
			}
		}

		return false;
	}

	public static boolean isExists(String Name) {
		if (!Namelist.isEmpty()) {
			for (String s : Namelist) {
				if (s.equals(Name)) {
					return true;
				}
			}
		}

		return false;
	}

	public static void remove(String Name) {
		if (!Namelist.isEmpty()) {
			for (int x = 0; x < Namelist.size(); ++x) {
				if (Namelist.get(x).equals(Name)) {
					Array.remove(x);
					Namelist.remove(x);
				}
			}
		}
	}
}