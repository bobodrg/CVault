package com.example.cvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cvault.ui.navigation.CVaultNavHost
import com.example.cvault.ui.theme.CVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CVaultTheme {
                CVaultNavHost()
            }
        }
    }
}
