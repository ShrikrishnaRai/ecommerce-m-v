package com.shree.ecommerce_m_v.vendor.vendor.resource;

import com.shree.ecommerce_m_v.utils.email.otp.OtpConfig;
import com.shree.ecommerce_m_v.utils.email.otp.response.ResponseVal;
import com.shree.ecommerce_m_v.utils.email.templates.RegistrationConfirmationTemplate;
import com.shree.ecommerce_m_v.utils.imageUploader.controller.MultipartS3Controller;
import com.shree.ecommerce_m_v.utils.values.AuthoritiesConstants;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import com.shree.ecommerce_m_v.vendor.vendor.service.VendorService;
import com.shree.ecommerce_m_v.vendor.vendor.service.dto.VendorDTO;
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
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/vendor")
public class VendorResource extends MultipartS3Controller {
    @Autowired
    private VendorService vendorService;

    @Autowired
    private OtpConfig otpConfig;

    @Autowired
    private RegistrationConfirmationTemplate registrationConfirmationTemplate;

    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.SUPER_ADMIN + "\")")
    public ResponseEntity<PagedModel<EntityModel<VendorDTO>>> getVendorList(
            @RequestHeader(value = "Authorization", required = false) String Authorization,
            @RequestParam(defaultValue = "0", required = false, value = "page") int page,
            @RequestParam(defaultValue = "vendorName", required = false, value = "sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC", required = false, value = "orderBy") String orderBy,
            PagedResourcesAssembler<VendorDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(vendorService.getVendorList(page, sortBy, orderBy)));
    }

//    @PostMapping
//    public ResponseEntity<String> saveVendor(@RequestHeader(value = "Authorization", required = false) final String Authorization,
//                                             @RequestPart(value = "photo", required = false) final MultipartFile multipartFile,
//                                             @RequestPart("vendorDTO") final String vendorDTO) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        VendorDTO vendorDTO1 = objectMapper.readValue(vendorDTO, VendorDTO.class);
//        if (multipartFile != null) {
//            vendorDTO1.setImage(uploadImage(multipartFile, S3ImagePath.vendorPath + vendorDTO1.getVendorName() + "/") + vendorDTO1.getVendorName() + "jpg");
//        }
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(vendorService.saveVendor(vendorDTO1));
//    }

    @RequestMapping(value = "/{vendorId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<VendorDTO> getVendorById(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                   @PathVariable("vendorId") @Min(1) final long vendorId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorService.getVendorWithId(vendorId));
    }

    @RequestMapping(value = "/search/{vendorName}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<PagedModel<EntityModel<VendorDTO>>> getVendorWithName(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                                                @PathVariable("vendorName") final String vendorName,
                                                                                PagedResourcesAssembler<VendorDTO> assembler) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(assembler.toModel(vendorService.getVendorWithName(vendorName)));
    }

    @DeleteMapping(value = "/{vendorId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + AuthoritiesConstants.SUPER_ADMIN + "\")")
    public ResponseEntity<String> deleteVendorWithID(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                     @PathVariable("vendorId") @Min(1) final long vendorId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(vendorService.deleteVendorWithId(vendorId));
    }

    @PutMapping("/{vendorId}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<VendorDTO> updateVendor(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                  @RequestBody final VendorDTO vendorDTO, @PathVariable final long vendorId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorService.updateVendor(vendorId, vendorDTO));
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseVal> generateOtpUponRegistration(@RequestBody VendorDTO vendorDTO,
                                                                   @ApiIgnore HttpSession httpSession) throws Exception {

        VendorEntity vendorEntity = vendorService.getVendorEntity(vendorDTO);
        httpSession.setAttribute("vendor", vendorEntity);
        String dbEmail = vendorService.findByEmail(vendorEntity.getContact().getEmail()
                , vendorEntity.getContact().getContactNo(), vendorEntity.getUsername());

        if (dbEmail == null) {
            int otp = otpConfig.generateOTP(vendorEntity.getContact().getEmail());
            registrationConfirmationTemplate.emailService(vendorEntity.getContact().getEmail(), otp, vendorEntity.getVendorName());
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
        VendorEntity vendorEntity = otpConfig.vendorObject(session);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(vendorService.otpVerification(otp, vendorEntity));

    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<VendorDTO> getVendorByEmail(@RequestHeader(value = "Authorization", required = false) String Authorization,
                                                      @PathVariable("email") String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vendorService.findVendorEntityByEmail(email));
    }
}
