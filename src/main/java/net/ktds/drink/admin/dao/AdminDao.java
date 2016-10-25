package net.ktds.drink.admin.dao;

import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;

public interface AdminDao {

	public int addAdvertisement(AdvertisementVO advertisementVO);
	
	public List<AdvertisementVO> getAdvertisementVideo();

	public AdvertisementVO getFileNameofAdvertisementBy(String advertisementId);

	public boolean deleteAdvertisement(String advertisementId);
	
}
