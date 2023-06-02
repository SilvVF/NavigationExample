package io.silv.navigationexample.nav

import org.koin.dsl.module

val navModule = module {

    single<AppComposeNavigator> {
        SampleComposeNavigator()
    }
}