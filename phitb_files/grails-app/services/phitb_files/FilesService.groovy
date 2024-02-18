package phitb_files

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat
import java.util.zip.ZipFile
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPReply
import org.springframework.web.multipart.MultipartFile
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


    public static String uploadFileDynamicallyToFTP(String type, InputStream inputStream, String fileName, String entityId) {
        FTPClient ftpClient = new FTPClient();
        String dirToCreate = "/" + type + "/" + entityId + "/";
        try {
            ftpClient.connect("webikind.net", 21);
            ftpClient.login("pharmitdev2@webikind.net", "pharmit!23");

            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new Exception("FTP server refused connection.");
            }

            // Check if the directory exists and create it if it doesn't
            if (!ftpClient.changeWorkingDirectory(dirToCreate)) {
                String[] dirs = dirToCreate.split("/");
                String currentDir = "";
                for (String dir : dirs) {
                    currentDir += "/" + dir;
                    if (!dir.isEmpty()) {
                        if (!ftpClient.changeWorkingDirectory(currentDir)) {
                            if (!ftpClient.makeDirectory(currentDir)) {
                                throw new Exception("Failed to create directory: " + currentDir);
                            }
                        }
                    }
                }
            }

            ftpClient.changeWorkingDirectory(dirToCreate);

            // Switch to active mode
            ftpClient.enterLocalActiveMode(); // Use this instead of enterLocalPassiveMode()

            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // Attempt to upload the file
            if (!ftpClient.storeFile(fileName, inputStream)) {
                throw new Exception("Error uploading file to FTP server. Reply Code: " + ftpClient.getReplyCode() + ", Reply String: " + ftpClient.getReplyString());
            }

            return dirToCreate + fileName;
        } catch (Exception ex) {
            System.err.println("Error in uploadFileDynamicallyToFTP: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        } finally {
            // Ensure resources are always properly closed
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                System.err.println("Error closing FTP connection: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

}