package com.droppynapi.repo;

import com.droppynapi.model.SizeChart;

import java.util.Collection;

public interface SizeChartRepo {
    SizeChart addSizeChart(SizeChart sizeChart, String brandId);
    Collection<SizeChart> getSizeChartByBrandId(String brandId);
    SizeChart getSizeChartById(String id);
    Collection<SizeChart> getAllSizeChart();
    void removeSizeChartById(String id);

}
