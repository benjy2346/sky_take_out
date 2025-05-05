package com.sky.controller.admin;

import com.sky.constant.StatusConstant;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增种类
     * @param categoryDTO 种类dto
     * @return 返回result class
     */
    @PostMapping
    public Result<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("新增种类；{}",categoryDTO.getType());
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 查询菜品分类
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("查询菜品分类");
        return Result.success(categoryService.pageQuery(categoryPageQueryDTO));
    }

    @PutMapping
    public Result<String> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("编辑菜品分类");
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> deleteCategory(Long id){
        log.info("删除菜品，id:{}",id);
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){
        log.info("启用禁用分类：{}",(status.equals( StatusConstant.ENABLE))?"启用":"禁用");
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> getCategoryByType(Integer type){
        log.info("根据类型查询菜品分类");

        return Result.success(categoryService.getCategoryByType(type));
    }
}
