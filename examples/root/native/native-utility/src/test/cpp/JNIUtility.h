/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class test_JNIUtility */

#ifndef _Included_test_JNIUtility
#define _Included_test_JNIUtility
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     test_JNIUtility
 * Method:    testJavaBoolean
 * Signature: (Z)Z
 */
JNIEXPORT jboolean JNICALL Java_test_JNIUtility_testJavaBoolean
  (JNIEnv *, jclass, jboolean);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaByte
 * Signature: (B)B
 */
JNIEXPORT jbyte JNICALL Java_test_JNIUtility_testJavaByte
  (JNIEnv *, jclass, jbyte);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaShort
 * Signature: (S)S
 */
JNIEXPORT jshort JNICALL Java_test_JNIUtility_testJavaShort
  (JNIEnv *, jclass, jshort);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaInteger
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_test_JNIUtility_testJavaInteger
  (JNIEnv *, jclass, jint);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaLong
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_test_JNIUtility_testJavaLong
  (JNIEnv *, jclass, jlong);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaFloat
 * Signature: (F)F
 */
JNIEXPORT jfloat JNICALL Java_test_JNIUtility_testJavaFloat
  (JNIEnv *, jclass, jfloat);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaDouble
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_test_JNIUtility_testJavaDouble
  (JNIEnv *, jclass, jdouble);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaString
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_test_JNIUtility_testJavaString
  (JNIEnv *, jclass, jstring, jstring);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaObject
 * Signature: (Ltest/Point;)Ltest/Point;
 */
JNIEXPORT jobject JNICALL Java_test_JNIUtility_testJavaObject
  (JNIEnv *, jclass, jobject);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaByteArray
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_test_JNIUtility_testJavaByteArray
  (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaShortArray
 * Signature: ([S)[S
 */
JNIEXPORT jshortArray JNICALL Java_test_JNIUtility_testJavaShortArray
  (JNIEnv *, jclass, jshortArray);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaIntegerArray
 * Signature: ([I)[I
 */
JNIEXPORT jintArray JNICALL Java_test_JNIUtility_testJavaIntegerArray
  (JNIEnv *, jclass, jintArray);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaLongArray
 * Signature: ([J)[J
 */
JNIEXPORT jlongArray JNICALL Java_test_JNIUtility_testJavaLongArray
  (JNIEnv *, jclass, jlongArray);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaFloatArray
 * Signature: ([F)[F
 */
JNIEXPORT jfloatArray JNICALL Java_test_JNIUtility_testJavaFloatArray
  (JNIEnv *, jclass, jfloatArray);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaDoubleArray
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_test_JNIUtility_testJavaDoubleArray
  (JNIEnv *, jclass, jdoubleArray);

/*
 * Class:     test_JNIUtility
 * Method:    testJavaStringArray
 * Signature: ([Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_test_JNIUtility_testJavaStringArray
  (JNIEnv *, jclass, jobjectArray, jstring);

/*
 * Class:     test_JNIUtility
 * Method:    test2DJavaByteArray
 * Signature: ([[B)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_test_JNIUtility_test2DJavaByteArray
  (JNIEnv *, jclass, jobjectArray);

/*
 * Class:     test_JNIUtility
 * Method:    testThrowOutOfMemoryError
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_test_JNIUtility_testThrowOutOfMemoryError
  (JNIEnv *, jclass);

/*
 * Class:     test_JNIUtility
 * Method:    testThrowInternalError
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_test_JNIUtility_testThrowInternalError
  (JNIEnv *, jclass);

/*
 * Class:     test_JNIUtility
 * Method:    testThrowNullPointerException
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_test_JNIUtility_testThrowNullPointerException
  (JNIEnv *, jclass);

/*
 * Class:     test_JNIUtility
 * Method:    testThrowIllegalStateException
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_test_JNIUtility_testThrowIllegalStateException
  (JNIEnv *, jclass);

/*
 * Class:     test_JNIUtility
 * Method:    testThrowIllegalArgumentException
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_test_JNIUtility_testThrowIllegalArgumentException
  (JNIEnv *, jclass);

/*
 * Class:     test_JNIUtility
 * Method:    testThrowUnsupportedOperationException
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_test_JNIUtility_testThrowUnsupportedOperationException
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
