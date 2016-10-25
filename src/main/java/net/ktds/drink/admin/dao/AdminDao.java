package net.ktds.drink.admin.dao;

import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.admin.vo.SearchAdvertisementVO;

public interface AdminDao {

	public int addAdvertisement(AdvertisementVO advertisementVO);
	
	public List<AdvertisementVO> getAdvertisementVideo(SearchAdvertisementVO searchAdvertisement);

	public AdvertisementVO getFileNameofAdvertisementBy(String advertisementId);

	public int deleteAdvertisement(String advertisementId);

	public AdvertisementVO getRandomAdvertisementVideoBy();

	public int getCountOfAdvertisements(SearchAdvertisementVO searchAdvertisement);

	
}
