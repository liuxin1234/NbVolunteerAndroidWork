package com.example.core;

import com.example.core.listener.AccessTokenListener;
import com.example.model.ActionCallbackListener;
import com.example.model.Attachment.AttachmentParaDto;
import com.example.model.Attachment.AttachmentsReturnDto;
import com.example.model.Attachment.AttachmentsViewDto;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityCreateBO;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.activityRecruit.ActivityRecruitDto;
import com.example.model.activityRecruit.ActivityRecruitListDto;
import com.example.model.activityRecruit.ActivityRecruitQueryOptionDto;
import com.example.model.activityTime.ActivityTimeListDto;
import com.example.model.activityTime.ActivityTimeQueryOptionDto;
import com.example.model.activityattention.ActivityAttentionDto;
import com.example.model.activityattention.ActivityAttentionListDto;
import com.example.model.activityattention.ActivityAttentionQueryOptionDto;
import com.example.model.area.AreaListDto;
import com.example.model.area.AreaViewDto;
import com.example.model.company.CompanyListDto;
import com.example.model.company.CompanyQueryOptionDto;
import com.example.model.company.CompanyViewDto;
import com.example.model.content.ContentListDto;
import com.example.model.content.ContentQueryOptionDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.dictionary.DictionaryTypeListDto;
import com.example.model.dictionary.DictionaryTypeQueryOptionDto;
import com.example.model.dictionary.DictionaryViewDto;
import com.example.model.jobActivity.JobActivityViewDto;
import com.example.model.mobileVersion.MobileVersionListDto;
import com.example.model.mobileVersion.MobileVersionQueryOptionDto;
import com.example.model.mobileVersion.MobileVersionViewDto;
import com.example.model.organization.OrganizationListDto;
import com.example.model.organization.OrganizationQueryOptionDto;
import com.example.model.signRecord.SignInOutDto;
import com.example.model.user.UserDepartmentViewDto;
import com.example.model.user.UserDto;
import com.example.model.user.UserListDto;
import com.example.model.user.UserPhotoDto;
import com.example.model.user.UserViewDto;
import com.example.model.volunteer.VolunteerBaseListDto;
import com.example.model.volunteer.VolunteerBaseQueryOptionDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerDto;
import com.example.model.volunteer.VolunteerQueryDto;
import com.example.model.volunteer.VolunteerTopViewDto;
import com.example.model.volunteer.VolunteerViewDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/2 16:40
 * 修改备注：
 */
public interface AppAction {
    /**
     * 获取票据
     *
     * @param username 用户名
     * @param password 密码MD5加密
     */
    public void getAccessToken(String username, String password, AccessTokenListener tokenListener);

    public void userCreate(List<UserDto> creates, ActionCallbackListener<List<String>> listener);

    public void userLogin(String userName, String password, ActionCallbackListener<UserViewDto> listener);

    public void activityCreate(List<ActivityCreateBO> activityCreateBOs, ActionCallbackListener<List<String>> listener);

    public void activityQuery(ActivityQueryOptionDto activityQueryOptionDto, ActionCallbackListener<PagedListEntityDto<ActivityListDto>> listener);

    public void activityRecruitCreate(List<ActivityRecruitDto> create, ActionCallbackListener<List<String>> listener);

    public void activityAttentionCreate(List<ActivityAttentionDto> create, ActionCallbackListener<List<String>> listener);

    public void activityAttentionDelete(List<String> id, ActionCallbackListener<String> listener);

    public void activityAttentionQuery(ActivityAttentionQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<ActivityAttentionListDto>> listener);

    public void jobActivityDetail(String jobActivityId, ActionCallbackListener<JobActivityViewDto> listener);

    //用户登录利用票据获取到的username，来获取用户的信息
    public void userIdLogin(String userId, ActionCallbackListener<UserListDto> listener);

    /**
     * activity 详细查询方法
     *
     * @param activityId
     * @param listener
     */
    public void activityDetail(String activityId, ActionCallbackListener<ActivityViewDto> listener);

    //志愿者（新增）
    public void volunteerCreate(List<VolunteerCreateDto> volunteerCreateBOs, ActionCallbackListener<List<String>> listener);

    //志愿者（查询）
    public void volunteerQuery(VolunteerQueryDto volunteerQueryBO, ActionCallbackListener<VolunteerDto> listener);

    public void volunteer_departmentQuery(String UserID, ActionCallbackListener<List<UserDepartmentViewDto>> listener);

    //志愿者详情信息
    public void get_volunteerDetail(String id, ActionCallbackListener<VolunteerViewDto> listener);

    //company POST 查询
    public void companyQuery(CompanyQueryOptionDto companyQueryOptionDto, ActionCallbackListener<PagedListEntityDto<CompanyListDto>> listener);

    //company get 查询
    public void companyDetail(String id, ActionCallbackListener<CompanyViewDto> listener);

    //company post创建
    public void companyCreat(List<CompanyListDto> companyListDtos, ActionCallbackListener<List<String>> listener);

    //company 更新
    public void companyUpdate(List<CompanyListDto> companyListDtos, ActionCallbackListener<List<String>> listener);

    //志愿者（信息修改）
    public void volunteerUpdate(List<VolunteerViewDto> volunteerEditDto, ActionCallbackListener<String> listener);

    //发送短信验证码功能
    public void getVerification(String phone, ActionCallbackListener<String> listener);

    //验证短信验证码功能
    public void getverify(String phone, String SMScode, ActionCallbackListener<Boolean> listener);

    //更改密码
    public void getold_PSWD(String id, ActionCallbackListener<String> listener);

    public void setnew_PSWD(String id, String newPassword, ActionCallbackListener<String> listener);

    //更改头像
    public void update_portrait(UserPhotoDto photo, ActionCallbackListener<String> listener);

    //获取头像
    public void get_portrait(String UserId, ActionCallbackListener<String> listener);

    //获取星星
    public void query_star_level(Double timelength, ActionCallbackListener<String> listener);

    /**
     * dictionary 查询
     *
     * @param query
     * @param listener
     */
    public void dictionaryQuery(DictionaryQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<DictionaryListDto>> listener);

    public void dictionaryQueryDefault(String typeCode, String parentId, ActionCallbackListener<List<DictionaryListDto>> listener);

    public void dictionaryQueryDetailDefault(String typeCode, String code, ActionCallbackListener<DictionaryViewDto> listener);


    /**
     * dictionary 类型 查询
     *
     * @param query
     * @param listener
     */
    public void dictionaryTypeQuery(DictionaryTypeQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<DictionaryTypeListDto>> listener);

    /**
     * 组织查询
     *
     * @param query
     * @param listener
     */
    public void organizationQuery(OrganizationQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<OrganizationListDto>> listener);

    public void organizationQueryChild(String parentId, ActionCallbackListener<List<OrganizationListDto>> listener);

    /**
     * 所在区域  get
     * 一级一级展开查询
     *
     * @param parentId
     * @param listener
     */
    public void AreaQuery(String parentId, ActionCallbackListener<List<AreaListDto>> listener);

    public void areaDetails(String code, ActionCallbackListener<AreaViewDto> listener);


    /**
     * 新闻的分页查询
     *
     * @param query
     * @param listener
     */
    public void contentQuery(ContentQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<ContentListDto>> listener);


    public void activityRecruitQuery(ActivityRecruitQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<ActivityRecruitListDto>> listener);


    /**
     * 志愿者服务站点
     */
    public void volunteerBaseQuery(VolunteerBaseQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>> listener);

    /**
     * 签到签退明细
     */
    public void signRecordCreate(List<SignInOutDto> signInOutDtos, ActionCallbackListener<List<String>> listener);

    //上传专业证书
    public void update_major_attachment(List<AttachmentParaDto> data, ActionCallbackListener<List<AttachmentsReturnDto>> listener);

    //查询活动时间
    public void activityTimeQuery(ActivityTimeQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<ActivityTimeListDto>> listener);

    public void mobileVersionQuery(MobileVersionQueryOptionDto query, ActionCallbackListener<PagedListEntityDto<MobileVersionListDto>> listener);

    public void mobileVersionDetails(String id, ActionCallbackListener<MobileVersionViewDto> listener);

    public void attatchmentDetails(String id, ActionCallbackListener<AttachmentsViewDto> listener);

    /**
     * 忘记密码
     */
    public void existsMobile(String mobile, Integer userType, ActionCallbackListener<String> listener);

    public void existsEmail(String email, Integer userType, ActionCallbackListener<String> listener);

    public void getByMobile(String mobile, Integer userType, ActionCallbackListener<String> listener);

    public void existsIDNumber(String idnumber, String mobile, Integer userType, ActionCallbackListener<String> listener);

    public void getByIDNumber(String idnumber, String mobile, Integer userType, ActionCallbackListener<String> listener);

    //已报名人员获取
    public void getMembership(String id, String maxSize, ActionCallbackListener<List<VolunteerTopViewDto>> listener);

    //个人报名活动删除
    public void activityRecruitRemove(List<String> activityRecruitId,ActionCallbackListener<String> listener);
}
