package phitb_ui

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsHttpSession
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject
import org.grails.web.util.WebUtils
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.ws.rs.core.Form
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.math.RoundingMode
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList;
import java.util.Iterator;

@Transactional
class UtilsService {

    HttpServletRequest request = WebUtils.retrieveGrailsWebRequest().currentRequest
    HttpSession session = request.session

    public ArrayList<Date> parseDateRange(String daterange)
    {
        ArrayList<Date> daterangeList = new ArrayList<>()
        String fromDate = daterange.split("-")[0].trim().replace("/", "-");
        String toDate = daterange.split("-")[1].trim().replace("/", "-");

//        SimpleDateFormat originalFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            Date fromdate = originalFormat.parse(fromDate);
            Date todate = originalFormat.parse(toDate);
            fromDate = targetFormat.format(fromdate);
            toDate = targetFormat.format(todate);

            //incrementing to date by one day
            Calendar c = Calendar.getInstance();
            c.setTime(targetFormat.parse(toDate));
            c.add(Calendar.DATE, 1);  // number of days to add
            toDate = targetFormat.format(c.getTime());  // dt is now the new date

            daterangeList.add(fromdate)
            daterangeList.add(todate)
            return daterangeList
        }
        catch (ParseException e)
        {
            e.printStackTrace()
        }
    }

    def resizeImage(File file, String src, boolean crop)
    {
        String[] parts = src.split(".jpg")
        if (!crop)
        {
            src = parts[0] + ".jpg"
        }
        else
        {
            src = parts[0] + "_small.jpg"
        }

        BufferedImage bufferedImage = null
        try
        {
            bufferedImage = ImageIO.read(file)
        }
        catch (IOException e)
        {
            e.printStackTrace()
        }

        BufferedImage newImage = new BufferedImage(
                bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB)

        Graphics2D g = newImage.createGraphics()
        g.drawImage(bufferedImage, 0, 0, null)
        g.dispose()

        //for profile pics, images will be resized
        BufferedImage scaledImage = Scalr.resize(newImage, Scalr.Mode.FIT_TO_WIDTH, 512)
        if (crop)
        {
            //crop is true only for notifications
            scaledImage = Scalr.crop(scaledImage, 0, 0, 512, 256)
        }
        File outputfile = new File(src)
        try
        {
            ImageIO.write(scaledImage, "jpg", outputfile)
        }
        catch (IOException e)
        {
            e.printStackTrace()
        }

    }


    public static String uploadFile(String folderName, MultipartFile imageFile, String imageName)
    {
        String name = "NA"
        SimpleDateFormat sdf = new SimpleDateFormat("mmDDyyhhmmss")
        String date = sdf.format(new Date())
        if (imageFile != null)
        {
            def fragments = imageFile.getOriginalFilename().split("\\.")
            if ((imageFile.size > 1) && (isAllowedFileType(fragments[fragments.length - 1])))
            {
                if ((imageName == null) || (imageName.equalsIgnoreCase("NA")))
                {
                    name = date + "." + fragments[fragments.length - 1]
                }
                else
                {
                    name = imageName
                }

                String uploadedFileLocation = new FileManagementService().getApplicationPath() + folderName + name
                File objFile = new File(uploadedFileLocation);
                objFile.mkdirs();
                if (objFile.exists())
                {
                    objFile.delete();
                }
                InputStream ios = imageFile.getInputStream()
                (new FileOutputStream(uploadedFileLocation) << ios).close()
                ios.close();
//                File file = new File(uploadedFileLocation)
                /* new UtilsService().resizeImage(file, uploadedFileLocation, false)*/
            }
        }
        return name;
    }

    static boolean isAllowedFileType(String fileType)
    {
        String[] fileTypes = ["jpg", "jpeg", "png", "bmp", "gif", "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx"]
        return fileTypes.contains(fileType.toLowerCase());
    }

    static Form jsonToFormDataConverter(JSONObject json)
    {
        Iterator<String> keys = json.keys();
        Form form = new Form()
        while (keys.hasNext())
        {
            String key = keys.next()
            form.param(key, json.get(key) as String)
        }
        return form
    }

    /**
     * This method is used to identify if particular feature is permitted or not from reading the feature list from db
     * @param featureName a particular feature like USER_EDIT is given as input
     * @param permittedFeaturesString a string of features
     * @return
     */
    static boolean isPermitted(String featureName, String permittedFeaturesString)
    {
        if (permittedFeaturesString != null)
        {
            def session = WebUtils.retrieveGrailsWebRequest ().session
            JSONArray permittedFeatures = session.getAttribute("features") as JSONArray
            if(permittedFeatures?.size()>0)
            {
                //check using already saved permissions from session
                for (JSONObject feature : permittedFeatures) {
                    if(featureName.equalsIgnoreCase(feature.get("name").toString()))
                    {
                        return true
                    }
                }
            }
            else
            {
                //make API calls for each permission
                ArrayList<String> permittedFeatures1 = new EntityService().getFeatureList(permittedFeaturesString).name;
                if (permittedFeatures1.contains(featureName))
                {
                    return true;
                }
            }

        }
        return false
    }


    static String dateFormater(String date)
    {
        OffsetDateTime odt = OffsetDateTime.parse(date);
        Instant instant = odt.toInstant();
    }


    public static double round(double value, int places, RoundingMode roundingMode = RoundingMode.HALF_UP) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, roundingMode);
        return bd.doubleValue();
    }

    String truncateString(String str, int length)
    {
        return str.substring(0, Math.min(str.length(), length))
    }

//    static FormDataMultiPart jsonToMultipartFormDataConverter(JSONObject json)
//    {
//        Iterator<String> keys = json.keys();
//        FormDataMultiPart multiPartEntity = new FormDataMultiPart()
//
//        while (keys.hasNext())
//        {
//            String key = keys.next()
//            multiPartEntity.field(key, json.get(key) as String)
//        }
//        return multiPartEntity
//    }

     boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    boolean isValidPhoneNumber(String phoneNumber) {
        boolean result = true;
        try {
           if(phoneNumber?.length() >= 6 && phoneNumber?.length() <= 12){
               result = true;
           }else{
               result = false;
           }
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * This method returns unique link for provided document type
     * @param docNo
     * @param docId
     * @param entityId
     * @param docCode
     * @return Short link with Unique Code
     */
    String generateUniqueLink(String entityId, String docCode, String docId, String docNo)
    {
        try {
            //DocCode: SE,SO,SR,PE,PO,PR,R,P,GRN,GTN
            String uniqueStr = entityId + "|" + docCode + "|" + docId + "|" + docNo
            String encryptedStr = new Cryptography().encryptText(uniqueStr)
            if(encryptedStr) {
                //TODO: generate hash of encryptedStr and store it in DB
                String hexStr = stringToHex(encryptedStr)
                String link = new Constants().SHORT_DOMAIN + hexStr
                return link
            }
            else
                return null
        }
        catch (Exception ex)
        {
            println(ex.stackTrace)
            return null
        }
    }

    /**
     * This method decrypts the unique link and give back the document details
     * @param uniqueCode
     * @return JSONObject with decrypted values ex: entityId, DocNo, DocId, docCode
     */
    JSONObject decryptUniqueLink(String uniqueCode)
    {
        try {
            JSONObject jsonObject = new JSONObject()
            String decryptedStr = new Cryptography().decryptText(hexToString(uniqueCode))
            if (decryptedStr) {
                String[] arr = decryptedStr.split('\\|')
                for (String ob : arr) {
                    if (!jsonObject.has("entityId")) {
                        jsonObject.put("entityId", ob)
                    } else if (!jsonObject.has("docCode")) {
                        jsonObject.put("docCode", ob)
                    } else if (!jsonObject.has("docId")) {
                        jsonObject.put("docId", ob)
                    } else if (!jsonObject.has("docNo")) {
                        jsonObject.put("docNo", ob)
                    }
                }
            }
            return jsonObject
        }
        catch (Exception ex)
        {
            println(ex.stackTrace)
            return null
        }
    }


    /**
     * String to Hex Converter
     * @param input
     * @return hexadecimal representation of given string
     */
    static String stringToHex(String input) {
        return String.format("%x", new BigInteger(1, input.getBytes()));
    }

    /**
     * Hex to String Converter
     * @param input
     * @return Plain String representation of given hex string
     */
    static String hexToString(String input) {
        return new String(new BigInteger(input, 16).toByteArray());
    }

    boolean isDateWithinRange (Date date, Date startDate, Date endDate) {
        return ! (date.before (startDate) || date.after (endDate));
    }

    /**
     * This method remove last comma (',') from String
     * @param characters is String to remove last comma
     * @return String without last Comma
     */
    static String removeLastComma(String characters)
    {

        int length = characters.size()
        if (characters != null && length > 0 && characters.charAt(length - 1).toString() == ",")
        {
            characters = characters.substring(0, length - 1)
        }

        return characters
    }

}
