mkdir -p lib
mkdir -p target

curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JColor-5.0.1.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.0.1/JColor-5.0.1.jar

javac -cp ".:./lib/JColor-5.0.1.jar:./lib/jcommander-1.82.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

cd target
jar xf ../lib/JColor-5.0.1.jar com
jar xf ../lib/jcommander-1.82.jar com
cd ..

cp -R src/resources target/.
rm -f ./target/images-to-chars-printer.jar

jar -cvfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
