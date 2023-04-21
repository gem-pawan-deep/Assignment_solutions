import com.github.tsohr.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
public class Post_HTTP_Client {
    private static String Postmethod(String URL) throws IOException{
        CloseableHttpClient httpClient=HttpClients.createDefault();
        String res="";
        HttpPost post=new HttpPost(URL);
        post.addHeader("Accept","application/json");
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer");
        String json = new JSONObject().put("name", "PawanDeep").put("gender", "male").put("email", "pawan@gmail.com").put("status","active").toString();
        post.setEntity(new StringEntity(json));
        CloseableHttpResponse response=httpClient.execute(post);
        res= EntityUtils.toString(response.getEntity());
        return res;
    }
    public static void main(String[] args){
        try{
            String res=Postmethod("https://gorest.co.in/public/v2/users");
            System.out.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
