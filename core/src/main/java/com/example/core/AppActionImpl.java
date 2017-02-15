package com.example.core;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.api.Api;
import com.example.api.ApiImpl;
import com.example.api.ApiResponse;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
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
 * 创建时间：2016/8/2 16:47
 * 修改备注：
 */
public class AppActionImpl implements AppAction {
    private static final String TAG = "AppActionImpl";

    private Context context;
    private Api api;

    private volatile static AppActionImpl singleton = null;    //注意此处加上了volatile关键字

    public static AppActionImpl getInstance(Context context) {
        if (singleton == null) {
            synchronized (AppActionImpl.class) {
                if (singleton == null) {
                    singleton = new AppActionImpl(context);
                    return singleton;    //有人提议在此处进行一次返回
                }
                return singleton;    //也有人提议在此处进行一次返回
            }
        }
        return singleton;
    }

    public AppActionImpl(Context context) {
        this.context = context;
        this.api = new ApiImpl();
    }

    @Override
    public void getAccessToken(final String username, final String password, final AccessTokenListener tokenListener) {
        new AsyncTask<Void, Void, AccessTokenBO>() {
            @Override
            protected AccessTokenBO doInBackground(Void... params) {
                return api.getAccessToken(username, password);
            }

            @Override
            protected void onPostExecute(AccessTokenBO accessTokenBO) {
                if (accessTokenBO != null) {
                    if (!TextUtils.isEmpty(accessTokenBO.getAccess_token())) {
                        saveAccessToken(accessTokenBO);
                    }
                    tokenListener.success(accessTokenBO);
                } else {
                    tokenListener.fail();
                }
            }
        }.execute();
    }

    @Override
    public void userIdLogin(final String userId, final ActionCallbackListener<UserListDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<UserListDto>>() {
            @Override
            protected ApiResponse<UserListDto> doInBackground(Void... params) {
                return api.userIdLogin(userId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<UserListDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }


    @Override
    public void userCreate(final List<UserDto> creates, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {

            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.userCreate(creates, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", "请检查网络后重试");
                }
            }
        }.execute();
    }

    @Override
    public void userLogin(final String userName, final String password, final ActionCallbackListener<UserViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<UserViewDto>>() {

            @Override
            protected ApiResponse<UserViewDto> doInBackground(Void... params) {
                return api.userLogin(userName, password, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<UserViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", "请检查网络后重试");
                }
            }
        }.execute();
    }

    @Override
    public void activityCreate(final List<ActivityCreateBO> activityCreateBOs, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.activityCreate(activityCreateBOs, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", "请检查网络后重试");
                }
            }
        }.execute();
    }

    @Override
    public void activityQuery(final ActivityQueryOptionDto activityQueryOptionDto,
                              final ActionCallbackListener<PagedListEntityDto<ActivityListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<ActivityListDto>>>() {

            @Override
            protected ApiResponse<PagedListEntityDto<ActivityListDto>> doInBackground(Void... params) {
                return api.activityQuery(activityQueryOptionDto, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<ActivityListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityRecruitCreate(final List<ActivityRecruitDto> create, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {

            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.activityRecruitCreate(create, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityAttentionCreate(final List<ActivityAttentionDto> create, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {

            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.activityAttentionCreate(create, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityAttentionDelete(final List<String> id, final ActionCallbackListener<String> listener) {
//判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<String>>() {

            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.activityAttentionDelete(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityAttentionQuery(final ActivityAttentionQueryOptionDto query,
                                       final ActionCallbackListener<PagedListEntityDto<ActivityAttentionListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<ActivityAttentionListDto>>>() {

            @Override
            protected ApiResponse<PagedListEntityDto<ActivityAttentionListDto>> doInBackground(Void... params) {
                return api.activityAttentionQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<ActivityAttentionListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void jobActivityDetail(final String jobActivityId, final ActionCallbackListener<JobActivityViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<JobActivityViewDto>>() {

            @Override
            protected ApiResponse<JobActivityViewDto> doInBackground(Void... params) {
                return api.jobActivityDetail(jobActivityId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<JobActivityViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityDetail(final String activityId, final ActionCallbackListener<ActivityViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<ActivityViewDto>>() {

            @Override
            protected ApiResponse<ActivityViewDto> doInBackground(Void... params) {
                return api.activityDetail(activityId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<ActivityViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者（新增）
    @Override
    public void volunteerCreate(final List<VolunteerCreateDto> volunteerCreateBOs, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.volunteerCreate(volunteerCreateBOs, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者（查询）
    @Override
    public void volunteerQuery(final VolunteerQueryDto volunteerQueryBO, final ActionCallbackListener<VolunteerDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<VolunteerDto>>() {
            @Override
            protected ApiResponse<VolunteerDto> doInBackground(Void... params) {
                return api.volunteerQuery(volunteerQueryBO, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<VolunteerDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void volunteer_departmentQuery(final String UserID, final ActionCallbackListener<List<UserDepartmentViewDto>> listener) {
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<UserDepartmentViewDto>>>() {
            @Override
            protected ApiResponse<List<UserDepartmentViewDto>> doInBackground(Void... params) {
                return api.ORGSquery(UserID, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<UserDepartmentViewDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者（详情信息）
    @Override
    public void get_volunteerDetail(final String id, final ActionCallbackListener<VolunteerViewDto> listener) {
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<VolunteerViewDto>>() {
            @Override
            protected ApiResponse<VolunteerViewDto> doInBackground(Void... params) {
                return api.get_volunteerDetail(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<VolunteerViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }


    //组织查询POST
    @Override
    public void companyQuery(final CompanyQueryOptionDto companyQueryOptionDto, final ActionCallbackListener<PagedListEntityDto<CompanyListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<CompanyListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<CompanyListDto>> doInBackground(Void... params) {
                return api.companyQuery(companyQueryOptionDto, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<CompanyListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void companyDetail(final String id, final ActionCallbackListener<CompanyViewDto> listener) {
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<CompanyViewDto>>() {
            @Override
            protected ApiResponse<CompanyViewDto> doInBackground(Void... params) {
                return api.companyDetail(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<CompanyViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void companyCreat(final List<CompanyListDto> companyListDtos, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.companyCreat(companyListDtos, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void companyUpdate(final List<CompanyListDto> companyListDtos, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.companyUpdate(companyListDtos, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    //志愿者更新
    @Override
    public void volunteerUpdate(final List<VolunteerViewDto> VolunteerViewDto, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.volunteerUpdate(VolunteerViewDto, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();

    }

    @Override
    public void dictionaryQuery(final DictionaryQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<DictionaryListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<DictionaryListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<DictionaryListDto>> doInBackground(Void... params) {
                return api.dictionaryQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<DictionaryListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void dictionaryQueryDefault(final String typeCode, final String parentId, final ActionCallbackListener<List<DictionaryListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<List<DictionaryListDto>>>() {
            @Override
            protected ApiResponse<List<DictionaryListDto>> doInBackground(Void... params) {
                return api.dictionaryQueryDefault(typeCode, parentId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<DictionaryListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void dictionaryQueryDetailDefault(final String typeCode, final String code, final ActionCallbackListener<DictionaryViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<DictionaryViewDto>>() {
            @Override
            protected ApiResponse<DictionaryViewDto> doInBackground(Void... params) {
                return api.dictionaryQueryDetailDefault(typeCode, code, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<DictionaryViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void dictionaryTypeQuery(final DictionaryTypeQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<DictionaryTypeListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<DictionaryTypeListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<DictionaryTypeListDto>> doInBackground(Void... params) {
                return api.dictionaryTypeQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<DictionaryTypeListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void organizationQuery(final OrganizationQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<OrganizationListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");

        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<OrganizationListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<OrganizationListDto>> doInBackground(Void... params) {
                return api.organizationQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<OrganizationListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void organizationQueryChild(final String parentId, final ActionCallbackListener<List<OrganizationListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<OrganizationListDto>>>() {
            @Override
            protected ApiResponse<List<OrganizationListDto>> doInBackground(Void... params) {
                return api.organizationQueryChild(parentId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<OrganizationListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }

            }
        }.execute();
    }

    @Override
    public void AreaQuery(final String parentId, final ActionCallbackListener<List<AreaListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<AreaListDto>>>() {
            @Override
            protected ApiResponse<List<AreaListDto>> doInBackground(Void... params) {
                return api.AreaQuery(parentId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<AreaListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void areaDetails(final String code, final ActionCallbackListener<AreaViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<AreaViewDto>>() {
            @Override
            protected ApiResponse<AreaViewDto> doInBackground(Void... params) {
                return api.areaDetails(code, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<AreaViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void contentQuery(final ContentQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<ContentListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<ContentListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<ContentListDto>> doInBackground(Void... params) {
                return api.contentQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<ContentListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityRecruitQuery(final ActivityRecruitQueryOptionDto query,
                                     final ActionCallbackListener<PagedListEntityDto<ActivityRecruitListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<ActivityRecruitListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<ActivityRecruitListDto>> doInBackground(Void... params) {
                return api.activityRecuitQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<ActivityRecruitListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void getVerification(final String phone,
                                final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.send_phone(phone, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void getverify(final String phone, final String SMScode,
                          final ActionCallbackListener<Boolean> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<Boolean>>() {
            @Override
            protected ApiResponse<Boolean> doInBackground(Void... params) {
                return api.get_verify(phone, SMScode, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<Boolean> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void getold_PSWD(final String id, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.get_oldPSWD(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void setnew_PSWD(final String id, final String newPassword, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.set_newPSWD(id, newPassword, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void update_portrait(final UserPhotoDto photo, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.update_portrait(photo, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void get_portrait(final String UserId, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.get_portrait(UserId, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void query_star_level(final Double timelength, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.query_star_level(timelength, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void volunteerBaseQuery(final VolunteerBaseQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<VolunteerBaseListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<VolunteerBaseListDto>> doInBackground(Void... params) {
                return api.volunteerBaseQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<VolunteerBaseListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void signRecordCreate(final List<SignInOutDto> signInOutDtos, final ActionCallbackListener<List<String>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<String>>>() {
            @Override
            protected ApiResponse<List<String>> doInBackground(Void... params) {
                return api.signRecordCreate(signInOutDtos, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<String>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void update_major_attachment(final List<AttachmentParaDto> data, final ActionCallbackListener<List<AttachmentsReturnDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<List<AttachmentsReturnDto>>>() {
            @Override
            protected ApiResponse<List<AttachmentsReturnDto>> doInBackground(Void... params) {
                return api.update_major_attachment(data, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<AttachmentsReturnDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityTimeQuery(final ActivityTimeQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<ActivityTimeListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<ActivityTimeListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<ActivityTimeListDto>> doInBackground(Void... params) {
                return api.activityTiemQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<ActivityTimeListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void mobileVersionQuery(final MobileVersionQueryOptionDto query, final ActionCallbackListener<PagedListEntityDto<MobileVersionListDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<PagedListEntityDto<MobileVersionListDto>>>() {
            @Override
            protected ApiResponse<PagedListEntityDto<MobileVersionListDto>> doInBackground(Void... params) {
                return api.mobileVersionQuery(query, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<PagedListEntityDto<MobileVersionListDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void mobileVersionDetails(final String id, final ActionCallbackListener<MobileVersionViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<MobileVersionViewDto>>() {
            @Override
            protected ApiResponse<MobileVersionViewDto> doInBackground(Void... params) {
                return api.mobileVersionDetails(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<MobileVersionViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void attatchmentDetails(final String id, final ActionCallbackListener<AttachmentsViewDto> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<AttachmentsViewDto>>() {
            @Override
            protected ApiResponse<AttachmentsViewDto> doInBackground(Void... params) {
                return api.attatchmentDetails(id, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<AttachmentsViewDto> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void existsMobile(final String mobile, final Integer userType, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.existsMobile(mobile, userType, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void existsEmail(final String email, final Integer userType, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.existsEmail(email, userType, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void getByMobile(final String mobile, final Integer userType, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.getByMobile(mobile, userType, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void existsIDNumber(final String idnumber, final String mobile, final Integer userType, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.existsIDNumber(idnumber, mobile, userType, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();

    }

    @Override
    public void getByIDNumber(final String idnumber, final String mobile, final Integer userType, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.getByIDNumber(idnumber, mobile, userType, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();

    }

    @Override
    public void getMembership(String id, String maxSize, final ActionCallbackListener<List<VolunteerTopViewDto>> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        final String addr = "?id=" + id + "&maxSize=" + maxSize;
        new AsyncTask<Void, Void, ApiResponse<List<VolunteerTopViewDto>>>() {
            @Override
            protected ApiResponse<List<VolunteerTopViewDto>> doInBackground(Void... params) {
                return api.activity_membership(addr, accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<VolunteerTopViewDto>> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    @Override
    public void activityRecruitRemove(final List<String> activityRecruitId, final ActionCallbackListener<String> listener) {
        //判断票据是否过期
        final String accessToken = LocalDate.getInstance(context).getLocalDate("access_token", "");
        new AsyncTask<Void, Void, ApiResponse<String>>() {
            @Override
            protected ApiResponse<String> doInBackground(Void... params) {
                return api.activityRecruitRemove(activityRecruitId,accessToken);
            }

            @Override
            protected void onPostExecute(ApiResponse<String> result) {
                if (result == null) {
                    listener.onFailure("", "请检查网络后重试");
                    return;
                }
                if (result.isSuccess()) {
                    listener.onSuccess(result.getData());
                } else {
                    listener.onFailure("", result.getMessage());
                }
            }
        }.execute();
    }

    private void saveAccessToken(AccessTokenBO accessTokenBO) {
        LocalDate.getInstance(context).setLocalDate("access_token", accessTokenBO.getAccess_token());
        LocalDate.getInstance(context).setLocalDate("token_type", accessTokenBO.getToken_type());
        LocalDate.getInstance(context).setLocalDate("expires_in", accessTokenBO.getExpires_in());
        LocalDate.getInstance(context).setLocalDate("userId", accessTokenBO.getUserId());
        LocalDate.getInstance(context).setLocalDate("refresh_token", accessTokenBO.getRefresh_token());
        LocalDate.getInstance(context).setLocalDate("userName", accessTokenBO.getUserName());
        LocalDate.getInstance(context).setLocalDate("scope", accessTokenBO.getScope());
        LocalDate.getInstance(context).setLocalDate("issued", accessTokenBO.getIssued());
        LocalDate.getInstance(context).setLocalDate("expires", accessTokenBO.getExpires());
    }


}
