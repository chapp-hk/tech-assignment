package com.gojek.assignment.arch.dependency.repository

import com.gojek.data.adapter.FormattedNumberAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.text.NumberFormat

@Module
class MoshiModule {

    @Provides
    internal fun providesMoshi(formattedNumberAdapter: FormattedNumberAdapter): Moshi {
        return Moshi.Builder()
            .add(formattedNumberAdapter)
            .build()
    }

    @Provides
    internal fun providesNumberFormat(): NumberFormat {
        return NumberFormat.getInstance()
    }

    @Provides
    internal fun providesFormattedNumberAdapter(numberFormat: NumberFormat): FormattedNumberAdapter {
        return FormattedNumberAdapter(numberFormat)
    }
}