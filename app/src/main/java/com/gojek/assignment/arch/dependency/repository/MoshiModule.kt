package com.gojek.assignment.arch.dependency.repository

import com.gojek.assignment.arch.helper.ColorHelper
import com.gojek.data.adapter.ColorStringAdapter
import com.gojek.data.adapter.FormattedNumberAdapter
import com.gojek.data.adapter.IColorHelper
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.converter.moshi.MoshiConverterFactory
import java.text.NumberFormat

@Module
class MoshiModule {

    @Provides
    internal fun providesMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    internal fun providesMoshi(
        formattedNumberAdapter: FormattedNumberAdapter,
        colorStringAdapter: ColorStringAdapter
    ): Moshi {
        return Moshi.Builder()
            .add(formattedNumberAdapter)
            .add(colorStringAdapter)
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

    @Provides
    internal fun providesColorHelper(): IColorHelper {
        return ColorHelper()
    }

    @Provides
    internal fun providesColorStringAdapter(colorHelper: IColorHelper): ColorStringAdapter {
        return ColorStringAdapter(colorHelper)
    }
}