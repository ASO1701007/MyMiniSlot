package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener{onstartBottonTapped(it)}
        up.setOnClickListener{onupBottonTapped()}
        down.setOnClickListener{ondownBottonTapped()}
        reset.setOnClickListener { onresetBottonTapped() }

        val intent = intent
        val data1 = intent.getIntExtra("EXTRA_DATA", 0)

        if (data1!=-1) {


            //共有プリファレンスの初期化
            val pref = PreferenceManager.getDefaultSharedPreferences(this);
            val editor = pref.edit();
            editor.clear().apply();

            //エディターのメソッドを呼び出して共有プリファレンスを更新
            editor.putInt("MY_COIN", 1000)
                    .putInt("BET_COIN", 10)
                    .apply()

        }

        setCoin();
    }
    fun onstartBottonTapped(view: View?){

        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val my=pref.getInt("MY_COIN",1000);
        if (my>0){

            val intent =Intent(this,GameActivity::class.java)
            this.startActivity(intent)
        }


    }

    fun setCoin(){

        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val my=pref.getInt("MY_COIN",1000);
        val bet=pref.getInt("BET_COIN",10);

        txt_my2.setText(my.toString());
        txt_bet2.setText(bet.toString());

    }
    fun onupBottonTapped(){

        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val bet=pref.getInt("BET_COIN",10);
        val betplus = bet+10;

        val editor = pref.edit();
        editor.putInt("BET_COIN",betplus).apply()

        setCoin()


    }
    fun ondownBottonTapped(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val my=pref.getInt("MY_COIN",1000);
        val bet=pref.getInt("BET_COIN",10);
        if (bet>10) {
            val betd = bet - 10
            val editor = pref.edit();
            editor.putInt("BET_COIN", betd).apply()

            setCoin()
        }


    }
    fun onresetBottonTapped(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val editor = pref.edit();
        editor.clear().apply();
        editor.putInt("MY_COIN",1000)
                .putInt("BET_COIN",10)
                .apply()

        setCoin()
    }

}
