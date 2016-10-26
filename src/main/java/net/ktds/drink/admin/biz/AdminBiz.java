package net.ktds.drink.admin.biz;

import net.ktds.drink.admin.vo.AdvertisementListVO;
import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;

public interface AdminBiz {
	
	public boolean addAdvertisement(AdvertisementVO advertisementVO);
	
	public AdvertisementListVO getAdvertisementVideo(SearchAdvertisementVO searchAdvertisement);
	
	public String getFileNameofAdvertisementBy(String advertisementId);
	
	public boolean deleteAdvertisement(String advertisementId);

	public AdvertisementVO getRandomAdvertisementVideoBy();
}
