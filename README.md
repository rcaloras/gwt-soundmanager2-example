# GWT-SoundManager2 Example
Check out this working example of Gwt-SoundManager2. It exemplifies its use in a handful of ways including startup, creation and playback of sounds, as well as some error handling. 

It also sets up a layer of abstraction between producing sound and soundmanager by using a SoundFactory interface. In practice this is a nice setup with Google's dependency injection for GWT using GIN.

##Quick Start
```bash
git clone https://github.com/rcaloras/gwt-soundmanager2-example.git
cd gwt-soundmanager2-example
mvn clean compile package gwt:run
```
If all goes well you should be able to go right to http://127.0.0.1:8888/GwtSoundManager2Example.html?gwt.codesvr=127.0.0.1:9997.

Some .mp3s are included for playback. They're pulled directly from the root of the webapp directory.