package com.test.testapp.ext

import com.test.testapp.event.LoadingEvent
import org.greenrobot.eventbus.EventBus


fun sendLoadingEvent(isShow:Boolean) {
    EventBus.getDefault().post(LoadingEvent(isShow))
}