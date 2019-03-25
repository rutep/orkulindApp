package Api;

import android.os.AsyncTask;

import org.json.JSONException;
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
    private String postData;


    public HttpPostRequest(String postData) {
        if (postData != null) {
            this.postData = postData;
        }
    }

    @Override
    protected String doInBackground(String... params){

        String stringUrl = LOCALURL + params[0];
        String response = null;

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
                writer.write(this.postData);
                writer.flush();
            }

            int statusCode = connection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(connection.getInputStream());

                response = convertInputStreamToString(inputStream);

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
