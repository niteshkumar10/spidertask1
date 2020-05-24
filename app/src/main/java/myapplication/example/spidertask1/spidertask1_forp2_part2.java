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

public class spidertask1_forp2_part2 extends AppCompatActivity {

    TextView p1_scoreview;
    TextView p2_scoreview;
    TextView p2_name;
    TextView resultview;
    ImageView p2_imageview;
    Button enter_7;
    RadioGroup p2_options;
    RadioButton p2_stone;
    RadioButton p2_paper;
    RadioButton p2_scissors;
    int p1_score;
    int p2_score;
    String p1;
    String p2;
    int p1_selected;
    int p2_selected;
    int times;
    int rounds;
    long backpressed = 0;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spidertask1_forp2_part2);

        p1_scoreview = (TextView)findViewById(R.id.p1_scoreview);
        p2_scoreview = (TextView)findViewById(R.id.p2_scoreview);
        resultview = (TextView)findViewById(R.id.resultview);
        p2_name = (TextView)findViewById(R.id.p2_name);
        p2_imageview = (ImageView)findViewById(R.id.p2_imageview);
        enter_7 = (Button)findViewById(R.id.enter_7);
        p2_options = (RadioGroup)findViewById(R.id.p2_options);
        p2_stone = (RadioButton)findViewById(R.id.p2_stone);
        p2_paper = (RadioButton)findViewById(R.id.p2_paper);
        p2_scissors = (RadioButton)findViewById(R.id.p2_scissors);
        if(savedInstanceState == null){
            p1 = getIntent().getStringExtra("p1_name");
            p1_score = getIntent().getIntExtra("p1_score",0);
            p2 = getIntent().getStringExtra("p2_name");
            p2_score = getIntent().getIntExtra("p2_score",0);
            rounds = getIntent().getIntExtra("rounds",0);
            times = getIntent().getIntExtra("times",0);
            p1_selected = getIntent().getIntExtra("p1_selected",0);
        }
        else{
            times = savedInstanceState.getInt("times");
            rounds = savedInstanceState.getInt("rounds");
            p1_score = savedInstanceState.getInt("p1_score");
            p2_score = savedInstanceState.getInt("p2_score");
            p1 = savedInstanceState.getString("p1_name");
            p2 = savedInstanceState.getString("p2_name");
            p1_selected = savedInstanceState.getInt("p1_selected");
        }
        score_update(p1_scoreview,p1_score,p1);
        score_update(p2_scoreview,p2_score,p2);
        p2_name.setText(p2 + "");


        p2_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( RadioGroup group, int checkedId ) {
                if(p2_stone.isChecked() || p2_paper.isChecked() || p2_scissors.isChecked()){
                    RadioButton op_select = (RadioButton) findViewById(p2_options.getCheckedRadioButtonId());
                    p2_selected = p2_options.indexOfChild(op_select);
                    setImage(p2_imageview,p2_selected);
                }
            }
        });
        enter_7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View v ) {
                if(p2_stone.isChecked() || p2_paper.isChecked() || p2_scissors.isChecked()){
                    result();
                    if(times < rounds){
                        Intent next_round = new Intent(getApplicationContext(),spidertask1_mainforp2.class);
                        next_round.putExtra("p1_name",p1);
                        next_round.putExtra("p2_name",p2);
                        next_round.putExtra("p1_score",p1_score);
                        next_round.putExtra("p2_score",p2_score);
                        next_round.putExtra("rounds",rounds);
                        next_round.putExtra("times",times);
                        startActivity(next_round);
                    }
                    else{
                        Intent result = new Intent(getApplicationContext(),spidertask1_winnerdeclarer.class);
                        result.putExtra("p1_score",p1_score);
                        result.putExtra("p2_score",p2_score);
                        result.putExtra("p1_name",p1);
                        result.putExtra("p2_name",p2);
                        startActivity(result);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"Please select an option",Toast.LENGTH_LONG);
            }
        });
    }
    public void setImage( ImageView r, int n){
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


    public void result(){
        if(p2_selected == p1_selected)
            resultview.setText("DRAW");
        else if((p2_selected == 0 && p1_selected == 1) || (p2_selected == 1 && p1_selected == 2) || (p2_selected == 2 && p1_selected == 0)){
            resultview.setText(p1  + " WIN");
            p1_score++;
            score_update(p1_scoreview,p1_score,p1);
        }
        else{
            resultview.setText(p2 + " WIN");
            p2_score++;
            score_update(p2_scoreview,p2_score,p2);
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
        outState.putInt("p1_selected",p1_selected);
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
