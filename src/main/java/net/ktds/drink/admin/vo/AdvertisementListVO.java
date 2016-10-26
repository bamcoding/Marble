package net.ktds.drink.admin.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.user.vo.UserVO;

public class AdvertisementListVO {
	private List<AdvertisementVO> advertisements;
	private Pager pager;
	
	public List<AdvertisementVO> getAdvertisements() {
		return advertisements;
	}
	public void setAdvertisements(List<AdvertisementVO> advertisements) {
		this.advertisements = advertisements;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
