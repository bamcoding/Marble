package net.ktds.drink.admin.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;

public class SoundTrackListVO {

	private List<SoundTrackVO> soundTracks;
	private Pager pager;
	public List<SoundTrackVO> getSoundTracks() {
		return soundTracks;
	}
	public void setSoundTracks(List<SoundTrackVO> soundTracks) {
		this.soundTracks = soundTracks;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	

	
}
