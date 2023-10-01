package phitb_files

import gorm.logical.delete.LogicalDelete

class UploadedFile implements Serializable, LogicalDelete<UploadedFile> {

    String fileName
    String folderName
    String docType
    String docNumber
    String description

    long uploadedByUser //store user id
    long entityId

    Date dateCreated
    Date lastUpdated

    static constraints = {
        docType nullable: true
        docNumber nullable: true
        description nullable: true
    }
}
