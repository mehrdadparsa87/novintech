package com.novintech.test.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.novintech.test.api.SupportAppService;
import com.novintech.test.db.SupportDatabase;
import com.novintech.test.db.dao.UserDao;
import com.novintech.test.utils.factory.RxErrorHandlingCallAdapterFactory;
import com.novintech.test.utils.rx.AppSchedulerProvider;
import com.novintech.test.utils.rx.RxBus;
import com.novintech.test.utils.rx.SchedulerProvider;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {


    @Provides
    @Singleton
    OkHttpClient getOkHttpClient() {

        return new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(false)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

    }

    @Provides
    @Singleton
    SupportAppService provideSupportAppService(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SupportAppService.class);
    }


    @Provides
    @Singleton
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return new RxBus();
    }


    @Singleton
    @Provides
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    SupportDatabase provideDb(Application app) {
        return SupportDatabase.getInstance(app.getApplicationContext());
    }

    @Singleton
    @Provides
    UserDao provideUserDao(SupportDatabase db) {
        return db.userDao();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 17 && Build.VERSION.SDK_INT < 22) {
        }

        return client;
    }


    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
