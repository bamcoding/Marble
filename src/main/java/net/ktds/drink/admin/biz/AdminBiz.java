package net.ktds.drink.admin.biz;

import net.ktds.drink.admin.vo.AdvertisementListVO;
import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;
import net.ktds.drink.admin.vo.SearchSoundTrackVO;
import net.ktds.drink.admin.vo.SoundTrackListVO;
import net.ktds.drink.admin.vo.SoundTrackVO;

public interface AdminBiz {
	
	public boolean addAdvertisement(AdvertisementVO advertisementVO);
	
	public AdvertisementListVO getAdvertisementVideo(SearchAdvertisementVO searchAdvertisement);
	
	public String getFileNameofAdvertisementBy(String advertisementId);
	
	public boolean deleteAdvertisement(String advertisementId);

	public AdvertisementVO getRandomAdvertisementVideoBy();
	
	public boolean addSoundTrack(SoundTrackVO soundTrackVO);
	
	public SoundTrackListVO getSoundTrack(SearchSoundTrackVO searchSoundTrack);
	
	public String getFileNameOfSoundTrackBy(String soundTrackId);
	
	public boolean deleteSoundTrack(String soundTrackId);

}
