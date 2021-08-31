package com.saipaisi.project.basic.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.saipaisi.framework.aspectj.lang.annotation.Log;
import com.saipaisi.framework.aspectj.lang.enums.BusinessType;
import com.saipaisi.project.basic.domain.BasicDataTable;
import com.saipaisi.project.basic.service.BasicDataTableService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 数据Controller
 * 
 * @author saaa
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/alltable")
public class BasicDataTableController extends BaseController
{
    @Autowired
    private BasicDataTableService basicDataTableService;

    /**
     * 查询数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicDataTable basicDataTable)
    {
        startPage();
        List<BasicDataTable> list = basicDataTableService.selectBasicDataTableList(basicDataTable);
        return getDataTable(list);
    }

    /**
     * 导出数据列表
     */
    @Log(title = "数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicDataTable basicDataTable)
    {
        List<BasicDataTable> list = basicDataTableService.selectBasicDataTableList(basicDataTable);
        ExcelUtil<BasicDataTable> util = new ExcelUtil<BasicDataTable>(BasicDataTable.class);
        return util.exportExcel(list, "alltable");
    }

    /**
     * 获取数据详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicDataTableService.selectBasicDataTableById(id));
    }

    /**
     * 新增数据
     */
    @Log(title = "数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicDataTable basicDataTable)
    {
        return toAjax(basicDataTableService.insertBasicDataTable(basicDataTable));
    }

    /**
     * 修改数据
     */
    @Log(title = "数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicDataTable basicDataTable)
    {
        return toAjax(basicDataTableService.updateBasicDataTable(basicDataTable));
    }

    /**
     * 删除数据
     */
    @Log(title = "数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicDataTableService.deleteBasicDataTableByIds(ids));
    }


    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        String filePath = "/home/upload/";
        // 取得当前上传文件的文件名称
        String originalFilename = file.getOriginalFilename();
        // 生成文件名
        String fileName = UUID.randomUUID() + "." + originalFilename.substring(originalFilename.lastIndexOf('.')+1, originalFilename.length());
        uploadFile(fileBytes, filePath, fileName);
        return new AjaxResult(200,"",filePath+fileName);
    }


    private void uploadFile(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }



}
