package com.flower.xin.api.admin;

import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.LoginParam;
import com.flower.xin.common.response.LoginResponse;
import com.flower.xin.common.response.PictureResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.OssUtil;
import com.flower.xin.common.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author eiven
 */
@CrossOrigin
@Controller
@RequestMapping("/admin/pic")
public class PictureAdminController {


    @PostMapping("upload")
    @ResponseBody
    ResponseMessage<PictureResponse> upload(@RequestParam(name = "file", required = false) MultipartFile file) throws ServiceException, IOException {
        PictureResponse response = new PictureResponse(OssUtil.upload(file.getBytes()));
        return ResultUtil.success(response);
    }

}
