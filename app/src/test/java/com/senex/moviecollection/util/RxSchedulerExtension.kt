package com.senex.moviecollection.util

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class RxSchedulerExtension : AfterEachCallback, BeforeEachCallback {
    private val scheduler = Schedulers.trampoline()

    override fun beforeEach(context: ExtensionContext) {
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }

        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
    }

    override fun afterEach(context: ExtensionContext) {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}