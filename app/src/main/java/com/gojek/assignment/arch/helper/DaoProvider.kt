package com.gojek.assignment.arch.helper

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gojek.data.repo.entity.RepoData
import com.gojek.data.repo.repository.RepoDao

@Database(entities = [RepoData::class], version = 1, exportSchema = false)
abstract class DaoProvider : RoomDatabase() {

    abstract fun getRepoDao(): RepoDao
}