
 --------------------------------------------------------------------
 KL-7 CIPHER MACHINE SIMULATOR v5.0.1
 --------------------------------------------------------------------

 Content:

 1. Description
 2. Installation
 3. What's new on this version?
 4. Copyrights

 --------------------------------------------------------------------
 1. Description
 --------------------------------------------------------------------

 This software is an accurate simulation of the KL-7 Cipher Machine.
 The KL-7, codenamed ADONIS or POLLUX, was an off-line rotor cipher
 machine, developed in the late 1940's by the American Armed Forces
 Security Agency (AFSA) and introduced by the newly formed National
 Security Agency (NSA) in 1952. The KL-7 served in the United States
 and several NATO countries until 1983.

 The KL-7 simulator provides an authentic look and feel with its
 hands-on approach. The development of this simulator is based on
 publicly available information on the KL-7. It's principles of
 operation and most of the technical details are known. However,
 the internal wiring of the ciphering rotors, which is considered
 part of the secret key settings, is still  classified. The KL-7
 simulator operates with the same cryptographic principles as the
 real KL-7 but consequently uses its own rotor and wiring.

 Nonetheless, the KL-7 simulator provides authentic handling and
 operates just like the real KL-7. With all known surviving KL-7’s
 sanitized (stripped from rotor wiring), this simulator is the
 only remaining way to actually work  with this beautiful machine
 and the simulator serves as an attempt to keep this machine and
 its history alive.

 The program comes with a very complete 20 page manual (pdf),
 explaining the simulator, the technical details and the history
 of the machine.


 --------------------------------------------------------------------
 2. Installation
 --------------------------------------------------------------------

 * System requirements:

 - Windows 98/ME/2000/XP/Vista/Win7, both 32 and 64 bit versions
 - Also works with WINE or other Windows emulation on Linux and MAC

 - The manual is provided in pdf format. A free pdf reader is available
   at http://www.adobe.com

 * To install the program:

 Open with Winzip or IZArc and choose install, or extract all files in
 the archive to an empty folder and start "setup.exe".

 * To uninstall:

 Open the configuration screen, choose software, select 'KL-7' in
 the list of programs and click the Add/Remove button.

 * Known Issues:

 - Setup fails to install files

   Solution: always install the KL-7 sim on your C: drive and don't
   change any installation parameters

 - Rotors move too fast on manual stepping on PC without sound card

   Solution: disable sound (speaker icon)
 
 - Program halts, terminates or generates error on 64 bit versions.

   Solution: right-click "KL-7.exe" (on normal installation, default
   installation folder will be "C:\Program Files\KL-7") and in the
   properties window select the Compatibility tab and enable the
   Compatibility Mode.

 - Windows Vista Ultimate could generate error "COMDLG32.OCX not
   correctly registered.

   Solution: open the DOS box (Start-> Run...) and un-register by
   entering "regsvr32 /u c:\windows\system32\comdlg32.ocx" and re-
   install the software.

 - If you encounter other issues, please contact the author by e-mail

 --------------------------------------------------------------------
 3. What's New on this version?
 --------------------------------------------------------------------

 All items with a + are newly added

 1.0.0 - Beta version
 1.0.1 - first full version
 1.0.3 - minor change key displayed position two digits: 01 i.s.o. 1
       - help file version 0003-09/2009 (added message examples)
 1.0.4 - added help message splash on startup
       - help file version 0006-09/2009
 2.0.0 - Corrected working principle according latest available info
       - Extended help file history section and schematics.
       - Warning: this version is NOT compatible with ealier versions
 2.0.1 - Paper advances when rotors advance manually
       - Paper also advances one step during deciphering V into LET
       - Minor graphical corrections
 2.1.0 - Added actual KL-7 motor sounds
 2.1.1 - FIG and LET should not make sound in P (only tubes switch)
       - No count in P mode for FIG and LET
       - Revised help file version 025/2011
 2.1.2 - Added custom cage contact plate wiring
       - Fixed sound problem
       - Counter in P mode correction
 3.0.0 - Test version encryption cycle updates
 4.0.0 - Major machine encryption cyclus update according latest info 
       - This version is incompatible with earlier versions!
       - Optimized custom loading and error checking
 4.1.0 - correction plate wiring definitions and custom routines
       - This version is incompatible with earlier versions!
 4.1.1 - Trim off leading spaces of paper strip on KL-7 clipboard
 4.1.2 - Minor graphical corrections
 5.0.0 + Major update according to latest declassified information
       + This version is incompatible with earlier versions!!!
       + Renamed rotor assembly parts according real KL-7 manual
       + Changes on how to set notch rings againt alphabet ring
       + Added 13th rotor core
       + Rotor core selection also possible for 4th rotor
       + Added settable wide ring exclusive for 4th rotor
       + Added option to save and load keys in txt file format
       + Added Rotor Set Mode to easily preset the rotor positions
       + Added Zeroize button
       + Added Arrow Up for FIG and Arrow Down for LTR
       + Re-wrote message procedures according to most recently
         declassified information
 5.0.1 + Reset of groups spacing when Selector to E position
 5.0.1 + Correction small typos in help file, edition now 501-04

 --------------------------------------------------------------------
 4. COPYRIGHT NOTICE
 --------------------------------------------------------------------
 Copyright Information

 This program is provided as freeware and can be used and distributed
 under the following conditions: it is strictly forbidden to use this
 software or copies or parts of it for commercial purposes or to sell
 or lease this software, or to make profit from this program by any
 means. You are allowed to use this software only if you agree to
 these conditions.

 Disclaimer of Warranties

 This software and the accompanying files are supplied “as is” and
 without warranties of any kind, either expressed or implied, with
 respect to this product, its quality, performance, or fitness for
 any particular purpose. The entire risk as to its quality and
 performance is with the user. In no event will the author of this
 software be liable for any direct, indirect or consequential damages,
 resulting out of the use or inability to use this software.


 --------------------------------------------------------------------
 © D. Rijmenants 2008-2013
 Cipher Machines & Cryptology
 Website: http://users.telenet.be/d.rijmenants
 E-mail : DR.Defcom@telenet.be
 --------------------------------------------------------------------
