package com.example.android.news.onboarding.data.model

import androidx.annotation.DrawableRes
import com.example.android.R

data class Page(val title: String, val content: String, @DrawableRes val imageId: Int)

val pages = listOf(
    Page(title = "", content = "", imageId = R.drawable.ic_launcher_background),
    Page(title = "", content = "", imageId = R.drawable.ic_launcher_background),
    Page(title = "", content = "", imageId = R.drawable.ic_launcher_background)
)