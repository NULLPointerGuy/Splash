package com.karthik.splash.homescreen.bottomliketab.di

import com.karthik.splash.homescreen.bottomliketab.BottomLikeTabFragment
import dagger.Subcomponent

/**
 * Created by karthikrk on 25/11/17.
 */
@BottomLikeTabScope
@Subcomponent(modules = [BottomLikeTabModule::class])
interface BottomLikeTabComponent {
    fun inject(feedsLike: BottomLikeTabFragment)
}
