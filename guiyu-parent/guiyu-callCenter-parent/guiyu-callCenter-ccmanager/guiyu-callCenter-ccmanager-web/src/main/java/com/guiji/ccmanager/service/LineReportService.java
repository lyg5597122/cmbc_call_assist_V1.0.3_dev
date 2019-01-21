package com.guiji.ccmanager.service;


import com.guiji.callcenter.dao.entity.ReportLineCode;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LineReportService {



    void statisticsReportLineCode() throws ParseException;

    List<Map> getLineMonitorReport(Integer lineId, Long userId, Date startTime);

    Map getLineHangupDetail(Integer lineId, Date startTime, Date enTime);
}
