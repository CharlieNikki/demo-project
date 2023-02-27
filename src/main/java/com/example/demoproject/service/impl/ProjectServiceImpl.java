package com.example.demoproject.service.impl;

import com.example.demoproject.entity.DetectProjectList;
import com.example.demoproject.entity.Project;
import com.example.demoproject.mapper.DetectProjectListMapper;
import com.example.demoproject.mapper.ImageMapper;
import com.example.demoproject.mapper.ProjectMapper;
import com.example.demoproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper mapper;

    @Resource
    private ImageMapper imageMapper;

    @Autowired
    private DetectProjectListMapper detectProjectListMapper;

    @Override
    public List<Project> selectAllProject() {
        return mapper.selectAllProject();
    }

    @Override
    public List<Project> selectProjectByStatus(Integer dataStatus, String userId) {
        return mapper.selectProjectByStatus(dataStatus, userId);
    }

    @Override
    public List<Project> selectProjectByUserId(String userId) {
        return mapper.selectProjectByUserId(userId);
    }

    /**
     * 根据id，删除对应的project信息
     *
     * @param id 工程id
     * @return
     */
    @Override
    @Transactional
    public boolean deleteProjectById(String id) {

        boolean flag = false;

        // 1. 删除本地图片
        // 搜索图片名称，拼接本地资源路径进行删除操作
        List<String> imagesPath = imageMapper.selectImageNameByProjectId(id);
        int i = 0;
        for (String s : imagesPath) {
            // 拼接成路径地址
            //String imagePath = "D:/project/images/" + s;
            String imagePath = "/usr/local/project/images/" + s;
            // 根据文件路径创建文件对象
            File file = new File(imagePath);
            // 该文件不是文件夹且不为空时
            if (file.isFile() && file.exists()) {
                // 删除该图片
                if (file.delete()) {
                    i++;
                }
            }
        }
        // 判断图片是否删除成功
        if (i == imagesPath.size()) {
            // 2. 直接删除id对应的project工程信息
            if (mapper.deleteProjectById(id) == 1) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<Project> selectProjectByPhone(String phone) {
        return mapper.selectProjectByPhone(phone);
    }

    /**
     * 新增project信息,新增projectList信息
     * @param project
     * @return
     */
    @Override
    @Transactional
    public boolean insertProject(Project project) {

        DetectProjectList detectProjectList = new DetectProjectList();
        detectProjectList.setProjectId(project.getId());
        detectProjectList.setProjectName(project.getProjectName());
        detectProjectList.setDate(project.getDate());
        detectProjectList.setContent(project.getContent());
        detectProjectList.setUsername(project.getUsername());
        detectProjectList.setDatastatus("未受理");
        detectProjectList.setTelephone(project.getTelephone());

        int insertProjectResult = mapper.insertProject(project);
        int insertListResult = detectProjectListMapper.insertDetectProjectList(detectProjectList);

        return insertListResult == insertProjectResult;
    }
}
