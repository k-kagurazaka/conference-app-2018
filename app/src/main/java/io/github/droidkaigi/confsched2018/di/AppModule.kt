package io.github.droidkaigi.confsched2018.di

import dagger.Module
import dagger.Provides
import io.github.droidkaigi.confsched2018.data.api.DroidKaigiApi
import io.github.droidkaigi.confsched2018.data.api.FeedApi
import io.github.droidkaigi.confsched2018.data.api.GithubApi
import io.github.droidkaigi.confsched2018.data.db.ContributorDatabase
import io.github.droidkaigi.confsched2018.data.db.FavoriteDatabase
import io.github.droidkaigi.confsched2018.data.db.SessionDatabase
import io.github.droidkaigi.confsched2018.data.repository.ContributorDataRepository
import io.github.droidkaigi.confsched2018.data.repository.ContributorRepository
import io.github.droidkaigi.confsched2018.data.repository.FeedDataRepository
import io.github.droidkaigi.confsched2018.data.repository.FeedRepository
import io.github.droidkaigi.confsched2018.data.repository.SessionDataRepository
import io.github.droidkaigi.confsched2018.data.repository.SessionRepository
import io.github.droidkaigi.confsched2018.util.rx.AppSchedulerProvider
import io.github.droidkaigi.confsched2018.util.rx.SchedulerProvider
import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
internal object AppModule {

    @Singleton @Provides @JvmStatic
    fun provideSessionRepository(
            api: DroidKaigiApi,
            sessionDatabase: SessionDatabase,
            favoriteDatabase: FavoriteDatabase,
            schedulerProvider: SchedulerProvider
    ): SessionRepository =
            SessionDataRepository(api, sessionDatabase, favoriteDatabase, schedulerProvider)

    @Singleton @Provides @JvmStatic
    fun provideFeedRepository(
            feedApi: FeedApi
    ): FeedRepository =
            FeedDataRepository(feedApi)

    @Singleton @Provides @JvmStatic
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Singleton @Provides @JvmStatic
    fun provideContributorsRepository(
            api: GithubApi,
            contributorDatabase: ContributorDatabase,
            schedulerProvider: SchedulerProvider
    ): ContributorRepository =
            ContributorDataRepository(api, contributorDatabase, schedulerProvider)
}
