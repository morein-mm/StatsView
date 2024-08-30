package ru.netology.statsview

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.netology.statsview.ui.StatView
import ru.netology.statsview.ui.theme.StatsViewTheme

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val view = findViewById<StatView>(R.id.statsView)
        view.data = listOf(
//            500F,
//            500F,
//            500F,
//            500F,
            0.25F,
            0.25F,
            0.25F,
            0.25F,
        )
        val textView = findViewById<TextView>(R.id.label)

//        view.startAnimation(
//            AnimationUtils.loadAnimation(this, R.anim.animation).apply {
//                setAnimationListener(object : Animation.AnimationListener{
//                    override fun onAnimationStart(animation: Animation?) {
//                        textView.setText("onAnimationStart")
//                    }
//
//                    override fun onAnimationEnd(animation: Animation?) {
//                        textView.setText("onAnimationEnd")
//                    }
//
//                    override fun onAnimationRepeat(animation: Animation?) {
//                        textView.setText("onAnimationRepeat")
//                    }
//
//                })
//            }
//        )

//        setContent {
//            StatsViewTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StatsViewTheme {
        Greeting("Android")
    }
}