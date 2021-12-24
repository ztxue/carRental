package com.shengyi.mapper.provider;

import com.shengyi.model.vo.CarRecordRequest;

//租赁记录搜索
public class CarRecordProvider {
    public String searchRecord(CarRecordRequest request) {
        StringBuffer sql = new StringBuffer("SELECT id,time_long,deposit,status,customer_id customerId,car_id carId,user_id userId,create_time FROM car_record WHERE 1=1");
        if (request.getStatus() != null) {
            sql.append(" AND status=#{vo.status}");
        }
        if (request.getStartTime() != null && request.getEndTime() != null) {
            sql.append(" AND create_time>=#{vo.startTime} AND create_time<=#{vo.endTime}");
        }
        return sql.toString();
    }
}
