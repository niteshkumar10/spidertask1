package myapplication.example.spidertask1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class spidertask1_mainforp2 extends AppCompatActivity {

    TextView p1_scoreview;
   TextView p2_scoreview;
   TextView p1_name;
   ImageView p1_imageview;
   Button enter_6;
   RadioGroup p1_options;
   RadioButton p1_stone;
   RadioButton p1_paper;
   RadioButton p1_scissors;
    int p1_score;
    int p2_score;
    String p1;
    String p2;
    int p1_selected;
    int times;
    int rounds;
    long backpressed = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spidertask1_mainforp2);


        p1_scoreview = (TextView)findViewById(R.id.p1_scoreview);
        p2_scoreview = (TextView)findViewById(R.id.p2_scoreview);
        p1_name = (TextView)findViewById(R.id.p1_name);
        p1_imageview = (ImageView)findViewById(R.id.p1_imageview);
        enter_6 = (Button)findViewById(R.id.enter_6);
        p1_options = (RadioGroup)findViewById(R.id.p1_options);
        p1_stone = (RadioButton)findViewById(R.id.p1_stone);
        p1_paper = (RadioButton)findViewById(R.id.p1_paper);
        p1_scissors = (RadioButton)findViewById(R.id.p1_scissors);
        if(savedInstanceState == null){
            times = getIntent().getIntExtra("times",0);
            rounds = getIntent().getIntExtra("rounds",0);
            p1 = getIntent().getStringExtra("p1_name");
            p1_score = getIntent().getIntExtra("p1_score",0);
            p2 = getIntent().getStringExtra("p2_name");
            p2_score = getIntent().getIntExtra("p2_score",0);
        }
        else{
            times = savedInstanceState.getInt("times");
            rounds = savedInstanceState.getInt("rounds");
            p1_score = savedInstanceState.getInt("p1_score");
            p2_score = savedInstanceState.getInt("p2_score");
            p1 = savedInstanceState.getString("p1_name");
            p2 = savedInstanceState.getString("p2_name");
        }

        score_update(p1_scoreview,p1_score,p1);
        score_update(p2_scoreview,p2_score,p2);
        p1_name.setText(p1 + "");
        p1_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged( RadioGroup group, int checkedId ) {
                    RadioButton option_selected = findViewById(p1_options.getCheckedRadioButtonId());
                    p1_selected = p1_options.indexOfChild(option_selected);
                    setImage(p1_imageview,p1_selected);
                }
            });
        enter_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {

                    if(p1_stone.isChecked() || p1_paper.isChecked() || p1_scissors.isChecked()){

                        times++;
                        Intent for_p2 = new Intent(getApplicationContext(),spidertask1_forp2_part2.class);
                        for_p2.putExtra("p1_name",p1);
                        for_p2.putExtra("p1_score",p1_score);
                        for_p2.putExtra("p2_name",p2);
                        for_p2.putExtra("p2_score",p2_score);
                        for_p2.putExtra("p1_selected",p1_selected);
                         for_p2.putExtra("times",times);
                        for_p2.putExtra("rounds",rounds);
                        startActivity(for_p2);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Please select and option",Toast.LENGTH_LONG).show();
                }
        });
    }
    public void setImage(ImageView r, int n){
        switch(n) {
            case 0:
                r.setImageResource(R.drawable.stone);
                break;
            case 1:
                r.setImageResource(R.drawable.paper);
                break;
            case 2:
                r.setImageResource(R.drawable.scissors);
                break;
        }
    }

    public void score_update( @org.jetbrains.annotations.NotNull TextView view, int score, String ch){
        view.setText(ch + ":" + score + "");
    }

    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState ) {
        super.onSaveInstanceState(outState);
        outState.putInt("p1_score",p1_score);
        outState.putInt("p2_score",p2_score);
        outState.putInt("rounds",rounds);
        outState.putInt("times",times);
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

