package net.ktds.drink.admin.biz;

import java.util.List;

import net.ktds.drink.admin.dao.AdminDao;
import net.ktds.drink.admin.dao.AdminDaoImpl;
import net.ktds.drink.admin.vo.AdvertisementVO;

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
	public List<AdvertisementVO> getAdvertisementVideo() {
		return adminDao.getAdvertisementVideo();
	}
	@Override
	public String getFileNameofAdvertisementBy(String advertisementId) {
		AdvertisementVO advertisementVO = adminDao.getFileNameofAdvertisementBy(advertisementId);
		return advertisementVO.getFileName();
	}
	@Override
	public boolean deleteAdvertisement(String advertisementId) {
		return adminDao.deleteAdvertisement(advertisementId);
	}

}
