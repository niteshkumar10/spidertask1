package myapplication.example.spidertask1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class spidertask1_winnerdeclarer extends AppCompatActivity {

    ConstraintLayout l1;
    TextView declare;
    Button menu;
    Button exit;
    TextView p1_scoreview;
    TextView p2_scoreview;
    String p1;
    String p2;
    int p1_score;
    int p2_score;
    long backpressed = 0;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spidertask1_winnerdeclarer);

        l1 = (ConstraintLayout)findViewById(R.id.layout_5);
        p1_scoreview = (TextView)findViewById(R.id.p1_scoreview);
        p2_scoreview = (TextView)findViewById(R.id.p2_scoreview);
        menu = (Button)findViewById(R.id.button_5);
        exit = (Button)findViewById(R.id.exit);
        declare = (TextView)findViewById(R.id.textView);
        if(savedInstanceState == null){
            p1_score = getIntent().getIntExtra("p1_score",0);
            p1 = getIntent().getStringExtra("p1_name");
            p2_score = getIntent().getIntExtra("p2_score",0);
            p2 = getIntent().getStringExtra("p2_name");
        }
       else{
           p1_score = savedInstanceState.getInt("p1_score");
           p2_score = savedInstanceState.getInt("p2_score");
           p1 = savedInstanceState.getString("p1_name");
           p2 = savedInstanceState.getString("p2_name");
        }
        score_update(p1_scoreview,p1_score,p1);
        score_update(p2_scoreview,p2_score,p2);
        l1.setBackgroundResource(R.color.green);
        get_winner();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent go_to_menu = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(go_to_menu);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick( View v ) {
                finishAffinity();
            }
        });

    }
    public void score_update( @org.jetbrains.annotations.NotNull TextView view, int score, String ch){
        view.setText(ch + ":" + score + "");
    }

    public void get_winner(){
        if(p1_score > p2_score){
            declare.setText("Winner is: " + p1);
            l1.setBackgroundResource(R.color.green);
        }
        else if(p1_score == p2_score){
            declare.setText("DRAW");
            l1.setBackgroundResource(R.color.grey);
        }
        else{
            declare.setText("Winner is: " + p2);
            l1.setBackgroundResource(R.color.green);
        }
    }

    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState ) {
        super.onSaveInstanceState(outState);
        outState.putInt("p1_score",p1_score);
        outState.putInt("p2_score",p2_score);
        outState.putString("p1_name",p1);
        outState.putString("p2_name",p2);
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
