package com.abrarshakhi.envelope

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.abrarshakhi.envelope.navigation.EnvelopeApp
import com.abrarshakhi.envelope.ui.theme.EnvelopeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnvelopeTheme {
                EnvelopeApp()
            }
        }
    }
}