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

public class spidertask1_activity2 extends AppCompatActivity {

    EditText player1_name;
    EditText player2_name;
    Button enter_2;
    String p1_name;
    String p2_name;
    int rounds_2;
    long backpressed = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spidertask1_activity2);


        rounds_2 = getIntent().getIntExtra("rounds",1);
        player1_name = (EditText)findViewById(R.id.player1_name);
        player2_name = (EditText)findViewById(R.id.player_name);
        enter_2 = (Button)findViewById(R.id.enter_2);

        enter_2.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick( View v ) {
                if(player1_name.getText().length() == 0)player1_name.setError("Player name can't be left blank");
                else if(player2_name.getText().length() == 0)player2_name.setError("Player name can't be left blank");
                else{
                    p1_name = player1_name.getText().toString();
                    p2_name = player2_name.getText().toString();
                    Intent activity_3 = new Intent(getApplicationContext(),spidertask1_mainforp2.class);
                    activity_3.putExtra("p1_name",p1_name);
                    activity_3.putExtra("p2_name",p2_name);
                    activity_3.putExtra("rounds",rounds_2);
                    startActivity(activity_3);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if(backpressed + 2000 > System.currentTimeMillis())finishAffinity();
        else{
            Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT).show();
            backpressed = System.currentTimeMillis();
        }
    }
}
