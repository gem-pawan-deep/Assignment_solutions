import com.github.tsohr.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class post_httpUrlConnection {
    public static void main(String[] args) throws IOException {
       try{
        URL url = new URL("https://gorest.co.in/public/v2/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept","application/json");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestProperty("Authorization","Bearer aac0f5647c78288207003c8f46abdf1422c9b245597e215f0a019430e0e7d513");
        conn.setDoOutput(true);
        String input = new JSONObject()
                .put("name", "Pawan Deep")
                .put("gender", "male")
                .put("email", "pawan27@gmail.com")
                .put("status","active").toString();
           try(OutputStream os = conn.getOutputStream()) {
               byte[] inputt = input.getBytes("utf-8");
               os.write(inputt, 0, inputt.length);
           }
           try(BufferedReader br = new BufferedReader(
                   new InputStreamReader(conn.getInputStream(), "utf-8"))) {
               StringBuilder response = new StringBuilder();
               String responseLine = null;
               while ((responseLine = br.readLine()) != null) {
                   response.append(responseLine.trim());
               }
               System.out.println(response.toString());
           }
    }
       catch (MalformedURLException e)
       {
           e.printStackTrace();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
    }
}

