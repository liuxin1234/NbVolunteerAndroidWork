package com.example.api;

import com.example.model.AccessTokenBO;
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
 * 类描述：接口类
 * 创建人：renhao
 * 创建时间：2016/8/2 13:42
 * 修改备注：
 */
public interface Api {

    public static final String USERID_LOGIN = "Nbcei.Framework.Api.Impl/v1/user/query";
    public static final String USER_CREATE = "Nbcei.Framework.Api.Impl/v1/user/create";
    public static final String USER_LOGIN = "Nbcei.Framework.Api.Impl/v1/user/get";

    //活动
    public static final String ACTIVITY_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/create";
    public static final String ACTIVITY_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/query";
    public static final String ACTIVITY_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/details";

    //报名
    public static final String ACTIVITY_RECRUIT_CREAT = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityrecruit/create";
    public static final String ACTIVITY_RECRUIT_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityrecruit/query";
    public static final String ACTIVITY_GETMEMBERSHIP = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activity/GetMembership";

    //关注
    public static final String ACTIVITY_ATTENTION_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityattention/create";
    public static final String ACTIVITY_ATTENTION_DELETE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityattention/delete";
    public static final String ACTIVITY_ATTENTION_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityattention/query";

    //岗位
    public static final String JOBACTIVITY_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/jobactivity/details";

    //志愿者
    public static final String VOLUNTEER_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/create";
    public static final String VOLUNTEER_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/query";
    public static final String VOLUNTEER_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/Volunteer/details";
    public static final String VOLUNTEER_UPDATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/Volunteer/update";

    //
    //组织
    public static final String COMPANY_CREAT = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/create";
    public static final String COMPANY_UPDATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/update";
    public static final String COMPANY_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/query";
    public static final String COMPANY_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/details";

    //字典
    public static final String DICTIONARY_QUERY = "Nbcei.Framework.Api.Impl/v1/dictionary/query ";
    public static final String DICTIONARYTYPE_QUERY = "Nbcei.Framework.Api.Impl/v1/dictionarytype/query";
    public static final String DICTIONARYTYPE_QUERY_CHILD = "Nbcei.Framework.Api.Impl/v1/dictionary/query/child";
    public static final String DICTIONARYTYPE_QUERY_DEFAULT = "Nbcei.Framework.Api.Impl/v1/dictionary/query/default/bycode";
    public static final String DICTIONARYTYPE_QUERY_DETAIL_DEFAULT = "Nbcei.Framework.Api.Impl/v1/dictionary/details/default";

    //组织
    public static final String ORGANIZATION_QUERY = "Nbcei.Framework.Api.Impl/v1/organization/query";
    public static final String ORGANIZATION_DETAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/details";
    public static final String ORGANIZATION_QUERY_CHILD = "Nbcei.Framework.Api.Impl/v1/organization/query/child";

    //所在区域
    public static final String AREA_QUERY = "Nbcei.Framework.Api.Impl/v1/area/query/child";
    public static final String AREA_DETAILS = "Nbcei.Framework.Api.Impl/v1/area/details";

    //新闻
    public static final String CONTENT_QUERY = "Nbcei.Plugin.CMS.Api.Impl/v1/Content/query";

    //


    //发送验证码
    public static final String SEND_PHONE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/smsauth/send/register";
    public static final String GET_VERYFY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/smsauth/valid/register";

    //密码修改
    public static final String GET_OLD_PSWD = "Nbcei.Framework.Api.Impl/v1/user/query/password";
    public static final String SET_NEW_PSWD = "Nbcei.Framework.Api.Impl/v1/user/update/setpassword";

    //头像操作
    public static final String UPDATE_PORTRAIT = "Nbcei.Framework.Api.Impl/v1/user/update/photo";
    public static final String GET_PORTRAIT = "Nbcei.Framework.Api.Impl/v1/user/query/photo";


    //志愿者服务站点
    public static final String VOLUNTEER_BASE_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteerbase/query";

    //签到签退明细
    public static final String SIGNRECORD_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/signrecord/create";

    //专业证书批量上传
    public static final String UPDATE_MAJOR_ATTACHMENT = "Nbcei.Plugin.Attachment.Api.Impl/v1/attachments/savefile";

    //查询活动时间
    public static final String ACTIVITY_TIME_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activitytime/query";

    //查询志愿者等级
    public static final String VOLUNTEER_STAR_LEVEL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/GetScoreLevel";
    public static final String VOLUNTEER_DEPARTMENT = "Nbcei.Framework.Api.Impl/v1/userdepartment/query/user";


    //app版本查询
    public static final String MOBILE_VERSION_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/mobileversion/query";
    public static final String MOBILE_VERSION_DETAILS = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/mobileversion/details";

    //获取文件地址
    public static final String ATTACTHMENTS_DETAILS = "Nbcei.Plugin.Attachment.Api.Impl/v1/attachments/details";

    //忘记密码
    public static final String EXISTS_MOBILE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/existsmobile";
    public static final String GET_BY_MOBILE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/getbymobile";
    public static final String EXISTS_IDNUMBER = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/existsidnumberandmobile";
    public static final String GET_BY_IDNUMBER = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/getbyidnumberandmobile";
    public static final String EXISTS_EMAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/existsemail";

    //报名活动删除
    public static final String ACTIVITY_RECRUIT_REMOVE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityrecruit/remove";

    /**
     * 获取accessToken
     *
     * @param username 用户名
     * @param password 密码MD5加密
     * @return 返回票据
     */
    public AccessTokenBO getAccessToken(String username, String password);

    /**
     * 刷新AccessToken
     *
     * @param refreshToken 刷新票据
     * @return 返回票据
     */
    public AccessTokenBO getAccessToken(String refreshToken);

    public ApiResponse<List<String>> userCreate(List<UserDto> creates, String accessToken);

    public ApiResponse<UserViewDto> userLogin(String userName, String password, String assessToken);

    public ApiResponse<List<String>> activityCreate(List<ActivityCreateBO> creates, String assessToken);

    public ApiResponse<PagedListEntityDto<ActivityListDto>> activityQuery(ActivityQueryOptionDto query, String accessToken);

    //利用用户票据获得的userId，来获取用户信息
    public ApiResponse<UserListDto> userIdLogin(String userId, String accessToken);

    /**
     * activity 详细信息查询方法
     *
     * @param activityId
     * @param accessToken
     * @return
     */
    public ApiResponse<ActivityViewDto> activityDetail(String activityId, String accessToken);

    /**
     * 报名
     *
     * @param create
     * @param accessToken
     * @return
     */
    public ApiResponse<List<String>> activityRecruitCreate(List<ActivityRecruitDto> create, String accessToken);

    /**
     * 获取报名状态
     *
     * @param query
     * @param accessToken
     * @return
     */
    public ApiResponse<PagedListEntityDto<ActivityRecruitListDto>> activityRecuitQuery(ActivityRecruitQueryOptionDto query, String accessToken);

    public ApiResponse<List<String>> activityAttentionCreate(List<ActivityAttentionDto> create, String accessToken);

    public ApiResponse<String> activityAttentionDelete(List<String> id, String accessToken);

    public ApiResponse<PagedListEntityDto<ActivityAttentionListDto>> activityAttentionQuery(ActivityAttentionQueryOptionDto query, String accessToken);

    public ApiResponse<List<VolunteerTopViewDto>> activity_membership(String query, String accessToken);

    /**
     * 岗位详细查询
     *
     * @param jobActivityId
     * @param accessToken
     * @return
     */
    public ApiResponse<JobActivityViewDto> jobActivityDetail(String jobActivityId, String accessToken);

    public ApiResponse<List<String>> volunteerCreate(List<VolunteerCreateDto> creates, String accessToken);

    public ApiResponse<VolunteerDto> volunteerQuery(VolunteerQueryDto query, String accessToken);


    public ApiResponse<PagedListEntityDto<CompanyListDto>> companyQuery(CompanyQueryOptionDto query, String accessToken);

    public ApiResponse<CompanyViewDto> companyDetail(String id, String acessToken);

    public ApiResponse<List<String>> companyCreat(List<CompanyListDto> creates, String accessToken);

    public ApiResponse<List<String>> companyUpdate(List<CompanyListDto> update, String accessToken);

    //短信验证码
    public ApiResponse<String> send_phone(String phone, String accessToken);

    public ApiResponse<Boolean> get_verify(String phone, String SMScode, String accessToken);

    //志愿者详细信息查询
    public ApiResponse<VolunteerViewDto> get_volunteerDetail(String id, String accessToken);

    //志愿者信息修改
    public ApiResponse<String> volunteerUpdate(List<VolunteerViewDto> update, String accessToken);

    //修改密码
    public ApiResponse<String> get_oldPSWD(String id, String accessToken);

    public ApiResponse<String> set_newPSWD(String id, String newPassword, String accessToken);

    //头像上传
    public ApiResponse<String> update_portrait(UserPhotoDto portrait, String accessToken);

    //取得头像
    public ApiResponse<String> get_portrait(String UserID, String accessToken);

    /**
     * @param query
     * @param accessToken
     * @return
     */
    public ApiResponse<PagedListEntityDto<DictionaryListDto>> dictionaryQuery(DictionaryQueryOptionDto query, String accessToken);

    public ApiResponse<PagedListEntityDto<DictionaryTypeListDto>> dictionaryTypeQuery(DictionaryTypeQueryOptionDto query, String accessToken);

    /**
     * 字典查询方法
     *
     * @param typeCode
     * @param parentId
     * @param accessToken
     * @return
     */
    public ApiResponse<List<DictionaryListDto>> dictionaryQueryDefault(String typeCode, String parentId, String accessToken);

    public ApiResponse<DictionaryViewDto> dictionaryQueryDetailDefault(String typeCode, String code, String accessToken);

    /**
     * 组织查询
     *
     * @param query
     * @param accessToken
     * @return
     */
    public ApiResponse<PagedListEntityDto<OrganizationListDto>> organizationQuery(OrganizationQueryOptionDto query, String accessToken);

    public ApiResponse<List<UserDepartmentViewDto>> ORGSquery(String UserID, String accessToken);

 /*   *//**
     * 查询组织详细信息
     * @param id
     * @param accessToken
     * @return
     *//*
    public ApiResponse<CompanyViewDto> organizationDetail(String id,String accessToken);*/

    /**
     * 所属机构查询
     */
    public ApiResponse<List<OrganizationListDto>> organizationQueryChild(String parentId, String accessToken);

    /**
     * 所在区域查询
     */
    public ApiResponse<List<AreaListDto>> AreaQuery(String parentId, String accessToken);

    public ApiResponse<AreaViewDto> areaDetails(String code, String accessToken);


    /**
     * 新闻查询 分页
     *
     * @param query
     * @param accessToken
     * @return
     */
    public ApiResponse<PagedListEntityDto<ContentListDto>> contentQuery(ContentQueryOptionDto query, String accessToken);

    /**
     * 志愿者服务站点
     */
    public ApiResponse<PagedListEntityDto<VolunteerBaseListDto>> volunteerBaseQuery(VolunteerBaseQueryOptionDto query, String accessToken);

    /**
     * 签到签退明细
     */
    public ApiResponse<List<String>> signRecordCreate(List<SignInOutDto> creates, String accessToken);

    //证书上传
    public ApiResponse<List<AttachmentsReturnDto>> update_major_attachment(List<AttachmentParaDto> data, String accessToken);

    //志愿者等级查询
    public ApiResponse<String> query_star_level(Double data, String accessToken);

    /**
     * 查询活动时间
     */
    public ApiResponse<PagedListEntityDto<ActivityTimeListDto>> activityTiemQuery(ActivityTimeQueryOptionDto query, String accessToken);

    public ApiResponse<PagedListEntityDto<MobileVersionListDto>> mobileVersionQuery(MobileVersionQueryOptionDto query, String accessToken);

    public ApiResponse<MobileVersionViewDto> mobileVersionDetails(String id, String accessToken);

    public ApiResponse<AttachmentsViewDto> attatchmentDetails(String id, String accessToken);


    /**
     * 忘记密码
     */
    public ApiResponse<String> existsMobile(String mobile, Integer userType, String accessToken);

    public ApiResponse<String> getByMobile(String mobile, Integer userType, String accessToken);

    public ApiResponse<String> existsIDNumber(String idnumber, String mobile, Integer userType, String accessToken);

    public ApiResponse<String> getByIDNumber(String idnumber, String mobile, Integer userType, String accessToken);

    public ApiResponse<String> existsEmail(String email, Integer userType, String accessToken);

    /**
     * 个人报名活动删除
     */
    public ApiResponse<String> activityRecruitRemove(List<String> activityRecruitId,String accessToken);
}
