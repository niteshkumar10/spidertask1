package myapplication.example.spidertask1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class spidertask1_activity1 extends AppCompatActivity {

    EditText player1_name;
    Button enter_1;
    String p_name;
    int rounds;
    long backpressed = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spidertask1_activity1);

        rounds = getIntent().getIntExtra("rounds",1);
        player1_name = (EditText)findViewById(R.id.player1_name);
        enter_1 = (Button)findViewById(R.id.enter_1);

        enter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if(player1_name.getText().length() == 0)player1_name.setError("Player name can't be left blank");
                else{
                    p_name = player1_name.getText().toString();
                    Intent activity_4 = new Intent(getApplicationContext(),spidertask1_activity4.class);
                    activity_4.putExtra("player_name",p_name);
                    activity_4.putExtra("rounds",rounds);
                    startActivity(activity_4);
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if((backpressed + 2000) > System.currentTimeMillis()){
            finishAffinity();
        }
        else{
            Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT).show();
            backpressed = System.currentTimeMillis();
        }
    }
}
