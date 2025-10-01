#include <jni.h>
#include "NitroRestartOnLoad.hpp"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void*) {
  return margelo::nitro::restart::initialize(vm);
}
