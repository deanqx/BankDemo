package net.Meta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MetaExpress {
	public static void CreateFolder(String Folder) {
		if (!new File(Folder).exists()) {
			new File(Folder).mkdir();
			MetaConsole.out("Folder ( " + Folder + " ) was created.", 0, false);
		} else {
			MetaConsole.out("Folder ( " + Folder + " ) already exists!", 2, true);
		}
	}

	public static void DeleteFolder(String Folder) {
		if (new File(Folder).isDirectory()) {
			String[] entries = new File(Folder).list();

			for (int x = 0; x < Objects.requireNonNull(entries).length; ++x) {
				File aktFile = new File(new File(Folder).getPath(), entries[x]);
				aktFile.delete();
			}
			new File(Folder).delete();
			MetaConsole.out("Folder ( " + Folder + " ) was deleted.", 0, false);
		}
	}

	public static boolean isFolderExists(String Folder) {
		if (new File(Folder).exists()) {
			MetaConsole.out("Folder ( " + Folder + " ) is exists.", 0, false);
			
			return true;
		} else {
			MetaConsole.out("Folder ( " + Folder + " ) isn't exists.", 0, false);
			
			return false;
		}
	}

	public static void Create(String Path) {
		if (!new File(Path).exists()) {
			try {
				new File(Path).createNewFile();

				MetaConsole.out("File ( " + Path + " ) was created.", 0, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			MetaConsole.out("File ( " + Path + " ) already exists!", 2, true);
		}
	}

	public static void Delete(String Path) {
		if (new File(Path).exists()) {
			new File(Path).delete();
			MetaConsole.out("File ( " + Path + " ) was deleted.", 0, false);
		} else {
			MetaConsole.out("File ( " + Path + " ) not found!", 2, true);
		}
	}

	public static void Rename(String OldPath, String NewPath) {
		if (new File(OldPath).exists()) {

			new File(OldPath).renameTo(new File(NewPath));
			MetaConsole.out("File ( " + OldPath + " ) renamed to ( " + NewPath + " ).", 0, false);
		} else {
			MetaConsole.out("File ( " + OldPath + " ) not found!", 2, true);
		}
	}

	public static void Write(String Path, String Content, boolean Replace) {
		Replace = !Replace;

		if (new File(Path).exists()) {
			try {
				FileWriter Writer = new FileWriter(new File(Path), Replace);
				
				Writer.write(Content);
				Writer.write(System.lineSeparator());
				MetaConsole.out("File ( " + Path + " ) write output ( " + Content + " ).", 0, false);

				Writer.flush();
				Writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			MetaConsole.out("File ( " + Path + " ) not found!", 2, true);
		}
	}

	public static List<String> Read(String Path) {
		if (new File(Path).exists()) {
			try {
				BufferedReader Reader = new BufferedReader(new FileReader(new File(Path)));
				String Line = "";
				List<String> Output = new ArrayList<>();

				while ((Line = Reader.readLine()) != null) {
					Output.add(Line);
				}

				Reader.close();
				MetaConsole.out("File ( " + Path + " ) readed.", 0, false);
				return Output;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			MetaConsole.out("File ( " + Path + " ) not found!", 2, true);
		}

		return null;
	}

	public static void setPropertie(String Path, String Key, String Content) {
		boolean Change = false;

		if (new File(Path).exists()) {
			if (!(new File(Path).length() == 0)) {
				String[] Propertie;
				List<String> Input = Read(Path);

				assert Input != null;
				for (int x = 0; x <= Input.size() - 1; ++x) {
					Propertie = Input.get(x).split("=");
					if (Propertie[0].equals(Key)) {
						Change = true;
						break;
					}
				}
				
				if (Change) {
					Delete(Path);
					Create(Path);

					for (int x = 0; x <= Input.size() - 1; ++x) {
						Propertie = Input.get(x).split("=");
						if (Propertie[0].equals(Key)) {
							Write(Path, Propertie[0] + "=" + Content, false);
							MetaConsole.out("Propertie ( " + Propertie[0] + " ) changed to ( " + Content + " ).", 0, false);
							continue;
						}

						Write(Path, Input.get(x), false);
					}
					
					return;
				}
			}
			
			Write(Path, Key + "=" + Content, false);
			MetaConsole.out("Propertie ( " + Key + " ) set to ( " + Content + " ).", 0, false);
		} else {
			MetaConsole.out("File ( " + Path + " ) not found!", 2, true);
		}
	}

	public static String getPropertie(String Path, String Key) {
		if (new File(Path).exists()) {
			String[] Propertie;
			List<String> Input = Read(Path);

			assert Input != null;
			for (int x = 0; x <= Input.size(); ++x) {
				Propertie = Input.get(x).split("=");
				if (Propertie[0].equals(Key)) {
					MetaConsole.out("File ( " + Path + " ) at Key=" + Key + " loaded!", 0, false);
					try {
						return Propertie[1];
					} catch (Exception e) {
					}
				}
			}

			MetaConsole.out("Key ( " + Key + " ) in ( " + Path + " ) not found!", 1, true);
		} else {
			MetaConsole.out("File ( " + Path + " ) not found!", 2, true);
		}

		return "";
	}
	
	public static boolean containsPropertie(String Path, String Key) {
		if (new File(Path).exists()) {
			if (!(new File(Path).length() == 0)) {
				String[] Propertie;
				List<String> Input = Read(Path);

				assert Input != null;
				for (int x = 0; x <= Input.size() - 1; ++x) {
					Propertie = Input.get(x).split("=");
					if (Propertie[0].equals(Key)) {
						return true;
					}
				}
				
				return false;
			}
			
			MetaConsole.out("Propertie ( " + Key + " ) contains true.", 0, false);
		} else {
			MetaConsole.out("File ( " + Path + " ) not found!", 2, true);
		}
		
		return false;
	}

	public static boolean isExists(String Path) {
		if (new File(Path).exists()) {
			MetaConsole.out("File ( " + Path + " ) is exists.", 0, false);
			return true;
		} else {
			MetaConsole.out("File ( " + Path + " ) isn't exists.", 0, false);
			return false;
		}
	}
}