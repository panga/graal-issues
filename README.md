# GraalVM Native Image Issues

## Fail to generate native-image using log4j2 configuration

GraalVM version: 1.0.0-RC4

1. Build java project

`mvn clean package`

2. Generate native image

`$GRAALVM_HOME/bin/native-image --no-server -H:ReflectionConfigurationFiles=reflection.json -H:+ReportUnsupportedElementsAtRuntime -H:IncludeResources="META-INF/services/.*" -cp "target/dependency/*" com.github.panga.Main`

Error:

```
classlist:   2,357.93 ms
       (cap):   1,149.21 ms
       setup:   2,797.93 ms
    analysis:  18,017.22 ms
error: unsupported features in 2 methods
Detailed message:
Error: Detected a ZipFile object in the image heap. This is not supported. The object was reached from a static initializer. All static class initialization is done during native image construction, thus a static initializer cannot contain code that captures state dependent on the build machine. Write your own initialization methods and call them explicitly from your main entry point.
Trace: 	object java.util.HashMap$Node
	object java.util.HashMap$Node[]
	object java.util.HashMap
	method sun.net.www.protocol.jar.JarFileFactory.getConnection(JarFile)
Call path from entry point to sun.net.www.protocol.jar.JarFileFactory.getConnection(JarFile): 
	at sun.net.www.protocol.jar.JarFileFactory.getConnection(JarFileFactory.java:61)
	at sun.net.www.protocol.jar.JarURLConnection.connect(JarURLConnection.java:129)
	at sun.net.www.protocol.jar.JarURLConnection.getInputStream(JarURLConnection.java:152)
	at java.net.URL.openStream(URL.java:1045)
	at com.sun.naming.internal.VersionHelper12$InputStreamEnumeration$1.run(VersionHelper12.java:228)
	at com.sun.naming.internal.VersionHelper12$InputStreamEnumeration$1.run(VersionHelper12.java:224)
	at com.oracle.svm.core.jdk.Target_java_security_AccessController.doPrivileged(SecuritySubstitutions.java:70)
	at java.util.SplittableRandom.initialSeed(SplittableRandom.java:228)
	at com.oracle.svm.core.jdk.Target_java_util_SplittableRandom.<init>(JavaUtilSubstitutions.java:233)
	at com.oracle.svm.core.jdk.IdentityHashCodeSupport.generateHashCode(IdentityHashCodeSupport.java:43)
	at com.oracle.svm.core.jdk.Target_java_lang_System.identityHashCode(JavaLangSubstitutions.java:333)
	at java.util.IdentityHashMap.hash(IdentityHashMap.java:295)
	at java.util.IdentityHashMap.containsKey(IdentityHashMap.java:353)
	at com.oracle.svm.core.jdk.Target_java_lang_ApplicationShutdownHooks.add(JavaLangSubstitutions.java:604)
	at java.lang.Runtime.addShutdownHook(Runtime.java:211)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:170)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eJavaMainWrapper_002erun_0028int_002corg_002egraalvm_002enativeimage_002ec_002etype_002eCCharPointerPointer_0029(generated:0)
Error: Detected a ZipFile object in the image heap. This is not supported. The object was reached from a static initializer. All static class initialization is done during native image construction, thus a static initializer cannot contain code that captures state dependent on the build machine. Write your own initialization methods and call them explicitly from your main entry point.
Trace: 	object java.util.HashMap$Node
	object java.util.HashMap$Node[]
	object java.util.HashMap
	method sun.net.www.protocol.jar.JarFileFactory.getCachedJarFile(URL)
Call path from entry point to sun.net.www.protocol.jar.JarFileFactory.getCachedJarFile(URL): 
	at sun.net.www.protocol.jar.JarFileFactory.getCachedJarFile(JarFileFactory.java:121)
	at sun.net.www.protocol.jar.JarFileFactory.get(JarFileFactory.java:86)
	at sun.net.www.protocol.jar.JarURLConnection.connect(JarURLConnection.java:122)
	at sun.net.www.protocol.jar.JarURLConnection.getInputStream(JarURLConnection.java:152)
	at java.net.URL.openStream(URL.java:1045)
	at com.sun.naming.internal.VersionHelper12$InputStreamEnumeration$1.run(VersionHelper12.java:228)
	at com.sun.naming.internal.VersionHelper12$InputStreamEnumeration$1.run(VersionHelper12.java:224)
	at com.oracle.svm.core.jdk.Target_java_security_AccessController.doPrivileged(SecuritySubstitutions.java:70)
	at java.util.SplittableRandom.initialSeed(SplittableRandom.java:228)
	at com.oracle.svm.core.jdk.Target_java_util_SplittableRandom.<init>(JavaUtilSubstitutions.java:233)
	at com.oracle.svm.core.jdk.IdentityHashCodeSupport.generateHashCode(IdentityHashCodeSupport.java:43)
	at com.oracle.svm.core.jdk.Target_java_lang_System.identityHashCode(JavaLangSubstitutions.java:333)
	at java.util.IdentityHashMap.hash(IdentityHashMap.java:295)
	at java.util.IdentityHashMap.containsKey(IdentityHashMap.java:353)
	at com.oracle.svm.core.jdk.Target_java_lang_ApplicationShutdownHooks.add(JavaLangSubstitutions.java:604)
	at java.lang.Runtime.addShutdownHook(Runtime.java:211)
	at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:170)
	at com.oracle.svm.core.code.CEntryPointCallStubs.com_002eoracle_002esvm_002ecore_002eJavaMainWrapper_002erun_0028int_002corg_002egraalvm_002enativeimage_002ec_002etype_002eCCharPointerPointer_0029(generated:0)

Error: Image building with exit status 1
```
