mkdir -p target
javac -d target ./src/java/edu/school21/printer/*/*.java
jar -cmf src/manifest.txt target/images-to-chars-printer.jar -C target edu/ -C src/ resources/
java -jar target/images-to-chars-printer.jar . 0