package martin.pelcatm.channelmessaging;

/**
 * Created by pelcatm on 29/01/2018.
 */
public class AuthentificationResponse {
    private String response;
    private String code;
    private String accesstoken;
    AuthentificationResponse(){

    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}