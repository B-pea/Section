package com.Section.util;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.Section.model.CarInfo;
import com.Section.model.CarrierInfo;
import com.Section.model.Notify;
import com.Section.model.NotifyChild;
import com.Section.model.OverlimitInfo;
import com.Section.model.SusOverlimitInfo;

/**
 * @Description: 常用工具类
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: 2016年12月5日 上午10:44:36.
 * @Update Date Time:
 * @see
 */
public class Common {
	/**
	 * Description: 判断变量是否为空
	 * 
	 * @param 待判断对象
	 * @return 判断结果，空返回true
	 * @throws Exception
	 * @see Note:
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Description: 获取当前时间，并格式化为数据库的格式，方便匹配
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 * @see Note:
	 */
	public static String CurrentTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String currentTime = format.format(date);
		return currentTime;
	}
	/**
	 * 计算比例
	 * @param divisor
	 * @param dividend
	 * @return
	 */
	public static String calculateRatio(double divisor,double dividend){
		if(divisor == 0 || dividend == 0){
			return "0";
		}
        DecimalFormat df = new DecimalFormat("#0.0000");
        double rate = (divisor/dividend)*100;
        String rateStr = String.valueOf(rate);
        if((rateStr.length() - rateStr.indexOf(".")+1) <= 4){
    		return rateStr;
        }
		return df.format(rate);
	}
	/**
	 * 首字母大小写转换
	 * 
	 * @param str
	 * @return
	 */
	public static String strToUpperCase(String str) {

		char[] chaArr = str.toCharArray();
		if (chaArr[0] >= 'a' && chaArr[0] <= 'z') {
			chaArr[0] = (char) (chaArr[0] - 32);
		}
		return String.valueOf(chaArr);

	}

	public static OverlimitInfo getOverLimitInfo(SusOverlimitInfo susInfo, CarInfo carInfo, CarrierInfo carrierInfo) {
		/*if (susInfo == null || carInfo == null || carrierInfo == null)
			return null;*/
		OverlimitInfo info = new OverlimitInfo();
		info.setBusiNum(susInfo.getBusiNum());
		info.setBusiType(susInfo.getBusiType());
		info.setSiteId(susInfo.getSiteId());
		info.setRoadwayNumber(susInfo.getRoadwayNumber());
		info.setMaxSpeed(susInfo.getMaxSpeed());
		info.setVehPlate(susInfo.getVehPlate());
		info.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(susInfo.getDateTime()));
		info.setBusiTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(susInfo.getBusiTime()));
		info.setBusiVehType(susInfo.getBusiVehType());
		info.setBusiAxleNum(susInfo.getBusiAxleNum());
		info.setBusiAixsGroupNum(susInfo.getBusiAixsGroupNum());
		info.setBusiAixsGroupModel(susInfo.getBusiAixsGroupModel());
		info.setBusiTotalWeight(susInfo.getBusiTotalWeight());
		info.setBusiSpeed(susInfo.getBusiSpeed());
		info.setDrivingStatus(susInfo.getDrivingStatus());
		info.setBusiAcc(susInfo.getBusiAcc());
		info.setBusiVehLength(susInfo.getBusiVehLength());
		info.setBusiVehWidth(susInfo.getBusiVehWidth());
		info.setBusiVehHeight(susInfo.getBusiVehHeight());
		info.setBusiAxleWeight1(susInfo.getBusiAxleWeight1());
		info.setBusiAxleWeight2(susInfo.getBusiAxleWeight2());
		info.setBusiAxleWeight3(susInfo.getBusiAxleWeight3());
		info.setBusiAxleWeight4(susInfo.getBusiAxleWeight4());
		info.setBusiAxleWeight5(susInfo.getBusiAxleWeight5());
		info.setBusiAxleWeight6(susInfo.getBusiAxleWeight6());
		info.setBusiAxleWeight7(susInfo.getBusiAxleWeight7());
		info.setBusiAxleWeight8(susInfo.getBusiAxleWeight8());
		info.setBusiAxleDis1(susInfo.getBusiAxleDis1());
		info.setBusiAxleDis2(susInfo.getBusiAxleDis2());
		info.setBusiAxleDis3(susInfo.getBusiAxleDis3());
		info.setBusiAxleDis4(susInfo.getBusiAxleDis4());
		info.setBusiAxleDis5(susInfo.getBusiAxleDis5());
		info.setBusiAxleDis6(susInfo.getBusiAxleDis6());
		info.setBusiAxleDis7(susInfo.getBusiAxleDis7());
		info.setBusiOverlimited(susInfo.getBusiOverlimited());
		info.setBizcertid(susInfo.getBizcertid());
		info.setOwnerId(susInfo.getOwnerId());
		info.setOwnerName(susInfo.getOwnerName());
		info.setVehType(susInfo.getVehType());
		info.setAixsNum(susInfo.getAixsNum());
		info.setAixsGroupNum(susInfo.getAixsGroupNum());
		info.setLimitLength(susInfo.getLimitLength());
		info.setLimitWidth(susInfo.getLimitWidth());
		info.setLimitHeight(susInfo.getLimitHeight());
		info.setLimitWeight(susInfo.getLimitWeight());
		if(carrierInfo!=null){
			info.setVehrierId(carrierInfo.getVehrierId());
			info.setVehrierName(carrierInfo.getName());
			info.setVehrierAdd(carrierInfo.getAddress());
			info.setVehrierPost(carrierInfo.getZipcode());
			info.setVehireArea(carrierInfo.getOrgName());
			info.setVehireTell(carrierInfo.getPhonenum());
			info.setLawPersonName(carrierInfo.getLegalRepName());
			info.setLawPersonId(carrierInfo.getLegalRepIdvehd());
		}
		
		info.setCargoname(susInfo.getCargoName());
		info.setCargotype(susInfo.getCargoType());
		if(carInfo != null){
			info.setDeliveryId(carInfo.getDeliveryId());
			info.setDriverId(carInfo.getDriverId());
			info.setDriverName(carInfo.getDriverName());
		}
		// 这两个字段在立案审批中可以不填写，所以注释掉
		// info.setDeparture();
		// info.setDestination();
		info.setOverLength(susInfo.getOverLength());
		info.setOverWidth(susInfo.getOverWidth());
		info.setOverHeight(susInfo.getOverHeight());
		info.setOverSpeek(susInfo.getOverSpeek());
		info.setOverWeight(susInfo.getOverWeight());
		float overRate = susInfo.getOverRate();
		info.setOverRate((int) overRate);
		/*info.setLastUpdateDate(new Date());*/
		info.setLastUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		info.setUpdateType("A");
		return info;
	}

	public static List<NotifyChild> getNotifyChild(List<Notify> notify) {
		List<NotifyChild> child = new ArrayList<>();
		for (Notify n : notify) {
			NotifyChild c = new NotifyChild();
			try {
				BeanUtils.copyProperties(c, n);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			child.add(c);
		}
		return child;
	}

	/*private static Object getFieldValueByName(String fieldName, Object object) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = object.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(object, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}*/

	/**
	 * @Description:把封装为Map方法
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 *//*
	public static Map<String, Object> getMapByObject(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<>();
		for (Field f : fields) {
			map.put(f.getName(), getFieldValueByName(f.getName(), object));
		}
		return map;
	}*/
}