package com.example.bannerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bannerview.banner.BannerView

class BannerActivity : AppCompatActivity() {

    private lateinit var bannerView: BannerView
    private var views: MutableList<View> = mutableListOf()
    private var titles: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        bannerView = findViewById(R.id.bannerView)
        var v1: View = layoutInflater.inflate(R.layout.daily_layout, null)
        var v2: View = layoutInflater.inflate(R.layout.popular_layout, null)
        var v3: View = layoutInflater.inflate(R.layout.recommend_layout, null)

        views.add(v1)
        views.add(v2)
        views.add(v3)

        titles.add("DAILY")
        titles.add("POPULAR")
        titles.add("RECOMMEND")

        bannerView.start(views, titles, 2, 500, R.drawable.logo)
    }
}
