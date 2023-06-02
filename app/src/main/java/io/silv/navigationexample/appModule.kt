package io.silv.navigationexample

import io.silv.navigationexample.nav.navModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    includes(
        navModule
    )

    viewModelOf(::SampleViewModel)
}