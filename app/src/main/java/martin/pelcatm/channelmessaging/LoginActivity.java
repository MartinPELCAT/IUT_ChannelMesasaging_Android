package martin.pelcatm.channelmessaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,OnDownloadListener{

    private EditText identifiant;
    private EditText password;
    private Button btnValider;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        identifiant = (EditText) findViewById(R.id.identifiant);
        password = (EditText) findViewById(R.id.password);
        btnValider = (Button) findViewById(R.id.validebtn);
        btnValider.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }


    @Override
    public void onClick(View v)
    {
        HttpPostHandler http = new HttpPostHandler();
        http.addOnDownloadListener(this);
        HashMap<String, String> paramsPost = new HashMap<>();
        paramsPost.put("username","mpelc");
        paramsPost.put("password","martinpelcat");
        http.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=connect",paramsPost));
        startActivity(new Intent(LoginActivity.this,ChannelListActivity.class));
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        System.out.println(downloadedContent);
        Gson gson = new Gson();
        AuthentificationResponse obj = gson.fromJson(downloadedContent, AuthentificationResponse.class);
        sharedPreferences
                .edit()
                .putString("accessToken",obj.getAccesstoken())
                .apply();

    }

    @Override
    public void onDownloadError(String error) {
        Toast.makeText(getApplicationContext(),"Error = "+error,Toast.LENGTH_SHORT).show();
    }


}
