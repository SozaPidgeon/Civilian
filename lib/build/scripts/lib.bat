@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  lib startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and LIB_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\lib.jar;%APP_HOME%\lib\JDA-4.2.0_227.jar;%APP_HOME%\lib\twitch4j-1.2.1.jar;%APP_HOME%\lib\twitch4j-chat-1.2.1.jar;%APP_HOME%\lib\twitch4j-extensions-1.2.1.jar;%APP_HOME%\lib\twitch4j-helix-1.2.1.jar;%APP_HOME%\lib\twitch4j-kraken-1.2.1.jar;%APP_HOME%\lib\twitch4j-messaginginterface-1.2.1.jar;%APP_HOME%\lib\twitch4j-pubsub-1.2.1.jar;%APP_HOME%\lib\twitch4j-graphql-1.2.1.jar;%APP_HOME%\lib\twitch4j-eventsub-common-1.2.1.jar;%APP_HOME%\lib\twitch4j-common-1.2.1.jar;%APP_HOME%\lib\events4j-handler-simple-0.9.5.jar;%APP_HOME%\lib\feign-hystrix-11.0.jar;%APP_HOME%\lib\hystrix-core-1.5.18.jar;%APP_HOME%\lib\archaius-core-0.7.6.jar;%APP_HOME%\lib\apollo-runtime-2.5.4.jar;%APP_HOME%\lib\apollo-normalized-cache-jvm-2.5.4.jar;%APP_HOME%\lib\cache-2.0.2.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\twitch4j-auth-1.2.1.jar;%APP_HOME%\lib\feign-okhttp-11.0.jar;%APP_HOME%\lib\credentialmanager-0.1.2.jar;%APP_HOME%\lib\okhttp-4.9.1.jar;%APP_HOME%\lib\apollo-http-cache-api-2.5.4.jar;%APP_HOME%\lib\apollo-normalized-cache-api-jvm-2.5.4.jar;%APP_HOME%\lib\apollo-api-jvm-2.5.4.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.4.10.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.4.10.jar;%APP_HOME%\lib\okio-jvm-2.9.0.jar;%APP_HOME%\lib\uuid-jvm-0.2.0.jar;%APP_HOME%\lib\kotlin-stdlib-1.4.10.jar;%APP_HOME%\lib\annotations-20.1.0.jar;%APP_HOME%\lib\events4j-core-0.9.5.jar;%APP_HOME%\lib\feign-slf4j-11.0.jar;%APP_HOME%\lib\events4j-api-0.9.5.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\nv-websocket-client-2.13.jar;%APP_HOME%\lib\opus-java-1.0.4.pom;%APP_HOME%\lib\commons-collections4-4.1.jar;%APP_HOME%\lib\trove4j-3.0.3.jar;%APP_HOME%\lib\jackson-annotations-2.12.2.jar;%APP_HOME%\lib\jackson-core-2.12.2.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.12.2.jar;%APP_HOME%\lib\feign-jackson-11.0.jar;%APP_HOME%\lib\jackson-databind-2.12.2.jar;%APP_HOME%\lib\commons-lang3-3.12.0.jar;%APP_HOME%\lib\commons-io-2.8.0.jar;%APP_HOME%\lib\caffeine-2.8.6.jar;%APP_HOME%\lib\opus-java-api-1.0.4.jar;%APP_HOME%\lib\opus-java-natives-1.0.4.jar;%APP_HOME%\lib\bucket4j-core-4.7.0.jar;%APP_HOME%\lib\commons-configuration-1.10.jar;%APP_HOME%\lib\checker-qual-3.7.0.jar;%APP_HOME%\lib\error_prone_annotations-2.4.0.jar;%APP_HOME%\lib\jna-4.4.0.jar;%APP_HOME%\lib\micrometer-core-1.3.2.jar;%APP_HOME%\lib\feign-core-11.0.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\rxjava-1.2.0.jar;%APP_HOME%\lib\HdrHistogram-2.1.11.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\guava-16.0.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.4.10.jar


@rem Execute lib
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %LIB_OPTS%  -classpath "%CLASSPATH%" Civilian %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable LIB_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%LIB_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
