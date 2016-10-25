package net.ktds.drink.admin.biz;

import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;

public interface AdminBiz {
	
	public boolean addAdvertisement(AdvertisementVO advertisementVO);
	
	public List<AdvertisementVO> getAdvertisementVideo();
	
	public String getFileNameofAdvertisementBy(String advertisementId);
	
	public boolean deleteAdvertisement(String advertisementId);
}
