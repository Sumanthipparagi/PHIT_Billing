package phitb_ui
import java.nio.file.Files

class FileLocationController {

    def index()
    {

        System.out.println(params.path)
        String defaultFile = System.getProperty("user.home") + File.separator + Constants.APPLICATION_FOLDER + File
                .separator+ "noimage.jpg"
        def path = params.path
        def fileLocation = System.getProperty("user.home") + File.separator + Constants.APPLICATION_FOLDER + File
                .separator + path

        if (path.toLowerCase().contains("user"))
        {
            defaultFile = System.getProperty("user.home") + File.separator + Constants.APPLICATION_FOLDER + File
                    .separator + "default" +
                    ".jpg"
        }
        File file = new File(fileLocation)
        if (file.isDirectory())
        {
            System.out.println("Requested for folder, instead of file")
        }


        if (path.toLowerCase().contains(".jpg") || path.toLowerCase().contains(".png") || path.toLowerCase().contains
                (".jpeg")|| path.toLowerCase().contains(".svg"))
        {
            if (!file.exists())
            {
                File file1 = new File(defaultFile)
                response.setHeader("Content-Type", getServletContext().getMimeType(defaultFile));
                response.setHeader("Content-Length", String.valueOf(file1.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file1.getName() + "\"");
                Files.copy(file1.toPath(), response.getOutputStream())
            }
            else
            {
                response.setHeader("Content-Type", getServletContext().getMimeType(fileLocation));
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                Files.copy(file.toPath(), response.getOutputStream())
            }
        }
        else if (path.toLowerCase().contains(".apk"))
        {
            if (!file.exists())
            {
                System.out.println("APK");
                response.setHeader("Content-Type", "application/vnd.android.package-archive")
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"")
                Files.copy(file.toPath(), response.getOutputStream())
            }
            else
            {
                File file1 = new File(defaultFile);
                response.setHeader("Content-Type", getServletContext().getMimeType(defaultFile));
                response.setHeader("Content-Length", String.valueOf(file1.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file1.getName() + "\"");
                Files.copy(file1.toPath(), response.getOutputStream());
            }
        }
        else if (path.toLowerCase().contains(".pdf"))
        {
            if (file.exists())
            {
                render(file: file, contentType: 'application/pdf')
            }
            else
            {
                File file1 = new File(defaultFile)
                response.setHeader("Content-Type", getServletContext().getMimeType(defaultFile));
                response.setHeader("Content-Length", String.valueOf(file1.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file1.getName() + "\"");
                Files.copy(file1.toPath(), response.getOutputStream())
            }
        }
        else if (path.toLowerCase().contains(".pptx"))
        {
            if (file.exists())
            {
                render(file: file, contentType: 'application/vnd.openxmlformats-officedocument.presentationml.presentation')
            }
            else
            {
                File file1 = new File(defaultFile)
                response.setHeader("Content-Type", getServletContext().getMimeType(defaultFile));
                response.setHeader("Content-Length", String.valueOf(file1.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file1.getName() + "\"");
                Files.copy(file1.toPath(), response.getOutputStream())
            }
        }
        else if (path.toLowerCase().substring(path.length() - 2, path.length()).equals("na"))
        {
            System.out.println("File Does not exists")
            File file1 = new File(defaultFile)
            response.setHeader("Content-Type", getServletContext().getMimeType(defaultFile));
            response.setHeader("Content-Length", String.valueOf(file1.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + file1.getName() + "\"");
            Files.copy(file1.toPath(), response.getOutputStream())
        }

        else
        {
            if (!file.exists())
            {
                response.setHeader("Content-Type", getServletContext().getMimeType(fileLocation));
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                Files.copy(file.toPath(), response.getOutputStream())
            }
            else
            {
                File file1 = new File(defaultFile);
                response.setHeader("Content-Type", getServletContext().getMimeType(defaultFile));
                response.setHeader("Content-Length", String.valueOf(file1.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + file1.getName() + "\"");
                Files.copy(file1.toPath(), response.getOutputStream())
            }
        }
    }

}
