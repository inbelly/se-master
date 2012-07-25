package com.megalogika.sv.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileService {
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	private File destDir = null;

	/**
	 * Nukopijuoja faila i vieta paskirta destDir ir grazina naujo failo varda
	 * 
	 * @throws IOException
	 */
	public String putFileToPlace(MultipartFile file) throws IOException {
		File newFile = getNewFile(file);
		file.transferTo(newFile);
		return newFile.getName();
	}

	private File getNewFile(MultipartFile file) {
		String fileName = fixFileName(file.getOriginalFilename());
		
		File newFile = new File(destDir,fileName);
		if (newFile.exists()) {
			newFile = new File(destDir, getDestinationFileName(fileName));
		}
		return newFile;
	}

	private String fixFileName(String fileName) {
		while(fileName.contains("..")){
			fileName = fileName.replace("..", ".");
		}
		
		if(fileName.lastIndexOf(".")==0){
			fileName = System.currentTimeMillis() + fileName;
		}
		if(fileName.startsWith(".")){
			fileName = fileName.substring(1, fileName.length());
		}
		return fileName;
	}

	public static String getDestinationFileName(String fileName) {
		int cutIndex = fileName.lastIndexOf(".");
		String fileExt = "";
		String fileBase = "";
		if (-1 != cutIndex) {
			fileExt = fileName.substring(cutIndex);
			fileBase = fileName.substring(0, fileName.lastIndexOf("."));
			return fileBase + Long.toString(System.currentTimeMillis()) + fileExt;
		}
		return fileName + Long.toString(System.currentTimeMillis());
	}

	public boolean isImage(MultipartFile file) {
		return "image/pjpeg".equals(file.getContentType()) || "image/jpeg".equals(file.getContentType()) || "image/gif".equals(file.getContentType())
				|| "image/png".equals(file.getContentType()) || "image/x-png".equals(file.getContentType());
	}

	public File getThumbnailPath() {
		return new File(destDir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg");
	}

	public File getDestDir() {
		return destDir;
	}

	public void setDestDir(File destDir1) {
		this.destDir = destDir1;
	}
	
	public boolean deleteFile(String filePath) {
		String fileName = destDir.getAbsolutePath() + File.separator + filePath.substring(filePath.lastIndexOf(File.separator)+1);
		File f = new File(fileName);
		if (!f.exists()) {
			//throw new IllegalArgumentException("FileService.deleteFile(): no such file or directory: " + fileName);
			logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not exist");
		}
		if (!f.canWrite()) {
			logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not allowed to delete");
		}
		if (f.isDirectory()) {
			logger.debug("FileService.deleteFile(): can't delete " + fileName + " - not file, but directory");
	    }
		return f.delete();
	}
	
	public void delete(String fileName) {
		File f = new File(destDir, fileName);
		if (f.exists()) {
			if (!f.delete()) {
				throw new IllegalArgumentException("Nepavyko ištrinti failo " + f);
			}
		} else {
			logger.debug("Failo " + f + " nėra failų sistemoje, taigi nėra ką trinti!");
			throw new IllegalArgumentException("Failo " + f + " nėra failų sistemoje, taigi nėra ką trinti!");
		}
	}
}