package com.example.mainpage

import com.example.data.model.LatestItem

interface MainPageNavigation {

    fun navigateFromMainToDetailPage(item: LatestItem)
}