package com.hamza.dagger2sample.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiResource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public ApiResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResource<T> success (@Nullable T data) {
        return new ApiResource<>(Status.SUCCESS, data, null);
    }

    public static <T> ApiResource<T> error(@NonNull String msg, @Nullable T data) {
        return new ApiResource<>(Status.ERROR, data, msg);
    }

    public static <T> ApiResource<T> loading(@Nullable T data) {
        return new ApiResource<>(Status.LOADING, data, null);
    }

    public enum Status { SUCCESS, ERROR, LOADING}
}