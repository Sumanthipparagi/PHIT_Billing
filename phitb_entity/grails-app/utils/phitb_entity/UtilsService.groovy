package phitb_entity

import org.springframework.web.multipart.MultipartFile

import java.text.SimpleDateFormat

class UtilsService
{

//    public static String uploadImage(String folderName, String imageName, MultipartFile imageFile)
//    {
//        String name = "NA"
//        SimpleDateFormat sdf = new SimpleDateFormat("mmDDyyhhmmss")
//        String date = sdf.format(new Date())
//        if (imageFile != null)
//        {
//            def fragments = imageFile.getOriginalFilename().split("\\.")
//            if ((imageFile.size > 1) && (fragments[fragments.length - 1].equalsIgnoreCase("jpg"))
//                    || (fragments[fragments.length - 1].equalsIgnoreCase("jpeg")
//                    || (fragments[fragments.length - 1].equalsIgnoreCase("png"))))
//            {
//                if ((imageName == null) || (imageName.equalsIgnoreCase("NA")))
//                {
//                    name = date + ".jpg"
//                }
//                else
//                {
//                    name = imageName
//                }
//                String uploadedFileLocation = new FileManagementService().getApplicationPath() + folderName + name
//                File objFile = new File(uploadedFileLocation);
//                objFile.mkdirs();
//                if (objFile.exists())
//                {
//                    objFile.delete()
//                }
//                InputStream ios = imageFile.getInputStream()
//                new FileOutputStream(uploadedFileLocation).leftShift(ios).close()
//                ios.close();
//                File file = new File(uploadedFileLocation)
//                /* new UtilsService().resizeImage(file, uploadedFileLocation, false)*/
//            }
//        }
//        return name;
//    }
}
