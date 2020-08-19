package com.shree.ecommerce_m_v.utils.email.otp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.shree.ecommerce_m_v.customer.customer.model.entity.CustomerEntity;
import com.shree.ecommerce_m_v.vendor.vendor.model.entity.VendorEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class OtpConfig {

    private static final Integer EXPIRE_MINS=30;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private LoadingCache<String,Integer> otpCache;

    public OtpConfig() throws Exception{
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    //Used to push the otp number against Key. Rewrite the OTP if it exists
    public int generateOTP(String key){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    //This method is used to return the OTP number against Key->Key values is username
    public int getOtp(String key) throws Exception {
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }

    public CustomerEntity customerObject(HttpSession httpSession)  throws Exception {


        CustomerEntity customerEntity = null;
        if (httpSession != null) {
            customerEntity = (CustomerEntity) httpSession.getAttribute("customer");
            customerEntity.setPassword(encoder.encode(customerEntity.getPassword()));
        }
        return customerEntity;
    }


    public VendorEntity vendorObject(HttpSession httpSession)  throws Exception {

        VendorEntity vendorEntity = null;
        if (httpSession != null) {
            vendorEntity = (VendorEntity) httpSession.getAttribute("vendor");
            vendorEntity.setPassword(encoder.encode(vendorEntity.getPassword()));
        }
        return VendorEntity.builder().build();
    }

    public void clearOtp(String key) {
        otpCache.invalidate(key);

    }
}



