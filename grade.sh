CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

#1 clone student's repo
git clone $1 student-submission
echo 'Step 1: Finished cloning'

#2 check student;s code contains ListExamples.java
if [[ -f student-submission/ListExamples.java ]]
then 
    echo "Step 2: ListExamples.java file exists"
else 
    echo "ListExamples.java does not exist"
    echo "Grade: 0"
    exit
fi

#3 puta ll relevant files in grading area dir
# listex, testlistex, lib dir
cp student-submission/ListExamples.java TestListExamples.java grading-area
cp -r lib grading-area

#4 compile java files and check they compiled
cd grading-area
javac -cp $CPATH ListExamples.java TestListExamples.java #ListExamples.java, TestListExamples.java

if [[ $? == 0 ]]
then 
    echo "Step 3: Files successfully compiled in grading-area"
else 
    echo "Files did not successfully compile"
    echo "Grade: 0"
    exit
fi

# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

#Grading code
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > output.txt 

line=$(tail -n 2 output.txt)
tests=${line:11:1}
testNum=$((tests))
failed=${line:25:1}
failedNum=$((failed))

echo $testNum
echo $failedNum
x=$(($testNum-$failedNum))

echo $x
echo Grade: $(($x * 100 / $testNum))%