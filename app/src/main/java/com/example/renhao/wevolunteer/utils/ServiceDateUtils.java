package com.example.renhao.wevolunteer.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.utils
 * 项目日期：2018/6/22
 * 作者：liux
 * 功能：用于首页和各个活动/岗位列表页的活动时间显示工具栏
 *    "StartTime": "2018-06-23 09:00:00",
 *   "FinishTime": "2018-07-02 16:00:00",
 * @author 750954283(qq)
 */

public class ServiceDateUtils {

    public static String setServiceTime(String startTime,String finishTime){
        String[] splitStartTiem = startTime.split(" ");
        String[] splitFinishTime = finishTime.split(" ");
//        Logger.e(splitStartTiem[0]+"\n"+splitStartTiem[1]+"\n"+splitFinishTime[0]+"\n"+splitFinishTime[1]);
        //服务开始日期
        String startYear = splitStartTiem[0].split("-")[0];
        String startMonth = splitStartTiem[0].split("-")[1];
        String startDay = splitStartTiem[0].split("-")[2];
        String startTimes = splitStartTiem[1].split(":")[0];
        String startDivision = splitStartTiem[1].split(":")[1];
        String startSecond = splitStartTiem[1].split(":")[2];
        //服务结束时间
        String finishYear = splitFinishTime[0].split("-")[0];
        String finishMonth = splitFinishTime[0].split("-")[1];
        String finishDay = splitFinishTime[0].split("-")[2];
        String finishTimes = splitFinishTime[1].split(":")[0];
        String finishDivision = splitFinishTime[1].split(":")[1];
        String finishSecond = splitFinishTime[1].split(":")[2];

        String serviceTime = startMonth + "月" +startDay + "日" + startTimes + "时" + "—"
                + finishMonth + "月" + finishDay + "日" + finishTimes + "时";
//        Logger.e(serviceTime);
        return serviceTime;
    }
}
