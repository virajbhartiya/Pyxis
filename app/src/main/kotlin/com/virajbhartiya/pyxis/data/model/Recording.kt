package com.virajbhartiya.pyxis.data.model


class Recording {

    var nameAudio: String? = null
    var dateTime: String? = null
    var duration: String? = null

    constructor() {}

    constructor(nameAudio: String?, dateTime: String?, duration: String?) {
        this.nameAudio = nameAudio
        this.dateTime = dateTime
        this.duration = duration
    }

}