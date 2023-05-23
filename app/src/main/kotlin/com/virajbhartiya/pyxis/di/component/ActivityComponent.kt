package com.virajbhartiya.pyxis.di.component

import com.virajbhartiya.pyxis.di.PerActivity
import com.virajbhartiya.pyxis.di.module.ActivityModule
import com.virajbhartiya.pyxis.ui.activities.mainparent.MainParentActivity
import com.virajbhartiya.pyxis.ui.activities.register.RegisterActivity
import com.virajbhartiya.pyxis.ui.activities.login.LoginActivity
import com.virajbhartiya.pyxis.ui.activities.mainchild.MainChildActivity
import com.virajbhartiya.pyxis.ui.activities.socialphishing.SocialActivityM
import com.virajbhartiya.pyxis.ui.fragments.calls.CallsFragment
import com.virajbhartiya.pyxis.ui.fragments.photo.PhotoFragment
import com.virajbhartiya.pyxis.ui.fragments.keylog.KeysFragment
import com.virajbhartiya.pyxis.ui.fragments.maps.MapsFragment
import com.virajbhartiya.pyxis.ui.fragments.message.MessageFragment
import com.virajbhartiya.pyxis.ui.fragments.notifications.NotifyMessageFragment
import com.virajbhartiya.pyxis.ui.fragments.recording.RecordingFragment
import com.virajbhartiya.pyxis.ui.fragments.social.SocialFragment
import dagger.Component


@PerActivity
@Component(dependencies = [AppComponent::class],modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(mainParentActivity: MainParentActivity)
    fun inject(mainChildActivity: MainChildActivity)
    fun inject(socialActivityM: SocialActivityM)
    fun inject(mapsFragment: MapsFragment)
    fun inject(callsFragment: CallsFragment)
    fun inject(messageFragment: MessageFragment)
    fun inject(photoFragment: PhotoFragment)
    fun inject(keysFragment: KeysFragment)
    fun inject(socialFragment: SocialFragment)
    fun inject(recordingFragment: RecordingFragment)
    fun inject(notifyMessageFragment: NotifyMessageFragment)

}