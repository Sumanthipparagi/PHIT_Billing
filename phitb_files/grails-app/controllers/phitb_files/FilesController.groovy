package phitb_files

import org.apache.catalina.connector.ClientAbortException

import javax.ws.rs.ClientErrorException

class FilesController {

    def index() { }

    def uploadFile()
    {

    }

    def downloadFile()
    {
        //Security measure as recommended by the owasp zed tool
        response.setHeader("X-Content-Type-Options", "nosniff")
        response.addHeader("X-XSS-Protection", "1; mode=block")
        response.addHeader("X-Frame-Options", "SAMEORIGIN")

        try
        {
            String defaultFileName = new FilesService().getApplicationPath() + "noimage.jpg"
            String path = params.path
            path = URLDecoder.decode(path)
            def fileLocation = new FilesService().getApplicationPath() + File.separator + path

            if (path.toLowerCase().contains('keypair'))
            {
                response.status = 400
                return
            }
            if (path.toLowerCase().contains("_small")) {
                defaultFileName = new FilesService().getApplicationPath() + "noimage.jpg"
                fileLocation = new FilesService().getApplicationPath() + File.separator + path
            }


            File requestedFile = new File(fileLocation)
            println("File Controller: Complete path requested file: " + requestedFile.absolutePath)

            if (requestedFile.isDirectory())
            {
                System.out.println("Requested for folder, instead of file")
                render(text: "No Such File.")
            }
            else
            {
                try
                {
                    if (path.toLowerCase().contains(".jpg") || path.toLowerCase().contains(".png") || path.toLowerCase().contains(".jpeg"))
                    {
                        if (requestedFile.exists())
                        {
                            render(file: requestedFile, contentType: getServletContext().getMimeType(requestedFile.getName()))
                        }
                        else
                        {
                            File defaultFile = new File(defaultFileName);
                            println("File controller: Default path taken... since file " + requestedFile.absolutePath + " doesn't exist.")
                            render(file: defaultFile, contentType: getServletContext().getMimeType(defaultFileName))
                        }
                    }
                    else if (path.toLowerCase().substring(path.length() - 2, path.length()).equals("na"))
                    {
                        System.out.println("File Does not exists");
                        File defaultFile = new File(defaultFileName);
                        render(file: defaultFile, contentType: getServletContext().getMimeType(defaultFileName))
                    }
                    else
                    {
                        if (requestedFile.exists())
                        {
                            render(file: requestedFile, contentType: getServletContext().getMimeType(requestedFile.getName()))
                        }
                        else
                        {
                            File defaultFile = new File(defaultFileName);
                            println("File controller: Default path taken... since file " + requestedFile.absolutePath + " doesn't exist.")
                            render(file: defaultFile, contentType: getServletContext().getMimeType(defaultFileName))
                        }
                    }
                }
                catch (ClientAbortException cl)
                {
                    println("FileLocationController:: " + cl.message)
                }
                catch (ClientErrorException ce)
                {
                    println("FileLocationController:: " + ce.message)
                }
                catch (Exception ex)
                {
                    println("FileLocationController:: " + ex.message)
                }
            }
        }
        catch (Exception ex)
        {
            println("FileLocationController:: " + ex.message)
            render(text: "No Such File.")
        }
    }

    def deleteFile()
    {

    }
}
