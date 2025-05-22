package net.Meta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class MetaConsole {
	private static String LogTemplate = "";
	private static boolean NoRepeat = true;
	
	public static void out(String Package, int Mode, boolean Important) {
		if (Enable.LogMode) {
			String Date = MetaTime.getDate();
			int x = 1;
			
			if (!new File(Enable.LogFolder).exists()) {
				new File(Enable.LogFolder).mkdir();
			}
			if (!new File(Enable.LogFolder + "/" + Date + ".log").exists()) {
				try {
					new File(Enable.LogFolder + "/" + Date + ".log").createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				LogTemplate = (Enable.LogFolder + "/" + Date + ".log");
				NoRepeat = false;
			}
			
			if (new File(Enable.LogFolder + "/" + Date + ".log").exists() && NoRepeat) {
				while (true) {
					if (!new File(Enable.LogFolder + "/" + Date + "(" + x + ").log").exists()) {
						try {
							new File(Enable.LogFolder + "/" + Date + "(" + x + ").log").createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						LogTemplate = (Enable.LogFolder + "/" + Date + "(" + x + ").log");
						x = 1;
						break;
					}
					
					++x;
				}
				
				NoRepeat = false;
			}
		} else {
			if (new File(Enable.LogFolder).exists()) {
				File DeleteFolder = new File(Enable.LogFolder);
				String[] entries = DeleteFolder.list();
				
				for (int x = 0; x < Objects.requireNonNull(entries).length; ++x) {
					File aktFile = new File(DeleteFolder.getPath(), entries[x]);
					aktFile.delete();
				}
				DeleteFolder.delete();
			}
		}

		String time = MetaTime.getTime();
		if (Enable.LogMode) {
			try {
				FileWriter Writer = new FileWriter(new File(LogTemplate), true);
				if (Mode == 0) {
					Writer.write("[" + time + " INFO]: " + Package);
				} else if (Mode == 1) {
					Writer.write("[" + time + " WARNING]: " + Package);
				} else if (Mode == 2) {
					Writer.write("[" + time + " ERROR]: " + Package);
				}
				Writer.write(System.lineSeparator());
				
				Writer.flush();
				Writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (!Important && !Enable.FullLogging) {
			return;
		}
		
		if (Mode == 0) {
			System.out.println("[" + time + " INFO]: " + Package);
		} else if (Mode == 1) {
			System.err.println("[" + time + " WARNING]: " + Package);
		} else if (Mode == 2) {
			System.err.println("[" + time + " ERROR]: " + Package);
		}
	}
	
	public static void outPrint(String Package, int Mode, boolean Important) {
		if (Enable.LogMode) {
			String Date = MetaTime.getDate();
			int x = 1;
			
			if (!new File(Enable.LogFolder).exists()) {
				new File(Enable.LogFolder).mkdir();
			}
			if (!new File(Enable.LogFolder + "/" + Date + ".log").exists()) {
				try {
					new File(Enable.LogFolder + "/" + Date + ".log").createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				LogTemplate = (Enable.LogFolder + "/" + Date + ".log");
				NoRepeat = false;
			}
			
			if (new File(Enable.LogFolder + "/" + Date + ".log").exists() && NoRepeat) {
				while (true) {
					if (!new File(Enable.LogFolder + "/" + Date + "(" + x + ").log").exists()) {
						try {
							new File(Enable.LogFolder + "/" + Date + "(" + x + ").log").createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
						LogTemplate = (Enable.LogFolder + "/" + Date + "(" + x + ").log");
						x = 1;
						break;
					}
					
					++x;
				}
				
				NoRepeat = false;
			}
		} else {
			if (new File(Enable.LogFolder).exists()) {
				File DeleteFolder = new File(Enable.LogFolder);
				String[] entries = DeleteFolder.list();
				
				for (int x = 0; x < Objects.requireNonNull(entries).length; ++x) {
					File aktFile = new File(DeleteFolder.getPath(), entries[x]);
					aktFile.delete();
				}
				DeleteFolder.delete();
			}
		}

		String time = MetaTime.getTime();
		if (Enable.LogMode) {
			try {
				FileWriter Writer = new FileWriter(new File(LogTemplate), true);
				if (Mode == 0) {
					Writer.write("[" + time + " INFO]: " + Package);
				} else if (Mode == 1) {
					Writer.write("[" + time + " WARNING]: " + Package);
				} else if (Mode == 2) {
					Writer.write("[" + time + " ERROR]: " + Package);
				}
				Writer.write(System.lineSeparator());
				
				Writer.flush();
				Writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (!Important && !Enable.FullLogging) {
			return;
		}
		
		if (Mode == 0) {
			System.out.print("[" + time + " INFO]: " + Package);
		} else if (Mode == 1) {
			System.err.print("[" + time + " WARNING]: " + Package);
		} else if (Mode == 2) {
			System.err.print("[" + time + " ERROR]: " + Package);
		}
	}
	
	public static void outException(String Exception, String Path) {
		out(Exception + " : Exception at " + Path, 2, true);
	}
}