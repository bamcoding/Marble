package net.ktds.drink.admin.dao;

import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;
import net.ktds.drink.admin.vo.SearchSoundTrackVO;
import net.ktds.drink.admin.vo.SoundTrackVO;

public interface AdminDao {

	public int addAdvertisement(AdvertisementVO advertisementVO);
	
	public List<AdvertisementVO> getAdvertisementVideo(SearchAdvertisementVO searchAdvertisement);

	public AdvertisementVO getFileNameofAdvertisementBy(String advertisementId);

	public int deleteAdvertisement(String advertisementId);

	public AdvertisementVO getRandomAdvertisementVideoBy();

	public int getCountOfAdvertisements(SearchAdvertisementVO searchAdvertisement);
	
	
	
	public int addSoundTrack(SoundTrackVO soundTrackVO);
	
	public List<SoundTrackVO> getSoundTrack(SearchSoundTrackVO searchSoundTrack);
	
	public SoundTrackVO getFileNameOfSoundTrackBy(String soundTrackId);

	public int deleteSoundTrack(String soundTrackId);

	public int getCountOfSoundTracks(SearchSoundTrackVO searchSoundTrack);


	
}
