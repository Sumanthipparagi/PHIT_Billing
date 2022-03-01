package phitb_entity

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject
import phitb_entity.Exception.BadRequestException
import phitb_entity.Exception.ResourceNotFoundException

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException

@Transactional
class AuthRegisterService {

    private static final Random RANDOM = new SecureRandom()
    private static final int ITERATIONS = 10000
    private static final int KEY_LENGTH = 256

    def getAll(String limit, String offset, String query) {
        Integer o = offset ? Integer.parseInt(offset.toString()) : 0
        Integer l = limit ? Integer.parseInt(limit.toString()) : 100
        if (!query)
            return AuthRegister.findAll([sort: 'id', max: l, offset: o, order: 'desc'])
        else
            return AuthRegister.findAllByUsernameIlike("%" + query + "%", [sort: 'id', max: l, offset: o, order:
                    'desc'])
    }

    AuthRegister get(String id) {
        return AuthRegister.findById(Long.parseLong(id))
    }

    JSONObject dataTables(JSONObject paramsJsonObject, String start, String length) {
        String searchTerm = paramsJsonObject.get("search[value]")
        String orderColumnId = paramsJsonObject.get("order[0][column]")
        String orderDir = paramsJsonObject.get("order[0][dir]")

        String orderColumn = "id"
        switch (orderColumnId) {
            case '0':
                orderColumn = "id"
                break;
            case '1':
                orderColumn = "startDate"
                break;
        }
        Integer offset = start ? Integer.parseInt(start.toString()) : 0
        Integer max = length ? Integer.parseInt(length.toString()) : 100
        def authRegisterCriteria = AuthRegister.createCriteria()
        def authRegisterCriteriaArrayList = authRegisterCriteria.list(max: max, offset: offset) {
            or {
                if (searchTerm != "") {
                    ilike('startDate', '%' + searchTerm + '%')
                }
            }
            eq('deleted', false)
            order(orderColumn, orderDir)
        }
        def recordsTotal = authRegisterCriteriaArrayList.totalCount
        JSONObject jsonObject = new JSONObject()
        jsonObject.put("draw", paramsJsonObject.draw)
        jsonObject.put("recordsTotal", recordsTotal)
        jsonObject.put("recordsFiltered", recordsTotal)
        jsonObject.put("data", authRegisterCriteriaArrayList)
        return jsonObject
    }

    AuthRegister save(JSONObject jsonObject) {
        AuthRegister authRegister = new AuthRegister()
        authRegister.username = jsonObject.get("username").toString()
        authRegister.password = jsonObject.get("password").toString()
        authRegister.user = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
        authRegister.save(flush: true)
        if (!authRegister.hasErrors())
            return authRegister
        else
            throw new BadRequestException()
    }

    AuthRegister update(JSONObject jsonObject, String id) {
        AuthRegister authRegister = AuthRegister.findById(Long.parseLong(id))
        if (authRegister) {
            authRegister.isUpdatable = true
            authRegister.username = jsonObject.get("username").toString()
            authRegister.password = jsonObject.get("password").toString()
            authRegister.user = UserRegister.findById(Long.parseLong(jsonObject.get("user").toString()))
            authRegister.save(flush: true)
            if (!authRegister.hasErrors())
                return authRegister
            else
                throw new BadRequestException()
        } else
            throw new ResourceNotFoundException()
    }

    void delete(String id) {
        if (id) {
            AuthRegister authRegister = AuthRegister.findById(Long.parseLong(id))
            if (authRegister) {
                authRegister.isUpdatable = true
                authRegister.delete()
            } else {
                throw new ResourceNotFoundException()
            }
        } else {
            throw new BadRequestException()
        }
    }




    /**
     * Returns a random salt to be used to hash a password.
     * @return a 16 bytes random salt
     */
    def getNextSalt()
    {
        byte[] salt = new byte[16]
        RANDOM.nextBytes(salt)
        return salt
    }

    /**
     * Returns a salted and hashed password using the provided hash.<br>
     * Note - side effect: the password is destroyed (the char[] is filled with zeros)
     *
     * @param password the password to be hashed
     * @param salt a 16 bytes salt, ideally obtained with the getNextSalt method
     * @return the hashed password with a pinch of salt
     */
    byte[] hash(char[] password, byte[] salt)
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH)
        Arrays.fill(password, Character.MIN_VALUE)
        try
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            return skf.generateSecret(spec).getEncoded()
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e)
        }
        finally
        {
            spec.clearPassword()
        }
    }

    /**
     * Convert byte array to Hex Binary
     * @param array byte array
     * @return Hex Binary string
     */
    String toHexString(byte[] array)
    {
        return DatatypeConverter.printHexBinary(array)
    }

    /**
     * Convert String  to byte array
     * @param s string
     * @return byte array of String
     */
    byte[] toByteArray(String s)
    {
        return DatatypeConverter.parseHexBinary(s)
    }

    /**
     * Convert string into Hashed String
     * @param rawPassword string to Hash
     * @return hashed String
     */
    def hashPassword(String rawPassword)
    {
        //set sender salt
        byte[] salt = getNextSalt()
        String password1 = toHexString(hash(rawPassword.toCharArray(), salt))
        //replace original password with hashed password in client request
        return password1 + "_" + toHexString(salt)
    }

    /**
     *
     * @param accessToken
     * @param mobile
     * @return
     */
   /* Auth accessTokenVerify(String accessToken, String mobile)
    {
        String accessTokenURL = new MethodHandleNatives.Constants().FACEBOOK_SMS_VERIFICATION_LINK + accessToken
        Client client = ClientBuilder.newClient()
        Response response = client.target(accessTokenURL).request().get()
        if (response.getStatus() == 200)
        {
            String resp = response.readEntity(String.class)
            JSONObject jsonObject = JSON.parse(resp) as JSONObject
            JSONObject jsonObject1 = jsonObject.("phone")
            if (jsonObject1.get("national_number").toString() == mobile)
            {

                User user = User.findByPhone(mobile)
                //if(sender != null&&(!sender.isWebUser))
                if (user != null)
                {
                    def auth = Auth.findByUser(user)
                    if (auth != null)
                    {
                        return auth
                    }
                    else
                    {
                        System.err.println("accessTokenVerify: Fail! auth null")
                        println("accessTokenVerify: Fail! auth null")
                        return null
                    }

                }
                else
                {
                    System.err.println("accessTokenVerify: Fail! sender null")
                    println("accessTokenVerify: Fail! sender null")
                    return null
                }
            }
            else
            {
                System.err.println("accessTokenVerify: Fail! JSON error: " + jsonObject.toString())
                println("accessTokenVerify: Fail! JSON error: " + jsonObject.toString())
                return null
            }

        }
        else
        {
            System.err.println("accessTokenVerify: Fail! Status from FB: " + response.getStatus())
            println("accessTokenVerify: Fail! Status from FB: " + response.getStatus())
            return null
        }
    }*/

    /*static PasswordHistory addPasswordHistory(
            String username, String password, String userType)
    {
        PasswordHistory userPasswordHistory = new PasswordHistory()
        userPasswordHistory.username = username
        userPasswordHistory.password = password
        userPasswordHistory.userType = userType
        PasswordHistory savedHistory = userPasswordHistory.save(flush: true)
        if (savedHistory)
        {
            oldPasswordDelete(username, userType)
            return savedHistory
        }
        return null
    }*/

   /* static boolean oldPasswordDelete(String username, String userType)
    {
        ArrayList<PasswordHistory> userPasswordHistory = PasswordHistory.findAllByUsernameAndUserType(username, userType, [offset: new Constants().MAX_PASSWORD_HISTORY_DELETE, sort: "dateCreated", order: "desc"])

        userPasswordHistory.each {
            it.delete(flush: true)
        }

        return true
    }*/

    static checkPasswordStrength(String password)
    {
        int strength = 0
        boolean hasDigit = false;
        boolean hasSpaceChar = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        List<String> specialChars = [
                "!", "?", "#", "\$", "%", "&", "(", ")", "*",
                "+", ",", "-", "/", ":", ";", "<", "=", ">",
                "?", "@", "[", "\\", "]", "^", "_", "`", "{",
                "|", "}", "~"
        ]
        if (password.length() >= 8)
        {
            strength = strength + 20
            for (int i = 0; i < password.length(); i++)
            {
                char x = password.charAt(i);
                if (Character.isUpperCase(x))
                {
                    hasUpperCase = true
                }
                else if (Character.isLowerCase(x))
                {
                    hasLowerCase = true
                }
                else if (Character.isDigit(x))
                {
                    hasDigit = true
                }
                else if (specialChars.contains(x.toString()))
                {
                    hasSpaceChar = true
                }

                // no need to check further, break the loop
                if (hasUpperCase && hasLowerCase && hasSpaceChar && hasDigit)
                {
                    break
                }

            }
            if (hasUpperCase)
            {
                strength = strength + 20
            }
            if (hasLowerCase)
            {
                strength = strength + 20
            }
            if (hasSpaceChar)
            {
                strength = strength + 20
            }
            if (hasDigit)
            {
                strength = strength + 20
            }
        }
        return strength
    }

   /* static boolean validatePassword(String newPassword, String username, String userType)
    {
        ArrayList<PasswordHistory> userPasswordHistories = PasswordHistory.findAllByUsernameAndUserType(username, userType)

        if (userPasswordHistories != [] && userPasswordHistories.size() != 0)
        {
            userPasswordHistories.each {
                history ->
                    byte[] salt = new AuthService()
                            .toByteArray(history.password.split("_")[1])
                    String password1 = new AuthService()
                            .toHexString(new AuthService().hash(newPassword.toCharArray(), salt))
                    def pass = password1 + "_" + history.password.split("_")[1]
                    if (history.password == pass)
                    {
                        return false
                    }
            }
        }
        return true

    }*/

}
