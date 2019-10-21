package com.oldhandgo.rest;

import com.oldhandgo.domain.Picture;
import com.oldhandgo.log.Log;
import com.oldhandgo.service.PictureService;
import com.oldhandgo.service.dto.PictureQueryCriteria;
import com.oldhandgo.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dormirr
 */
@RestController
@RequestMapping("/api")
public class PictureController {

    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Log("查询图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_SELECT')")
    @GetMapping(value = "/pictures")
    public ResponseEntity getRoles(PictureQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(pictureService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    /**
     * 上传图片
     *
     * @param file 文件
     * @return 上传是否成功
     */
    @Log("上传图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_UPLOAD')")
    @PostMapping(value = "/pictures")
    public ResponseEntity upload(@RequestParam MultipartFile file) {
        String userName = SecurityUtils.getUsername();
        Picture picture = pictureService.upload(file, userName);
        Map<String, Object> map = new HashMap<>(3);
        map.put("errno", 0);
        map.put("id", picture.getId());
        map.put("data", new String[]{picture.getUrl()});
        return new ResponseEntity(map, HttpStatus.OK);
    }

    /**
     * 删除图片
     *
     * @param id 图片ID
     * @return 结果
     */
    @Log("删除图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_DELETE')")
    @DeleteMapping(value = "/pictures/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        pictureService.delete(pictureService.findById(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 删除多张图片
     *
     * @param ids 图片ID
     * @return 结果
     */
    @Log("删除图片")
    @PreAuthorize("hasAnyRole('ADMIN','PICTURE_ALL','PICTURE_DELETE')")
    @DeleteMapping(value = "/pictures")
    public ResponseEntity deleteAll(@RequestBody Long[] ids) {
        pictureService.deleteAll(ids);
        return new ResponseEntity(HttpStatus.OK);
    }
}