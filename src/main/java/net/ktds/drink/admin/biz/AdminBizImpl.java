package net.ktds.drink.admin.biz;



import java.util.List;

import net.ktds.drink.admin.dao.AdminDao;
import net.ktds.drink.admin.dao.AdminDaoImpl;
import net.ktds.drink.admin.vo.AdvertisementListVO;
import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;
import net.ktds.drink.admin.vo.SearchSoundTrackVO;
import net.ktds.drink.admin.vo.SoundTrackListVO;
import net.ktds.drink.admin.vo.SoundTrackVO;
import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.support.pager.PagerFactory;

public class AdminBizImpl implements AdminBiz {
	private AdminDao adminDao;

	public AdminBizImpl(){
		
		adminDao = new AdminDaoImpl();
		
	}
	@Override
	public boolean addAdvertisement(AdvertisementVO advertisementVO) {
		return adminDao.addAdvertisement(advertisementVO) > 0;
	}
	@Override
	public AdvertisementListVO getAdvertisementVideo(SearchAdvertisementVO searchAdvertisement) {
		
		int totalCount = adminDao.getCountOfAdvertisements(searchAdvertisement);
		
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchAdvertisement.getPageNumber());
		
		searchAdvertisement.setStartRowNumber(pager.getStartArticleNumber());
		searchAdvertisement.setEndRowNumber(pager.getEndArticleNumber());
		
		List<AdvertisementVO> advertisement = adminDao.getAdvertisementVideo(searchAdvertisement);
		
		AdvertisementListVO advertisementList = new AdvertisementListVO();
		advertisementList.setPager(pager);
		advertisementList.setAdvertisements(advertisement);
		
		
		return advertisementList;
	}
	
	@Override
	public String getFileNameofAdvertisementBy(String advertisementId) {
		AdvertisementVO advertisementVO = adminDao.getFileNameofAdvertisementBy(advertisementId);
		return advertisementVO.getFileName();
	}
	@Override
	public boolean deleteAdvertisement(String advertisementId) {
		return adminDao.deleteAdvertisement(advertisementId) > 0;
	}
	@Override
	public AdvertisementVO getRandomAdvertisementVideoBy() {
		return adminDao.getRandomAdvertisementVideoBy();
	}
	
	//===================================
	@Override
	public boolean addSoundTrack(SoundTrackVO soundTrackVO) {
		return adminDao.addSoundTrack(soundTrackVO) > 0;
	}
	@Override
	public SoundTrackListVO getSoundTrack(SearchSoundTrackVO searchSoundTrack) {
		
		int totalCount = adminDao.getCountOfSoundTracks(searchSoundTrack);
		
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchSoundTrack.getPageNumber());
		
		searchSoundTrack.setStartRowNumber(pager.getStartArticleNumber());
		searchSoundTrack.setEndRowNumber(pager.getEndArticleNumber());
		
		List<SoundTrackVO> soundTrack = adminDao.getSoundTrack(searchSoundTrack);
		
		SoundTrackListVO soundTrackList = new SoundTrackListVO();
		soundTrackList.setPager(pager);
		soundTrackList.setSoundTracks(soundTrack);
		
		return soundTrackList;
	}
	@Override
	public String getFileNameOfSoundTrackBy(String soundTrackId) {
		SoundTrackVO soundTrackVO = adminDao.getFileNameOfSoundTrackBy(soundTrackId);
		return soundTrackVO.getFileName();
	}
	@Override
	public boolean deleteSoundTrack(String soundTrackId) {
		return adminDao.deleteSoundTrack(soundTrackId) > 0;
	}
	

}
