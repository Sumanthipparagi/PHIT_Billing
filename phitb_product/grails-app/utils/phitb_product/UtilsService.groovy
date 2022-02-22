package phitb_product

class UtilsService {

    static def convertArrayToString(String stringInput)
    {
        if (stringInput)
        {
            stringInput = stringInput.trim()
            stringInput = removeFirstChar(stringInput,"[")
            stringInput = removeLastChar(stringInput,"]")
            return stringInput?.split(', ')
        }
        return null
    }

    static String removeFirstChar(String characters, String removableChar)
    {
        int length = characters.size()
        if (characters != null && length > 0 && characters.charAt(0) == removableChar)
        {
            characters = characters.substring(1, length)
        }
        return characters
    }

    static String removeLastChar(String characters, String removableChar)
    {
        int length = characters.size()
        if (characters != null && length > 0 && characters.charAt(length - 1) == removableChar)
        {
            characters = characters.substring(0, length - 1)
        }
        return characters
    }
}
