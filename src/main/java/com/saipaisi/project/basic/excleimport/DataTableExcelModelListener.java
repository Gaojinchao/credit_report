package com.saipaisi.project.basic.excleimport;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.saipaisi.common.utils.ThreadLocalCache;
import com.saipaisi.project.basic.domain.BasicDataTable;
import com.saipaisi.project.basic.mapper.BasicDataTableMapper;
import com.saipaisi.project.system.domain.SysUser;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lin.shi
 * @Date: 2020/8/6 10:51 下午
 * @Describe:
 */
@Slf4j
public class DataTableExcelModelListener extends AnalysisEventListener<BasicDataTable> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 20000;

    //自定义用于暂时存储data
    private List<BasicDataTable> list = new ArrayList<BasicDataTable>();


    private BasicDataTableMapper basicDataTableMapper;

    private SysUser sysUser;

    private  Long pId;

    private String type;

    private String ordersn;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param
     */
    public DataTableExcelModelListener(BasicDataTableMapper basicDataTableMapper,  String type,String ordersn) {
        this.sysUser = sysUser;
        this.type=type;
        this.ordersn=ordersn;
        this.basicDataTableMapper=basicDataTableMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(BasicDataTable data, AnalysisContext context) {

        if(data!=null){
            data.setType(type);
            data.setOrderSn(ordersn);
            list.add(data);
        }

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        if(list!=null && list.size()>0){
            int i=0;
            for (BasicDataTable b:list
                 ) {
                i+=1;
                b.setSortIndex(i);
                basicDataTableMapper.insertBasicDataTable(b);
            }

            ThreadLocalCache.set("dataTable"+type,list);
        }
        log.info("存储数据库成功！");
    }

}
