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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class spidertask1_activity4 extends AppCompatActivity {

    ConstraintLayout layout;
    ImageView imageview;
    ImageView computer_image;
    TextView player_name;
    TextView player_score_view;
    TextView comp_score_view;
    int rounds;
    Button enter_4;
    RadioGroup player_options;
    RadioButton stone;
    RadioButton paper;
    RadioButton scissors;
    String player;
    int times;
    int op_selected;
    TextView result_view;
    int rnd;
    int p_score;
    int c_score;
    String comp = "Computer";
    long backpressed = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spidertask1_activity4);

        player_score_view = (TextView)findViewById(R.id.player_scoreview);
        comp_score_view = (TextView)findViewById(R.id.comp_scoreview);
        layout = (ConstraintLayout)findViewById(R.id.layout_4);
        imageview = (ImageView)findViewById(R.id.imageView);
        computer_image = (ImageView)findViewById(R.id.computer_image);
        player_name = (TextView)findViewById(R.id.player_name);
        result_view = (TextView)findViewById(R.id.result);
        player_options = (RadioGroup)findViewById(R.id.player_options);
        stone = (RadioButton)findViewById(R.id.player_option1);
        paper = (RadioButton)findViewById(R.id.player_option2);
        scissors = (RadioButton)findViewById(R.id.player_option3);
        enter_4 = (Button)findViewById(R.id.enter_4);
        if(savedInstanceState == null){
            player = getIntent().getStringExtra("player_name");
            p_score = getIntent().getIntExtra("p_score",0);
            c_score = getIntent().getIntExtra("c_score",0);
            score_update(player_score_view,p_score,player);
            score_update(comp_score_view,c_score,comp);
            rounds = getIntent().getIntExtra("rounds",0);
            times = getIntent().getIntExtra("times",0);
        }
        else{
            player = savedInstanceState.getString("p1_name");
            p_score = savedInstanceState.getInt("p1_score");
            c_score = savedInstanceState.getInt("p2_score");
            rounds = savedInstanceState.getInt("rounds");
            times = savedInstanceState.getInt("times");
        }
        player_name.setText(player + "");
        if(times < rounds){
            player_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged( RadioGroup group, int checkedId ) {
                    RadioButton option_selected = findViewById(player_options.getCheckedRadioButtonId());
                    op_selected = player_options.indexOfChild(option_selected);
                    setImage(imageview,op_selected);
                }
            });
            enter_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick( View v ) {

                    if(stone.isChecked() || paper.isChecked() || scissors.isChecked()){

                        rnd = (int)(Math.random() * 2);
                        setImage(computer_image,rnd);
                        result();
                        times++;
                        Intent repeater = new Intent(getApplicationContext(),spidertask1_activity4.class);
                        repeater.putExtra("player_name",player);
                        repeater.putExtra("p_score",p_score);
                        repeater.putExtra("c_score",c_score);
                        repeater.putExtra("times",times);
                        repeater.putExtra("rounds",rounds);
                        startActivity(repeater);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Please select and option",Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Intent winner = new Intent(getApplicationContext(),spidertask1_winnerdeclarer.class);
            winner.putExtra("p1_score",p_score);
            winner.putExtra("p2_score",c_score);
            winner.putExtra("p1_name",player);
            winner.putExtra("p2_name",comp);
            startActivity(winner);
        }
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


    public void result(){
        if(rnd == op_selected)
            result_view.setText("DRAW");
        else if((rnd == 0 && op_selected == 1) || (rnd == 1 && op_selected == 2) || (rnd == 2 && op_selected == 0)){
            result_view.setText("WIN");
            layout.setBackgroundResource(R.color.green);
            p_score++;
            score_update(player_score_view,p_score,player);
        }
        else{
            result_view.setText("LOST");
            layout.setBackgroundResource(R.color.red);
            c_score++;
            score_update(comp_score_view,c_score,comp);
        }
    }
    public void score_update( @org.jetbrains.annotations.NotNull TextView view, int score, String ch){
        view.setText(ch + ":" + score + "");
    }

    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState ) {
        super.onSaveInstanceState(outState);
        outState.putInt("p1_score",p_score);
        outState.putInt("p2_score",c_score);
        outState.putInt("rounds",rounds);
        outState.putInt("times",times);
        outState.putString("p1_name",player);
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
