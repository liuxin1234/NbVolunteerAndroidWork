package com.example.api;

import android.text.TextUtils;

import com.example.api.net.HttpEngine;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/2 14:44
 * 修改备注：
 */
public class ApiImpl implements Api {
    private static final String TAG = "ApiImpl";

    public ApiImpl() {
    }

    @Override
    public AccessTokenBO getAccessToken(String username, String password) {
        try {
            return HttpEngine.getInstance().getAccessToken(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AccessTokenBO getAccessToken(String refreshToken) {
        try {
            return HttpEngine.getInstance().getAccessToken(refreshToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ApiResponse<UserListDto> userIdLogin(String userId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(userId);
        Type typeOft = new TypeToken<ApiResponse<UserListDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, USERID_LOGIN, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }


    @Override
    public ApiResponse<List<String>> userCreate(List<UserDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);

        Type typeOdT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();

        try {
            return HttpEngine.getInstance().postApiHandler(params, USER_CREATE, typeOdT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<UserViewDto> userLogin(String userName, String password, String assessToken) {

        /*Map<String,String> params=new HashMap<>();
        params.put("password",password);
        params.put("userName",userName);*/

        List<String> params = new ArrayList<>();
        params.add(userName);
        params.add(password);

        Type typeOfT = new TypeToken<ApiResponse<UserViewDto>>() {
        }.getType();

        try {
            return HttpEngine.getInstance().getApiHandler(params, USER_LOGIN, typeOfT, assessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> activityCreate(List<ActivityCreateBO> creates, String assessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_CREATE, typeOfT, assessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<ActivityListDto>> activityQuery(ActivityQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<ActivityListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<ActivityViewDto> activityDetail(String activityId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(activityId);
        Type typeOfT = new TypeToken<ApiResponse<ActivityViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, ACTIVITY_DETAIL, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> activityRecruitCreate(List<ActivityRecruitDto> create, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(create);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_RECRUIT_CREAT, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<JobActivityViewDto> jobActivityDetail(String jobActivityId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(jobActivityId);
        Type typeOfT = new TypeToken<ApiResponse<JobActivityViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, JOBACTIVITY_DETAIL, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> volunteerCreate(List<VolunteerCreateDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, VOLUNTEER_CREATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<VolunteerDto> volunteerQuery(VolunteerQueryDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<VolunteerDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, VOLUNTEER_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }


    @Override
    public ApiResponse<PagedListEntityDto<CompanyListDto>> companyQuery(CompanyQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<CompanyListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, COMPANY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<CompanyViewDto> companyDetail(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<CompanyViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, COMPANY_DETAIL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> companyCreat(List<CompanyListDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, COMPANY_CREAT, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> companyUpdate(List<CompanyListDto> update, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(update);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, COMPANY_UPDATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<DictionaryListDto>> dictionaryQuery(DictionaryQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<DictionaryListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, DICTIONARY_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<DictionaryTypeListDto>> dictionaryTypeQuery(DictionaryTypeQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<DictionaryTypeListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, DICTIONARYTYPE_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<DictionaryListDto>> dictionaryQueryDefault(String typeCode, String parentId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(typeCode);
        if (TextUtils.isEmpty(parentId)) {
            params.add("00000000-0000-0000-0000-000000000000");
        } else {
            params.add(parentId);
        }
        Type typeOft = new TypeToken<ApiResponse<List<DictionaryListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, DICTIONARYTYPE_QUERY_DEFAULT, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<DictionaryViewDto> dictionaryQueryDetailDefault(String typeCode, String code, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(typeCode);
        params.add(code);
        Type typeOft = new TypeToken<ApiResponse<DictionaryViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, DICTIONARYTYPE_QUERY_DETAIL_DEFAULT, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<OrganizationListDto>> organizationQuery(OrganizationQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<OrganizationListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ORGANIZATION_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<UserDepartmentViewDto>> ORGSquery(String UserID, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(UserID);
        Type typeOft = new TypeToken<ApiResponse<List<UserDepartmentViewDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, VOLUNTEER_DEPARTMENT, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<OrganizationListDto>> organizationQueryChild(String parentId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(parentId);
        Type typeOft = new TypeToken<ApiResponse<List<OrganizationListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, ORGANIZATION_QUERY_CHILD, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<AreaListDto>> AreaQuery(String parentId, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(parentId);
        Type typeOft = new TypeToken<ApiResponse<List<AreaListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, AREA_QUERY, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<AreaViewDto> areaDetails(String code, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(code);
        Type typeOft = new TypeToken<ApiResponse<AreaViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, AREA_DETAILS, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<ContentListDto>> contentQuery(ContentQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<ContentListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, CONTENT_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<ActivityRecruitListDto>> activityRecuitQuery(ActivityRecruitQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<ActivityRecruitListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_RECRUIT_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> activityAttentionCreate(List<ActivityAttentionDto> create, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(create);
        Type typeOfT = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_ATTENTION_CREATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> activityAttentionDelete(List<String> id, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(id);
        Type typeOfT = new TypeToken<ApiResponse<Object>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_ATTENTION_DELETE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<ActivityAttentionListDto>> activityAttentionQuery(ActivityAttentionQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOfT = new TypeToken<ApiResponse<PagedListEntityDto<ActivityAttentionListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_ATTENTION_QUERY, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<VolunteerTopViewDto>> activity_membership(String query, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(query);
        Type typeOft = new TypeToken<ApiResponse<List<VolunteerTopViewDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, ACTIVITY_GETMEMBERSHIP, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> send_phone(String phone, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(phone);
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, SEND_PHONE, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<Boolean> get_verify(String phone, String SMScode, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(phone);
        params.add(SMScode);
        Type typeOft = new TypeToken<ApiResponse<Boolean>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, GET_VERYFY, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<VolunteerViewDto> get_volunteerDetail(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<VolunteerViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, VOLUNTEER_DETAIL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> get_oldPSWD(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, GET_OLD_PSWD, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> set_newPSWD(String id, String newPassword, String accessToken) {
        Gson gson = new Gson();
        //传空值
        List<String> update = new ArrayList<>();
        update.add(id);
        update.add(newPassword);
        Type typeOfT = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().PSWDpostApiHandler(update, SET_NEW_PSWD, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> update_portrait(UserPhotoDto portrait, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(portrait);
        Type typeOfT = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, UPDATE_PORTRAIT, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> get_portrait(String UserID, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(UserID);
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, GET_PORTRAIT, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");

    }

    @Override
    public ApiResponse<String> volunteerUpdate(List<VolunteerViewDto> update, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(update);
        Type typeOfT = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, VOLUNTEER_UPDATE, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<VolunteerBaseListDto>> volunteerBaseQuery(VolunteerBaseQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOft = new TypeToken<ApiResponse<PagedListEntityDto<VolunteerBaseListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, VOLUNTEER_BASE_QUERY, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<String>> signRecordCreate(List<SignInOutDto> creates, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(creates);
        Type typeOft = new TypeToken<ApiResponse<List<String>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, SIGNRECORD_CREATE, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<List<AttachmentsReturnDto>> update_major_attachment(List<AttachmentParaDto> data, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(data);
        Type typeOfT = new TypeToken<ApiResponse<List<AttachmentsReturnDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, UPDATE_MAJOR_ATTACHMENT, typeOfT, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");

    }

    @Override
    public ApiResponse<String> query_star_level(Double data, String accessToken) {
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(data, VOLUNTEER_STAR_LEVEL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<ActivityTimeListDto>> activityTiemQuery(ActivityTimeQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOft = new TypeToken<ApiResponse<PagedListEntityDto<ActivityTimeListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, ACTIVITY_TIME_QUERY, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<PagedListEntityDto<MobileVersionListDto>> mobileVersionQuery(MobileVersionQueryOptionDto query, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(query);
        Type typeOft = new TypeToken<ApiResponse<PagedListEntityDto<MobileVersionListDto>>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params, MOBILE_VERSION_QUERY, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<MobileVersionViewDto> mobileVersionDetails(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<MobileVersionViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, MOBILE_VERSION_DETAILS, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<AttachmentsViewDto> attatchmentDetails(String id, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(id);
        Type typeOft = new TypeToken<ApiResponse<AttachmentsViewDto>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, ATTACTHMENTS_DETAILS, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> existsMobile(String mobile, Integer userType, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(mobile);
        params.add(String.valueOf(userType));
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, EXISTS_MOBILE, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> getByMobile(String mobile, Integer userType, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(mobile);
        params.add(String.valueOf(userType));
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, GET_BY_MOBILE, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> existsIDNumber(String idnumber, String mobile, Integer userType, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(idnumber);
        params.add(mobile);
        params.add(String.valueOf(userType));
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, EXISTS_IDNUMBER, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> getByIDNumber(String idnumber, String mobile, Integer userType, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(idnumber);
        params.add(mobile);
        params.add(String.valueOf(userType));
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, GET_BY_IDNUMBER, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> existsEmail(String email, Integer userType, String accessToken) {
        List<String> params = new ArrayList<>();
        params.add(email);
        params.add(String.valueOf(userType));
        Type typeOft = new TypeToken<ApiResponse<String>>() {
        }.getType();
        try {
            return HttpEngine.getInstance().getApiHandler(params, EXISTS_EMAIL, typeOft, accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(false, "网络连接异常");
    }

    @Override
    public ApiResponse<String> activityRecruitRemove(List<String> activityRecruitId, String accessToken) {
        Gson gson = new Gson();
        String params = gson.toJson(activityRecruitId);
        Type typeOft = new TypeToken<ApiResponse<String>>(){}.getType();
        try {
            return HttpEngine.getInstance().postApiHandler(params,ACTIVITY_RECRUIT_REMOVE,typeOft,accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ApiResponse<>(false, "网络连接异常");
    }
}
