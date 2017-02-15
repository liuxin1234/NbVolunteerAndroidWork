package com.example.renhao.wevolunteer.event;

import com.example.model.company.CompanyViewDto;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 14:31
 * 修改备注：
 */
public class OrganizationDetailEvent {
    private CompanyViewDto mCompanyViewDto;

    public OrganizationDetailEvent() {
    }

    public OrganizationDetailEvent(CompanyViewDto companyViewDto) {
        mCompanyViewDto = companyViewDto;
    }

    public CompanyViewDto getCompanyViewDto() {
        return mCompanyViewDto;
    }

    public void setCompanyViewDto(CompanyViewDto companyViewDto) {
        mCompanyViewDto = companyViewDto;
    }
}
