package martin.pelcatm.channelmessaging;


import java.util.HashMap;

public class PostRequest {

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private String URL;

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    private HashMap<String, String> params;


    public PostRequest(String url, HashMap<String , String> params)
    {
        this.URL = url;
        this.params = params;
    }

}
