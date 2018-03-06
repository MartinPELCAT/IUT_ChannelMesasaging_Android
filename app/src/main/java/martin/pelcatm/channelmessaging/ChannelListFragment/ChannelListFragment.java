package martin.pelcatm.channelmessaging.ChannelListFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.HashMap;

import martin.pelcatm.channelmessaging.Channel;
import martin.pelcatm.channelmessaging.ChannelActivity;
import martin.pelcatm.channelmessaging.ChannelListActivity;
import martin.pelcatm.channelmessaging.ChannelResponse;
import martin.pelcatm.channelmessaging.HttpPostHandler;
import martin.pelcatm.channelmessaging.OnDownloadListener;
import martin.pelcatm.channelmessaging.PostRequest;
import martin.pelcatm.channelmessaging.R;

/**
 * Created by pelcatm on 02/03/2018.
 */
public class ChannelListFragment extends AppCompatActivity implements View.OnClickListener,OnDownloadListener {

    SharedPreferences sharedPreferences;
    private ListView liste;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_fragment);
        liste = (ListView) findViewById(R.id.listViewFragmentList);
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        HttpPostHandler http = new HttpPostHandler();
        http.addOnDownloadListener(this);
        HashMap<String, String> paramsPost = new HashMap<>();
        String myToken = sharedPreferences.getString("accessToken",null);
        paramsPost.put("accesstoken",myToken);
        http.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=getchannels",paramsPost));

    }

    @Override
    public void onClick(View v) {

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
                Intent intent = new Intent(ChannelListFragment.this, ChannelActivity.class);
                intent.putExtra("channelId",String.valueOf(id+1));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDownloadError(String error) {

    }
}
