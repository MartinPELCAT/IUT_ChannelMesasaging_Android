package martin.pelcatm.channelmessaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelListActivity extends AppCompatActivity implements View.OnClickListener,OnDownloadListener{

    SharedPreferences sharedPreferences;
    private ListView liste;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_activity);
        liste = (ListView) findViewById(R.id.listView);
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        HttpPostHandler http = new HttpPostHandler();
        http.addOnDownloadListener(this);
        HashMap<String, String> paramsPost = new HashMap<>();
        String myToken = sharedPreferences.getString("accessToken",null);
        paramsPost.put("accesstoken",myToken);
        http.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=getchannels",paramsPost));

    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        System.out.println(downloadedContent);
        Gson gson = new Gson();
        ChannelResponse obj = gson.fromJson(downloadedContent, ChannelResponse.class);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        for (Channel chan:obj.getChannels()
             ) {
            arrayAdapter.add(chan.getName().toString());

        }
        liste.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChannelListActivity.this, ChannelActivity.class);
                intent.putExtra("channelId",String.valueOf(id+1));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDownloadError(String error) {

    }
}
