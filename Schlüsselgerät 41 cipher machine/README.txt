   SG-41 Simulator
   Written by George Lasry, February 2021
   Based on the paper by Klaus Kopacz and Paul Reuvers.
   https://www.cryptomuseum.com/crypto/sg41/index.htm

   First, rename Sg41.rename_to_jar to Sg41.jar (is provided with suffix *.rename_to_jar since most email systems reject jar (*.jar) files).
   You need to make sure you have Java installed on you computer (Windows https://www.java.com/en/download/help/windows_manual_download.html, 
   or Linux - https://www.java.com/en/download/help/linux_x64_install.html. To check that you have java properly installed, from command line: java -version).


    Usage:
    To encipher/decipher using a given key:
        java -jar Sg41.jar 0001101011000100010001101|0110100100001011100101100|11001001000100100100010|01001000111010001110010|001001001010000101011010|011001100110001011010100 ABCD0100 SCHLUESSELGERAETVIEREINSWANDERER
    To encipher/decipher and print all the steps:
        java -jar Sg41.jar 0001101011000100010001101|0110100100001011100101100|11001001000100100100010|01001000111010001110010|001001001010000101011010|011001100110001011010100 ABCD0100 SCHLUESSELGERAETVIEREINSWANDERER DETAILS
    To encipher using a random key:
        java -jar Sg41.jar SCHLUESSELGERAETVIEREINSWANDERER
