package com.novintech.test.utils.factory;

import com.google.gson.Gson;
import com.novintech.test.api.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@SuppressWarnings("unused")
public class CustomRxJava2CallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory originalFactory;
    private final Gson gson = new Gson();

    public static CustomRxJava2CallAdapterFactory create() {
        return new CustomRxJava2CallAdapterFactory(null);
    }

    public static CallAdapter.Factory createWithScheduler(Scheduler scheduler) {
        return new CustomRxJava2CallAdapterFactory(scheduler);
    }

    private CustomRxJava2CallAdapterFactory(Scheduler scheduler) {
        if (scheduler == null) {
            originalFactory = RxJava2CallAdapterFactory.create();
        } else {
            originalFactory = RxJava2CallAdapterFactory.createWithScheduler(scheduler);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(originalFactory.get(returnType, annotations, retrofit), gson);
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Single<ApiResponse<R>>> {
        private final CallAdapter<R, ApiResponse<R>> wrapped;
        private Gson gson;

        RxCallAdapterWrapper(CallAdapter<R, ApiResponse<R>> wrapped, Gson gson) {
            this.wrapped = wrapped;
            this.gson = gson;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Single adapt(Call<R> call) {
            Object object = wrapped.adapt(call);
        return new Single() {
            @Override
            protected void subscribeActual(SingleObserver observer) {
                AtomicBoolean started = new AtomicBoolean(false);
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            observer.onSuccess(new ApiResponse<>(response));
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            observer.onSuccess(new ApiResponse<R>(t));
                        }
                    });
                }
            }
        };

          //  return ((Single<ApiResponse<R>>) object).onErrorResumeNext(throwable -> Single.error(transformException(throwable, call)));


        }


        private Throwable transformException(Throwable throwable, Call call) {
            // We can also access the initial call.request().url() e.g. to detect which route caused an exception
//            if (throwable instanceof HttpException) {
//                MyApiError apiError = null;
//                Response<?> response = ((HttpException) throwable).response();
//                if (response.errorBody() != null) {
//                    try {
//                        apiError = gson.fromJson(response.errorBody().string(), MyCustomError.class);
//                    } catch (Exception e) {
//                        Timber.d(e);
//                    }
//                    if (apiError != null) {
//                        return apiError;
//                    }
//                }
//            }
            return throwable;
        }
    }
}