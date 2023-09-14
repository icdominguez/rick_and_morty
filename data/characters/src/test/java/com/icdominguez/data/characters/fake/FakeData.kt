package com.icdominguez.data.characters.fake

import com.google.gson.Gson
import com.icdominguez.core.common.fakedata.FakeObjects
import com.icdominguez.core.network.model.response.GetCharactersResponse

object FakeData {

    val page1Characters = Gson().fromJson(FakeObjects.charactersPage1, GetCharactersResponse::class.java)
    val page2Characters = Gson().fromJson(FakeObjects.charactersPage2, GetCharactersResponse::class.java)
    val page3Characters = Gson().fromJson(FakeObjects.charactersPage3, GetCharactersResponse::class.java)
}