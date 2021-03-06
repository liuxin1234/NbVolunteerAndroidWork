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
import com.example.model.appraiseCompany.VolunteerAppraiseCompanyDto;
import com.example.model.area.AreaListDto;
import com.example.model.area.AreaQueryOptionDto;
import com.example.model.area.AreaViewDto;
import com.example.model.company.CompanyListDto;
import com.example.model.company.CompanyQueryOptionDto;
import com.example.model.company.CompanyViewDto;
import com.example.model.content.ContentListDto;
import com.example.model.content.ContentQueryOptionDto;
import com.example.model.content.ContentViewDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;
import com.example.model.dictionary.DictionaryTypeListDto;
import com.example.model.dictionary.DictionaryTypeQueryOptionDto;
import com.example.model.dictionary.DictionaryViewDto;
import com.example.model.durationRecord.DurationRecordListDto;
import com.example.model.durationRecord.DurationRecordQueryOptionDto;
import com.example.model.jobActivity.JobActivityViewDto;
import com.example.model.mobileVersion.MobileVersionListDto;
import com.example.model.mobileVersion.MobileVersionQueryOptionDto;
import com.example.model.mobileVersion.MobileVersionViewDto;
import com.example.model.organization.OrganizationListDto;
import com.example.model.organization.OrganizationQueryOptionDto;
import com.example.model.share.ShareDto;
import com.example.model.share.ShareListDto;
import com.example.model.share.ShareQueryOptionDto;
import com.example.model.shareMessage.SharemessageDto;
import com.example.model.shareMessage.SharemessageListDto;
import com.example.model.shareMessage.SharemessageQueryOptionDto;
import com.example.model.sharePraise.SharepraiseDto;
import com.example.model.sharePraise.SharepraiseListDto;
import com.example.model.sharePraise.SharepraiseQueryOptionDto;
import com.example.model.signRecord.SignInOutDto;
import com.example.model.signRecord.SignRecordSimple;
import com.example.model.signResult.SignResultListDto;
import com.example.model.signResult.SignResultQueryOptionDto;
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
import java.util.Map;

/**
 * 项目名称：WeVolunteer
 * 类描述：接口类
 * 创建人：renhao
 * 创建时间：2016/8/2 13:42
 * 修改备注：
 */
public interface Api {

//    public static final String USERID_LOGIN = "Nbcei.Framework.Api.Impl/v1/user/query";
    public static final String USERID_LOGIN = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/query";

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
    public static final String AREA_KEY_WORD_QUERY = "Nbcei.Framework.Api.Impl/v1/area/query";

    //新闻
    public static final String CONTENT_QUERY = "Nbcei.Plugin.CMS.Api.Impl/v1/Content/query";
    //查询版权，主办方等等详细信息
    public static final String CONTENT_DETAILS = "Nbcei.Plugin.CMS.Api.Impl/v1/Content/details";



    //发送验证码
    public static final String SEND_PHONE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/smsauth/send/register";
    public static final String GET_VERYFY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/smsauth/valid/register";

    //密码修改
//    public static final String GET_OLD_PSWD = "Nbcei.Framework.Api.Impl/v1/user/query/password";
//    public static final String SET_NEW_PSWD = "Nbcei.Framework.Api.Impl/v1/user/update/setpassword";
    public static final String GET_OLD_PSWD = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/query/password/";
    public static final String SET_NEW_PSWD = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/update/setpassword/";


    //头像操作
    public static final String UPDATE_PORTRAIT = "Nbcei.Framework.Api.Impl/v1/user/update/photo";
    public static final String GET_PORTRAIT = "Nbcei.Framework.Api.Impl/v1/user/query/photo";
    public static final String CREATE_PORTRAIT = "Nbcei.Framework.Api.Impl/v1/user/create/photo";

    //上传头像 新的接口
    public static final String UPDATE_PORTRAIT_IMAGE_NEW = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/share/UploadPhoto";


    //志愿者服务站点
    public static final String VOLUNTEER_BASE_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteerbase/query";

    //签到签退明细（已弃用）
    public static final String SIGNRECORD_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/signrecord/create";

    //二维码,签到码 签到签退: 测试地址/api/Nbcei.Plugin.NbVolunteer.Api.Impl/v1/signrecord/createboth
    public static final String SIGNRECORD_CREATE_BOTH ="Nbcei.Plugin.NbVolunteer.Api.Impl/v1/signrecord/createboth";

    //查询签到签退的状态
    public static final String SIGNRECORD_EXISTS = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/signrecord/ExistsSignRecord";


    //专业证书批量上传
//    public static final String UPDATE_MAJOR_ATTACHMENT = "Nbcei.Plugin.Attachment.Api.Impl/v1/attachments/savefile";
    //新的专业证书批量上传 接口地址
    public static final String UPDATE_MAJOR_ATTACHMENT = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/share/SaveFile";

    //查询活动时间
    public static final String ACTIVITY_TIME_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activitytime/query";

    //查询志愿者等级
    public static final String VOLUNTEER_STAR_LEVEL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/GetScoreLevel/";
//    public static final String VOLUNTEER_DEPARTMENT = "Nbcei.Framework.Api.Impl/v1/userdepartment/query/user";
    //志愿者所属部门查询
    public static final String VOLUNTEER_DEPARTMENT = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/query/user";



    //app版本查询
    public static final String MOBILE_VERSION_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/mobileversion/query";
    public static final String MOBILE_VERSION_DETAILS = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/mobileversion/details";

    //获取文件地址
    public static final String ATTACTHMENTS_DETAILS = "Nbcei.Plugin.Attachment.Api.Impl/v1/attachments/details";
//    public static final String ATTACTHMENTS_DETAILS = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/GetAttachmentsdetails";

    //忘记密码
    public static final String EXISTS_MOBILE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/existsmobile";
    public static final String GET_BY_MOBILE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/getbymobile";
    public static final String EXISTS_IDNUMBER = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/existsidnumberandmobile";
    public static final String GET_BY_IDNUMBER = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/getbyidnumberandmobile";
    public static final String EXISTS_EMAIL = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/existsemail";

    //忘记密码 新接口
    public static final String GET_BY_IDNUMBER_AND_MOBILE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/company/getbyidnumberandmobile";
    public static final String POST_RESET_PASSWORD_AND_MOBILE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteer/update/ResetPasswordAndMobile";


    //报名活动删除
    public static final String ACTIVITY_RECRUIT_REMOVE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/activityrecruit/remove";

    //我的岗位/活动报名的签离签到时间
    //志愿者服务时长的接口
    public static final String SIGN_RESULT_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/signresult/query";
    //历史时长补录 DurationRecord
    public static final String DURATIONRECORD_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/durationrecord/query";

    //待评价 Volunteerappraisecompany
    public static final String VOLUNTEER_APPRAISE_COMPANY_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/volunteerappraisecompany/create";

    //秀一秀 朋友圈分享
    public static final String SHARE_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/share/create";
    public static final String SHARE_SAVE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/share/SaveShare";
    public static final String SHARE_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/share/query2";
    //秀一秀 评论
    public static final String SHARE_MESSAGE_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/sharemessage/create";
    public static final String SHARE_MESSAGE_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/sharemessage/query";
    //秀一秀 点赞
    public static final String SHARE_PRAISE_CREATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/sharepraise/create";
    public static final String SHARE_PRAISE_UPDATE = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/sharepraise/update";
    public static final String SHARE_PRAISE_QUERY = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/sharepraise/query";
    //点赞新增or更新
    public static final String SHARE_PRAISE_OPERATION = "Nbcei.Plugin.NbVolunteer.Api.Impl/v1/sharepraise/operation";


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

    //创建头像
    public ApiResponse<String> create_portrait(UserPhotoDto photoDto,String accessToken);

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

    public ApiResponse<PagedListEntityDto<AreaListDto>> postAreaKeyWordQuery(AreaQueryOptionDto query,String accessToken);

    /**
     * 新闻查询 分页
     *
     * @param query
     * @param accessToken
     * @return
     */
    public ApiResponse<PagedListEntityDto<ContentListDto>> contentQuery(ContentQueryOptionDto query, String accessToken);

    public ApiResponse<ContentViewDto> getContentDetails(String id,String accessToken);

    /**
     * 志愿者服务站点
     */
    public ApiResponse<PagedListEntityDto<VolunteerBaseListDto>> volunteerBaseQuery(VolunteerBaseQueryOptionDto query, String accessToken);

    /**
     * 签到签退明细
     */
    public ApiResponse<List<String>> signRecordCreate(List<SignInOutDto> creates, String accessToken);

    public ApiResponse<List<String>> signRecordCreateBoth(List<SignInOutDto> creates,String accessToken);

    /**
     * 查询签到签退状态
     */
    public ApiResponse<String> post_ExistsSignRecord(List<SignRecordSimple> data,String accessToken);


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

    public ApiResponse<String> getByIdNumberAndMobile(String idNumber,String mobile, Integer userType, String accessToken);

    public ApiResponse<String> postResetPasswordAndMobile(Integer userType,String userId,String mobile,String password, String accessToken);

    /**
     * 个人报名活动删除
     */
    public ApiResponse<String> activityRecruitRemove(List<String> activityRecruitId,String accessToken);

    /**
     * 我的岗位/活动报名的签离签到时间
     */
    public ApiResponse<PagedListEntityDto<SignResultListDto>> postSignResultQuery(SignResultQueryOptionDto signResultQueryOptionDto, String accessToken);

    public ApiResponse<PagedListEntityDto<DurationRecordListDto>> postDurationRecordQuery(DurationRecordQueryOptionDto durationRecordQueryOptionDto, String accessToken);

    /**
     * 待评价
     */
    public ApiResponse<List<String>> postAppraiseCompanyCreate(List<VolunteerAppraiseCompanyDto> appraiseCompanyDtos,String accessToken);

    /**
     * 秀一秀 朋友圈分享
     */
    public ApiResponse<List<String>> postShareCreate(List<ShareDto> shareDtoList,String accessToken);
    public ApiResponse<String> postShareSave(Map<String,String> params, List<String> imageUrls, String accessToken);
    public ApiResponse<PagedListEntityDto<ShareListDto>> postShareQuery(ShareQueryOptionDto shareQueryOptionDto,String accessToken);
    /**
     * 秀一秀 评论
     */
    public ApiResponse<List<String>> postShareMessageCreate(List<SharemessageDto> sharemessageDtoList,String accessToken);
    public ApiResponse<PagedListEntityDto<SharemessageListDto>> postShareMessageQuery(SharemessageQueryOptionDto sharemessageQueryOptionDto,String accessToken);
    /**
     * 秀一秀 点赞
     */
    public ApiResponse<List<String>> postSharePraiseCreate(List<SharepraiseDto> sharepraiseDtoList,String accessToken);
    public ApiResponse<String> postSharePraiseUpdate(List<SharepraiseDto> sharepraiseDtoList,String accessToken);
    public ApiResponse<PagedListEntityDto<SharepraiseListDto>> postSharePraiseQuery(SharepraiseQueryOptionDto sharepraiseQueryOptionDto, String accessToken);
    public ApiResponse<String> postSharePraiseOperation(List<SharepraiseDto> sharepraiseDtoList,String accessToken);
    /**
     * 获取，上传头像  新的接口方法
     */
    public ApiResponse<String> postShareUploadPhoto(Map<String,String> params,List<String> imageUrls,String accessToken);
}
