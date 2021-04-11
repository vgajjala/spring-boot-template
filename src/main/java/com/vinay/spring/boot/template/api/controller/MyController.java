
package com.visa.cloud.storage.api.impl;

import com.visa.cloud.commons.commonutils.model.ResponseObject;
import com.visa.cloud.core.workflow.exceptions.RequestProvisioningException;
import com.visa.cloud.gateway.model.exception.DataValidationException;
import com.visa.cloud.gateway.security.model.UserPrincipal;
import com.visa.cloud.gateway.security.service.CloudServicesSecurityContext;
import com.visa.cloud.storage.api.NasApplicationRequestCommandApi;
import com.visa.cloud.storage.clients.CloudModelFactory;
import com.visa.cloud.storage.config.mapstruct.NasRequestMapper;
import com.visa.cloud.storage.config.mapstruct.ProvisionNasRequestMapper;
import com.visa.cloud.storage.dto.nas.NasRequestDto;
import com.visa.cloud.storage.dto.nas.ProvisionNasRequestDto;
import com.visa.cloud.storage.exceptions.NasRequestException;
import com.visa.cloud.storage.model.nas.NasRequest;
import com.visa.cloud.storage.model.nas.ProvisionNasRequest;
import com.visa.cloud.storage.service.INasRequestService;
import com.visa.cloud.storage.validators.NasRequestValidator;
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
class NasApplicationRequestCommandApiController implements NasApplicationRequestCommandApi {

    
    
    private IMyService myService;
    private MyModelMapper mapper;
    private MyModelValidator myRequestValidator;

    @InitBinder
    public void validationBinder(WebDataBinder binder) {
        binder.setValidator(myRequestValidator);
    }


    @Override
    public ResponseEntity<ResponseObject<ProvisionNasRequestDto>> createNewNasApplicationVolume(@Valid @RequestBody NasRequestDto body, BindingResult bindingResult) throws RequestProvisioningException, NasRequestException {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult);
        }

        NasRequest nasRequest = mapper.toNasRequest(body);

        UserPrincipal userPrincipal = cloudServicesSecurityContext.getUserPrincipal();
        String userName = apiHelper.getApiRequesterName(userPrincipal);
        nasRequest.setRequestedBy(userName);
        nasRequest.setSubmittedBy(userPrincipal.getUserName());

        Optional<ProvisionNasRequest> savedProvisionNasRequestOptional = nasRequestService.createNewVolumeNasApplicationRequest(nasRequest);
        ProvisionNasRequestDto nasRequestDto = null;
        if (savedProvisionNasRequestOptional.isPresent()) {
            nasRequestDto = provisionNasRequestMapper.toProvisionNasRequestDto(savedProvisionNasRequestOptional.get());
        }

        return new ResponseEntity<>(cloudModelFactory.createResponseObject(nasRequestDto, "NAS Application Request for New Volume;  Accepted"), HttpStatus.ACCEPTED);
    }




}
