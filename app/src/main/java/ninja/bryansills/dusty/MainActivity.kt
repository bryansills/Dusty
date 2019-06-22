package ninja.bryansills.dusty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ninja.bryansills.dusty.network.Buttz

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttz = Buttz("blarg")
        Log.d("buttz", buttz.toString())
    }
}
