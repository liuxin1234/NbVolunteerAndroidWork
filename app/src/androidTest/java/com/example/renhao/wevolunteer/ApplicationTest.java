package com.example.renhao.wevolunteer;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.dictionary.DictionaryQueryOptionDto;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        DictionaryQueryOptionDto queryOptionDto = new DictionaryQueryOptionDto();
        queryOptionDto.setDictionaryTypeId("ActivityType");
        AppActionImpl.getInstance(getContext()).dictionaryQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<DictionaryListDto> data) {

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }
}