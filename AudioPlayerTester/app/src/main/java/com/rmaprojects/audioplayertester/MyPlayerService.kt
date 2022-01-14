package com.rmaprojects.audioplayertester

import snow.player.PlayerService
import snow.player.annotation.PersistenceId
import jdk.internal.net.http.common.Log.channel




@PersistenceId("MyPlayerService")
class MyPlayerService : PlayerService() {

    private val mMusicStore: MusicStore? = null

    override fun onCreate() {

    }

}