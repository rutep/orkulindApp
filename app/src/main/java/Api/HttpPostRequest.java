package Api;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpPostRequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "POST";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String LOCALURL = "http://10.0.2.2:8080/";
    private JSONObject postData;


    public HttpPostRequest(Map<String, String> postData) {
        if (postData != null) {
            this.postData = new JSONObject(postData);
        }
    }

    @Override
    protected String doInBackground(String... params){
        String stringUrl = LOCALURL + params[0];

        String response = null;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            //Post Data
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            int statusCode = connection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                response = convertInputStreamToString(inputStream);

                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            } else {
                // Status code is not 200
                // Do something to handle the error
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return response;
    }
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }

    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
