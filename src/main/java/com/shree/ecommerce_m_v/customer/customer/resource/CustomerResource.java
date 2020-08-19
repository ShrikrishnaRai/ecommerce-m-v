package com.shree.ecommerce_m_v.customer.customer.resource;

import com.shree.ecommerce_m_v.customer.customer.model.DTO.CustomerDTO;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.customer.customer.service.CustomerService;
import com.shree.ecommerce_m_v.utils.email.otp.OtpConfig;
import com.shree.ecommerce_m_v.utils.email.otp.response.ResponseVal;
import com.shree.ecommerce_m_v.utils.email.templates.RegistrationConfirmationTemplate;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/customer")
public class CustomerResource extends MultipartS3Controller {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OtpConfig otpConfig;

    @Autowired
    private RegistrationConfirmationTemplate registrationTemplate;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CustomerDTO>>> getAllCustomers(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @RequestParam(defaultValue = "0", required = false, value = "page") final int page,
            PagedResourcesAssembler<CustomerDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(customerService.getAllCustomers(page)));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @PathVariable("customerId") final Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerById(customerId));

    }

    @GetMapping("/search/{name}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\")")
    public ResponseEntity<CustomerDTO> getCustomerByName(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @PathVariable("name") final String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerByName(name));
    }

//    @PostMapping
//    public ResponseEntity<String> saveCustomer(
//            @RequestHeader(value = "Authorization", required = false) final String Authorization,
//            @RequestPart(value = "photo", required = false) final MultipartFile multipartFile,
//            @RequestPart("customerDTO") String customerDTO) throws Exception {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        CustomerDTO customerDTO1 = objectMapper.readValue(customerDTO, CustomerDTO.class);
//        if (multipartFile != null) {
//            customerDTO1.setImage(uploadImage(multipartFile, S3ImagePath.customerPath + customerDTO1.getCustomerName() + "/").getImageUrl());
//        }
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(customerService.saveCustomer(customerDTO1));
//    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(value = "Authorization", required = false) final String Authorization,
                                                 @PathVariable final long customerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(customerService.deleteCustomerWithId(customerId));
    }

    @PutMapping("/{customerId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<CustomerDTO> updateCustomers(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @PathVariable("customerId") final Long customerId,
            @RequestBody final CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.updateCustomer(customerId, customerDTO));
    }


    @PostMapping("/signUp")
    public ResponseEntity<ResponseVal> generateOtpUponRegistration(@RequestBody CustomerDTO customerDTO,
                                                                   @ApiIgnore HttpSession httpSession) throws Exception {

        CustomerEntity customerEntity = customerService.getCustomerEntity(customerDTO);
        httpSession.setAttribute("customer", customerEntity);
        String dbEmail = customerService.findByEmail(customerEntity.getEmail()
                , customerEntity.getUsername(), customerEntity.getContactNo());

        if (dbEmail == null) {
            int otp = otpConfig.generateOTP(customerEntity.getEmail());
            registrationTemplate.emailService(customerEntity.getEmail(), otp, customerEntity.getCustomerName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseVal.builder()
                            .code(200)
                            .message("Please enter the verification code you have received on your provided email")
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseVal.builder()
                            .code(200)
                            .message("Email already exists")
                            .build());
        }
    }

    @PostMapping("/emailVerification")
    public ResponseEntity<ResponseVal> otpVerification(@RequestParam int otp,
                                                       @ApiIgnore HttpSession session) throws Exception {
        CustomerEntity customerEntity = otpConfig.customerObject(session);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(customerService.otpVerification(otp, customerEntity));

    }

    @GetMapping("/search/email/{email}")
    public CustomerEntity findCustomerByEmail(
            @RequestHeader(value = "Authorization", required = false) final String Authorization,
            @PathVariable("email") String email) {
        return customerService.findCustomerByEmail(email);
    }
}
