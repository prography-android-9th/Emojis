package com.prography.slider

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SliderPresenter {
    fun getCurrentDate(): String {
        val currentDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())
        return currentDate
    }

    fun getEmojiList(): List<SliderItem>{
        return listOf(
            SliderItem("0", listOf("#Studio Ghibli", "#Symmetrical", "#Dramatic Lighting"), "https://images.unsplash.com/photo-1694481348806-0b6de4934812?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1471&q=80"),
            SliderItem("1", listOf("#Illustration", "#Portrait", "#Hopeful"), "https://images.unsplash.com/photo-1694057442309-bfe467bff9a9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80"),
            SliderItem("2", listOf("#Watercolor Painting ", "#Wide View", "#Dramatic Contrast"), "https://images.unsplash.com/photo-1559803509-40f78353d413?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2564&q=80"),
            SliderItem("3", listOf("#Ink Style", "#Symmetrical", "#Sad"), "https://plus.unsplash.com/premium_photo-1668633086435-a16be494a922?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1287&q=80"),
        )
    }
}




