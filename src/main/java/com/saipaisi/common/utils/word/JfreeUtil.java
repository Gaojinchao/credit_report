package com.saipaisi.common.utils.word;


import cn.afterturn.easypoi.entity.ImageEntity;
import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.util.Assert;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


/**
 * @Author alfred.zong
 * @Date 2021/4/19 9:50
 * @Description
 */
@Slf4j
public class JfreeUtil {

    private static String tempImgPath="E:\\tempJfree.jpeg";

    /**
     * 将图片转化为字节数组
     * @return 字节数组
     */
    private static byte[] imgToByte(){
        File file = new File(tempImgPath);
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        //删除临时文件
        file.delete();
        return buffer;
    }

    public static ImageEntity pieChart(String title, Map<String, Integer> datas, int width, int height) {

        //创建主题样式
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));
        //设置主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        //根据jfree生成一个本地饼状图
        DefaultPieDataset pds = new DefaultPieDataset();
        datas.forEach(pds::setValue);
        //图标标题、数据集合、是否显示图例标识、是否显示tooltips、是否支持超链接
        JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, false);
        //设置抗锯齿
        chart.setTextAntiAlias(false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("暂无数据");
        //忽略无值的分类
        plot.setIgnoreNullValues(true);
        plot.setBackgroundAlpha(0f);
        //设置标签阴影颜色
        plot.setShadowPaint(new Color(255,255,255));
        //设置标签生成器(默认{0})
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1})/{2}"));
        try {
            ChartUtils.saveChartAsJPEG(new File(tempImgPath), chart, width, height);
        } catch (IOException e1) {
            log.error("生成饼状图失败！");
        }
        ImageEntity imageEntity = new ImageEntity(imgToByte(), width, height);
        Assert.notNull(imageEntity.getData(),"生成饼状图对象失败！");
        return imageEntity;
    }

//    public static ImageEntity barChart(String title, List<Map<String, Integer>> datas, int width, int height){
//        DefaultCategoryDataset categoryDataset=new DefaultCategoryDataset();
//        datas.forEach(e->{
//            categoryDataset.addValue(e.get("num"),e.get("name"),e.get("name"));
//        });
//        JFreeChart chart=ChartFactory.createBarChart(title,"类型","单位百分比",categoryDataset,)
//        //处理主标题乱码
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,15));
//        //处理子标题乱码
//        chart.getLegend().setItemFont(new Font("宋体",Font.BOLD,15));
//        //获取图表区域对象
//        /**
//         * 三种获取图标对象的方法
//         *  1.debug
//         *  2.输出（system.out.println()）
//         *  3.api文档
//         */
//
//        CategoryPlot categoryPlot = chart.getCategoryPlot();
//
//        //取得x轴的对象
//        CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
//        //取得y轴的对象
//        ValueAxis valueAxis = categoryPlot.getRangeAxis();
//
//        //解决x轴上的乱码问题
//        categoryAxis.setTickLabelFont(new Font("宋体",Font.BOLD,15));
//        //解决x轴外的乱码问题
//        categoryAxis.setLabelFont(new Font("宋体",Font.BOLD,15));
//
//        //解决y轴上的乱码问题
//        valueAxis.setTickLabelFont(new Font("宋体",Font.BOLD,15));
//        //解决y轴外的乱码问题
//        valueAxis.setLabelFont(new Font("宋体",Font.BOLD,15));
//
//
//
//    }



    /**
     * 将图片转化为字节数组
     * @return 字节数组
     */
    public static byte[] imgToByPathByte(String path){
        File file = new File(path);
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        //删除临时文件
        return buffer;
    }

}
