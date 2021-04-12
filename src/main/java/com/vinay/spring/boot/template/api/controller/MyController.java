
package com.vinay.spring.boot.template.api.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MyController implements MyRequestCommandApi {
    
    private IMyService myService;
    private MyModelMapper mapper;
    private MyModelValidator myRequestValidator;

    // Note move this to the Controller advice
    @InitBinder
    public void validationBinder(WebDataBinder binder) {
        binder.setValidator(myRequestValidator);
    }


    @Override
    public ResponseEntity<MyPojoDto> createNewNasApplicationVolume(@Valid @RequestBody MyPojoDto body, BindingResult bindingResult) throws MyRequestException {
        // Note move this to the Controller advice
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult);
        }

        MyModel myModel = mapper.toMyModel(body);

        
        Optional<MyModel> savedModel = myService.createModel(myModel);
        MyPojoDto myPojoDtoSaved;
        if (savedModel.isPresent()) {
            myPojoDtoSaved = mapper.toMyPojoDto(savedModel.get());
        }

        return new ResponseEntity<>(myPojoDtoSaved, HttpStatus.OK);
    }

}
