package com.dx.bilibili.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.dx.bilibili.model.db.AppDatabase;
import com.dx.bilibili.model.db.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiayiyang on 17/6/12.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    UserDao provideUserDao(AppDatabase db){
        return db.userDao();
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
    }

}
