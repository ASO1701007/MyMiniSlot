package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    val img:Array<Int> = arrayOf(
            R.drawable.banana,
            R.drawable.bar,
            R.drawable.bigwin,
            R.drawable.cherry,
            R.drawable.grape,
            R.drawable.lemon,
            R.drawable.orange,
            R.drawable.seven,
            R.drawable.waltermelon
            )

    val hai:Array<Int> = arrayOf(
            -1,-1,-1
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setCoin()


        imageButton.setOnClickListener{onimgbBottonTapped()}
        imageButton2.setOnClickListener{onimgb2BottonTapped()}
        imageButton3.setOnClickListener{onimgb3BottonTapped()}


        back.setOnClickListener{onbackBottonTapped(it)}

    }


    fun onbackBottonTapped(view: View?){
        val intent = Intent(this, MainActivity::class.java);
        val data1 = -1;
        intent.putExtra("EXTRA_DATA", data1);
        this.startActivity(intent);
    }


    fun setCoin(){

        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val my=pref.getInt("MY_COIN",1000);
        val bet=pref.getInt("BET_COIN",10);

        txt_my2.setText(my.toString());
        txt_bet2.setText(bet.toString());


    }

    fun onimgbBottonTapped(){

        if (hai[0]==-1){
            val i=(Math.random()*10).toInt();
            imageButton.setImageResource(img[i]);
            hai[0]=i;

        }
        if (hai[0]!=-1&&hai[1]!=-1&&hai[2]!=-1){
            rec();
        }

    }

    fun onimgb2BottonTapped(){

        if (hai[1]==-1){
            val i=(Math.random()*10).toInt();
            imageButton2.setImageResource(img[i]);
            hai[1]=i;

        }
        if (hai[0]!=-1&&hai[1]!=-1&&hai[2]!=-1){
            rec();
        }

    }


    fun onimgb3BottonTapped(){

        if (hai[2]==-1){

            val i=(Math.random()*10).toInt();
            imageButton3.setImageResource(img[i]);
            hai[2]=i;
        }

        if (hai[0]!=-1&&hai[1]!=-1&&hai[2]!=-1){
            rec();
        }

    }

    fun rec(){

        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val my = pref.getInt("MY_COIN", 1000);
        val bet = pref.getInt("BET_COIN", 10);
        var re=0;

            if (hai[0]==hai[1]&&hai[1]==hai[2]&&hai[2]==hai[0]){//３つ揃う

                msg.setText("あたり");



                    if(hai[0]==img[7]&&hai[1]==img[7]&&hai[2]==img[7]){
                        re=20//７

                    }else if(hai[0]==img[3]&&hai[1]==img[3]&&hai[2]==img[3]) {
                            re= 10 //bigwin

                    }else if (hai[0]==img[2]&&hai[1]==img[2]&&hai[2]==img[2]) {
                                re= 5//bar

                    }else {
                                     re = 2//その他
                                    }

        }else if(hai[0]==hai[1]||hai[1]==hai[2]){
                // 2つ揃い

                msg.setText("あたり");

                if (hai[0] == img[7] && hai[1] == img[7] || hai[1] == img[7] && hai[2] == img[7]) {
                    re = 3//７
                } else {
                    re = 1//その他
                }

        }else if (hai[0]==img[8]||hai[1]==img[8]||hai[2]==img[8]){//1つあれば当たり(スイカ)

                        msg.setText("あたり");
                            re= 1//すいか

        }else if (hai[0]==img[6]||hai[1]==img[3]||hai[2]==img[5]){//左から「オレンジ」「チェリー」「レモン」の順に揃う → 30倍

                        msg.setText("あたり");

                        re= 30

        }else if(hai[0]==img[8]||hai[1]==img[0]||hai[2]==img[4]){//左から「スイカ」「バナナ」「グレープ」の順に揃う → 10倍

                        msg.setText("あたり");
                        re= 10

        }else{
            msg.setText("はずれ");

            re=(-10);

        }

        val myplus=my+bet*re;

        val editor = pref.edit();
        editor.putInt("MY_COIN",myplus)
                .apply()
        setCoin()

    }

}
