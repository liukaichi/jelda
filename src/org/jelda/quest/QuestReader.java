package org.jelda.quest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest;
import org.jelda.quest.sprite.SpritePool;

public class QuestReader {
	public static Quest loadQuest(File f) {
		if (isValidQuestFile(f)) {
			File tempFolder;
			try {
				tempFolder = unzipToFolder(f);
			} catch (IOException e) {
				Write.error("Could not load file " + f.getAbsolutePath());
				return null;
			}
			Quest quest = new Quest();
			quest.setQuestManifest(new Manifest(new File(tempFolder, "quest.manifest")));
			Write.info("Loaded manifest:\n\tname "+ quest.getQuestName() + "\n\tversion " + quest.getQuestVersion() + "\n\tauthor " +quest.getQuestAuthor());
			quest.setSpritePool(new SpritePool(new File(tempFolder, "sprites")));
			Write.info("Indexed " + quest.getNumSprites() + " sprites: " + quest.getSpriteFilenames());
			JOptionPane.showMessageDialog(null, null, null, JOptionPane.DEFAULT_OPTION, new ImageIcon(quest.getSpritePool().getSprite("2")));
			
			return quest;
		} else {
			Write.error(f.getAbsolutePath() + " is not a valid quest file");
			return null;
		}
	}
	


	public static boolean isValidQuestFile(File f) {
		return isValidZip(f);
	}

	private static boolean isValidZip(File f) {
		ZipFile zipfile = null;
		try {
			zipfile = new ZipFile(f);
			return true;
		} catch (ZipException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (zipfile != null) {
					zipfile.close();
					zipfile = null;
				}
			} catch (IOException e) {
			}
		}
	}

	public static File unzipToFolder(File f) throws IOException {
		
		File outDir = new File("." + File.separator + "temp" + File.separator
				+ f.getName());
		if (shouldReExtract(f, outDir)) {
			Write.info("Deleting " + outDir.getAbsolutePath());
			Write.info("Reading from " + f.getAbsolutePath()
					+ "\nWriting to " + outDir.getAbsolutePath());
			delete(outDir);
			outDir.mkdir();
			/*
			 * STEP 1 : Create directory with the name of the zip file
			 * 
			 * For e.g. if we are going to extract c:/demo.zip create c:/demo
			 * directory where we can extract all the zip entries
			 * 
			 * 
			 * File fSourceZip = new File(strZipFile); String zipPath =
			 * strZipFile.substring(0, strZipFile.length()-4); File temp = new
			 * File(zipPath); temp.mkdir(); System.out.println(zipPath +
			 * " created");
			 * 
			 * 
			 * STEP 2 : Extract entries while creating required sub-directories
			 */
			ZipFile zipFile = new ZipFile(f);
			Enumeration<? extends ZipEntry> e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = e.nextElement();
				File destinationFilePath = new File(outDir, entry.getName());

				// create directories if required.
				destinationFilePath.getParentFile().mkdirs();

				// if the entry is directory, leave it. Otherwise extract it.
				if (entry.isDirectory()) {
					continue;
				} else {

					/*
					 * Get the InputStream for current entry of the zip file
					 * using
					 * 
					 * InputStream getInputStream(Entry entry) method.
					 */
					BufferedInputStream bis = new BufferedInputStream(
							zipFile.getInputStream(entry));

					int b;
					byte buffer[] = new byte[1024];

					/*
					 * read the current entry from the zip file, extract it and
					 * write the extracted file.
					 */
					FileOutputStream fos = new FileOutputStream(
							destinationFilePath);
					BufferedOutputStream bos = new BufferedOutputStream(fos,
							1024);

					while ((b = bis.read(buffer, 0, 1024)) != -1) {
						bos.write(buffer, 0, b);
					}

					// flush the output stream and close it.
					bos.flush();
					bos.close();

					// close the input stream.
					bis.close();
				}
			}
			zipFile.close();
		}

		return outDir;
	}

	private static boolean shouldReExtract(File f, File outDir) {
		// TODO Implement an overwrite protocol. Maybe if checksum is different?
		return true;
	}

	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			
		}
	}

	public static void main(String[] args) {
		QuestReader.loadQuest(new File("./quests/quests.zip"));
	}
}
