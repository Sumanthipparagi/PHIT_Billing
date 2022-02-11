package phitb_ui

import grails.gorm.transactions.Transactional

@Transactional
class FileManagementService {


    /**
     * Function to get the Application Data Storage Directory on the File System.
     * Use this function everywhere in the project. Do not hardcode base path.
     * @return Path String of the application data storage directory
     */
    String getApplicationPath()
    {
        return System.getProperty("user.home") + File.separator + Constants.APPLICATION_FOLDER + File.separator
    }

    /**
     *
     * @param file File object to be saved
     * @param filePathWithExtension path of the file to be stored along with filename and extension
     * @return true if file created, false otherwise
     */
    boolean createFile(File file, String filePathWithExtension)
    {
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
    boolean moveFile(String sourceFilePath, String destinationFilePath)
    {
        File sourceFile = new File(sourceFilePath)
        File destinationFile = new File(destinationFilePath)
        boolean fileMoved = sourceFile.renameTo(destinationFile)
        return fileMoved
    }

//    /**
//     * Function to compress files and create a Zip file
//     * @param files List of files to be zipped
//     * @param zipDestinationPath File path of the destination Zip File to be created
//     * @param deleteSourceFiles
//     * @return Send true if Source files has to be deleted after compression, false to retain the Source files
//     */
//    def zipFiles(ArrayList<File> files, String zipDestinationPath, boolean deleteSourceFiles)
//    {
//        try
//        {
//            ZipFile zipFile = new ZipFile(zipDestinationPath)
//            ZipParameters zipParameters = new ZipParameters()
//            zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE)
//            zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL)
//
//            for (File file : files)
//            {
//                zipFile.addFile(file, zipParameters)
//
//                if (deleteSourceFiles && file.exists())
//                {
//                    file.delete()
//                }
//            }
//        }
//        catch (ZipException e)
//        {
//            e.printStackTrace()
//        }
//    }

//    /**
//     * Function to unZip a zip File and extract all files to destination directory
//     * @param zipFilePath File path with extension of the Zip file to be extracted
//     * @param destinationDirectory Path to the destination Directory
//     * @param deleteZipFile Send true if Zip file has to be deleted after extraction, false to retain the Zip file
//     */
//    static String unZip(String zipFilePath, String destinationDirectory, boolean deleteZipFile)
//    {
//        String password = "password"
//        String imageNames = ""
//        try
//        {
//            ZipFile zipFile = new ZipFile(zipFilePath)
//            if (zipFile.isEncrypted())
//            {
//                zipFile.setPassword(password)
//            }
//
//            ArrayList fileHeaderList = zipFile.getFileHeaders()
//            for (int i = 0; i < fileHeaderList.size(); i++)
//            {
//                FileHeader fileHeader = (FileHeader) fileHeaderList.get(i)
//                SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyyhhmmssSSS")
//                Date dt = new Date()
//                String fileName = sdf.format(dt) + i + ".jpg"
//                imageNames += "," + fileName
//                (new FileOutputStream(destinationDirectory + "/" + fileName) << zipFile.getInputStream(fileHeader)).close()
//            }
//
//            // zipFile.extractAll(destinationDirectory)
//            if ((deleteZipFile) && (zipFile.getFile().exists()))
//            {
//                zipFile.getFile().deleteOnExit()
//            }
//
//        }
//        catch (ZipException e)
//        {
//            e.printStackTrace()
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace()
//        }
//        return imageNames
//    }
}
