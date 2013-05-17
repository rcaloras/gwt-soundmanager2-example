package com.chj.client.sound;


import com.chj.gwt.client.soundmanager2.Callback;
import com.chj.gwt.client.soundmanager2.SMSound;
import com.chj.gwt.client.soundmanager2.SoundManager;

public class SMSoundFactory implements SoundFactory {

	private static final String SOUND_ID = "soundID";

	// SoundManger to play our sounds

	private SoundManager sm;

	public SMSoundFactory(){
		sm = SoundManager.quickStart(null, 9, true);

	}


	public void playSong(String song, Callback callback) {

		// Delete the instance of the old song before playing a new one
		if(!sm.getSoundIDs().isEmpty()){
			sm.destroySound(SOUND_ID);
		}

		/* Need to setup our options to callback */
		if(callback != null){
			sm.getDefaultOptions().onFinish(callback);
		}
		System.out.println("playing song "+song);
		sm.createSound(SOUND_ID, song);
		sm.play(SOUND_ID);
	}

	public void stopPlaying() {
		// TODO Auto-generated method stub
		System.out.println("Stopping all music!");
		sm.stopAll();
		// Maybe fire some event event here on an eventbus

	}

	public boolean isSoundPlaying() {
		// TODO Auto-generated method stub
		SMSound track = sm.getSoundById(SOUND_ID);
		return ((track!= null) && (track.getPlayState() == 1));
	}

	public void pausePlaying() {
		sm.pause(SOUND_ID);
	}

	public void resumePlaying() {
		sm.resume(SOUND_ID);
	}


	public void setPlayingCallback(final PlayingCallback playing) {
		if(sm == null){
			return;
		}
		sm.defaultOptions.whilePlaying(new com.chj.gwt.client.soundmanager2.Callback() {

			public void execute() {
				playing.fire(sm.getSoundById(SOUND_ID).getPosition(), sm.getSoundById(SOUND_ID).getDurationEstimate());
			}

		});
	}

	public void onStartup(Callback callback ){
		sm.onReady(callback);

	}


	public void onStartError(Callback callback) {
		sm.onTimeout(callback);

	}



}
