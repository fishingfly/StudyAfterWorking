package saic.zy.Transcation;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class SpeechTransDemo {

    public static void main(String[] args) throws Exception {
        translationNameField();
    }
    
    public static String startTranscation(String tempStr) throws Exception {
        String appKey ="2f0583fa3a105468";
        String query = tempStr;
        String salt = String.valueOf(System.currentTimeMillis());
        String from = "zh-CHS";
        String to = "EN";
        String sign = md5(appKey + query + salt+ "MNP8T1MKXn9se90L773YIGnWeU71Nn2d");
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("sign", sign);
        params.put("salt", salt);
        params.put("appKey", appKey);
        String requestForHttpRet = requestForHttp("http://openapi.youdao.com/api", params);
        return requestForHttpRet;
    }
    
    public static void translationNameField() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }  
        
        //第二步，获取一个数据库的连接  
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/testzhou","test","123456");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }  
        
          //第三步，创建一个会话  
        Statement stmt = null;
        try {
            stmt = (Statement) conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
            
          //或者查询记录  
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("select name from table_s");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
            
        StringBuilder str = new StringBuilder();
          //第五步，对查询的结果进行处理  
        try {
            while(rs.next()){   
              //对记录的操作  
                String startTranscation = startTranscation(rs.getString("name"));//取回翻译的字符串
                str.append("update table_s set name = \"").append(startTranscation)
                .append("\" where name = \"").append(rs.getString("name")).append("\";\r\n");//拼接数据库udate字符串
                System.out.println(str);
              }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        //将拼接完的update字符串放入文件  easyUse.txt中，一遍使用其中的update语句直接更新数据库。
        FileUtils.writeStringToFile(new File("easyUse.txt"), str.toString(), "utf-8");
          //第六步，关闭连接  
          rs.close();  
          stmt.close();  
          conn.close();  
    }

    public static String requestForHttp(String url,Map<String,String> requestParams) throws Exception{
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Iterator<Entry<String, String>> it = requestParams.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
        /**HttpResponse*/
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        }finally{
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        //取出返回的json，并从其中精确的取出key为"translation"的值，最后返回
        com.alibaba.fastjson.JSONObject object = JSONArray.parseObject(result);
        JSONArray object2 = (JSONArray) object.get("translation");
        return object2.get(0).toString().toLowerCase().replaceAll("\\.", "");
    }

    /**
     * 生成32位MD5摘要
     * @param string
     * @return
     */
    public static String md5(String string) {
        if(string == null){
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        try{
            byte[] btInput = string.getBytes("utf-8");
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 根据api地址和参数生成请求URL
     * @param url
     * @param params
     * @return
     */
//    public static String getUrlWithQueryString(String url, Map<String, String> params) {
//        if (params == null) {
//            return url;
//        }
//
//        StringBuilder builder = new StringBuilder(url);
//        if (url.contains("?")) {
//            builder.append("&");
//        } else {
//            builder.append("?");
//        }
//
//        int i = 0;
//        for (String key : params.keySet()) {
//            String value = params.get(key);
//            if (value == null) { // 过滤空的key
//                continue;
//            }
//
//            if (i != 0) {
//                builder.append('&');
//            }
//
//            builder.append(key);
//            builder.append('=');
//            builder.append(encode(value));
//
//            i++;
//        }
//
//        return builder.toString();
//    }
    /**
     * 进行URL编码
     * @param input
     * @return
     */
    public static String encode(String input) {
        if (input == null) {
            return "";
        }

        try {
            return URLEncoder.encode(input, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return input;
    }
}