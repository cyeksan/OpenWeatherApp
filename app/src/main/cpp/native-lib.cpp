#include <jni.h>
#include <string>

std::string getData(int x) {
    std::string app_secret = "Null";
    if (x == 1) app_secret = "ec4b10464ec4ef8d4b22fa0c71568691"; //Inactive API key
    return app_secret;
}

extern "C" jstring
Java_com_csappgenerator_weatherapp_common_Keys_getApiKey(
        JNIEnv *env,
        jobject /* this */,
        jint id) {
    std::string app_secret = "Null";
    app_secret = getData(id);
    return env->NewStringUTF(app_secret.c_str());
}