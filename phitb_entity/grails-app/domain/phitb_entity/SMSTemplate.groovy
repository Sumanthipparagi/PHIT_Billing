package phitb_entity

import gorm.logical.delete.LogicalDelete

class SMSTemplate implements LogicalDelete<SMSTemplate> {

    String template
    String templateName
    String templateId
    String senderId
    boolean active
    EntityRegister entityRegister
    Date dateCreated
    Date lastUpdated

    static constraints = {
        entityRegister nullable: true
    }
}
