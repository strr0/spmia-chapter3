package com.strr.licenses.service;

import com.strr.licenses.config.ServiceConfig;
import com.strr.licenses.model.License;
import com.strr.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;
    private final ServiceConfig serviceConfig;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig) {
        this.licenseRepository = licenseRepository;
        this.serviceConfig = serviceConfig;
    }

    public License getLicense(String organizationId, String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        return license.withComment(serviceConfig.getExampleProperty());
    }

    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license){
        license.withId( UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    public void updateLicense(License license){
        licenseRepository.save(license);
    }

    public void deleteLicense(String licenseId){
        licenseRepository.deleteById(licenseId);
    }
}
