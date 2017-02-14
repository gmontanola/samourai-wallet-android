package com.samourai.wallet.JSONRPC;

import com.samourai.wallet.util.CharSequenceX;

import org.json.JSONException;
import org.json.JSONObject;

public class TrustedNodeUtil {

    public static final int DEFAULT_PORT = 8332;

    private static String node = null;
    private static int port = DEFAULT_PORT;
    private static String user = null;
    private static CharSequenceX password = null;

    private static TrustedNodeUtil instance = null;

    private TrustedNodeUtil() { ; }

    public static TrustedNodeUtil getInstance() {

        if(instance == null) {
            instance = new TrustedNodeUtil();
        }

        return instance;
    }

    public void setParams(String user, CharSequenceX password, String node, int port) {
        TrustedNodeUtil.user = user;
        TrustedNodeUtil.password = password;
        TrustedNodeUtil.node = node;
        TrustedNodeUtil.port = port;
    }

    public void reset() {
        TrustedNodeUtil.user = null;
        TrustedNodeUtil.password = null;
        TrustedNodeUtil.node = null;
        TrustedNodeUtil.port = TrustedNodeUtil.DEFAULT_PORT;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        TrustedNodeUtil.node = node;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        TrustedNodeUtil.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        TrustedNodeUtil.user = user;
    }

    public CharSequenceX getPassword() {
        return password;
    }

    public void setPassword(CharSequenceX password) {
        TrustedNodeUtil.password = password;
    }

    public String getURL()  {

        // http://user:password@node:port/

        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(user);
        sb.append(":");
        sb.append(password.toString());
        sb.append("@");
        sb.append(node);
        sb.append(":");
        sb.append(port);
        sb.append("/");

        return sb.toString();
    }

    public JSONObject toJSON() {

        JSONObject jsonPayload = new JSONObject();
        try {

            if(node != null)    {
                jsonPayload.put("node", node);
            }
            jsonPayload.put("port", port);
            if(user != null)    {
                jsonPayload.put("user", user);
            }
            if(password != null)    {
                jsonPayload.put("password", password.toString());
            }

        }
        catch(JSONException je) {
            ;
        }

        return jsonPayload;
    }

    public void fromJSON(JSONObject jsonPayload) {

        try {

            if(jsonPayload.has("node")) {
                node = jsonPayload.getString("node");
            }

            if(jsonPayload.has("port")) {
                port = jsonPayload.getInt("port");
            }

            if(jsonPayload.has("user")) {
                user = jsonPayload.getString("user");
            }

            if(jsonPayload.has("password")) {
                password = new CharSequenceX(jsonPayload.getString("password"));
            }

        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }

    }

}