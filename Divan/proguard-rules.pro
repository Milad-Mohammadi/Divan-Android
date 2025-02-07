# Retrofit and OkHttp
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keep class retrofit2.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**

# Prevent warnings for Google API Client
-dontwarn com.google.api.client.http.**
-keep class com.google.api.client.http.** { *; }

# Prevent warnings for Joda-Time
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }

# Dagger Hilt (Prevents DI issues in release)
-keep class dagger.hilt.** { *; }
-keep class hilt_aggregated_deps.** { *; }
-keep class dagger.internal.** { *; }
-keep class javax.inject.** { *; }

# Keep classes annotated with @Inject
-keep class * { @javax.inject.Inject <init>(...); }

# ViewModel (if using Hilt ViewModel)
-keep class * extends androidx.lifecycle.ViewModel { *; }

# GSON SerializedName
-keepclassmembers class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Keep DTOs (Data Transfer Objects)
-keepclassmembers class ac.divan.data.remote.dto.** { *; }
-keep class ac.divan.data.remote.dto.** { *; }

# Prevent R8 from stripping annotations
-keepattributes *Annotation*

# Keep API interface methods
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclassmembernames interface * {
    @retrofit2.http.* <methods>;
}