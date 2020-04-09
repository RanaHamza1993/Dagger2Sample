package com.hamza.dagger2sample.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiResource<T> {

    @NonNull
    public final AuthStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public ApiResource(@NonNull AuthStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResource<T> authenticated (@Nullable T data) {
        return new ApiResource<>(AuthStatus.AUTHENTICATED, data, null);
    }

    public static <T> ApiResource<T> error(@NonNull String msg, @Nullable T data) {
        return new ApiResource<>(AuthStatus.ERROR, data, msg);
    }

    public static <T> ApiResource<T> loading(@Nullable T data) {
        return new ApiResource<>(AuthStatus.LOADING, data, null);
    }

    public static <T> ApiResource<T> logout () {
        return new ApiResource<>(AuthStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum AuthStatus { AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED}

}