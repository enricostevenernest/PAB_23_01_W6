package com.example.pab_23_01_w5

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : ComponentActivity() {
    private val navigationItems = listOf(
        NavigationItem(R.id.nav_biodata, R.layout.layout_biodata),
        NavigationItem(R.id.nav_tentang, R.layout.layout_tentang),
        NavigationItem(R.id.nav_about, R.layout.layout_about),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main)

        navigationItems.forEach { item ->
            findViewById<TextView>(item.viewId).setOnClickListener {
                showPage(item)
            }
        }

        showPage(navigationItems.first())
        applySystemInsets()
    }

    private fun showPage(selectedItem: NavigationItem) {
        val contentContainer = findViewById<FrameLayout>(R.id.content_container)
        contentContainer.removeAllViews()
        LayoutInflater.from(this).inflate(selectedItem.layoutId, contentContainer, true)

        navigationItems.forEach { item ->
            findViewById<TextView>(item.viewId).isSelected = item == selectedItem
        }
    }

    private fun applySystemInsets() {
        val mainContainer = findViewById<android.view.View>(R.id.main_container)
        ViewCompat.setOnApplyWindowInsetsListener(mainContainer) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private data class NavigationItem(
        val viewId: Int,
        val layoutId: Int
    )
}

