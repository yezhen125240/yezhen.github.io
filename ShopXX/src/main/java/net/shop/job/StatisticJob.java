/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.job;

import java.util.Calendar;

import javax.annotation.Resource;

import net.shop.entity.Statistic;
import net.shop.service.StatisticService;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job - 统计
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
@Lazy(false)
@Component("statisticJob")
public class StatisticJob {

	@Resource(name = "statisticServiceImpl")
	private StatisticService statisticService;

	/**
	 * 收集
	 */
	@Scheduled(cron = "${job.statistic_collect.cron}")
	public void collect() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (statisticService.exists(year, month, day)) {
			return;
		}
		Statistic statistic = statisticService.collect(year, month, day);
		statisticService.save(statistic);
	}

}