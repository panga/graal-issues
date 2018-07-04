# GraalVM Native Image Issues

## Resource openStream() fails on second usage

Issue: https://github.com/oracle/graal/issues/513

Problem: When using the resource.openStream() for the second time, it returns an empty stream.

GraalVM version: 1.0.0-rc3

1. Build java project

```
mvn package
``` 

2. Generate native image

```
$GRAALVM_HOME/bin/native-image --no-server -H:IncludeResources="test.properties" -jar target/graal-issues-0.0.1-SNAPSHOT.jar
``` 

3. Run java project

``` 
java -jar target/graal-issues-0.0.1-SNAPSHOT.jar 
``` 

Output:

```
stream.available()=9
content.length()=9
new stream.available()=9
```

4. Run native image

Output:

```
stream.available()=9
content.length()=9
new stream.available()=0
```