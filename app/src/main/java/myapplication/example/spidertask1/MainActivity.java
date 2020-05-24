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

public class MainActivity extends AppCompatActivity {

   EditText rounds;
   EditText players;
   Button enter;
   int no_of_rounds;
   int no_of_players;
   long backpressed = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rounds = (EditText)findViewById(R.id.no_rounds);
        players = (EditText)findViewById(R.id.no_players);
        enter = (Button)findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if(players.getText().length() == 0)players.setError("rounds field can't be left blank");
                else if(rounds.getText().length() == 0) rounds.setError("Number of players field can't be left blank");
                else if(Integer.parseInt(players.getText().toString()) == 0)players.setError("0 players not accepted");
                else if(Integer.parseInt(players.getText().toString()) >= 3)players.setError("Number of players should be less than 3");
                else{
                    no_of_players = Integer.parseInt(players.getText().toString());
                    no_of_rounds = Integer.parseInt(rounds.getText().toString());
                    if(no_of_players == 1){
                            Intent activity_1 = new Intent(getApplicationContext(),spidertask1_activity1.class);
                            activity_1.putExtra("rounds",no_of_rounds);
                            startActivity(activity_1);
                    }
                    else{
                            Intent activity_2 = new Intent(getApplicationContext(),spidertask1_activity2.class);
                            activity_2.putExtra("rounds",no_of_rounds);
                            startActivity(activity_2);
                    }
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
