package phitb_files

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat
import java.util.zip.ZipFile

@Transactional
class FilesService {

    public static String APPLICATION_FOLDER = "phitb_files";
    /**
     * Function to get the Application Data Storage Directory on the File System.
     * Use this function everywhere in the project. Do not hardcode base path.
     * @return Path String of the application data storage directory
     */
    def String getApplicationPath() {
        return System.getProperty("user.home") + File.separator + APPLICATION_FOLDER + File.separator;
    }

    /**
     *
     * @param file File object to be saved
     * @param filePathWithExtension path of the file to be stored along with filename and extension
     * @return true if file created, false otherwise
     */
    def boolean createFile(File file, String filePathWithExtension) {
        return file.renameTo(new File(filePathWithExtension))
    }

    /**
     * Function to copy the contents of one file to another.
     * @param sourceFilePath File path of the Source File
     * @param destinationFilePath File path of the Destination File
     * @return
     */
    /*def copyFile(String sourceFilePath, String destinationFilePath) {
        (new AntBuilder()).copy(file: sourceFilePath, tofile: destinationFilePath)
    }*/

    /**
     * Function to Copy the files from one directory to another by retaining its folder structure.
     * @param sourceDir
     * @param destinationDir
     * @return
     */
    /*def copyDirectoryToAnotherDirectory(String sourceDir, String destinationDir) {
        new AntBuilder().copy(todir: destinationDir) {
            fileset(dir: sourceDir)
        }
    }*/

    /**
     * Function to move a File from Source to Destination
     * @param sourceFilePath File path of the Source File
     * @param destinationFilePath File path of the Destination File
     * @return true if file is moved, false otherwise
     */
    def boolean moveFile(String sourceFilePath, String destinationFilePath) {
        File sourceFile = new File(sourceFilePath);
        File destinationFile = new File(destinationFilePath);
        boolean fileMoved = sourceFile.renameTo(destinationFile);
        return fileMoved
    }

}
