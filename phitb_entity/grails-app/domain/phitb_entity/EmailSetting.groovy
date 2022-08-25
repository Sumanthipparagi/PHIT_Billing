package phitb_entity

import gorm.logical.delete.LogicalDelete

class EmailSetting implements LogicalDelete<EmailSetting>, Serializable{

    EntityRegister entity
    String senderMail
    String smtpUsername
    String smtpPassword
    String smtpServer
    String smtpPort //Comma separated
    boolean authenticationRequired
    String encryptionType //null for not required or else "SSL/TLS" or "STARTTLS"
    boolean active //email sending will be enabled or disabled
    String emailService //CUSTOM or DEFAULT

    Date dateCreated
    Date lastUpdated

    static mapping = {

    }
    static constraints = {
        senderMail nullable: true
        smtpUsername nullable: true
        smtpPassword nullable: true, maxSize: 600
        smtpServer nullable: true
        smtpPort nullable: true
        encryptionType nullable: true
        emailService inList: ["DEFAULT", "CUSTOM"], nullable: true
        entity unique: true
    }
    boolean isUpdatable
    static transients = ['isUpdatable']
    def beforeUpdate()
    {

        if (!this.isUpdatable)
        {
            System.out.println("EmailSetting Domain update Prevented " + new Date().toString() + " ,id: " + this.id)
            return false
        }
        else
        {
            System.out.println("EmailSetting Domain Updated " + new Date().toString() + " ,id: " + this.id)
        }
    }
}
