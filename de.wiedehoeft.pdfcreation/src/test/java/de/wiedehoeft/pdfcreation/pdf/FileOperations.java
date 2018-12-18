package de.wiedehoeft.pdfcreation.pdf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileOperations {

  File directory;
  final List<File> createdFiles;

  FileOperations() {
    this.createdFiles = new ArrayList<>();
  }

  private static final Logger logger = LogManager.getLogger(FileOperations.class);

  FileOperations initDir(String dirPath) {
    directory = new File(dirPath);
    boolean mkdir = directory.mkdir();

    if (mkdir) {
      logger.info("Created new dir at {}", directory.getPath());
    } else {
      logger.error("Unable to create directory at {}", dirPath);
    }

    return this;
  }

  FileOperations createFile(String fileName) {
    String pathname = directory.getPath() + File.separator + fileName;
    File file = new File(pathname);

    boolean createdFile = false;

    try {
      createdFile = file.createNewFile();
    } catch (IOException e) {
      logger.error("Unable to create file at {}. See exception", pathname, e);
    }

    if (createdFile) {
      this.createdFiles.add(file);
      logger.info("Created new file at {}", pathname);
    } else {
      logger.error("Unable to create file at {}.", pathname);
    }

    return this;
  }
}
