package phitb_files

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        group "/api/v1.0/files", {
            "/upload"(controller: 'files', action: 'uploadFile')
            "/download"(controller: 'files', action: 'downloadFile')
            "/delete"(controller: 'files', action: 'deleteFile')
            "/canvasimageuploadtoftp"(controller:'files', action: 'saveCanvasImage')
        }
    }
}
