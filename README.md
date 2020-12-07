Kehitysympäristönä käytämme Eclipseä.


Eclipsen voi ladata osoitteesta http://www.eclipse.org/downloads/eclipse-packages/.


Eclipsen käyttö vaatii joko JRE:n tai JDK:n. JDK:n voi ladata osoitteesta https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html.


Eclipsen ensimmäisellä käyttökerralla valitaan workspace-kansio. Tähän kansioon tallentuvat projektien kansiot. Näihin tallentuvat mm. lähdekoodit (java.) sekä tavukoodit (.class).
Tämä workspace kloonataan tästä repositorysta ja viedään Eclipsen repository näkymään. Näin voit importoida projektin ohjelmointi näkymään. Huomaathan, että sinulla pitää olla git asennettu koneellesi, jotta tämä on mahdollista toteuttaa: https://git-scm.com/downloads. 

Jotta projekti voi toimia, on sinulla oltava yhteys educloud-koneeseen jolla tietokanta sijaitsee. Yhteyden luomiseksi tarvitset Putty ohjelman (voit ladata osoitteesta: https://www.putty.org/), johon asetat kohdetietokoneen osoitteen ja kirjaudut sisään tunnuksillasi. Huomaathan, että connection type kuuluu olla SSH ja portin 22.

Ja jotta voit saada yhteyden educloud koneeseen, on sinun käytettävä joko tunnelointia tai metropolian VPN yhteyttä (löydät osoitteesta: https://vpn.metropolia.fi). 

Kun olet yhdistänyt Metropolian verkkoon, pystyt ajamaan projektin onnistuneesti. 
