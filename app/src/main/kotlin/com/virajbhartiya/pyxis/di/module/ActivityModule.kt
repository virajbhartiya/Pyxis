package com.virajbhartiya.pyxis.di.module

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.virajbhartiya.pyxis.di.PerActivity
import com.virajbhartiya.pyxis.ui.activities.login.InteractorLogin
import com.virajbhartiya.pyxis.ui.activities.login.InterfaceInteractorLogin
import com.virajbhartiya.pyxis.ui.activities.login.InterfaceViewLogin
import com.virajbhartiya.pyxis.ui.activities.mainparent.InteractorMainParent
import com.virajbhartiya.pyxis.ui.activities.mainparent.InterfaceInteractorMainParent
import com.virajbhartiya.pyxis.ui.activities.mainparent.InterfaceViewMainParent
import com.virajbhartiya.pyxis.ui.activities.register.InteractorRegister
import com.virajbhartiya.pyxis.ui.activities.register.InterfaceInteractorRegister
import com.virajbhartiya.pyxis.ui.activities.register.InterfaceViewRegister
import com.virajbhartiya.pyxis.ui.fragments.calls.InteractorCalls
import com.virajbhartiya.pyxis.ui.fragments.calls.InterfaceInteractorCalls
import com.virajbhartiya.pyxis.ui.fragments.calls.InterfaceViewCalls
import com.virajbhartiya.pyxis.ui.fragments.photo.InteractorPhoto
import com.virajbhartiya.pyxis.ui.fragments.photo.InterfaceInteractorPhoto
import com.virajbhartiya.pyxis.ui.fragments.photo.InterfaceViewPhoto
import com.virajbhartiya.pyxis.ui.fragments.keylog.InteractorKeys
import com.virajbhartiya.pyxis.ui.fragments.keylog.InterfaceInteractorKeys
import com.virajbhartiya.pyxis.ui.fragments.keylog.InterfaceViewKeys
import com.virajbhartiya.pyxis.ui.fragments.maps.InteractorMaps
import com.virajbhartiya.pyxis.ui.fragments.maps.InterfaceInteractorMaps
import com.virajbhartiya.pyxis.ui.fragments.maps.InterfaceViewMaps
import com.virajbhartiya.pyxis.ui.fragments.message.InteractorMessage
import com.virajbhartiya.pyxis.ui.fragments.message.InterfaceInteractorMessage
import com.virajbhartiya.pyxis.ui.fragments.message.InterfaceViewMessage
import com.virajbhartiya.pyxis.ui.fragments.notifications.InteractorNotifyMessage
import com.virajbhartiya.pyxis.ui.fragments.notifications.InterfaceInteractorNotifyMessage
import com.virajbhartiya.pyxis.ui.fragments.notifications.InterfaceViewNotifyMessage
import com.virajbhartiya.pyxis.ui.fragments.recording.InteractorRecording
import com.virajbhartiya.pyxis.ui.fragments.recording.InterfaceInteractorRecording
import com.virajbhartiya.pyxis.ui.fragments.recording.InterfaceViewRecording
import com.virajbhartiya.pyxis.ui.fragments.social.InteractorSocial
import com.virajbhartiya.pyxis.ui.fragments.social.InterfaceInteractorSocial
import com.virajbhartiya.pyxis.ui.fragments.social.InterfaceViewSocial
import com.google.android.gms.maps.SupportMapFragment
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideContext(): Context = activity.applicationContext

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideSupportFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    fun provideSupportMapFragment(): SupportMapFragment = SupportMapFragment.newInstance()

    @Provides
    fun provideLayoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(context)

    @Provides
    @PerActivity
    fun provideInterfaceInteractorMain(interactorParent: InteractorMainParent<InterfaceViewMainParent>): InterfaceInteractorMainParent<InterfaceViewMainParent> = interactorParent

    @Provides
    @PerActivity
    fun provideInterfaceInteractorLogin(interactor: InteractorLogin<InterfaceViewLogin>): InterfaceInteractorLogin<InterfaceViewLogin> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorRegister(interactor: InteractorRegister<InterfaceViewRegister>): InterfaceInteractorRegister<InterfaceViewRegister> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorMaps(interactor: InteractorMaps<InterfaceViewMaps>): InterfaceInteractorMaps<InterfaceViewMaps> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorCalls(interactor: InteractorCalls<InterfaceViewCalls>): InterfaceInteractorCalls<InterfaceViewCalls> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorKeys(interactor: InteractorKeys<InterfaceViewKeys>): InterfaceInteractorKeys<InterfaceViewKeys> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorMessage(interactor: InteractorMessage<InterfaceViewMessage>): InterfaceInteractorMessage<InterfaceViewMessage> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorPhoto(interactor: InteractorPhoto<InterfaceViewPhoto>): InterfaceInteractorPhoto<InterfaceViewPhoto> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorSocial(interactor: InteractorSocial<InterfaceViewSocial>): InterfaceInteractorSocial<InterfaceViewSocial> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorRecording(interactor: InteractorRecording<InterfaceViewRecording>): InterfaceInteractorRecording<InterfaceViewRecording> = interactor

    @Provides
    @PerActivity
    fun provideInterfaceInteractorNotify(interactor: InteractorNotifyMessage<InterfaceViewNotifyMessage>): InterfaceInteractorNotifyMessage<InterfaceViewNotifyMessage> = interactor


}