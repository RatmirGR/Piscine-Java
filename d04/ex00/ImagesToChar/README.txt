mkdir -p target
javac -d target ./src/java/edu/school21/printer/*/*.java
java -cp ./target edu/school21/printer/app/Program . 0 image.bmp