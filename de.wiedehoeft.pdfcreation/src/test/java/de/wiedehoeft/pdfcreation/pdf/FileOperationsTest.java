package de.wiedehoeft.pdfcreation.pdf;

import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TemporaryFolderExtension.class)
class FileOperationsTest {

  private TemporaryFolder temporaryFolder;

  @BeforeEach
  void prepare(TemporaryFolder temporaryFolder) {
    this.temporaryFolder = temporaryFolder;
  }

  @Test
  void shouldCreateNewDirWithName() {
    // Arrange
    String rootPath = temporaryFolder.getRoot().getPath();
    String path = String.format("%s/pdfs", rootPath);

    // Act
    File directory = new FileOperations().initDir(path).directory;

    // Assert
    assertThat(directory).exists();
    assertThat(directory.getName()).isEqualTo("pdfs");
  }

  @Test
  void shouldCreateNewFileInDir() {
    // Arrange
    String rootPath = temporaryFolder.getRoot().getPath();
    String path = String.format("%s/pdfs", rootPath);
    FileOperations fileOperations = new FileOperations();
    File directory = fileOperations.initDir(path).directory;

    // Act
    fileOperations.createFile("testFile.pdf");

    // Assert
    List<File> createdFiles = fileOperations.createdFiles;
    assertThat(createdFiles.size()).isEqualTo(1);
    assertThat(createdFiles.get(0).getName()).isEqualTo("testFile.pdf");
  }
}
