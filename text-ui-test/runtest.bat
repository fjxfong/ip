@ECHO OFF

REM ===== SET JDK PATH =====
set JAVA_HOME=C:\Users\Xiang\.jdks\ms-17.0.17
set PATH=%JAVA_HOME%\bin;%PATH%

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -Xlint:none -d ..\bin ..\src\main\java\*.java ..\src\main\java\tasklist\*.java ..\src\main\java\task\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Psyduck < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
