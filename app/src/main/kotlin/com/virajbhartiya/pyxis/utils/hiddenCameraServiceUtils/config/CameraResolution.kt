package com.virajbhartiya.pyxis.utils.hiddenCameraServiceUtils.config

import androidx.annotation.IntDef


class CameraResolution{

    init {
        throw RuntimeException("Cannot initiate CameraResolution.")
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(MEDIUM_RESOLUTION, SLOW_RESOLUTION)
    annotation class SupportedResolution

    companion object {
        const val MEDIUM_RESOLUTION = 2006
        const val SLOW_RESOLUTION = 7895
    }
}
