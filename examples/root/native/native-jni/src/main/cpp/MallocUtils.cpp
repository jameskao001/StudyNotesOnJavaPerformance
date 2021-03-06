#include <malloc.h>
#include <mcheck.h>
#include <iostream>
#include "MallocUtils.h"

using namespace std;

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_github_mcfongtw_jni_utils_MallocUtils
 * Method:    mallocStats
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_github_mcfongtw_jni_utils_MallocUtils_mallocStats(JNIEnv *jenv, jclass jclazz) {
    malloc_stats();
}


/*
 * Class:     com_github_mcfongtw_jni_utils_MallocUtils
 * Method:    mallopt
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_github_mcfongtw_jni_utils_MallocUtils_mallopt
  (JNIEnv *jenv, jclass jclazz, jint j_param, jint j_value) {
    int c_param = (int)j_param;
    int c_value = (int)j_value;

    return mallopt( c_param, c_value);
  }

/*
 * Class:     com_github_mcfongtw_jni_utils_MallocUtils
 * Method:    mtrace
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_github_mcfongtw_jni_utils_MallocUtils_mtrace
  (JNIEnv *jenv, jclass jclazz) {
    mtrace();
  }

/*
 * Class:     com_github_mcfongtw_jni_utils_MallocUtils
 * Method:    muntrace
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_github_mcfongtw_jni_utils_MallocUtils_muntrace
  (JNIEnv *jenv, jclass jclazz) {
    muntrace();
  }

#ifdef __cplusplus
}
#endif